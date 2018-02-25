package view;

import constant.ENWarningLevel;
import control.MyActionListener;
import service.ServiceFactory;
import service.SvcService;
import service.impl.SvcServiceImpl;
import util.LogUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by lyd on 2017/1/4.
 */
public class MainFrame extends JFrame {
    static SvcService svcService= ServiceFactory.getSvcService();
    private MenuBar menuBar;
    private ToolBar toolBar;
    private FootBar footBar;
    private CenterPanel centerPanel;
    private MyActionListener myActionListener;
    JDialog msgDialog=new JDialog(this);
    private JLabel msgLabel=new JLabel();

    private static MainFrame mainFrame;

    public MainFrame() throws HeadlessException {
        super("Demo");
        initFrame();
    }
    private  void initFrame(){

        setLayout(new BorderLayout());

        //region 菜单
        myActionListener =new MyActionListener(this);
        //设置服务里监听，用于后台提交信息到窗体
        svcService.setViewListener(myActionListener);
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
        setSize(1200,800);
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
        svcService.initSystem();

        if(args!=null && args.length>1){
            String logFilePaht=args[0];
            LogUtil.setFileLogPath(logFilePaht);
        }
        mainFrame=new MainFrame();
        //增加一个默认窗口
        mainFrame.addTab("");
    }

    public void showMsg(String tltle, String msg, ENWarningLevel warningLevel){
        msgLabel.setText(msg);
        msgDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        msgDialog.setLocationRelativeTo(null);
        msgDialog.setTitle(tltle);
        msgDialog.setSize(300,200);
        msgDialog.setVisible(true);

    }
    public static String openFileSelect(){
        String  filePahtName=null;

       /* FileDialog fileDialog  = new FileDialog(mainFrame, "Open File", FileDialog.LOAD);
        fileDialog.setVisible(true);

        if(fileDialog.getDirectory()!=null && fileDialog.getFile()!=null){
             filePahtName=fileDialog.getDirectory() + fileDialog.getFile();
        }*/

        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY  );
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        filePahtName=file.getAbsolutePath();
        return filePahtName;
    }
    public void changeFootMsg(String status,String msg){
        footBar.changeMsg(status,msg);
    }
    public void changeFootMsg(String conninfoStr,String status,String msg){
        footBar.changeMsg(conninfoStr,status,msg);
    }
}
