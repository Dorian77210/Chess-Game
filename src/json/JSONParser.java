package json;

import models.views.BoardModel;
import models.game.pieces.*;
import enums.PieceType;

import helper.Assert;
import helper.Position;
import helper.collections.PieceCollection;

import engine.initializer.BoardInitializer;

import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
  * The class <code>JSONParser</code> parses the board model to json  
  * @version 1.0
  * @author Dorian Terbah 
**/

public class JSONParser {

    /**
      * Constant used to describe the separator for the key in json
    **/
    private static final String JSON_POSITION_SPLITERATOR = "|";

    //constants for the attributes of the peices

    /**
      * Constant used to describe if a piece is a black piece for the json 
    **/
    private static final String JSON_IS_BLACK_PIECE = "is-black-piece";

    /**
      * Constant used to describe if a king was already checked 
    **/
    private static final String JSON_WAS_ALREADY_CHECKED = "was-already-checked";

    /**
      * Constant used to describe if a piece has already moved for the json 
    **/
    private static final String JSON_IS_FIRST_TIME_MOVING = "is-first-time-moving";

    /**
      * Constant used to describe the name of the class of a piece for the json 
    **/
    private static final String JSON_CLASS = "class";

    /**
      * Get a JSON representation of the board model 
      * @param model The model of the board
      * @return The JSON representation of the board model 
    **/
    public static final JSONObject boardToJSON(BoardModel model) {
        JSONObject json = new JSONObject();
        JSONObject pieceInformations;

        Position position;
        String keyPosition = "";
        Cell cell;
        Piece piece;

        for(int y = 0; y < BoardView.HEIGHT; y++) {
            for(int x = 0; x < BoardView.WIDTH; x++) {
                position = new Position(x, y);
                cell = model.getCell(position);
                piece = cell.getPiece();

                if(Assert.isSet(piece)) {
                    //generate the json for the piece
                    pieceInformations = new JSONObject()
                                            .put(JSON_IS_BLACK_PIECE, piece.isBlackPiece())
                                            .put(JSON_IS_FIRST_TIME_MOVING, piece.isFirstTimeMoving())
                                            .put(JSON_CLASS, piece.getClassInfo());

                    if(piece instanceof King) {
                        pieceInformations = pieceInformations.put(JSON_WAS_ALREADY_CHECKED, ((King)piece).wasAlreadyChecked());
                    }

                    keyPosition = "" + position.x + "|" + position.y;
                    json.put(keyPosition, pieceInformations);
                } 
            }
        }

        return json;
    }

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


        boolean isBlackPiece, wasAlreadyChecked;
        String classInfo;

        for(String key : JSONObject.getNames(json)) {
            //calculate the position
            index = key.indexOf(JSON_POSITION_SPLITERATOR);
            rawX = key.substring(0, index);
            rawY = key.substring(index + 1	, key.length());
            position = new Position(Integer.parseInt(rawX), Integer.parseInt(rawY));

            //retrieve the jsonsObject associated to the position
            pieceInformations = json.getJSONObject(key);
            classInfo = pieceInformations.getString(JSON_CLASS);
            isBlackPiece = pieceInformations.getBoolean(JSON_IS_BLACK_PIECE);
            type = isBlackPiece ? PieceType.BLACK_PIECE : PieceType.WHITE_PIECE;

            try {
                Class<?> clazz = Class.forName("models.game.pieces." + classInfo);
                Constructor<?> constructor = clazz.getConstructor(Position.class, PieceType.class);
                Object rawObject = constructor.newInstance(position, type);
                piece = (Piece)rawObject;
                if(piece instanceof King) {
                    wasAlreadyChecked = pieceInformations.getString(JSON_WAS_ALREADY_CHECKED);
                    ((King)piece).setWasAlreadyChecked(wasAlreadyChecked);
                }

                collection.add(piece);

            } catch(ClassNotFoundException classNotFoundException) {
                System.err.println("Class not found");
                System.exit(1);
            } catch(InstantiationException instanciationException) {
                System.err.println("Error during the instanciation");
                System.exit(1);
            } catch(IllegalAccessException illegalException) {
                System.err.println("Error for illegal");
                System.exit(1);
            } catch(InvocationTargetException invocationTargetException) {
                System.err.println("Error when incoking the constructor");
                System.exit(1);
            } catch(NoSuchMethodException noSuchMethodException) {
                System.err.println("Error for the method");
                System.exit(1);
            }

        }

        return collection;
    }
}