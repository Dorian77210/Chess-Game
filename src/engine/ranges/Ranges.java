package engine.ranges;

import models.game.pieces.*;

import models.views.BoardModel;

import ui.board.*;

import helper.Assert;
import helper.Distance;
import helper.Position;

import engine.ranges.states.BishopMovementStates;
import engine.ranges.BishopRange;

import java.util.ArrayList;

/**
  * The class <code>Ranges</code> fives the movements range for all pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Ranges {

    /**
      * List used for the ranges 
    **/
    private ArrayList<Cell> range;

    public Ranges() {
        this.range = new ArrayList<Cell>();
    }

    /**
      * Get the available range for a piece
      * @param piece The concerned piece
      * @param model The model of the board
      * @return The available range for the piece 
    **/
    public ArrayList<Cell> getAvailableRangeFor(BoardModel model, Piece piece) {
        if(piece instanceof Bishop) {
            return getAvailableRangeFor(model, (Bishop)piece);
        } else if(piece instanceof Knight) {
            return getAvailableRangeFor(model, (Knight)piece);
        } else if(piece instanceof King) {
            return getAvailableRangeFor(model, (King)piece);
        } else if(piece instanceof Queen) {
            return getAvailableRangeFor(model, (Queen)piece);
        } else if(piece instanceof Pawn) {
            return getAvailableRangeFor(model, (Pawn)piece);
        } else {
            return getAvailableRangeFor(model, (Rook)piece);
        }
    }

    /**
      * Get the available range for a bishop
      * @param bishop The concerned piece
      * @param model The model of the board
      * @return The available range for the bishop 
    **/
    private ArrayList<Cell> getAvailableRangeFor(BoardModel model, Bishop bishop) {
        this.range.clear();

        Position position = bishop.getPosition();

        BishopMovementStates states = new BishopMovementStates();

        BishopRange.addTopLeftCell(model, bishop, states, this.range, bishop.getPosition());
        BishopRange.addTopRightCell(model, bishop, states, this.range, bishop.getPosition());
        BishopRange.addBottomRightCell(model, bishop, states, this.range, bishop.getPosition());
        BishopRange.addBottomLeftCell(model, bishop, states, this.range, bishop.getPosition());

        return this.range;
    }

    /**
      * Get the available range for a bishop
      * @param knight The concerned piece
      * @param model The model of the board
      * @return The available range for the kngith 
    **/
    private ArrayList<Cell> getAvailableRangeFor(BoardModel model, Knight knight) {
        Position position = knight.getPosition();
        Position currentPosition = null;
        this.range.clear();
        Cell cell;

        for(int y = 0; y < BoardView.HEIGHT; y++) {
            for(int x = 0; x < BoardView.WIDTH; x++) {
                currentPosition = new Position(x, y);
                if(Distance.getDistance(position, currentPosition) == Knight.KNIGHT_MOVEMENT_DISTANCE) {
                    cell = model.getCell(currentPosition);
                    if(cell.isEmpty() || (!cell.isEmpty() && (!cell.getPiece().isSameTeamAs(knight)))) { 
                        this.range.add(model.getCell(currentPosition));
                    }
                }
            }
        }

        return this.range;
    }

    /**
      * Get the available range for a bishop
      * @param king The concerned piece
      * @param model The model of the board
      * @return The available range for the king 
    **/
    private ArrayList<Cell> getAvailableRangeFor(BoardModel model, King king) {

        return null;
    }

    /**
      * Get the available range for a bishop
      * @param queen The concerned piece
      * @param model The model of the board
      * @return The available range for the queen 
    **/
    private ArrayList<Cell> getAvailableRangeFor(BoardModel model, Queen queen) {

        return null;
    }

    /**
      * Get the available range for a bishop
      * @param pawn The concerned piece
      * @param model The model of the board
      * @return The available range for the pawn 
    **/
    private ArrayList<Cell> getAvailableRangeFor(BoardModel model, Pawn pawn) {
        this.range.clear();
        Position position = pawn.getPosition();
        Cell currentCell = null;
        int direction = (pawn.isBlackPiece()) ?  1 : -1;

        //add the cells

        //cell in front of the pawn
        currentCell = model.getCell(new Position(position.x, position.y + direction));
        if(Assert.isSet(currentCell) && currentCell.isEmpty()) {
            this.range.add(currentCell);
        }   

        //cellin front of the pawn + 1
        if(pawn.isFirstTimeMoving()) {
            currentCell = model.getCell(new Position(position.x, position.y + (2 * direction)));
            if(Assert.isSet(currentCell) && currentCell.isEmpty()) {
                this.range.add(currentCell);
            }
        }

        currentCell = model.getCell(new Position(position.x - 1, position.y + direction));
        if(Assert.isSet(currentCell) && !currentCell.isEmpty() && !currentCell.getPiece().isSameTeamAs(pawn)) {
            this.range.add(currentCell);
        }

        currentCell = model.getCell(new Position(position.x + 1, position.y + direction));
        if(Assert.isSet(currentCell) && !currentCell.isEmpty() && !currentCell.getPiece().isSameTeamAs(pawn)) {
            this.range.add(currentCell);
        }

        return this.range;
    }

    /**
      * Get the available range for a rook
      * @param bishop The concerned piece
      * @param model The model of the board
      * @return The available range for the rook 
    **/
    private ArrayList<Cell> getAvailableRangeFor(BoardModel model, Rook rook) {

        return null;
    }

}