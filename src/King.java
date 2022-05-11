import java.util.LinkedList;

public class King extends Piece{

    public King(char xp, char yp, boolean isWhite) {
        super(xp, yp, isWhite, "king");
    }

    public LinkedList<Position> optionalMoves() {
        LinkedList<Position> v = new LinkedList<>();
        Board board = Board.getInstance();
        char rank = position.rank;
        char file = position.file;
        for(char r = (char) (rank -1); r <= rank + 1; r++)
            for (char f = (char) (file - 1); f <= file + 1; f++) {
                if(r >= '1' && r <= '8'
                        && f >= 'a' && f<= 'h'
                        && (f != file || r != rank))
                    if(!board.TheresSomethingThere(f, r )
                            || board.getByBox(new Position(f, r )).isWhite!= this.isWhite) {
                        v.add(new Position(f, r));
                    }
            }
        if (board.castling(isWhite, board.LONG)) {
            v.add(new Position('c', position.rank ));
        }
        if (board.castling(isWhite, board.SHORT)) {
            v.add(new Position( 'g', position.rank ));
        }
        return v;
    }
}
