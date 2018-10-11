package util;

import bean.FileInfo;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明:
 * 系统版本: 2.4.2.0
 *
 * @author: lyd
 * 开发时间: 2018-10-11
 */
public class FileUtil {

    public static void saveFile(Component parentComponent, FileInfo fileInfo) throws Exception {
        String filePathNamestr = fileInfo.getFilePath() + File.separator + fileInfo.getFileName();
        if (!validFile(parentComponent, new String[]{filePathNamestr})) {
            //验证不通过
            return;
        }

        File file = new File(filePathNamestr);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file));
            writer.write(fileInfo.getContent());
            writer.flush();
            JOptionPane.showMessageDialog(parentComponent, fileInfo.getFileName()+"保存成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    private static boolean validFile(Component parentComponent, String[] filePathName) throws Exception {
        List<File> files = new ArrayList<File>();
        for (String name : filePathName) {
            File file = new File(name);
            if (validFile(parentComponent, file)) {
                files.add(file);
            } else {
                return false;
            }
        }
        //删除file
        for (File file : files) {
            if (file.exists()) {
                if (!file.delete()) {
                    throw new Exception("无法覆盖文件：" + file.getName());
                }
            }
        }
        return true;
    }

    private static boolean validFile(Component parentComponent, File file) {
        if (file.exists()) {
            int i = JOptionPane.showConfirmDialog(parentComponent, "文件：" + file.getName() + ",已存在，是否覆盖", "警告", JOptionPane.WARNING_MESSAGE);
            if (i == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
