package bean.gencode;

import java.util.List;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class ClassMethod {
    private String describe;
    /**
     * public\protected \ private
     */
    private String permission;
    /**
     * 返回类型，null为void
     */
    private ClassInfo retType;
    /**
     * 泛型
     */
    private  ClassInfo generic;
    private String name;
    /**
     * 参数
     */
    private List<ClassField> params;
    /**
     * 方法体内容
     */
    private String content;
    /**
     * 返回值，void时不需用
     */
    private String retValue;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public ClassInfo getRetType() {
        return retType;
    }

    public void setRetType(ClassInfo retType) {
        this.retType = retType;
    }

    public ClassInfo getGeneric() {
        return generic;
    }

    public void setGeneric(ClassInfo generic) {
        this.generic = generic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassField> getParams() {
        return params;
    }

    public void setParams(List<ClassField> params) {
        this.params = params;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRetValue() {
        return retValue;
    }

    public void setRetValue(String retValue) {
        this.retValue = retValue;
    }
}
