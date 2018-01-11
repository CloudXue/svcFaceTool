package dao.impl;

import bean.TsvcSql;
import dao.BaseDao;
import dao.TsvcSqlDao;
import util.BeanUtils;

import java.util.List;
import java.util.Map;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-11<br>
 * <br>
 */
public class TsvcSqlDaoImpl extends BaseDao<TsvcSql> implements TsvcSqlDao {
    @Override
    protected String getTableName() {
        return TsvcSql.getTableName();
    }

    @Override
    protected String[] createUpdateFieldNames() {
        return new String[]{"c_functionno","c_sqlstatement","c_orderby","c_sqltype","c_datasource"};
    }

    @Override
    protected String[] createInsertFieldNames() {
        return new String[]{"c_functionno","c_sqlstatement","c_orderby","c_sqltype","c_datasource"};
    }

    @Override
    public TsvcSql getTsvcSql(String ucNo) throws Exception {
        String sqlWhere=" where C_FUNCTIONNO ='"+ucNo+"'";
        TsvcSql tsvcSql=new TsvcSql();
        List<Map<String,Object>> maplist=getData(" t.* ",sqlWhere);
        if(maplist.size()==1){
            BeanUtils.covertMapToBeanWithoutNull(tsvcSql,maplist.get(0));
        }else if(maplist.size()<1) {
            tsvcSql=null;
        }else {
            throw  new Exception("查找到多个SQL定义");
        }
        return tsvcSql;
    }
}
