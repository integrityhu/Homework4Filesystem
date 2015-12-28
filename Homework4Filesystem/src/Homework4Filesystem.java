import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Parser;

import tasks.ObserveDirectoryTask;
import tasks.ReadFileTask;
import tasks.RecursiveScannerDirWithOldMethod;
import tasks.FileSystemScannerTasks;

public class Homework4Filesystem {

    @SuppressWarnings("static-access")
    private static Options getCommandLineOptions() {
        Options options = new Options();
        options.addOption(OptionBuilder.isRequired(true).withArgName("task").withLongOpt("task").hasArg(true).withDescription("filesystem task [rm,mkdir,oldScan,queueScan,nioScan,find,observe]").create());
        options.addOption(OptionBuilder.isRequired(false).withArgName("dir").withLongOpt("dir").hasArg(true).withDescription("target dir for all tasks").create());
        options.addOption(OptionBuilder.isRequired(false).withArgName("file").withLongOpt("file").hasArg(true).withDescription("file to print").create());
        options.addOption(OptionBuilder.isRequired(false).withArgName("pattern").withLongOpt("pattern").hasArg(true).withDescription("pattern to find").create());
        options.addOption(OptionBuilder.isRequired(false).withArgName("r").withLongOpt("r").hasArg(false).withDescription("observe recursive").create());
        return options;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Parser parser = new GnuParser();
        Options options = getCommandLineOptions();
        String task = null, dir = null, pattern = null, fileName = null;
        CommandLine commandLine = null;
        boolean recursive = false;
        try {
            commandLine = parser.parse(options, args);
            task = (String) commandLine.getOptionValue("task");
            switch (task) {
            case "find":
                pattern = (String) commandLine.getOptionValue("pattern");
                break;
            case "observe":
                recursive = commandLine.hasOption('r');
                break;
            case "print":
                fileName = commandLine.getOptionValue("file");
                break;                
            }
            if (! "print".equals(task)) {
                dir = (String) commandLine.getOptionValue("dir");
            }
        } catch (ParseException ex) {
            System.out.println(ex.getLocalizedMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar homework4filesystem.jar", options);
            System.exit(-1);
        }

        switch (task) {
        case "oldScan":
            FileSystemScannerTasks.recursiveScanWithOldMethod(dir);
            break;
        case "nioScan":
            FileSystemScannerTasks.scanWithNIOMethod(dir);
            break;
        case "queueScan":
            FileSystemScannerTasks.scanWithQueue(dir);
            break;
        case "find":
            FileSystemScannerTasks.findNIOMethod(dir, pattern);
            break;
        case "observe":
            ObserveDirectoryTask.observe(dir, recursive);
            break;
        case "print":
            ReadFileTask.printFileWithScanner(fileName);
            break;
        }

    }

}
