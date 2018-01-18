package service.impl;

import bean.HsiRight;
import dao.HsiRightDao;
import dao.impl.HsiRightDaoImpl;
import service.SvcService;
import util.DateUtil;

import java.io.UnsupportedEncodingException;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-11<br>
 * <br>
 */
public class SvcServiceImpl implements SvcService {
    HsiRightDao hsiRightDao=new HsiRightDaoImpl();
    private final static String sqlHead="--*********************************************************\n" +
            "--FUNDCRM系统初始化脚本\n" +
            "--创建日期：%1$s\n" +
            "--数据库  ：fundcrm/fundcrm@localdb\n" +
            "--*********************************************************";
    private final static String sqlDelete="DELETE FROM %1$s where C_FUNCTIONNO = '%2$s';";
    private final static String hsiRightSql="INSERT INTO HSI_RIGHT" +
            "(C_RIGHTCODE,C_RIGHTNAME,C_CLASS,C_SYSNAME,C_FUNCTIONNO," +
            "C_JAVACLASS,C_JAVAMETHOD,C_CLIENTPROGS,C_TABLENAME,C_UCTYPE,C_ISLIMIT) VALUES" +
            " ('%s','%s','%s','FUNDCRM','%s','%s','%s',NULL,NULL,'%s','%s');";
    @Override
    public String generateSql(String uc) {
        StringBuilder sb=new StringBuilder();
        sb.append(String.format(sqlHead, DateUtil.getCurrentDateTimeString()));
        sb.append("\n");
        sb.append("\n");
        sb.append("--****************************************************\n" +
                "--**  英文表名：HSI_RIGHT\n" +
                "--****************************************************");
        sb.append("\n");
        try {
            sb.append(generateHsi_right(uc));
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

    private String generateHsi_right(String uc) throws Exception {
        StringBuilder sb=new StringBuilder();
        sb.append(String.format(sqlDelete, HsiRight.getTableName(),uc));
        sb.append("\n");
        HsiRight hsiRight=hsiRightDao.getHsiRigh(uc);
        sb.append(String.format(hsiRightSql, hsiRight.getC_rightcode(),hsiRight.getC_rightname(),hsiRight.getC_class(),
                hsiRight.getC_functionno(),hsiRight.getC_javaclass(),hsiRight.getC_javamethod(),
                hsiRight.getC_uctype(),hsiRight.getC_islimit()));
        sb.append("\n");
        sb.append("\n");
        return sb.toString();
    }

}
