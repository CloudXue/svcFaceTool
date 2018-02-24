package service;

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
public class ServiceFactory {
    private static SvcService svcService;
    private static TsvcSqlService tsvcSqlService;
    private static UcDefineService ucDefineService;

    public static SvcService getSvcService() {
        if(svcService==null){
            synchronized (ServiceFactory.class) {
                if(svcService==null){
                    svcService=new SvcServiceImpl();
                }
            }
        }
        return svcService;
    }
    public static TsvcSqlService getTsvcSqlService() {
        if(tsvcSqlService==null){
            synchronized (ServiceFactory.class) {
                if(tsvcSqlService==null){
                    tsvcSqlService=new TsvcSqlServiceImpl();
                }
            }
        }
        return tsvcSqlService;
    }
    public static UcDefineService getUcDefineService() {
        if(ucDefineService==null){
            synchronized (ServiceFactory.class) {
                if(ucDefineService==null){
                    ucDefineService=new UcDefineServiceImpl();
                }
            }
        }
        return ucDefineService;
    }


}
