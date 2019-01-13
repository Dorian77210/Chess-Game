package engine.ranges;

import helper.Position;
import helper.Assert;
import helper.collections.CellCollection;

import models.views.BoardModel;

import models.game.players.Player;
import enums.PlayerType;

import models.game.pieces.Piece;
import models.game.pieces.King;

import ui.board.Cell;

import engine.Engine;

/**
  * The class <code>KingRange</code> represents the range for the king
  * @version 1.0
  * @author Dorian Terbah 
**/

public class KingRange {

    public static final CellCollection getRawKingRange(BoardModel model, King king) {
        Position position = king.getPosition();

        CellCollection range = new CellCollection();

        //add the cells

        //top cell
        Cell cell = model.getTopCell(position);
        if(Assert.isSet(cell) && (cell.isEmpty() || (Assert.isSet(cell.getPiece()) && !cell.getPiece().isSameTeamAs(king)))) {
            range.add(cell);
        } 
        
        cell = model.getTopLeftCell(position);
        if(Assert.isSet(cell) && (cell.isEmpty() || (Assert.isSet(cell.getPiece()) && !cell.getPiece().isSameTeamAs(king)))) {
            range.add(cell);
        } 

        cell = model.getLeftCell(position);
        if(Assert.isSet(cell) && (cell.isEmpty() || (Assert.isSet(cell.getPiece()) && !cell.getPiece().isSameTeamAs(king)))) {
            range.add(cell);
        } 

        cell = model.getBottomLeftCell(position);
        if(Assert.isSet(cell) && (cell.isEmpty() || (Assert.isSet(cell.getPiece()) && !cell.getPiece().isSameTeamAs(king)))) {
            range.add(cell);
        } 

        cell = model.getBottomCell(position);
        if(Assert.isSet(cell) && (cell.isEmpty() || (Assert.isSet(cell.getPiece()) && !cell.getPiece().isSameTeamAs(king)))) {
            range.add(cell);
        } 

        cell = model.getBottomRightCell(position);
        if(Assert.isSet(cell) && (cell.isEmpty() || (Assert.isSet(cell.getPiece()) && !cell.getPiece().isSameTeamAs(king)))) {
            range.add(cell);
        } 

        cell = model.getRightCell(position);
        if(Assert.isSet(cell) && (cell.isEmpty() || (Assert.isSet(cell.getPiece()) && !cell.getPiece().isSameTeamAs(king)))) {
            range.add(cell);
        } 

        cell = model.getTopRightCell(position);
        if(Assert.isSet(cell) && (cell.isEmpty() || (Assert.isSet(cell.getPiece()) && !cell.getPiece().isSameTeamAs(king)))) {
            range.add(cell);
        }  

        return range;
    }

}