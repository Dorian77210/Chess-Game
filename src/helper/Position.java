package helper;

/**
  * The class <code>Position</code> represents the position of a cell or a piece
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Position {

    /**
      * The x coordinate in the board 
    **/
    public int x;

    /**
      * The y coordinate in the board  
    **/
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
	}
	
	public boolean equals(Position position) {
		return ((this.x == position.x) && (this.y == position.y));
	}
}