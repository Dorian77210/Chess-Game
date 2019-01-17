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

    /**
      * Boolean to verify if the king was already in checked 
    **/
    private boolean wasAlreadyChecked;

	public King(Position position, PieceType type) {
		super(position, type);
		this.representation = Piece.BASE_PATH;
        this.representation += (this.isBlackPiece) ? "black-side/" + KING_REPRESENTATION : "white-side/" + KING_REPRESENTATION; 
        
        //defautl value
        this.wasAlreadyChecked = false;

        this.classInfo = "King";
    }

    public King(King king) {
        super(king);
    }
    
    /*****************************
    *************GETTER***********
    *****************************/
    /**
      * Get if the king was already in checked
      * @param True if the king was already checked, else false 
    **/
    public boolean wasAlreadyChecked() {
        return this.wasAlreadyChecked;
    }

    /***************************** 
    *************SETTER*********** 
    *****************************/
    /**
      * Toggle the wasAlreadyChecked state
    **/
    public void toggleWasAlreadyChecked() {
        this.wasAlreadyChecked = !this.wasAlreadyChecked;
    }

    /***************************** 
    ************CLONE************* 
    *****************************/

    /**
      * Get a clone object of the piece
      * @return The clone of this object 
    **/
    public King clone() {
        return new King(this);
    }
}