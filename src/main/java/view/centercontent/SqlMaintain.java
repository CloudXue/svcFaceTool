package view.centercontent;

import bean.TsvcSql;
import constant.EnActionEvent;
import control.MyActionListener;
import service.ServiceFactory;
import service.TsvcSqlService;
import service.impl.TsvcSqlServiceImpl;
import util.StringUtils;
import view.component.ComboBox;
import view.component.SButton;
import view.factory.ColorFactory;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lyd on 2017/5/11.
 */
public class SqlMaintain  extends BaseJPanel {
    TsvcSqlService tsvcSqlService= ServiceFactory.getTsvcSqlService();
    private boolean enableTestSql=false;
    /**
     * 数据，原始
     */
    private TsvcSql tsvcSql_old;
    /**
     * 修改后
     */
    private TsvcSql tsvcSql_new;
    /**
     * 数据是否有过修改
     */
    private boolean isDataChange=false;
    /**
     *按钮事件控制
     */
    private SqlMaintainControl control;
    private   CenterContentPanel centerContentPanel;
    /**
     *删除按钮
     */
    private SButton delBtn=new SButton("删除");
    /**
     *刷新按钮
     */
    private SButton refreshBtn=new SButton("刷新");
    /**
     *存盘按钮
     */
    private SButton saveBtn=new SButton("存盘");
    /**
     *sql帮助按钮
     */
    private SButton helpBtn=new SButton("sql写法帮助");
    /**
     *sql测试
     */
    private SButton sqlTestBtn=new SButton("sql语句测试");
    /**
    * sql分页测试
    */
    private SButton sqlPagingBtn=new SButton("分页组合sql测试");
    /**
     * sql语句
     */
    private JTextArea sqlText=new JTextArea();
    /**
     * 默认排序字段
     */
    private JTextField orderField=new JTextField(30);
    /**
     *sql类型
     */
    private ComboBox sqlType;
    /**
     * sql类型
     */
    private Map<String,String> sqlTypeMap;


    private JTable sqlTestResultTable=new JTable();//测试结果table

    @Override
    public void close() {
        tsvcSqlService=null;
    }

