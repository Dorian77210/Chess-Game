package helper.collide;

import models.game.pieces.Piece;
import models.game.pieces.King;

import models.views.BoardModel;

import engine.Engine;

import ui.board.Cell;

import helper.Position;

import java.util.ArrayList;

/**
  * The class <code>PieceCollision</code> checks the collisions between pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class PieceCollision {
    
    public PieceCollision() {

    }

    /**
      * Get the pieces which collide with a piece
      * @param kingRange The currentking range
      * @param pieces The others pieces
      * @param king The current king
      * @param model The model of the game
      * @return All pieces which collide with the piece
    **/
    public static final ArrayList<Piece> getPiecesCollideWith(ArrayList<Cell> kingRange, King king, ArrayList<Piece> pieces, BoardModel model) {
        ArrayList<Piece> result = new ArrayList<Piece>();
        ArrayList<Cell> range;
        Cell kingCell = model.getCell(king.getPosition());
        Cell cell;

        for(Piece piece : pieces) {
            if(!(piece instanceof King)) {
                for(int i = 0; i < kingRange.size(); i++) {
                    cell = kingRange.get(i);
                    Piece p = cell.getPiece();
                    cell.setPiece(king);
                    range = Engine.ranges.getAvailableRangeFor(model, piece);
                    if(range.contains(cell)) {
                        System.out.println("ok");
                        kingRange.remove(cell);
                        result.add(piece);
                        i = 0;
                    }

                    cell.setPiece(p);
                }

            }
        }

        kingCell.setPiece(king);
        return result;
    }
}