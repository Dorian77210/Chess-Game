package helper.collide;

import models.game.pieces.Piece;
import models.game.pieces.King;

import models.views.BoardModel;

import engine.Engine;

import ui.board.Cell;

import helper.Position;

import engine.ranges.KingRange;

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
      * @param opponentPieces The others pieces
      * @param king The current king
      * @param model The model of the game
      * @return All pieces which collide with the piece
    **/
    public static final ArrayList<Piece> getPiecesCollideWithKing(ArrayList<Cell> kingRange, King king, ArrayList<Piece> opponentPieces, BoardModel model) {
        ArrayList<Piece> result = getPiecesDirectCollideWith(king, opponentPieces, model);
        ArrayList<Cell> range;
        Cell kingCell = model.getCell(king.getPosition());
        Cell cell;

        Piece piece;

        for(Piece opponentPiece : opponentPieces) {
            for(int i = 0; i < kingRange.size(); i++) {
                cell = kingRange.get(i);
                piece = cell.getPiece();
                cell.setPiece(king);

                range = (opponentPiece instanceof King) 
                      ? KingRange.getRawKingRange(model, (King)opponentPiece)
                      : Engine.instance().ranges.getAvailableRangeFor(opponentPiece);
                
                if(range.contains(cell)) {
                    result.add(opponentPiece);
                }

                cell.setPiece(piece);
            }
        }

        System.out.println(result.size());
        // set the king model to his initial position
        kingCell.setPiece(king);
        return result;
    }

    /**
      * Get the pieces with collide direct with the king
      * @param kingRange The range of the king
      * @param king The concerned king
      * @param opponentPieces The opponent pieces
      * @param model The model of the board
      * @return The pieces which collide with king 
    **/
    public static final ArrayList<Piece> getPiecesDirectCollideWith(King king, ArrayList<Piece> opponentPieces, BoardModel model) {
        ArrayList<Piece> result = new ArrayList<Piece>();
        ArrayList<Cell> range;
        Cell kingCell = model.getCell(king.getPosition());

        for(Piece opponentPiece : opponentPieces) {
            range = (opponentPiece instanceof King) 
                  ? KingRange.getRawKingRange(model, (King)opponentPiece)
                  : Engine.instance().ranges.getAvailableRangeFor(opponentPiece);

            if(range.contains(kingCell)) {
                result.add(opponentPiece);
            }
        }

        if((result.size() > 0) && Engine.instance().informations.isBlackPlayerPlaying() && !Engine.instance().informations.isBlackPlayerChecked()) {
            Engine.instance().informations.setIsBlackPlayerChecked(true);
        } else if((result.size() > 0) && !Engine.instance().informations.isBlackPlayerPlaying() && !Engine.instance().informations.isWhitePlayerChecked()) {
            Engine.instance().informations.setIsWhitePlayerChecked(true);
        }

        return result;
    }
}