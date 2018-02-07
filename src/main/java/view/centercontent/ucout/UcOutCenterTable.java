package view.centercontent.ucout;

import bean.TsvcViewconfig;
import control.MyActionListener;
import service.SvcService;
import service.impl.SvcServiceImpl;
import util.StringUtils;
import util.SvcUtil;
import view.centercontent.BaseJPanel;
import view.centercontent.CenterContentPanel;
import view.centercontent.UcOutMaintain;
import view.component.ComboBoxMapModel;
import view.component.MapComboBox;
import view.component.SvcTableCellEditor;
import view.factory.FontFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-02<br>
 * <br>
 */
public class UcOutCenterTable extends BaseJPanel {
    private SvcService svcService = new SvcServiceImpl();
    private CenterContentPanel centerContentPanel;
    UcOutMaintain ucOutMaintain;
    private JTable table;
    DefaultTableModel tableModel = null;
    DefaultListSelectionModel model;

    private int currentSelIndex=0;

    @Override
    public void close() {
        svcService=null;
        //ucOutMaintain=null;
        tableModel=null;
        model=null;
        table=null;

    }

    public UcOutCenterTable(MyActionListener myActionListener, UcOutMaintain ucOutMaintain, CenterContentPanel centerContentPanel) {
        this.myActionListener = myActionListener;
        this.ucOutMaintain = ucOutMaintain;
        this.centerContentPanel = centerContentPanel;
        init();
    }

