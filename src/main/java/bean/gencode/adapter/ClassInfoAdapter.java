package bean.gencode.adapter;

import bean.gencode.ClassField;
import bean.gencode.ClassInfo;
import bean.gencode.IClassToFile;
import util.DateUtil;
import util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public abstract class ClassInfoAdapter implements IClassToFile {
    protected ClassInfo classInfo;

    /**
     * 保存新创建类信息，用于关联
     */
    protected Map<String, ClassInfo> newClsMap = new HashMap<>();

    protected List<String> referenceCls = new ArrayList<>();

    public ClassInfoAdapter(ClassInfo classInfo) {
        this.classInfo = classInfo;
        this.prepareToString();
        this.init();
    }

    @Override
    public String toFileString() {
        StringBuilder fileStr = new StringBuilder();
        fileStr.append(generateHead());
        fileStr.append(generateClass());

        return fileStr.toString();
    }


    private  void prepareToString(){

    }

    private void init() {
        classInfo.setType(ClassInfo.TYPE_CLASS);
        initExtra();


    }

    private String generateHead() {
        StringBuilder head = new StringBuilder();
        head.append("package ").append(classInfo.getPackageStr()).append(";\r\n").append("\r\n");

        if (referenceCls != null) {
            for (String cls : referenceCls) {
                if (ClassInfoUtil.getClassInfoMap().containsKey(cls)) {
                    ClassInfo classInfo = ClassInfoUtil.getClassInfoMap().get(cls);
                    head.append("import ").append(classInfo.getFullName()).append(";\r\n");
                }
            }
        }

        head.append("\r\n/**\r\n" +
                "* " + classInfo.getDescribe() + "\r\n" +
                "* \r\n" +
                "* @author " + classInfo.getCreateUser() + "\r\n" +
                "* @date " + DateUtil.getCurrentDateString() + "\r\n" +
                "*/\r\n");

        return head.toString();
    }

    private String generateClass() {
        StringBuilder cls = new StringBuilder();
        cls.append("public").append(" ");
        if(classInfo.getType().equalsIgnoreCase(ClassInfo.TYPE_INTERFACE)){
            cls.append("interface").append(" ");
        }else{
            cls.append("class").append(" ");
        }
        cls.append(classInfo.getClassName()).append(" ");
        if(StringUtils.isNotNullAndNotEmpty(classInfo.getParentClass())){
            if (ClassInfoUtil.getClassInfoMap().containsKey(classInfo.getParentClass())) {
                ClassInfo parentcls = ClassInfoUtil.getClassInfoMap().get(classInfo.getParentClass());
                if(parentcls.getGeneric()!=null){
                    cls.append("extends").append(" ").append(parentcls.getClassName())
                            .append("<").append(parentcls.getGeneric().getParentClass()).append(">").append(" ");
                }else{
                    cls.append("extends").append(" ").append(classInfo.getParentClass()).append(" ");
                }
            }else{
                cls.append("extends").append(" ").append(classInfo.getParentClass()).append(" ");
            }
        }
        if(classInfo.getInterfaceList()!=null && classInfo.getInterfaceList().size()>0){
            cls.append("implements").append(" ");
            for(String iter : classInfo.getInterfaceList()){
                cls.append(iter).append(",");
            }
            cls.setLength(cls.length()-1);
            cls.append(" ");
        }
        cls.append("{").append("\r\n");
        cls.append(generateField());
        cls.append(generateMethod());
        cls.append("}");
        return cls.toString();
    }

    private String generateField() {
        return "";
    }

    private String generateMethod() {
        return "";
    }

    protected  void initExtra() {

    }
}
