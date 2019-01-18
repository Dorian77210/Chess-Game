package undo;

import helper.collections.PieceCollection;

import enums.PieceType;

import engine.informations.GameInformations;

/**
  * The class <code>BoardSave</code> saves the crucial informations of the board
  * @version 1.0
  * @author Dorian Terbah
**/

public class BoardSave {

    /**
      * All of the pieces of the two players
    **/
    private PieceCollection allPieces;

    /**
      * The informations of the game 
    **/
    private GameInformations informations;

    public BoardSave(PieceCollection allPieces, GameInformations informations) {
        this.allPieces = allPieces;
        this.informations = informations;
    }

    /***************************** 
    ***********GETTER************* 
    ******************************/
    
    /**
      * Get the pieces according to their color
      * @param isWhitePiece If the pieces arewhite
      * @return The pieces according to the color
    **/
    public PieceCollection getPiecesAccordingToColor(boolean isWhitePiece) {                              
        return this.allPieces.getPiecesByColor(isWhitePiece);
    }

    /**
      * Get all of the pieces
      * @return All of the pieces 
    **/
    public PieceCollection getAllPieces() {
        return this.allPieces;
    }

    /**
      * Get the informations of the game
      * @return The informations of the game
    **/
    public GameInformations getGameInformations() {
        return this.informations;
    }
}