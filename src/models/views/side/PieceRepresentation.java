package models.views.side;

import enums.PlayerType;
import enums.KindOfPiece;

/**
  * The class <code>PieceRepresentation</code> the piece representation
  * @version 1.0
  * @author Dorian Terbah 
**/

public class PieceRepresentation {

    /**
      * The kind of piece 
    **/
    private KindOfPiece kind;

    /**
      * The type of the player 
    **/
    private PlayerType type;

    public PieceRepresentation(KindOfPiece kind, PlayerType type) {
        this.kind = kind;
        this.type = type;
    }

    /***************************** 
    *************GETTER*********** 
    *****************************/

    /**
      * Get the kind of piece
      * @return The kind of piece 
    **/
    public KindOfPiece getKindOfPiece() {
        return this.kind;
    }

    /**
      * Get the type of the player
      * @return The typeof the player  
    **/
    public PlayerType getPlayerType() {
        return this.type;
    }
}