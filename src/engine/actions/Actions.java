package engine.actions;

import models.game.pieces.Piece;
import models.game.pieces.Pawn;
import models.game.pieces.Queen;

import ui.board.Cell;

import helper.Assert;
import helper.Position;
import helper.collections.CellCollection;

import models.views.BoardModel;

import models.game.players.Player;

import enums.PlayerType;

import engine.Engine;

/**
  * The class <code>Actions</code> fives the differents actions of the game
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Actions {
    
    public Actions() {

    }

    /**
      * Move a piece 
      * @param piece The piece
      * @param target The target cell
      * @param model The model of the board 
    **/
    public void move(Piece piece, Cell target, BoardModel model) {
        CellCollection range = Engine.instance().ranges.getAvailableRangeFor(piece);
        Player targetPlayer = Engine.instance().getNotCurrentPlayer();

        if(range.contains(target)) {

            Position position = piece.getPosition();
            Cell source = model.getCell(position);
            source.deletePiece();

            //check if the source piece can eat the target piece
            Piece targetPiece = target.getPiece();
            if(Assert.isSet(targetPiece)) {
                targetPlayer.removePiece(targetPiece);
            }
            
            //update the checked state for the current player
            if(Engine.instance().informations.isBlackPlayerPlaying()) {
                Engine.instance().informations.setIsBlackPlayerChecked(false);
            } else {
                Engine.instance().informations.setIsWhitePlayerChecked(false);
            }

            target.setPiece(piece);

             // update position of the piece
             if(piece instanceof Pawn) {
                 if((piece.isBlackPiece() && piece.getPosition().y == 7) ||
                    (piece.isWhitePiece() && piece.getPosition().y == 0)
                 ) {
                     target.setPiece(Engine.instance().getCurrentPlayer().pawnToQueen((Pawn)piece));
                 }
             }


            //increment the number of rounds
            Engine.instance().informations.incrementRounds();

            //check the first move for the current piece
            if(piece.isFirstTimeMoving()) {
                piece.toggleIsFirstTimeMoving();
            }

           

            Engine.instance().updateCheckedStates();

            
        }
    }
}