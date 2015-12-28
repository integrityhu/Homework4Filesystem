package tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFileTask {
    public static void printFileWithScanner(String fileName) {
        try {
          Scanner scanner = new Scanner(new File(fileName));
          scanner.useDelimiter
            (System.getProperty("line.separator")); 
          while (scanner.hasNext()) {
            System.out.println(scanner.next());
          }
          scanner.close();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      } 
}
