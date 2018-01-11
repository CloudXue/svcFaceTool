package dao;

import bean.BaseBean;

/**
 * Created by lyd on 2017-07-06.
 */
public interface IBaseDao<TDtoModel extends BaseBean>  {

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

    public int del(TDtoModel bean)throws Exception;
    public boolean update(TDtoModel bean)throws Exception;
    public boolean insert(TDtoModel bean)throws Exception;
}
