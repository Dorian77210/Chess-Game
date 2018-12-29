package helper;

import helper.Position;

public class Distance {

    /**
      * Get the distance between two positions
      * @param p The first position
      * @param q The second distance
      * @return The distance between the two positions 
    **/
    public static double getDistance(Position p, Position q) {
        double x = Math.pow(p.x - q.x, 2);
        double y = Math.pow(p.y - q.y, 2);
        return Math.sqrt(x + y);
    }

    /**
      * Get the Manhattan between two positions
      * @param p The first position
      * @param q The second distance
      * @return The Manhattan distance between the two positions 
    **/
    public static double getManhattanDistance(Position p, Position q) {
        return (double)(Math.abs(p.x - q.x) + (Math.abs(p.y - q.y)));
    }
}