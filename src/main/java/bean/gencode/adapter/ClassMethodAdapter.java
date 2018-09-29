package bean.gencode.adapter;

import bean.gencode.ClassMethod;
import bean.gencode.IClassToFile;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class ClassMethodAdapter  implements IClassToFile {
    ClassMethod classMethod;

    public ClassMethodAdapter(ClassMethod classMethod) {
        this.classMethod = classMethod;
    }

    @Override
    public String toFileString() {
        return null;
    }
}
