package engine;

import engine.counter.PieceCounter;

import engine.initializer.PieceInitializer;
import engine.initializer.BoardInitializer;

import enums.GameMode;
import enums.PlayerType;
import enums.PieceType;

import engine.game.GamePieces;

import engine.actions.Actions;

import engine.informations.GameInformations;

import engine.ranges.Ranges;
import engine.ranges.KingRange;

import models.game.players.Player;

import models.game.pieces.Piece;
import models.game.pieces.King;

import models.views.BoardModel;

import helper.collections.PieceCollection;
import helper.collections.CellCollection;
import helper.collide.PieceCollision;

import json.JSONExport;
import json.JSONImport;
import json.JSONParser;

import undo.UndoRedo;

import ui.board.Cell;
import ui.board.BoardView;

import java.io.File;

import org.json.JSONObject;

/**
  * The class <code>Engine</code> represents the engine of the game
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Engine {

    /**
      * The singleton of the engine 
    **/
    private static Engine engine;

	/**
	  * The initializer of piece 
	**/
    private static final PieceInitializer pieceInitializer = new PieceInitializer();

	/**
	  * Initializer of the board 
	**/
	private static final BoardInitializer boardInitializer = new BoardInitializer();


    public final Ranges ranges;

    /**
      * The differents actions for the game 
    **/
    public static final Actions actions = new Actions();

    /**
      * The counter of piece for the game 
    **/
    public static final PieceCounter pieceCounter = new PieceCounter();

    /**
      * The mode of the game 
    **/
    private GameMode mode;

    /**
      * The black player 
    **/
    private Player blackPlayer;
    
    /**
      * The white player 
    **/
    private Player whitePlayer;
    
    /**
      * The model of the board 
    **/
    private BoardModel boardModel;

    /**
      * The infomations about the game 
    **/
    public GameInformations informations;

    private Engine(GameMode mode, PieceCollection whitePieces, PieceCollection blackPieces,
                   BoardModel boardModel
    ) {
        this.mode = mode;

        this.whitePlayer = new Player(PlayerType.WHITE_PLAYER, whitePieces);
        this.blackPlayer = (this.mode.equals(GameMode.PVE_MODE)) ? null /*temp*/ : new Player(PlayerType.BLACK_PLAYER, blackPieces);
        
        this.boardModel = boardModel;

        this.informations = new GameInformations();

        this.ranges = new Ranges(this.boardModel);
    }

    //second constructor
    private Engine(PieceCollection pieces, GameInformations gameInformations, BoardModel model) {
        this.informations = gameInformations;
        
        this.whitePlayer = new Player(PlayerType.WHITE_PLAYER, pieces.getPiecesByColor(true));
        this.blackPlayer = new Player(PlayerType.BLACK_PLAYER, pieces.getPiecesByColor(false));

        this.boardModel = model;

        this.ranges = new Ranges(this.boardModel);
    }


    /***************************** 
    ***********INSTANCE*********** 
    ******************************/

    /**
      * Get the unique instance of the Engine
      * @return The unique instance of the Engine
    **/
    public static final Engine instance() {
        return Engine.engine;
    }

    /***************************** 
    ********INIT METHODS********** 
    *****************************/

    /**
      * Initialize the engine 
      * @param mode The mode of the game
      * @param boardModel The model of the board 
    **/
    public static final void initialize(GameMode mode, BoardModel boardModel) {
        if(mode.equals(GameMode.PVE_MODE) || mode.equals(GameMode.PVP_MODE)) {
            GamePieces gamePiece = Engine.pieceInitializer.recoverPieces(); //recover all of the pieces in the file
            Engine.engine = new Engine(mode, gamePiece.getPieces(PieceType.WHITE_PIECE), gamePiece.getPieces(PieceType.BLACK_PIECE), boardModel);
        } else if(mode.equals(GameMode.LOAD_GAME)) {
            File file = new File(new File(JSONImport.EXPORT_DIRECTORY), JSONImport.GAME_EXPORT_FILE);
            String json = JSONImport.load(file);
            JSONObject jsonObject = new JSONObject(json);
            PieceCollection collection = JSONParser.jsonToPlayers(jsonObject);
            GameInformations gameInformations = JSONParser.jsonToInformations(jsonObject.getJSONObject(JSONParser.JSON_GAME_INFORMATIONS));
            Engine.engine = new Engine(collection, gameInformations, boardModel);

            //load the undo/redo
            UndoRedo.instance().load();
        }


	}
	
	/**
	  * Initialize the cells of the board
	  * @param cells The cells of the board
	**/
	public void initializeBoard(Cell[][] cells) {
		PieceCollection pieces = new PieceCollection(this.blackPlayer.getPieces());
		pieces.addAll(this.whitePlayer.getPieces());

		Engine.boardInitializer.initializeCells(cells, pieces);
    }
    
    /***************************** 
    ***********GETTER************* 
    ******************************/

    /**
      * Get the player according to his type
      * @param type The type of the player 
    **/
    public Player getPlayer(PlayerType type) {
        return (type.equals(PlayerType.BLACK_PLAYER)) ? this.blackPlayer : this.whitePlayer;
    }

    /**
      * Get the current player
      * @return The current player 
    **/
    public Player getCurrentPlayer() {
        return (this.informations.isBlackPlayerPlaying()) ? this.blackPlayer : this.whitePlayer;
    }

    /**
      * Get the not other player
      * @return The not current player 
    **/
    public Player getNotCurrentPlayer() {
        return (this.informations.isBlackPlayerPlaying()) ? this.whitePlayer : this.blackPlayer;
    }

    /**
      * Get all of the pieces
      * @return all of the pieces 
    **/
    public PieceCollection getAllPieces() {
        PieceCollection collection = new PieceCollection();
        collection.addAll(this.whitePlayer.getPieces());
        collection.addAll(this.blackPlayer.getPieces());

        return collection;

    }

    /***************************** 
    ***********UPDATE************* 
    ******************************/
    
    /**
      * Update the checked states 
    **/
    public void updateCheckedStates() {
        Player currentPlayer = this.getCurrentPlayer();
        King currentKing = currentPlayer.getKing();
        CellCollection kingRawRange = KingRange.getRawKingRange(this.boardModel, currentKing); 
        PieceCollection collidePieces = PieceCollision.getPiecesCollideWithKing(currentKing, kingRawRange, this.boardModel);

        if(!collidePieces.isEmpty()) {
            if(currentPlayer.isBlackPlayer()) {
                this.informations.setIsBlackPlayerChecked(true);
                if(!currentKing.wasAlreadyChecked()) {
                    currentKing.toggleWasAlreadyChecked();
                }
            } else {
                this.informations.setIsWhitePlayerChecked(true);
                if(!currentKing.wasAlreadyChecked()) {
                    currentKing.toggleWasAlreadyChecked();
                }
            }
        }
    }

    /***************************** 
    ************CLONE************* 
    *****************************/

    /**
      * Copy a copy of the current board model according to his json representation
      * @return A string representation of the board
    **/
    public String copyOfBoard() {
        //JSONObject json = JSONParser.boardToJSON(this.boardModel);
        return "";
        //return json.toString();
    }

    /**
      * Restitute the board model
      * @param collection The pieces 
    **/
    public void restituteBoard(PieceCollection collection) {
        Cell[][] cells = new Cell[BoardView.HEIGHT][BoardView.WIDTH];
        BoardInitializer.initializeCells(cells, collection);

        this.boardModel = new BoardModel(cells);
    }

    /***************************** 
    ************SAVE************** 
    *****************************/
    /**
      * Save the game 
    **/
    public void saveGame() {
        //create a json object with the informations that concern the pieces and the game informations   
        JSONObject json = JSONParser.piecesToJSON(this.getAllPieces());
        json.put("game-informations", JSONParser.informationsToJSON(this.informations));

        File file = new File(new File(JSONExport.EXPORT_DIRECTORY), JSONExport.GAME_EXPORT_FILE);
        JSONExport.export(json, file);

        UndoRedo.instance().save();
    }
}