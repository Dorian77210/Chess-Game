package engine.ranges;

import models.game.pieces.*;

import models.views.BoardModel;

import ui.board.*;

import helper.Assert;
import helper.Distance;
import helper.Position;
import helper.collections.CellCollection;
import helper.collections.PieceCollection;
import helper.collide.PieceCollision;
import helper.collide.FilterPieces;

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
    public CellCollection getAvailableRangeFor(Piece piece) {
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
    private CellCollection getAvailableRangeFor(Bishop bishop) {
        CellCollection range = new CellCollection();

        Position position = bishop.getPosition();

        BishopMovementStates states = new BishopMovementStates();

        BishopRange.addTopLeftCell(this.boardModel, bishop, states, range, bishop.getPosition());
        BishopRange.addTopRightCell(this.boardModel, bishop, states, range, bishop.getPosition());
        BishopRange.addBottomRightCell(this.boardModel, bishop, states, range, bishop.getPosition());
        BishopRange.addBottomLeftCell(this.boardModel, bishop, states, range, bishop.getPosition());

        if((bishop.isBlackPiece() && Engine.instance().informations.isBlackPlayerPlaying()) ||
           (bishop.isWhitePiece() && !Engine.instance().informations.isBlackPlayerPlaying())
        ) {
            FilterPieces.filterRange(bishop, range, this.boardModel);
        }

        return range;
    }

    /**
      * Get the available range for a bishop
      * @param knight The concerned piece
      * @return The available range for the kngith 
    **/
    private CellCollection getAvailableRangeFor(Knight knight) {
        Position position = knight.getPosition();
        Position currentPosition = null;

        CellCollection knightRange = new CellCollection();

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

        //filter the range if his king is checked
        if((knight.isBlackPiece() && Engine.instance().informations.isBlackPlayerPlaying()) ||
           (knight.isWhitePiece() && !Engine.instance().informations.isBlackPlayerPlaying())
        ) {
            FilterPieces.filterRange(knight, knightRange, this.boardModel);
        }

        return knightRange;
    }

    /**
      * Get the available range for a bishop
      * @param king The concerned piece
      * @return The available range for the king 
    **/
    private CellCollection getAvailableRangeFor(King king) {

        CellCollection kingRange = KingRange.getRawKingRange(this.boardModel, king);
        PieceCollection collidePieces = PieceCollision.getPiecesCollideWithKing(king, kingRange, this.boardModel);
        FilterPieces.filterKingRange(kingRange, collidePieces, this.boardModel);

        return kingRange;
    }

    /**
      * Get the available range for a bishop
      * @param queen The concerned piece
      * @return The available range for the queen 
    **/
    private CellCollection getAvailableRangeFor(Queen queen) {
        CellCollection range = new CellCollection();

        QueenMovementStates states = new QueenMovementStates();

        QueenRange.addBottomLeftCell(this.boardModel, queen, states, range, queen.getPosition());
        QueenRange.addTopLeftCell(this.boardModel, queen, states, range, queen.getPosition());
        QueenRange.addTopRightCell(this.boardModel, queen, states, range, queen.getPosition());
        QueenRange.addBottomRightCell(this.boardModel, queen, states, range, queen.getPosition());
        
        QueenRange.addLeftCell(this.boardModel, queen, states, range, queen.getPosition());
        QueenRange.addTopCell(this.boardModel, queen, states, range, queen.getPosition());
        QueenRange.addBottomCell(this.boardModel, queen, states, range, queen.getPosition());
        QueenRange.addRightCell(this.boardModel, queen, states, range, queen.getPosition());
        
        if((queen.isBlackPiece() && Engine.instance().informations.isBlackPlayerPlaying()) ||
           (queen.isWhitePiece() && !Engine.instance().informations.isBlackPlayerPlaying())
        ) {
            FilterPieces.filterRange(queen, range, this.boardModel);
        }

        return range;
    }

    /**
      * Get the available range for a bishop
      * @param pawn The concerned piece
      * @return The available range for the pawn 
    **/
    private CellCollection getAvailableRangeFor(Pawn pawn) {
        CellCollection range = new CellCollection();

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

        if((pawn.isBlackPiece() && Engine.instance().informations.isBlackPlayerPlaying()) ||
           (pawn.isWhitePiece() && !Engine.instance().informations.isBlackPlayerPlaying())
        ) {
            FilterPieces.filterRange(pawn, range, this.boardModel);
        }

        return range;
    }

    /**
      * Get the available range for a rook
      * @param bishop The concerned piece
      * @return The available range for the rook 
    **/
    private CellCollection getAvailableRangeFor(Rook rook) {
        CellCollection range = new CellCollection();

        Position position = rook.getPosition();

        RookMovementStates states = new RookMovementStates();

        RookRange.addBottomCell(this.boardModel, rook, states, range, rook.getPosition());
        RookRange.addTopCell(this.boardModel, rook, states, range, rook.getPosition());
        RookRange.addRightCell(this.boardModel, rook, states, range, rook.getPosition());
        RookRange.addLeftCell(this.boardModel, rook, states, range, rook.getPosition());
        
        if((rook.isBlackPiece() && Engine.instance().informations.isBlackPlayerPlaying()) ||
           (rook.isWhitePiece() && !Engine.instance().informations.isBlackPlayerPlaying())
        ) {
            FilterPieces.filterRange(rook, range, this.boardModel);
        }

        return range;
    }

}