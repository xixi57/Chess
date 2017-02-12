/**
 * Created by raoyinchen on 2/3/17.
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class Steps {
    List<move> res;
    public Steps () throws IOException {
        res = new ArrayList<move>();



        Scanner scan = new Scanner(new File("test.txt"));
        while(scan.hasNextLine()){
            String line = scan.nextLine();
           //System.out.println(line);
            String[] lines = line.split(" ");
            //if(lines.length == 0 ) continue;
            if(lines.length < 2) continue;
//           System.out.println(lines[0]);
//           System.out.println(lines[1]);

            res.add(new move(lines[0]));
            res.add(new move(lines[1]));//

            //Here you can manipulate the string the way you want
        }
    }

    public List<move> getSteps() {
        return res;
    }


    public static void main(String args[]) throws IOException {
        Steps test = new Steps();
        //System.out.print(test.getSteps().size());
//        for (int i = 0; i < test.getSteps().size(); i++ ) {
//            System.out.print(test.getSteps().get(i).prev + " ");
//            System.out.print(test.getSteps().get(i).next + "\n");
//        }
    }

}

class move {
    String prev;
    String next;
    String promo = null;
    public move(String oneMove) {
        if (oneMove.equals("O-O")) {
            prev = "O-O";
            next = "O-O";
            return;
        } else if (oneMove.equals("O-O-O")) {
            prev = "O-O-O";
            next = "O-O-O";
            return;
        }  else if(oneMove.length() > 5) {
            String[] moves = oneMove.split("-");
            prev = moves[0];
            next = moves[1];
            prev = new StringBuilder().append(8 - ((int) moves[0].charAt(1) - '0')).append((int) (moves[0].charAt(0) - 'A')).toString();
            next = new StringBuilder().append(8 - ((int) moves[1].charAt(1) - '0')).append((int) (moves[1].charAt(0) - 'A')).toString();
            promo = oneMove.substring(6,7);

        } else {
            String[] moves = oneMove.split("-");
            prev = moves[0];
            next = moves[1];
            prev = new StringBuilder().append(8 - ((int) moves[0].charAt(1) - '0')).append((int) (moves[0].charAt(0) - 'A')).toString();
            next = new StringBuilder().append(8 - ((int) moves[1].charAt(1) - '0')).append((int) (moves[1].charAt(0) - 'A')).toString();
        }
    }
        public move(String prev, String next) {
            this.prev = prev;
            this.next = next;
        }
        public void setPromo(String promo) {
            this.promo = promo;
        }

    public String getPromo() {
        return this.promo;
    }
//        prev = new int[2];
////        next = new int[1];
//        String[] moves = oneMove.split("-");
//           prev = moves[0];
//           next = moves[1];
////        System.out.print(prev);
////        System.out.print(next);
//        //System.out.print( 8 - ((int)moves[0].charAt(1) - '0'));
//        prev = new StringBuilder().append(8 - ((int)moves[0].charAt(1) - '0')).append((int)(moves[0].charAt(0) - 'A')).toString();
//        next = new StringBuilder().append(8 - ((int)moves[1].charAt(1) - '0')).append((int)(moves[1].charAt(0) - 'A')).toString();
//        //System.out.println(prev + " " + next);
////            prev[0] = moves[0].charAt(1) - 'A';
////            prev[1] = 7 - Integer.valueOf(moves[0].charAt(0));
////            next[0] = moves[1].charAt(1) - 'A';
////            next[1] = 7 - Integer.valueOf(moves[1].charAt(0));
////            System.out.print(prev);
////            System.out.print(next);
//

}
