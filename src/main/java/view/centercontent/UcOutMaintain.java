package view.centercontent;

import bean.TsvcViewconfig;
import constant.EnActionEvent;
import control.MyActionListener;
import service.SvcService;
import service.impl.SvcServiceImpl;
import view.centercontent.ucout.UcOutCenterTable;
import view.factory.ColorFactory;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lyd on 2017/5/11.
 */
public class UcOutMaintain extends BaseJPanel   implements ActionListener {
    SvcService svcService=new SvcServiceImpl();
    private   CenterContentPanel centerContentPanel;
    //初始化输入输出
    private JButton initBtn=new JButton("初始化输入输出");
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
    private UcOutCenterTable centerTable;

    @Override
    public void close() {
        centerTable=null;
    }


    public UcOutMaintain(MyActionListener myActionListener,CenterContentPanel centerContentPanel) {
        this.centerContentPanel=centerContentPanel;
        this.myActionListener= myActionListener;
        centerTable=new UcOutCenterTable(myActionListener,this,centerContentPanel);
        init();
    }

    private void init(){
        //<editor-fold desc="设置命令">
        initBtn.setActionCommand(EnActionEvent.UCOUT_INIT.getCmd());
        copyBtn.setActionCommand(EnActionEvent.UCOUT_COPY.getCmd());
        insertBtn.setActionCommand(EnActionEvent.UCOUT_INSERT.getCmd());
        tailInsertBtn.setActionCommand(EnActionEvent.UCOUT_TAILINSERT.getCmd());
        delBtn.setActionCommand(EnActionEvent.UCOUT_DEL.getCmd());
        saveBtn.setActionCommand(EnActionEvent.UCOUT_SAVE.getCmd());
        refreshBtn.setActionCommand(EnActionEvent.UCOUT_REFRESH.getCmd());
        upBtn.setActionCommand(EnActionEvent.UCOUT_UP.getCmd());
        downBtn.setActionCommand(EnActionEvent.UCOUT_DOWN.getCmd());
        //</editor-fold>

        //<editor-fold desc="设置监听">
        initBtn.addActionListener(this);
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
        initBtn.setFont(FontFactory.getBtnFont());
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
        northJPanel.add(initBtn);
        //northJPanel.add(copyBtn);
        northJPanel.add(insertBtn);
        northJPanel.add(tailInsertBtn);
        northJPanel.add(delBtn);
        northJPanel.add(saveBtn);
        northJPanel.add(refreshBtn);
       // northJPanel.add(upBtn);
        //northJPanel.add(downBtn);
        //</editor-fold>



        //添加组件到窗体
        this.setLayout(new BorderLayout());
        this.add(northJPanel,BorderLayout.NORTH);
        this.add(centerTable,BorderLayout.CENTER);

    }
    @Override
    public void onFocus(boolean refresh) {
        if(refresh){
            centerTable.reloadUc(centerContentPanel.getUcNo());

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println(e.getActionCommand());
        if(e.getActionCommand().equals(EnActionEvent.UCOUT_SAVE.getCmd())){
            java.util.List<TsvcViewconfig> tsvcInterfaceList=centerTable.getAllColumnDatas();
            //存盘
            svcService.saveTsvcViewconfig(tsvcInterfaceList,centerContentPanel.getUcNo());
            //刷新
            centerTable.reloadUc(centerContentPanel.getUcNo());
        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_INIT.getCmd())){
            centerTable.initData();
        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_COPY.getCmd())){

        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_INSERT.getCmd())){
            //插入
            centerTable.insert();
        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_TAILINSERT.getCmd())){
            //尾加
            centerTable.tailInsert();
        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_DEL.getCmd())){
            //删除
            centerTable.removeSelect();
        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_REFRESH.getCmd())){
            centerTable.reloadUc(centerContentPanel.getUcNo());
        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_UP.getCmd())){

        }else if(e.getActionCommand().equals(EnActionEvent.UCOUT_DOWN.getCmd())){

        }
    }


}
