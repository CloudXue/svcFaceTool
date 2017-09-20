package util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.1.5 <br>
 * 开发人员: lyd
 * 开发时间: 2017-09-20<br>
 * <br>
 */
public class LogUtil {
    private static LogUtil logUtil;
    private   Logger logger ;
    private static String fileLogPath;
    private LogUtil() {
        if(StringUtils.isNullOrEmpty(fileLogPath)){
            fileLogPath=LogUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            fileLogPath=fileLogPath.substring(1,fileLogPath.length()-1)+File.separator+"log"+File.separator;
        }else{
            fileLogPath+= File.separator+"log"+File.separator;
        }
        System.out.println(fileLogPath);
        System.setProperty("log.base",fileLogPath);
        PropertyConfigurator.configure(LogUtil.class.getResourceAsStream("/log4j.properties") );
        logger  =  Logger.getLogger(LogUtil.class );
    }
    public static void main(String[] args) {
       /* String path=LogUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String path2 = LogUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        String path3 = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(path);
        System.out.println("path 1 = " + path);
        System.out.println("path 2 = " + path2);
        System.out.println("path 3 = " + path3);*/
       for(int i=0;i<100;i++){
           new Thread(new Runnable() {
               @Override
               public void run() {
                   LogUtil.error("hahah");
               }
           }).start();
       }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtil.error("hahah");


    }
    private Logger getlog(){
        return this.logger;
    }

    public static Logger getLogger(){
        if(logUtil==null){
            //System.out.println("进入null");
            synchronized(LogUtil.class){
                if (logUtil==null){
                    //System.out.println("new LogUtil");
                    logUtil=new LogUtil();
                }
            }
        }/*else{
            //System.out.println("未进入null");
        }*/
       return logUtil.getlog();
    }

    public static void info(String msg){
        LogUtil.getLogger().info(msg);
    }

    public static void error(String msg){
        LogUtil.getLogger().error(msg);
    }
    public static void error(String msg, Throwable t){
        LogUtil.getLogger().error(msg,t);
    }

    public static void setFileLogPath(String fileLogPat) {
        fileLogPath = fileLogPat;
    }

}
