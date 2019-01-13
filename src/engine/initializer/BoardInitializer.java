package engine.initializer;

import models.game.pieces.Piece;

import ui.board.BoardView;

import helper.constants.Palette;
import helper.Position;
import helper.collections.PieceCollection;

import ui.board.Cell;

import java.util.ArrayList;

import java.awt.Color;
/**
  * The class <code>BoardInitialize</code> initialize the cells of the board
  * @version 1.0
  * @author Dorian Terbah 
**/

public class BoardInitializer {
    
    /**
      * Initialize the cells of the board
      * @param cells The cells to initialize 
      * @param pieces All piece of the game
    **/
    public static final void initializeCells(Cell[][] cells, PieceCollection pieces) {
        Position position;
        Piece piece;
        Color color;

        for(int y = 0; y < BoardView.HEIGHT; y++) {
            for(int x = 0; x < BoardView.WIDTH; x++) {
                position = new Position(x, y);
                color = (((x + y) % 2) == 0) ? Palette.CHEROKEE_CELL_COLOR : Palette.COPPER_CELL_COLOR;
                cells[y][x] = new Cell(color, position);
                piece = getPiece(pieces, position);
                cells[y][x].setPiece(piece);
            }
        }
    }

    /**
      * Get a piece if it associated to the position
      * @param pieces All piece of the game
      * @position The current position
      * @return The piece asociated to the position
    **/
    private static final Piece getPiece(PieceCollection pieces, Position position) {
        for(Piece piece : pieces) {
            if(piece.getPosition().equals(position)) {
                return piece;
            }
        }

        return null;
    }   
}