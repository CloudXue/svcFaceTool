package service.impl;

import bean.HsiRight;
import dao.DaoFactory;
import dao.HsiRightDao;
import dao.impl.HsiRightDaoImpl;
import service.UcDefineService;
import util.LogUtil;

import java.util.List;

/**
 * Created by lyd on 2017-06-30.
 */
public class UcDefineServiceImpl implements UcDefineService {
    HsiRightDao hsiRightDao= DaoFactory.getHsiRightDao();
    @Override
    public List<HsiRight> getAllUc() throws Exception {
        return hsiRightDao.getHsiRighFuzzy("");
    }

    @Override
    public HsiRight getUc(String functionno) throws Exception {
        return hsiRightDao.getHsiRigh(functionno);
    }

    @Override
    public List<HsiRight> getAllUc(String condition) throws Exception {
        return hsiRightDao.getHsiRighFuzzy(condition);
    }


    @Override
    public void save(List<String> delList, List<HsiRight> addUcList, List<HsiRight> editUcList) throws Exception {
        //开启事务
        hsiRightDao.openTransaction();

        try {
            hsiRightDao.delete(delList);
            hsiRightDao.edit(editUcList);
            hsiRightDao.add(addUcList);
        } catch (Exception e) {
            //回滚事务
            hsiRightDao.rollbackTransaction();
            LogUtil.error("保存uc定义异常",e);
           throw e;
        }
        //提交事务
        hsiRightDao.commitTransaction();

    }
}
