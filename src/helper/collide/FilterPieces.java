package helper.collide;

import helper.collections.CellCollection;
import helper.collections.PieceCollection;

import models.game.pieces.Piece;
import models.game.pieces.King;
import models.views.BoardModel;

import ui.board.Cell;

import engine.ranges.KingRange;
import engine.Engine;

/**
  * The class <code>FilterPieces</code> filter pieces 
  * @version 1.0
  * @author Dorian Terbah 
**/

public class FilterPieces {
    
    /**
      * Filter the movement of the king
      * @param kingRawRange The raw range of the king
      * @param collidePieces The pieces to analyze
      * @param model The model of the board  
    **/
    public static final void filterKingRange(CellCollection kingRawRange, PieceCollection collidePieces, BoardModel model) {
        King currentKing = Engine.instance().getCurrentPlayer().getKing();

        Cell kingCell = model.getCell(currentKing.getPosition());
        CellCollection collideRange;
        Cell cell;
        int i;

        Piece piece;
        for(Piece collidePiece : collidePieces) {
            for(i = 0; i < kingRawRange.size();) {
                cell = kingRawRange.get(i);
                piece = cell.getPiece();
                cell.setPiece(currentKing);

                collideRange = (collidePiece instanceof King) 
                             ? KingRange.getRawKingRange(model, (King)collidePiece)
                             : Engine.instance().ranges.getAvailableRangeFor(collidePiece);

                if(collideRange.contains(cell)) {
                    kingRawRange.remove(cell);
                } else {
                    i++;
                }

                cell.setPiece(piece);
            }
        }

        kingCell.setPiece(currentKing);
    }

    /**
      * Filter the range of any piece
      * @param source The source piece
      * @param range The range to filter
      * @param model The model of the board
    **/
    public static final void filterRange(Piece source, CellCollection range, BoardModel model) {
        King currentKing = Engine.instance().getCurrentPlayer().getKing();

        PieceCollection directCollidePieces;

        Cell currentCell = model.getCell(source.getPosition());
        CellCollection kingRange;
        Cell cell;
        int i;

        Piece piece;
        for(i = 0; i < range.size();) {
            cell = range.get(i);
            piece = cell.getPiece();
            cell.setPiece(source);

            kingRange = KingRange.getRawKingRange(model, currentKing);

            directCollidePieces = PieceCollision.getDirectPiecesCollideWithKing(currentKing, kingRange, model);
            if(!directCollidePieces.isEmpty()) {
                for(Piece directCollidePiece : directCollidePieces) {
                    if(directCollidePiece.getPosition().equals(source.getPosition())) {
                        i++;
                    } else {
                        range.remove(cell);
                    }
                }
            } else {
                i++;
            }
            cell.setPiece(piece);

        }

        currentCell.setPiece(source);

    }
}