package JumpNShootMan;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class JumpNShootMan extends JPanel{
//**Main Game Variables
// ***************** Mega Man variables ***************** 
    // ***************** Starting animation ***************** 
        boolean playStart = true;
        int aTimer = 90;
        int teleX = 477;
        int teleY = -15;
        int teleSound = 0;
        int soundTimer = 360;
    // ***************** Starting position ***************** 
        int cx = 458;
        int cy = 551;
        boolean damaged = false;
        int dmgTime = 45;
    // ***************** Platforms variables ***************** 
        ArrayList<Collision> platforms;
        ArrayList storePlatforms;        
        ArrayList storePlayerFloor;
        ArrayList storeCeiling;
        int ceiling = 0;
        int playerCeiling = ceiling;
        int groundFloor = 552;
        int playerFloor = groundFloor;
    // ***************** Jumping variables ***************** 
        boolean isJump = false;
        int velocity = 0; //jump strength
        int vel = velocity;
        int gravity = 1; //weight
        int jumpSound = 0;
    // ***************** Facing variables ***************** 
    	int facingLeft = 0;
        int facingRight = 1;
    // ***************** Enemy variables ***************** 
        ArrayList<EnemySpawn> enemies;
        ArrayList enemyFloor;
        int nextEnemy;
        int onScreen = 0;
        double killMultiplier = 1;
        int killCounter = 0;
        Rectangle metBox;
        int currentDmg;
    // ***************** Shot ***************** 
        ArrayList<PlayerShot> playerShots;
        int shotDelay = 0;
        int shotCounter;
        int shotTime;
    // ***************** Items ***************** 
        ArrayList<Items> items;
        boolean spawnHealth = false;
        int itemSpawnChance = 0;
        int itemCounter = 0;
        int getX;
        int getY;
    // ***************** Health bar variables ***************** 
        int hp = 20;
        int currentHP = hp;
        int hpCoverPosy = 215; // moves Health cover image over the health bar to simulate losing health
    // ***************** User input variables ***************** 
        int leftArrow = 0;
        int rightArrow = 0;
        int upArrow = 0;
        int spaceBar = 0;
        int escKey = 0;
        int pKey = 0;
    // ***************** Timer and delay ***************** 
        Timer timerEv;
        Random rNum;
        double gameScore = 0;
        boolean gameOver = false;
        boolean isPaused = false;
        boolean gameStart = false;
        long gameStartTime;
// ***************** Image Variables ***************** 
    // ***************** Mega Man Images ***************** 
        Image idle;
        Image idleL;
        Image jump;
        Image jumpL;
        Image jumpShoot;
        Image jumpShootL;
        Image run1;
        Image run1L;
        Image runShoot1;
        Image runShoot1L;
        Image shoot;
        Image shootL;
        Image damage;
        Image damageL;
        Image NewRun;
        Image NewRunL;   
        Image playerShotImL;
        Image playerShotImR;
        Image tele1;
        Image tele2;
    // ***************** Enemy Images ***************** 
        Image met;
        Image metL;
        Image metF;
        Image metFL;
        Image metta;
        Image mettaL;   
        Image mettaF;
        Image mettaFL;
        Image mettaur;
        Image mettaurL;
        Image mettaurF;
        Image mettaurFL;
        Image metDeath;
    // ***************** Field and Screen Images ***************** 
    	Image ground;
    	Image background;
        Image pause;
        Image start;
        Image pressStart;
        Image platform;
        Image bottom;
        Image helmet;
        Image health;
        Image healthBlock;
        Image gameEnd;
        Image healthS;
        Image healthB;
    // ***************** Boss Variables *****************
        Image wizard;
        Image fBall;
        Image bossBG;
        
        boolean bossStage = false;
        int nextFireball;
        int numFires;
        int fireDelay;
        Image bBackground;
        Image portal;
        Image Fball;
        ArrayList<fireball> Fireballs;
        int fireCounter = 0;
        
    // ***************** Sound Variables *************************
        int songEv = 32;
        int songCount = 0;
        int playStartSound = 0;
        long musicStartTime;
        int minTimer;
        
    public static void main(String[] args) {
        JumpNShootMan mainPanel = new JumpNShootMan();
        //Setup main window and display to the screen
        JFrame window = new JFrame();
        window.setTitle("JumpNShootMan");
        window.setSize(960, 730); //Horizontal and vertical pixal size
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null); //Centers game window on the screen
        window.setResizable(false); //Cannot resize game window
        window.add(mainPanel);
        window.setVisible(true);
    }    
    public JumpNShootMan(){
        setBackground(Color.BLACK);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyPressedEvent(e);
            }            
            @Override
            public void keyReleased(KeyEvent e) {
                keyReleasedEvent(e);
            }
        });
        setFocusable(true); //This window can receive input from the keyboard
        setDoubleBuffered(true); //Performs clean screen buffering
        
