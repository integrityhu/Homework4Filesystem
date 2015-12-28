package tasks;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Date;

import utils.FileSystemUtils;

public class RecursiveScannerDirWithOldMethod extends Thread {

    private String startDir;

    private static FilenameFilter fileNameFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            boolean result = !name.equals(".thumb");
            return result;
        }
    };

    private static FileFilter dirFilter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            boolean result = pathname.isDirectory() && !(pathname.getName().equals("..") || pathname.getName().equals("."));
            return result;
        }
    };

    public RecursiveScannerDirWithOldMethod(String startDir) {
        this.startDir = startDir;
    }

    private void scanDir(File dir) {
        String[] fileList = FileSystemUtils.listFilesWithNameFilter(dir.getAbsolutePath(), fileNameFilter);
        if (fileList != null) {
            for (int idx = 0; idx < fileList.length; idx++) {
                File file = new File(dir.getAbsolutePath() + File.separator + fileList[idx]);
                if (!file.isDirectory()) {
                    System.out.println(file.getAbsolutePath() + " {length:" + file.length() + "}");
                }
            }
        }

        File[] dirList = FileSystemUtils.listFiles(dir.getAbsolutePath(), dirFilter);
        if (dirList != null) {
            for (int idx = 0; idx < dirList.length; idx++) {
                System.out.println(dirList[idx] + " {dir}");
                scanDir(dirList[idx]);

            }
        }
    }

    @Override
    public void run() {
        Date start = new Date();
        scanDir(new File(startDir));
        Date end = new Date();
        System.out.println(end.getTime()-start.getTime() + " ms");
    }

}
