package view.component;

import constant.EnActionEvent;
import util.StringUtils;
import view.component.ui.MyComboBoxUI;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明: 可以编辑的下拉框<br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-01<br>
 * <br>
 */
public class EditComBox extends JComboBox   implements ActionListener,KeyListener {
    List<ActionListener> dataChangeListener=new ArrayList<ActionListener>();
    private  String previousValue=null;
    private List<FocusListener> focusListenerList;
    private boolean firingActionEvent = false;
    public EditComBox() {
        super();
        init();
    }

    public EditComBox(ComboBoxModel aModel) {
        super(aModel);
        init();
    }

    public EditComBox(Object[] items) {
        super(items);
        init();
    }
    private void init(){
        focusListenerList=new ArrayList<>();
        setEditable(true);
        setUI(new MyComboBoxUI());
        ((MyComboBoxUI)getUI()).tips();
        JTextComponent editor = (JTextComponent) getEditor().getEditorComponent();
        editor.setDocument(new FixedAutoSelection(getModel()));
        addActionListener(this);
        //添加按键监听，当按键释放则对控件赋值，这个值也会赋值到table里
        getEditor().getEditorComponent().addKeyListener(this);


        ComBoxFocusListener listener=new ComBoxFocusListener();
        getEditor().getEditorComponent().addFocusListener(listener);
        //this.addFocusListener(listener);
        //setFocusable(true);
    }
    class FixedAutoSelection  extends PlainDocument{
        ComboBoxModel model;
        // flag to indicate if setSelectedItem has been called
        // subsequent calls to remove/insertString should be ignored
        boolean selecting=false;
        public FixedAutoSelection(ComboBoxModel model) {
            this.model = model;
        }
        public void remove(int offs, int len) throws BadLocationException {
            // return immediately when selecting an item
            if (selecting) return;
            super.remove(offs, len);
        }

        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            // return immediately when selecting an item
            if (selecting) return;
            //System.out.println("insert " + str + " at " + offs);
            // insert the string into the document
            super.insertString(offs, str, a);
            // get the resulting string
            String content = getText(0, getLength());
            // lookup a matching item
            Object item = lookupItem(content);
            // select the item (or deselect if null)
            //if(item!=model.getSelectedItem()) System.out.println("Selecting '" + item + "'");
            selecting = true;
            model.setSelectedItem(item);
            selecting = false;
           /* if(item!=null && StringUtils.isNotNullAndNotEmpty(item.toString())){
               if( getEditor().getItem().toString().equals(item)){
                   selecting = false;
               }
            }else{
                selecting = false;
            }*/
        }

        private Object lookupItem(String pattern) {
            // iterate over all items
            for (int i=0, n=model.getSize(); i < n; i++) {
                Object currentItem = model.getElementAt(i);
                // current item starts with the pattern?
                if (currentItem.toString().toUpperCase().contains(pattern.toUpperCase())) {
                    return currentItem;
                }
            }
            // no item starts with the pattern => return null
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(previousValue!=null){
            if(!previousValue.equals(StringUtils.valueOf(getSelectedItem()))) {
                previousValue = StringUtils.valueOf(getSelectedItem());
                ActionEvent dataChange = new ActionEvent(this, ActionEvent.ACTION_FIRST, EnActionEvent.COMBOBOXDATACHANGE.getCmd());
                for (ActionListener listener : dataChangeListener) {
                    listener.actionPerformed(dataChange);
                }
            }
        }else{
            previousValue=StringUtils.valueOf(getSelectedItem());
        }
    }

    public void addDataChangeActionListener(ActionListener l) {
        dataChangeListener.add(l);
    }
    protected void fireActionEvent(){
        fireActionEvent(false);
    }
    protected void fireActionEvent(boolean stopCellEditing){
        if (!firingActionEvent) {
            // Set flag to ensure that an infinite loop is not created
            firingActionEvent = true;
            ActionEvent e = null;
            // Guaranteed to return a non-null array
            Object[] listeners = listenerList.getListenerList();
            long mostRecentEventTime = EventQueue.getMostRecentEventTime();
            int modifiers = 0;
            AWTEvent currentEvent = EventQueue.getCurrentEvent();
            if (currentEvent instanceof InputEvent) {
                modifiers = ((InputEvent)currentEvent).getModifiers();
            } else if (currentEvent instanceof ActionEvent) {
                modifiers = ((ActionEvent)currentEvent).getModifiers();
            }
            // Process the listeners last to first, notifying
            // those that are interested in this event
            for ( int i = listeners.length-2; i>=0; i-=2 ) {
                if ( listeners[i]==ActionListener.class ) {
                    //DefaultCellEditor里事件会关闭下拉框
                    if(!stopCellEditing){
                        if((listeners[i+1]).getClass().getName().contains(DefaultCellEditor.class.getName())){
                            continue;
                        }
                    }
                    // Lazily create the event:
                    e = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,
                            getActionCommand(),
                            mostRecentEventTime, modifiers);
                    ((ActionListener)listeners[i+1]).actionPerformed(e);
                }
            }
            firingActionEvent = false;
        }
    }
    class ComBoxFocusListener implements FocusListener{
        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {
            getModel().setSelectedItem(getEditor().getItem().toString());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        getModel().setSelectedItem(getEditor().getItem().toString());
        //enter，触发事件，结束编辑
        if(e.getKeyChar()==KeyEvent.VK_ENTER){
            fireActionEvent(true);
        }
    }
    public void setSelectedItem(Object anObject) {
        //回填选择的值
        if(anObject!=null || StringUtils.isNotNullAndNotEmpty(anObject.toString())){
            getEditor().setItem(anObject.toString());
        }
        super.setSelectedItem(anObject);
    }
}
