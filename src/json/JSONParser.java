package json;

import models.views.BoardModel;
import models.game.pieces.*;
import enums.PieceType;

import engine.informations.GameInformations;

import ui.board.BoardView;
import ui.board.Cell;

import undo.BoardSave;

import helper.Assert;
import helper.Position;
import helper.collections.PieceCollection;

import engine.initializer.BoardInitializer;

import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.util.LinkedList;
import java.util.HashMap;

/**
  * The class <code>JSONParser</code> parses the board model to json  
  * @version 1.0
  * @author Dorian Terbah 
**/

public class JSONParser {

    /**
      * Constant used to describe the separator for the key in json
    **/
    public static final String JSON_POSITION_SPLITERATOR = "\\|";

    //constants for the attributes of the peices

    /**
      * Constant used to describe if a piece is a black piece for the json 
    **/
    public static final String JSON_IS_BLACK_PIECE = "is-black-piece";

    /**
      * Constant used to describe if a king was already checked 
    **/
    public static final String JSON_WAS_ALREADY_CHECKED = "was-already-checked";

    /**
      * Constant used to describe if a piece has already moved for the json 
    **/
    public static final String JSON_IS_FIRST_TIME_MOVING = "is-first-time-moving";

    /**
      * Constant used to describe the name of the class of a piece for the json 
    **/
    public static final String JSON_CLASS = "class";

    /**
      * Constant used as key for the game informations in json 
    **/
    public static final String JSON_GAME_INFORMATIONS = "game-informations";

    /**
      * Constant used to describe the number of rounds of the game for the json 
    **/
    private static final String JSON_GAME_INFORMATIONS_ROUNDS = "game-informations-rounds";

    /**
      * Constant used to know if the white player is checked 
    **/
    private static final String JSON_IS_WHITE_PLAYER_CHECKED = "is-white-player-checked";

    /**
      * Constant used to know if the black player is checked 
    **/
    private static final String JSON_IS_BLACK_PLAYER_CHECKED = "is-black-player-checked";

    /**
      * Constant used to set the index of x when spliting string position
    **/
    private static final byte X_RAW_COORDINATE_INDEX = 0x0;

    /**
      * Constant used to set the index of y when spliting string position 
    **/
    private static final byte Y_RAW_COORDINATE_INDEX = 0x1;

    /***************************** 
     ***********UNDO/REDO********* 
     ******************************/

    //BoardSave
    /**
      * Constant used to set the key of pieces for the board save in json format 
    **/
    public static final String JSON_BOARD_SAVE_PIECES = "board-save-pieces";    

    /**
      * Constant used to set the key of informations for the board in json format
    **/
    public static final String JSON_BOARD_SAVE_INFORMATIONS = "board-save-informations";

    //Undo/redo
    /**
      * Constant used to describe the json's part of the undo
    **/
    public static final String JSON_UNDO_KEY = "undo";

    /**
      * Constant used to describe the json's part of the redo
    **/
    public static final String JSON_REDO_KEY = "redo";

    /**
      * Constant used to describe the key for the saves for the undo redo 
    **/
    public static final String JSON_UNDO_REDO_BOARD_SAVE_KEY = "board-save-";

     /***************************** 
     ************X to JSON********* 
     ******************************/

    /**
      * Get a JSON representation of the pieces
      * @param model The model of the board
      * @return The JSON representation of the board model 
    **/
    public static final JSONObject piecesToJSON(PieceCollection pieces) {
        JSONObject json = new JSONObject();
        JSONObject pieceInformations;

        Position position;
        String keyPosition = "";
        Cell cell;

        for(Piece piece : pieces) {
            position = piece.getPosition();
            pieceInformations = piece.toJSONFormat();
            keyPosition = position.toJSONFormat();
            json.put(keyPosition, pieceInformations);
        }

        return json;
    }

    /**
      * Save the informations of the game with a json format 
      * @param informations The informations of the game
    **/
    public static final JSONObject informationsToJSON(GameInformations informations) {
        JSONObject json = new JSONObject()
                                      .put(JSON_GAME_INFORMATIONS_ROUNDS, informations.getRounds())
                                      .put(JSON_IS_BLACK_PLAYER_CHECKED, informations.isBlackPlayerChecked())
                                      .put(JSON_IS_WHITE_PLAYER_CHECKED, informations.isWhitePlayerChecked());

        return json;
    }

    /**
      * Get the undo/redo representation in JSON format
      * @param undo The stack of undo
      * @param redo The stack of redo
      * @return The JSON undo/redo's format  
    **/
    public static final JSONObject undoRedoToJSON(LinkedList<BoardSave> undo, LinkedList<BoardSave> redo) {
        JSONObject json = new JSONObject();
        JSONObject undoJSON = new JSONObject();
        JSONObject redoJSON = new JSONObject();

        JSONObject saveJSON = null;

        int index = 0x0; //initial value
        //undo 
        for(BoardSave boardSave : undo) {
            saveJSON = boardSave.toJSONFormat();
            undoJSON.put(JSON_UNDO_REDO_BOARD_SAVE_KEY + index, saveJSON);
            index++;
        }   

        //redo
        index = 0x0;
        for(BoardSave boardSave : redo) {
            saveJSON = boardSave.toJSONFormat();
            redoJSON.put(JSON_UNDO_REDO_BOARD_SAVE_KEY + index, saveJSON);
            index++;
        }

        json.put(JSON_UNDO_KEY, undoJSON);
        json.put(JSON_REDO_KEY, redoJSON);

        return json;
    }


