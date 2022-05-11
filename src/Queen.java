import java.util.ArrayList;
import java.util.LinkedList;


public class Queen extends Piece{


    public Queen(char xp, char yp, boolean isWhite) {
        super(xp, yp, isWhite, "queen");
    }

    public LinkedList<Position> optionalMoves() {
        LinkedList<Position> l = new LinkedList<>();
        l.addAll(optionalMovesRook());
        l.addAll(optionalMovesBishop());
        return l;

    }
    public LinkedList<Position> optionalMovesRook() {
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
    public LinkedList<Position> optionalMovesBishop() {
        LinkedList<Position> v = new LinkedList<>();
        Board board =Board.getInstance();

        char i = (char) (position.file-1);
        char j = (char) (position.rank-1);
        for (; (i >='a' && j >='1'); i--, j--)
        {
            if (board.TheresSomethingThere(i,j)) {
                if(board.getByBox(i,j).isWhite != isWhite)
                {
                    v.add(new Position(i ,j));
                }
                break;
            }
            v.add(new Position(i ,j));
        }
        i = (char) (position.file+1);
        j = (char) (position.rank+1);
        for (; (i <='h' && j <='8'); i++, j++)
        {
            if (board.TheresSomethingThere(i,j)) {
                if(board.getByBox(i,j).isWhite != isWhite)
                {
                    v.add(new Position(i ,j));
                }
                break;
            }
            v.add(new Position(i ,j));
        }

        i = (char) (position.file+1);
        j = (char) (position.rank-1);
        for (; (i <='h' && j >='1'); i++, j--)
        {
            if (board.TheresSomethingThere(i,j)) {
                if(board.getByBox(i,j).isWhite != isWhite)
                {
                    v.add(new Position(i ,j));
                }
                break;
            }
            v.add(new Position(i ,j));
        }
        i = (char) (position.file-1);
        j = (char) (position.rank+1);
        for (; (i >='a' && j <='8'); i--, j++)
        {
            if (board.TheresSomethingThere(i,j)) {
                if(board.getByBox(i,j).isWhite != isWhite)
                {
                    v.add(new Position(i ,j));
                }
                break;
            }
            v.add(new Position(i ,j));
        }
        return v;
    }


}