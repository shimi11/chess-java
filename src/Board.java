import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Board {

    public static final byte LONG = 1;
    public static final byte SHORT = -1;

    private static Board single_instance = null;
    HashMap<Character, Piece> mapBoard ;
    LinkedList<Piece> pieces ;
    LinkedList<Piece> killedPieces ;
    King[] kingList =new King[2];

    boolean isWhiteTor = true;
    Stack<DataMove> moves;

    /*private Board()
    {
        pieces = new LinkedList<>();
        killedPieces = new LinkedList<>();
        moves = new Stack<>();
        pieces.add(new Rook('a', '8', false));
        pieces.add(new Knight('b', '8', false));
        pieces.add(new Bishop('c', '8', false));
        pieces.add(new Queen('d', '8', false));
        kingList[0] = new King('e', '8', false);
        pieces.add(kingList[0]);
        pieces.add(new Bishop('f', '8', false));
        pieces.add(new Knight('g', '8', false));
        pieces.add(new Rook('h', '8', false));

        pieces.add(new Pawn('a', '7', false));
        pieces.add(new Pawn('b', '7', false));
        pieces.add(new Pawn('c', '7', false));
        pieces.add(new Pawn('d', '7', false));
        pieces.add(new Pawn('e', '7', false));
        pieces.add(new Pawn('f', '7', false));
        pieces.add(new Pawn('g', '7', false));
        pieces.add(new Pawn('h', '7', false));

        pieces.add(new Rook('a', '1', true));
        pieces.add(new Knight('b', '1', true));
        pieces.add(new Bishop('c', '1', true));
        pieces.add(new Queen('d', '1', true));
        kingList[1] = new King('e', '1', true);
        pieces.add(kingList[1]);
        pieces.add(new Bishop('f', '1', true));
        pieces.add(new Knight('g', '1', true));
        pieces.add(new Rook('h', '1', true));

        pieces.add(new Pawn('a', '2', true));
        pieces.add(new Pawn('b', '2', true));
        pieces.add(new Pawn('c', '2', true));
        pieces.add(new Pawn('d', '2', true));
        pieces.add(new Pawn('e', '2', true));
        pieces.add(new Pawn('f', '2', true));
        pieces.add(new Pawn('g', '2', true));
        pieces.add(new Pawn('h', '2', true));




        mapBoard = new HashMap<Character, Piece>();
        for (Piece p:pieces) {
            mapBoard_put(p.position.file ,p.position.rank,p);
        }
    }*/
    private Board()//castling
    {
        pieces = new LinkedList<>();
        killedPieces = new LinkedList<>();
        moves = new Stack<>();
        pieces.add(new Rook('a', '8', false));
        pieces.add(new Knight('b', '8', false));
        pieces.add(new Bishop('c', '8', false));
        pieces.add(new Queen('d', '8', false));
        kingList[0] = new King('e', '8', false);
        pieces.add(kingList[0]);
        //pieces.add(new Bishop('f', '8', false));
        //pieces.add(new Knight('g', '8', false));
        pieces.add(new Rook('h', '8', false));

        pieces.add(new Pawn('a', '7', false));
        pieces.add(new Pawn('b', '7', false));
        pieces.add(new Pawn('c', '7', false));
        pieces.add(new Pawn('d', '7', false));
        pieces.add(new Pawn('e', '7', false));
        pieces.add(new Pawn('f', '7', false));
        pieces.add(new Pawn('g', '7', false));
        pieces.add(new Pawn('h', '7', false));

        pieces.add(new Rook('a', '1', true));
        pieces.add(new Knight('b', '1', true));
        pieces.add(new Bishop('c', '1', true));
        pieces.add(new Queen('d', '1', true));
        kingList[1] = new King('e', '1', true);
        pieces.add(kingList[1]);
        pieces.add(new Bishop('f', '1', true));
        pieces.add(new Knight('g', '1', true));
        pieces.add(new Rook('h', '1', true));

        pieces.add(new Pawn('a', '2', true));
        pieces.add(new Pawn('b', '2', true));
        pieces.add(new Pawn('c', '2', true));
        pieces.add(new Pawn('d', '2', true));
        pieces.add(new Pawn('e', '2', true));
        pieces.add(new Pawn('f', '2', true));
        pieces.add(new Pawn('g', '2', true));
        pieces.add(new Pawn('h', '2', true));




        mapBoard = new HashMap<>();
        for (Piece p:pieces) {
            mapBoard_put(p.position.file ,p.position.rank,p);
        }

    }
    public LinkedList<Piece> getPieces()
    {
        LinkedList<Piece> outPieces;// = new LinkedList<Piece>();
        outPieces = (LinkedList) pieces.clone();
        return outPieces;
    }

    public static Board getInstance()
    {
        if (single_instance == null)
            single_instance = new Board();

        return single_instance;
    }
    private char getKey(Position position)
    {
        return getKey(position.file ,position.rank);
    }
    private char getKey(char file, char rank)
    {
        return (char) (file +rank*8);
    }
    public Piece getByBox(Position position)
    {//a 1 -> h 8
        return mapBoard.get(getKey(position)) ;
    }
    public Piece getByBox(char file, char rank)
    {
        return mapBoard.get((char) (file +rank*8)) ;
    }
    public boolean TheresSomethingThere(Position position)
    {
        return (mapBoard.get(getKey(position)) != null);
    }
    public boolean TheresSomethingThere(char file, char rank)
    {
        return (mapBoard.get((char) (file +rank*8)) != null);
    }
    public Piece mapBoard_put(Position position,Piece piece)
    {
        return mapBoard.put(getKey(position),piece);
    }
    public Piece mapBoard_put(char file, char rank,Piece piece)
    {
        return mapBoard.put(getKey(file ,rank),piece);
    }
    public Piece mapBoard_remove(char file, char rank)
    {
        return  mapBoard.remove(getKey(file ,rank));
    }
    public Piece mapBoard_remove(Position position)
    {
        return  mapBoard.remove(getKey(position));
    }




    public boolean isKingDanger(boolean isWhite)
    {
        LinkedList<Position> v;
        Position kingPos = kingList[isWhite ? 1:0].position;

        for (Piece p : pieces)
        {
            if(p.isWhite !=isWhite) {
                v = p.optionalMoves();
                for (Position pos : v) {
                    if (pos == kingPos)
                        return true;
                }
                v.clear();
            }
        }
        return false;
    }
    public boolean castling(boolean isWhite, byte side)
    {
        if (kingList[isWhite? 1:0].moved_before)
            return false;

       /* if (kingList[isWhiteTor? 1:0].get_position().file != 'e')
        {
            return false;
        }*/

        Position PosKing = new Position(kingList[isWhiteTor? 1:0].position);
        Position PosRook = new Position(PosKing);
        Piece piece;
        switch (side) {
            case SHORT -> {
                PosRook.file = 'h';
                piece = getByBox(PosRook);
                if (piece == null || !piece.name.equals("rook") || piece.moved_before)
                    return false;
                if (TheresSomethingThere('f', PosKing.rank))
                    return false;
                if (TheresSomethingThere('g', PosKing.rank))
                    return false;
                doMove(new Move('e', PosKing.rank, 'f', PosKing.rank));
                if (isKingDanger(isWhite)) {
                    unDoMove();
                    return false;
                }
                doMove(new Move('f', PosKing.rank, 'g', PosKing.rank));
                if (isKingDanger(isWhite)) {
                    unDoMove();
                    unDoMove();
                    return false;
                }
                unDoMove();
                unDoMove();
            }
            case LONG -> {
                PosRook.file = 'a';
                piece = getByBox(PosRook);
                if (piece == null || !piece.name.equals("rook") || piece.moved_before)
                    return false;
                if (TheresSomethingThere('b', PosKing.rank))
                    return false;
                if (TheresSomethingThere('c', PosKing.rank))
                    return false;
                if (TheresSomethingThere('d', PosKing.rank))
                    return false;
                doMove(new Move('e', PosKing.rank, 'd', PosKing.rank));
                if (isKingDanger(isWhite)) {
                    unDoMove();
                    return false;
                }
                doMove(new Move('d', PosKing.rank, 'c', PosKing.rank));
                if (isKingDanger(isWhite)) {
                    unDoMove();
                    unDoMove();
                    return false;
                }
                unDoMove();
                unDoMove();
            }
            default -> {
            }
        }
        return true;
    }
    public boolean isCastling(Move move)
    {
        return move.from.file == 'e' && (move.to.file == 'g' || move.to.file == 'c')
                && (move.to.rank == '1' || move.to.rank == '8');
    }
    public boolean isPromotion(boolean isWhite, Position position)
    {
        return (isWhite && position.rank == '8')
                || (!isWhite && position.rank == '1');
    }
    private Piece doPromotion(boolean color, Position position)
    {
        //return new Queen(color, position);
        return null;
    }

    public LinkedList<Piece> doMove(Move move)
    {
        DataMove dataMove = new DataMove();
        dataMove.from = move.from;
        dataMove.to = move.to;
        moves.push(dataMove);
        if (!getByBox(move.from).moved_before) {
            dataMove.first_move = true;
        }

        Piece pToMove =mapBoard_remove(move.from);
        Piece pieceToDelete = getByBox(move.to);
        mapBoard_put(move.to, pToMove);
        pToMove.move(move.to.file ,move.to.rank);

        if (pToMove.name.equals("king") && dataMove.first_move
            && isCastling(move))
        {
            dataMove.castling = true;
            if (move.to.file == 'g' && move.from.file == 'e')//short
            {
                Piece pRook = getByBox('h', move.to.rank);
                mapBoard_put('f' ,move.to.rank, pRook);
                pRook.move('f',move.to.rank );
            }
            else
            {
                Piece pRook = getByBox('a', move.to.rank);
                mapBoard_put('d',move.to.rank, pRook);
                mapBoard_remove( 'a',move.to.rank);
                pRook.move('d',move.to.rank );
            }

        }
	    else
        {
            if(pToMove.name.equals("pawn")
                && isPromotion(pToMove.isWhite, move.to))
            {
                dataMove.crowned = pToMove;
                mapBoard_put(move.to, doPromotion(dataMove.crowned.isWhite, move.to));
            }

            if (pieceToDelete != null)
            {
                dataMove.killed = pieceToDelete;
                pieces.remove(pieceToDelete);
                killedPieces.add(pieceToDelete);
            }
        }
        return pieces;
    }

    public LinkedList<Position> legalMoves(Piece piece)
    {
        LinkedList<Position> v =new LinkedList<>();
        LinkedList<Position> vOptionalMove = piece.optionalMoves();
        if (vOptionalMove.isEmpty())
        {
            return v;
        }
        Position startPos = piece.position;
        Move move=new Move(startPos.file,startPos.rank,'0','0');

        for (Position p: vOptionalMove)
        {
            move.to = p;
            doMove(move);
            if (!isKingDanger(piece.isWhite))
            {
                v.add(p);
            }
            unDoMove();
        }
        return v;
    }

    public void unDoMove()
    {
       /* if (moves.empty())
        {
            return;
        }*/
        DataMove dataMove = moves.pop();
        Piece pUnMove =mapBoard.remove((char) (dataMove.to.file +dataMove.to.rank*8));
        mapBoard_put(dataMove.from,pUnMove);
        pUnMove.position.file = dataMove.from.file;
        pUnMove.position.rank = dataMove.from.rank;

        if (dataMove.castling)
        {
            char cFrom = 'a';
            char cTo = 'd';

            if (dataMove.from.file == 'e' && dataMove.to.file == 'g')//short
            {
                cFrom = 'h';
                cTo = 'f';
            }

            Piece pRook = getByBox(cTo, dataMove.to.rank);
            mapBoard_put( cFrom ,dataMove.to.rank, pRook);
            mapBoard_remove( cTo,dataMove.to.rank);
            pRook.move(cFrom,dataMove.to.rank );
            pRook.notify_();
        }
        else
        {
            if (dataMove.crowned != null)
            {
                mapBoard_put(dataMove.from, dataMove.crowned);

                dataMove.crowned.move(dataMove.from.file,dataMove.from.rank);
            }
            if (dataMove.killed != null)
            {

                killedPieces.remove(dataMove.killed);

                pieces.add(dataMove.killed);
                mapBoard_put(dataMove.killed.position, dataMove.killed);
            }
        }
        if (dataMove.first_move)
            getByBox(dataMove.from).notify_();
    }

}
