package engine.ranges;

import models.views.BoardModel;

import models.game.pieces.Queen;

import ui.board.Cell;

import helper.Position;
import helper.Assert;
import helper.collections.CellCollection;

import engine.ranges.states.QueenMovementStates;

/**
  * The class <code>QueenRange</code> gives the range for the queen 
  * @version 1.0
  * @author Dorian Terbah 
**/

public class QueenRange {

    /**
      * Add a top cell for the range
      * @param model The model for the board
      * @param queen The concerned queen
      * @param states The queen's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addTopCell(BoardModel model, Queen queen, QueenMovementStates states, CellCollection range, Position position) {
        Cell cell = model.getTopCell(position);
        if(!states.isTopBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addTopCell(model, queen, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(queen)) {
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
      * @param model The model for the board
      * @param queen The concerned queen
      * @param states The queen's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addRightCell(BoardModel model, Queen queen, QueenMovementStates states, CellCollection range, Position position) {
        Cell cell = model.getRightCell(position);
        if(!states.isRightBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addRightCell(model, queen, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(queen)) {
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
      * @param model The model for the board
      * @param queen The concerned queen
      * @param states The queen's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addBottomCell(BoardModel model, Queen queen, QueenMovementStates states, CellCollection range, Position position) {
        Cell cell = model.getBottomCell(position);
        if(!states.isBottomBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addBottomCell(model, queen, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(queen)) {
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
      * @param model The model for the board
      * @param queen The concerned queen
      * @param states The queen's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addLeftCell(BoardModel model, Queen queen, QueenMovementStates states, CellCollection range, Position position) {
        Cell cell = model.getLeftCell(position);
        if(!states.isLeftBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addLeftCell(model, queen, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(queen)) {
                        range.add(cell);
                    }

                    states.isLeftBlocked = true;
                }
            } else {
                states.isLeftBlocked = true;
            }
        }
    }

    /**
      * Add a top left cell for the range
      * @param model The model for the board
      * @param queen The concerned queen
      * @param states The queen's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addTopLeftCell(BoardModel model, Queen queen, QueenMovementStates states, CellCollection range, Position position) {
        Cell cell = model.getTopLeftCell(position);
        if(!states.isLeftTopBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addTopLeftCell(model, queen, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(queen)) {
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
      * @param model The model for the board
      * @param queen The concerned queen
      * @param states The queen's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addTopRightCell(BoardModel model, Queen queen, QueenMovementStates states, CellCollection range, Position position) {
        Cell cell = model.getTopRightCell(position);
        if(!states.isRightTopBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addTopRightCell(model, queen, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(queen)) {
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
      * @param model The model for the board
      * @param queen The concerned queen
      * @param states The queen's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addBottomLeftCell(BoardModel model, Queen queen, QueenMovementStates states, CellCollection range, Position position) {
        Cell cell = model.getBottomLeftCell(position);
        if(!states.isLeftBottomBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addBottomLeftCell(model, queen, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(queen)) {
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
      * @param model The model for the board
      * @param queen The concerned queen
      * @param states The queen's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void addBottomRightCell(BoardModel model, Queen queen, QueenMovementStates states, CellCollection range, Position position) {
        Cell cell = model.getBottomRightCell(position);
        if(!states.isRightBottomBlocked) {
            if(Assert.isSet(cell)) {
                if(cell.isEmpty()) {
                    range.add(cell);
                    addBottomRightCell(model, queen, states, range, cell.getPosition());
                } else {
                    if(!cell.getPiece().isSameTeamAs(queen)) {
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