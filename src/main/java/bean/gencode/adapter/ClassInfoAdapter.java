package bean.gencode.adapter;

import bean.gencode.ClassInfo;
import bean.gencode.IClassToFile;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class ClassInfoAdapter implements IClassToFile {
    ClassInfo classInfo;

    public ClassInfoAdapter(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    @Override
    public String toFileString() {
        return null;
    }


    private void prepareToString(){

    }

    private void init(){

    }

    private String generateHead(){
        return "";
    }

    private String generateClass(){
        return "";
    }

    private String generateField(){
        return "";
    }

    private String generateMethod(){
        return "";
    }

    protected   void initExtra(){

    }
}
