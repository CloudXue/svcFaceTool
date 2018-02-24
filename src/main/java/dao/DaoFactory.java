package dao;

import bean.TsvcInterface;
import dao.impl.*;
import service.SvcService;
import service.TsvcSqlService;
import service.UcDefineService;
import service.impl.SvcServiceImpl;
import service.impl.TsvcSqlServiceImpl;
import service.impl.UcDefineServiceImpl;

/**
 * ����˵��: <br>
 * ϵͳ�汾: 1.0.0 <br>
 * ������Ա: lyd
 * ����ʱ��: 2018-02-24<br>
 * <br>
 */
public class DaoFactory {
    private static HsiRightDao hsiRightDao;
    private static SvcDao svcDao;
    private static TsvcInterfaceDao tsvcInterfaceDao;
    private static TsvcSqlDao tsvcSqlDao;
    private static TsvcViewconfigDao tsvcViewconfigDao;


    public static HsiRightDao getHsiRightDao() {
        if(hsiRightDao==null){
            synchronized (DaoFactory.class) {
                if(hsiRightDao==null){
                    hsiRightDao=new HsiRightDaoImpl();
                }
            }
        }
        return hsiRightDao;
    }
    public static SvcDao getSvcDao() {
        if(svcDao==null){
            synchronized (DaoFactory.class) {
                if(svcDao==null){
                    svcDao=new SvcDaoImpl();
                }
            }
        }
        return svcDao;
    }
    public static TsvcInterfaceDao getTsvcInterfaceDao() {
        if(tsvcInterfaceDao==null){
            synchronized (DaoFactory.class) {
                if(tsvcInterfaceDao==null){
                    tsvcInterfaceDao=new TsvcInterfaceDaoImpl();
                }
            }
        }
        return tsvcInterfaceDao;
    }
    public static TsvcSqlDao getTsvcSqlDao() {
        if(tsvcSqlDao==null){
            synchronized (DaoFactory.class) {
                if(tsvcSqlDao==null){
                    tsvcSqlDao=new TsvcSqlDaoImpl();
                }
            }
        }
        return tsvcSqlDao;
    }
    public static TsvcViewconfigDao getTsvcViewconfigDao() {
        if(tsvcViewconfigDao==null){
            synchronized (DaoFactory.class) {
                if(tsvcViewconfigDao==null){
                    tsvcViewconfigDao=new TsvcViewconfigDaoImpl();
                }
            }
        }
        return tsvcViewconfigDao;
    }


}
