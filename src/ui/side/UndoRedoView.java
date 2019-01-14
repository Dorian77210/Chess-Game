package ui.side;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.Dimension;

import controller.side.UndoRedoController;

import ui.board.BoardView;

/**
  * The class <code>UndoRedoview</code> enables the players to makeundo and redo
  * @version 1.0
  * @author Dorian Terbah 
**/

public class UndoRedoView extends JPanel {

    /**
      * The undo button 
    **/
    private JButton undo;

    /**
      * The redo button 
    **/
    private JButton redo;

    /**
      * His controller 
    **/
    private UndoRedoController controller;

    public UndoRedoView(BoardView boardView) {
        super(new FlowLayout(FlowLayout.CENTER));

        //create the buttons
        this.undo = new JButton("UNDO");
        this.redo = new JButton("REDO");

        this.add(this.undo);
        this.add(this.redo);

        //create the controller
        this.controller = new UndoRedoController(boardView, this.undo, this.redo);
    }
}