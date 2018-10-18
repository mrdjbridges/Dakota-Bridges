package JumpNShootMan;
import java.awt.Image;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class Items {
    Image im;
    int itemType;
    int ix;
    int iy;
    int itemValue;
    int dropRate;

    Random rNum = new Random();
        
    
    public Items(Image i, int x, int y, int value) {
        im = i;        
        ix = x;
        iy = y;
        if (value == 1){
            itemValue = 5;
        }
        else {
            itemValue = 2;
        }
    }
    
    public int getX() { 
        return ix;
    }
       
    public int getY() {   
        return iy;
    }
    
    public void setX(int x) {
        ix = x;
    }

    public void setY(int y) {
        iy = y;
    }

    public Image getImage() {
        return im;
    }
    public int getValue() {
        return itemValue;
    }
    
    public int getCRX1() {
        return ix;
    }
    
    public int getCRY1() {
        return iy;
    }
}

    