// ***************** Megaman images Label 100+ ***************** 
        ImageIcon i100 = new
        ImageIcon("src\\images\\MegaMan\\Idle.png");
        idle = i100.getImage();
        
        ImageIcon i101 = new
        ImageIcon("src\\images\\MegaMan\\IdleL.png");
        idleL = i101.getImage(); 
        
        ImageIcon i102 = new
        ImageIcon("src\\images\\MegaMan\\Jump.png");
        jump = i102.getImage();
        
        ImageIcon i103 = new
        ImageIcon("src\\images\\MegaMan\\JumpL.png");
        jumpL = i103.getImage();
        
        ImageIcon i104 = new
        ImageIcon("src\\images\\MegaMan\\JumpShoot.png");
        jumpShoot = i104.getImage();
        
        ImageIcon i105= new
        ImageIcon("src\\images\\MegaMan\\JumpShootL.png");
        jumpShootL = i105.getImage();
        
        ImageIcon i106 = new
        ImageIcon("src\\images\\MegaMan\\Run.gif");
        run1 = i106.getImage();
        
        ImageIcon i107 = new
        ImageIcon("src\\images\\MegaMan\\RunL.gif");
        run1L = i107.getImage();
        
        ImageIcon i108 = new
        ImageIcon("src\\images\\MegaMan\\RunShoot.gif");
        runShoot1 = i108.getImage();

        ImageIcon i109 = new
        ImageIcon("src\\images\\MegaMan\\RunShootL.gif");
        runShoot1L = i109.getImage();
        
        ImageIcon i110 = new
        ImageIcon("src\\images\\MegaMan\\Shoot.png");
        shoot = i110.getImage();
        
        ImageIcon i111 = new
        ImageIcon("src\\images\\MegaMan\\ShootL.png");
        shootL = i111.getImage();        
        
        ImageIcon i112 = new
        ImageIcon("src\\images\\MegaShotL.png");
        playerShotImL = i112.getImage();
        
        ImageIcon i113 = new
        ImageIcon("src\\images\\MegaShotR.png");
        playerShotImR = i113.getImage();
        
        ImageIcon i114 = new
        ImageIcon("src\\images\\MegaMan\\damage.gif");
        damage = i114.getImage();
        
        ImageIcon i115 = new
        ImageIcon("src\\images\\MegaMan\\damageL.gif");
        damageL = i115.getImage();        
        
        ImageIcon i116 = new
        ImageIcon("src\\images\\MegaMan\\Teleport1.png");
        tele1 = i116.getImage();   
        
        ImageIcon i117 = new
        ImageIcon("src\\images\\MegaMan\\Teleport2.gif");
        tele2 = i117.getImage();   
        
// ***************** Enemy images Label 200+ ***************** 
        ImageIcon i200 = new
        ImageIcon("src\\images\\Enemies\\Met.gif");
        met = i200.getImage();
        
        ImageIcon i201 = new
        ImageIcon("src\\images\\Enemies\\MetL.gif");
        metL = i201.getImage();
        
        ImageIcon i202 = new
        ImageIcon("src\\images\\Enemies\\MetF.png");
        metF = i202.getImage();
        
        ImageIcon i203 = new
        ImageIcon("src\\images\\Enemies\\MetFL.png");
        metFL = i203.getImage();
        
        ImageIcon i210 = new
        ImageIcon("src\\images\\Enemies\\Metta.gif");
        metta = i210.getImage();
        
        ImageIcon i211 = new
        ImageIcon("src\\images\\Enemies\\MettaL.gif");
        mettaL = i211.getImage();
        
        ImageIcon i212 = new
        ImageIcon("src\\images\\Enemies\\MettaF.png");
        mettaF = i212.getImage();
        
        ImageIcon i213 = new
        ImageIcon("src\\images\\Enemies\\MettaFL.png");
        mettaFL = i213.getImage();
        
        ImageIcon i220 = new
        ImageIcon("src\\images\\Enemies\\Mettaur.gif");
        mettaur = i220.getImage();
        
        ImageIcon i221 = new
        ImageIcon("src\\images\\Enemies\\MettaurL.gif");
        mettaurL = i221.getImage();
        
        ImageIcon i222 = new
        ImageIcon("src\\images\\Enemies\\MettaurF.png");
        mettaurF = i222.getImage();
        
        ImageIcon i223 = new
        ImageIcon("src\\images\\Enemies\\MettaurFL.png");
        mettaurFL = i223.getImage();
        
        ImageIcon i230 = new
        ImageIcon("src\\images\\Enemies\\metDeath.gif");
        metDeath = i230.getImage();
        
// ***************** Field and screen images Label 300+ ***************** 
        ImageIcon i300 = new
        ImageIcon("src\\images\\background.png");
        background = i300.getImage();
        
        ImageIcon i301 = new
        ImageIcon("src\\images\\bottom.png");
        bottom = i301.getImage(); 
        
        ImageIcon i302 = new
        ImageIcon("src\\images\\ground.png");
        ground = i302.getImage();

        ImageIcon i303 = new
        ImageIcon("src\\images\\platform.png");
        platform = i303.getImage();

        ImageIcon i304 = new
        ImageIcon("src\\images\\megaman\\helmet.png");
        helmet = i304.getImage();
         
        ImageIcon i305 = new
        ImageIcon("src\\images\\megaman\\Health bar.png");
        health = i305.getImage();
        
        ImageIcon i306 = new
        ImageIcon("src\\images\\megaman\\Health bar cover.png");
        healthBlock = i306.getImage();
        
        ImageIcon i307 = new
        ImageIcon("src\\images\\end.jpg");
        gameEnd = i307.getImage();
        
        ImageIcon i308 = new
        ImageIcon("src\\images\\items\\smallHealth.gif");
        healthS = i308.getImage();    
        
        ImageIcon i309 = new
        ImageIcon("src\\images\\items\\bigHealth.gif");
        healthB = i309.getImage(); 
        
        ImageIcon i310 = new
        ImageIcon("src\\images\\gamePause.png");
        pause = i310.getImage();
        
        ImageIcon i311 = new
        ImageIcon("src\\images\\startScreen.png");
        start = i311.getImage();
        
        ImageIcon i312 = new
        ImageIcon("src\\images\\pressStart.gif");
        pressStart = i312.getImage();        
// ***************** Image Arrays  *****************         
        ImageIcon i400 = new
        ImageIcon("src\\images\\boss\\wizard.gif");
        wizard = i400.getImage();             
        
        ImageIcon i401 = new
        ImageIcon("src\\images\\boss\\fireball.gif");
        fBall = i401.getImage();    
                
        ImageIcon i402 = new
        ImageIcon("src\\images\\boss\\Bbackground.gif");
        bossBG = i402.getImage();   
        
// ***************** Image Arrays  *****************         
        playerShots = new ArrayList<>();
        enemies = new ArrayList<>(); 
        items = new ArrayList<>();
        Fireballs = new ArrayList<>();
        
