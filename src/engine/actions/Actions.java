package engine.actions;

import models.game.pieces.King;
import models.game.pieces.Piece;
import models.game.pieces.Pawn;
import models.game.pieces.Queen;
import models.game.pieces.Rook;

import ui.board.Cell;

import helper.Assert;
import helper.Position;
import helper.collections.CellCollection;

import models.views.BoardModel;

import models.game.players.Player;

import enums.PlayerType;
import enums.ActionType;

import log.Log;
import log.LogItem;

import engine.Engine;

import undo.UndoRedo;

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
        
        if(canMakeCastling(piece, target.getPiece(), model)) {
            //save the image of the game
            UndoRedo.instance().push(Engine.instance().getAllPieces().clone());
            castling((King)piece, (Rook)target.getPiece(), model);
            Log.instance().push(new LogItem(piece, target.getPiece(), ActionType.CASTLING_ACTION));
            return;
        }

        if(range.contains(target)) {

            //save the image of the game
            UndoRedo.instance().push(Engine.instance().getAllPieces().clone());

            Position position = piece.getPosition();
            Cell source = model.getCell(position);
            source.deletePiece();

            //check if the source piece can eat the target piece
            Piece targetPiece = target.getPiece();
            if(Assert.isSet(targetPiece)) {
                targetPlayer.removePiece(targetPiece);
                Log.instance().push(new LogItem(piece, targetPiece, ActionType.EATEN_ACTION));
            } else {
                Log.instance().push(new LogItem(piece, target.getPosition()));
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

            if(Engine.instance().informations.isBlackPlayerChecked()) {
                Log.instance().push(new LogItem(true));
            } else if(Engine.instance().informations.isWhitePlayerChecked()) {
                Log.instance().push(new LogItem(false));
            }

            source.refreshAppearance();
            target.refreshAppearance();

        }
    }

    /**
      * Check if the player can make a castling
      * @param source The source of the possible castling
      * @param target The target of the possible castling
      * @param model The model of the board
      * @return True if the player can make a catling, else false 
    **/
    private static final boolean canMakeCastling(Piece source, Piece target, BoardModel model) {
        King king;
        int xSource = 0, xTarget = 0, y = 0;
        Position position;
        Cell cell;

        if(Assert.isSet(source) && Assert.isSet(target)) {
            if(source.isSameTeamAs(target)) {
                if((source instanceof King) && (target instanceof Rook)) {
                    if(source.isFirstTimeMoving() && target.isFirstTimeMoving()) {
                        king = (King)source;
                        y = target.getPosition().y;
                        if(!king.wasAlreadyChecked()) {
                            //check if the cells between the two pieces are empty
                            if(king.getPosition().x < source.getPosition().x) {
                                xSource = target.getPosition().x + 1;
                                xTarget = king.getPosition().x;
                            } else {
                                xSource = king.getPosition().x + 1;
                                xTarget = target.getPosition().x;
                            }

                            for(int x = xSource; x < xTarget; x++) {
                                position = new Position(x, y);
                                cell = model.getCell(position);
                                if(!cell.isEmpty()) {
                                    return false;
                                } 
                            }

                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
      * Do the castling action
      * @param source The source
      * @param target The target 
      * @param model The model of the board 
    **/
    public void castling(King source, Rook target, BoardModel model) {
        Position sourcePosition = source.getPosition();
        Position targetPosition = target.getPosition();

        Cell sourceCell = model.getCell(sourcePosition);
        Cell targetCell = model.getCell(targetPosition);

        //delete the pieces fr the concerned pieces
        sourceCell.deletePiece();
        targetCell.deletePiece();

        if(targetPosition.x == 0) {
            //high castling
            source.move(new Position(2, sourcePosition.y));
            target.move(new Position(3, targetPosition.y));

        } else {    
            //small castling
            source.move(new Position(6, sourcePosition.y));
            target.move(new Position(5, targetPosition.y));
        }

        //update the new cells
        model.getCell(source.getPosition()).setPiece(source);
        model.getCell(target.getPosition()).setPiece(target);

        //toggle the first time moving state
        source.toggleIsFirstTimeMoving();
        target.toggleIsFirstTimeMoving();

        //increment the number of rounds
        Engine.instance().informations.incrementRounds();
        Engine.instance().updateCheckedStates();
    }
}