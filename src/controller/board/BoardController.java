package controller.board;

import ui.board.BoardView;
import ui.board.Cell;

import models.game.pieces.Piece;

import models.views.BoardModel;

import helper.constants.Palette;
import helper.Assert;

import engine.Engine;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
  * The class <code>BoardController</code> controls the actions on the cells
  * @version 1.0
  * @author Dorian Terbah 
**/

public class BoardController implements ActionListener {

    /**
      * The representation of the game 
    **/
    private BoardView boardView;

    /**
      * The model of the board 
    **/
    private BoardModel boardModel;

    public BoardController(BoardView boardView, BoardModel boardModel) {
        this.boardView = boardView;
        this.boardModel = boardModel;
    }

    /**
      * React to an button's event
      * @param event The actual event
    **/
    @Override 
    public void actionPerformed(ActionEvent event) {
        //refresh the board
        this.boardView.refreshBoard();
        Cell cell = (Cell)event.getSource();
        cell.setBackground(Palette.SELECTED_CELL_COLOR);

        Piece piece = cell.getPiece();
        if(Assert.isSet(piece) && (piece.canMove())) {
            this.boardView.showMovementRange(Engine.ranges.getAvailableRangeFor(this.boardModel, piece));
        }

        this.boardModel.setSelectedCell(cell);
    }   

    /**
      * Add the action listener to a cell
      * @param cell The concerned cell 
    **/
    public void addActionListenerTo(Cell cell) {
        cell.addActionListener(this);
    }
}