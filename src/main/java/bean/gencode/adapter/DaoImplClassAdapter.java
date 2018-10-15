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
public class DaoImplClassAdapter  extends ClassInfoAdapter implements IClassToFile {
    public DaoImplClassAdapter(ClassInfo classInfo) {
        super(classInfo);
    }

    @Override
    protected void initExtra() {

    }
}
