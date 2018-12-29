package ui.board;

import ui.board.Cell;

//import controller.views.BoardController;

import engine.Engine;

import models.game.pieces.*;

import models.views.BoardModel;

import ui.views.View;

import enums.GameMode;

import javax.swing.JPanel;

import java.awt.GridLayout;
/**
  * The class <code>BoardView</code> represents the game
  * @version 1.0
  * @author Dorian Terbah 
**/

public class BoardView extends View {

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
        Engine.initialize(mode, this.model);
        Engine.instance().initializeBoard(this.cells);

		//add the cells to the board
		this.displayCells();
    }

	/**
	  * Add the cells to the board 
	**/
    private void displayCells() {
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				this.add(this.cells[y][x]);
			}
		}
    }
}