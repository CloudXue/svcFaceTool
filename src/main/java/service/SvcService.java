package service;

import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-11<br>
 * <br>
 */
public interface SvcService {
    public void initSystem();
    /**
     * 生成sql语句
     * @param uc
     * @return
     */
    public String generateSql(String uc) ;

    public Vector<Vector<String>> getUcIn(String uc) throws Exception;
    public Vector<Vector<String>> getUcOut(String uc) throws Exception;

    /**
     * 获得字典和字典缓存
     * @return
     */
    public List<String> getDictionies();
    /**
     * 获得辅助查询
     * @return
     */
    public Map<String,String> getMidsearch();
}
