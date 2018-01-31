package util;

import java.io.File;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-31<br>
 * <br>
 */
public class SystemUtil {
    public static String getSvcDirectory(){
        String fileLogPath=SystemUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if(fileLogPath.indexOf("/")==0){
            fileLogPath=fileLogPath.substring(1,fileLogPath.length());
        }
        fileLogPath=fileLogPath.substring(0,fileLogPath.length()-1);
        fileLogPath=fileLogPath.substring(0,fileLogPath.lastIndexOf("/")+1);
        fileLogPath=fileLogPath+ "uc"+"/";
        return fileLogPath;
    }

}
