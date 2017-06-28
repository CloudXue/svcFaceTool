package control;

import constant.EnActionEvent;
import view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lyd on 2017/1/4.
 */
public class MyActionListener implements ActionListener {
    private MainFrame mainFrame;
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(EnActionEvent.NEWTABCLICK.getCmd())){
            mainFrame.addTab("");
        }else if(e.getActionCommand().equals(EnActionEvent.CLOSEALLTABCLICK.getCmd())){
            mainFrame.closeAllTab();
        }
    }

    public MyActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}
