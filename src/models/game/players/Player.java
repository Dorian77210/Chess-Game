package models.game.players;

import models.game.pieces.Piece;

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