package bean;

import util.StringUtils;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-22<br>
 * <br>
 */
public class TsvcInterface extends BaseBean {
    private final static String insertSql="INSERT INTO TSVCINTERFACE(C_FUNCTIONNO,C_FLAG,C_PACKFLAG," +
            "C_FIELDNAME,C_EXPLAIN,C_PROPERTY,L_LEN,L_DECLEN,C_FIELDTYPE,C_FIELDFLAG,C_NOTNULL," +
            "C_CONDITION,C_EXISTVALUE,C_VIEWLEVEL,C_VIEWTYPE,C_DICNAME,C_ISDEFAULT,C_VALUE," +
            "C_MIDSEARCHNAME,L_NO,C_NOTSHOWALLITEM) VALUES ('%s','%s','%s','%s','%s','%s','%s'," +
            "'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');\n";
    public static String tableName="TSVCINTERFACE";
    private String c_functionno;
    private String c_flag;
    private String c_packflag;
    private String c_fieldname;
    private String c_explain;
    private String c_property;
    private String l_len;
    private String l_declen;
    private String c_fieldtype;
    private String c_fieldflag;
    private String c_notnull ;
    private String c_condition;
    private String c_existvalue;
    private String c_viewlevel;
    private String c_viewtype;
    private String c_dicname;
    private String c_isdefault;
    private String c_value;
    private String c_midsearchname;
    private String l_no;
    private String c_notshowallitem;

    @Override
    public String generateInsertSql() {
        return String.format(insertSql, StringUtils.valueOf(getC_functionno()),
                StringUtils.valueOf(getC_flag()),StringUtils.valueOf(getC_packflag()),
                StringUtils.valueOf(getC_fieldname()),StringUtils.valueOf(getC_explain()),
                StringUtils.valueOf(getC_property()),StringUtils.valueOf(getL_len()),
                StringUtils.valueOf(getL_declen()),StringUtils.valueOf(getC_fieldtype()),
                StringUtils.valueOf(getC_fieldflag()),StringUtils.valueOf(getC_notnull()),
                StringUtils.valueOf(getC_condition()),StringUtils.valueOf(getC_existvalue()),
                StringUtils.valueOf(getC_viewlevel()),StringUtils.valueOf(getC_viewtype()),
                StringUtils.valueOf(getC_dicname()),StringUtils.valueOf(getC_isdefault()),
                StringUtils.valueOf(getC_value()),StringUtils.valueOf(getC_midsearchname()),
                StringUtils.valueOf(getL_no()),StringUtils.valueOf(getC_notshowallitem()));
    }
    public static String generateHead() {
        return "--****************************************************\n" +
                "--**  英文表名：TSVCINTERFACE\n" +
                "--****************************************************\n";
    }
    @Override
    public String getKeyStr() {
        return "C_FUNCTIONNO";
    }

    @Override
    public String getKeyValue() {
        return "C_FUNCTIONNO";
    }

    @Override
    public String getKeyValueStr() {
        return "C_FUNCTIONNO";
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String getC_functionno() {
        return c_functionno;
    }

    @Override
    public void setC_functionno(String c_functionno) {
        this.c_functionno = c_functionno;
    }

    public String getC_flag() {
        return c_flag;
    }

    public void setC_flag(String c_flag) {
        this.c_flag = c_flag;
    }

    public String getC_packflag() {
        return c_packflag;
    }

    public void setC_packflag(String c_packflag) {
        this.c_packflag = c_packflag;
    }

    public String getC_fieldname() {
        return c_fieldname;
    }

    public void setC_fieldname(String c_fieldname) {
        this.c_fieldname = c_fieldname;
    }

    public String getC_explain() {
        return c_explain;
    }

    public void setC_explain(String c_explain) {
        this.c_explain = c_explain;
    }

    public String getC_property() {
        return c_property;
    }

    public void setC_property(String c_property) {
        this.c_property = c_property;
    }

    public String getL_len() {
        return l_len;
    }

    public void setL_len(String l_len) {
        this.l_len = l_len;
    }

    public String getL_declen() {
        return l_declen;
    }

    public void setL_declen(String l_declen) {
        this.l_declen = l_declen;
    }

    public String getC_fieldtype() {
        return c_fieldtype;
    }

    public void setC_fieldtype(String c_fieldtype) {
        this.c_fieldtype = c_fieldtype;
    }

    public String getC_fieldflag() {
        return c_fieldflag;
    }

    public void setC_fieldflag(String c_fieldflag) {
        this.c_fieldflag = c_fieldflag;
    }

    public String getC_notnull() {
        return c_notnull;
    }

    public void setC_notnull(String c_notnull) {
        this.c_notnull = c_notnull;
    }

    public String getC_condition() {
        return c_condition;
    }

    public void setC_condition(String c_condition) {
        this.c_condition = c_condition;
    }

    public String getC_existvalue() {
        return c_existvalue;
    }

    public void setC_existvalue(String c_existvalue) {
        this.c_existvalue = c_existvalue;
    }

    public String getC_viewlevel() {
        return c_viewlevel;
    }

    public void setC_viewlevel(String c_viewlevel) {
        this.c_viewlevel = c_viewlevel;
    }

    public String getC_viewtype() {
        return c_viewtype;
    }

    public void setC_viewtype(String c_viewtype) {
        this.c_viewtype = c_viewtype;
    }

    public String getC_dicname() {
        return c_dicname;
    }

    public void setC_dicname(String c_dicname) {
        this.c_dicname = c_dicname;
    }

    public String getC_isdefault() {
        return c_isdefault;
    }

    public void setC_isdefault(String c_isdefault) {
        this.c_isdefault = c_isdefault;
    }

    public String getC_value() {
        return c_value;
    }

    public void setC_value(String c_value) {
        this.c_value = c_value;
    }

    public String getC_midsearchname() {
        return c_midsearchname;
    }

    public void setC_midsearchname(String c_midsearchname) {
        this.c_midsearchname = c_midsearchname;
    }

    public String getL_no() {
        return l_no;
    }

    public void setL_no(String l_no) {
        this.l_no = l_no;
    }

    public String getC_notshowallitem() {
        return c_notshowallitem;
    }

    public void setC_notshowallitem(String c_notshowallitem) {
        this.c_notshowallitem = c_notshowallitem;
    }
}