// ***************** Timers ***************** 
        rNum = new Random();
        nextEnemy = rNum.nextInt(20) + 10;
        nextFireball = rNum.nextInt(300) + 50;

        timerEv = new Timer(16, new TimerClass()); //Time given in milliseconds
        if (!isPaused){
            timerEv.start();
        }
        else {
            timerEv.stop();
        }
    }    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    // *************** Hit boxes *************** 
        //starting points for  TOP collision boxes
        Point groundPoint = new Point (-100, 600);
        Point bottomLeftPoint = new Point(-100, 450);
        Point bottomRightPoint = new Point(600, 450);
        Point middleLeftPoint = new Point(-300, 325);
        Point middleRightPoint = new Point(800, 325);
        Point middlePoint = new Point(250, 280);
        Point topLeftPoint = new Point(-100, 130);
        Point topRightPoint = new Point(600, 130);
        
        //starting points for BOTTOM collision boxes
        Point bottomLeftPointTwo = new Point(-100, 475); //took -5 off for test
        Point bottomRightPointTwo = new Point(600, 475);
        Point middleLeftPointTwo = new Point(-300, 350);
        Point middleRightPointTwo = new Point(800, 350);
        Point middlePointTwo = new Point(250, 305);
        Point topLeftPointTwo = new Point(-100, 155);
        Point topRightPointTwo = new Point(600, 155);
        
        //starting points for megaman box
        Point feetPoint = new Point((cx + 6), (cy + 30));
        Point headPoint = new Point((cx + 6), cy);
        
        //dimensions of collision boxes
        Dimension groundDimension = new Dimension(1080, 5);
        Dimension boxDimensions = new Dimension(446, 5);
        Dimension feetDimensions = new Dimension(30, 20); 
        Dimension headDimension = new Dimension(30, 10);
        
        //TOP collision boxes
        Rectangle groundBox = new Rectangle (groundPoint, groundDimension);
        Rectangle bottomLeftBox = new Rectangle(bottomLeftPoint, boxDimensions);
        Rectangle bottomRightBox = new Rectangle(bottomRightPoint, boxDimensions);
        Rectangle middleLeftBox = new Rectangle(middleLeftPoint, boxDimensions);
        Rectangle middleRightBox = new Rectangle(middleRightPoint, boxDimensions);
        Rectangle middleBox = new Rectangle(middlePoint, boxDimensions);
        Rectangle topLeftBox = new Rectangle(topLeftPoint, boxDimensions);
        Rectangle topRightBox = new Rectangle(topRightPoint, boxDimensions);
        
        //megaman collision box
        Rectangle megamanFeet = new Rectangle(feetPoint, feetDimensions);
        Rectangle megamanHead = new Rectangle(headPoint, headDimension);
                
        //BOTTOM collision boxes
        Rectangle bottomLeftBoxTwo = new Rectangle(bottomLeftPointTwo, boxDimensions);
        Rectangle bottomRightBoxTwo = new Rectangle(bottomRightPointTwo, boxDimensions);
        Rectangle middleLeftBoxTwo = new Rectangle(middleLeftPointTwo, boxDimensions);
        Rectangle middleRightBoxTwo = new Rectangle(middleRightPointTwo, boxDimensions);
        Rectangle middleBoxTwo = new Rectangle(middlePointTwo, boxDimensions);
        Rectangle topLeftBoxTwo =  new Rectangle(topLeftPointTwo, boxDimensions);
        Rectangle topRightBoxTwo = new Rectangle(topRightPointTwo, boxDimensions);
        
    // *************** Draw to screen *************** 
