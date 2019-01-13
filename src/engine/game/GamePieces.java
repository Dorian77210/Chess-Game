package engine.game;

import models.game.pieces.Piece;

import enums.PieceType;

import helper.collections.PieceCollection;

import java.util.ArrayList;
/**
  * The class <code>GamePieces</code> represents all of the piece of the game
  * @version 1.0
  * @author Dorian Terbah 
**/

public class GamePieces {

    /**
      * The white pieces 
    **/
    private PieceCollection whitePieces;

    /**
      * The black pieces 
    **/
    private PieceCollection blackPieces;

    public GamePieces(PieceCollection whitePieces, PieceCollection blackPieces) {
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;
    }

    /***************************** 
    *************GETTER*********** 
    *****************************/
    
    /**
      * Get the pieces according to the type
      * @param pieceType The type of pieces wanted
      * @return Pieces associated to the type 
    **/
    public PieceCollection getPieces(PieceType pieceType) {
        return (pieceType.equals(PieceType.BLACK_PIECE)) ? this.blackPieces : this.whitePieces;
    }
}