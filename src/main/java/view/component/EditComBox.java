package view.component;

import constant.EnActionEvent;
import util.StringUtils;
import view.component.ui.MyComboBoxUI;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
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
        setEditable(true);
        setUI(new MyComboBoxUI());
        ((MyComboBoxUI)getUI()).tips();
        //JTextComponent editor = (JTextComponent) getEditor().getEditorComponent();
        //editor.setDocument(new FixedAutoSelection(getModel()));
        addActionListener(this);
        getEditor().getEditorComponent().addKeyListener(this);
        setFocusable(true);
        /*this.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println("focusGained");
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("focusLost");
                getModel().setSelectedItem(getEditor().getItem().toString());
            }
        });*/

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
        }

        private Object lookupItem(String pattern) {
            // iterate over all items
            for (int i=0, n=model.getSize(); i < n; i++) {
                Object currentItem = model.getElementAt(i);
                // current item starts with the pattern?
                if (currentItem.toString().toUpperCase().startsWith(pattern.toUpperCase())) {
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar()==e.VK_ENTER){
            getModel().setSelectedItem(getEditor().getItem().toString());
        }
        //getModel().setSelectedItem(getEditor().getItem().toString());
    }
}
