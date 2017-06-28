package view.centercontent;

import constant.EnActionEvent;
import control.UcDefineControl;
import view.BaseJPanel;
import control.MyActionListener;
import view.centercontent.fcdefine.CenterTable;
import view.widget.ColorFactory;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lyd on 2017/5/11.
 * 功能表定义维护panel
 * 使用BorderLayout布局，上中下三部分
 */
public class UcDefineMaintain extends BaseJPanel {

    private JButton insertBtn;    //插入按钮
    private JButton tailInsertBtn;//尾加按钮
    private JButton delBtn;       //删除按钮
    private JButton saveBtn;      //存盘按钮
    private JButton exportExcelBtn;//导出excel按钮

    private JTextField queryInput;//输入查询框
    private JButton queryBtn; //查询按钮

    private BorderLayout layout;

    private UcDefineControl control;
    CenterTable  centerTable;//中间table展示

    /**
     * 获得输入框数据
     * @return
     */
    public String getInputTxt(){
        return queryInput.getText();
    }

    /**
     * 向上传播动作,传播到myActionListener
     * @param e
     */
    public void spreadAction(ActionEvent e){
        myActionListener.actionPerformed(e);
    }
    public UcDefineMaintain(MyActionListener myActionListener) {
        this.myActionListener= myActionListener;
        control=new UcDefineControl(this);
        centerTable=new CenterTable(myActionListener);
        init();
    }

    private void init(){
        layout=new BorderLayout();
        this.setLayout(layout);
        initNorth();
        this.add(centerTable,BorderLayout.CENTER);
    }

    /**
     * BorderLayout的North
     */
    private void initNorth(){
        insertBtn=new JButton("插入");
        tailInsertBtn=new JButton("尾加");
        delBtn=new JButton("删除");
        saveBtn=new JButton("存盘");
        queryInput=new JTextField(20);
        queryBtn=new JButton("查询");

        //region 监听命令添加
        insertBtn.setActionCommand(EnActionEvent.UCDEFINE_INSERTCLICK.getCmd());
        tailInsertBtn.setActionCommand(EnActionEvent.UCDEFINE_TAILINSERTCLICK.getCmd());
        delBtn.setActionCommand(EnActionEvent.UCDEFINE_DELCLICK.getCmd());
        saveBtn.setActionCommand(EnActionEvent.UCDEFINE_SAVECLICK.getCmd());
        queryBtn.setActionCommand(EnActionEvent.UCDEFINE_QUERYCLICK.getCmd());

        insertBtn.addActionListener(control);
        tailInsertBtn.addActionListener(control);
        delBtn.addActionListener(control);
        saveBtn.addActionListener(control);
        queryBtn.addActionListener(control);

        //endregion


        JPanel northPanel=new JPanel();
        //设置边框
        northPanel.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));

        //region 布局管理
        GridLayout layout=new GridLayout(1,2);
        northPanel.setLayout(layout);

        JPanel btnJpanel=new JPanel();
        FlowLayout flowLayout=new FlowLayout(FlowLayout.LEFT );
        btnJpanel.setLayout(flowLayout);
        btnJpanel.add(insertBtn);
        btnJpanel.add(tailInsertBtn);
        btnJpanel.add(delBtn);
        btnJpanel.add(saveBtn);

        JPanel queryJpanel=new JPanel();
        queryJpanel.setLayout(flowLayout);
        //queryInput.setSize(50,10);
        queryInput.setBounds(10,10,30,170);
        queryJpanel.add(new JLabel("权限/UC"));
        queryJpanel.add(queryInput);
        queryJpanel.add(queryBtn);
       /* GridBagLayout queryPanellayout=new GridBagLayout();
        GridBagConstraints constraints=new GridBagConstraints();
        constraints.fill=GridBagConstraints.BOTH;
        constraints.weightx=0.0;
        queryJpanel.setLayout(queryPanellayout);
        queryInput.setSize(50,10);
        queryJpanel.add(new JLabel("权限/UC"));
        constraints.weightx=1.0;
        queryJpanel.add(queryInput);
        constraints.weightx=0.0;
        queryJpanel.add(queryBtn);*/
        //endregion

        //添加元素
        northPanel.add(btnJpanel);
        northPanel.add(queryJpanel);


        this.add(northPanel,BorderLayout.NORTH);
    }

    public CenterTable getCenterTable() {
        return centerTable;
    }
}
