package engine.ranges;

import models.views.BoardModel;

import models.game.pieces.Rook;

import ui.board.Cell;

import helper.Position;
import helper.Assert;

import engine.ranges.states.RookMovementStates;

import java.util.ArrayList;

/**
  * The class <code>BishopRange</code> gives the range for the bishôp 
  * @version 1.0
  * @author Dorian Terbah 
**/

public class RookRange {

    /**
      * Add a top cell for the range
      * @param model The modle for the board
      * @param bishop The concerned bishop
      * @param states The bishop's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addTopCell(BoardModel model, Rook rook, RookMovementStates states, ArrayList<Cell> range, Position position) {
        Cell cell = model.getTopCell(position);
        if(!states.isTopBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addTopCell(model, rook, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(rook)) {
                        range.add(cell);
                    }

                    states.isTopBlocked = true;
                }
            } else {
                states.isTopBlocked = true;
            }
        }
    }

    /**
      * Add a right cell for the range
      * @param model The modle for the board
      * @param bishop The concerned bishop
      * @param states The bishop's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addRightCell(BoardModel model, Rook rook, RookMovementStates states, ArrayList<Cell> range, Position position) {
        Cell cell = model.getRightCell(position);
        if(!states.isRightBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addRightCell(model, rook, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(rook)) {
                        range.add(cell);
                    }

                    states.isRightBlocked = true;
                }
            } else {
                states.isRightBlocked = true;
            }
        }
    }

    /**
      * Add a top cell for the range
      * @param model The modle for the board
      * @param bishop The concerned bishop
      * @param states The bishop's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addBottomCell(BoardModel model, Rook rook, RookMovementStates states, ArrayList<Cell> range, Position position) {
        Cell cell = model.getBottomCell(position);
        if(!states.isBottomBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addBottomCell(model, rook, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(rook)) {
                        range.add(cell);
                    }

                    states.isBottomBlocked = true;
                }
            } else {
                states.isBottomBlocked = true;
            }
        }
    }

    /**
      * Add a left cell for the range
      * @param model The modle for the board
      * @param bishop The concerned bishop
      * @param states The bishop's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addLeftCell(BoardModel model, Rook rook, RookMovementStates states, ArrayList<Cell> range, Position position) {
        Cell cell = model.getLeftCell(position);
        if(!states.isLeftBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addLeftCell(model, rook, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(rook)) {
                        range.add(cell);
                    }

                    states.isLeftBlocked = true;
                }
            } else {
                states.isLeftBlocked = true;
            }
        }
    }
}