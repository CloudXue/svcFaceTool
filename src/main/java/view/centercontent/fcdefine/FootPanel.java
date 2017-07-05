package view.centercontent.fcdefine;

import bean.HsiRight;
import util.StringUtils;
import view.centercontent.BaseJPanel;
import view.centercontent.UcDefineMaintain;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lyd on 2017/5/16.
 *  * 功能表定义维护-单个uc定义  hsi_right
 */
public class FootPanel  extends BaseJPanel{
    private UcDefineMaintain ucDefineMaintain;
    //暂存当前。如果有更改依据currentUc去更改数据
    private String currentUc="";
    private HsiRight newUcData;
    private HsiRight oldUcData;
    private String opttype="";//mod、add
    private static  String OPTTYPE_MOD="mod";
    private static  String OPTTYPE_ADD="add";
    private FootPanelDocumentListener textFiledListener;

    private ActionListener actionListener;
    /**
     * 功能编号
     */
    private JLabel rightcodelabel=new JLabel("功能编号：");
    private JTextField rightcodetextfield=new JTextField();
    /**
     * UC功能号
     */
    private JLabel functionnolabel=new JLabel("UC功能号：");
    private JTextField functionnotextfield=new JTextField();

    /**
     *权限名称
     */
    private JLabel rightnamelabel=new JLabel("权限名称：");
    private JTextField rightnametextfield=new JTextField();
    /**
     * 权限类别
     */
    private JLabel classlabel=new JLabel("权限类别：");
    private JComboBox classcombobox=new JComboBox(new String[]{"0:操作权限","2:公共权限","1:其他权限"});
    /**
     *BO类名
     */
    private JLabel javaclasslabel=new JLabel("BO类名：");
    private JTextField javaclasstextfield=new JTextField("com.hundsun.fund.crm.app.pub.bo.CommonQueryBO");
    /**
     *BO方法
     */
    private JLabel javamethodlabel=new JLabel("BO方法：");
    private JTextField javamethodtextfield=new JTextField("execute");
    /**
     *功能类型
     */
    private JLabel uctypelabel=new JLabel("功能类型：");
    private JComboBox uctypecombobox=new JComboBox(
            new String[]{"1:查询","2:修改","3:删除","4:增加","5:相关主题","6:其他"});
    /**
     *主表名称
     */
    private JLabel tablenamelabel=new JLabel("主表名称：");
    private JTextField tablenametextfield=new JTextField();
    /**
     *日终处理时停用
     */
    private JLabel islimitlabel=new JLabel("日终处理时停用：");
    private JComboBox islimitcombobox=new JComboBox(new String[]{"0:否","1:是"});




    public FootPanel(ActionListener actionListener,UcDefineMaintain ucDefineMaintain) {
        this.actionListener=actionListener;
        this.ucDefineMaintain=ucDefineMaintain;
        init();
    }