    /***************************** 
     ************JSON to X********* 
     ******************************/

    /**
      * Parse a json to restitute all pieces
      * @param json The json
      * @return All of pieces
    **/
    public static final PieceCollection jsonToPlayers(JSONObject json) {
        PieceCollection collection = new PieceCollection();

        Position position;
        String rawX, rawY;
        JSONObject pieceInformations;
        Piece piece;
        int index;
        PieceType type;


        boolean isBlackPiece, wasAlreadyChecked, isFirstTimeMoving;
        String classInfo;

        for(String key : JSONObject.getNames(json)) {
            //calculate the position
            if(key.equals(JSON_GAME_INFORMATIONS)) continue; //avoid the game informations
            rawX = key.split(JSON_POSITION_SPLITERATOR)[X_RAW_COORDINATE_INDEX];
            rawY = key.split(JSON_POSITION_SPLITERATOR)[Y_RAW_COORDINATE_INDEX];
            position = new Position(Integer.parseInt(rawX), Integer.parseInt(rawY));

            //retrieve the jsonsObject associated to the position
            pieceInformations = json.getJSONObject(key);
            classInfo = pieceInformations.getString(JSON_CLASS);
            isBlackPiece = pieceInformations.getBoolean(JSON_IS_BLACK_PIECE);   
            isFirstTimeMoving = pieceInformations.getBoolean(JSON_IS_FIRST_TIME_MOVING);
            type = isBlackPiece ? PieceType.BLACK_PIECE : PieceType.WHITE_PIECE;

            //generic creation of piece
            try {
                Class<?> clazz = Class.forName("models.game.pieces." + classInfo);
                Constructor<?> constructor = clazz.getConstructor(Position.class, PieceType.class);
                Object rawObject = constructor.newInstance(position, type);
                piece = (Piece)rawObject;
                if(piece instanceof King) {
                    wasAlreadyChecked = pieceInformations.getBoolean(JSON_WAS_ALREADY_CHECKED);
                    ((King)piece).setWasAlreadyChecked(wasAlreadyChecked);
                } 

                piece.setIsFirstTimeMoving(isFirstTimeMoving);

                collection.add(piece);

            } catch(ClassNotFoundException classNotFoundException) {
                System.out.println(classInfo);
                System.err.println("Class not found");
                System.exit(1);
            } catch(InstantiationException instanciationException) {
                System.err.println("Error during the instanciation");
                System.exit(1);
            } catch(IllegalAccessException illegalException) {
                System.err.println("Error for illegal");
                System.exit(1);
            } catch(InvocationTargetException invocationTargetException) {
                System.err.println("Error when invoking the constructor");
                System.exit(1);
            } catch(NoSuchMethodException noSuchMethodException) {
                System.err.println("Error for the method");
                System.exit(1);
            }

        }

        return collection;
    }

    /**
      * Get a GameInformations thanks to a json 
      * @param json The json object
      * @return The informations associated to the json
    **/
    public static final GameInformations jsonToInformations(JSONObject json) {
        GameInformations informations = new GameInformations();
        informations.setRounds(json.getInt(JSON_GAME_INFORMATIONS_ROUNDS));
        informations.setIsBlackPlayerChecked(json.getBoolean(JSON_IS_BLACK_PLAYER_CHECKED));
        informations.setIsWhitePlayerChecked(json.getBoolean(JSON_IS_WHITE_PLAYER_CHECKED));

        return informations;
    }

    /**
      * Retrieve the undo redo thanks to the json
      * @param json The json file
      * @return An hashmap with the undo stack and the redo stack 
    **/
    public static final HashMap<String, LinkedList<BoardSave>> jsonToUndoRedo(JSONObject json) {
        HashMap<String, LinkedList<BoardSave>> hashMap = new HashMap<String, LinkedList<BoardSave>>();
        
        LinkedList<BoardSave> undo = new LinkedList<BoardSave>();
        LinkedList<BoardSave> redo = new LinkedList<BoardSave>();

        //retrieve the json associated to the undo and redo
        JSONObject undoJSON = json.getJSONObject(JSON_UNDO_KEY);
        JSONObject redoJSON = json.getJSONObject(JSON_REDO_KEY);

        JSONObject saveJSON = null;

        //create the undo stack
        if(Assert.isSet(JSONObject.getNames(undoJSON))) {
            for(String key : JSONObject.getNames(undoJSON)) {
                saveJSON = undoJSON.getJSONObject(key);
                undo.add(new BoardSave(saveJSON));
            }
        }
        

        //create the redo stack
        if(Assert.isSet(JSONObject.getNames(redoJSON))) {
            for(String key : JSONObject.getNames(redoJSON)) {
                saveJSON = redoJSON.getJSONObject(key);
                redo.add(new BoardSave(saveJSON));
            }
        }

        //create the hash map
        hashMap.put(JSON_UNDO_KEY, undo);
        hashMap.put(JSON_REDO_KEY, redo);

        return hashMap;
    }
}