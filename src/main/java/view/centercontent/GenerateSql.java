package view.centercontent;

import constant.EnActionEvent;
import control.MyActionListener;
import service.SvcService;
import service.impl.SvcServiceImpl;
import util.StringUtils;
import view.MainFrame;
import view.component.SButton;
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
public class GenerateSql  extends BaseJPanel implements ActionListener {
    private SvcService svcService=new SvcServiceImpl();
    private   CenterContentPanel centerContentPanel;
    /**
     *生成SQL
     */
    private SButton generateSqlBtn=new SButton("生成SQL");
    private JTextField filepath = new JTextField(30);

    private SButton filechooserbtn=new SButton("选择目录");
    /**
     *保存
     */
    private SButton saveBtn=new SButton("保存");
    /**
     * 生成sql文本框
     */
    private JTextArea sqlText=new JTextArea();

    public GenerateSql(MyActionListener myActionListener,CenterContentPanel centerContentPanel) {
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
            sqlText.setText(sql);
        }else if(e.getActionCommand().equals(EnActionEvent.GENERATESQL_OPENFILESEL.getCmd())){
            String filePath= MainFrame.openFileSelect();
            if(StringUtils.isNotNullAndNotEmpty(filePath)){
                filepath.setText(filePath);
            }
        }else if(e.getActionCommand().equals(EnActionEvent.GENERATESQL_SAVE.getCmd())){

        }
    }
}