    public SqlMaintain(MyActionListener myActionListener, CenterContentPanel centerContentPanel) {
        control=new SqlMaintainControl();
        sqlTypeMap=new LinkedHashMap<String,String>();
        sqlTypeMap.put("0:普通SQL语句","0");
        sqlTypeMap.put("1:存储过程","1");
        sqlType=new ComboBox(sqlTypeMap);
        this.centerContentPanel=centerContentPanel;
        this.myActionListener= myActionListener;
        init();
    }
    private void init(){
       // JLabel jLabel=new JLabel("SqlMaintain");
        //this.add(jLabel);
        this.setLayout(new BorderLayout());
        //按钮区域
        JPanel northPanel=new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        //设置边框
        northPanel.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));
        delBtn.setActionCommand(EnActionEvent.SQLMAINTAIN_DEL.getCmd());
        saveBtn.setActionCommand(EnActionEvent.SQLMAINTAIN_SAVE.getCmd());
        refreshBtn.setActionCommand(EnActionEvent.SQLMAINTAIN_REFRESH.getCmd());
        helpBtn.setActionCommand(EnActionEvent.SQLMAINTAIN_HELP.getCmd());
        delBtn.addActionListener(control);
        saveBtn.addActionListener(control);
        refreshBtn.addActionListener(control);
        helpBtn.addActionListener(control);
        northPanel.add(delBtn);
        northPanel.add(saveBtn);
        northPanel.add(refreshBtn);
       // northPanel.add(helpBtn);


        //中间区域
        JPanel centerPanel=new JPanel();
        //中间sql区域
        JPanel centerSqlHead=new JPanel();
        centerSqlHead.setLayout(new FlowLayout(FlowLayout.LEFT));
        centerSqlHead.add(new JLabel("SQL语句："));
        centerSqlHead.add(new JLabel("默认排序字段："));
        orderField.setFont(FontFactory.getTxtInputFootFont());
        centerSqlHead.add(orderField);
        centerSqlHead.add(new JLabel("SQL类型："));
        centerSqlHead.add(sqlType);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(centerSqlHead,BorderLayout.NORTH);
        //设置边框
        sqlText.setFont(FontFactory.getSqlInputFootFont());

        JScrollPane sqlTextScrollPane = new JScrollPane(sqlText);
        sqlTextScrollPane.setBorder(new LineBorder(ColorFactory.getContentNorthBorerColor(),1));
        centerPanel.add(sqlTextScrollPane,BorderLayout.CENTER);


        if(enableTestSql){
            //底部测区域
            JPanel testPanel=new JPanel();
            //底部测试区域按钮
            JPanel testPanelHead=new JPanel();
            testPanelHead.setLayout(new FlowLayout(FlowLayout.LEFT));
            sqlTestBtn.setActionCommand(EnActionEvent.SQLMAINTAIN_SQLTEST.getCmd());
            sqlPagingBtn.setActionCommand(EnActionEvent.SQLMAINTAIN_SQLPAGING.getCmd());
            sqlTestBtn.addActionListener(control);
            sqlPagingBtn.addActionListener(control);
            testPanelHead.add(sqlTestBtn);
            testPanelHead.add(sqlPagingBtn);
            sqlTestResultTable.setPreferredScrollableViewportSize(new Dimension(100,100));
            sqlTestResultTable.setFont(FontFactory.getJTableFont());
            JScrollPane scrollPane = new JScrollPane(sqlTestResultTable);
            testPanel.setLayout(new BorderLayout());
            testPanel.add(testPanelHead,BorderLayout.NORTH);
            testPanel.add(scrollPane,BorderLayout.CENTER);
            this.add(testPanel,BorderLayout.SOUTH);
        }
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
    }

    class SqlMaintainControl implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("SQL维护："+e.getActionCommand());
            if(e.getActionCommand().equals(EnActionEvent.SQLMAINTAIN_DEL.getCmd())){
                clean();
            }else if(e.getActionCommand().equals(EnActionEvent.SQLMAINTAIN_SAVE.getCmd())){
                save();
            }else if(e.getActionCommand().equals(EnActionEvent.SQLMAINTAIN_REFRESH.getCmd())){
                reSetSqlMaintainData();
            }else if(e.getActionCommand().equals(EnActionEvent.SQLMAINTAIN_HELP.getCmd())){

            }else if(e.getActionCommand().equals(EnActionEvent.SQLMAINTAIN_SQLTEST.getCmd())){

            }else if(e.getActionCommand().equals(EnActionEvent.SQLMAINTAIN_SQLPAGING.getCmd())){

            }


        }
    }

    /**
     * 清空显示
     */
    private void clean(){
        tsvcSql_new=null;
        sqlText.setText("");
        orderField.setText("");
    }
    /**
     * 保存数据
     */
    private void save(){
        if (tsvcSql_old != null) {
            try {
                tsvcSql_new = (TsvcSql) tsvcSql_old.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        } else {
            tsvcSql_new = new TsvcSql();
            tsvcSql_new.setC_functionno(centerContentPanel.getUcNo());
        }
        tsvcSql_new.setC_orderby(orderField.getText());
        tsvcSql_new.setC_sqlstatement(sqlText.getText());
        tsvcSql_new.setC_sqltype(sqlType.getSelectItemValue());
        if (!tsvcSql_new.equals(tsvcSql_old)) {
            tsvcSqlService.operate(tsvcSql_new);
        }
        //保存完后，刷新一遍
        reSetSqlMaintainData();
        tsvcSql_new=null;
    }
    /**
     * 重新设置界面显示
     */
    private void reSetSqlMaintainData(){
        clean();
        isDataChange=false;
        tsvcSql_old=null;
        String ucNo=centerContentPanel.getUcNo();
        if(StringUtils.isNotNullAndNotEmpty(ucNo)){
            TsvcSql tsvcSql=tsvcSqlService.getTsvcSql(ucNo);
            tsvcSql_old=tsvcSql;
            tsvcSql_new=tsvcSql;
            if(tsvcSql!=null){
                sqlText.setText(tsvcSql.getC_sqlstatement());
                orderField.setText(tsvcSql.getC_orderby());
                sqlType.setSelectedItem(tsvcSql.getC_sqltype());
            }
        }
    }

    /**
     * 检测是否变动
     * @return
     */
    public boolean isDataChange() {
        String sql=sqlText.getText();
        String order=orderField.getText();
        String type=sqlType.getSelectItemValue();
        if(tsvcSql_old==null){
            if(StringUtils.isNotNullAndNotEmpty(sql) ||StringUtils.isNotNullAndNotEmpty(order)
                    ||StringUtils.isNotNullAndNotEmpty(type)){
                return true;
            }
        }else{
            if(!sql.equals(tsvcSql_old.getC_sqlstatement()) || !order.equals(tsvcSql_old.getC_orderby())
                    || !type.equals(tsvcSql_old.getC_sqltype())){
                return true;
            }
        }
        return false;
    }


    @Override
    public void onFocus(boolean refresh) {
       if(refresh){
           String ucNo=centerContentPanel.getUcNo();
           reSetSqlMaintainData();
          /* if(  tsvcSql_old!=null && tsvcSql_old.getC_functionno().equals(ucNo)){
               System.out.println("不更新sql");
           }else{
               reSetSqlMaintainData();
           }*/

       }
    }
}
