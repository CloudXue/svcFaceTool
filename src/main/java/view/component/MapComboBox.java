package view.component;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import static oracle.jdbc.driver.DatabaseError.test;

/**
 * 功能说明: 联合ComboBoxMapModel使用<br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-05<br>
 * <br>
 */
public class MapComboBox extends JComboBox<String> {
    public MapComboBox() {
    }

    public MapComboBox(ComboBoxModel<String> aModel) {
        super(aModel);
        setUI(new MyComboBoxUI());
        ((MyComboBoxUI)getUI()).tips();
    }

    public void setSelectedItem(Object anObject) {
        dataModel.setSelectedItem(anObject);
        super.setSelectedItem(anObject);
    }
    class MyComboBoxUI extends BasicComboBoxUI {
        public MyComboBoxUI() {
            super();
        }

        public void tips() {
            if (listBox != null) {
                listBox.addMouseMotionListener(new MouseMotionListener() {
                    Component oldCom;
                    Component curCom;

                    public void mouseMoved(MouseEvent e) {
                        curCom = listBox.getCellRenderer()
                                .getListCellRendererComponent(listBox, null, 0,
                                        true, true);
                        if (oldCom == null || oldCom != curCom) {
                            oldCom = curCom;
                        }
                        if (oldCom instanceof JComponent) {
                            ((JComponent) oldCom)
                                    .setToolTipText(""+listBox.getSelectedValue());
                        }
                    }

                    public void mouseDragged(MouseEvent e) {
                    }
                });
            }
        }
    }
}
