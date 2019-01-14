package helper.collections;

import log.LogItem;

import java.util.ArrayList;

/**
  * The class <code>LogCollection</code> represents a custom arraylist for the log
  * @version 1.0
  * @author Dorian Terbah 
**/

public class LogCollection extends ArrayList<LogItem> {
    
    public LogCollection() {
        super();
    }

    /***************************** 
    ***********TO STRONG********** 
    *****************************/
    /**
      * Get the description of the log
      * @return The description of the log 
    **/
    @Override
    public String toString() {
        String str = "";
        for(LogItem logItem : this) {
            str += logItem.toString() + "\n";
        }

        return str;
    }
}