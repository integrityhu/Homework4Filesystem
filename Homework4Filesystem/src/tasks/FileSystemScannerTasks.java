package tasks;

public class FileSystemScannerTasks {

    public static void recursiveScanWithOldMethod(String dir) {
        RecursiveScannerDirWithOldMethod fsScanner = new RecursiveScannerDirWithOldMethod(dir);            
        fsScanner.start();
        synchronized (fsScanner) {
            try {
                fsScanner.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  
        } 
    }

    public static void scanWithNIOMethod(String dir) {
        RecursiveScannerDirWithNIO fsScanner = new RecursiveScannerDirWithNIO(dir, "*");            
        fsScanner.start();
        synchronized (fsScanner) {
            try {
                fsScanner.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  
        } 
    }

    public static void findNIOMethod(String dir, String pattern) {
        RecursiveScannerDirWithNIO fsScanner = new RecursiveScannerDirWithNIO(dir, pattern);            
        fsScanner.start();
        synchronized (fsScanner) {
            try {
                fsScanner.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  
        } 
    }

    public static void scanWithQueue(String dir) {
        FileSystemQueueScanner fsScanner = new FileSystemQueueScanner(dir);            
        fsScanner.start();
        synchronized (fsScanner) {
            try {
                fsScanner.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  
        }         
    }
}
