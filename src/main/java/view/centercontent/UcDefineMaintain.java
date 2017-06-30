package view.centercontent;

import bean.HsiRight;
import constant.EnActionEvent;
import service.UcDefineService;
import service.impl.UcDefineServiceImpl;
import util.StringUtils;
import control.MyActionListener;
import view.centercontent.fcdefine.CenterTable;
import view.centercontent.fcdefine.FootPanel;
import view.factory.ColorFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyd on 2017/5/11.
 * 功能表定义维护panel
 * 使用BorderLayout布局，上中下三部分
 */
public class UcDefineMaintain extends BaseJPanel  {
    private   CenterContentPanel centerContentPanel;
    UcDefineService ucDefineService=new UcDefineServiceImpl();
    private JButton insertBtn;    //插入按钮
    private JButton tailInsertBtn;//尾加按钮
    private JButton delBtn;       //删除按钮
    private JButton saveBtn;      //存盘按钮
    private JButton exportExcelBtn;//导出excel按钮

    private JTextField queryInput;//输入查询框
    private JButton queryBtn; //查询按钮

    private BorderLayout layout;

    private UcDefineControl control;
    private CenterTable  centerTable;//中间table展示
    private FootPanel footPanel;//中间table展示

    private Map<String,HsiRight> addUcMap=new HashMap<String,HsiRight>();
    private Map<String,HsiRight> editUcMap=new HashMap<String,HsiRight>();
    private List<String> deleUcList=new ArrayList<String>();



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
    public UcDefineMaintain(MyActionListener myActionListener,CenterContentPanel centerContentPanel) {
        this.centerContentPanel=centerContentPanel;
        this.myActionListener= myActionListener;
        control=new UcDefineControl(this);
        centerTable=new CenterTable(myActionListener,this);
        footPanel=new FootPanel(myActionListener,this);
        init();
    }

    private void init(){
        layout=new BorderLayout();
        this.setLayout(layout);
        initNorth();
        this.add(centerTable,BorderLayout.CENTER);
        this.add(footPanel,BorderLayout.SOUTH);
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

    public void tableSelect(int index,String functionno){
        HsiRight hsiRight= null;
        try {
            if(StringUtils.isNotNullAndNotEmpty(functionno)){
                //不为空才去查
                hsiRight = ucDefineService.getUc(functionno);
            }
        } catch (Exception e) {
            handleExceptionMsg(e);
        }
        if(hsiRight==null){
            //更改title
            centerContentPanel.setTitle("home");
            //头部更改
            centerContentPanel.setHeadValue("","","");
            //底部修改
            footPanel.setFootPanelData(null);
        }else{
            //更改title
            centerContentPanel.setTitle(hsiRight.getC_functionno());
            //头部更改
            centerContentPanel.setHeadValue(hsiRight.getC_rightcode(),hsiRight.getC_functionno(),hsiRight.getC_rightname());
            //底部修改
            footPanel.setFootPanelData(hsiRight);
        }

    }

    public void addAddUcMap(String ucno,HsiRight hsiRight){

       addUcMap.put(ucno,hsiRight);
    }

    /**
     * 如果在新增map里有这个uc，那么就去addmap里操作
     * @param ucno
     * @param hsiRight
     */
    public void addEditUcMap(String ucno,HsiRight hsiRight){
        if(addUcMap.containsKey(ucno)){
            addUcMap.put(ucno,hsiRight);
        }else{
            editUcMap.put(ucno,hsiRight);
        }

    }


    //内部监听类
     class UcDefineControl  implements ActionListener {
        UcDefineMaintain ucDefineMaintain;

        public UcDefineControl(UcDefineMaintain ucDefineMaintain) {
            this.ucDefineMaintain = ucDefineMaintain;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.err.println(e.getActionCommand());
            if(e.getActionCommand().equals(EnActionEvent.UCDEFINE_QUERYCLICK.getCmd())){
                //清空所有缓存数据
                addUcMap.clear();;
                editUcMap.clear();
                deleUcList.clear();
                //重新加载table
                String searchTxt=ucDefineMaintain.getInputTxt();
                centerTable.reloadUc(searchTxt);
                //底部选择第一行
                centerTable.cloumSelect(0);
                //向上传播
                ucDefineMaintain.spreadAction(e);
            }else if(e.getActionCommand().equals(EnActionEvent.UCDEFINE_INSERTCLICK.getCmd())){
                //插入

            }else if(e.getActionCommand().equals(EnActionEvent.UCDEFINE_TAILINSERTCLICK.getCmd())){
                //尾插入

            }else if(e.getActionCommand().equals(EnActionEvent.UCDEFINE_DELCLICK.getCmd())){
                //删除 todo 数据中先删除
                ucDefineMaintain.getCenterTable().removeSelect();
            }else if(e.getActionCommand().equals(EnActionEvent.UCDEFINE_SAVECLICK.getCmd())){
                //存盘

                //清空所有缓存数据
                addUcMap.clear();;
                editUcMap.clear();
                deleUcList.clear();
            }

        }
    }
}
