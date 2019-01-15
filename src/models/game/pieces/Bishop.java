package models.game.pieces;

import helper.Position;

import ui.board.Cell;

import enums.PieceType;

import models.game.pieces.Piece;

/**
  * The class <code>Bishop</code> represents a bishop in the pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Bishop extends Piece {

    /**
	  * Constant used to represent the image of the rook 
	**/
    private static final String BISHOP_REPRESENTATION = "bishop.png";

    public Bishop(Position position, PieceType type) {
        super(position, type);
        this.representation = Piece.BASE_PATH;
        this.representation += (this.isBlackPiece) ? "black-side/" + BISHOP_REPRESENTATION : "white-side/" + BISHOP_REPRESENTATION;

        this.classInfo = "Bishop";
    }

    public Bishop(Bishop bishop) {
        super(bishop);
    }
}