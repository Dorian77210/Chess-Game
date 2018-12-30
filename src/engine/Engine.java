package engine;

import engine.initializer.PieceInitializer;
import engine.initializer.BoardInitializer;

import enums.GameMode;
import enums.PlayerType;
import enums.PieceType;

import engine.game.GamePieces;

import engine.actions.Actions;

import engine.informations.GameInformations;

import engine.ranges.Ranges;

import models.game.players.Player;

import models.game.pieces.Piece;

import models.views.BoardModel;

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


    public static final Ranges ranges = new Ranges();

    /**
      * The differents actions for the game 
    **/
    public static final Actions actions = new Actions();

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

    private Engine(GameMode mode, ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces,
                   BoardModel boardModel
    ) {
        this.mode = mode;

        this.whitePlayer = new Player(PlayerType.WHITE_PLAYER, whitePieces);
        this.blackPlayer = (this.mode.equals(GameMode.PVE_MODE)) ? null /*temp*/ : new Player(PlayerType.BLACK_PLAYER, blackPieces);
        
        this.boardModel = boardModel;

        this.informations = new GameInformations();
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

        Engine.engine = new engine.Engine(mode, gamePiece.getPieces(PieceType.WHITE_PIECE), gamePiece.getPieces(PieceType.BLACK_PIECE), boardModel);
	}
	
	/**
	  * Initialize the cells of the board
	  * @param cells The cells of the board
	**/
	public void initializeBoard(Cell[][] cells) {
		ArrayList<Piece> pieces = new ArrayList<Piece>(this.blackPlayer.getPieces());
		pieces.addAll(this.whitePlayer.getPieces());

		Engine.boardInitializer.initializeCells(cells, pieces);
    }
    
    
}