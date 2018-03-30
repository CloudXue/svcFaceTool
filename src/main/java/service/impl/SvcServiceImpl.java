package service.impl;

import bean.*;
import constant.ENWarningLevel;
import constant.EnActionEvent;
import control.MyActionListener;
import dao.*;
import dao.impl.*;
import exception.ViewException;
import org.apache.log4j.Logger;
import service.SvcService;
import util.DateUtil;
import util.LogUtil;
import util.StringUtils;
import util.SvcUtil;

import java.util.*;


/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-11<br>
 * <br>
 */
public class SvcServiceImpl implements SvcService {
    private static Logger logger= LogUtil.getLogger(SvcServiceImpl.class);

    MyActionListener viewListener;
    SvcDao svcDao=DaoFactory.getSvcDao();
    HsiRightDao hsiRightDao = DaoFactory.getHsiRightDao();
    TsvcSqlDao tsvcSqlDao = DaoFactory.getTsvcSqlDao();
    TsvcViewconfigDao tsvcViewconfigDao =DaoFactory.getTsvcViewconfigDao();
    TsvcInterfaceDao tsvcInterfaceDao = DaoFactory.getTsvcInterfaceDao();
    private final static String sqlHead = "--*********************************************************\r\n" +
            "--FUNDCRM系统初始化脚本\r\n" +
            "--创建日期：%1$s\r\n" +
            "--数据库  ：fundcrm/fundcrm@localdb\r\n" +
            "--*********************************************************";

