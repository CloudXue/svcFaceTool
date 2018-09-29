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
public class DaoClassAdapter  extends ClassInfoAdapter implements IClassToFile {
    public DaoClassAdapter(ClassInfo classInfo) {
        super(classInfo);
    }
}
