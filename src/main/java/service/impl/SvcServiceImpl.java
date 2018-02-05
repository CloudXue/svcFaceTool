package service.impl;

import bean.*;
import dao.HsiRightDao;
import dao.TsvcInterfaceDao;
import dao.TsvcSqlDao;
import dao.TsvcViewconfigDao;
import dao.impl.HsiRightDaoImpl;
import dao.impl.TsvcInterfaceDaoImpl;
import dao.impl.TsvcSqlDaoImpl;
import dao.impl.TsvcViewconfigDaoImpl;
import service.SvcService;
import util.DateUtil;
import util.StringUtils;

import java.util.List;
import java.util.Vector;


/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-11<br>
 * <br>
 */
public class SvcServiceImpl implements SvcService {
    HsiRightDao hsiRightDao=new HsiRightDaoImpl();
    TsvcSqlDao tsvcSqlDao=new TsvcSqlDaoImpl();
    TsvcViewconfigDao tsvcViewconfigDao=new TsvcViewconfigDaoImpl();
    TsvcInterfaceDao tsvcInterfaceDao=new TsvcInterfaceDaoImpl();
    private final static String sqlHead="--*********************************************************\n" +
            "--FUNDCRM系统初始化脚本\n" +
            "--创建日期：%1$s\n" +
            "--数据库  ：fundcrm/fundcrm@localdb\n" +
            "--*********************************************************";



    @Override
    public String generateSql(String uc)  {
        StringBuilder sb= null;
        try {
            sb = new StringBuilder();
            sb.append(String.format(sqlHead, DateUtil.getCurrentDateTimeString()));
            sb.append("\n");
            sb.append("\n");
            sb.append(HsiRight.generateHead());
            sb.append(BaseBean.generateDelSql(HsiRight.tableName,uc));
            sb.append("\n");
            HsiRight hsiRight=hsiRightDao.getHsiRigh(uc);
            sb.append(hsiRight.generateInsertSql());
            sb.append("\n");
            sb.append("\n");
            sb.append(TsvcSql.generateHead());
            TsvcSql tsvcSql=tsvcSqlDao.getTsvcSql(uc);
            sb.append(BaseBean.generateDelSql(TsvcSql.tableName,uc));
            sb.append("\n");
            if(tsvcSql!=null){
                sb.append(tsvcSql.generateInsertSql());
            }
            sb.append("\n");
            sb.append("\n");
            sb.append(TsvcInterface.generateHead());
            sb.append(BaseBean.generateDelSql(TsvcInterface.tableName,uc));
            sb.append("\n");
            List<TsvcInterface> tsvcInterfaceList=tsvcInterfaceDao.getTsvcInterfaceList(uc);
            for(TsvcInterface tsvcInterface : tsvcInterfaceList){
                sb.append(tsvcInterface.generateInsertSql());
            }
            sb.append("\n");
            sb.append("\n");
            sb.append(TsvcViewconfig.generateHead());
            sb.append(BaseBean.generateDelSql(TsvcViewconfig.tableName,uc));
            sb.append("\n");
            List<TsvcViewconfig> tsvcViewconfigsList=tsvcViewconfigDao.getTsvcViewconfig(uc);
            for(TsvcViewconfig tsvcViewconfig : tsvcViewconfigsList){
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
        Vector<Vector<String>> retVector=new Vector<Vector<String>>();
        List<TsvcInterface> tsvcInterfaceList=tsvcInterfaceDao.getTsvcInterfaceListHasOrder(uc);
        if(tsvcInterfaceList!=null){
            for(TsvcInterface tsvcInterface : tsvcInterfaceList){
                Vector<String> vector=new Vector<String>();
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
        Vector<Vector<String>> retVector=new Vector<Vector<String>>();
        List<TsvcViewconfig> tsvcViewconfigList=tsvcViewconfigDao.getTsvcViewconfigHasOrder(uc);
        if(tsvcViewconfigList!=null){
            for(TsvcViewconfig tsvcViewconfig : tsvcViewconfigList){
                Vector<String> vector=new Vector<String>();
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

    private String valueOf(String str){
        return StringUtils.valueOf(str);
    }
}
