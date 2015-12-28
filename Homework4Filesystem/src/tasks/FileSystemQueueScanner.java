package tasks;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class FileSystemQueueScanner extends Thread {

    private String startDir;
    private Queue<File> dirs = new LinkedList<File>();
    
    public FileSystemQueueScanner(String startDir) {
        this.startDir = startDir;
    }
    
    private void scanDir(String dir) {
        File file = new File(dir);
        dirs.add(file);
        while (!dirs.isEmpty()) {
          for (File f : dirs.poll().listFiles()) {
            if (f.isDirectory()) {
              dirs.add(f);
              System.out.println(f.getAbsolutePath() + " {dir}");
            } else if (f.isFile()) {
              System.out.println(f.getAbsolutePath() + " {fileName:"+f.getName()+";length:" + file.length() + "}");
            }
          }
        }
    }
    
    @Override
    public void run() {
        Date start = new Date();
        scanDir(startDir);
        Date end = new Date();
        System.out.println(end.getTime()-start.getTime() + " ms");
    }

}
