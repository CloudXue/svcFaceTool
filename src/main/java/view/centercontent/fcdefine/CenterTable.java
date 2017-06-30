package view.centercontent.fcdefine;

import bean.HsiRight;
import control.MyActionListener;
import service.UcDefineService;
import service.impl.UcDefineServiceImpl;
import view.centercontent.BaseJPanel;
import view.centercontent.UcDefineMaintain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by lyd on 2017/5/18.
 * 功能表定义维护-列表显示界面
 */
public class CenterTable  extends BaseJPanel {
    UcDefineService ucDefineService=new UcDefineServiceImpl();

    private UcDefineMaintain ucDefineMaintain;
    private JTable table;
    private String[] title=new String[]{"权限代码","UC功能号","权限名称","分类","BO类名","BO方法"};
    DefaultTableModel tableModel =null;



    public CenterTable(MyActionListener myActionListener,UcDefineMaintain ucDefineMaintain) {
        this.myActionListener=myActionListener;
        this.ucDefineMaintain=ucDefineMaintain;
        init();
    }

    private void init(){
        tableModel=new DefaultTableModel(null,getTitle()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table=new JTable(tableModel);

        this.setLayout(new GridLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int sr;
                if ((sr = table.getSelectedRow()) == -1) {
                    return;
                }
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
                    int index=getClickedRow(e.getY());
                    if(index>=0){
                        cloumSelect(index);
                    }
                }
            }
        });
        //异步加载数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                reloadUc("");
                //选择第一行
                cloumSelect(0);
            }
        }).start();
    }

    public void removeAll(){
        for (int i = tableModel.getDataVector().size()-1; i >=0; i--) {
            tableModel.removeRow(i);// rowIndex是要删除的行序号
        }

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

    private Vector<String> getTitle(){
        Vector<String> title=new Vector<String>();
        title.add("权限代码");
        title.add("UC功能号");
        title.add("权限名称");
        title.add("分类");
        title.add("BO类名");
        title.add("BO方法");
        return  title;
    }

    private Vector<Vector<String>> getData(String condition){
        List<HsiRight> hsiRightList=new ArrayList<HsiRight>();
        try {
            hsiRightList=ucDefineService.getAllUc(condition);
        } catch (Exception e) {
            handleExceptionMsg(e);
        }
        Vector<Vector<String>> dataVector=new Vector<Vector<String>>();
        for(HsiRight hsiRight : hsiRightList){
            Vector<String> vector=new Vector<>();
            vector.add(hsiRight.getC_rightcode());
            vector.add(hsiRight.getC_functionno());
            vector.add(hsiRight.getC_rightname());
            vector.add(hsiRight.getC_className());
            vector.add(hsiRight.getC_javaclass());
            vector.add(hsiRight.getC_javamethod());
            dataVector.add(vector);
        }

        return dataVector;

    }
    /**
     * 获取鼠标点击的行号
     * @param y 点击位置的纵坐标值
     * @return 行号
     */
    private int getClickedRow(int y) {
        // JTable的行总数
        int rowCount = table.getRowCount();
        // JTable行的累计纵坐标
        int rowY = 0;

        if (y < 0) {
            return -1;
        }
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            rowY = rowY + (int) table.getCellRect(rowIndex, 0, true).getHeight();
            if (y < rowY) {
                return rowIndex;
            }
        }

        return -1;
    }

    public void cloumSelect(int index){
        String functionno= "";
        if (index<tableModel.getRowCount()) {
            functionno = (String)tableModel.getValueAt(index,1);
        }
        ucDefineMaintain.tableSelect(index,functionno);
    }
    public void reloadUc(String condition){
        tableModel.setDataVector(getData(condition),getTitle());
    }

}