if (!gameStart){    
    g.drawImage(start, 0, 0, null);
    soundTimer -= 1;
    if (soundTimer <= 0){
        g.drawImage(pressStart, 0, 0, null);
    }
} 
else if (gameStart){
    if (playStart) {
        aTimer -= 1;
        if (aTimer > 0){
            teleY = teleY + 15;
        }
        else if (aTimer <= 0){
            playStart = false;
            gameStartTime = System.currentTimeMillis();
            musicStartTime = System.currentTimeMillis();
        }  
        g.drawImage(background, 0, 0, null);
        g.drawImage(platform, -100, 450, this); //bottom left
        g.drawImage(platform, 600, 450, this);  //bottom right
        g.drawImage(platform, -300, 325, this); //middle left
        g.drawImage(platform, 800, 325, this);  //middle right        
        g.drawImage(platform, 250, 280, this);  //middle
        g.drawImage(platform, -100, 130, this); //top left
        g.drawImage(platform, 600, 130, this);  //top right                    
        g.drawImage(ground, 0, 598, this);    
        g.drawImage(bottom, 0, 650, this);  // 0, -50      
        if (teleY < cy){
            g.drawImage(tele1, teleX, teleY, this);
        }
        else {
            g.drawImage(tele2, cx-5, cy-3, this);
            if (teleSound == 0){
                playSound(4);
                teleSound++;
            }
        }
    }
    else{    
        if (isPaused) {     
            if (!bossStage){
                g.drawImage(background, 0, 0, null);
            }
            else{
                g.drawImage(bossBG, 0, 0, null);
            }
            g.drawImage(pause, 0, 0, null);
            g.drawImage(bottom, 0, 650, this);  // 0, -50
            g.drawImage(health, 50, 650, this);   //50, 0
            g.drawImage(healthBlock, hpCoverPosy, 650, this);        
            g.drawImage(helmet, 10, 655, this);   //10, 0        
        }
        if (!isPaused){
            if (!bossStage){
                g.drawImage(background, 0, 0, null);
                g.drawImage(platform, -100, 450, this); //bottom left
                g.drawImage(platform, 600, 450, this);  //bottom right
                g.drawImage(platform, -300, 325, this); //middle left
                g.drawImage(platform, 800, 325, this);  //middle right        
                g.drawImage(platform, 250, 280, this);  //middle
                g.drawImage(platform, -100, 130, this); //top left
                g.drawImage(platform, 600, 130, this);  //top right                    
                g.drawImage(ground, 0, 598, this);    
            }
            else {
                g.drawImage(bossBG, 0, 0, this);
                g.drawImage(wizard, 70, 350, this);            
            }
            g.drawImage(bottom, 0, 650, this);  // 0, -50
            g.drawImage(health, 50, 650, this);   //50, 0
            g.drawImage(healthBlock, hpCoverPosy, 650, this);        
            g.drawImage(helmet, 10, 655, this);   //10, 0

            storePlatforms = new ArrayList<>();
                storePlatforms.add(groundBox);
                storePlatforms.add(bottomLeftBox);
                storePlatforms.add(bottomRightBox);
                storePlatforms.add(middleLeftBox);
                storePlatforms.add(middleRightBox);
                storePlatforms.add(middleBox);
                storePlatforms.add(topLeftBox);
                storePlatforms.add(topRightBox);

            int playerPlat1 = 402; //bottomLeftBox & bottomRightBox
            int playerPlat2 = 277; //middleLeftBox & middleRightBox
            int playerPlat3 = 232; //middleBox
            int playerPlat4 = 82;  //topRightBox & topLeftBox   
            storePlayerFloor = new ArrayList<>();
                storePlayerFloor.add(groundFloor);
                storePlayerFloor.add(playerPlat1);
                storePlayerFloor.add(playerPlat1);
                storePlayerFloor.add(playerPlat2);
                storePlayerFloor.add(playerPlat2);
                storePlayerFloor.add(playerPlat3);
                storePlayerFloor.add(playerPlat4);
                storePlayerFloor.add(playerPlat4);

            storeCeiling = new ArrayList<>();
                storeCeiling.add(bottomLeftBoxTwo);
                storeCeiling.add(bottomRightBoxTwo);
                storeCeiling.add(middleLeftBoxTwo);
                storeCeiling.add(middleRightBoxTwo);
                storeCeiling.add(middleBoxTwo);
                storeCeiling.add(topLeftBoxTwo);
                storeCeiling.add(topRightBoxTwo);

        // ********** Platform Collision **********
        if (!bossStage){
            for (int plat = 0; plat < storePlatforms.size(); plat++){
                Rectangle getPlat = (Rectangle) storePlatforms.get(plat);
                if (megamanFeet.getBounds().intersects(getPlat)){                
                    playerFloor = (int) storePlayerFloor.get(plat);
                    cy = (int) storePlayerFloor.get(plat);    
                    break;
                }
                else {
                    playerFloor = groundFloor;
                }
            }
        }
        else {
            playerFloor = groundFloor;
        }
        // ********** Ceiling Collision **********
        if (!bossStage){
            for (int ceil = 0; ceil < storeCeiling.size(); ceil++){
                Rectangle getCeil = (Rectangle) storeCeiling.get(ceil);
                if (megamanHead.getBounds().intersects(getCeil)){
                    playerCeiling = (int) storePlayerFloor.get(ceil + 1) + 70;
                }
            }
        }
        else {
            playerCeiling = ceiling;
        }

        // ********** Draw player shot ********** 
            for (int i = 0; i < playerShots.size(); i++) {
                PlayerShot ps = playerShots.get(i);
                Image s = ps.getImage();
                int shx = ps.getX();
                int shy = ps.getY();
                g.drawImage(s, shx, shy, this);
            }

        // ********** Draw items **********    
            for (int k = 0; k < items.size(); k++){
                Items item = items.get(k);
                Image healthIm = item.getImage();
                int ix = item.getX();
                int iy = item.getY();
                g.drawImage(healthIm, ix, iy, this);
            }

         // ********** Draw enemies **********   
            for (int j = 0; j < enemies.size(); j++) {
                EnemySpawn enemy = enemies.get(j);
                Image eWalk = enemy.getWalkImage();
                Image eFall = enemy.getFallImage();
                int ex = enemy.getX();
                int ey = enemy.getY();
                int fall = enemy.getFall();  
                if (fall == 1){
                    g.drawImage(eWalk, ex, ey, this);
                }
                else{
                    g.drawImage(eFall, ex, ey, this);
                }
                Point metPoint = new Point(ex, ey);
                Dimension metDimensions = new Dimension(36, 38);
                metBox = new Rectangle(metPoint, metDimensions);   
                int enemyPlat1 = 414;
                int enemyPlat2 = 294;
                int enemyPlat3 = 244;
                int enemyPlat4 = 94;
                int enemyGround = 651;
                enemyFloor = new ArrayList<>();
                    enemyFloor.add(enemyGround);
                    enemyFloor.add(enemyPlat1);
                    enemyFloor.add(enemyPlat1);
                    enemyFloor.add(enemyPlat2);
                    enemyFloor.add(enemyPlat2);
                    enemyFloor.add(enemyPlat3);
                    enemyFloor.add(enemyPlat4);
                    enemyFloor.add(enemyPlat4);
                for (int ePlat = 0; ePlat < storePlatforms.size(); ePlat++){
                    Rectangle getEPlat = (Rectangle) storePlatforms.get(ePlat);
                    if (metBox.getBounds().intersects(getEPlat)){                
                        int setEFloor = (int) enemyFloor.get(ePlat);
                        enemy.setGF(setEFloor);
                        break;
                    }
                    else {
                        enemy.setGF(561);
                    }
                }  
            }


        // ***************** DRAW PLAYER MOVEMENT  ***************** 
            if (damaged == true) {
                if (facingRight == 1){
                    g.drawImage(damage, cx, cy, this);
                }
                else if (facingLeft == 1){
                    g.drawImage(damageL, cx, cy, this);
                }
            }
            if (damaged == false){
            // ***************** No Input Static Drawing ***************** 
                if (rightArrow == 0 && leftArrow == 0 || rightArrow == 1 && leftArrow == 1 ){
                    if (spaceBar == 0){
                        if (facingRight == 1 && isJump == false){
                            g.drawImage(idle, cx, cy, this);
                        }
                        else if (facingLeft == 1 && spaceBar == 0 && isJump == false){
                            g.drawImage(idleL, cx, cy, this); 
                        }
                    }
            // ***************** No movement shooting *****************
                    if (spaceBar == 1){ //Shoot
                        if (facingRight == 1 && isJump == false){
                            g.drawImage(shoot, cx, cy, this);                            
                        }
                        else if (facingLeft == 1 && isJump == false){
                            g.drawImage(shootL, cx-20, cy, this);
                        }
                    }
                }
            // ***************** Right Arrow Frame Count Drawing ***************** 
                if (rightArrow == 1 && leftArrow == 0){
                    if (spaceBar == 0 && isJump == false){ //Shoot and run
                        g.drawImage(run1, cx, cy, this);
                    }
                    else if (spaceBar == 1 && isJump == false) {
                        g.drawImage(runShoot1, cx, cy, this);
                    }
               } 
            // ***************** Left Arrow Frame Count Drawing ***************** 
                if (leftArrow == 1 && rightArrow == 0){
                    if (spaceBar == 0 && isJump == false){              
                        g.drawImage(run1L, cx, cy, this);
                    }
                    else if (spaceBar == 1 && isJump == false) {
                        g.drawImage(runShoot1L, cx-10, cy, this);
                    }
                }        
            // ***************** Jump Directions & Animations ***************** 
                if (isJump == true){ //jump shoot
                    if (spaceBar == 0){
                        if (facingRight == 1){
                            g.drawImage(jump, cx, cy, this);}
                        else if (facingLeft == 1){
                            g.drawImage(jumpL, cx, cy, this);}}
                    else if (spaceBar == 1){
                        if (facingRight == 1){
                            g.drawImage(jumpShoot, cx, cy, this);}
                        else if (facingLeft == 1){
                            g.drawImage(jumpShootL, cx, cy, this);
                        }
                    }
                }
            }
        }//ispaused end
        // *************** Game Timer and Score*************** 
            if(!isPaused || isPaused){
            // ***************** Draw score to screen ***************** 
                g.setColor(Color.WHITE);
                g.setFont(new Font("SansSerif", Font.BOLD, 36));

                int currentScore = (int) gameScore;
                String displayScore = Integer.toString(currentScore);
                String displayCombo = Double.toString(killMultiplier); //C-c-combo  

                g.drawString("Score:" + displayScore, 580, 685);
                g.drawString("Bonus:" + displayCombo, 320, 685);

            // ***************** Draw game timer ***************** 
                long CurrentTime = System.currentTimeMillis();
                long secTimer = (CurrentTime - gameStartTime)/1000;
                if (secTimer == 60){
                    gameStartTime = System.currentTimeMillis();
                    minTimer++;
                }
                String displaySec = Long.toString(secTimer);
                String displayMin = Long.toString(minTimer);
                if (secTimer < 10){
                    g.drawString("0" + displaySec, 860, 685);
                }
                else {
                    g.drawString(displaySec, 860, 685);
                }
                if (minTimer < 10){
                    g.drawString(displayMin + ":", 825, 685);
                }
                else {        
                    g.drawString(displayMin + ":", 805, 685);
                }
            }    
        //Draw game over
            if (currentHP <= 0){
                g.drawImage(gameEnd, 0, 0, this);
            }
            Toolkit.getDefaultToolkit().sync();
        }//end playStart else
    }
}//end paintComponent

    public void eventFrame(){
        if (playStartSound == 0){
            playSound(15);
            playStartSound++;
        }

        /*if (gameScore == 10){
            bossStage = true;
        }
        */
        if (gameStart){
            if (!playStart){
                    playMusic(1);
                if (!isPaused){
                    gameOver();
                    playerMovement();
                    playerFireShot();
                    playerMoveShot();
                    healthPickup();
                    if (!bossStage){
                        enemySpawn();
                        enemyMove();
                        enemyKill();        
                        enemyCrash();
                    }
                    else {
                        removeEnemies();
                        fireBalls();
                        //fireCrash();
                    }
                }
            }
        }
        if (escKey == 1){
            System.exit(0);
        }  
        repaint();
    }
