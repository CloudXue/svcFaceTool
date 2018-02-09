package service.impl;

import bean.*;
import dao.*;
import dao.impl.*;
import service.SvcService;
import util.DateUtil;
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
    SvcDao svcDao=new SvcDaoImpl();
    HsiRightDao hsiRightDao = new HsiRightDaoImpl();
    TsvcSqlDao tsvcSqlDao = new TsvcSqlDaoImpl();
    TsvcViewconfigDao tsvcViewconfigDao = new TsvcViewconfigDaoImpl();
    TsvcInterfaceDao tsvcInterfaceDao = new TsvcInterfaceDaoImpl();
    private final static String sqlHead = "--*********************************************************\n" +
            "--FUNDCRM系统初始化脚本\n" +
            "--创建日期：%1$s\n" +
            "--数据库  ：fundcrm/fundcrm@localdb\n" +
            "--*********************************************************";

    public void initSystem() {
        SvcUtil.init();
    }

    public void findSqlField(String uc){
        /**
         * todo 未完成
         * 1、处理sql
         * 2、查询所有字段
         * 3、获取所有表，
         * 4、获取注释，表名+字段名
         * 5、字段匹配第一注释，
         * 6、一个字段有多个时候，由列表下拉出
         */
        String sql=" select * from HSI_RIGHT where 1<>1 ";
        List<SqlFieldType> field= null;
        try {
            field = svcDao.findField(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String > tableName=new ArrayList<>();
        tableName.add("HSI_RIGHT");
        tableName.add("TSVCSQL");
        try {
            Map<String,String> comm=svcDao.findFieldComments(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(field);
    }

    @Override
    public String generateSql(String uc) {
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            sb.append(String.format(sqlHead, DateUtil.getCurrentDateTimeString()));
            sb.append("\n");
            sb.append("\n");
            sb.append(HsiRight.generateHead());
            sb.append(BaseBean.generateDelSql(HsiRight.tableName, uc));
            sb.append("\n");
            HsiRight hsiRight = hsiRightDao.getHsiRigh(uc);
            sb.append(hsiRight.generateInsertSql());
            sb.append("\n");
            sb.append("\n");
            sb.append(TsvcSql.generateHead());
            TsvcSql tsvcSql = tsvcSqlDao.getTsvcSql(uc);
            sb.append(BaseBean.generateDelSql(TsvcSql.tableName, uc));
            sb.append("\n");
            if (tsvcSql != null) {
                sb.append(tsvcSql.generateInsertSql());
            }
            sb.append("\n");
            sb.append("\n");
            sb.append(TsvcInterface.generateHead());
            sb.append(BaseBean.generateDelSql(TsvcInterface.tableName, uc));
            sb.append("\n");
            List<TsvcInterface> tsvcInterfaceList = tsvcInterfaceDao.getTsvcInterfaceList(uc);
            for (TsvcInterface tsvcInterface : tsvcInterfaceList) {
                sb.append(tsvcInterface.generateInsertSql());
            }
            sb.append("\n");
            sb.append("\n");
            sb.append(TsvcViewconfig.generateHead());
            sb.append(BaseBean.generateDelSql(TsvcViewconfig.tableName, uc));
            sb.append("\n");
            List<TsvcViewconfig> tsvcViewconfigsList = tsvcViewconfigDao.getTsvcViewconfig(uc);
            for (TsvcViewconfig tsvcViewconfig : tsvcViewconfigsList) {
                sb.append(tsvcViewconfig.generateInsertSql());
            }
            sb.append("\n");
            sb.append("commit;");


        } catch (Exception e) {
            e.printStackTrace();
        }


        /*try {
            return new String(sb.toString().getBytes("UTF-8"),"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        return sb.toString();
    }

    @Override
    public Vector<Vector<String>> getUcIn(String uc) throws Exception {
        Vector<Vector<String>> retVector = new Vector<Vector<String>>();
        List<TsvcInterface> tsvcInterfaceList = tsvcInterfaceDao.getTsvcInterfaceListHasOrder(uc);
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
    public Vector<Vector<String>> getUcOut(String uc) throws Exception {
        Vector<Vector<String>> retVector = new Vector<Vector<String>>();
        List<TsvcViewconfig> tsvcViewconfigList = tsvcViewconfigDao.getTsvcViewconfigHasOrder(uc);
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
    public List<String> getDictionies() {
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
            e.printStackTrace();
        }
        return retList;
    }

    @Override
    public Map<String, String> getMidsearch() {
        Map<String, String> retMap = new LinkedHashMap<String, String>();
        String sql = "select t.c_caption||'-'||t.c_type key,t.c_type value from tsvcmidsearch t  order by t.c_caption";
        try {
            List<Map<String, Object>> retMapList = hsiRightDao.queryForList(sql);
            for (Map<String, Object> map : retMapList) {
                retMap.put(valueOf(map.get("KEY")),valueOf(map.get("VALUE")));
            }
        } catch (Exception e) {
            retMap.clear();
            e.printStackTrace();
        }
        return retMap;
    }

    @Override
    public void saveTsvcInterface(List<TsvcInterface> tsvcInterfaceList) {
        if(tsvcInterfaceList==null || tsvcInterfaceList.size()<1){
            return;
        }
        try {
            tsvcInterfaceDao.openTransaction();
            //先删除
            String uc=tsvcInterfaceList.get(0).getC_functionno();
            String deleteSql="delete TSVCINTERFACE where C_FUNCTIONNO='"+uc+"'";
            tsvcInterfaceDao.executeSql(deleteSql);
            //添加
            for(TsvcInterface tsvcInterface: tsvcInterfaceList){
                if(!tsvcInterfaceDao.insert(tsvcInterface)){
                    throw new Exception("保存数据失败"+tsvcInterface.getC_fieldname());
                }
            }
            //提交事务
            tsvcInterfaceDao.commitTransaction();
        } catch (Exception e) {
            try {
                tsvcInterfaceDao.rollbackTransaction();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveTsvcViewconfig(List<TsvcViewconfig> tsvcViewconfigsList) {
        if(tsvcViewconfigsList==null || tsvcViewconfigsList.size()<1){
            return;
        }
        try {
            tsvcViewconfigDao.openTransaction();
            //先删除
            String uc=tsvcViewconfigsList.get(0).getC_functionno();
            String deleteSql="delete TSVCVIEWCONFIG where C_FUNCTIONNO='"+uc+"'";
            tsvcViewconfigDao.executeSql(deleteSql);
            //添加
            for(TsvcViewconfig tsvcViewconfig: tsvcViewconfigsList){
                if(!tsvcViewconfigDao.insert(tsvcViewconfig)){
                    throw new Exception("保存数据失败"+tsvcViewconfig.getC_property());
                }
            }
            //提交事务
            tsvcViewconfigDao.commitTransaction();
        } catch (Exception e) {
            try {
                tsvcViewconfigDao.rollbackTransaction();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<TsvcInterface> getOutTsvcInterface(String uc) {
        List<TsvcInterface> tsvcInterfaceList=null;
        try {
            tsvcInterfaceList=tsvcInterfaceDao.getOutTsvcInterface(uc);
        } catch (Exception e) {
            tsvcInterfaceList=new ArrayList<>();
            e.printStackTrace();
        }
        return tsvcInterfaceList;
    }

    private String valueOf(Object str) {
        return StringUtils.valueOf(str);
    }
}
