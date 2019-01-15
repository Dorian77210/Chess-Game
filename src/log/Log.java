package log;

import helper.Assert;
import helper.collections.LogCollection;
import log.LogItem;


import java.util.LinkedList;

/**
  * The class <code>Log</code> controls the log of the game
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Log {
    
    /**
      * It's a singleton to avoid multple instance of this class 
    **/
    private static Log instance;

    /**
      * Stack used for the undo 
    **/
    private LinkedList<LogItem> undoStack;

    /**
      * Stack used for the redo 
    **/
    private LinkedList<LogItem> redoStack;

    private Log() {
        this.undoStack = new LinkedList<LogItem>();
        this.redoStack = new LinkedList<LogItem>();
    }

    /**
      * Get the only instance of the class
      * @return The only instance of the class 
    **/
    public static Log instance() {
        if(Assert.isNull(Log.instance)) {
            Log.instance = new Log();
        }

        return Log.instance;
    }    

    /**
      * Give a previous log of the game 
      * @return The previous log of the game
    **/
    public LogItem undo() {
        if(!this.undoStack.isEmpty()) {
            LogItem item = this.undoStack.removeLast();
            this.redoStack.add(item);            
            return item;
        }

        return null;
    }

    /**
      * Give a next log of the game
      * @return The next version of the game
    **/
    public LogItem redo() {
        if(!this.redoStack.isEmpty()) {
            LogItem item = this.redoStack.removeLast();
            this.undoStack.add(item);
            return item;
        }

        return null;
    }

    /**
      * Push a new log of the game
      * @param item The new item
    **/
    public void push(LogItem item) {
        this.undoStack.add(item);
        this.clearRedoStack();
    }


    /**
      * Clear the redoStack pile
    **/
    private void clearRedoStack() {
    	if(!this.redoStack.isEmpty()) {
    		this.redoStack.clear();
    	}	
    }

    /**
      * Refresh all stacks 
    **/
    public void refreshStacks() {
        this.redoStack.clear();
        this.undoStack.clear();
    }

    /**
      * Get the log 
      * @return The log 
    **/
    public LogCollection getLog() {
        LogCollection collection = new LogCollection();

        for(LogItem item : this.undoStack) {
            collection.add(item);
        }

        return collection; 
    }
}