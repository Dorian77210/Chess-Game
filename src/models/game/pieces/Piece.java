package models.game.pieces;

import helper.Position;

import ui.board.Cell;

import enums.PieceType;

/**
  * The class <code>Piece</code> represents the base of all of the pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Piece {

    protected static final String BASE_PATH = "./rsc/images/"; 

    /**
      * The position of the piece in the board 
    **/
    protected Position position;

    /**
      * The type of the piece 
    **/
    protected PieceType type;

    /**
      * A boolean to describe the team of the piece 
    **/
    protected boolean isBlackPiece; 

    /**
      * The path of the image 
    **/
    protected String representation;

    public Piece(Position position, PieceType type) {
        this.position = position;
        this.type = type;

        this.isBlackPiece = type.equals(PieceType.BLACK_PIECE);
    }

    /***************************** 
    *************GETTER*********** 
    *****************************/

    /**
      * Check if the piece is a black piece
      * @return True if it is, else false 
    **/
    public boolean isBlackPiece() {
        return this.isBlackPiece;
    }

    /**
      * Check if the piece is a white piece
      * @return True if it is, else false
    **/
    public boolean isWhitePiece() {
        return !this.isBlackPiece;
    }

    /**
      * Get the path of the representation of the piece
      * @return The path 
    **/
    public String getRepresentation() {
        return this.representation;
    }

    /**
      * Get the position of the piece in the board
      * @return The position of the piece int the board 
    **/
    public Position getPosition() {
        return this.position;
    }

    /***************************** 
    *************MOVE************* 
    *****************************/

    /**
      * Move the picece to a new position
      * @param position The new position of the piece 
    **/
    public void move(Position position) {
        this.position = position;
    }


}