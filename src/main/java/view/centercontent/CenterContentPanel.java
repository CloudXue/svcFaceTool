package view.centercontent;

import control.MyActionListener;
import view.CenterpanelTab;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by lyd on 2017/5/11.
 * 内层panel,包含多个tab页
 */
public class CenterContentPanel extends BaseJPanel implements ChangeListener {
    private CenterpanelTab centerpanelTab;
    private HeadPanel headPanel;
    private JTabbedPane contentTab;
    public CenterContentPanel(MyActionListener myActionListener,CenterpanelTab centerpanelTab) {
        this.centerpanelTab=centerpanelTab;
        this.myActionListener = myActionListener;
        init();
    }

    private void init(){
        setLayout(new BorderLayout());
        headPanel=new HeadPanel();
        contentTab=new JTabbedPane();
        //todo title 样式
        //contentTab.setFont(FontFactory.getContentTabTitle());

        contentTab.addTab("功能表定义维护",new UcDefineMaintain(myActionListener,this));
        contentTab.addTab("数据源SQL维护",new SqlMaintain(myActionListener,this));
        contentTab.addTab("功能输入输出定义",new UcInMaintain(myActionListener,this));
        contentTab.addTab("数据源→查询显示配置",new UcOutMaintain(myActionListener,this));
        contentTab.addTab("生成SQL语言",new GenerateSql(myActionListener,this));
        contentTab.addChangeListener(this);
        this.add(headPanel,BorderLayout.NORTH);
        this.add(contentTab,BorderLayout.CENTER);
    }
    public void setHeadValue(String ucCode,String ucNo,String ucName){
        headPanel.setHeadValue(ucCode,ucNo,ucName);
    }
    public String getUcNo(){
        return headPanel.getUcNo();
    }
    public String getUcCodeField(){
        return headPanel.getUcCodeField();
    }
    public void setTitle(String title){
        centerpanelTab.setTitle(title);
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        ((BaseJPanel)contentTab.getSelectedComponent()).onFocus(true);
    }
}
