package dao.impl;

import bean.HsiRight;
import dao.BaseDao;
import dao.GenCodeDao;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class GenCodeDaoImpl  extends BaseDao<HsiRight> implements GenCodeDao {
    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected String[] createUpdateFieldNames() {
        return new String[0];
    }

    @Override
    protected String[] createInsertFieldNames() {
        return new String[0];
    }
}
