package helper.collections;

import ui.board.Cell;

import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.function.Predicate;

/**
  * The class <code>CellCollection</code> represents a custom arraylist with cells
  * @version 1.0
  * @author Dorian Terbah 
**/

public class CellCollection extends ArrayList<Cell> {

    public CellCollection() {
        super();
    }

    public CellCollection(CellCollection cellCollection) {
        super();
        this.addAll(cellCollection);
    }

    /***************************** 
    ***********GETTER************* 
    ******************************/

    /**
      * Get the first cell of the collection
      * @return The first piece of the collection 
    **/
    public Cell first() {
        return this.get(0);
    }

    /**
      * Filter the collection with a predicate
      * @param predicate The predicate
      * @return The filtered collection 
    **/
    public void filter(Predicate predicate) {
        this.stream().filter(predicate);
    }


    /**
      * get the last piece of the collection
      * @return The last piece of the collection 
    **/
    public Cell last() {
        return this.get(this.size() - 1);
    }
}