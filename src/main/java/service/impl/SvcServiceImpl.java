package service.impl;

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
    private final static String sqlHead="--*********************************************************\n" +
            "--FUNDCRM系统初始化脚本\n" +
            "--创建日期：%1$s\n" +
            "--数据库  ：fundcrm/fundcrm@localdb\n" +
            "--*********************************************************";
    @Override
    public String generateSql(String uc) {
        StringBuilder sb=new StringBuilder();
        sb.append(String.format(sqlHead, DateUtil.getCurrentDateTimeString()));

        /*try {
            return new String(sb.toString().getBytes("UTF-8"),"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        return sb.toString();
    }


}
