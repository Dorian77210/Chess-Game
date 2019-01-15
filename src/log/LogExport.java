package log;

import log.Log;

import java.io.*;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
  * The class <code>LogExport</code> exports the log
  * @version 1.0
  * @author Dorian Terbah 
**/

public class LogExport {

    private static final String FILE_PATH = "export.txt";

    public static final void exportLog() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = new Date();

        File file = new File(new File("export"), FILE_PATH);
        
        FileWriter writer;

        String header = new StringBuilder()
                            .append("####################################################\n")
                            .append("############# Created by Dorian Terbah #############\n")
                            .append("####################################################\n")
                            .append(dateFormat.format(date) + "\n")
                            .toString();
    
        String footer = new StringBuilder()
                            .append("####################################################\n")
                            .append("##################### END LOG ######################\n")
                            .append("####################################################\n")
                            .toString();
        try {
            writer = new FileWriter(file);

            //write the log
            try {
                writer.write(header);
                writer.write(Log.instance().getLog().toString());
                writer.write(footer);
            } catch(IOException writeException) {
                System.err.println("Error when writing");
                System.exit(1);
            }

            try {
                writer.close();
            } catch(IOException closeException) {
                System.err.println("Error when closing file");
                System.exit(1);
            }
        } catch(IOException exception) {
            System.err.println("Error when opening file");
            System.exit(1);
        }
    }
}