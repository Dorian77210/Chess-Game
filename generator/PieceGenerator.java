import java.io.*;


public class PieceGenerator {
    
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
    
    public static void main(String[] args) {
        String file = args[0];
        byte[][] masks = {
            {BLACK_ROOK_MASK, BLACK_KNIGHT_MASK, BLACK_BISHOP_MASK, BLACK_QUEEN_MASK, BLACK_KING_MASK, BLACK_BISHOP_MASK, BLACK_KNIGHT_MASK, BLACK_ROOK_MASK},
            {BLACK_PAWN_MASK, BLACK_PAWN_MASK, BLACK_PAWN_MASK, BLACK_PAWN_MASK, BLACK_PAWN_MASK, BLACK_PAWN_MASK, BLACK_PAWN_MASK, BLACK_PAWN_MASK},
            {EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK},
            {EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK},
            {EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK},
            {EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK, EMPTY_CELL_MASK},
            {WHITE_PAWN_MASK, WHITE_PAWN_MASK, WHITE_PAWN_MASK, WHITE_PAWN_MASK, WHITE_PAWN_MASK, WHITE_PAWN_MASK, WHITE_PAWN_MASK, WHITE_PAWN_MASK},
            {WHITE_ROOK_MASK, WHITE_KNIGHT_MASK, WHITE_BISHOP_MASK, WHITE_QUEEN_MASK, WHITE_KING_MASK, WHITE_BISHOP_MASK, WHITE_KNIGHT_MASK, WHITE_ROOK_MASK}
        };

        DataOutputStream outfile = null;
        try {
            outfile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(file))));

            try {
                for(int i = 0; i < 8; i++) {
                    for(int j = 0; j< 8; j++) {
                        outfile.writeByte(masks[i][j]);
                    }
                }
            } catch(IOException writeException) {
                System.err.println("Error when writing masks");
            }

            try {
                outfile.close();
            } catch(IOException closeException) {
                System.err.println("Error when closing file");
            }

        } catch(FileNotFoundException fileNotFoundException) {
            System.err.println("Error when searching file");
        }
    }
}