package service.impl;

import bean.TsvcSql;
import dao.DaoFactory;
import dao.TsvcSqlDao;
import dao.impl.TsvcSqlDaoImpl;
import service.TsvcSqlService;
import util.StringUtils;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-11<br>
 * <br>
 */
public class TsvcSqlServiceImpl implements TsvcSqlService {
    TsvcSqlDao tsvcSqlDao = DaoFactory.getTsvcSqlDao();

    @Override
    public TsvcSql getTsvcSql(String ucNo) {
        try {
            return tsvcSqlDao.getTsvcSql(ucNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void operate(TsvcSql tsvcSql) {
        if (StringUtils.isNotNullAndNotEmpty(tsvcSql.getC_functionno())) {
            TsvcSql tsvcSqlDb = null;
            try {
                tsvcSqlDb = tsvcSqlDao.getTsvcSql(tsvcSql.getC_functionno());

                if (StringUtils.isNullOrEmpty(tsvcSql.getC_sqlstatement()) ) {
                    if(tsvcSqlDb != null){
                        //如果没有sql  删除
                        tsvcSqlDao.del(tsvcSql);
                    }else{
                        //不处理
                    }
                } else {
                    if (tsvcSqlDb != null) {
                        //更新
                        tsvcSqlDao.update(tsvcSql);
                    } else {
                        //插入
                        tsvcSqlDao.insert(tsvcSql);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
