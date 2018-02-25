package control;

import bean.SystemData;
import constant.ENWarningLevel;
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
        //EnActionEvent enActionEvent=e.getActionCommand();
        if(e.getActionCommand().equals(EnActionEvent.NEWTABCLICK.getCmd())){
            mainFrame.addTab("");
        }else if(e.getActionCommand().equals(EnActionEvent.CLOSEALLTABCLICK.getCmd())){
            mainFrame.closeAllTab();
        }else if(e.getActionCommand().equals(EnActionEvent.EXCEPTION.getCmd())){
            EnActionEvent enActionEvent=(EnActionEvent)e.getSource();
            mainFrame.showMsg("错误",enActionEvent.getMsg(), ENWarningLevel.ERROR);
        }else if(e.getActionCommand().equals(EnActionEvent.INFO.getCmd())){
            EnActionEvent enActionEvent=(EnActionEvent)e.getSource();
            mainFrame.showMsg("消息",enActionEvent.getMsg(), ENWarningLevel.INFO);
        }else if(e.getActionCommand().equals(EnActionEvent.WARNING.getCmd())){
            EnActionEvent enActionEvent=(EnActionEvent)e.getSource();
            mainFrame.showMsg("警告",enActionEvent.getMsg(), ENWarningLevel.WARNING);
        }

    }

    public void actionPerformedFromService(ENWarningLevel warningLevel,ActionEvent e) {
        EnActionEvent enActionEvent=(EnActionEvent)e.getSource();
        if(enActionEvent==EnActionEvent.SYSTEM_SUCSTART){
            String jdbcurl=SystemData.getDataConnInfo().getJdbcurl();
            jdbcurl=jdbcurl.substring(jdbcurl.indexOf("@"),jdbcurl.length());
            mainFrame.changeFootMsg(SystemData.getDataConnInfo().getUsername()+"/"+
                    SystemData.getDataConnInfo().getPaswword()+"/"+jdbcurl,"连接成功","");
        }else if(enActionEvent==EnActionEvent.SYSTEM_FAILSTART) {
            mainFrame.showMsg("警告",enActionEvent.getMsg(), ENWarningLevel.WARNING);
        }else{
            mainFrame.showMsg("警告",enActionEvent.getMsg(), ENWarningLevel.WARNING);
        }
    }
    public MyActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * 新ActionEvent，source也来自enActionEvent
     * @param enActionEvent
     * @return
     */
    public static ActionEvent getActionEvent(EnActionEvent enActionEvent){
        ActionEvent actionEvent=new ActionEvent(enActionEvent,0,enActionEvent.getCmd());
        return actionEvent;
    }
    public static ActionEvent getActionEvent(EnActionEvent enActionEvent,String msg){
        enActionEvent.setMsg(msg);
        return getActionEvent( enActionEvent);
    }
}
