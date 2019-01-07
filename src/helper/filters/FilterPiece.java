package helper.filters;

import helper.Position;

import ui.board.Cell;

import models.views.BoardModel;

import models.game.pieces.Piece;
import models.game.pieces.King;

import engine.Engine;

import java.util.ArrayList;

/**
  * The class <code>FilterPiece</code> filter pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class FilterPiece {

    public FilterPiece() {

    }

    /**
      * Filter a range for a piece
      * @param range The current range to filter
      * @param collidePieces The pieces which collide with the filter's piece 
      * @param king The current king
      * @param model The model of the board
    **/
    public static final void filterRange(ArrayList<Cell> range, King king, ArrayList<Piece> collidePieces, BoardModel model) {
        ArrayList<Cell> collideRange;
        Cell kingCell = model.getCell(king.getPosition());
        Cell cell;

        for(Piece collidePiece : collidePieces) {
            for(int i = 0; i < range.size(); i++) {
                if(!(collidePiece instanceof King)) {
                    cell = range.get(i);
                    Piece temp = cell.getPiece();
                    cell.setPiece(king);
                    collideRange = Engine.ranges.getAvailableRangeFor(model, collidePiece);
                    if(collideRange.contains(cell)) {
                        range.remove(cell);
                    }

                    cell.setPiece(temp);
                }
            }
        }

        kingCell.setPiece(king);
    }
    
    public static final void filterRange(ArrayList<Cell> range, ArrayList<Piece> pieces, BoardModel model) {
        ArrayList<Cell> collideRange;

        for(Piece piece : pieces) {
            for(Cell cell : range) {
                collideRange = Engine.ranges.getAvailableRangeFor(model, piece);
                if(!collideRange.contains(cell)) {
                    range.remove(cell);
                }
            }
        }
    }
}