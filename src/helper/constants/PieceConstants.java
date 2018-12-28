package helper.constants;

/**
  * The class <code>PieceConstantds</code> represents all constants used in the initialize file for the board
  * @version 1.0
  * @author Dorian Terbah 
**/

public class PieceConstants {
    
    public static final byte EMPTY_CELL_MASK = 0x0;

    //white pieces mask
    public static final byte WHITE_PAWN_MASK = 0x1;
    public static final byte WHITE_ROOK_MASK = 0x2;
    public static final byte WHITE_BISHOP_MASK = 0x3;
    public static final byte WHITE_QUEEN_MASK = 0x4;
    public static final byte WHITE_KING_MASK = 0x5;
    public static final byte WHITE_KNIGHT_MASK = 0x6;

    //black pieces mask
    public static final byte BLACK_PAWN_MASK = 0x7;
    public static final byte BLACK_ROOK_MASK = 0x8;
    public static final byte BLACK_BISHOP_MASK = 0x9;
    public static final byte BLACK_QUEEN_MASK = 0xa;
    public static final byte BLACK_KING_MASK = 0xb;
    public static final byte BLACK_KNIGHT_MASK = 0xc;
}