package view.centercontent;

import bean.SqlFieldType;
import bean.TsvcInterface;
import constant.EnActionEvent;
import control.MyActionListener;
import service.SvcService;
import service.impl.SvcServiceImpl;
import view.centercontent.ucin.UcInCenterTable;
import view.factory.ColorFactory;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by lyd on 2017/5/11.
 */
public class UcInMaintain extends BaseJPanel  implements ActionListener {
    SvcService svcService=new SvcServiceImpl();
    private   CenterContentPanel centerContentPanel;
    //初始化输入输出
    private JButton initInBtn=new JButton("初始化输入");
    private JButton initOutBtn=new JButton("初始化输出");
    //拷贝自
    private JButton copyBtn=new JButton("拷贝自");
    //插入
    private JButton insertBtn=new JButton("插入");
    //尾加
    private JButton tailInsertBtn=new JButton("尾加");
    //删除
    private JButton delBtn=new JButton("删除");
    //存盘
    private JButton saveBtn=new JButton("存盘");
    //刷新
    private JButton refreshBtn=new JButton("刷新");
    //上移
    private JButton upBtn=new JButton("上移");
    //下移
    private JButton downBtn=new JButton("下移");

    //中间table展示
    private UcInCenterTable centerTable;

    @Override
    public void close() {
        centerTable=null;
    }

    public UcInMaintain(MyActionListener myActionListener, CenterContentPanel centerContentPanel) {
        this.centerContentPanel=centerContentPanel;
        this.myActionListener= myActionListener;
        centerTable=new UcInCenterTable(myActionListener,this,centerContentPanel);
        init();
    }

    private void init(){
        //<editor-fold desc="设置命令">
        initInBtn.setActionCommand(EnActionEvent.UCIN_INITIN.getCmd());
        initOutBtn.setActionCommand(EnActionEvent.UCIN_INITOUT.getCmd());
        copyBtn.setActionCommand(EnActionEvent.UCIN_COPY.getCmd());
        insertBtn.setActionCommand(EnActionEvent.UCIN_INSERT.getCmd());
        tailInsertBtn.setActionCommand(EnActionEvent.UCIN_TAILINSERT.getCmd());
        delBtn.setActionCommand(EnActionEvent.UCIN_DEL.getCmd());
        saveBtn.setActionCommand(EnActionEvent.UCIN_SAVE.getCmd());
        refreshBtn.setActionCommand(EnActionEvent.UCIN_REFRESH.getCmd());
        upBtn.setActionCommand(EnActionEvent.UCIN_UP.getCmd());
        downBtn.setActionCommand(EnActionEvent.UCIN_DOWN.getCmd());
        //</editor-fold>

        //<editor-fold desc="设置监听">
        initInBtn.addActionListener(this);
        initOutBtn.addActionListener(this);
        copyBtn.addActionListener(this);
        insertBtn.addActionListener(this);
        tailInsertBtn.addActionListener(this);
        delBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        refreshBtn.addActionListener(this);
        upBtn.addActionListener(this);
        downBtn.addActionListener(this);
        //</editor-fold>

        //<editor-fold desc="设置字体">
        initInBtn.setFont(FontFactory.getBtnFont());
        initOutBtn.setFont(FontFactory.getBtnFont());
        copyBtn.setFont(FontFactory.getBtnFont());
        insertBtn.setFont(FontFactory.getBtnFont());
        tailInsertBtn.setFont(FontFactory.getBtnFont());
        delBtn.setFont(FontFactory.getBtnFont());
        saveBtn.setFont(FontFactory.getBtnFont());
        refreshBtn.setFont(FontFactory.getBtnFont());
        upBtn.setFont(FontFactory.getBtnFont());
        downBtn.setFont(FontFactory.getBtnFont());
        //</editor-fold>

        //<editor-fold desc="北部panel">
        JPanel northJPanel=new JPanel();
        //设置边框
        northJPanel.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));
        northJPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        northJPanel.add(initInBtn);
        northJPanel.add(initOutBtn);
        northJPanel.add(copyBtn);
        northJPanel.add(insertBtn);
        northJPanel.add(tailInsertBtn);
        northJPanel.add(delBtn);
        northJPanel.add(saveBtn);
        northJPanel.add(refreshBtn);
        northJPanel.add(upBtn);
        northJPanel.add(downBtn);
        //</editor-fold>



        //添加组件到窗体
        this.setLayout(new BorderLayout());
        this.add(northJPanel,BorderLayout.NORTH);
        this.add(centerTable,BorderLayout.CENTER);

    }
    @Override
    public void onFocus(boolean refresh) {
        if(refresh){
            centerTable.asynReloadUc(centerContentPanel.getUcNo());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if(centerTable.isRefresh()){
            System.out.println("正在刷新");
            return;
        }
        if(e.getActionCommand().equals(EnActionEvent.UCIN_SAVE.getCmd())){
            java.util.List<TsvcInterface> tsvcInterfaceList=centerTable.getAllColumnDatas();
            //存盘
            svcService.saveTsvcInterface(tsvcInterfaceList,centerContentPanel.getUcNo());
            //刷新
            centerTable.asynReloadUc(centerContentPanel.getUcNo());
        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_INITIN.getCmd())){
            //初始化
            List<SqlFieldType> fieldList= svcService.findSqlField(centerContentPanel.getUcNo());
            centerTable.initIn(fieldList);

        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_INITOUT.getCmd())){
            //初始化
            List<SqlFieldType> fieldList= svcService.findSqlField(centerContentPanel.getUcNo());
            centerTable.initOut(fieldList);

        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_COPY.getCmd())){

        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_INSERT.getCmd())){
            //插入
            centerTable.insert();
        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_TAILINSERT.getCmd())){
            //尾加
            centerTable.tailInsert();
        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_DEL.getCmd())){
            //删除
            centerTable.removeSelect();
        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_REFRESH.getCmd())){
            centerTable.asynReloadUc(centerContentPanel.getUcNo());
        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_UP.getCmd())){

        }else if(e.getActionCommand().equals(EnActionEvent.UCIN_DOWN.getCmd())){

        }

    }
}
