package engine.ranges;

import models.game.pieces.*;

import models.views.BoardModel;

import ui.board.*;

import helper.Assert;
import helper.Distance;
import helper.Position;

import engine.ranges.states.BishopMovementStates;
import engine.ranges.BishopRange;

import engine.ranges.states.RookMovementStates;
import engine.ranges.RookRange;

import engine.ranges.states.QueenMovementStates;
import engine.ranges.QueenRange;

import engine.ranges.KingRange;

import models.game.players.Player;
import enums.PlayerType;

import engine.Engine;

import helper.collide.PieceCollision;
import helper.filters.FilterPiece;

import java.util.ArrayList;

/**
  * The class <code>Ranges</code> fives the movements range for all pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Ranges {


    /**
      * The model of the board 
    **/
    private BoardModel boardModel;

    public Ranges(BoardModel boardModel) {
        this.boardModel = boardModel;
    }

    /**
      * Get the available range for a piece
      * @param piece The concerned piece
      * @return The available range for the piece 
    **/
    public ArrayList<Cell> getAvailableRangeFor(Piece piece) {
        if(piece instanceof Bishop) {
            return getAvailableRangeFor((Bishop)piece);
        } else if(piece instanceof Knight) {
            return getAvailableRangeFor((Knight)piece);
        } else if(piece instanceof King) {
            return getAvailableRangeFor((King)piece);
        } else if(piece instanceof Queen) {
            return getAvailableRangeFor((Queen)piece);
        } else if(piece instanceof Pawn) {
            return getAvailableRangeFor((Pawn)piece);
        } else {
            return getAvailableRangeFor((Rook)piece);
        }
    }

    /**
      * Get the available range for a bishop
      * @param bishop The concerned piece
      * @return The available range for the bishop 
    **/
    private ArrayList<Cell> getAvailableRangeFor(Bishop bishop) {
        ArrayList<Cell> range = new ArrayList<Cell>();

        Position position = bishop.getPosition();

        BishopMovementStates states = new BishopMovementStates();

        BishopRange.addTopLeftCell(this.boardModel, bishop, states, range, bishop.getPosition());
        BishopRange.addTopRightCell(this.boardModel, bishop, states, range, bishop.getPosition());
        BishopRange.addBottomRightCell(this.boardModel, bishop, states, range, bishop.getPosition());
        BishopRange.addBottomLeftCell(this.boardModel, bishop, states, range, bishop.getPosition());

        return range;
    }

    /**
      * Get the available range for a bishop
      * @param knight The concerned piece
      * @return The available range for the kngith 
    **/
    private ArrayList<Cell> getAvailableRangeFor(Knight knight) {
        Position position = knight.getPosition();
        Position currentPosition = null;

        ArrayList<Cell> knightRange = new ArrayList<Cell>();

        Cell cell;

        for(int y = 0; y < BoardView.HEIGHT; y++) {
            for(int x = 0; x < BoardView.WIDTH; x++) {
                currentPosition = new Position(x, y);
                if(Distance.getDistance(position, currentPosition) == Knight.KNIGHT_MOVEMENT_DISTANCE) {
                    cell = this.boardModel.getCell(currentPosition);
                    if(cell.isEmpty() || (!cell.isEmpty() && (!cell.getPiece().isSameTeamAs(knight)))) { 
                        knightRange.add(this.boardModel.getCell(currentPosition));
                    }
                }
            }
        }
        
        if((knight.isBlackPiece() && Engine.instance().informations.isBlackPlayerChecked()) ||
           (knight.isWhitePiece() && Engine.instance().informations.isWhitePlayerChecked())
        ) {
            System.out.println(knight.isBlackPiece());
            Player current = (Engine.instance().informations.isBlackPlayerPlaying())
                           ? Engine.instance().getPlayer(PlayerType.BLACK_PLAYER)
                           : Engine.instance().getPlayer(PlayerType.WHITE_PLAYER);


            //problem with board model
            FilterPiece.filterOtherRange(knight, knightRange, current.getKing(), this.boardModel);
        }

        return knightRange;
    }

    /**
      * Get the available range for a bishop
      * @param king The concerned piece
      * @return The available range for the king 
    **/
    private ArrayList<Cell> getAvailableRangeFor(King king) {

        ArrayList<Cell> kingRange = KingRange.getRawKingRange(this.boardModel, king);

        KingRange.removeOpponentPieces(kingRange, this.boardModel, king);
        
        return kingRange;
    }

    /**
      * Get the available range for a bishop
      * @param queen The concerned piece
      * @return The available range for the queen 
    **/
    private ArrayList<Cell> getAvailableRangeFor(Queen queen) {
        ArrayList<Cell> range = new ArrayList<Cell>();

        QueenMovementStates states = new QueenMovementStates();

        QueenRange.addBottomLeftCell(this.boardModel, queen, states, range, queen.getPosition());
        QueenRange.addTopLeftCell(this.boardModel, queen, states, range, queen.getPosition());
        QueenRange.addTopRightCell(this.boardModel, queen, states, range, queen.getPosition());
        QueenRange.addBottomRightCell(this.boardModel, queen, states, range, queen.getPosition());
        
        QueenRange.addLeftCell(this.boardModel, queen, states, range, queen.getPosition());
        QueenRange.addTopCell(this.boardModel, queen, states, range, queen.getPosition());
        QueenRange.addBottomCell(this.boardModel, queen, states, range, queen.getPosition());
        QueenRange.addRightCell(this.boardModel, queen, states, range, queen.getPosition());
        
        return range;
    }

    /**
      * Get the available range for a bishop
      * @param pawn The concerned piece
      * @return The available range for the pawn 
    **/
    private ArrayList<Cell> getAvailableRangeFor(Pawn pawn) {
        ArrayList<Cell> range = new ArrayList<Cell>();

        Position position = pawn.getPosition();
        Cell currentCell = null;
        int direction = (pawn.isBlackPiece()) ?  1 : -1;
        boolean isBlocked = false;

        //add the cells

        //cell in front of the pawn
        currentCell = this.boardModel.getCell(new Position(position.x, position.y + direction));
        if(Assert.isSet(currentCell) && currentCell.isEmpty()) {
            range.add(currentCell);
        }  else  {
            isBlocked = true;
        }

        //cellin front of the pawn + 1
        if(pawn.isFirstTimeMoving() && !isBlocked) {
            currentCell = this.boardModel.getCell(new Position(position.x, position.y + (2 * direction)));
            if(Assert.isSet(currentCell) && currentCell.isEmpty()) {
                range.add(currentCell);
            }
        }

        currentCell = this.boardModel.getCell(new Position(position.x - 1, position.y + direction));
        if(Assert.isSet(currentCell) && !currentCell.isEmpty() && !currentCell.getPiece().isSameTeamAs(pawn)) {
            range.add(currentCell);
        }

        currentCell = this.boardModel.getCell(new Position(position.x + 1, position.y + direction));
        if(Assert.isSet(currentCell) && !currentCell.isEmpty() && !currentCell.getPiece().isSameTeamAs(pawn)) {
            range.add(currentCell);
        }

        return range;
    }

    /**
      * Get the available range for a rook
      * @param bishop The concerned piece
      * @return The available range for the rook 
    **/
    private ArrayList<Cell> getAvailableRangeFor(Rook rook) {
        ArrayList<Cell> range = new ArrayList<Cell>();

        Position position = rook.getPosition();

        RookMovementStates states = new RookMovementStates();

        RookRange.addBottomCell(this.boardModel, rook, states, range, rook.getPosition());
        RookRange.addTopCell(this.boardModel, rook, states, range, rook.getPosition());
        RookRange.addRightCell(this.boardModel, rook, states, range, rook.getPosition());
        RookRange.addLeftCell(this.boardModel, rook, states, range, rook.getPosition());
        
        return range;
    }

}