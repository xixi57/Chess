/**
 * Created by raoyinchen on 2/3/17.
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



//steps are used to convert the file to array of object which containes pre step and next step
public class Steps {
    List<move> res;
    public Steps () throws IOException {
        res = new ArrayList<move>();



        Scanner scan = new Scanner(new File("test.txt"));
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] lines = line.split(" ");
            if(lines.length < 2) continue;

            res.add(new move(lines[0]));
            res.add(new move(lines[1]));//

        }
    }

    public List<move> getSteps() {
        return res;
    }


//    public static void main(String args[]) throws IOException {
//        Steps test = new Steps();
//    }

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
}
