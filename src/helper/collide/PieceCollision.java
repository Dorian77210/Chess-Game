package helper.collide;

import helper.collections.CellCollection;
import helper.collections.PieceCollection;
import helper.Position;

import ui.board.Cell;

import models.game.pieces.King;
import models.game.pieces.Piece;

import models.views.BoardModel;
import models.game.players.Player;

import engine.ranges.KingRange;

import engine.Engine;

/**
  * The class <code>PieceCollision</code> give the collide pieces 
  * @version 1.0
  * @author Dorian Terbah 
**/

public class PieceCollision {

    /**
      * Get the pieces which put directly the king in check
      * @param king The concerned king
      * @param rawRange The raw range of the king
      * @param model The model of the board 
    **/
    public static final PieceCollection getDirectPiecesCollideWithKing(King king, CellCollection rawRange, BoardModel model) {
        //retrieve the opponents pieces
        Player opponentPlayer = Engine.instance().getNotCurrentPlayer();
        PieceCollection opponentPieces = opponentPlayer.getPieces();
        PieceCollection directPieces = new PieceCollection();

        Cell kingCell = model.getCell(king.getPosition());
        CellCollection range;

        for(Piece opponentPiece : opponentPieces) {
            // retrieve the range of the current opponent piece
            range = (opponentPiece instanceof King)
                    ? KingRange.getRawKingRange(model, (King)opponentPiece)
                    : Engine.instance().ranges.getAvailableRangeFor(opponentPiece);

            if(range.contains(kingCell)) {
                directPieces.add(opponentPiece);
            }
        }

        return directPieces;
    }

    /** 
      * Get the peices which could put the king in the next round
      * @param king The concerned king 
      * @param rawRange The raw range of the king
      * @param model The model of the board 
    **/
    public static final PieceCollection getIndirectPiecesCollideWithKing(King king, CellCollection rawRange, BoardModel model) {
        //retrieve the opponents pieces
        Player opponentPlayer = Engine.instance().getNotCurrentPlayer();
        PieceCollection opponentPieces = opponentPlayer.getPieces();
        PieceCollection indirectPieces = new PieceCollection();

        CellCollection range;
        Cell cell;
        Cell kingCell = model.getCell(king.getPosition());

        int i;
        Piece piece;

        for(Piece opponentPiece : opponentPieces) {
            for(i= 0; i < rawRange.size(); i++) {
                cell = rawRange.get(i);
                piece = cell.getPiece();
                cell.setPiece(king);
                range = (opponentPiece instanceof King)
                        ? KingRange.getRawKingRange(model, (King)opponentPiece)
                        : Engine.instance().ranges.getAvailableRangeFor(opponentPiece);

                if(range.contains(cell)) {
                    indirectPieces.add(opponentPiece);
                } else if(range.contains(kingCell)) {
                    indirectPieces.add(opponentPiece);
                }
                
                //retrieve the normal state for the cell
                cell.setPiece(piece);
            }
        }

        kingCell.setPiece(king);

        return indirectPieces;
    }

    /**
      * Get the indirect and direct pieces which collide with the king
      * @param king The concerned king
      * @param rawRange The raw range of the king
      * @param model The model of the board
    **/
    public static final PieceCollection getPiecesCollideWithKing(King king, CellCollection rawRange, BoardModel model) {
        PieceCollection collection = getIndirectPiecesCollideWithKing(king, rawRange, model);
        collection.addAll(getDirectPiecesCollideWithKing(king, rawRange, model));

        return collection;
    }
}