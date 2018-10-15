package service.impl;

import bean.gencode.*;
import bean.gencode.adapter.ClassInfoAdapter;
import bean.gencode.adapter.DtoClassAdapter;
import constant.ENWarningLevel;
import constant.EnActionEvent;
import dao.DaoFactory;
import dao.GenCodeDao;
import exception.ViewException;
import org.apache.log4j.Logger;
import service.GenerateCodeService;
import service.ServiceFactory;
import service.SvcService;
import util.LogUtil;
import util.StringUtils;

import java.util.*;

/**
 * 功能说明:
 * 系统版本: 2.4.2.0
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class GenerateCodeServiceImpl implements GenerateCodeService {
    private static Logger logger = LogUtil.getLogger(GenerateCodeServiceImpl.class);
    GenCodeDao genCodeDao = DaoFactory.getGenCodeDao();
    SvcService svcService = ServiceFactory.getSvcService();
    private final static List<String> notgengetset=new ArrayList<>();
    private final static List<String> notupdate=new ArrayList<>();
    static {
        notgengetset.add("urid");
        notgengetset.add("rowversion");
        notgengetset.add("tenantid");
        notgengetset.add("createdby");
        notgengetset.add("createdon");
        notgengetset.add("lastmodifiedby");
        notgengetset.add("lastmodifiedon");

        notupdate.add("urid");
        notupdate.add("tenantid");
        notupdate.add("createdby");
        notupdate.add("createdon");
    }
    @Override
    public ClassInfo getTableCodeClass(GenCodeViewConfig codeViewConfig) throws Exception {
        validConfig(codeViewConfig);
        ClassInfo classInfo=null;
        TableInfo tableInfo=null;
        try {
            tableInfo=genCodeDao.getTableInfo(codeViewConfig.getTableName());
        } catch (Exception e) {
           logger.error(e.getMessage(),e);
            svcService.throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
        }
        if(tableInfo==null ){
            svcService.throwErrorMsg(EnActionEvent.COMMOM_WARNING,"根据表名："+codeViewConfig.getTableName()+"未找到表或表无字段");
        }else{
            classInfo=convert(tableInfo,codeViewConfig);

        }


        return classInfo;
    }

    @Override
    public String getBeanXml(String beanid, String beanFullName,String describe) {
        String retStr="";
        if(StringUtils.isNotNullAndNotEmpty(beanid)){
            String beanidStr=StringUtils.lowerFirstChar(beanid);
            retStr="<!--"+describe+"-->\n" +
                    "<bean id=\""+beanidStr+"\" class=\""+beanFullName+"\"/>\n";
        }
        return retStr;
    }

    @Override
    public Map<String, String> getTables() throws Exception {
        Map<String, String> retMap=new LinkedHashMap<>();
        List<TableInfo> allTables=genCodeDao.getTables();
        if(allTables!=null){
            for(TableInfo tableInfo : allTables){
                retMap.put(tableInfo.getName()+"-"+tableInfo.getDescribe(),tableInfo.getName());
            }
        }
        return retMap;
    }

    @Override
    public Map<String, String> getTableCode(GenCodeViewConfig codeViewConfig) throws Exception {
        Map<String, String> retMap=new LinkedHashMap<>();
        ClassInfo classInfo=getTableCodeClass(codeViewConfig);
        if(classInfo==null ){
            svcService.throwErrorMsg(EnActionEvent.COMMOM_WARNING,"根据表名："+codeViewConfig.getTableName()+"未成功生成类");
        }

        ClassInfo dtoClassInfo= (ClassInfo) classInfo.clone();
        ClassInfoAdapter dto=new DtoClassAdapter(dtoClassInfo);
        retMap.put(dtoClassInfo.getClassName(),dto.toFileString());

        ClassInfo daoClassInfo= (ClassInfo) classInfo.clone();
        daoClassInfo.setClassName(daoClassInfo.getClassName()+"Dao");
        ClassInfoAdapter dao=new DtoClassAdapter(daoClassInfo);
        retMap.put(daoClassInfo.getClassName(),dao.toFileString());

        ClassInfo daoimplClassInfo= (ClassInfo) classInfo.clone();
        daoimplClassInfo.setClassName(daoimplClassInfo.getClassName()+"DaoImpl");
        ClassInfoAdapter daoimpl=new DtoClassAdapter(daoimplClassInfo);
        retMap.put(daoimplClassInfo.getClassName(),daoimpl.toFileString());

        retMap.put("describe",classInfo.getDescribe());

        return retMap;
    }

    private ClassInfo convert(TableInfo tableInfo, GenCodeViewConfig codeViewConfig){
        ClassInfo dto=new ClassInfo();

        dto.setClassName(codeViewConfig.getClassName());
        dto.setPackageStr(codeViewConfig.getPackageName());
        dto.setDescribe(tableInfo.getDescribe());
        dto.setTableName(tableInfo.getName());

        if(tableInfo.getFields()!=null && tableInfo.getFields().size()>0){
            for(TableField tableField : tableInfo.getFields()){
                ClassField field=new ClassField();
                field.setName(tableField.getName());
                field.setDescribe(tableField.getDescribe());
                field.setType(convertDbType(tableField.getFieldType()));
                field.setPermission("private");
                if(notgengetset.contains(tableField.getName().toLowerCase())){
                    field.setIsgenerateget(false);
                    field.setIsgenerateset(false);
                }else{
                    field.setIsgenerateget(true);
                    field.setIsgenerateset(true);
                }
                if(notupdate.contains(tableField.getName().toLowerCase())){
                    field.setIscanupdate(false);
                }else{
                    field.setIscanupdate(false);
                }
                field.setIsfinal(false);
                field.setIsstatic(false);
                dto.getField().add(field);
            }

        }
        return dto;
    }
    private String convertDbType(String dbType){
        dbType=dbType.toUpperCase();
        if(dbType.contains("CHAR")){
            return "String";
        }else if(dbType.contains("DATE")){
            return "Date";
        }else if(dbType.contains("INTEGER")){
            return "Integer";
        }else if(dbType.contains("NUMBER")){
            return "Integer";
        }else if(dbType.contains("CLOB")){
            return "String";
        }else if(dbType.contains("FLOAT")){
            return "BigDecimal";
        }

        return dbType;
    }

    private void validConfig(GenCodeViewConfig codeViewConfig) throws Exception {
        if(StringUtils.isNullOrEmpty(codeViewConfig.getTableName())){
            throw new Exception("表名不可为空");
        }
        if(StringUtils.isNullOrEmpty(codeViewConfig.getClassName())){
            throw new Exception("dto不可为空");
        }
        if(StringUtils.isNullOrEmpty(codeViewConfig.getDtoExtendClassName())){
            throw new Exception("dto父类名不可为空");
        }
        if(StringUtils.isNullOrEmpty(codeViewConfig.getPackageName())){
            throw new Exception("包名不可为空");
        }
        if(StringUtils.isNullOrEmpty(codeViewConfig.getUserName())){
            throw new Exception("用户不可为空");
        }
    }
}
