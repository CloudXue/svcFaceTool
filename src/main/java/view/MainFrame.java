package view;

import constant.ENWarningLevel;
import control.MyActionListener;
import util.LogUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lyd on 2017/1/4.
 */
public class MainFrame extends JFrame {
    private MenuBar menuBar;
    private ToolBar toolBar;
    private FootBar footBar;
    private CenterPanel centerPanel;
    private MyActionListener myActionListener;
    JDialog msgDialog=new JDialog(this);
    private JLabel msgLabel=new JLabel();


    public MainFrame() throws HeadlessException {
        super("Demo");
        initFrame();
    }
    private  void initFrame(){

        setLayout(new BorderLayout());

        //region 菜单
        myActionListener =new MyActionListener(this);
        menuBar=new MenuBar(myActionListener);
        this.setJMenuBar(menuBar);
        //endregion

        //region bar
        toolBar=new ToolBar(myActionListener);
        footBar=new FootBar();
        this.add(toolBar,BorderLayout.NORTH);
        this.add(footBar,BorderLayout.SOUTH);
        //endregion
        //region center中间
        centerPanel=new CenterPanel(myActionListener);
        this.add(centerPanel,BorderLayout.CENTER);
        //endregion





        //region frame基础设置
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        //endregion

        msgDialog.add(msgLabel);
    }
    public void addTab(String titleName){
        centerPanel.addTab(titleName);
    }
    public void closeAllTab(){
        centerPanel.closeAllTab();
        //关闭后增加一个默认的
        centerPanel.addTab("");
    }
    public static void main(String[] args) {
        if(args!=null && args.length>1){
            String logFilePaht=args[0];
            LogUtil.setFileLogPath(logFilePaht);
        }
        MainFrame mainFrame=new MainFrame();
        //增加一个默认窗口
        mainFrame.addTab("");
    }

    public void showMsg(String tltle, String msg, ENWarningLevel warningLevel){
        LogUtil.info(tltle+":"+msg+":"+warningLevel);
        msg=System.getProperties().getProperty("log.base");
        msgLabel.setText(msg);
        msgDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        msgDialog.setLocationRelativeTo(null);
        msgDialog.setTitle(tltle);
        msgDialog.setSize(300,200);
        msgDialog.setVisible(true);
        LogUtil.info(tltle+":"+msg+":"+warningLevel);
    }
}
