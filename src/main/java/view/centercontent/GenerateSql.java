package view.centercontent;

import view.BaseJPanel;
import control.MyActionListener;

import javax.swing.*;

/**
 * Created by lyd on 2017/5/11.
 */
public class GenerateSql  extends BaseJPanel {
    public GenerateSql(MyActionListener myActionListener) {
        this.myActionListener= myActionListener;
        init();
    }
    private void init(){
        JLabel jLabel=new JLabel("GenerateSql");
        this.add(jLabel);

    }
}
