/**
 * Created by Xi Wang on 2/5/17.
 */
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;


public class Chess extends JFrame implements ActionListener {


    private Container contents;

    private JLabel[][] squares = new JLabel[8][8];//set the chess background. squares is the look of the chess play
    private Tile[][] tiles = new Tile[8][8]; // set the chess tiles to keep track of the chess movement, seperately from the background labels.
    // tiles keep track of the chess at a position(63 intotal) in a stack. each time a chess move to the tile position, the image is pushed in to the stack,
    // when a chess jump to other position, the image is poped out of the stack.  the look of the label is updated whever therer is a change of  status in tile positon
    private Color colorgreen = Color.GREEN;

    private List<move> steps;
    private int cnt = 0;

    //define three buttons
    JButton previous;
    JButton next;
    JButton refresh;

    Boolean turn = true;//white's turn?


    Image black_bishop;
    Image black_king;
    Image black_knight;
    Image black_pawn;
    Image black_queen;
    Image black_rook;

    Image white_bishop;
    Image white_king;
    Image white_knight;
    Image white_pawn;
    Image white_queen;
    Image white_rook;

    String w_k = "74";//keep track of white king position for special case
    String w_q = "73";//keep track of white queen position for special case
    String b_k = "04";//keep track of black king position for special case
    String b_q = "03";//keep track of black queen position for special case


    public Chess() {
        // set up the background color and initalize the chess positions
        try {
            Steps instructions = new Steps();

            steps = instructions.getSteps();
//            for (int i = 0; i< steps.size(); i++) {
//               // System.out.println(steps.get(i).prev + " " + steps.get(i).next);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        contents = getContentPane();
        contents.setLayout(new GridLayout(9, 9));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new JLabel();
                if ((i + j) % 2 != 0) {

                    squares[i][j].setForeground(colorgreen);
                    squares[i][j].setBackground(colorgreen);
                    squares[i][j].setOpaque(true);

                } else {
                    squares[i][j].setForeground(Color.WHITE);
                    squares[i][j].setBackground(Color.WHITE);
                    squares[i][j].setOpaque(true);
                }
                contents.add(squares[i][j]);
            }
        }


