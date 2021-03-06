package bean;

import util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-10<br>
 * <br>
 */
public class TsvcSql extends  BaseBean implements Serializable,Cloneable {
    private final static String tsvcsql="INSERT INTO TSVCSQL(C_FUNCTIONNO,C_SQLSTATEMENT,C_ORDERBY,C_SQLTYPE,C_DATASOURCE)" +
            " VALUES ('%s','%s','%s','%s',NULL);";
    @Override
    public String generateInsertSql() {
        return String.format(tsvcsql,getC_functionno(),getC_sqlstatement().replaceAll("'","''").
                        replaceAll("\n\n","\n").replaceAll("\r\n\r\n","\n").replaceAll("\r\n","\n").
                        replaceAll("\n","\r\n"),
                StringUtils.valueOf(getC_orderby()),getC_sqltype());
    }
    public static String generateHead() {
        return "--****************************************************\r\n" +
                "--**  英文表名：TSVCSQL\r\n" +
                "--****************************************************\r\n";
    }

    @Override
    public String getKeyValue() {
        return this.c_functionno;
    }

    public static String tableName="TSVCSQL";
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
    public List<String> getKeyStr() {
        List<String> key=new ArrayList<>();
        key.add("c_functionno");
        return key;
    }
    @Override
    public List<String> getKeyValueStr() {
        List<String> key=new ArrayList<>();
        key.add("c_functionno");
        return key;
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

    public  String getTableName() {
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
