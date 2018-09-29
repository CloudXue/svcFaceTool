package bean.gencode;


import java.util.List;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class ClassInfo {
    /**
     * 包名
     */
    private String packageStr;
    /**
     * 类名
     */
    private String className;
    /**
     * 类型、class、interface
     */
    private String type;
    /**
     * 泛型
     */
    private ClassInfo generic;
    /**
     * 继承接口名称
     */
    private List<String> interfaceList;
    /**
     * 父类
     */
    private ClassInfo parentClass;
    /**
     * 成员
     */
    private List<ClassField> field;
    /**
     * 方法
     */
    private List<ClassMethod> methods;

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ClassInfo getGeneric() {
        return generic;
    }

    public void setGeneric(ClassInfo generic) {
        this.generic = generic;
    }

    public List<String> getInterfaceList() {
        return interfaceList;
    }

    public void setInterfaceList(List<String> interfaceList) {
        this.interfaceList = interfaceList;
    }

    public ClassInfo getParentClass() {
        return parentClass;
    }

    public void setParentClass(ClassInfo parentClass) {
        this.parentClass = parentClass;
    }

    public List<ClassField> getField() {
        return field;
    }

    public void setField(List<ClassField> field) {
        this.field = field;
    }

    public List<ClassMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<ClassMethod> methods) {
        this.methods = methods;
    }
}
