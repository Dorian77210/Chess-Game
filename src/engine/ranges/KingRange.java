package engine.ranges;

import helper.Position;
import helper.Assert;

import models.views.BoardModel;

import models.game.pieces.Piece;
import models.game.pieces.King;

import ui.board.Cell;

import engine.ranges.states.KingMovementStates;
import engine.Engine;

import java.util.ArrayList;
/**
  * The class <code>KingRange</code> represents the range for the king
  * @version 1.0
  * @author Dorian Terbah 
**/

public class KingRange {

    public static final KingMovementStates getkingMovementStates(BoardModel model, ArrayList<Cell> range, King king) {
        KingMovementStates states = new KingMovementStates();


        return states;
    }

    /**
      * Add a top cell for the range
      * @param model The model for the board
      * @param king The concerned king
      * @param states The king's movement states
      * @param range The current range
      * @param position The current position 
    **/
    public static void checkTopCells(BoardModel model, KingMovementStates states, ArrayList<Cell> range, King king, Position position) {
        Position kingPosition = king.getPosition();
        Cell cell = model.getTopCell(position);
        Piece piece;

        if(Assert.isSet(cell) && !states.isTopBlocked) {
            if(position.y == (kingPosition.y + 1)) {
                piece = model.getCell(position).getPiece();
                if((Assert.isSet(piece) && !piece.isSameTeamAs(king)) || cell.isEmpty()) {
                    range.add(cell);
                }
            } else {
                piece = model.getCell(position).getPiece();
                if(Assert.isSet(piece)) {
                    ArrayList<Cell> pieceRange = Engine.ranges.getAvailableRangeFor(model, piece);
                    if(pieceRange.contains(model.getCell(position))) {
                        states.isTopBlocked = true;
                    }
                }
            }
        }
    }
}