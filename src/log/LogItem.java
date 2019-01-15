package log;

import enums.ActionType;

import helper.Position;

import models.game.pieces.Piece;


/**
  * The class <code>LogItem</code> represents a item on the log
  * @version 1.0
  * @author Dorian Terbah 
**/

public class LogItem {

    /**
      * The message in the log 
    **/
    private String message;

    /**
      * Constructor for the movement action 
    **/
    public LogItem(Piece source, Position destination) {
        this.message = "";
        this.createMovementMessage(source, destination);
    }

    /**
      * Constructor for the castling and eaten action 
    **/
    public LogItem(Piece source, Piece target, ActionType type) {
        this.message = "";
        if(type.equals(ActionType.CASTLING_ACTION)) {
            this.createCastlingMessage(source, target);
        } else if(type.equals(ActionType.EATEN_ACTION)) {
            this.createEatenMessage(source, target);
        } 
    }

    /**
      * Constructor for the checked message 
    **/
    public LogItem(boolean isBlackChecked) {
        this.message = "";
        this.createCheckedMessage(isBlackChecked);
    }

    /***************************** 
    ***********MESSAGES*********** 
    *****************************/

    /**
      * Create a message for the castiling action
      * @param source The source of the action
      * @param target The target of the action 
    **/
    private void createCastlingMessage(Piece source, Piece target) {
        String team = source.isBlackPiece() ? " black side" : "white side";
        this.message = "Castling for the " + team;
    }

    /**
      * Create a message for the checked 
      * @param isBlackChecked The state of checked black 
    **/
    private void createCheckedMessage(boolean isBlackChecked) {
        String team = isBlackChecked ? "Black King" : "White King";
        this.message = "The " + team + " is checked !";
    }

    /**
      * Create a message for a movement    
    **/
    private void createMovementMessage(Piece source, Position destination) {
        String color = source.isBlackPiece() ? " black" : " white";
        this.message = "The" + color + " " + source.getClassInfo() + " moves to the position " + destination.toString();
    }

    /**
      * Create a message for the eaten action
      * @param source The source of the action
      * @param target The target of the action 
    **/
    private void createEatenMessage(Piece source, Piece target) {
        String sourceTeam = source.isBlackPiece() ? " black" : "white";
        String targetTeam = target.isBlackPiece() ? " black" : "white";

        this.message = "The " + sourceTeam + " " + source.getClassInfo() + " has eaten a " + targetTeam + " " + target.getClassInfo(); 
    }

    public String toString() {
        return this.message;
    }
}