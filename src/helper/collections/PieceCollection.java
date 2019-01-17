package helper.collections;

import models.game.pieces.*;

import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.function.Predicate;

/**
  * The class <code>PieceCollection</code> represents a custom arraylist with pieces
  * @version 1.0
  * @author Dorian Terbah 
**/

public class PieceCollection extends ArrayList<Piece> {

    public PieceCollection() {
        super();
    }

    public PieceCollection(PieceCollection pieceCollection) {
        super();
        this.addAll(pieceCollection);
    }

    /***************************** 
    ***********GETTER************* 
    ******************************/

    /**
      * Get the first piece of the collection
      * @return The first piece of the collection 
    **/
    public Piece first() {
        return this.get(0);
    }

    /**
      * Get all pieces by instance
      * @param instance The instance of the wanted pieces 
      * @return All pieces according to the wanted instance 
    **/
    public PieceCollection getPiecesByInstance(Class instance) {
        PieceCollection pieceCollection = new PieceCollection();

        for(Piece piece : this) {
            if(piece.getClass().equals(instance)) {
                pieceCollection.add(piece);
            }
        }

        return pieceCollection;
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
    public Piece last() {
        return this.get(this.size() - 1);
    }

    /**
      * Remove all occurences of a piece
      * @param piece The piece to remove 
    **/
    public void removeAll(Piece piece) {
        Piece currentPiece;
        int i;
        
        for(i = 0; i < this.size(); i++) {
            currentPiece = this.get(i);
            if(currentPiece.equals(piece)) {
                this.remove(currentPiece);
            } else {
                i++;
            }
        }
    }

    /**
      * Get the pieces by color
      * @param isWhite The isWhite state
      * @return A collection with this condition 
    **/
    public PieceCollection getPiecesByColor(boolean isWhite) {
        PieceCollection collection = new PieceCollection();
        for(Piece piece : this) {
            if(piece.isWhitePiece() == isWhite) {
                collection.add(piece);
            }
        }

        return collection;
    }

    /***************************** 
    ************CLONE************* 
    ******************************/

    @Override 
    /**
      * Return a cloned instance of this
      * @return A new cloned instance of this
    **/
    public PieceCollection clone() {
        PieceCollection collection = new PieceCollection();
        for(Piece piece : this) {
            collection.add(piece.clone());
        }

        return collection;
    }
}