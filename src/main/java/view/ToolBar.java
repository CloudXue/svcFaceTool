package view;

import constant.EnActionEvent;
import control.MyActionListener;

import javax.swing.*;

/**
 * Created by lyd on 2017/1/4.
 */
public class ToolBar extends JToolBar{
    private JButton newtab;
    private JButton closealltab;
    private MyActionListener myActionListener;

    public ToolBar(MyActionListener myActionListener) {
        this.myActionListener = myActionListener;
        initToolBar();
    }
    private void initToolBar(){
        newtab=new JButton("新窗口");
        newtab.setActionCommand(EnActionEvent.NEWTABCLICK.getCmd());
        newtab.addActionListener(myActionListener);
        closealltab=new JButton("关闭所有");
        closealltab.setActionCommand(EnActionEvent.CLOSEALLTABCLICK.getCmd());
        closealltab.addActionListener(myActionListener);
        this.add(newtab);
        this.addSeparator();
        this.add(closealltab);
    }


}