        try {
            for (int i = 0; i < 8; i++) {
                 for (int j = 0; j < 8; j++) {
                     tiles[i][j] = new Tile(i, j, null, null);
                 }
            }
            //initialize the chess positions
            black_bishop = ImageIO.read(getClass().getResource("black_bishop.png"));
            squares[0][2].setIcon(new ImageIcon(black_bishop));
            squares[0][5].setIcon(new ImageIcon(black_bishop));
            tiles[0][2].setImage(black_bishop);
            tiles[0][2].setName("b_bishop");
            tiles[0][5].setImage(black_bishop);
            tiles[0][5].setName("b_bishop");
            tiles[0][2].getStack().add(black_bishop);
            tiles[0][5].getStack().add(black_bishop);

            black_king = ImageIO.read(getClass().getResource("black_king.png"));
            squares[0][4].setIcon(new ImageIcon(black_king));
            tiles[0][4].setImage(black_king);
            tiles[0][4].setName("b_king");
            tiles[0][4].getStack().add(black_king);

            black_knight = ImageIO.read(getClass().getResource("black_knight.png"));
            squares[0][1].setIcon(new ImageIcon(black_knight));
            squares[0][6].setIcon(new ImageIcon(black_knight));
            tiles[0][1].setImage(black_knight);
            tiles[0][6].setImage(black_knight);
            tiles[0][1].setName("b_knight");
            tiles[0][6].setName("b_knight");
            tiles[0][1].getStack().add(black_knight);
            tiles[0][6].getStack().add(black_knight);


            black_pawn = ImageIO.read(getClass().getResource("black_pawn.png"));
            for (int j = 0; j < 8; j++) {
                squares[1][j].setIcon(new ImageIcon(black_pawn));
                tiles[1][j].setImage(black_pawn);
                tiles[1][j].setName("b_pawn");
                tiles[1][j].getStack().add(black_pawn);
            }


            black_queen = ImageIO.read(getClass().getResource("black_queen.png"));
            squares[0][3].setIcon(new ImageIcon(black_queen));
            tiles[0][3].setImage(black_queen);
            tiles[0][3].setName("b_queen");
            tiles[0][3].getStack().add(black_queen);

            black_rook = ImageIO.read(getClass().getResource("black_rook.png"));
            squares[0][0].setIcon(new ImageIcon(black_rook));
            squares[0][7].setIcon(new ImageIcon(black_rook));
            tiles[0][0].setImage(black_rook);
            tiles[0][7].setImage(black_rook);
            tiles[0][0].setName("b_rook");
            tiles[0][7].setName("b_rook");
            tiles[0][7].getStack().add(black_rook);
            tiles[0][0].getStack().add(black_rook);

            white_bishop = ImageIO.read(getClass().getResource("white_bishop.png"));
            squares[7][2].setIcon(new ImageIcon(white_bishop));
            squares[7][5].setIcon(new ImageIcon(white_bishop));
            tiles[7][2].setImage(white_bishop);
            tiles[7][5].setImage(white_bishop);
            tiles[7][2].setName("w_bishop");
            tiles[7][5].setName("w_bishop");
            tiles[7][2].getStack().add(white_bishop);
            tiles[7][5].getStack().add(white_bishop);

            white_queen = ImageIO.read(getClass().getResource("white_queen.png"));
            squares[7][3].setIcon(new ImageIcon(white_queen));
            tiles[7][3].setImage(white_queen);
            tiles[7][3].setName("w_queen");
            tiles[7][3].getStack().add(white_queen);

            white_knight = ImageIO.read(getClass().getResource("white_knight.png"));
            squares[7][1].setIcon(new ImageIcon(white_knight));
            squares[7][6].setIcon(new ImageIcon(white_knight));
            tiles[7][1].setImage(white_knight);
            tiles[7][6].setImage(white_knight);
            tiles[7][1].setName("w_knight");
            tiles[7][6].setName("w_knight");
            tiles[7][6].getStack().add(white_knight);
            tiles[7][1].getStack().add(white_knight);

            white_pawn = ImageIO.read(getClass().getResource("white_pawn.png"));
            for (int j = 0; j < 8; j++) {
                squares[6][j].setIcon(new ImageIcon(white_pawn));
                tiles[6][j].setImage(white_pawn);
                tiles[6][j].setName("w_pawn");
                tiles[6][j].getStack().add(white_pawn);

            }

            white_king = ImageIO.read(getClass().getResource("white_king.png"));
                squares[7][4].setIcon(new ImageIcon(white_king));
                tiles[7][4].setImage(white_king);
                tiles[7][4].setName("w_king");
                tiles[7][4].getStack().add(white_king);


            white_rook = ImageIO.read(getClass().getResource("white_rook.png"));
            squares[7][0].setIcon(new ImageIcon(white_rook));
            squares[7][7].setIcon(new ImageIcon(white_rook));
            tiles[7][0].setImage(white_rook);
            tiles[7][7].setImage(white_rook);
            tiles[7][0].setName("w_rook");
            tiles[7][7].setName("w_rook");
            tiles[7][7].getStack().add(white_rook);
            tiles[7][0].getStack().add(white_rook);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        //set up actionlistenener and handlers
        previous = new JButton("Previous");
        next = new JButton("Next");
        refresh = new JButton("Refresh");

        contents.add(previous, BorderLayout.CENTER);
        contents.add(next, BorderLayout.CENTER);
        contents.add(refresh, BorderLayout.CENTER);

        Handler butt = new Handler();
        previous.addActionListener(this);
        next.addActionListener(this);
        refresh.addActionListener(this);
        previous.addActionListener(butt);
        next.addActionListener(butt);
        refresh.addActionListener(butt);

        pack();
        setSize(700, 700);

        setVisible(true);
        setLocationRelativeTo(null);

    }
    public void actionPerformed(ActionEvent e) {

    }

