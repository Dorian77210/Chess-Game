package ui.side;

import enums.PlayerType;
import enums.KindOfPiece;

import models.views.side.PieceRepresentation;

import engine.Engine;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.GridLayout;

/**
  * The class <code>ItemView</code> represents the display of the count of one piece
  * @version 1.0
  * @author Dorian Terbah 
**/

public class ItemView extends JPanel {

    /**
      * The pice representation 
    **/
    private PieceRepresentation pieceRepresentation;

    /**
      * To represent the icon 
    **/
    private JLabel image;

    /**
      * To represent the count of the piece 
    **/
    private JLabel count;

    public ItemView(String image, KindOfPiece kind, PlayerType type) {
        super(new GridLayout(1, 2));
        this.pieceRepresentation = new PieceRepresentation(kind, type);

        this.image = new JLabel(new ImageIcon(image));
        this.count = new JLabel();

        //add the label
        this.add(this.image);
        this.add(this.count);

        this.refreshCount();
    }

    /**
      * Refresh the count  
    **/
    public void refreshCount() {
        this.count.setText("  X" + Engine.pieceCounter.getCountOf(this.pieceRepresentation.getKindOfPiece(), this.pieceRepresentation.getPlayerType()));
    }
}