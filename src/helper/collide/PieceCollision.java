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
    /*public static final ArrayList<Piece> getPiecesCollideWith(ArrayList<Cell> kingRange, King king, ArrayList<Piece> pieces, BoardModel model) {
        ArrayList<Piece> result = new ArrayList<Piece>();
        ArrayList<Cell> range;
        Cell kingCell = model.getCell(king.getPosition());
        Cell cell;

        for(Piece piece : pieces) {
            for(int i = 0; i < kingRange.size(); i++) {
                if(!(piece instanceof King)) {
                    cell = kingRange.get(i);
                    Piece temp = cell.getPiece();
                    cell.setPiece(king);
                  //  range = Engine.ranges.getAvailableRangeFor(model, piece);
                    if(range.contains(cell)) {
                        result.add(piece);
                    }

                    cell.setPiece(temp);
                }
            }
        }

        kingCell.setPiece(king);
        return result;
    }

    /**
      * Get the pieces with collide direct with the king
      * @param kingRange The range of the king
      * @param king The concerned king
      * @param The others pieces
      * @param model The model of the board
      * @return The pieces which collide with king 
    **/
   /* public static final ArrayList<Piece> getPiecesDirectCollideWith(ArrayList<Cell> kingRange, King king, ArrayList<Piece> pieces, BoardModel model) {
        ArrayList<Piece> result = new ArrayList<Piece>();
        ArrayList<Cell> range;

        for(Piece piece : pieces) {
            for(Cell cell : kingRange) {
                if(!(piece instanceof King)) {
                  //  range = Engine.ranges.getAvailableRangeFor(model, piece);
                    if(range.contains(cell)) {
                        result.add(piece);
                    }
                }
            }
        }

        return result;
    }*/
}