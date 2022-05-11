import java.util.LinkedList;

abstract class  Piece {

    Position position;
    boolean isWhite;
    String name;
    boolean moved_before;
    public Piece(char xp,char yp,boolean isWhite,     String name)
    {

        position = new Position(xp, yp);
        System.out.println("new Piece "+ name +' '+position.rank +','+ position.file);

        this.isWhite = isWhite;
        this.name = name;
        moved_before = false;
    }
    public void move(char xp,char yp)
    {
        moved_before = true;
        position.file = xp;
        position.rank = yp;
    }
    abstract  public LinkedList<Position> optionalMoves() ;
    public String getName(){
        return name;
    }
    public boolean isWhite()
    {
        return isWhite;
    }

    public void notify_()
    {
        moved_before = false;
    }



}
