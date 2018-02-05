package view.component;

import javax.swing.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-02-05<br>
 * <br>
 */
public class ComboBoxMapModel  extends AbstractListModel<String> implements ComboBoxModel<String>, Serializable {
    Vector<String> key;
    Map<String,String> objects;
    Object selectedObject;

    public ComboBoxMapModel(Map<String, String> map) {
        objects = new LinkedHashMap<String,String>();
        key=new Vector<String>();
        int i,c;
        for ( Map.Entry<String,String> entry : map.entrySet()){
            key.add(entry.getKey());
            objects.put(entry.getKey(),entry.getValue());
        }
        if ( getSize() > 0 ) {
            selectedObject = objects.get(getElementAt( 0 ));
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if(objects.containsKey(anItem.toString())){
            selectedObject=objects.get(anItem.toString());
        }else{
            for(Map.Entry<String,String> entry : objects.entrySet()){
                if(anItem.toString().equals(entry.getValue())){
                    selectedObject=entry.getValue();
                    break;
                }
            }
        }

    }

    @Override
    public Object getSelectedItem() {
        return selectedObject;
    }

    @Override
    public int getSize() {
        return objects.size();
    }

    @Override
    public String getElementAt(int index) {
        return key.get(index);
    }
}
