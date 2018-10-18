package JumpNShootMan;

import java.awt.image.BufferedImage;

public class MegaManSpriteSheet {
    
    private BufferedImage image;
    
    public MegaManSpriteSheet(BufferedImage image){
        this.image = image;
    }    
    public BufferedImage grabImage(int col, int row, int width, int height){
        BufferedImage img = image.getSubimage((col * width) - width, (row * height) -  height, width, height);
        return img;
    }
    
}
