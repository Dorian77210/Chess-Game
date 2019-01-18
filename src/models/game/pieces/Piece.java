package models.game.pieces;

import helper.Position;

import ui.board.Cell;

import engine.Engine;

import enums.PieceType;

/**
  * The class <code>Piece</code> represents the base of all of the pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Piece {

    public static final String BASE_PATH = "./rsc/images/"; 

    /**
      * The position of the piece in the board 
    **/
    protected Position position;

    /**
      * The type of the piece 
    **/
    protected PieceType type;

    /**
      * A boolean to describe the team of the piece 
    **/
    protected boolean isBlackPiece; 

    /**
      * Boolean to represent if this pawn if moving for the first time 
    **/
    protected boolean isFirstTimeMoving;

    /**
      * The path of the image 
    **/
    protected String representation;

    /**
      * Describe the class 
    **/
    protected String classInfo;

    public Piece(Position position, PieceType type) {
        this.position = position;
        this.type = type;

        this.isBlackPiece = type.equals(PieceType.BLACK_PIECE);

        //default value
        this.isFirstTimeMoving = true;

        this.classInfo = "";
    }

    public Piece(Piece piece) {
        this.position = new Position(piece.position.x, piece.position.y);
        this.type = piece.type;

        this.isBlackPiece = piece.isBlackPiece;

        this.isFirstTimeMoving = piece.isFirstTimeMoving;

        this.representation = piece.representation;

        this.classInfo = "";
    }

    /***************************** 
    *************GETTER*********** 
    *****************************/

    /**
      * Check if the piece is a black piece
      * @return True if it is, else false 
    **/
    public boolean isBlackPiece() {
        return this.isBlackPiece;
    }

    /**
      * Check if the piece is a white piece
      * @return True if it is, else false
    **/
    public boolean isWhitePiece() {
        return !this.isBlackPiece;
    }

    /**
      * Check if this pawn if moving for the first time
      * return True if it is, else false 
    **/
    public boolean isFirstTimeMoving() {
        return this.isFirstTimeMoving;
    }

    /**
      * Get the path of the representation of the piece
      * @return The path 
    **/
    public String getRepresentation() {
        return this.representation;
    }

    /**
      * Get the position of the piece in the board
      * @return The position of the piece int the board 
    **/
    public Position getPosition() {
        return this.position;
    }

    /**
      * Get the color of the piece
      * @return The color of the piece 
    **/
    public PieceType getPieceType() {
        return this.type;
    }

    /**
      * Check if this piece can move
      * @return True this piece can move, else false
    **/
    public boolean canMove() {
        return (this.isBlackPiece == Engine.instance().informations.isBlackPlayerPlaying());
    }

    /**
      * Check if this piece and an other are in the same team
      * @return True if it is, else fale 
    **/
    public boolean isSameTeamAs(Piece piece) {
        return (this.type.equals(piece.type));
    }

    /**
      * Get the class info
      * @return The class info 
    **/
    public String getClassInfo() {
        return this.classInfo;
    }

    /***************************** 
    *************MOVE************* 
    *****************************/

    /**
      * Move the picece to a new position
      * @param position The new position of the piece 
    **/
    public void move(Position position) {
        this.position = position;
    }   

    /***************************** 
    ************TOGGLE************ 
    *****************************/

    /**
      * Toggle the isFirstTimeMoving state 
    **/
    public void toggleIsFirstTimeMoving() {
        this.isFirstTimeMoving = !this.isFirstTimeMoving;
    }

    /***************************** 
    *************SETTER*********** 
    *****************************/
    /**
      * Set the isFirstTimeMoving state
      * @param isFirstTimeMoving The state 
    **/
    public void setIsFirstTimeMoving(boolean isFirstTimeMoving) {
        this.isFirstTimeMoving = isFirstTimeMoving;
    }

    /***************************** 
    ************CLONE************* 
    *****************************/

    /**
      * Get a clone object of the piece
      * @return The clone of this object 
    **/
    @Override
    public Piece clone() {
        return new Piece(this);
    }
}