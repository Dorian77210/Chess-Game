package ui.board;

import models.game.pieces.*;

import helper.Assert;
import helper.Position;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

/**
  * The class <code>Cell</code> represents a cell of the board
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Cell extends JButton {

    /**
      * The potential piece in this cell
    **/
    private Piece piece;

    /**
      * The initial color of the cell 
    **/
    private Color initialColor;

    /**
      * The position of the cell in the board 
    **/
    private Position position;

    public Cell(Color color, Position position) {
        this.initialColor = color;
        this.position = position;
        this.piece = null; //initial value

        this.setBackground(this.initialColor);
    }

    public Cell(Color color, Position position, Piece piece) {
        this.initialColor = color;
        this.position = position;
        this.piece = piece;

        this.setBackground(this.initialColor);
    }

    /**
      * Refresh the appearance of the cell 
    **/
    public void refreshAppearance() {
        this.setBackground(this.initialColor);
        ImageIcon icon = (Assert.isNull(this.piece)) ? null : new ImageIcon(this.piece.getRepresentation());
        this.setIcon(icon);
    }

    /***************************** 
    *************SETTER*********** 
    *****************************/

    /**
      * Set the model of the cell
      * @param piece The new model for the cell
    **/
    public void setPiece(Piece piece) {
        this.piece = piece;
        if(Assert.isSet(this.piece)) { 
            this.piece.move(this.position);
        }

        this.refreshAppearance();
    }

    /**
      * Delete the current model of the cell 
    **/
    public void deletePiece() {
        this.piece = null;
        this.refreshAppearance();
    }

    /***************************** 
    *************GETTER*********** 
    *****************************/

    /**
      * Get the model of the cell
      * @return Null if it is null, else the model 
    **/
    public Piece getPiece() {
        return this.piece;
    }

    /**
      * Get the position of the cell in the board
      * @return The position of the cell in the board 
    **/
    public Position getPosition() {
        return this.position;
    }

    /**
      * Get the empty state of the cell
      * @return True if the cell hasn't piece model, else false 
    **/
    public boolean isEmpty() {
        return (this.piece == null);
    }

}
