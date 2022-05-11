public class Position {
    char file;
    char rank;

    public Position(char file, char rank ) {
        this.file = file;
        this.rank = rank;
    }
    public Position(Position other ) {
        this.file = other.file;
        this.rank = other.rank;
    }
}
