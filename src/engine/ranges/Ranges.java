package engine.ranges;

import models.game.pieces.*;

import models.views.BoardModel;

import ui.board.*;

import helper.Assert;
import helper.Distance;
import helper.Position;

import java.util.ArrayList;

/**
  * The class <code>Ranges</code> fives the movements range for all pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Ranges {

    public Ranges() {

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

        return null;
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
        ArrayList<Cell> cells = new ArrayList<Cell>();
        Cell cell;

        for(int y = 0; y < BoardView.HEIGHT; y++) {
            for(int x = 0;x < BoardView.WIDTH; x++) {
                currentPosition = new Position(x, y);
                if(Distance.getDistance(position, currentPosition) == Knight.KNIGHT_MOVEMENT_DISTANCE) {
                    cell = model.getCell(currentPosition);
                    if(cell.isEmpty() || (!cell.isEmpty() && (!cell.getPiece().isSameTeamAs(knight)))) { 
                        cells.add(model.getCell(currentPosition));
                    }
                }
            }
        }

        return cells;
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

        return null;
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