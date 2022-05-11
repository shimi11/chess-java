
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.*;


public class BoardGUI extends JFrame implements MouseListener {

    final JLabel[][] labels = new JLabel[8][8];
    Image imgs[];
    LinkedList<Position> toMave;
    Position toMaveFrom ;
    int squareSize = 0;
    public BoardGUI(LinkedList<Piece> ps) throws IOException
    {

        /*BufferedImage all=ImageIO.read(new File("chess.png"));
        Image imgs[]=new Image[12];
        int ind=0;
        for(int y=0;y<400;y+=200){
            for(int x=0;x<1200;x+=200){
                imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        for(int i=0;i<12;i++) {
            try {
                BufferedImage bi = new BufferedImage(imgs[i].getWidth(null),
                        imgs[i].getHeight(null), BufferedImage.TYPE_INT_ARGB);
                bi.getGraphics().drawImage(imgs[i],0,0, null);
                ImageIO.write(bi, "PNG", new File(i+".png"));

            } catch (IOException e) {

            }

        }*/
        imgs =new Image[12];
        for(int i=0;i<12;i++) {
            try {
                BufferedImage bi=ImageIO.read(new File(i+".png"));
                imgs[i] = bi;
            } catch (IOException e) {

            }

        }
        //frame = new JFrame();
        //this.setBounds(10, 10, 512, 512);
        this.setBounds(10, 10, 1000, 1000);
        //frame.setUndecorated(true);
        //frame.setResizable(false);
        squareSize = this.getWidth()/9;
/*
        System.out.println("frame width : "+frame.getWidth());
        System.out.println("frame height: "+frame.getHeight());
        System.out.println("content pane width : "+frame.getContentPane().getWidth());
        System.out.println("content pane height: "+frame.getContentPane().getHeight());
        System.out.println("width  of left + right  borders: "+(frame.getWidth()-frame.getContentPane ().getWidth()));
        System.out.println("height of top  + bottom borders: "+(frame.getHeight()-frame.getContentPane().getHeight()));
*/
        JPanel panel = new JPanel();

        boolean white=true;
        for(int y= 0;y<8;y++){
            for(int x= 0;x<8;x++){
                labels[y][x] = new JLabel();
                if(white){
                    //labels[y][x].setBackground(Color.red);
                    labels[y][x].setBackground(new Color(200,200, 200));
                }else{
                    //labels[y][x].setBackground(Color.blue);
                    labels[y][x].setBackground(new Color(200, 100, 0));
                }

                labels[y][x].setOpaque(true);
                labels[y][x].setBounds(x*64, y*64, 64, 64);
                labels[y][x].addMouseListener(this);
                panel.add(labels[y][x]);

                white=!white;
            }
            white=!white;
        }
        //this.add(pn);
        panel.setLayout(null);
        this.add(panel);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);

    }

    void update(LinkedList<Piece> ps)
    {
        for(JLabel pLine[]: labels)
        {
            for(JLabel label: pLine)
            {
                label.setIcon(null);
            }
        }
        for(Piece p: ps){
            int ind=0;
            if(p.getName().equalsIgnoreCase("king")){
                ind=0;
            }
            if(p.getName().equalsIgnoreCase("queen")){
                ind=1;
            }
            if(p.getName().equalsIgnoreCase("bishop")){
                ind=2;
            }
            if(p.getName().equalsIgnoreCase("knight")){
                ind=3;
            }
            if(p.getName().equalsIgnoreCase("rook")){
                ind=4;
            }
            if(p.getName().equalsIgnoreCase("pawn")){
                ind=5;
            }
            if(!p.isWhite()){
                ind+=6;
            }
            labels[p.position.rank-'1'][p.position.file-'a'].setIcon(new ImageIcon(imgs[ind]));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object src = e.getSource();
        int x = -1;
        int y = -1;
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                if (src == labels[i][j]) {
                    x = i;
                    y = j;
                    break;
                }
            }
            if (x >= 0) {
                break;
            }
        }
        if (x >= 0) {
            System.out.printf("JLabel[%d][%d] was clicked.%n", x, y);

            toMavePiece(new Position((char) (y+'a'), (char)(x +'1')));
        }
        else {
            System.out.println("Could not find clicked label.");
        }

    }
    public void toMavePiece(Position pos)
    {
        Board board = Board.getInstance();
        if(board.TheresSomethingThere(pos))
        {
            System.out.println("clicked of "+board.getByBox(pos));

            if(board.getByBox(pos).isWhite == board.isWhiteTor) {



                toMaveFrom = pos;
                if (toMave != null)
                    unMark();
                toMave = board.legalMoves(board.getByBox(pos));
                System.out.println(toMave);

                toMark();
            }
        } else if (toMave != null) {
                System.out.println("11111");

                for (Position p:toMave) {
                    System.out.println("22222");
                    if(p.file == pos.file && p.rank ==pos.rank)
                    {
                        System.out.println("33333");
                        unMark();
                        LinkedList<Piece> pieces = board.doMove(new Move( toMaveFrom, pos));
                        //LinkedList<Piece> pieces =board.mave(toMaveFrom, pos);
                        if(pieces !=null)
                        {
                            board.isWhiteTor = !board.isWhiteTor;
                            update(pieces);
                            System.out.println("44444");
                        }
                        break;
                    }
                }
        }


    }
    public void toMark()
    {
        //System.out.println("aaaa");

        for (Position pos:toMave) {
            Color color = labels[pos.rank-'1'][pos.file-'a'].getBackground();
            //color.
            labels[pos.rank-'1'][pos.file-'a'].setBackground(new Color(
                    color.getRed()+50,   color.getGreen(),color.getBlue()));
        }
    }
    public void unMark()
    {
        for (Position pos:toMave) {
            Color color = labels[pos.rank-'1'][pos.file-'a'].getBackground();
            //color.
            labels[pos.rank-'1'][pos.file-'a'].setBackground(new Color(
                    color.getRed()-50,   color.getGreen(),color.getBlue()));
        }
        toMave.clear();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