// ***************** PLAYER EVENTS *****************   
    public void gameOver(){                               
        if (currentHP <= 0){
            gameOver = true;
        }
        if (gameOver == true){
            songCount = 0;
            timerEv.stop();
            long t1 = System.currentTimeMillis();
            long t2 = t1 + 3000;
            while(t1 < t2){ 
                t1 = System.currentTimeMillis();}
            System.exit(0);
        }          
    }
    public void playerMovement(){
        /*  Check if player hit's the ceiling or bottom of a platform
            and causes the player to descend
        */
        if (cy == playerCeiling){
            velocity = 1;
            velocity =- 1;
        }
        //Checks if the player is jumping
        isJump = cy < playerFloor;  
        
        //Plays player landing sound
        if (!isJump && jumpSound == 0) {
            playSound(6);
            jumpSound++;
        }
        if (isJump){
            jumpSound = 0;
        }
        
        //Jumping 
        if (upArrow == 1){ 
            if (velocity == 0 & cy >= playerFloor){
                velocity = 20;
            }
            cy -= velocity;             //Move the player on the y-axis based on the strength of the jump
            velocity -= gravity;        //Gradually decreases the strength of the jump 
            if (cy > playerFloor)       //Keeps player from going off the bottom of the screen
                cy = playerFloor;
            if (cy < playerCeiling)     //Keeps player from going off the top of the screen
                cy = playerCeiling;            
        }
        //Continues the jump after the button is released
        else if (cy < playerFloor && upArrow == 0){
            cy -= velocity;             //Move the player on the y-axis based on the strength of the jump
            velocity -= gravity;        //Gradually decreases the strength of the jump 
            if (cy < playerCeiling)     //Keeps player from going off the top of the screen
                cy = playerCeiling;   
        }
        //Resets variables for next jump
        else if (cy >= playerFloor && upArrow == 0){
            velocity = vel;         //Resets velocity when player hits the ground
            playerCeiling = ceiling;
        }
        //Stops movement when damaged
        int moveAmount = 5;
        if (damaged){
            moveAmount = 0;
        }
        else if(!damaged){
            moveAmount = 5;
        }
        
        //Move player left
        if (leftArrow == 1 && rightArrow == 0) {
            cx = cx - moveAmount;
            if (cx < -50)
                cx = 1024;
            facingLeft = 1;
            facingRight = 0;  
                      
        }
        //Move player right
        if (leftArrow == 0 && rightArrow == 1) {
            cx = cx + moveAmount;
            if (cx > 1024)
                cx = -128;
            facingRight = 1;
            facingLeft = 0;
        }
    }
    public void playerFireShot(){
        //Timer for shooting
        if (shotDelay > 0){
            shotDelay--;   
        }
        //Shooting limiter
        int shtdelay = 60;
        shotTime -= 1;
        if (shotTime <= 0){
            shotCounter = 0;
            shotTime = shtdelay;
        }           
        if (shotDelay == 0 && spaceBar == 1 && facingLeft == 1 && facingRight == 0 && shotCounter < 3) {
            int nx = cx - 20;
            int ny = cy + 16;
            PlayerShot ps = new PlayerShot(playerShotImL, nx, ny, 1);
            playerShots.add(ps);
            shotDelay = 10;
            shotCounter++;
            playSound(5);
        }
        if (shotDelay == 0 && spaceBar == 1 && facingLeft == 0 && facingRight == 1 && shotCounter < 3) {
            int nx = cx + 20;
            int ny = cy + 16;
            PlayerShot ps = new PlayerShot(playerShotImR, nx, ny, 2);
            playerShots.add(ps);
            shotDelay = 10;
            shotCounter++;
            playSound(5);
        }
    }
    public void playerMoveShot(){
        int numShots = playerShots.size();
        int shots = numShots - 1;
        int shotSpeed = 10;
        while (shots >= 0) {
            PlayerShot shot = playerShots.get(shots);
            if (shot.getSD() == 1){
                int curx = shot.getX();
                curx -= shotSpeed;
                if (curx < -30){
                    playerShots.remove(shot);
                    
                }
                else{
                    shot.setX(curx);
                }
            }   
            else{
                int curx = shot.getX();
                curx += shotSpeed;
                if (curx > 960){
                    playerShots.remove(shot);
                    
                }
                else{
                    shot.setX(curx);
                }
            }
            shots--;
        }        
    }
    public void healthPickup(){                    
        if (currentHP >= 20){
            currentHP = 20;
        }
        
        if (itemSpawnChance >= 1){
            itemSpawnChance = 0;
            Random rollItemSpawn = new Random();
            int roll = rollItemSpawn.nextInt(11);
            spawnHealth = roll <= 2;
            
        }
        if (itemCounter <= 0){
            if (spawnHealth == true){
                Random healthSize = new Random();
                int hs = healthSize.nextInt(11);
                if (hs < 2){
                    Items newItem = new Items(healthB, getX, getY, 1);
                    items.add(newItem);
                    itemCounter++;
                    spawnHealth = false;
                }
                else {
                    Items newItem = new Items(healthS, getX, getY+20, 2);
                    items.add(newItem);
                    itemCounter++;
                    spawnHealth = false;
                }
            }
        }
        
        int numItems = items.size();
        int item = numItems - 1;
        while(item >= 0){
            Items currentItem = items.get(item);
            int itemy = currentItem.getCRY1();
            int myy1 = cy - 40;
            int myy2 = cy + 50;
                if (itemy > myy1 && itemy < myy2){
                    int itemx = currentItem.getCRX1();
                    int myx1 = cx - 40;
                    int myx2 = cx + 50;
                        if (itemx >= myx1 && itemx <= myx2) {
                            int value = currentItem.getValue();
                            if(currentHP >= 20){
                                currentHP = 20;
                                items.remove(item);
                            }
                            else{
                                items.remove(item);
                                currentHP += value;
                                hpCoverPosy += (value * 8);
                                playSound(24);
                            }
                            itemCounter--;
                        }
                }
            item--;
        }
    }
   
