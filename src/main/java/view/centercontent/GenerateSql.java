package view.centercontent;

import control.MyActionListener;

import javax.swing.*;

/**
 * Created by lyd on 2017/5/11.
 */
public class GenerateSql  extends BaseJPanel {
    private   CenterContentPanel centerContentPanel;
    public GenerateSql(MyActionListener myActionListener,CenterContentPanel centerContentPanel) {
        this.centerContentPanel=centerContentPanel;
        this.myActionListener= myActionListener;
        init();
    }
    private void init(){
        JLabel jLabel=new JLabel("GenerateSql");
        this.add(jLabel);

    }
}
