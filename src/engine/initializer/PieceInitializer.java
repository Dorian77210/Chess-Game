package engine.initializer;

import java.io.*;

import enums.PieceType;

import models.game.pieces.*;

import engine.game.GamePieces;

import ui.board.BoardView;

import helper.collections.PieceCollection;
import helper.constants.PieceConstants;
import helper.Console;
import helper.Position;

import java.util.ArrayList;

/**
  * The class <code>PieceInitializer</code> initialize the pieces of the players
  * @version 1.0
  * @author Dorian Terbah 
**/

public class PieceInitializer {
    
    private static final String BOARD_INITIALIZER_FILE = "./rsc/files/board.dat";

    /**
      * Recover all of the pieces in the file
      * @return The pieces 
    **/
    public static final GamePieces recoverPieces() {
        GamePieces gamePieces = null;

        PieceCollection whitePieces = new PieceCollection();
        PieceCollection blackPieces = new PieceCollection();

        byte mask = 0;

        DataInputStream infile = null;
        Position position = null;

        try {   
            infile = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(BOARD_INITIALIZER_FILE))));
            for(int y = 0; y < BoardView.HEIGHT; y++) {
                for(int x = 0; x < BoardView.WIDTH; x++) {
                    try {
                        mask = infile.readByte();
                    } catch(IOException readException) {
                        Console.log("Error when reading in the file " + BOARD_INITIALIZER_FILE);
                        System.exit(1);
                    }

                    position = new Position(x, y);
                    
                    //manage the piece
                    switch(mask) {
                        //White piece
                        case PieceConstants.WHITE_PAWN_MASK:
                            whitePieces.add(new Pawn(position, PieceType.WHITE_PIECE));
                        break;

                        case PieceConstants.WHITE_ROOK_MASK:
                            whitePieces.add(new Rook(position, PieceType.WHITE_PIECE));
                        break;

                        case PieceConstants.WHITE_BISHOP_MASK:
                            whitePieces.add(new Bishop(position, PieceType.WHITE_PIECE));
                        break;

                        case PieceConstants.WHITE_QUEEN_MASK:
                            whitePieces.add(new Queen(position, PieceType.WHITE_PIECE));
                        break;

                        case PieceConstants.WHITE_KNIGHT_MASK:
                            whitePieces.add(new Knight(position, PieceType.WHITE_PIECE));
                        break;

                        case PieceConstants.WHITE_KING_MASK:
                            whitePieces.add(new King(position, PieceType.WHITE_PIECE));
                        break;

                        //Black piece
                        case PieceConstants.BLACK_PAWN_MASK:
                            blackPieces.add(new Pawn(position, PieceType.BLACK_PIECE));
                        break;

                        case PieceConstants.BLACK_ROOK_MASK:
                            blackPieces.add(new Rook(position, PieceType.BLACK_PIECE));
                        break;

                        case PieceConstants.BLACK_BISHOP_MASK:
                            blackPieces.add(new Bishop(position, PieceType.BLACK_PIECE));
                        break;

                        case PieceConstants.BLACK_QUEEN_MASK:
                            blackPieces.add(new Queen(position, PieceType.BLACK_PIECE));
                        break;  

                        case PieceConstants.BLACK_KNIGHT_MASK:
                            blackPieces.add(new Knight(position, PieceType.BLACK_PIECE));
                        break;

                        case PieceConstants.BLACK_KING_MASK:
                            blackPieces.add(new King(position, PieceType.BLACK_PIECE));
                        break;
                    }
                }
            }

        } catch(FileNotFoundException fileNotFoundException) {
            Console.log("Error when searching file " + BOARD_INITIALIZER_FILE);
            System.exit(1);
        }

        return new GamePieces(whitePieces, blackPieces);
    }
}