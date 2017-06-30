package view.centercontent;

import control.MyActionListener;

import javax.swing.*;

/**
 * Created by lyd on 2017/5/11.
 */
public class SqlMaintain  extends BaseJPanel {
    private   CenterContentPanel centerContentPanel;
    public SqlMaintain(MyActionListener myActionListener,CenterContentPanel centerContentPanel) {
        this.centerContentPanel=centerContentPanel;
        this.myActionListener= myActionListener;
        init();
    }
    private void init(){
        JLabel jLabel=new JLabel("SqlMaintain");
        this.add(jLabel);

    }
}