// ***************** ENEMY EVENTS *****************     
    public void enemySpawn(){
        if (nextEnemy > 0){
            nextEnemy--;
        }
        if (nextEnemy == 0) {
            if (onScreen <= 6 ){
                Random spawnPoint = new Random();
                Random enemyT = new Random();                
                int enemyType = 0;
                int next = 0;
                if (gameScore < 150) {
                    next = 1;
                }
                else if (gameScore > 150 && gameScore < 500){
                    enemyType = enemyT.nextInt(101);
                        if (enemyType <= 30){
                            next = 2;
                        }
                        else {
                            next = 1;
                        }                    
                }
                else if (gameScore > 500){
                        if (enemyType <= 10){
                            next = 3;
                        }
                        else if (enemyType >= 10 && enemyType <= 45){
                            next = 2;
                        }
                        else{
                            next = 1;
                        }
                }
                
                int sp = spawnPoint.nextInt(2); //Random to pick left(1) or right side(0)
                
                int lvl1 = 1;
                int lvl2 = 2;
                int lvl3 = 3;
                
                if (next == 1){
                    if (sp == 1){   //Enemy spawn for left side of screen
                        EnemySpawn newEnemy = new EnemySpawn(met, metF, 1, lvl1, -128);
                        enemies.add(newEnemy);
                        onScreen++;                    
                        nextEnemy = rNum.nextInt(100) + 50;   
                    }
                    else {          //Enemy spawn for right side of screen
                        EnemySpawn newEnemy = new EnemySpawn(metL, metFL, 2, lvl1, 1024);
                        enemies.add(newEnemy);
                        onScreen++;                    
                        nextEnemy = rNum.nextInt(100) + 50;    
                    }
                }
                if (next == 2){
                    if (sp == 1){   //Enemy spawn for left side of screen
                        EnemySpawn newEnemy = new EnemySpawn(metta, mettaF, 1, lvl2, -128);
                        enemies.add(newEnemy);
                        onScreen++;                    
                        nextEnemy = rNum.nextInt(200) + 50;   
                    }
                    else {          //Enemy spawn for right side of screen
                        EnemySpawn newEnemy = new EnemySpawn(mettaL, mettaFL, 2, lvl2, 1024);
                        enemies.add(newEnemy);
                        onScreen++;                    
                        nextEnemy = rNum.nextInt(200) + 50;    
                    }
                }
                if (next == 3){
                    if (sp == 1){   //Enemy spawn for left side of screen
                        EnemySpawn newEnemy = new EnemySpawn(mettaur, mettaurF, 1, lvl3, -128);
                        enemies.add(newEnemy);
                        onScreen++;                    
                        nextEnemy = rNum.nextInt(300) + 50;   
                    }
                    else {          //Enemy spawn for right side of screen
                        EnemySpawn newEnemy = new EnemySpawn(mettaurL, mettaurFL, 2, lvl3, 1024);
                        enemies.add(newEnemy);
                        onScreen++;                    
                        nextEnemy = rNum.nextInt(300) + 50;    
                    }
                }
            }
        }        
    }
    public void enemyMove() {
        int numEnemies = enemies.size();
        int enemy = numEnemies - 1;
        while (enemy >= 0) {
            EnemySpawn currentSpawn = enemies.get(enemy);
            int newx;
            int cursx = currentSpawn.getX();
            int cursy = currentSpawn.getY();
            int enemyGroundFloor = currentSpawn.getGF();
            int mvr = currentSpawn.getMoveRate();
            int drr = currentSpawn.getDropRate();
            int newy = cursy + drr;
            int mdir = currentSpawn.getMoveDir();
            int zoneRight = currentSpawn.getZoneRight();
            int zoneLeft = currentSpawn.getZoneLeft();
            
            if (newy >= enemyGroundFloor){
                currentSpawn.setY(enemyGroundFloor);
                currentSpawn.setDropRate(1);
                currentSpawn.setFall(1);
            }            
            else{ 
                int grav = 1;
                grav = grav + drr;                
                currentSpawn.setDropRate(grav);
                currentSpawn.setY(newy);
                currentSpawn.setFall(0);
            }            
            if (mdir == 1) {//Left
                newx = cursx + mvr;                
                if (newx >= zoneRight){
                    newx = zoneLeft;
                    if (newy >= 510){
                        currentSpawn.setY(95);
                    }
                }
            }            
            else {          //Right
                newx = cursx - mvr;                
                if (newx <= zoneLeft){
                    newx = zoneRight;
                    if (newy >= 510){
                        currentSpawn.setY(95);
                    }
                }
            }
            currentSpawn.setX(newx);
            enemy--;
        }  
    }
    public void enemyKill() {
        int numShots = playerShots.size();
        int shots = numShots - 1;
        while (shots >= 0){
            PlayerShot phs2 = playerShots.get(shots);
            int critx = phs2.getCRX();
            int crity = phs2.getCRY();
            int numEnemies = enemies.size();
            int Enemy = numEnemies - 1;
            while (Enemy >= 0) {
                EnemySpawn currentMet = enemies.get(Enemy);
                int crity2 = currentMet.getCRY2();
                int crity1 = currentMet.getCRY1();
                if (crity <= crity2  && crity > crity1) {
                    int critx1 = currentMet.getCRX1();
                    int critx2 = currentMet.getCRX2();
                    if (critx >= critx1 && critx <= critx2) {
                        int value = currentMet.getEnemyValue();  
                        int enemyHP = currentMet.getHP();
                        enemyHP = enemyHP - 1;
                        playSound(9);
                        currentMet.setHP(enemyHP);
                        playerShots.remove(shots);
                        if (enemyHP == 0) { 
                            getX = currentMet.getX();
                            getY = currentMet.getY();
                            gameScore = gameScore + (value * killMultiplier);
                            enemies.remove(Enemy);
                            onScreen--;
                            killCounter++;
                            itemSpawnChance++;
                            if (killCounter >= 2 && killMultiplier < 3){
                                killMultiplier = (killMultiplier + .5);
                                killCounter = 0;
                            }
                        }

                    }
                }
                Enemy--;   
            }
            shots--;
        }   
    }
    public void enemyCrash(){
        //Player invincibility timer
        int dmgDelay = 45;
        if (damaged){
        dmgTime -= 1;
            if (dmgTime > 35){
                if (facingRight == 1){
                    cx = cx - 3;
                }
                else{
                    cx = cx + 3;
                }
                cy = cy - 3;
            }
            if (dmgTime <= 0){
                dmgTime = dmgDelay;
                damaged = false;
            }         
        }
        int numEnemies = enemies.size();
        int Enemy = numEnemies - 1;        
        while (Enemy >= 0) {            
            EnemySpawn currentSpawn = enemies.get(Enemy);            
            int crity = currentSpawn.getCRY1();
            int myy1 = cy - 30;
            int myy2 = cy + 30;
            if (!damaged){
               currentDmg = currentSpawn.getAtk();
            } 
            else {
                currentDmg = 0;
            }
                if (crity >= myy1 && crity <= myy2) {
                    int critx = currentSpawn.getCRX1();
                    int myx1 = cx - 20;
                    int myx2 = cx + 30;
                    if (critx >= myx1 && critx <= myx2) {    
                        if (!damaged){
                            enemies.remove(Enemy);
                            onScreen--;                        
                            playSound(7);                        
                            currentHP -= currentDmg;
                            hpCoverPosy -= (currentDmg * 8);
                            damaged = true;
                            killMultiplier = 1; 
                        }
                    }
                }
            Enemy--; 
        }
    }