    public void initSystem() {
        try {
            //基础数据需要同步加载
            SystemData.init();
        } catch (Exception e) {
            logger.error("基础数据加载异常",e);
            throwErrorMsg(EnActionEvent.SYSTEM_FAILSTART,"基础数据加载异常"+e.getMessage());
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SvcUtil.init();
                    throwErrorMsg(EnActionEvent.SYSTEM_SUCSTART,"启动成功");
                } catch (Exception e) {
                    throwErrorMsg(EnActionEvent.SYSTEM_FAILSTART,"启动成功");
                }
            }
        }).start();
    }

    public List<SqlFieldType> findSqlField(String uc){
        /**
         * 1、处理sql
         * 2、查询所有字段
         * 3、获取所有表，
         * 4、获取注释，表名+字段名
         * 5、字段匹配第一注释，
         * 6、todo，暂时不做。一个字段有多个时候，由列表下拉出
         */
        List<SqlFieldType> field= new ArrayList<SqlFieldType>();
        try {
            TsvcSql tsvcSql= tsvcSqlDao.getTsvcSql(uc);
            if(tsvcSql==null){
                return field;
            }
            if(StringUtils.isNullOrEmpty(tsvcSql.getC_sqlstatement())){
                return field;
            }

            String sql=analysisUcSql(tsvcSql.getC_sqlstatement());
            List<String > sourcetableName=getTableName(sql);
            List<String> tableNameList=new ArrayList<>();
            //<editor-fold desc="处理R table">
            for(String tableName : sourcetableName){
                String table=tableName;
                if(table.startsWith("R")){
                    table=table.substring(1,table.length());
                    sql=sql.replaceAll(tableName,table);
                }
                tableNameList.add(table.toUpperCase());
            }
            //</editor-fold>
            field = svcDao.findField(sql);

            Map<String,String> comm=svcDao.findFieldComments(tableNameList);
            for(Map.Entry<String,String> entry : comm.entrySet()){
                for(SqlFieldType sqlFieldType : field){
                    if(entry.getKey().toUpperCase().contains("#"+sqlFieldType.getField().toUpperCase()+"#")){
                        sqlFieldType.setFieldNameMap(entry.getKey(),entry.getValue());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("查询sql配置字段异常",e);
            throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
        }
        return field;
    }

    @Override
    public String generateSql(String uc) {
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            sb.append(String.format(sqlHead, DateUtil.getCurrentDateTimeString()));
            sb.append("\r\n");
            sb.append("\r\n");
            sb.append(HsiRight.generateHead());
            sb.append(BaseBean.generateDelSql(HsiRight.tableName, uc));
            sb.append("\r\n");
            HsiRight hsiRight = hsiRightDao.getHsiRigh(uc);
            sb.append(hsiRight.generateInsertSql());
            sb.append("\r\n");
            sb.append("\r\n");
            sb.append(TsvcSql.generateHead());
            TsvcSql tsvcSql = tsvcSqlDao.getTsvcSql(uc);
            sb.append(BaseBean.generateDelSql(TsvcSql.tableName, uc));
            sb.append("\r\n");
            if (tsvcSql != null) {
                sb.append(tsvcSql.generateInsertSql());
            }
            sb.append("\r\n");
            sb.append("\r\n");
            sb.append(TsvcInterface.generateHead());
            sb.append(BaseBean.generateDelSql(TsvcInterface.tableName, uc));
            sb.append("\r\n");
            List<TsvcInterface> tsvcInterfaceList = tsvcInterfaceDao.getTsvcInterfaceList(uc);
            for (TsvcInterface tsvcInterface : tsvcInterfaceList) {
                sb.append(tsvcInterface.generateInsertSql());
            }
            sb.append("\r\n");
            sb.append("\r\n");
            sb.append(TsvcViewconfig.generateHead());
            sb.append(BaseBean.generateDelSql(TsvcViewconfig.tableName, uc));
            sb.append("\r\n");
            List<TsvcViewconfig> tsvcViewconfigsList = tsvcViewconfigDao.getTsvcViewconfig(uc);
            for (TsvcViewconfig tsvcViewconfig : tsvcViewconfigsList) {
                sb.append(tsvcViewconfig.generateInsertSql());
            }
            sb.append("\r\n");
            sb.append("commit;");


        } catch (Exception e) {
            logger.error("生成sql异常",e);
            throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
        }


        /*try {
            return new String(sb.toString().getBytes("UTF-8"),"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        return sb.toString();
    }

    @Override
    public Vector<Vector<String>> getUcIn(String uc)  {
        Vector<Vector<String>> retVector = new Vector<Vector<String>>();
        List<TsvcInterface> tsvcInterfaceList = null;
        try {
            tsvcInterfaceList = tsvcInterfaceDao.getTsvcInterfaceListHasOrder(uc);
        } catch (Exception e) {
            logger.error("查询配置输入输出异常：",e);
            throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
            return retVector;
        }
        if (tsvcInterfaceList != null) {
            for (TsvcInterface tsvcInterface : tsvcInterfaceList) {
                Vector<String> vector = new Vector<String>();
                vector.add(valueOf(tsvcInterface.getC_flag()));
                vector.add(valueOf(tsvcInterface.getC_packflag()));
                vector.add(valueOf(tsvcInterface.getC_fieldname()));
                vector.add(valueOf(tsvcInterface.getC_explain()));
                vector.add(valueOf(tsvcInterface.getC_property()));
                vector.add(valueOf(tsvcInterface.getL_len()));
                vector.add(valueOf(tsvcInterface.getL_declen()));
                vector.add(valueOf(tsvcInterface.getC_fieldtype()));
                vector.add(valueOf(tsvcInterface.getC_notnull()));
                vector.add(valueOf(tsvcInterface.getC_fieldflag()));
                vector.add(valueOf(tsvcInterface.getC_condition()));
                vector.add(valueOf(tsvcInterface.getL_no()));
                vector.add(valueOf(tsvcInterface.getC_viewlevel()));
                vector.add(valueOf(tsvcInterface.getC_viewtype()));
                vector.add(valueOf(tsvcInterface.getC_dicname()));
                vector.add(valueOf(tsvcInterface.getC_midsearchname()));
                vector.add(valueOf(tsvcInterface.getC_isdefault()));
                vector.add(valueOf(tsvcInterface.getC_value()));

                vector.add(valueOf(tsvcInterface.getC_existvalue()));
                vector.add(valueOf(tsvcInterface.getRowid()));
                retVector.add(vector);
            }
        }
        return retVector;
    }

    @Override
    public Vector<Vector<String>> getUcOut(String uc) {
        Vector<Vector<String>> retVector = new Vector<Vector<String>>();
        List<TsvcViewconfig> tsvcViewconfigList = null;
        try {
            tsvcViewconfigList = tsvcViewconfigDao.getTsvcViewconfigHasOrder(uc);
        } catch (Exception e) {
            logger.error("查询配置显示异常：",e);
            throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
            return retVector;
        }
        if (tsvcViewconfigList != null) {
            for (TsvcViewconfig tsvcViewconfig : tsvcViewconfigList) {
                Vector<String> vector = new Vector<String>();
                vector.add(valueOf(tsvcViewconfig.getC_businflag()));
                vector.add(valueOf(tsvcViewconfig.getC_property()));
                vector.add(valueOf(tsvcViewconfig.getC_viewlevel()));
                vector.add(valueOf(tsvcViewconfig.getC_viewtype()));
                vector.add(valueOf(tsvcViewconfig.getC_dicname()));
                vector.add(valueOf(tsvcViewconfig.getC_viewname()));
                vector.add(valueOf(tsvcViewconfig.getL_no()));
                vector.add(valueOf(tsvcViewconfig.getC_hyperlink()));
                vector.add(valueOf(tsvcViewconfig.getC_frametype()));
                vector.add(valueOf(tsvcViewconfig.getC_len()));
                vector.add(valueOf(tsvcViewconfig.getC_pattern()));
                vector.add(valueOf(tsvcViewconfig.getC_hiddenelem()));
                vector.add(valueOf(tsvcViewconfig.getC_edittype()));
                vector.add(valueOf(tsvcViewconfig.getC_defaultvalue()));
                vector.add(valueOf(tsvcViewconfig.getC_limit()));
                vector.add(valueOf(tsvcViewconfig.getC_midsearchname()));
                vector.add(valueOf(tsvcViewconfig.getC_event()));
                vector.add(valueOf(tsvcViewconfig.getC_inputtype()));
                vector.add(valueOf(tsvcViewconfig.getRowid()));
                retVector.add(vector);
            }
        }
        return retVector;
    }

    @Override
    public List<String> getDictionies() throws Exception {
        List<String> retList = new ArrayList<String>();
        String sql = "select * from (select t.c_caption caption from tdictionarycache t where  t.c_keyvalue='#' " +
                " union all " +
                " select  t.c_caption caption from tdictionary t where t.c_keyvalue='#' )" +
                " order by caption";
        try {
            List<Map<String, Object>> retMapList = hsiRightDao.queryForList(sql);
            for (Map<String, Object> map : retMapList) {
                retList.add(valueOf(map.get("CAPTION")));
            }
        } catch (Exception e) {
            retList.clear();
            logger.error("获取字典数据异常",e);
            throw e;
        }
        return retList;
    }

    @Override
    public Map<String, String> getMidsearch() throws Exception {
        Map<String, String> retMap = new LinkedHashMap<String, String>();
        String sql = "select t.c_caption||'-'||t.c_type key,t.c_type value from tsvcmidsearch t  order by t.c_caption";
        try {
            List<Map<String, Object>> retMapList = hsiRightDao.queryForList(sql);
            for (Map<String, Object> map : retMapList) {
                retMap.put(valueOf(map.get("KEY")),valueOf(map.get("VALUE")));
            }
        } catch (Exception e) {
            retMap.clear();
            logger.error("获取辅助查询数据异常",e);
            throw e;
        }
        return retMap;
    }

    @Override
    public void saveTsvcInterface(List<TsvcInterface> tsvcInterfaceList,String uc) {

        try {
            tsvcInterfaceDao.openTransaction();
            //先删除
            String deleteSql="delete TSVCINTERFACE where C_FUNCTIONNO='"+uc+"'";
            tsvcInterfaceDao.executeSql(deleteSql);
            if(tsvcInterfaceList!=null || tsvcInterfaceList.size()>0){
                //添加
                for(TsvcInterface tsvcInterface: tsvcInterfaceList){
                    if(!tsvcInterfaceDao.insert(tsvcInterface)){
                        throw new Exception("保存数据失败"+tsvcInterface.getC_fieldname());
                    }
                }
            }
            //提交事务
            tsvcInterfaceDao.commitTransaction();
        } catch (Exception e) {
            tsvcInterfaceDao.rollbackTransaction();
            logger.error("保存功能输入输出异常",e);
            throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
        }
    }

    @Override
    public void saveTsvcViewconfig(List<TsvcViewconfig> tsvcViewconfigsList,String uc) {

        try {
            tsvcViewconfigDao.openTransaction();
            //先删除
            String deleteSql="delete TSVCVIEWCONFIG where C_FUNCTIONNO='"+uc+"'";
            tsvcViewconfigDao.executeSql(deleteSql);
            //添加
            if(tsvcViewconfigsList!=null || tsvcViewconfigsList.size()>0){
                for(TsvcViewconfig tsvcViewconfig: tsvcViewconfigsList){
                    if(!tsvcViewconfigDao.insert(tsvcViewconfig)){
                        throw new Exception("保存数据失败"+tsvcViewconfig.getC_property());
                    }
                }
            }
            //提交事务
            tsvcViewconfigDao.commitTransaction();
        } catch (Exception e) {
            tsvcViewconfigDao.rollbackTransaction();
            logger.error("保存功能查询显示异常",e);
            throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
        }
    }

    @Override
    public List<TsvcInterface> getOutTsvcInterface(String uc) {
        List<TsvcInterface> tsvcInterfaceList=null;
        try {
            tsvcInterfaceList=tsvcInterfaceDao.getOutTsvcInterface(uc);
        } catch (Exception e) {
            tsvcInterfaceList=new ArrayList<>();
            logger.error("获取查询显示数据异常：",e);
            throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
        }
        return tsvcInterfaceList;
    }

    private String valueOf(Object str) {
        return StringUtils.valueOf(str);
    }

    private String analysisUcSql(String sql){
        String retSql=sql.replaceAll("@where_rep@"," ").replaceAll("@WHERE_REP@"," ");
        if(retSql.contains(":")){
            String[] sqlWords=retSql.split("=|\\s+");
            for(String word : sqlWords){
                if(word.contains(":")){
                    if(word.contains("(")){
                        word=word.replaceAll("\\(","");
                    }
                    if(word.contains(")")){
                        word=word.replaceAll("\\)","");
                    }
                    retSql=retSql.replaceAll(word,"''");
                }
            }
        }
        return retSql;
    }
    private List<String> getTableName(String sql){
        List<String> tableName=new ArrayList<String>();
        String[] sqlWords=sql.split("\\s+|\\(");
        boolean hasFrom=false;

        String currentTab="";
        for(String word : sqlWords){
            if(hasFrom){
                if(word.equalsIgnoreCase("select")){
                    hasFrom=false;
                    continue;
                }
                //todo 临时过滤 start with的词表，查询包含下级组织时会出现
                if(word.equalsIgnoreCase("start")){
                    hasFrom=false;
                    currentTab=null;
                    continue;
                }
                if(word.equalsIgnoreCase("where")||word.equalsIgnoreCase("(")){
                    if(StringUtils.isNotNullAndNotEmpty(currentTab)){
                        if(!tableName.contains(currentTab)){
                            tableName.add(currentTab);
                        }

                        currentTab=null ;
                    }
                    hasFrom=false;
                    currentTab=null;
                    continue;
                }
                if(StringUtils.isNotNullAndNotEmpty(currentTab)){
                    if(word.contains(",")||word.equalsIgnoreCase("join")){
                        if(!tableName.contains(currentTab)){
                            tableName.add(currentTab);
                        }
                        currentTab=null ;
                        if(word.contains(",")){
                            if(word.indexOf(",")<word.length()){
                                currentTab=word.substring(word.indexOf(",")+1,word.length());
                            }
                        }

                    }
                }else{
                    if(word.contains(",")){
                        currentTab=word.replaceAll(",","") ;
                        if(!tableName.contains(currentTab)){
                            tableName.add(currentTab);
                        }
                        currentTab=null;
                        if(word.indexOf(",")<word.length()){
                            currentTab=word.substring(word.indexOf(","),word.length());
                        }
                    }else{
                        currentTab=word;
                    }
                }
            }else{
                if(word.equalsIgnoreCase("FROM")){
                    hasFrom=true;
                }else  if(word.equalsIgnoreCase("where")){
                    hasFrom=false;
                }
            }
        }
        if(StringUtils.isNotNullAndNotEmpty(currentTab)){
            if(!tableName.contains(currentTab)){
                tableName.add(currentTab);
            }
            currentTab=null ;
        }
        return tableName;
    }
    public  void throwErrorMsg(EnActionEvent enActionEvent, String msg){
        //logger.info("提交事件："+enActionEvent.getCmd()+",消息："+msg);
        viewListener.actionPerformedFromService(enActionEvent.getWarningLevel(),MyActionListener.getActionEvent(enActionEvent,msg));
        if(enActionEvent.getWarningLevel().equals(ENWarningLevel.ERROR)){
            throw new ViewException(msg);
        }
    }

    public void setViewListener(MyActionListener viewListener) {
        this.viewListener = viewListener;
    }
    @Override
    public boolean exchange(TsvcInterface current, TsvcInterface opp) {
        if(!current.getC_flag().equals(opp.getC_flag())){
            return false;
        }
        if(!canMove(current)){
            return false;
        }
        if(!canMove(opp)){
            return false;
        }
        String currentno=current.getL_no();
        current.setL_no(opp.getL_no());
        opp.setL_no(currentno);
        try {
            tsvcInterfaceDao.update(current,new String[]{"l_no"});
            tsvcInterfaceDao.update(opp,new String[]{"l_no"});
        } catch (Exception e) {
            throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
        }
        return true;

    }


    @Override
    public boolean exchange(TsvcViewconfig current, TsvcViewconfig opp) {
        if(!canMove(current)){
            return false;
        }
        if(!canMove(opp)){
            return false;
        }
        String currentno=current.getL_no();
        current.setL_no(opp.getL_no());
        opp.setL_no(currentno);
        try {
            tsvcViewconfigDao.update(current,new String[]{"l_no"});
            tsvcViewconfigDao.update(opp,new String[]{"l_no"});
        } catch (Exception e) {
            throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
        }
        return true;
    }

    private boolean canMove(TsvcInterface current){
        if(current==null){
            return false;
        }
        if(StringUtils.isNullOrEmpty(current.getL_no())){
            throwErrorMsg(EnActionEvent.COMMOM_ERROR,"序号为空");
        }
        try {
            Integer.parseInt(current.getL_no());
        } catch (NumberFormatException e) {
            throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
        }
        return true;
    }

    private boolean canMove(TsvcViewconfig current){
        if(current==null){
            return false;
        }
        if(StringUtils.isNullOrEmpty(current.getL_no())){
            throwErrorMsg(EnActionEvent.COMMOM_ERROR,"序号为空");
        }
        try {
            Integer.parseInt(current.getL_no());
        } catch (NumberFormatException e) {
            throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
        }
        return true;
    }
}
