package models.views;

import ui.board.Cell;

import helper.Position;

import enums.GameMode;

import ui.board.BoardView;

/**
  * The class <code>BoardModel</code> represents the model of the board
  * @version 1.0
  * @author Dorian Terbah 
**/

public class BoardModel {

    /**
      * The cells that compose the board 
    **/
    private Cell[][] cells;

    /**
      * The current selected cell 
    **/
    private Cell selectedCell;

    public BoardModel(Cell[][] cells) {
        this.cells = cells;
        //default value
        this.selectedCell = null;
    }

    /***************************** 
    *************GETTER*********** 
    *****************************/

    /**
      * Get cell according to a position
      * @param position The current position
      * @return The cell associated to the position 
    **/
    public Cell getCell(Position position) {
        return this.cells[position.y][position.x];
    }

    /**
      * Get the top cell according to a position
      * @param position The current position
      * @return The top cell associated to the position
    **/
    public Cell getTopCell(Position position) {
        return (position.y > 0) ? this.getCell(new Position(position.x, position.y - 1)) : null;
    }

    /** 
      * Get the right top cell according to a position
      * @param position The current position
      * @return The top right cell associated to the position
    **/
    public Cell getTopRightCell(Position position) {
        return ((position.y > 0) && (position.x < BoardView.WIDTH)) ? this.getCell(new Position(position.x + 1, position.y - 1)) : null;
    }

    /** 
      * Get the right cell according to a position
      * @param position The current position
      * @return The top right cell associated to the position
    **/
    public Cell getRightCell(Position position) {
        return (position.x < BoardView.WIDTH) ? this.getCell(new Position(position.x + 1, position.y)) : null;
    }

    /** 
      * Get the bottom right cell according to a position
      * @param position The current position
      * @return The top right cell associated to the position
    **/
    public Cell getBottomRightCell(Position position) {
        return ((position.y < (BoardView.HEIGHT - 1)) && (position.x < BoardView.WIDTH)) ? this.getCell(new Position(position.x + 1, position.y + 1)) : null;
    }

    /** 
      * Get the bottom cell according to a position
      * @param position The current position
      * @return The top right cell associated to the position
    **/
    public Cell getBottomCell(Position position) {
        return (position.y < (BoardView.HEIGHT - 1)) ? this.getCell(new Position(position.x, position.y + 1)) : null;
    }

    /** 
      * Get the left bottom cell according to a position
      * @param position The current position
      * @return The top right cell associated to the position
    **/
    public Cell getBottomLeftCell(Position position) {
        return ((position.x > 0) && (position.y < (BoardView.HEIGHT - 1))) ? this.getCell(new Position(position.x - 1, position.y + 1)) : null;
    }

    /** 
      * Get the left top cell according to a position
      * @param position The current position
      * @return The top right cell associated to the position
    **/
    public Cell getTopLeftCell(Position position) {
        return ((position.x > 0) && (position.y > 0)) ? this.getCell(new Position(position.x - 1, position.y - 1)) : null;
    }

    /***************************** 
    *************SETTER*********** 
    *****************************/

    /**
      * Update the selected cell
      * @param selectedCell The new selected cell 
    **/
    public void setSelectedCell(Cell selectedCell) {
        this.selectedCell = selectedCell;
    }
}
