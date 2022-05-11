import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math;


public class Knight extends Piece {


    public Knight(char xp, char yp, boolean isWhite) {
        super(xp, yp, isWhite, "knight");
    }

    public LinkedList<Position> optionalMoves() {
        LinkedList<Position> v = new LinkedList<>();
        for (char file = (char) (position.file - 2); file <= position.file + 2; file++)
            for (char rank = (char) (position.rank - 2); rank <= position.rank + 2; rank++) {
                if (file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8')
                    if (Math.pow((position.file - file), 2) + Math.pow((position.rank - rank), 2) == 5)
                        if (!Board.getInstance().TheresSomethingThere(file, rank)
                        || Board.getInstance().getByBox(file, rank).isWhite() != isWhite)
                v.add(new Position(file, rank));
            }
        return v;
    }
}