package engine.ranges.states;

/**
  * The class <code>KingMovementState</code> represents the differents possibilities for the king to move
  * @version 1.0
  * @author Dorian Terbah 
**/

public class KingMovementStates {
    
    /**
      * If the king is blocked for the top position 
    **/
    public boolean isTopBlocked;

    /**
      * If the king is blocked for the right top position 
    **/
    public boolean isRightTopBlocked;

    /**
      * If the king is blocked for the right position 
    **/
    public boolean isRightBlocked;

    /**
      * If the king is blocked for the right bottom position 
    **/
    public boolean isRightBottomBlocked;

    /**
      * If the king is blocked for the bottom position 
    **/
    public boolean isBottomBlocked;

    /**
      * If the king is blocked for the left bottom position 
    **/
    public boolean isLeftBottomBlocked;
    
    /**
      * If the king is blocked for the left position 
    **/
    public boolean isLeftBlocked;

    /**
      * If the king is blocked for the left top position 
    **/
    public boolean isLeftTopBlocked;

    /**
      * If the king is blocked for the left top position 
    **/

    public KingMovementStates() {
        this.isLeftTopBlocked = false;
        this.isRightBottomBlocked = false;
        this.isRightTopBlocked = false;
        this.isLeftBottomBlocked = false;

        this.isTopBlocked = false;
        this.isBottomBlocked = false;
        this.isRightBlocked = false;
        this.isLeftBlocked = false;
    }
}