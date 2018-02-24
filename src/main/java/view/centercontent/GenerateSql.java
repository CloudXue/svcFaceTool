package view.centercontent;

import constant.EnActionEvent;
import control.MyActionListener;
import service.ServiceFactory;
import service.SvcService;
import service.impl.SvcServiceImpl;
import util.DateUtil;
import util.StringUtils;
import util.SystemUtil;
import view.MainFrame;
import view.component.SButton;
import view.factory.ColorFactory;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyd on 2017/5/11.
 */
public class GenerateSql  extends BaseJPanel implements ActionListener {
    private SvcService svcService= ServiceFactory.getSvcService();
    private   CenterContentPanel centerContentPanel;
    /**
     *生成SQL
     */
    private SButton generateSqlBtn=new SButton("生成SQL");
    private JTextField filepath = new JTextField(60);

    private SButton filechooserbtn=new SButton("选择目录");
    /**
     *保存
     */
    private SButton saveBtn=new SButton("保存");
    /**
     * 生成sql文本框
     */
    private JTextArea sqlText=new JTextArea();

    @Override
    public void close() {

    }

    public GenerateSql(MyActionListener myActionListener, CenterContentPanel centerContentPanel) {
        this.centerContentPanel=centerContentPanel;
        this.myActionListener= myActionListener;
        init();
    }
    private void init(){

        JLabel jLabel1=new JLabel("生成响应的配置SQL:");
        JLabel jLabel2=new JLabel("保存到:");
        jLabel1.setFont(FontFactory.getJTableFont());
        jLabel2.setFont(FontFactory.getJTableFont());

        generateSqlBtn.setActionCommand(EnActionEvent.GENERATESQL_GEN.getCmd());
        filechooserbtn.setActionCommand(EnActionEvent.GENERATESQL_OPENFILESEL.getCmd());
        saveBtn.setActionCommand(EnActionEvent.GENERATESQL_SAVE.getCmd());
        generateSqlBtn.addActionListener(this);
        filechooserbtn.addActionListener(this);
        saveBtn.addActionListener(this);

        filepath.setFont(FontFactory.getTxtInputFootFont());

        JPanel northPanel=new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        northPanel.add(jLabel1);
        northPanel.add(generateSqlBtn);
        northPanel.add(jLabel2);
        northPanel.add(filepath);
        northPanel.add(filechooserbtn);
        northPanel.add(saveBtn);

        //设置边框
        sqlText.setFont(FontFactory.getSqlInputFootFont());
        JScrollPane sqlTextScrollPane = new JScrollPane(sqlText);
        sqlTextScrollPane.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));


        this.setLayout(new BorderLayout());
        this.add(northPanel,BorderLayout.NORTH);
        this.add(sqlTextScrollPane,BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(EnActionEvent.GENERATESQL_GEN.getCmd())){
            String sql=svcService.generateSql(centerContentPanel.getUcNo());
            String filePathNamestr=filepath.getText();
            String separator="/";
            if(File.separator.equals("/")){
                separator="/";
            }else{
                separator="\\";
            }
            if(StringUtils.isNotNullAndNotEmpty(filePathNamestr)){

                filePathNamestr=filePathNamestr.substring(0,filePathNamestr.lastIndexOf(separator));
                filePathNamestr+=separator+getFileName();
            }else{
                filePathNamestr=SystemUtil.getSvcDirectory()+getFileName();
            }

            if(File.separator.equals("/")){
                filePathNamestr=filePathNamestr.replaceAll("\\\\",separator);
            }else{
                filePathNamestr=filePathNamestr.replaceAll("/","\\\\");
            }
            filepath.setText(filePathNamestr);
            sqlText.setText(sql);
        }else if(e.getActionCommand().equals(EnActionEvent.GENERATESQL_OPENFILESEL.getCmd())){
            String filePath= MainFrame.openFileSelect();
            if(StringUtils.isNotNullAndNotEmpty(filePath)){
                String fileName=filePath+File.separator+getFileName();
                if(File.separator.equals("/")){
                    fileName=fileName.replaceAll("\\\\","/");
                }else{
                    fileName=fileName.replaceAll("/","\\\\");
                }
                filepath.setText(fileName);
            }
        }else if(e.getActionCommand().equals(EnActionEvent.GENERATESQL_SAVE.getCmd())){
            String filePathNamestr=filepath.getText();
            String ucSql=sqlText.getText();
            if(StringUtils.isNullOrEmpty(ucSql)){
                showWarningMsg("请先生成sql语句");
                return;
            }
            if (StringUtils.isNullOrEmpty(filePathNamestr)) {
                showWarningMsg("保存目录必填！");
                return;
            }
            try {
                if(!validFile(new String[]{filePathNamestr})){
                    //验证不通过
                    return;
                }
            } catch (Exception e1) {
                showWarningMsg(e1.getMessage());
                return;
            }
            File file=new File(filePathNamestr);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            PrintWriter writer=null;
            try {
                 writer=new PrintWriter(new FileWriter(file));
                writer.write(ucSql);
                writer.flush();
                JOptionPane.showMessageDialog(this,"保存成功","提示",JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                showWarningMsg(e1.getMessage());
                return;
            }finally {
                if(writer!=null){
                    writer.close();
                }
            }
        }
    }

    private void showWarningMsg(String msg){
        JOptionPane.showMessageDialog(this,msg,"提示",JOptionPane.WARNING_MESSAGE);

    }
    /**
     * 验证通过，会删除源文件
     * @param filePathName
     * @return
     */
    private  boolean validFile(String [] filePathName) throws Exception {
        List<File> files=new ArrayList<File>();
        for(String name : filePathName){
            File file=new File(name);
            if(validFile(file)){
                files.add(file);
            }else{
                return false;
            }
        }
        //删除file
        for(File file :files){
            if(file.exists()){
                if(!file.delete()){
                    throw new Exception("无法覆盖文件："+file.getName());
                }
            }
        }
        return true;
    }
    private boolean validFile(File file){
        if(file.exists()){
            int i=JOptionPane.showConfirmDialog(this,"文件："+file.getName()+",已存在，是否覆盖","警告",JOptionPane.WARNING_MESSAGE);
            if(i==2){
                //取消
                return false;
            }
        }
        return true;
    }

    /**
     * 返回文件名称
     * @return
     */
    private String getFileName(){
        return centerContentPanel.getUcCodeField()+"_"+ DateUtil.getCurrentDateString("yyyyMMdd")+".sql";
    }
}
