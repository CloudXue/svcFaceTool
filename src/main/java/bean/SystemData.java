package bean;

import constant.ENSystem;
import util.StringUtils;

import java.io.*;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-11<br>
 * <br>
 */
public class SystemData {

    private static DataConnInfo dataConnInfo;
    private static ENSystem system=ENSystem.ATS;
    private static String defaultdir="";
    private static  boolean isusecode=false;
    public static  String versionNumber="1.1.0";

    public static void  init() throws Exception {
        String fileLogPath = SystemData.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        fileLogPath = fileLogPath.substring(0, fileLogPath.length() - 1);
        fileLogPath = fileLogPath.substring(0, fileLogPath.lastIndexOf("/"));
        fileLogPath = fileLogPath + File.separator;
        Properties prop = new Properties();
        fileLogPath= URLDecoder.decode(fileLogPath, "UTF-8");
        InputStream in = new FileInputStream(new File(fileLogPath + "config.properties"));
        try {
            prop.load(in);
            in.close();
        } catch (IOException e) {
            //logger.error("加载配置文件失败！", e);
            throw e;
        }
        dataConnInfo=new DataConnInfo();
        dataConnInfo.setJdbcurl(prop.getProperty("dburl").trim());
        dataConnInfo.setUsername(prop.getProperty("dbusername").trim());
        dataConnInfo.setPaswword(prop.getProperty("dbpassword").trim());
        String drivertypestr=prop.getProperty("drivertype");
        if(StringUtils.isNotNullAndNotEmpty(drivertypestr)){
            dataConnInfo.setDrivertype(drivertypestr.trim());
        }else{
            dataConnInfo.setDrivertype("oracle");
        }


        String defaultdirstr=prop.getProperty("defaultdir");
        if(StringUtils.isNotNullAndNotEmpty(defaultdirstr)){
            defaultdir=defaultdirstr;
        }
        String isusecodestr=prop.getProperty("isusecode");
        if("1".equals(isusecodestr)){
            isusecode=true;
        }

    }


   public static class DataConnInfo{
        public final static String ORACLE="oracle";
        public final static String MYSQL="mysql";
        private String jdbcurl;
        private String username;
        private String paswword;
        private String drivertype;

        public String getJdbcurl() {
            return jdbcurl;
        }

        public void setJdbcurl(String jdbcurl) {
            this.jdbcurl = jdbcurl;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPaswword() {
            return paswword;
        }

        public void setPaswword(String paswword) {
            this.paswword = paswword;
        }

       public String getDrivertype() {
           return drivertype;
       }

       public void setDrivertype(String drivertype) {
           this.drivertype = drivertype;
       }
   }

    public static DataConnInfo getDataConnInfo() {
        return dataConnInfo;
    }

    public static void setDataConnInfo(DataConnInfo dataConnInfo) {
        SystemData.dataConnInfo = dataConnInfo;
    }

    public static ENSystem getSystem() {
        return system;
    }

    public static void setSystem(ENSystem system) {
        SystemData.system = system;
    }

    public static String getDefaultdir() {
        return defaultdir;
    }

    public static boolean isIsusecode() {
        return isusecode;
    }
}
