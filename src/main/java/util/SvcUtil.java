package util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.1.5 <br>
 * 开发人员: lyd
 * 开发时间: 2017-09-20<br>
 * <br>
 */
public class SvcUtil {

    public static String getSysName(){
        return "FUNDCRM";
    }

    public static Map<String,String> getUcOutViewType(){
        Map<String,String> map=new  LinkedHashMap<String,String>() ;
        map.put("S:字符串","S");
        map.put("HC:隐藏电话","HC");
        map.put("HM:隐藏邮箱","HM");
        map.put("N:字典显示","N");
        map.put("D:日期","D");
        map.put("T:时间","T");
        map.put("DT:日期时间","DT");
        map.put("C:字符","C");
        map.put("I:整型","I");
        map.put("F:浮点数(F4表示4位小数)","F");
        map.put("F4:浮点数(4位小数)","F4");
        map.put("H:HTML","H");
        map.put("W:文件大小(Byte,K,M)","W");
        map.put("P:百分比显示","P");
        map.put("PK:千","PK");
        map.put("FW:万","FW");
        map.put("FM:百万","FM");
        map.put("FY:亿","FY");
        return map;
    }
}