// ***************** BOSS EVENTS *****************    
    public void removeEnemies(){
        if (enemies.size() > 0){
            for (int i = 0; i < enemies.size(); i++){
                enemies.remove(i);
                onScreen--;
            }
        }
        if (items.size() > 0){
            for (int j = 0; j < items.size(); j++){
                items.remove(j);
            }
        }
    }    
    public void fireBalls(){
        if(nextFireball > 0){
            nextFireball--;
        }
        //Spawn fire
        if (nextFireball == 0){
            Random fireDir = new Random();
            //fire direction
            int fd = fireDir.nextInt(3);
            //fire position           
            int fp = (551 - fireCounter * 120);
            if (fireCounter == 0){
                fireDelay = 0;
            }
            if (fireDelay == 0){
                 if (numFires < 5) {
                     fireball newFire = new fireball(Fball, 1, fp);
                     Fireballs.add(newFire);
                     numFires++;
                     fireCounter++;
                     nextFireball = 10;  
                     if (fireCounter >= 5){
                         fireDelay = 1; 
                     }                         
                 }                    
            }
        }        
    }
    public void fireCrash(){
        int numFire = Fireballs.size();
        int fire = numFire - 1;
        while (fire >= 0){           
            fireball currentFire = Fireballs.get(fire);           
            int crity = currentFire.getCRY1();
            int myy1 = cy - 30;
            int myy2 = cy + 30;
            if (!damaged){
               currentDmg = currentFire.getPower();
            } 
            else {
                currentDmg = 0;
            }
                if (crity >= myy1 && crity <= myy2) {
                    int critx = currentFire.getCRX1();
                    int myx1 = cx - 20;
                    int myx2 = cx + 30;
                    if (critx >= myx1 && critx <= myx2) {    
                        if (!damaged){
                            Fireballs.remove(fire);
                            fireCounter--;                        
                            playSound(22);                        
                            currentHP -= currentDmg;
                            hpCoverPosy -= (currentDmg * 8);
                            damaged = true;
                            killMultiplier = 1; 
                        }
                    }
                }
            fire--; 
        }
    }