    private void init() {
        tableModel = new DefaultTableModel(null, getTitle());
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(FontFactory.getJTableFont());
        //表头不可拖动
        table.getTableHeader().setReorderingAllowed(false);


        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model = (DefaultListSelectionModel) table.getSelectionModel();
        this.setLayout(new GridLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int sr;
                if ((sr = table.getSelectedRow()) == -1) {
                    return;
                }
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
                    int index = getClickedRow(e.getY());
                    if (index >= 0) {
                        currentSelIndex=index;
                        cloumSelect(index);
                    }
                }
            }
        });
    }

    private Vector<String> getTitle() {
        Vector<String> title = new Vector<String>();
        title.add("功能分类");
        title.add("程序属性");
        title.add("显示级别");
        title.add("显示字段类型");
        title.add("字典名称");
        title.add("显示标题名称");
        title.add("序号");
        title.add("地址");
        title.add("链接处理");
        title.add("宽度");
        title.add("格式");
        title.add("相关隐藏框");
        title.add("修改级别");
        title.add("默认值");
        title.add("输入限制");
        title.add("弹出搜索框类型");
        title.add("事件");
        title.add("输入类型");
        title.add("rowid");
        return title;
    }

    public void reloadUc(String uc) {
        TableColumnModel tcm = table.getColumnModel();
        try {
            removeAll();
            tableModel.setDataVector(svcService.getUcOut(uc), getTitle());
            //其实没有移除，仅仅隐藏显示而已,
            tcm.removeColumn(tcm.getColumn(18));
            tcm.removeColumn(tcm.getColumn(0));
            for (int i = 0; i < table.getColumnCount(); i++) {
                if (i ==0 || i == 3 || i == 4) {
                    table.getColumnModel().getColumn(i).setPreferredWidth(120);
                }else{
                    //table.getColumnModel().getColumn(i).setPreferredWidth(75);
                }
            }
            //选择第一行
            cloumSelect(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //显示类型
        ComboBoxMapModel mapModel=new ComboBoxMapModel(SvcUtil.getUcOutViewType());
        JComboBox comboBox=new MapComboBox(mapModel);
        SvcTableCellEditor cellEditor=new SvcTableCellEditor(comboBox);
        tcm.getColumn(2).setCellEditor( cellEditor );
        //字典
        mapModel=new ComboBoxMapModel(SvcUtil.getDiction());
        comboBox=new MapComboBox(mapModel);
        cellEditor=new SvcTableCellEditor(comboBox);
        tcm.getColumn(3).setCellEditor( cellEditor );
        //显示级别
        mapModel=new ComboBoxMapModel(SvcUtil.getUcOutViewLevel());
        comboBox=new MapComboBox(mapModel);
        cellEditor=new SvcTableCellEditor(comboBox);
        tcm.getColumn(1).setCellEditor( cellEditor );

    }
    public void removeAll() {
        for (int i = tableModel.getDataVector().size() - 1; i >= 0; i--) {
            tableModel.removeRow(i);// rowIndex是要删除的行序号
        }
    }

    /**
     * 获取鼠标点击的行号
     *
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

    /**
     * 选中某一行
     *
     * @param index
     */
    public void cloumSelect(int index) {
        //选中这一行
        model.setSelectionInterval(index, index);
        //滚动到这一行
        Rectangle rect = table.getCellRect(index, 0, true);
        table.scrollRectToVisible(rect);
        String functionno = "";
        if (index < tableModel.getRowCount()) {
            functionno = (String) tableModel.getValueAt(index, 6);
        }
        //ucDefineMaintain.tableSelect(index,functionno);
    }
    public java.util.List<TsvcViewconfig> getAllColumnDatas(){
        java.util.List<TsvcViewconfig> retList=new java.util.ArrayList<TsvcViewconfig>();
        if(tableModel.getRowCount()>0){
            Vector<Vector<String>> datas=(Vector<Vector<String>>)tableModel.getDataVector();
            for(Vector<String> data : datas){
                TsvcViewconfig tsvcViewconfig=new TsvcViewconfig(centerContentPanel.getUcNo(),
                        StringUtils.valueOf(data.get(0)),
                        StringUtils.valueOf(data.get(1)),
                        StringUtils.valueOf(data.get(2)),
                        StringUtils.valueOf(data.get(3)),
                        StringUtils.valueOf(data.get(4)),
                        StringUtils.valueOf(data.get(5)),
                        StringUtils.valueOf(data.get(6)),
                        StringUtils.valueOf(data.get(7)),
                        StringUtils.valueOf(data.get(8)),
                        StringUtils.valueOf(data.get(9)),
                        StringUtils.valueOf(data.get(10)),
                        StringUtils.valueOf(data.get(11)),
                        StringUtils.valueOf(data.get(12)),
                        StringUtils.valueOf(data.get(13)),
                        StringUtils.valueOf(data.get(14)),
                        StringUtils.valueOf(data.get(15)),
                        StringUtils.valueOf(data.get(16)),
                        StringUtils.valueOf(data.get(17))
                );
                retList.add(tsvcViewconfig);
            }
        }
        return retList;
    }
    public TsvcViewconfig insert(){
        TsvcViewconfig tsvcViewconfig=TsvcViewconfig.generateDefault();
        tsvcViewconfig.setC_functionno(centerContentPanel.getUcNo());
        if(tableModel.getRowCount()>0){
            int endindex=(tableModel.getRowCount()-1);
            if(currentSelIndex>endindex){
                currentSelIndex=endindex;
            }
        }else{
            int endindex=tableModel.getRowCount();
            currentSelIndex=endindex;
            tsvcViewconfig.setL_no(10+"");
        }
        tableModel.insertRow(currentSelIndex,tsvcViewconfig.toVector());
        this.cloumSelect(currentSelIndex);
        return tsvcViewconfig;
    }
    /**
     * 尾加
     */
    public TsvcViewconfig tailInsert(){
        TsvcViewconfig tsvcViewconfig=TsvcViewconfig.generateDefault();
        tsvcViewconfig.setC_functionno(centerContentPanel.getUcNo());
        if(tableModel.getRowCount()>0){
            int index=(tableModel.getRowCount()-1);
            String l_noStr=StringUtils.valueOf(tableModel.getValueAt(index,6));
            if(StringUtils.isNotNullAndNotEmpty(l_noStr)){
                Integer l_no=Integer.parseInt(l_noStr);
                if(l_no!=null){
                    tsvcViewconfig.setL_no((l_no+10)+"");
                }
            }
            tableModel.addRow(tsvcViewconfig.toVector());
            index=(tableModel.getRowCount()-1);
            this.cloumSelect(index);
        }else{
            //无数据
            tsvcViewconfig.setL_no(10+"");
            tableModel.addRow(tsvcViewconfig.toVector());
            int index=(tableModel.getRowCount()-1);
            this.cloumSelect(index);
        }


        return tsvcViewconfig;
    }
    public void removeSelect(){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        int[] rows=table.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
            tableModel.removeRow(rows[i]-i);
        }
        if(tableModel.getRowCount()>0){
            int endindex=(tableModel.getRowCount()-1);
            if(rows[0]>endindex){
                this.cloumSelect(endindex);
            }else{
                this.cloumSelect(rows[0]);
            }
        }else{
            //全部删除完
            currentSelIndex=0;
        }

    }
}
