package engine.ranges.states;

/**
  * The class <code>BishopMovementState</code> represents the differents possibilities for the bishop to move
  * @version 1.0
  * @author Dorian Terbah 
**/

public class BishopMovementStates {

    /**
      * If the bishop is blocked for the left top position 
    **/
    public boolean isLeftTopBlocked;

    /**
      * If the bishop is blocked for the right top position 
    **/
    public boolean isRightTopBlocked;

    /**
      * If the bishop is blocked for the left bottom position 
    **/
    public boolean isLeftBottomBlocked;

    /**
      * If the bishop is blocked for the right bottom position 
    **/
    public boolean isRightBottomBlocked;

    public BishopMovementStates() {
        this.isLeftTopBlocked = false;
        this.isRightBottomBlocked = false;
        this.isRightTopBlocked = false;
        this.isLeftBottomBlocked = false;
    }
}