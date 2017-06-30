package bean;

import util.StringUtils;

import java.io.Serializable;

/**
 * Created by lyd on 2017-06-28.
 */
public class HsiRight implements Serializable,Cloneable {

    private static String tableName="HSI_RIGHT";
    /**
     *功能编码
     */
    private String c_rightcode;
    /**
     *UC功能号
     */
    private String c_rightname;
    /**
     *权限类别
     */
    private String c_class="";
    /**
     *系统名称
     */
    private String c_sysname;
    /**
     *UC功能号
     */
    private String c_functionno;
    /**
     *BO类名
     */
    private String c_javaclass;
    /**
     *BO方法
     */
    private String c_javamethod;
    /**
     *客户过程
     */
    private String c_clientprogs="";
    /**
     *主表名称
     */
    private String c_tablename="";
    /**
     *功能类型
     */
    private String c_uctype;
    /**
     *日终处理时停用
     */
    private String c_islimit;

    public String getC_rightcode() {
        return c_rightcode;
    }

    public void setC_rightcode(String c_rightcode) {
        this.c_rightcode = c_rightcode;
    }

    public String getC_rightname() {
        return c_rightname;
    }

    public void setC_rightname(String c_rightname) {
        this.c_rightname = c_rightname;
    }

    public String getC_class() {
        return c_class;
    }
    public String getC_className() {
        //"0:操作权限","2:公共权限","1:其他权限"
        if(StringUtils.isNotNullAndNotEmpty(c_class)){
            if(c_class.equals("0")){
                return "操作权限";
            }else if(c_class.equals("2")){
                return "公共权限";
            }else if(c_class.equals("1")){
                return "其他权限";
            }
        }
        return c_class;
    }

    public void setC_class(String c_class) {
        this.c_class = c_class;
    }

    public String getC_sysname() {
        return c_sysname;
    }

    public void setC_sysname(String c_sysname) {
        this.c_sysname = c_sysname;
    }

    public String getC_functionno() {
        return c_functionno;
    }

    public void setC_functionno(String c_functionno) {
        this.c_functionno = c_functionno;
    }

    public String getC_javaclass() {
        return c_javaclass;
    }

    public void setC_javaclass(String c_javaclass) {
        this.c_javaclass = c_javaclass;
    }

    public String getC_javamethod() {
        return c_javamethod;
    }

    public void setC_javamethod(String c_javamethod) {
        this.c_javamethod = c_javamethod;
    }

    public String getC_clientprogs() {
        return c_clientprogs;
    }

    public void setC_clientprogs(String c_clientprogs) {
        this.c_clientprogs = c_clientprogs;
    }

    public String getC_tablename() {
        return c_tablename;
    }

    public void setC_tablename(String c_tablename) {
        this.c_tablename = c_tablename;
    }

    public String getC_uctype() {
        return c_uctype;
    }

    public void setC_uctype(String c_uctype) {
        this.c_uctype = c_uctype;
    }

    public String getC_islimit() {
        return c_islimit;
    }

    public void setC_islimit(String c_islimit) {
        this.c_islimit = c_islimit;
    }

    public static String getTableName(){
        return tableName;
    }

    @Override
    public Object clone()  {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
