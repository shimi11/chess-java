import java.util.ArrayList;
import java.util.LinkedList;


public class Rook extends Piece{


    public Rook(char xp, char yp, boolean isWhite) {
        super(xp, yp, isWhite, "rook");
    }

    public LinkedList<Position> optionalMoves() {
        LinkedList<Position> v= new LinkedList<>();
        LinkedList<Position> questionable= new LinkedList<>();
        for (char file = (char)(position.file + 1); file <= 'h'; file++) {
            if (Board.getInstance().TheresSomethingThere(file, position.rank)) {
                questionable.add(new Position(file, position.rank));
                break;
            }
            v.add(new Position(file, position.rank));
        }
        for (char file = (char)(position.file - 1); file >= 'a'; file--) {
            if (Board.getInstance().TheresSomethingThere(file, position.rank)) {
                questionable.add(new Position(file, position.rank));
                break;
            }
            v.add(new Position(file, position.rank));
        }
        for (char rank = (char)(position.rank + 1); rank <= '8'; rank++) {
            if (Board.getInstance().TheresSomethingThere(position.file, rank)) {
                questionable.add(new Position(position.file, rank));
                break;
            }
            v.add(new Position(position.file, rank));
        }
        for (char rank = (char)(position.rank - 1); rank >= '1'; rank--) {
            if (Board.getInstance().TheresSomethingThere( position.file, rank )) {
                questionable.add(new Position(position.file, rank));
                break;
            }
            v.add(new Position(position.file, rank));
        }
        for (Position p : questionable) {
            if (Board.getInstance().getByBox(p).isWhite != isWhite)
                v.add(p);
        }
        return v;
    }

}