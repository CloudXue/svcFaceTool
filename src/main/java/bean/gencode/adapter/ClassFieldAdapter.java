package bean.gencode.adapter;

import bean.gencode.ClassField;
import bean.gencode.IClassToFile;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class ClassFieldAdapter implements IClassToFile {
    ClassField classField;

    public ClassFieldAdapter(ClassField classField) {
        this.classField = classField;
    }

    @Override
    public String toFileString() {
        return null;
    }



    private void prepareToString(){

    }



}
