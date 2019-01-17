package ui.board;

import ui.board.Cell;

import ui.side.PieceCountView;

import controller.board.BoardController;

import engine.Engine;

import models.game.pieces.*;

import models.views.BoardModel;

import helper.Position;

import ui.views.View;
import ui.side.UndoRedoView;
import ui.side.LogView;

import enums.GameMode;

import helper.constants.Palette;
import helper.collections.CellCollection;
import helper.collections.PieceCollection;

import undo.UndoRedo;
import log.Log;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.BorderLayout;


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

    /**
      * The side where is displayed the count of pieces 
    **/
    private PieceCountView pieceCountView;

    /**
      * The undo/redo view 
    **/
    private UndoRedoView undoRedoView;

    /**
      * The log view 
    **/
    private LogView logView;

    /**
      * The controller of the board 
    **/
    private BoardController controller;

    public BoardView(GameMode mode) {
        super(new java.awt.BorderLayout());
        this.cells = new Cell[HEIGHT][WIDTH];

        this.model = new BoardModel(this.cells);

        this.controller = new BoardController(this, this.model);

        //generate the board (cells)
        Engine.initialize(mode, this.model);
        Engine.instance().initializeBoard(this.cells);

		//add the cells to the board
        this.displayCells();
        
        this.addActionListenerToCells();

        this.pieceCountView = new PieceCountView();
        this.add(this.pieceCountView, BorderLayout.WEST);

        this.undoRedoView = new UndoRedoView(this);
        this.add(this.undoRedoView, BorderLayout.SOUTH);

        this.logView = new LogView();
        this.add(this.logView, BorderLayout.EAST);
        //refresh stacks
        Log.instance().refreshStacks();
        UndoRedo.instance().refreshStacks();
    }

    /***************************** 
    ******APPEARANCE METHODS****** 
    *****************************/

	/**
	  * Add the cells to the board 
	**/
    private void displayCells() {
        JPanel panel = new JPanel(new GridLayout(WIDTH, HEIGHT));
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				panel.add(this.cells[y][x]);
			}
        }
        
        this.add(panel, BorderLayout.CENTER);
    }

    /**
      * Refresh the board 
    **/
    public void refreshBoard() {
        Cell cell;
        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++) {
                cell = this.cells[y][x];
                cell.refreshAppearance();
            }
        }
    }

    /**
      * Show the movement area of a piece
      * @param range The movement range
    **/
    public void showMovementRange(CellCollection range) {
        for(Cell cell : range) {
            cell.setBackground(Palette.RANGE_CELL_COLOR);
            cell.repaint();
        }
    }

    /**
      * Refresh the count of piece 
    **/
    public void refreshCounts() {
        //refresh counts
        this.pieceCountView.refreshCounts();
    }

    /***************************** 
    ****ACTIION LISTENER METHOD*** 
    *****************************/

    /**
      * Ask to the controller to add action listener to cells 
    **/
    private void addActionListenerToCells() {
        Cell cell;
        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++) {
                cell = this.cells[y][x];
                this.controller.addActionListenerTo(cell);
            }
        }
    }

    /***************************** 
    ************VERSION*********** 
    *****************************/
    /**
      * Change the version of the version
      * @param allPieces All of the pieces 
    **/
    public void changeVersion(PieceCollection allPieces) {
        this.refreshBoard();

        Cell cell;
        Position position;
        //delete all of the pieces
        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++) {
                cell = this.cells[y][x];
                cell.deletePiece();
            }
        }

        //set the new models
        for(Piece piece : allPieces) {
            position = piece.getPosition();
            cell = this.cells[position.y][position.x];
            cell.setPiece(piece);
            cell.refreshAppearance();
        }

        this.refreshCounts();
    }

    public void refreshDisplayLog() {
        this.logView.refreshDisplayLog();
    }
}