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

import java.util.List;


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


}
