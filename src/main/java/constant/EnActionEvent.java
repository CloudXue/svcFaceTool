package constant;

/**
 * Created by lyd on 2017/1/4.
 */
public enum EnActionEvent {
    /**
     * 登录点击
     */
    LOGINCLICK("loginclick"),
    /**
     *断开点击
     */
    LOGOUTCLICK("logoutclick"),
    /**
     *退出点击
     */
    CLOSECLICK("closeclick"),
    /**
     *关于点击
     */
    ABOUTCLICK("aboutclick"),
    /**
     *新窗口点击
     */
    NEWTABCLICK("newtabclick"),
    /**
     *关闭所有点击
     */
    CLOSEALLTABCLICK("closealltabclick"),
    /**
     *uc定义插入按钮点击
     */
    UCDEFINE_INSERTCLICK("insertclick"),
    /**
     *uc定义尾加按钮点击
     */
    UCDEFINE_TAILINSERTCLICK("tailinsertclick"),
    /**
     *uc定义删除按钮点击
     */
    UCDEFINE_DELCLICK("delclick"),
    /**
     *uc定义保存按钮点击
     */
    UCDEFINE_SAVECLICK("saveclick"),
    /**
     *uc定义查询按钮点击
     */
    UCDEFINE_QUERYCLICK("queryclick"),
    /**
     *异常
     */
    EXCEPTION("EXCEPTION"),
    /**
     *提示
     */
    INFO("INFO"),
    /**
     *警告
     */
    WARNING("WARNING"),
    /**
     * uc定义中权限类别选中事件
     */
    UCDEFINE_CLASSSELECT("UCDEFINECLASSSELECT"),
    /**
     *uc定义中功能类型选中事件
     */
    UCDEFINE_UCTYPESELECT("UCDEFINEUCTYPESELECT"),
    /**
     *uc定义中日终处理时停用选中事件
     */
    UCDEFINE_ISLIMITSELECT("UCDEFINEISLIMITSELECT"),
    /**
     * SQL维护删除
     */
    SQLMAINTAIN_DEL("SQLMAINTAIN_DEL"),
    /**
     *SQL维护存盘
     */
    SQLMAINTAIN_SAVE("SQLMAINTAIN_SAVE"),
    /**
     *SQL维护刷新
     */
    SQLMAINTAIN_REFRESH("SQLMAINTAIN_REFRESH"),
    /**
     *SQL维护sql写法帮助
     */
    SQLMAINTAIN_HELP("SQLMAINTAIN_HELP"),
    /**
     *SQL维护sql语句测试
     */
    SQLMAINTAIN_SQLTEST("SQLMAINTAIN_SQLTEST"),
    /**
     *SQL维护分页组合sql测试
     */
    SQLMAINTAIN_SQLPAGING("SQLMAINTAIN_SQLPAGING"),
    /**
     *生成sql,生成
     */
    GENERATESQL_GEN("GENERATESQL_GEN"),
    /**
     *生成sql,打开文件
     */
    GENERATESQL_OPENFILESEL("GENERATESQL_OPENFILESEL"),
    /**
     *生成sql,保存
     */
    GENERATESQL_SAVE("GENERATESQL_SAVE"),

    ;
    private String cmd;
    private String msg;



    EnActionEvent(String cmd) {
        this.cmd = cmd;
    }

    EnActionEvent(String cmd, String msg) {
        this.cmd = cmd;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCmd(){
        return this.cmd;
    }
}
