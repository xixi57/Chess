import java.awt.*;
import java.util.Stack;

/**
 * Created by Xi Wang on 2/5/17.
 */
public class Tile {
    Image curImage;
    Stack<Image> stack = new Stack<Image>();
    int x;
    int y;//follows the numerical order;
    String name = null;
    public Tile(int x, int y, Image image,String name) {
        this.stack.add(image);
        this.curImage = image;
        this.x = x;
        this.y = y;
        this.name = name;
    }
    public void setImage(Image image) {
        this.curImage = image;
    }
    public Image getImage(){
        return curImage;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public Stack getStack() {
        return this.stack;
    }


}
