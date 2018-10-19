package bean.gencode.adapter;

import bean.gencode.ClassInfo;


import java.util.*;

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

    public static List<String> getDtoNotGenField(){
        List<String> retList=new ArrayList<>();
        retList.add("urid");
        retList.add("tenantid");
        retList.add("createdby");
        retList.add("createdon");
        retList.add("lastmodifiedby");
        retList.add("lastmodifiedon");
        retList.add("rowversion");
        return  retList;
    }
}
