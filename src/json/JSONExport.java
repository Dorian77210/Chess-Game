package json;

import java.io.*;

import org.json.JSONObject;

/**
  * The class <code>JSONExport</code> exports the game in a file
  * @version 1.0
  * @author Dorian Terbah 
**/

public class JSONExport {

    /**
      * Constant used to have the path of the game file 
    **/
    private static final String GAME_EXPORT_FILE = "game.txt";

    /**
      * Constant used to have the directory of the file game 
    **/
    private static final String GAME_EXPORT_DIRECTORY = "export";

    /**
      * Export the game in a file
      * @param json The game in json 
    **/
    public static final void export(String json) {
        File file = new File(new File(GAME_EXPORT_DIRECTORY), GAME_EXPORT_FILE);
        
        FileWriter writer;

        try {
            writer = new FileWriter(file);

            try {
                writer.write(json);
            } catch(IOException writingException) {
                System.err.println("Error when writing game in the file " + GAME_EXPORT_FILE);
                System.exit(1);
            }

            try {
                writer.close();
            } catch(IOException closeException) {
                System.err.println("Error when closing the file " + GAME_EXPORT_FILE);
                System.exit(1);
            }

        } catch(IOException writerException) {
            System.err.println("Error when creating the file " + GAME_EXPORT_FILE);
            System.exit(1);
        }
    }

    /**
      * Export the game in a file
      * @param json The game in json 
    **/
    public static final void export(JSONObject json) {
        export(json.toString());
    }
}