package models.game.pieces;

import helper.Position;

import ui.board.Cell;

import enums.PieceType;

import models.game.pieces.Piece;

/**
  * The class <code>Knight</code> represents a rook in the pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Knight extends Piece {

    /**
	  * Constant used to represent the image of the rook 
	**/
    private static final String KNIGHT_REPRESENTATION = "knight.png";

    /**
      * Constant used to represent the distance of movement for a knight 
    **/
    public static final double KNIGHT_MOVEMENT_DISTANCE = Math.sqrt(5);

    public Knight(Position position, PieceType type) {
        super(position, type);
        this.representation = Piece.BASE_PATH;
        this.representation += (this.isBlackPiece) ? "black-side/" + KNIGHT_REPRESENTATION : "white-side/" + KNIGHT_REPRESENTATION;
    }

    public Knight(Knight knight) {
        super(knight);
    }
}