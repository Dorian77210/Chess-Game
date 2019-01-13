package engine.counter;

import models.game.pieces.*;

import models.game.players.Player;

import engine.Engine;

import enums.KindOfPiece;

import enums.PlayerType;

import helper.collections.PieceCollection;

import java.util.ArrayList;

/**
  * The class <code>PieceCounter</code> enables to have the number of piece 
  * @version 1.0
  * @author Dorian Terbah 
**/

public class PieceCounter {
    
    public PieceCounter() {

    }

    /**
      * Count the number of almost piece 
      * @param kind Thekind of wanted piece
      * @param type The color of the player
    **/
    public final int getCountOf(KindOfPiece kind, PlayerType type) {
        Player player = Engine.instance().getPlayer(type);
        PieceCollection pieces = player.getPieces();

        int count = 0;
        for(Piece piece : pieces) {
            if(this.typeCorrespondToClass(kind, piece)) {
                count++;
            }
        }

        return count;
    } 

    /**
      * Check if a kind corresponds to a piece
      * @param kind The kind of piece
      * @param piece The concerned piece
      * @return True it they correspon, else false 
    **/
    private final boolean typeCorrespondToClass(KindOfPiece kind, Piece piece) {
        if(kind.equals(KindOfPiece.KIND_BISHOP) && piece instanceof Bishop) {
            return true;
        } else if(kind.equals(KindOfPiece.KIND_KING) && piece instanceof King) {
            return true;
        } else if(kind.equals(KindOfPiece.KIND_KNIGHT) && piece instanceof Knight) {
            return true;
        } else if(kind.equals(KindOfPiece.KIND_PAWN) && piece instanceof Pawn) {
            return true;
        } else if(kind.equals(KindOfPiece.KIND_QUEEN) && piece instanceof Queen) {
            return true;
        } else if(kind.equals(KindOfPiece.KIND_ROOK) && piece instanceof Rook) {
            return true;
        }

        return false;
    }
}