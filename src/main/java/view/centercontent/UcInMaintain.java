package view.centercontent;

import view.BaseJPanel;
import control.MyActionListener;

import javax.swing.*;

/**
 * Created by lyd on 2017/5/11.
 */
public class UcInMaintain extends BaseJPanel {
    public UcInMaintain(MyActionListener myActionListener) {
        this.myActionListener= myActionListener;
        init();
    }

    private void init(){
        JLabel jLabel=new JLabel("UcInMaintain");
        this.add(jLabel);

    }
}
