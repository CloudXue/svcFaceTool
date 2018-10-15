package bean.gencode.adapter;

import bean.gencode.ClassField;
import bean.gencode.ClassInfo;
import bean.gencode.IClassToFile;
import util.StringUtils;

/**
 * 功能说明:
 * 系统版本:
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class DtoClassAdapter extends ClassInfoAdapter implements IClassToFile {
    public DtoClassAdapter(ClassInfo classInfo) {
        super(classInfo);
    }

    @Override
    protected void initExtra() {
        if (StringUtils.isNotNullAndNotEmpty(classInfo.getParentClass())) {
            referenceCls.add(classInfo.getParentClass());
        }
        if (classInfo.getField() != null) {
            for (ClassField field : classInfo.getField()) {
                if (!referenceCls.contains(field.getType())) {
                    referenceCls.add(field.getType());
                }
            }
        }
        if (classInfo.getInterfaceList() != null) {
            for (String interfacestr : classInfo.getInterfaceList()) {
                if (!referenceCls.contains(interfacestr)) {
                    referenceCls.add(interfacestr);
                }
            }
        }

        classInfo.setType(ClassInfo.TYPE_CLASS);
    }
}
