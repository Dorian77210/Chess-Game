package models.game.pieces;

import helper.Position;

import ui.board.Cell;

import enums.PieceType;

import models.game.pieces.Piece;

/**
  * The class <code>King</code> represents the king in the pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class King extends Piece {

	/**
	  * Constant used to represent the image of the king 
	**/
	private static final String KING_REPRESENTATION = "king.png";

	public King(Position position, PieceType type) {
		super(position, type);
		this.representation = Piece.BASE_PATH;
		this.representation += (this.isBlackPiece) ? "black-side/" + KING_REPRESENTATION : "white-side/" + KING_REPRESENTATION; 
	}
}