    private class Handler implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent e ){
            // when previous button is clicked the location(x and y position) of the chess at the that of teh chess moving to are obtained
            //as px, py(previous position) and nx,ny(net position). tiles are updated( chess imaged poped from the previous stack and added to next stack);
            // label image are updated. similar to 'next' case but px, py are nx, ny.
            if (e.getSource() == previous) {
                if (cnt == 0) return;
                cnt--;
                int px = Integer.parseInt(String.valueOf(steps.get(cnt).next.charAt(0)));
                int py = Integer.parseInt(String.valueOf(steps.get(cnt).next.charAt(1)));
                int nx = Integer.parseInt(String.valueOf(steps.get(cnt).prev.charAt(0)));
                int ny = Integer.parseInt(String.valueOf(steps.get(cnt).prev.charAt(1)));

                if ( tiles[px][py].getName() != null && tiles[px][py].getName().equals("w_queen")) {
                    w_q = steps.get(cnt).prev;
                    //System.out.print(w_q);
                }
                if ( tiles[px][py].getName() != null && tiles[px][py].getName().equals("b_queen")) {
                    b_q = steps.get(cnt).prev;
                    //System.out.print(b_q);
                }
                if ( tiles[px][py].getName() != null && tiles[px][py].getName().equals("w_king")) {
                    w_k = steps.get(cnt).prev;
                    //System.out.print(w_k);
                }
                if ( tiles[px][py].getName() != null && tiles[px][py].getName().equals("b_king")) {
                    b_k = steps.get(cnt).prev;
                    //System.out.print(b_k);
                }


                Image cur_image = (Image) tiles[px][py].getStack().pop();
                if(tiles[px][py].getStack().size() == 0 || tiles[px][py].getStack().peek() == null) {
                    squares[px][py].setIcon(null);
                } else {

                    squares[px][py].setIcon(new ImageIcon((Image)(tiles[px][py].getStack().peek())));
                }


                tiles[nx][ny].setName(tiles[px][py].getName());
                tiles[px][py].setName(null);
                tiles[nx][ny].getStack().add(cur_image);
                if (cur_image == null) {
                    squares[nx][ny].setIcon(null);
                } else {
                    squares[nx][ny].setIcon(new ImageIcon(cur_image));
                }
                tiles[px][py].setImage(null);

// when NETX button is clicked the location(x and y position) of the chess at the that of teh chess moving to are obtained
                //as px, py(previous position) and nx,ny(net position). tiles are updated( chess imaged poped from the previous stack and added to next stack);
                // label image are updated.
            } else if (e.getSource() == next) {
                if (cnt == steps.size()) return;

                if(steps.get(cnt).prev.equals("O-O")) {
                    //System.out.print("O-O");
                    //System.out.print(w_k);
                    ooCase();
                }
                if(steps.get(cnt).prev.equals("O-O-O")) {
                    //System.out.println("O-O-O");
                    oooCase();
                }
                if(steps.get(cnt).getPromo() != null) {
                    promoCase();
                }

                int px = Integer.parseInt(String.valueOf(steps.get(cnt).prev.charAt(0)));
                int py = Integer.parseInt(String.valueOf(steps.get(cnt).prev.charAt(1)));
                int nx = Integer.parseInt(String.valueOf(steps.get(cnt).next.charAt(0)));
                int ny = Integer.parseInt(String.valueOf(steps.get(cnt).next.charAt(1)));


                if ( tiles[px][py].getName() != null && tiles[px][py].getName().equals("w_queen")) {
                    w_q = steps.get(cnt).next;
                    //System.out.print(w_q);
                }
                if ( tiles[px][py].getName() != null && tiles[px][py].getName().equals("b_queen")) {
                    b_q = steps.get(cnt).next;
                    //System.out.print(b_q);
                }

                if ( tiles[px][py].getName() != null && tiles[px][py].getName().equals("w_king")) {
                    w_q = steps.get(cnt).next;
                    //System.out.print(w_k);
                }
                if ( tiles[px][py].getName() != null && tiles[px][py].getName().equals("b_king")) {
                    b_q = steps.get(cnt).next;
                    //System.out.print(b_k);
                }


                Image cur_image = (Image) tiles[px][py].getStack().pop();
//                if(tiles[px][py].getStack().size() == 0 || tiles[px][py].getStack().peek() == null) {
//                    squares[px][py].setIcon(null);
//                } else {
                    squares[px][py].setIcon(null);
//                }


                tiles[nx][ny].setName(tiles[px][py].getName());
                tiles[px][py].setName(null);
                tiles[nx][ny].getStack().add(cur_image);

                    squares[nx][ny].setIcon(new ImageIcon(cur_image));

                tiles[px][py].setImage(null);

                cnt++;

            } else  if (e.getSource() == refresh) {
                cnt = 0;
                reSet();
            }

            turn = !turn;
        }
    // reset th whole chess background and tiles.
        public void reSet() {
            turn = true;


            try {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        tiles[i][j] = new Tile(i, j, null, null);
                        squares[i][j].setIcon(null);
                    }
                }

                black_bishop = ImageIO.read(getClass().getResource("black_bishop.png"));
                squares[0][2].setIcon(new ImageIcon(black_bishop));
                squares[0][5].setIcon(new ImageIcon(black_bishop));
                tiles[0][2].setImage(black_bishop);
                tiles[0][2].setName("b_bishop");
                tiles[0][5].setImage(black_bishop);
                tiles[0][5].setName("b_bishop");
                tiles[0][2].getStack().add(black_bishop);
                tiles[0][5].getStack().add(black_bishop);

                black_king = ImageIO.read(getClass().getResource("black_king.png"));
                squares[0][4].setIcon(new ImageIcon(black_king));
                tiles[0][4].setImage(black_king);
                tiles[0][4].setName("b_king");
                tiles[0][4].getStack().add(black_king);

                black_knight = ImageIO.read(getClass().getResource("black_knight.png"));
                squares[0][1].setIcon(new ImageIcon(black_knight));
                squares[0][6].setIcon(new ImageIcon(black_knight));
                tiles[0][1].setImage(black_knight);
                tiles[0][6].setImage(black_knight);
                tiles[0][1].setName("b_knight");
                tiles[0][6].setName("b_knight");
                tiles[0][1].getStack().add(black_knight);
                tiles[0][6].getStack().add(black_knight);


                black_pawn = ImageIO.read(getClass().getResource("black_pawn.png"));
                for (int j = 0; j < 8; j++) {
                    squares[1][j].setIcon(new ImageIcon(black_pawn));
                    tiles[1][j].setImage(black_pawn);
                    tiles[1][j].setName("b_pawn");
                    tiles[1][j].getStack().add(black_pawn);
                }


                black_queen = ImageIO.read(getClass().getResource("black_queen.png"));
                squares[0][3].setIcon(new ImageIcon(black_queen));
                tiles[0][3].setImage(black_queen);
                tiles[0][3].setName("b_queen");
                tiles[0][3].getStack().add(black_queen);

                black_rook = ImageIO.read(getClass().getResource("black_rook.png"));
                squares[0][0].setIcon(new ImageIcon(black_rook));
                squares[0][7].setIcon(new ImageIcon(black_rook));
                tiles[0][0].setImage(black_rook);
                tiles[0][7].setImage(black_rook);
                tiles[0][0].setName("b_rook");
                tiles[0][7].setName("b_rook");
                tiles[0][7].getStack().add(black_rook);
                tiles[0][0].getStack().add(black_rook);

                white_bishop = ImageIO.read(getClass().getResource("white_bishop.png"));
                squares[7][2].setIcon(new ImageIcon(white_bishop));
                squares[7][5].setIcon(new ImageIcon(white_bishop));
                tiles[7][2].setImage(white_bishop);
                tiles[7][5].setImage(white_bishop);
                tiles[7][2].setName("w_bishop");
                tiles[7][5].setName("w_bishop");
                tiles[7][2].getStack().add(white_bishop);
                tiles[7][5].getStack().add(white_bishop);

                white_queen = ImageIO.read(getClass().getResource("white_queen.png"));
                squares[7][3].setIcon(new ImageIcon(white_queen));
                tiles[7][3].setImage(white_queen);
                tiles[7][3].setName("w_queen");
                tiles[7][3].getStack().add(white_queen);

                white_knight = ImageIO.read(getClass().getResource("white_knight.png"));
                squares[7][1].setIcon(new ImageIcon(white_knight));
                squares[7][6].setIcon(new ImageIcon(white_knight));
                tiles[7][1].setImage(white_knight);
                tiles[7][6].setImage(white_knight);
                tiles[7][1].setName("w_knight");
                tiles[7][6].setName("w_knight");
                tiles[7][6].getStack().add(white_knight);
                tiles[7][1].getStack().add(white_knight);

                white_pawn = ImageIO.read(getClass().getResource("white_pawn.png"));
                for (int j = 0; j < 8; j++) {
                    squares[6][j].setIcon(new ImageIcon(white_pawn));
                    tiles[6][j].setImage(white_pawn);
                    tiles[6][j].setName("w_pawn");
                    tiles[6][j].getStack().add(white_pawn);

                }

                white_king = ImageIO.read(getClass().getResource("white_king.png"));
                squares[7][4].setIcon(new ImageIcon(white_king));
                tiles[7][4].setImage(white_king);
                tiles[7][4].setName("w_king");
                tiles[7][4].getStack().add(white_king);


                white_rook = ImageIO.read(getClass().getResource("white_rook.png"));
                squares[7][0].setIcon(new ImageIcon(white_rook));
                squares[7][7].setIcon(new ImageIcon(white_rook));
                tiles[7][0].setImage(white_rook);
                tiles[7][7].setImage(white_rook);
                tiles[7][0].setName("w_rook");
                tiles[7][7].setName("w_rook");
                tiles[7][7].getStack().add(white_rook);
                tiles[7][0].getStack().add(white_rook);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
    }

    //handle special case
    public void ooCase() {
        int kx;
        int ky;
        if(turn) {//white's turn
            kx = (int) w_k.charAt(0) - '0';
            ky = (int) w_k.charAt(1) - '0';
            //System.out.println(kx + "  " + ky);
            while (ky <= 7 && (tiles[kx][ky].getName() == null || !tiles[kx][ky].getName().equals("w_rook"))) {
                ky++;
                //System.out.println(ky);
            }
//
            StringBuilder strrxy = new StringBuilder();
            strrxy.append(kx).append(((int)(w_k.charAt(1) - '0') + ky) / 2);
            StringBuilder strkxy = new StringBuilder();
            strkxy.append(kx).append(((int)(w_k.charAt(1) - '0') + ky) / 2 + 1);

            steps.set(cnt,new move(w_k, strkxy.toString()));
            steps.add(cnt+1, new move("" + kx+ky,strrxy.toString()));

        } else {
            kx = (int) b_k.charAt(0) - '0';
            ky = (int) b_k.charAt(1) - '0';
            //System.out.println(kx + "  " + ky);
            while (ky <= 7 && (tiles[kx][ky].getName() == null || !tiles[kx][ky].getName().equals("b_rook"))) {
                ky++;
            }

            StringBuilder strrxy = new StringBuilder();
            strrxy.append(kx).append((((int)(b_k.charAt(1) - '0') + ky) / 2));
            StringBuilder strkxy = new StringBuilder();
            strkxy.append(kx).append(((int)(w_k.charAt(1) - '0') + ky) / 2 + 1);

            steps.set(cnt,new move(b_k, strkxy.toString()));
            steps.add(cnt+1, new move("" + kx+ky,strrxy.toString()));

        }


    }
    public void oooCase() {
        int kx;
        int ky;
        if(turn) {//white's turn
            kx = (int) w_k.charAt(0) - '0';
            ky = (int) w_k.charAt(1) - '0';
            while (ky >= 7 && (tiles[kx][ky].getName() == null || !tiles[kx][ky].getName().equals("w_rook"))) {
                ky--;
            }

            StringBuilder strrxy = new StringBuilder();
            strrxy.append(kx).append(((int)(w_k.charAt(1) - '0') + ky) / 2);
            StringBuilder strkxy = new StringBuilder();
            strkxy.append(kx).append(((int)(w_k.charAt(1) - '0') + ky) / 2 - 1);

            steps.set(cnt,new move(w_k, strkxy.toString()));
            steps.add(cnt+1, new move("" + kx+ky,strrxy.toString()));

        } else {
            kx = (int) b_k.charAt(0) - '0';
            ky = (int) b_k.charAt(1) - '0';
            while (ky >0 && (tiles[kx][ky].getName() == null || !tiles[kx][ky].getName().equals("b_rook"))) {
                ky--;
            }

            StringBuilder strrxy = new StringBuilder();
            strrxy.append(kx).append((((int)(b_k.charAt(1) - '0') + ky) / 2));
            StringBuilder strkxy = new StringBuilder();
            strkxy.append(kx).append(((int)(w_k.charAt(1) - '0') + ky) / 2 - 1);

            steps.set(cnt,new move(b_k, strkxy.toString()));
            steps.add(cnt+1, new move("" + kx+ky,strrxy.toString()));

        }

    }
    public void promoCase() {
        int px = Integer.parseInt(String.valueOf(steps.get(cnt).next.charAt(0)));
        int py = Integer.parseInt(String.valueOf(steps.get(cnt).next.charAt(1)));

        if(steps.get(cnt).getPromo() == "Q") {
            tiles[px][py].getStack().add(turn?white_queen : black_queen);
        } else if (steps.get(cnt).getPromo() == "N") {
            tiles[px][py].getStack().add(turn?white_knight : black_knight);
        } else if (steps.get(cnt).getPromo() == "B") {
            tiles[px][py].getStack().add(turn ? white_bishop : black_bishop);
        } else if (steps.get(cnt).getPromo() == "R") {
            tiles[px][py].getStack().add(turn ? white_knight : black_knight);
        }

    }
    public static void main(String args[]) {

        Chess grid = new Chess();
    }

}



