package engine.ranges;

import models.views.BoardModel;

import models.game.pieces.Bishop;

import ui.board.Cell;

import helper.Position;
import helper.Assert;

import engine.ranges.states.BishopMovementStates;

import java.util.ArrayList;

/**
  * The class <code>BishopRange</code> gives the range for the bishôp 
  * @version 1.0
  * @author Dorian Terbah 
**/

public class BishopRange {

    /**
      * Add a top left cell for the range
      * @param model The modle for the board
      * @param bishop The concerned bishop
      * @param states The bishop's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addTopLeftCell(BoardModel model, Bishop bishop, BishopMovementStates states, ArrayList<Cell> range, Position position) {
        Cell cell = model.getTopLeftCell(position);
        if(!states.isLeftTopBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addTopLeftCell(model, bishop, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(bishop)) {
                        range.add(cell);
                    }

                    states.isLeftTopBlocked = true;
                }
            } else {
                states.isLeftTopBlocked = true;
            }
        }
    }

    /**
      * Add a top right cell for the range
      * @param model The modle for the board
      * @param bishop The concerned bishop
      * @param states The bishop's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addTopRightCell(BoardModel model, Bishop bishop, BishopMovementStates states, ArrayList<Cell> range, Position position) {
        Cell cell = model.getTopRightCell(position);
        if(!states.isRightTopBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addTopRightCell(model, bishop, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(bishop)) {
                        range.add(cell);
                    }

                    states.isRightTopBlocked = true;
                }
            } else {
                states.isRightTopBlocked = true;
            }
        }
    }

    /**
      * Add a bottom left cell for the range
      * @param model The modle for the board
      * @param bishop The concerned bishop
      * @param states The bishop's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addBottomLeftCell(BoardModel model, Bishop bishop, BishopMovementStates states, ArrayList<Cell> range, Position position) {
        Cell cell = model.getBottomLeftCell(position);
        if(!states.isLeftBottomBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addBottomLeftCell(model, bishop, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(bishop)) {
                        range.add(cell);
                    }

                    states.isLeftBottomBlocked = true;
                }
            } else {
                states.isLeftBottomBlocked = true;
            }
        }
    }

    /**
      * Add a bottom right cell for the range
      * @param model The modle for the board
      * @param bishop The concerned bishop
      * @param states The bishop's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addBottomRightCell(BoardModel model, Bishop bishop, BishopMovementStates states, ArrayList<Cell> range, Position position) {
        Cell cell = model.getBottomRightCell(position);
        if(!states.isRightBottomBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addBottomRightCell(model, bishop, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(bishop)) {
                        range.add(cell);
                    }

                    states.isRightBottomBlocked = true;
                }
            } else {
                states.isRightBottomBlocked = true;
            }
        }
    }
}