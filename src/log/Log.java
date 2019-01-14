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
    private LinkedList<LogCollection> undoStack;

    /**
      * Stack used for the redo 
    **/
    private LinkedList<LogCollection> redoStack;

    private Log() {
        this.undoStack = new LinkedList<LogCollection>();
        this.redoStack = new LinkedList<LogCollection>();
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
    public LogCollection undo(LogCollection logCollection) {
        if(!this.undoStack.isEmpty()) {
            this.redoStack.add(logCollection);
            return this.undoStack.removeLast();
        }

        return null;
    }

    /**
      * Give a next log of the game
      * @return The next version of the game
    **/
    public LogCollection redo(LogCollection logCollection) {
        if(!this.redoStack.isEmpty()) {
            this.undoStack.add(logCollection);
            return this.redoStack.removeLast();
        }

        return null;
    }

    /**
      * Push a new log of the game
      * @param logCollection The new log
    **/
    public void push(LogCollection logCollection) {
        this.undoStack.add(logCollection);
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
}