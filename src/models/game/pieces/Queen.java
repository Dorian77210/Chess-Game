package models.game.pieces;

import helper.Position;

import ui.board.Cell;

import enums.PieceType;

import models.game.pieces.Piece;

/**
  * The class <code>Queen</code> represents a queen in the pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Queen extends Piece {

    /**
	  * Constant used to represent the image of the queen 
	**/
    private static final String QUEEN_REPRESENTATION = "queen.png";

    public Queen(Position position, PieceType type) {
        super(position, type);
        this.representation = Piece.BASE_PATH;
        this.representation += (this.isBlackPiece) ? "black-side/" + QUEEN_REPRESENTATION : "white-side/" + QUEEN_REPRESENTATION;

        this.classInfo = "Queen";
    }

    public Queen(Queen queen) {
        super(queen);
    }

    /***************************** 
    ************CLONE************* 
    *****************************/

    /**
      * Get a clone object of the piece
      * @return The clone of this object 
    **/
    public Queen clone() {
        return new Queen(this);
    }
}