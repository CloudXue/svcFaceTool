package view.centercontent.fcdefine;

import control.MyActionListener;
import view.BaseJPanel;

import java.awt.event.ActionListener;

/**
 * Created by lyd on 2017/5/16.
 */
public class FootPanel  extends BaseJPanel{
    private ActionListener actionListener;
    public FootPanel(ActionListener actionListener) {
        this.actionListener=actionListener;
    }


}
