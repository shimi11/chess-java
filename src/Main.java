import java.util.LinkedList;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Board board =  Board.getInstance();
        LinkedList<Piece>  pieces = board.getPieces();


        BoardGUI boardGUI = new BoardGUI(pieces);
        boardGUI.update(pieces);
        //boardGUI.update(pieces);

    }
}