// ***************** SOUNDS & MUSIC*****************  
    /*
    Sounds work as follows:
        Sound effect is assigned a number
        Use "sound(#);" where you want a sound effect to play
        To assign a number to a sound effect use an "if (s == #)" 
    */
    public void playSound(int s) {
    // ***************** Megaman Sounds *****************
        if (s == 1){
            try {
                File soundFile = new File("src\\sounds\\01 - GameStart.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-70.0f );
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException k) {}
        }      
        if (s == 2){
            try {
                File soundFile = new File("src\\sounds\\02 - PauseMenu.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-50.0f );
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException k) {}
        }    
        if (s == 4){ //MegaBuster sound effect
            try {
                File soundFile = new File("src\\sounds\\04 - MegamanWarp.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);     
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-50.0f );
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException k) {}
        }        
        if (s == 5){ //MegaBuster sound effect
            try {
                File soundFile = new File("src\\sounds\\05 - MegaBuster.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);     
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-50.0f );
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException k) {}
        }
        
        if (s == 6){ //Megaman landing
            try {
                File soundFile = new File("src\\sounds\\06 - MegamanLand.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-50.0f );
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException k) {}
        }
        
        if (s == 7){
            try { //Megaman taking damage
                File soundFile = new File("src\\sounds\\07 - MegamanDamage.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-50.0f );
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException k) {}
        }
        
        if (s == 8){ //Megaman defeat
            try {
                File soundFile = new File("src\\sounds\\08 - MegamanDefeat.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-50.0f );
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException k) {}
        }
        if (s == 15){
            try {
                File soundFile = new File("src\\sounds\\15 - Victory.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-20.0f );
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException k) {}
        }
        if (s == 22){
            try {
                File soundFile = new File("src\\sounds\\22 - FireStorm1.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-50.0f );
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException k) {}
        }        
        if (s == 24){
            try {
                File soundFile = new File("src\\sounds\\24 - EnergyFill.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-50.0f );
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException k) {}
        }
        
    // ***************** Enemy Sounds ***************** 
    
        if (s == 9){ //Megaman defeat
            try {
                File soundFile = new File("src\\sounds\\09 - EnemyDamage.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-50.0f );
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException k) {}
        }
    }
    public void playMusic(int m){
        if (m == 1){            
            long songTimer = (System.currentTimeMillis() - musicStartTime)/1000;
            int sTimer = (int) songTimer;
            if (songCount == 0){
                try {
                    File soundFile = new File("src\\music\\song.wav");
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                    Clip music = AudioSystem.getClip();
                    music.open(audioIn);
                    FloatControl gainControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(-25.0f );                    
                    music.start();
                    songCount++;
                }
                catch (IOException | LineUnavailableException | UnsupportedAudioFileException k) {}
            }
            else if (sTimer == songEv){
                songCount--;
                musicStartTime = System.currentTimeMillis();
            }           
        }
    }

    public void keyPressedEvent(KeyEvent e){
        int cd = e.getKeyCode();
        if(!gameStart){
            if (soundTimer <= 0){
                if (cd == KeyEvent.VK_SPACE){ //shoot
                    gameStart = true;
                    playSound(1);
                }
            }
        }
        else {
            if (!damaged){
                if (cd == KeyEvent.VK_UP) //jump
                    upArrow = 1;
                if (cd == KeyEvent.VK_LEFT) //move left
                    leftArrow = 1;
                if (cd == KeyEvent.VK_RIGHT) //move right
                    rightArrow = 1;  
                if (cd == KeyEvent.VK_SPACE) //shoot
                    spaceBar = 1;
            }  
            if (damaged){
                if (cd == KeyEvent.VK_UP) //jump
                    upArrow = 0;
                if (cd == KeyEvent.VK_LEFT) //move left
                    leftArrow = 0;
                if (cd == KeyEvent.VK_RIGHT) //move right
                    rightArrow = 0;       
                if (cd == KeyEvent.VK_SPACE) //shoot
                    spaceBar = 0;
            }
            if (cd == KeyEvent.VK_ESCAPE) //exit game
                escKey = 1;
            if (isPaused == false){
                if (cd == KeyEvent.VK_P){
                    pKey = 1;
                    isPaused = true;
                    playSound(2);
                }  
            }
            else {
                if (cd == KeyEvent.VK_P){
                    pKey = 1;
                    isPaused = false;
                    playSound(2);
                } 
            }
        }
    }  
    
    public void keyReleasedEvent(KeyEvent e){
        int cd = e.getKeyCode();
        if (cd == KeyEvent.VK_UP)
            upArrow = 0;
        if (cd == KeyEvent.VK_LEFT)
            leftArrow = 0;
        if (cd == KeyEvent.VK_RIGHT)
            rightArrow = 0;        
        if (cd == KeyEvent.VK_SPACE)
            spaceBar = 0;
    }
    
    class TimerClass implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            eventFrame();
        }
    }
}