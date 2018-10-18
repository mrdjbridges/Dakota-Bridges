package JumpNShootMan;

import java.awt.Image;

public class Collision {
    int px;
    int py;
    Image im;

    public Collision(Image i, int x, int y) {
            px = x;
            py = y;
            im = i;
    }
    
    public Image getImage() {
        return im;
    }
    
    public int getX() {
            return px;
    }

    public int getY() {
            return py;
    }

    public void setX(int x) {
            px = x;
    }

    public void setY(int y) {
            py = y;
    }

    public int getCRX1() {
            return px;
    }

    public int getCRX2() {
            return py + 44;
    }

    public int getCRY1() {
            return px;
    }

    public int getCRY2() {
            return py + 44;
    }
}