package engine.ranges.states;

/**
  * The class <code>RookMovementState</code> represents the differents possibilities for the rook to move
  * @version 1.0
  * @author Dorian Terbah 
**/

public class RookMovementStates {

    /**
      * If the rook is blocked for the top position 
    **/
    public boolean isTopBlocked;

    /**
      * If the rook is blocked for the right position 
    **/
    public boolean isRightBlocked;

    /**
      * If the rook is blocked for the bottom position 
    **/
    public boolean isBottomBlocked;

    /**
      * If the rook is blocked for the left position 
    **/
    public boolean isLeftBlocked;

    public RookMovementStates() {
        this.isTopBlocked = false;
        this.isBottomBlocked = false;
        this.isRightBlocked = false;
        this.isLeftBlocked = false;
    }
}