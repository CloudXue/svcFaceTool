package view.centercontent.fcdefine;

import control.MyActionListener;
import view.BaseJPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by lyd on 2017/5/18.
 */
public class CenterTable  extends BaseJPanel {
    private ActionListener actionListener;
    private JTable table;
    private String[] title=new String[]{"权限代码","UC功能号","权限名称","分类","BO类名","BO方法"};
    public CenterTable(MyActionListener myActionListener) {
        this.myActionListener=myActionListener;
        init();
    }
    private void init(){
        DefaultTableModel tableModel=new DefaultTableModel(getData(),title);
        table=new JTable(tableModel);
        this.setLayout(new GridLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
       // this.add(table);
    }

    public void removeAll(){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        for (int i = tableModel.getDataVector().size()-1; i >=0; i--) {
            tableModel.removeRow(i);// rowIndex是要删除的行序号
        }

    }
    public void getSelectRow(){

    }
    public void removeSelect(){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        int[] rows=table.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
            tableModel.removeRow(rows[i]);
        }

    }
    public void tailInsert(){

    }


    private Object[][] getData(){
        Object[][] data={
                {"a06c262","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c263","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c264","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c265","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"},
                {"a06c266","UC_SE_CHEQUEPAYATT_DELETE","支票付款附件删除","操作权限","com.hundsun.fund.crm.app.pub.bo.CommonQueryBO","execute"}
        };

        return data;
    }


}
