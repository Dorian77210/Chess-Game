package engine.actions;

import models.game.pieces.Piece;

import ui.board.Cell;

import helper.Assert;
import helper.Position;

import models.views.BoardModel;

import models.game.players.Player;

import enums.PlayerType;

import engine.Engine;

import java.util.ArrayList;
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
        ArrayList<Cell> range = Engine.instance().ranges.getAvailableRangeFor(piece);
        Player targetPlayer = (piece.isBlackPiece()) ? Engine.instance().getPlayer(PlayerType.WHITE_PLAYER) : Engine.instance().getPlayer(PlayerType.BLACK_PLAYER);

        if(range.contains(target)) {
            Position position = piece.getPosition();
            Cell source = model.getCell(position);
            source.deletePiece();

            //check if the source piece can eat the target piece
            Piece targetPiece = target.getPiece();
            if(Assert.isSet(targetPiece)) {
                targetPlayer.removePiece(targetPiece);
            }

            target.setPiece(piece);

            Engine.instance().informations.incrementRounds();

            if(piece.isFirstTimeMoving()) {
                piece.toggleIsFirstTimeMoving();
            }
        }
    }
}