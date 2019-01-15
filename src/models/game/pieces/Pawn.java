package models.game.pieces;

import helper.Position;

import ui.board.Cell;

import enums.PieceType;

import models.game.pieces.Piece;

/**
  * The class <code>Pawn</code> represents a pawn in the pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Pawn extends Piece {

    /**
	  * Constant used to represent the image of the pawn 
	**/
    private static final String PAWN_REPRESENTATION = "pawn.png";

    public Pawn(Position position, PieceType type) {
        super(position, type);
        this.representation = Piece.BASE_PATH;
        this.representation += (this.isBlackPiece) ? "black-side/" + PAWN_REPRESENTATION : "white-side/" + PAWN_REPRESENTATION;

        this.classInfo = "Pawn";
    }

    public Pawn(Pawn pawn) {
        super(pawn);
    }
}