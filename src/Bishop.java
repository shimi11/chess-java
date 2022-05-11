import java.util.ArrayList;
import java.util.LinkedList;


public class Bishop extends Piece{


    public Bishop(char xp, char yp, boolean isWhite) {
        super(xp, yp, isWhite, "bishop");
    }

    public LinkedList<Position> optionalMoves() {
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