package ui.board;

import ui.board.Cell;

import controller.views.BoardController;

import models.game.pieces.*;

import models.views.BoardModel;

import enums.GameMode;

import javax.swing.JPanel;

import java.awt.GridLayout;

/**
  * The class <code>BoardView</code> represents the game
  * @version 1.0
  * @author Dorian Terbah 
**/

public class BoardView extends JPanel {

    /**
      * Constant used to describe the width of the board 
    **/
    public static final int WIDTH = 8;  

    /**
      * Constant used to dexcribe the height of the board 
    **/
    public static final int HEIGHT = 8;

    /**
      * All cases of the board 
    **/
    private Cell[][] cells; 

    /**
      * Model of the board 
    **/
    private BoardModel model;

    public BoardView(GameMode mode) {
        super(new GridLayout(WIDTH, HEIGHT));
        this.cells = new Cell[HEIGHT][WIDTH];

        this.model = new BoardModel(this.cells);

        //generate the board (cells)
        Engine.instance().initialize(mode);
        Engine.instance().generateBoard(this.cells);
    }
}