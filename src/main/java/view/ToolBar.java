package view;

import constant.EnActionEvent;
import control.MyActionListener;
import view.component.SButton;

import javax.swing.*;

/**
 * Created by lyd on 2017/1/4.
 */
public class ToolBar extends JToolBar{
    private SButton newtab;
    private SButton closealltab;
    private SButton refresh;
    private MyActionListener myActionListener;

    public ToolBar(MyActionListener myActionListener) {
        this.myActionListener = myActionListener;
        initToolBar();
    }
    private void initToolBar(){
        newtab=new SButton("新窗口");
        newtab.setActionCommand(EnActionEvent.NEWTABCLICK.getCmd());
        newtab.addActionListener(myActionListener);
        closealltab=new SButton("关闭所有");
        closealltab.setActionCommand(EnActionEvent.CLOSEALLTABCLICK.getCmd());
        closealltab.addActionListener(myActionListener);

        refresh=new SButton("刷新缓存");
        refresh.setActionCommand(EnActionEvent.REFRESHCACHE.getCmd());
        refresh.addActionListener(myActionListener);



        this.add(newtab);
        this.addSeparator();
        this.add(closealltab);
        this.addSeparator();
        this.add(refresh);
    }


}
