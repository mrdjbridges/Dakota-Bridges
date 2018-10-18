/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JumpNShootMan;
import java.awt.Image;
public class PlayerShot {
    int sx;
    int sy;
    Image im;
    int sd;
    
    public PlayerShot(Image i, int x, int y, int d) {
        im = i;
        sx = x;
        sy = y;
        sd = d;
    }
    
    public int getX() {
        return sx;
    }
        
    public void setX(int x) {
        sx = x;
    }
    
    public int getY() {
        return sy;
    }
    
    public void setY(int y) {
        sy = y;
    }
    
    public Image getImage() {
        return im;
    }
    
    public int getCRX() {
        return sx + 2;
    }
    
    public int getCRY() {
        return sy;
    }
    
    public int getSD() {
        return sd;
    }
}


