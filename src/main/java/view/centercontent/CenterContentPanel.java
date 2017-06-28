package view.centercontent;

import control.MyActionListener;
import view.BaseJPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lyd on 2017/5/11.
 */
public class CenterContentPanel extends BaseJPanel{
    private HeadPanel headPanel;
    private JTabbedPane contentTab;
    public CenterContentPanel(MyActionListener myActionListener) {
        this.myActionListener = myActionListener;
        init();
    }

    private void init(){
        setLayout(new BorderLayout());
        headPanel=new HeadPanel();
        contentTab=new JTabbedPane();
        //todo title 样式
        //contentTab.setFont(FontFactory.getContentTabTitle());

        contentTab.addTab("功能表定义维护",new UcDefineMaintain(myActionListener));
        contentTab.addTab("数据源SQL维护",new SqlMaintain(myActionListener));
        contentTab.addTab("功能输入输出定义",new UcInMaintain(myActionListener));
        contentTab.addTab("数据源→查询显示配置",new UcOutMaintain(myActionListener));
        contentTab.addTab("生成SQL语言",new GenerateSql(myActionListener));
        this.add(headPanel,BorderLayout.NORTH);
        this.add(contentTab,BorderLayout.CENTER);
    }
}
