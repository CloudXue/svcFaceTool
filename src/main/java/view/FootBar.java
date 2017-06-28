package view;

import javax.swing.*;

/**
 * Created by lyd on 2017/5/10.
 */
public class FootBar  extends JToolBar {
    private JLabel conninfo;
    private JLabel connstate;
    private JLabel errorinfo;

    public FootBar() {
        init();
    }
    private void init(){
        conninfo=new JLabel("jats101@127.0.0.1/mcs100");
        connstate=new JLabel("未连接数据库");
        errorinfo=new JLabel("errorinfo");
        this.add(conninfo);
        this.addSeparator();
        this.add(connstate);
        this.addSeparator();
        this.add(errorinfo);
    }
}
