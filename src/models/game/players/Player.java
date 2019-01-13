package models.game.players;

import models.game.pieces.Pawn;
import models.game.pieces.Piece;
import models.game.pieces.Queen;
import models.game.pieces.King;

import enums.PlayerType;

import helper.collections.PieceCollection;
import helper.Position;

import java.util.ArrayList;

/**
  * The class <code>Player</code> represents a basic player
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Player {

    /**
      * The pieces of the player 
    **/
    private PieceCollection pieces;

    /**
      * The type of the player 
    **/
    private PlayerType type;

    public Player(PlayerType type, PieceCollection pieces) {
        this.type = type;
        this.pieces = pieces;
    }

    /***************************** 
    *************GETTER*********** 
    *****************************/

    /**
      * Get all pieces of the player
      * @return The pieces of the player  
    **/
    public PieceCollection getPieces() {
        return this.pieces;
    }

    /**
      * Get the king of this player
      * @return The king of the player 
    **/
    public King getKing() {
        for(Piece piece : this.pieces) {
            if(piece instanceof King) {
                return (King)piece;
            }
        }

        return null;
    }
    
    /**
      * Get the black's state color
      * @return The blacks state color 
    **/
    public boolean isBlackPlayer() {
        return this.type.equals(PlayerType.BLACK_PLAYER);
    }

    /***************************** 
    *********REMOVE METHOD******** 
    *****************************/

    /**
      * Remove a piece
      * @param piece The piece to remove 
    **/
    public void removePiece(Piece piece) {
        this.pieces.remove(piece);
    }

    /***************************** 
    *********PAWN TO QUEEN******** 
    *****************************/
    
    /**
      * Transform a pawn to a queen
      * @param pawn The concerned pawn 
    **/
    public Queen pawnToQueen(Pawn pawn) {
        Queen queen = new Queen(pawn.getPosition(), pawn.getPieceType());
        this.pieces.remove(pawn);
        this.pieces.add(queen);

        return queen;
    }
}