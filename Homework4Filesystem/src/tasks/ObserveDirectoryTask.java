package tasks;

public class ObserveDirectoryTask {

    public static void observe(String dir, boolean recursive) {
        DirectoryObserver observer = new DirectoryObserver(dir,recursive);
        observer.start();
        synchronized (observer) {
            try {
                observer.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  
        } 
    }
    
}
