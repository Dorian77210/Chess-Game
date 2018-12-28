package models.game.pieces;

import helper.Position;

import ui.board.Cell;

import enums.PieceType;

import models.game.pieces.Piece;

/**
  * The class <code>Rook</code> represents a rook in the pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Rook extends Piece {

    /**
	  * Constant used to represent the image of the rook 
	**/
    private static final String ROOK_REPRESENTATION = "rook.png";

    public Rook(Position position, PieceType type) {
        super(position, type);
        this.representation = Piece.BASE_PATH;
        this.representation += (this.isBlackPiece) ? "black-side/" + ROOK_REPRESENTATION : "white-side/" + ROOK_REPRESENTATION;
    }
}