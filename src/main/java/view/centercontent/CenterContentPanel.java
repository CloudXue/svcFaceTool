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
    private BaseJPanel ucDefineMaintain;
    private BaseJPanel sqlMaintain;
    private BaseJPanel ucInMaintain;
    private BaseJPanel ucOutMaintain;
    private BaseJPanel generateSql;

    public CenterContentPanel(MyActionListener myActionListener, CenterpanelTab centerpanelTab) {
        this.centerpanelTab = centerpanelTab;
        this.myActionListener = myActionListener;
        ucDefineMaintain = new UcDefineMaintain(myActionListener, this);
        sqlMaintain = new SqlMaintain(myActionListener, this);
        ucInMaintain = new UcInMaintain(myActionListener, this);
        ucOutMaintain = new UcOutMaintain(myActionListener, this);
        generateSql = new GenerateSql(myActionListener, this);
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        headPanel = new HeadPanel();
        contentTab = new JTabbedPane();
        //todo title 样式
        //contentTab.setFont(FontFactory.getContentTabTitle());

        contentTab.addTab("功能表定义维护", ucDefineMaintain);
        contentTab.addTab("数据源SQL维护", sqlMaintain);
        contentTab.addTab("功能输入输出定义", ucInMaintain);
        contentTab.addTab("数据源→查询显示配置", ucOutMaintain);
        contentTab.addTab("生成SQL语言", generateSql);
        contentTab.addChangeListener(this);
        this.add(headPanel, BorderLayout.NORTH);
        this.add(contentTab, BorderLayout.CENTER);
    }

    public void setHeadValue(String ucCode, String ucNo, String ucName) {
        headPanel.setHeadValue(ucCode, ucNo, ucName);
    }

    public String getUcNo() {
        return headPanel.getUcNo();
    }

    public String getUcCodeField() {
        return headPanel.getUcCodeField();
    }

    public void setTitle(String title) {
        centerpanelTab.setTitle(title);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        ((BaseJPanel) contentTab.getSelectedComponent()).onFocus(true);
    }

    public void close() {
        headPanel = null;
        contentTab = null;

        ucDefineMaintain.close();
        sqlMaintain.close();
        ucInMaintain.close();
        ucOutMaintain.close();
        generateSql.close();

        ucDefineMaintain = null;
        sqlMaintain = null;
        ucInMaintain = null;
        ucOutMaintain = null;
        generateSql = null;
    }
}