    private void init(){

        //region 布局管理
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        //创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().addComponent(rightcodelabel)
                .addComponent(classlabel)
                .addComponent(uctypelabel));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(rightcodetextfield)
                .addComponent(classcombobox)
                .addComponent(uctypecombobox));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(functionnolabel)
                .addComponent(javaclasslabel)
                .addComponent(tablenamelabel));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(functionnotextfield)
                .addComponent(javaclasstextfield)
                .addComponent(tablenametextfield));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(rightnamelabel)
                .addComponent(javamethodlabel)
                .addComponent(islimitlabel));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(rightnametextfield)
                .addComponent(javamethodtextfield)
                .addComponent(islimitcombobox));
        hGroup.addGap(5);
        layout.setHorizontalGroup(hGroup);
        //创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(rightcodelabel).addComponent(rightcodetextfield).addComponent(functionnolabel)
                .addComponent(functionnotextfield).addComponent(rightnamelabel).addComponent(rightnametextfield));
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(classlabel).addComponent(classcombobox).addComponent(javaclasslabel)
                .addComponent(javaclasstextfield).addComponent(javamethodlabel).addComponent(javamethodtextfield));
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(uctypelabel).addComponent(uctypecombobox).addComponent(tablenamelabel)
                .addComponent(tablenametextfield).addComponent(islimitlabel).addComponent(islimitcombobox));
        vGroup.addGap(10);
        //设置垂直组
        layout.setVerticalGroup(vGroup);
        //endregion

        rightcodetextfield.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.print("insertUpdate---");
                System.out.println(rightcodetextfield.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.print("removeUpdate---");
                System.out.println(rightcodetextfield.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.print("changedUpdate---");
                System.out.println(rightcodetextfield.getText());
            }
        });
    }
    public HsiRight getFootPanelData(){
        HsiRight hsiRight= (HsiRight) oldUcData.clone();
        hsiRight.setC_rightcode(rightcodetextfield.getText());
        hsiRight.setC_rightname(rightnametextfield.getText());
        String classcomboboxstr=(String)classcombobox.getSelectedItem();
        hsiRight.setC_class(classcomboboxstr.split(":")[0]);
        hsiRight.setC_functionno(functionnotextfield.getText());
        hsiRight.setC_javaclass(javaclasstextfield.getText());
        hsiRight.setC_javamethod(javamethodtextfield.getText());
        hsiRight.setC_tablename(tablenametextfield.getText());
        String uctypecomboboxstr=(String)uctypecombobox.getSelectedItem();
        hsiRight.setC_uctype(uctypecomboboxstr.split(":")[0]);
        String islimitcomboboxstr=(String)islimitcombobox.getSelectedItem();
        hsiRight.setC_islimit(islimitcomboboxstr.split(":")[0]);
        return hsiRight;
    }
    public void setFootPanelData(HsiRight hsiRight){
        if(hsiRight==null){
            //清除暂存
            oldUcData=null;
            currentUc="";
            newUcData=null;
            //清除文本框
            rightcodetextfield.setText("");
            rightnametextfield.setText("");
            functionnotextfield.setText("");
            javaclasstextfield.setText("");
            javamethodtextfield.setText("");
            tablenametextfield.setText("");
            classcombobox.setSelectedIndex(0);
            uctypecombobox.setSelectedIndex(0);
            islimitcombobox.setSelectedIndex(0);
        }else{
            //保存暂存
            oldUcData=hsiRight;
            currentUc=hsiRight.getC_functionno_hid();
            newUcData=(HsiRight)hsiRight.clone();
            rightcodetextfield.setText(hsiRight.getC_rightcode());
            rightnametextfield.setText(hsiRight.getC_rightname());
            Integer classindex= 0;
            if (StringUtils.isNotNullAndNotEmpty(hsiRight.getC_class())) {
                classindex = Integer.parseInt(hsiRight.getC_class());
            }
            if(classindex==2){
                classindex=1;
            }else if(classindex==1){
                classindex=2;
            }
            classcombobox.setSelectedIndex(classindex);
            functionnotextfield.setText(hsiRight.getC_functionno());
            javaclasstextfield.setText(hsiRight.getC_javaclass());
            javamethodtextfield.setText(hsiRight.getC_javamethod());
            tablenametextfield.setText(hsiRight.getC_tablename());
            Integer uctype= 0;
            if (StringUtils.isNotNullAndNotEmpty(hsiRight.getC_uctype())) {
                uctype = Integer.parseInt(hsiRight.getC_uctype())-1;
            }
            uctypecombobox.setSelectedIndex(uctype);

            Integer islimit= 0;
            if (StringUtils.isNotNullAndNotEmpty(hsiRight.getC_islimit())) {
                islimit = Integer.parseInt(hsiRight.getC_islimit());
            }
            islimitcombobox.setSelectedIndex(islimit);
        }

    }


    private void ucDataChange(){
        //先判是否真的修改
        newUcData=this.getFootPanelData();
        if(!newUcData.equals(oldUcData)){
           //有过修改，则触发
            if(opttype.equals(OPTTYPE_ADD)){
                ucDefineMaintain.addAddUcMap(currentUc,newUcData);
            }else if(opttype.equals(OPTTYPE_MOD)){
                ucDefineMaintain.addEditUcMap(currentUc,newUcData);
            }
        }
    }
    class FootPanelControl implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }

    class FootPanelDocumentListener implements DocumentListener{
        @Override
        public void insertUpdate(DocumentEvent e) {
            ucDataChange();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            ucDataChange();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            ucDataChange();
        }
    }
}
