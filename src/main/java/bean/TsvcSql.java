package bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-10<br>
 * <br>
 */
public class TsvcSql extends  BaseBean implements Serializable,Cloneable {
    @Override
    public String getKeyValue() {
        return this.c_functionno;
    }

    private static String tableName="TSVCSQL";
    /**
     * 函数编号
     */
    private String c_functionno;
    /**
     *SQL报表
     */
    private String c_sqlstatement;
    /**
     *排序
     */
    private String c_orderby;
    /**
     *SQL类型。0:普通SQL语句,1:存储过程
     */
    private String c_sqltype;
    /**
     *数据源
     */
    private String c_datasource;


    @Override
    public String getKeyStr() {
        return "c_functionno";
    }

    @Override
    public String getKeyValueStr() {
        return "c_functionno";
    }

    public String getC_functionno() {
        return c_functionno;
    }

    public void setC_functionno(String c_functionno) {
        this.c_functionno = c_functionno;
    }

    public String getC_sqlstatement() {
        return c_sqlstatement;
    }

    public void setC_sqlstatement(String c_sqlstatement) {
        this.c_sqlstatement = c_sqlstatement;
    }

    public String getC_orderby() {
        return c_orderby;
    }

    public void setC_orderby(String c_orderby) {
        this.c_orderby = c_orderby;
    }

    public String getC_sqltype() {
        return c_sqltype;
    }

    public void setC_sqltype(String c_sqltype) {
        this.c_sqltype = c_sqltype;
    }

    public String getC_datasource() {
        return c_datasource;
    }

    public void setC_datasource(String c_datasource) {
        this.c_datasource = c_datasource;
    }

    public static String getTableName() {
        return tableName;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TsvcSql tsvcSql = (TsvcSql) o;
        return Objects.equals(c_functionno, tsvcSql.c_functionno) &&
                Objects.equals(c_sqlstatement, tsvcSql.c_sqlstatement) &&
                Objects.equals(c_orderby, tsvcSql.c_orderby) &&
                Objects.equals(c_sqltype, tsvcSql.c_sqltype) &&
                Objects.equals(c_datasource, tsvcSql.c_datasource);
    }

    @Override
    public int hashCode() {

        return Objects.hash(c_functionno, c_sqlstatement, c_orderby, c_sqltype, c_datasource);
    }
}
