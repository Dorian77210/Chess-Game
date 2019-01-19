package undo;

import helper.Assert;
import helper.collections.PieceCollection;

import engine.Engine;
import engine.informations.GameInformations;

import undo.BoardSave;

import json.JSONExport;
import json.JSONParser;
import json.JSONImport;

import java.io.File;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.HashMap;

/**
  * The class <code>UndoRedo</code> controls the undo and the redo
  * @version 1.0
  * @author Dorian Terbah
**/

public class UndoRedo {
    
    /**
      * It's a singleton to avoid multple instance of this class 
    **/
    private static UndoRedo undoRedo;

    /**
      * Stack used for the undo 
    **/
    private LinkedList<BoardSave> undoStack;

    /**
      * Stack used for the redo 
    **/
    private LinkedList<BoardSave> redoStack;

    public UndoRedo() {
        this.undoStack = new LinkedList<BoardSave>();
        this.redoStack = new LinkedList<BoardSave>();
    }

    /**
      * Get the only instance of the class
      * @return The only instance of the class 
    **/
    public static UndoRedo instance() {
        if(Assert.isNull(UndoRedo.undoRedo)) {
            UndoRedo.undoRedo = new UndoRedo();
        }

        return UndoRedo.undoRedo;
    }

    /**
      * Give a previous version of the game 
      * @return The previous version of the game
    **/
    public BoardSave undo(BoardSave currentBoard) {
        if(!this.undoStack.isEmpty()) {
            this.redoStack.add(currentBoard);
            return this.undoStack.removeLast();
        }

        return null;
    }

    /**
      * Give a next version of the game
      * @return The next version of the game
    **/
    public BoardSave redo(BoardSave currentBoard) {
        if(!this.redoStack.isEmpty()) {
            this.undoStack.add(currentBoard);
            return this.redoStack.removeLast();
        }

        return null;
    }

    /**
      * Push a new version of the game
      * @param allPieces All of the pieces of the two players
    **/
    public void push(PieceCollection allPieces) {
        this.undoStack.add(new BoardSave(allPieces, Engine.instance().informations));
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

    /***************************** 
    *************SAVE************* 
    ******************************/
    /**
      * Save the undo/redo in a file 
    **/
    public void save() {
        File file = new File(new File(JSONExport.EXPORT_DIRECTORY), JSONExport.UNDO_REDO_EXPORT_FILE);
        JSONObject json = JSONParser.undoRedoToJSON(this.undoStack, this.redoStack);
        JSONExport.export(json.toString(), file);
    }

    /**
      * Load the undo/redo in a file 
    **/
    public void load() {    
        File file = new File(new File(JSONImport.EXPORT_DIRECTORY), JSONImport.UNDO_REDO_EXPORT_FILE);
        String json = JSONImport.load(file);
        JSONObject jsonObject = new JSONObject(json);

        HashMap<String, LinkedList<BoardSave>> hashMap = JSONParser.jsonToUndoRedo(jsonObject);

        //refresh the stacks with the new values
        this.undoStack = hashMap.get(JSONParser.JSON_UNDO_KEY);
        this.redoStack = hashMap.get(JSONParser.JSON_REDO_KEY);
    }
}