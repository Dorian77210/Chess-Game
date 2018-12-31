package ui.side;

import enums.KindOfPiece;
import enums.PlayerType;

import ui.Window;

import engine.Engine;

import models.game.pieces.Piece;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridLayout;

import java.util.ArrayList;

/**
  * The class <code>PieceCountView</code> represents the count of the pieces display
  * @version 1.0
  * @author Dorian Terbah 
**/

public class PieceCountView extends JPanel {

    /**
      * All of the panels 
    **/
    private ArrayList<ItemView> items;

    /**
      * kind of pieces 
    **/
    private static final KindOfPiece[] kinds = new KindOfPiece[] {
        KindOfPiece.KIND_BISHOP, KindOfPiece.KIND_KING, KindOfPiece.KIND_KNIGHT, KindOfPiece.KIND_PAWN, KindOfPiece.KIND_QUEEN, KindOfPiece.KIND_ROOK,
        KindOfPiece.KIND_BISHOP, KindOfPiece.KIND_KING, KindOfPiece.KIND_KNIGHT, KindOfPiece.KIND_PAWN, KindOfPiece.KIND_QUEEN, KindOfPiece.KIND_ROOK
    };

    /**
      * Types of player 
    **/
    private static final PlayerType[] types = new PlayerType[] {
        PlayerType.BLACK_PLAYER, PlayerType.BLACK_PLAYER, PlayerType.BLACK_PLAYER, PlayerType.BLACK_PLAYER, PlayerType.BLACK_PLAYER, PlayerType.BLACK_PLAYER,
        PlayerType.WHITE_PLAYER, PlayerType.WHITE_PLAYER, PlayerType.WHITE_PLAYER, PlayerType.WHITE_PLAYER, PlayerType.WHITE_PLAYER, PlayerType.WHITE_PLAYER
    };

    public PieceCountView() {
        super(new GridLayout(6, 2));

        this.items = new ArrayList<ItemView>();

        this.setPreferredSize(new Dimension(200, Window.HEIGHT));

        String blackBasePath = Piece.BASE_PATH + "black-side/";
        String whiteBasePath = Piece.BASE_PATH + "white-side/";

        String[] paths = new String[] {
            blackBasePath + "bishop.png", blackBasePath + "king.png", blackBasePath + "knight.png", blackBasePath + "pawn.png", blackBasePath + "queen.png", blackBasePath + "rook.png",
            whiteBasePath + "bishop.png", whiteBasePath + "king.png", whiteBasePath + "knight.png", whiteBasePath + "pawn.png", whiteBasePath + "queen.png", whiteBasePath + "rook.png"
        };

        //generate itemViews
        for(int i = 0; i < paths.length; i++) {
            ItemView item = new ItemView(paths[i], kinds[i], types[i]); 
            this.items.add(item);
            this.add(item);
        }
    }

    /**
      * Refresh all counts 
    **/
    public void refreshCounts() {
        for(ItemView item : this.items) {
            item.refreshCount();
        }
    }
} 