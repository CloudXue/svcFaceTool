package view.centercontent;

import constant.EnActionEvent;
import control.MyActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by lyd on 2017/5/11.
 */
public abstract class BaseJPanel extends JPanel {

    protected MyActionListener myActionListener;


    protected void handleExceptionMsg(Exception e){
        ActionEvent action=MyActionListener.getActionEvent(EnActionEvent.EXCEPTION,e.getMessage());
        myActionListener.actionPerformed(action);
        e.printStackTrace();
    }
}
