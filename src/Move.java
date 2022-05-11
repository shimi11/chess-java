public class Move {
    Position from;
    Position to;
    Move(char from_file,char from_rank,char to_file,char to_rank)
    {
        from = new Position(from_file,from_rank);
        to = new Position(to_file,to_rank);
    }
    Move(Position pFrom,Position pTo)
    {
        from = new Position(pFrom.file,pFrom.rank);
        to = new Position(pTo.file,pTo.rank);
    }
}
