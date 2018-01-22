package service.impl;

import bean.BaseBean;
import bean.HsiRight;
import bean.TsvcSql;
import dao.HsiRightDao;
import dao.TsvcSqlDao;
import dao.impl.HsiRightDaoImpl;
import dao.impl.TsvcSqlDaoImpl;
import service.SvcService;
import util.DateUtil;


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
            sb.append(tsvcSql.generateInsertSql());
            sb.append("\n");
            sb.append("\n");



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
