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
    UCDEFINE_ISLIMITSELECT("UCDEFINEISLIMITSELECT")


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
