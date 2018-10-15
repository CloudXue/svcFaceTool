package bean.gencode.adapter;

import bean.gencode.ClassInfo;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能说明:
 * @author: lyd
 * 开发时间: 2018-10-15
 */
public class ClassInfoUtil {
    private static Map<String,ClassInfo> classInfoMap;
    static {
        classInfoMap =new HashMap<>(getClassInfoMap());
    }
    /**
     * 获取内置的类信息
     * @return
     */
    public static  Map<String,ClassInfo> getClassInfoMap(){
        Map<String,ClassInfo> map=new LinkedHashMap<>();

        ClassInfo bigDecimal=new ClassInfo();
        bigDecimal.setPackageStr("java.math");
        bigDecimal.setClassName("BigDecimal");
        map.put("BigDecimal",bigDecimal);

        ClassInfo date=new ClassInfo();
        date.setPackageStr("java.util");
        date.setClassName("Date");
        map.put("Date",date);

        return map;
    }

    public Map<String,ClassInfo> getAllClassInfoMap(){
        return classInfoMap;
    }
}
