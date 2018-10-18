package JumpNShootMan;
import java.awt.Image;
import java.awt.Rectangle;
public class EnemySpawn {
    int enemyType;
    Image walkIm;
    Image fallIm;
    int ex;
    int ey;
    int zoneLeft;
    int zoneRight;
    int moveDir;
    int moveRate;
    int dropRate;
    int enemyValue;
    int enemyGroundFloor = 560;
    int HP;
    int fall;
    int atk;
    Rectangle enemyBox;
    Rectangle enemyBox2;
    
    public EnemySpawn(Image i, Image f, int sp, int hp, int side) {
        walkIm = i;
        fallIm = f;
        
        //Set starting position
        zoneLeft = -128;
        zoneRight = 1024;

        //Move rate
        //moveRate = 5;
        
        //Set spawn posiition
        ey = 94;
        ex = side;
        if (sp == 1){       //Left side spawn point
            moveDir = 1;    //Moves enemy left to right
        }
        else if (sp == 2){  //Right side spawn point
            moveDir = 2;    //Moves enemy right to left
        }      
        //Set HP value for mets
        
        if (hp == 1){
            HP = 3;
            atk = 2;
            enemyValue = 10;
            moveRate = 3;
        }
        else if (hp == 2){
            HP = 5;
            atk = 4;
            enemyValue = 15;
            moveRate = 5;  
        }
        else if (hp == 3){
            HP = 7;
            atk = 6;
            enemyValue = 20;
            moveRate = 6;
        }
    }    

    public int getGF() {
        return enemyGroundFloor;
    }
    
    public void setGF(int GF) {
        enemyGroundFloor = GF;
    }
    
    public int getHP() {
        return HP;
    }
    
    public void setHP(int hp) {
        HP = hp;
    }
    
    public int getAtk(){
        return atk;
    }
    
    public int getX() { 
        return ex;
    }
       
    public int getY() {
        return ey;
    }
    
    public void setX(int x) {
        ex = x;
    }

    public void setY(int y) {
        ey = y;
    }
    
    public void setFall(int f) {
        fall = f;
    }
    
    public int getFall() {
        return fall;
    }
    
    public Image getWalkImage() {
        return walkIm;
    }
    
    public Image getFallImage() {
        return fallIm;
    }
    
    public int getCRX1() {
        return ex;
    }
    
    public int getCRY1() {
        return ey - 10;
    }

    public int getCRX2() {
        return ex + 20;
    }
    
    public int getCRY2() {
        return ey + 40;
    }
    
    public void setDropRate(int drr) {
        dropRate = drr;
    }
    
    public int getDropRate() {
        //Sends the decent speed
        return dropRate;
    }
    
    public void setMoveRate(int mvr) {
        moveRate = mvr;
    }
    
    public int getMoveRate() {
        return moveRate;
    }
    
    public void setMoveDir(int mdir) {
        moveDir = mdir;
    }
    
    public int getMoveDir() {
        return moveDir;
    }
    
    public int getZoneLeft() {
        return zoneLeft;
    }

    public int getZoneRight() {
        return zoneRight;
    }
    
    public int getEnemyValue() {
        //Sends point value
        return enemyValue;
    }
    
    //Attempting to change move direction
    public void setBounds(Rectangle metBox){
        enemyBox = metBox;
    }
    
    public Rectangle getBounds(){
        return enemyBox;
    }
    
    public void setBounds2(Rectangle metBox2){
        enemyBox2 = metBox2;
    }
    
    public Rectangle getBounds2(){
        return enemyBox2;
    }    
}
