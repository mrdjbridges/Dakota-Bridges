package JumpNShootMan;

import java.awt.Image;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class fireball {
    Image im;
    int fx;
    int fy;
    int leftside;
    int rightside;
    int top;
    int moveDir;
    
    Random rNum = new Random();
    int moveRate;
    
    public fireball(Image i, int fd, int fp) {
        moveRate = 10;
        moveDir = fd;
        im = i; 
        
        leftside = 15;
        rightside = 900;
        top = 10;
        fx = 15;
        fy = fp;
    }
    
    public int getX() {
        return fx;
    }
       
    public int getY() {
        return fy;
    }
    
    public int getleft() {
        return leftside;
    }
    
    public int getright() {
        return rightside;
    }
    
    public int gettop() {
        return top;
    }
    
    public void setX(int x) {
        fx = x;
    }    

    public void setY(int y) {
        fy = y;
    }

    public Image getImage() {
        return im;
    }
    
    public int getCRX1() {
        return fx;
    }
    
    public int getCRY1() {
        return fy;
    }
    
    public int getMoveRate() {
        //Sends the move speed
        return moveRate;
    }
    
   public int getMoveDir() {
       return moveDir;
   }
    
}

    


