import java.util.LinkedList;


public class Pawn extends Piece{


    public Pawn(char xp, char yp, boolean isWhite) {
        super(xp, yp, isWhite, "pawn");
    }

    public LinkedList<Position> optionalMoves() {
        LinkedList<Position> v = new LinkedList<>();
        int step = (this.isWhite) ? 1 : -1;
        char start_position = (this.isWhite) ? '2' : '7';
        Board board = Board.getInstance();
        Position pos = new Position(position.file, (char)(position.rank + 2 * step) );
        if (position.rank == start_position) {
            if (!board.TheresSomethingThere(pos.file, (char)(pos.rank - step))
                    && !board.TheresSomethingThere(pos))
                v.add(new Position(pos));//go 2
        }
        pos.file =  position.file;
        pos.rank =  (char)(position.rank + step) ;
        if (pos.rank<='8' &&pos.rank >='1' && !board.TheresSomethingThere(pos))
            v.add(new Position(pos));//go 1
        if (pos.file > 'a') {
            pos.file = (char)(position.file - 1);
            if (board.TheresSomethingThere(pos)) {
                if (board.getByBox(pos).isWhite != this.isWhite)
                    v.add(new Position(pos));//go Left
            }

        }
        pos.file = (char)(position.file + 1);
        if (pos.file <= 'h') {
            if (board.TheresSomethingThere(pos)) {
                if (board.getByBox(pos).isWhite != this.isWhite)
                    v.add(new Position(pos));//go Right
            }
        }
        return v;
    }
}