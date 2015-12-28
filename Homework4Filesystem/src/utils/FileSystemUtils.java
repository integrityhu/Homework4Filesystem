package utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.LinkedList;
import java.util.Queue;

public class FileSystemUtils {

    public static String[] listFilesWithNameFilter(String root, FilenameFilter filter) {
        String[] result = null;
        File dir = new File(root);
        if (filter != null) {
            result = dir.list(filter);
        } else {
            result = dir.list();
        }
        return result;
    }
    
    public static File[] listFiles(String root, FileFilter filter) {
        File[] result = null;
        File dir = new File(root);
        if (filter != null) {
            result = dir.listFiles(filter);
        } else {
            result = dir.listFiles();
        }
        return result;
    }

}
