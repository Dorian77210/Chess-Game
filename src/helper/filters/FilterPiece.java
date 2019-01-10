package helper.filters;

import helper.Position;

import ui.board.Cell;

import models.views.BoardModel;

import models.game.pieces.Piece;
import models.game.pieces.King;
import engine.ranges.KingRange;

import engine.Engine;

import helper.Console;

import helper.collide.PieceCollision;

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
    public static final void filterKingRange(ArrayList<Cell> kingRange, King king, ArrayList<Piece> collidePieces, BoardModel model) {
        ArrayList<Cell> collideRange;
        Cell kingCell = model.getCell(king.getPosition());
        Cell cell;
        Piece piece;


        for(Piece collidePiece : collidePieces) {
            for(int i = 0; i < kingRange.size(); i++) {
                cell = kingRange.get(i);
                piece = cell.getPiece();
                cell.setPiece(king);

                collideRange = (collidePiece instanceof King)
                             ? KingRange.getRawKingRange(model, (King)collidePiece)
                             : Engine.instance().ranges.getAvailableRangeFor(collidePiece);

                if(collideRange.contains(cell)) {
                    kingRange.remove(cell);
                    i = 0;
                }

                cell.setPiece(piece);
            }
        }

        kingCell.setPiece(king);
    }

    
    public static final void filterOtherRange(Piece source, ArrayList<Cell> range, King king, BoardModel model) {
        ArrayList<Piece> collidePieces;
        ArrayList<Cell> kingRange;
        int i;

        Cell sourceCell = model.getCell(source.getPosition());

        Cell cell;  
        Piece piece;

        for(i = 0; i < range.size();) {
            cell = range.get(i);    
            piece = cell.getPiece();
            cell.setPiece(source);

            kingRange = Engine.instance().ranges.getAvailableRangeFor(king);

            collidePieces = PieceCollision.getPiecesCollideWithKing(kingRange, king, model);


            if(!collidePieces.isEmpty()) {
                for(Piece p : collidePieces) {
                    if(!range.contains(model.getCell(p.getPosition()))) {
                        range.remove(cell);
                        //simulate a cost
                    } else {
                        i++;
                    }
                }
            } else {
                i++;
            }

            
            cell.setPiece(piece);
        }

        sourceCell.setPiece(source);
    }  
}