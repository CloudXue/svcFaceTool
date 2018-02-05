package service;

import java.util.Vector;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-11<br>
 * <br>
 */
public interface SvcService {
    /**
     * 生成sql语句
     * @param uc
     * @return
     */
    public String generateSql(String uc) ;

    public Vector<Vector<String>> getUcIn(String uc) throws Exception;
    public Vector<Vector<String>> getUcOut(String uc) throws Exception;
}
