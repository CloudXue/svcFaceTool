package bean;

import util.StringUtils;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-22<br>
 * <br>
 */
public class TsvcViewconfig  extends BaseBean {
    private final static String insertSql="INSERT INTO TSVCVIEWCONFIG(C_FUNCTIONNO,C_BUSINFLAG,C_PROPERTY," +
            "C_VIEWLEVEL,C_VIEWTYPE,C_DICNAME,C_VIEWNAME,C_RESERVE,L_NO,C_HYPERLINK,C_FRAMETYPE," +
            "C_CANSTAT,C_HIDDENELEM,C_EDITTYPE,C_INPUTTYPE,C_DEFAULTVALUE,C_LIMIT,C_MIDSEARCHNAME," +
            "C_EVENT,C_SEARCHLINK,C_LEN,C_PATTERN) VALUES ('%s','%s','%s','%s','%s','%s'," +
            "'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');\n";
    @Override
    public String generateInsertSql() {
        return String.format(insertSql,
                StringUtils.valueOf(getC_functionno()),StringUtils.valueOf(getC_businflag()),
                StringUtils.valueOf(getC_property()),StringUtils.valueOf(getC_viewlevel()),
                StringUtils.valueOf(getC_viewtype()),StringUtils.valueOf(getC_dicname()),
                StringUtils.valueOf(getC_viewname()),StringUtils.valueOf(getC_reserve()),
                StringUtils.valueOf(getL_no()),StringUtils.valueOf(getC_hyperlink()),
                StringUtils.valueOf(getC_frametype()),
                StringUtils.valueOf(getC_canstat()),StringUtils.valueOf(getC_hiddenelem()),
                StringUtils.valueOf(getC_edittype()),StringUtils.valueOf(getC_inputtype()),
                StringUtils.valueOf(getC_defaultvalue()),StringUtils.valueOf(getC_limit()),
                StringUtils.valueOf(getC_midsearchname()),StringUtils.valueOf(getC_event()),
                StringUtils.valueOf(getC_searchlink()),StringUtils.valueOf(getC_len()),
                StringUtils.valueOf(getC_pattern()));
    }
    public static String tableName="TSVCVIEWCONFIG";
    private String c_functionno;
    private String c_businflag;
    private String c_property;
    private String c_viewlevel;
    private String c_viewtype;
    private String c_dicname;
    private String c_viewname;
    private String c_reserve;
    private String l_no;
    private String c_hyperlink;
    private String c_frametype;
    private String c_canstat;
    private String c_hiddenelem;
    private String c_edittype;
    private String c_inputtype;
    private String c_defaultvalue;
    private String c_limit;
    private String c_midsearchname;
    private String c_event;
    private String c_searchlink;
    private String c_len;
    private String c_pattern;


    public static String generateHead() {
        return "--****************************************************\n" +
                "--**  英文表名：TSVCVIEWCONFIG\n" +
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
        return TsvcViewconfig.tableName;
    }


    @Override
    public String getC_functionno() {
        return c_functionno;
    }

    @Override
    public void setC_functionno(String c_functionno) {
        this.c_functionno = c_functionno;
    }

    public String getC_businflag() {
        return c_businflag;
    }

    public void setC_businflag(String c_businflag) {
        this.c_businflag = c_businflag;
    }

    public String getC_property() {
        return c_property;
    }

    public void setC_property(String c_property) {
        this.c_property = c_property;
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

    public String getC_viewname() {
        return c_viewname;
    }

    public void setC_viewname(String c_viewname) {
        this.c_viewname = c_viewname;
    }

    public String getC_reserve() {
        return c_reserve;
    }

    public void setC_reserve(String c_reserve) {
        this.c_reserve = c_reserve;
    }

    public String getL_no() {
        return l_no;
    }

    public void setL_no(String l_no) {
        this.l_no = l_no;
    }

    public String getC_hyperlink() {
        return c_hyperlink;
    }

    public void setC_hyperlink(String c_hyperlink) {
        this.c_hyperlink = c_hyperlink;
    }

    public String getC_frametype() {
        return c_frametype;
    }

    public void setC_frametype(String c_frametype) {
        this.c_frametype = c_frametype;
    }

    public String getC_canstat() {
        return c_canstat;
    }

    public void setC_canstat(String c_canstat) {
        this.c_canstat = c_canstat;
    }

    public String getC_hiddenelem() {
        return c_hiddenelem;
    }

    public void setC_hiddenelem(String c_hiddenelem) {
        this.c_hiddenelem = c_hiddenelem;
    }

    public String getC_edittype() {
        return c_edittype;
    }

    public void setC_edittype(String c_edittype) {
        this.c_edittype = c_edittype;
    }

    public String getC_inputtype() {
        return c_inputtype;
    }

    public void setC_inputtype(String c_inputtype) {
        this.c_inputtype = c_inputtype;
    }

    public String getC_defaultvalue() {
        return c_defaultvalue;
    }

    public void setC_defaultvalue(String c_defaultvalue) {
        this.c_defaultvalue = c_defaultvalue;
    }

    public String getC_limit() {
        return c_limit;
    }

    public void setC_limit(String c_limit) {
        this.c_limit = c_limit;
    }

    public String getC_midsearchname() {
        return c_midsearchname;
    }

    public void setC_midsearchname(String c_midsearchname) {
        this.c_midsearchname = c_midsearchname;
    }

    public String getC_event() {
        return c_event;
    }

    public void setC_event(String c_event) {
        this.c_event = c_event;
    }

    public String getC_searchlink() {
        return c_searchlink;
    }

    public void setC_searchlink(String c_searchlink) {
        this.c_searchlink = c_searchlink;
    }

    public String getC_len() {
        return c_len;
    }

    public void setC_len(String c_len) {
        this.c_len = c_len;
    }

    public String getC_pattern() {
        return c_pattern;
    }

    public void setC_pattern(String c_pattern) {
        this.c_pattern = c_pattern;
    }
}
