package json;

import java.io.*;

import org.json.JSONObject;

/**
  * The class <code>JSONImport</code> imports the game from a file
  * @version 1.0
  * @author Dorian Terbah 
**/

public class JSONImport {

    /**
      * Constant used to have the path of the game file 
    **/
    public static final String GAME_EXPORT_FILE = "game.txt";

    /**
      * Constant used to have the path of the undo/redo file 
    **/
    public static final String UNDO_REDO_EXPORT_FILE = "undo-redo.txt";

    /**
      * Constant used to have the directory of the file game 
    **/
    public static final String EXPORT_DIRECTORY = "export";

    /**
      * Import the game from the file 
    **/
    public static final String load(File file) {
        String json = "";
        BufferedReader reader;
        StringBuffer buffer = new StringBuffer();

        try {
            reader = new BufferedReader(new FileReader(file));

            try {
                while((json = reader.readLine()) != null) {
                    buffer.append(json);
                }
            } catch(IOException readException) {
                System.err.println("Error when reading the file " + GAME_EXPORT_FILE);
                System.exit(1);
            }

        } catch(IOException readerException) {
            System.err.println("Error during searching the file " + GAME_EXPORT_FILE);
            System.exit(1);
        }

        return buffer.toString();
    }
}