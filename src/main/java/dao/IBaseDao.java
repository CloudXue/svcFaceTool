package dao;

/**
 * Created by lyd on 2017-07-06.
 */
public interface IBaseDao {

    /**
     * 开启事务
     */
    public void openTransaction() throws Exception ;
    /**
     * 回滚事务
     */
    public void rollbackTransaction() throws Exception ;
    /**
     * 提交事务
     */
    public void commitTransaction() throws Exception ;
}
