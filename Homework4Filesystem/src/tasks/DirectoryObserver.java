package tasks;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class DirectoryObserver extends Thread {

    private String targetDir;
    private boolean recursive;

    public DirectoryObserver(String targetDir, boolean recursive) {
        this.targetDir = targetDir;
        this.recursive = recursive;
    }

    @Override
    public void run() {
        Path dir = (new File(targetDir)).toPath();
        try {
            new WatchDir(dir, recursive).processEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
