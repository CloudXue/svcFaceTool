package view.centercontent.ucin;

import control.MyActionListener;
import service.SvcService;
import service.impl.SvcServiceImpl;
import view.centercontent.BaseJPanel;
import view.centercontent.CenterContentPanel;
import view.centercontent.UcInMaintain;
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
public class UcInCenterTable extends BaseJPanel {
    private SvcService svcService = new SvcServiceImpl();
    private CenterContentPanel centerContentPanel;
    UcInMaintain ucInMaintain;
    private JTable table;
    DefaultTableModel tableModel = null;
    DefaultListSelectionModel model;

    public UcInCenterTable(MyActionListener myActionListener, UcInMaintain ucInMaintain, CenterContentPanel centerContentPanel) {
        this.myActionListener = myActionListener;
        this.ucInMaintain = ucInMaintain;
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
                        cloumSelect(index);
                    }
                }
            }
        });
    }

    private Vector<String> getTitle() {
        Vector<String> title = new Vector<String>();
        title.add("输入/输出");
        title.add("公/私包");
        title.add("SQL字段");
        title.add("字段描述");
        title.add("程序属性");
        title.add("长度");
        title.add("小数位");
        title.add("数据库类型");
        title.add("非空");
        title.add("字段类别");
        title.add("条件");
        title.add("序号");
        title.add("输入显示级别");
        title.add("输入类型");
        title.add("字典名称");
        title.add("辅助查询");
        title.add("视图初始条件");
        title.add("视图字段初始条件值");
        title.add("exist语句");
        title.add("rowid");
        return title;
    }

    public void reloadUc(String uc) {
        try {
            removeAll();
            tableModel.setDataVector(svcService.getUcIn(uc), getTitle());
            for (int i = 0; i < table.getColumnCount(); i++) {
                if (i == 2 || i == 3 || i == 4) {
                    table.getColumnModel().getColumn(i).setPreferredWidth(120);
                }else{
                    //table.getColumnModel().getColumn(i).setPreferredWidth(75);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TableColumnModel tcm = table.getColumnModel();
        //其实没有移除，仅仅隐藏显示而已,
         tcm.removeColumn(tcm.getColumn(19));
         tcm.removeColumn(tcm.getColumn(18));
         tcm.removeColumn(tcm.getColumn(1));

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

}
