package service.impl;

import bean.HsiRight;
import dao.HsiRightDao;
import dao.impl.HsiRightDaoImpl;
import service.UcDefineService;

import java.util.List;

/**
 * Created by lyd on 2017-06-30.
 */
public class UcDefineServiceImpl implements UcDefineService {
    HsiRightDao hsiRightDao=new HsiRightDaoImpl();
    @Override
    public List<HsiRight> getAllUc() throws Exception {
        return hsiRightDao.getAllData();
    }

    @Override
    public HsiRight getUc(String functionno) throws Exception {
        return hsiRightDao.getHsiRigh(functionno);
    }

    @Override
    public List<HsiRight> getAllUc(String condition) throws Exception {
        return hsiRightDao.getHsiRighFuzzy(condition);
    }
}
