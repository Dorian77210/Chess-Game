package models.game.players;

import models.game.pieces.Piece;
import models.game.pieces.King;

import enums.PlayerType;

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
    private ArrayList<Piece> pieces;

    /**
      * The type of the player 
    **/
    private PlayerType type;

    public Player(PlayerType type, ArrayList<Piece> pieces) {
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
    public ArrayList<Piece> getPieces() {
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
}