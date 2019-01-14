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

import ui.board.Cell;

import java.util.ArrayList;

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


    public Ranges ranges;

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

    /***************************** 
    ***********INSTANCE*********** 
    *****************************

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
        GamePieces gamePiece = Engine.pieceInitializer.recoverPieces(); //recover all of the pieces in the file

        Engine.engine = new Engine(mode, gamePiece.getPieces(PieceType.WHITE_PIECE), gamePiece.getPieces(PieceType.BLACK_PIECE), boardModel);
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
}