package engine;

import engine.initializer.PieceInitializer;

import enums.GameMode;
import enums.PlayerType;
import enums.PieceType;

import engine.game.GamePieces;

import models.game.players.Player;

import models.game.pieces.Piece;

import models.views.BoardModel;

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

    public static final PieceInitializer pieceInitializer = new PieceInitializer();
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
    private Player whitePayer;
    
    /**
      * The model of the board 
    **/
    private BoardModel boardModel;

    private Engine(GameMode mode, ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces,
                   BoardModel boardModel
    ) {
        this.mode = mode;

        this.whitePayer = new Player(PlayerType.WHITE_PLAYER, whitePieces);
        this.blackPlayer = (this.mode.equals(GameMode.PVE_MODE)) ? null /*temp*/ : new Player(PlayerType.BLACK_PLAYER, blackPieces);
        
        this.boardModel = boardModel;
    }

    /***************************** 
    ***********INSTANCE*********** 
    *****************************

    /**
      * Get the unique instance of the Engine
      * @return The unique instance of the Engine
    **/
    public Engine instance() {
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
    public void initialize(GameMode mode, BoardModel boardModel) {
        GamePieces gamePiece = Engine.pieceInitializer.recoverPieces();

        Engine.engine = new engine.Engine(mode, gamePiece.getPieces(PieceType.WHITE_PIECE), gamePiece.getPieces(PieceType.BLACK_PIECE), boardModel);
    }
}