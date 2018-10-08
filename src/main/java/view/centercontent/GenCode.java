package view.centercontent;

import bean.SystemData;
import constant.EnActionEvent;
import control.MyActionListener;
import service.GenerateCodeService;
import service.ServiceFactory;
import service.SvcService;
import util.LogUtil;
import util.StringUtils;
import util.SystemUtil;
import view.MainFrame;
import view.component.ComboBoxMapModel;
import view.component.EditComBox;
import view.component.SButton;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * 功能说明:
 * @author: lyd
 * 开发时间: 2018-09-30
 */
public class GenCode extends BaseJPanel  implements ActionListener {
    SvcService svcService= ServiceFactory.getSvcService();
    GenerateCodeService generateCodeService= ServiceFactory.getGenerateCodeService();
    private JLabel tableNamelabel=new JLabel("表名称：");
    JComboBox tableName ;


    private JLabel dtoNamelabel=new JLabel("dto类名：");
    JTextField dtoName =new JTextField(30);

    private JLabel dtoParentlabel=new JLabel("dto父类名：");
    JComboBox dtoParent = new EditComBox(true);

    private JLabel packageStrlabel=new JLabel("包名：");
    JTextField packageStr =new JTextField(30);



    private SButton generateBtn=new SButton("生成");
    private JTextField filepath = new JTextField(60);

    private SButton filechooserbtn=new SButton("选择目录");
    private SButton saveBtn=new SButton("保存");
    private SButton openBtn=new SButton("打开目录");


    public GenCode(MyActionListener myActionListener) {
        this.myActionListener= myActionListener;
        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();
    }
    private void init(){
        try {
            ComboBoxMapModel mapModel = new ComboBoxMapModel(generateCodeService.getTables());
            tableName = new EditComBox(mapModel,true);
        } catch (Exception e) {
            showWarningMsg(e.getMessage());
            return;
        }

        tableNamelabel.setFont(FontFactory.getJTableFont());
        dtoNamelabel.setFont(FontFactory.getJTableFont());
        dtoParentlabel.setFont(FontFactory.getJTableFont());
        packageStrlabel.setFont(FontFactory.getJTableFont());

        dtoName.setFont(FontFactory.getTxtInputFootFont());
        packageStr.setFont(FontFactory.getTxtInputFootFont());
        filepath.setFont(FontFactory.getTxtInputFootFont());


        generateBtn.setFont(FontFactory.getBtnFont());
        filechooserbtn.setFont(FontFactory.getBtnFont());
        saveBtn.setFont(FontFactory.getBtnFont());
        openBtn.setFont(FontFactory.getBtnFont());

        generateBtn.setActionCommand(EnActionEvent.GENCODE_GEN.getCmd());
        filechooserbtn.setActionCommand(EnActionEvent.GENCODE_OPENFILESEL.getCmd());
        saveBtn.setActionCommand(EnActionEvent.GENCODE_SAVE.getCmd());
        openBtn.setActionCommand(EnActionEvent.GENCODE_OPEN.getCmd());

        generateBtn.addActionListener(this);
        filechooserbtn.addActionListener(this);
        saveBtn.addActionListener(this);
        openBtn.addActionListener(this);



        //tableName.setSize(new Dimension(180, 40));
        // 这里就是设置JComboBox宽度的代码
        tableName.setPreferredSize(new Dimension(200, 20));
        dtoParent.setPreferredSize(new Dimension(120, 20));
        //dtoParent.setSize(new Dimension(180, 40));

        JPanel headPanel =new JPanel();

        JPanel infoPanel =new JPanel();

        infoPanel.add(tableNamelabel);
        infoPanel.add(tableName);

        infoPanel.add(dtoNamelabel);
        infoPanel.add(dtoName);

        infoPanel.add(dtoParentlabel);
        infoPanel.add(dtoParent);

        infoPanel.add(packageStrlabel);
        infoPanel.add(packageStr);

        JPanel filePanel=new JPanel();
        filePanel.add(generateBtn);
        filePanel.add(filepath);
        filePanel.add(filechooserbtn);
        filePanel.add(saveBtn);
        filePanel.add(openBtn);

        headPanel.setLayout(new BorderLayout());
        headPanel.add(infoPanel,BorderLayout.NORTH);
        headPanel.add(filePanel,BorderLayout.CENTER);


        this.setLayout(new BorderLayout());
        this.add(headPanel,BorderLayout.NORTH);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(EnActionEvent.GENCODE_GEN.getCmd())){

        } else if(e.getActionCommand().equals(EnActionEvent.GENCODE_OPENFILESEL.getCmd())){
            String filePath= MainFrame.openFileSelect();
            if(StringUtils.isNotNullAndNotEmpty(filePath)){
                String fileName=filePath+ File.separator;
                if(File.separator.equals("/")){
                    fileName=fileName.replaceAll("\\\\","/");
                }else{
                    fileName=fileName.replaceAll("/","\\\\");
                }
                filepath.setText(fileName);
            }
        }else if(e.getActionCommand().equals(EnActionEvent.GENCODE_SAVE.getCmd())){

        }else if(e.getActionCommand().equals(EnActionEvent.GENCODE_OPEN.getCmd())){
            String filePathNamestr=filepath.getText();
            String filePathstr="";
            if (StringUtils.isNullOrEmpty(filePathNamestr)) {
                showWarningMsg("保存目录必填！");
                return;
            }
            String separator="/";
            if(File.separator.equals("/")){
                separator="/";
            }else{
                separator="\\";
            }
            if(StringUtils.isNotNullAndNotEmpty(filePathNamestr)){

                filePathNamestr=filePathNamestr.substring(0,filePathNamestr.lastIndexOf(separator));
            }else{
                filePathNamestr=SystemUtil.getSvcDirectory();
            }

            if(File.separator.equals("/")){
                filePathstr=filePathNamestr.replaceAll("\\\\",separator);
            }else{
                filePathstr=filePathNamestr.replaceAll("/","\\\\");
            }
            String[] cmd = new String[5];
            cmd[0] = "cmd";
            cmd[1] = "/c";
            cmd[2] = "start";
            cmd[3] = " ";
            cmd[4] = filePathstr;
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e1) {
                showWarningMsg("无法打开目录："+filePathstr+",异常信息："+e1.getMessage());
            }
        }

    }

    @Override
    public void onFocus(boolean refresh) {
        super.onFocus(refresh);
        String filePathNamestr=filepath.getText();
        if (StringUtils.isNullOrEmpty(filePathNamestr)) {
            String filePath= SystemUtil.getDbDirectory();
            if(File.separator.equals("/")){
                filePath=filePath.replaceAll("\\\\","/");
            }else{
                filePath=filePath.replaceAll("/","\\\\");
            }
            filepath.setText(filePath);
        }

    }

    @Override
    public void close() {

    }

    private void showWarningMsg(String msg){
        JOptionPane.showMessageDialog(this,msg,"提示",JOptionPane.WARNING_MESSAGE);

    }
}
