package service.impl;

import bean.gencode.*;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public ClassInfo getTableCodeClass(String tableName,GenCodeViewConfig codeViewConfig) {
        ClassInfo classInfo=null;
        TableInfo tableInfo=null;
        try {
            tableInfo=genCodeDao.getTableInfo(tableName);
        } catch (Exception e) {
           logger.error(e.getMessage(),e);
            svcService.throwErrorMsg(EnActionEvent.COMMOM_ERROR,e.getMessage());
        }
        if(tableInfo==null ){
            svcService.throwErrorMsg(EnActionEvent.COMMOM_WARNING,"根据表名："+tableName+"未找到表或表无字段");
        }else{
            classInfo=convert(tableInfo,codeViewConfig);
        }


        return classInfo;
    }

    @Override
    public String getBeanXml(String beanid, String beanFullName) {
        return null;
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

    private ClassInfo convert(TableInfo tableInfo, GenCodeViewConfig codeViewConfig){
        ClassInfo dto=new ClassInfo();

        dto.setClassName(codeViewConfig.getClassName());
        dto.setPackageStr(codeViewConfig.getPackageName());
        dto.setDescribe(tableInfo.getDescribe());
        dto.setTableName(tableInfo.getTableName());

        if(tableInfo.getFields()!=null && tableInfo.getFields().size()>0){
            for(TableField tableField : tableInfo.getFields()){
                ClassField field=new ClassField();
                field.setName(tableField.getName());
                field.setDescribe(tableField.getDescribe());
                field.setType(convertDbType(tableField.getFieldType()));
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
}
