package ui.side;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.FlowLayout;

import controller.side.SaveGameController;

import ui.board.BoardView;

/**
  * The class <code>SaveGameView</code> represents the north viw of the board view
  * @version 1.0
  * @author Dorian Terbah 
**/

public class SaveGameView extends JPanel {

    private SaveGameController controller;

    private JButton saveButton;

    public SaveGameView(BoardView boardView) {
        super(new FlowLayout(FlowLayout.CENTER));
        //create the button
        this.saveButton = new JButton("Save game and exit");
        this.add(this.saveButton);

        this.controller = new SaveGameController(this.saveButton, boardView);
    }

}