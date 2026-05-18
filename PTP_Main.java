/**
 * Allows user to play the entire game "Protect the Pointer!"
 *
 * @author Kevin Zhang
 * @version 6/1/22
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;
import java.awt.geom.AffineTransform;
public class PTP_Main extends JPanel implements MouseListener, KeyListener, Runnable
{
    static private JFrame myFrame;
    static private int frame_xCoord;
    static private int frame_yCoord;
    static private JPanel myPanel;
    static private int windowWidth;
    static private int windowHeight;

    static private int lastClick_xCoord;
    static private int lastClick_yCoord;
    static private Point mouseLoc = new Point(0, 0);

    static private boolean gameOver;
    static private String gameMode;
    static private ArrayList<SuperObstacle> obstacles = new ArrayList<>();
    //static private ArrayList<JLabel> obsLabels = new ArrayList<>();
    static private double points;
    static private double recentScore1;
    static private double highScore1;
    static private double recentBulletScore;
    static private double highBulletScore;
    static private double recentScore2;
    static private double highScore2;
    static private double recentLaserScore;
    static private double highLaserScore;
    static private boolean level2PopUp;
    static private double recentScore3;
    static private double highScore3;
    static private double recentBouncerScore;
    static private double highBouncerScore;
    static private boolean level3PopUp;

    static private boolean rulesPopUp;

    static private int time;
    static private double delayVariable;
    static private int lives;
    static private int bulletSpawnRate;
    static private int laserSpawnRate;
    static private int laserHarmRate;

    static private boolean initializeGameModeSettings;
    static private boolean lightMode;
    static private int themeNumber; 
    static private boolean[] themesUnlocked;
    // 1 is Default Theme 
    // 2 is Circle Theme
    // 3 is Yin Yang Theme
    // 4 is Party Theme
    // 5 is Meme Theme

    static private boolean playMusicGameOver;

    static private String[] lobbySongNames; 
    static private int lobbySongNumber;
    static private Clip clipLobby;
    static private boolean[] lobbySongsUnlocked;
    // 0 is Freddy's Music
    // 1 is Mario Galaxy
    // 2 is Mario Bros Wii
    // 3 is Plants vs Zombies
    // 4 is Subway Surfers
    // 5 is Undertale Shop

    static private String[] levelSongNames; 
    static private int levelSongNumber;
    static private Clip clipLevel;
    static private boolean[] levelSongsUnlocked;
    // 0 is Coconut Mall
    // 1 is Gourmet Race 
    // 2 is Jetpack Joyride
    // 3 is Mario Sunshine
    // 4 is Minecraft Pigstep
    // 5 is Pokemon Battle

    static private Clip clipMLG;
    static private int memeChangeTime;
    static private Clip clipHitmarker;

    static private int coins;
    static private boolean[] powerupsUnlocked;
    static private int healPowerupRate;
    static private int pointPowerupRate;
    static private int clearPowerupRate;
    static private int moneyPowerupRate;

    Image lock = Toolkit.getDefaultToolkit().getImage("lock.png");

    public PTP_Main()
    {
        myFrame.addMouseListener(this);
        myFrame.addKeyListener(this);
        gameOver = true;
        gameMode = "";
        lives = 3;
        //points = 0;
        recentScore1 = 0;
        highScore1 = 0;
        recentScore2 = 0;
        highScore2 = 0;
        level2PopUp = false;
        recentScore3 = 0;
        highScore3 = 0;
        level3PopUp = false;
        rulesPopUp = false;
        lightMode = true;
        themeNumber = 1;

        lobbySongNumber = 0;
        lobbySongNames = new String[6];
        lobbySongNames[0] = "Freddy's Music";
        lobbySongNames[1] = "Mario Galaxy";
        lobbySongNames[2] = "Mario Bros Wii";
        lobbySongNames[3] = "Plants vs Zombies";
        lobbySongNames[4] = "Subway Surfers";
        lobbySongNames[5] = "Undertale Shop";
        lobbySongsUnlocked = new boolean [6];

        levelSongNumber = 0;
        levelSongNames = new String[6];
        levelSongNames[0] = "Coconut Mall";
        levelSongNames[1] = "Gourmet Race";
        levelSongNames[2] = "Jetpack Joyride";
        levelSongNames[3] = "Mario Sunshine";
        levelSongNames[4] = "Minecraft Pigstep";
        levelSongNames[5] = "Pokemon Battle";
        levelSongsUnlocked = new boolean [6];

        coins = 0;
        powerupsUnlocked = new boolean[4];
        themesUnlocked =  new boolean[5];
        themesUnlocked[0] = true;
        initializeGameModeSettings = false;
        playMusicGameOver = true;

    }

    public void paintComponent(Graphics g){
        if(gameOver == true)
        {

            //epilepsy version
            // int r = 0; int gr = 0; int b = 0; int a = 0;
            // r = (int)(Math.random()*256);
            // gr = (int)(Math.random()*256);
            // b = (int)(Math.random()*256);
            // a = (int)(Math.random()*256);
            // this.setBackground(new Color(r,gr,b,a));
            // repaint();

            //light mode background
            if(lightMode)
            {
                this.setBackground(Color.WHITE);
                g.setColor(Color.BLACK);
            }
            else //Dark Mode
            {
                this.setBackground(Color.BLACK);
                g.setColor(Color.WHITE);
            }

            // title text
            g.setFont(new Font("TimesRoman", Font.BOLD, 50));
            g.drawString("Protect the Pointer!",300,50);

            //rules text
            g.setFont(new Font("TimesRoman", Font.ITALIC, 20));
            g.drawString("How To Play?",450, 120);
            //g.drawString("How To Play?",380, 615); centered bottom
            g.drawRect(425, 100, 160, 40);

            g.drawString("Created by Kevin Zhang :)",400,80);

            //light or dark mode
            g.drawRect(25, 25, 170, 90);
            if(lightMode)
            {
                g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g.drawString("switch to", 75, 65);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("Dark Mode", 50, 90);
            }
            else //dark mode
            {
                g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g.drawString("switch to", 75, 65);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("Light Mode", 50, 90);
            }

            //themes
            g.drawRect(750, 66, 200, 50);
            g.drawString("Tap to change themes!", 750, 50);
            if(themeNumber != 1)
            {
                g.drawRect(760, 125, 180, 35);
            }
            if(themeNumber == 1)
            {
                g.drawString("Default Theme", 765, 100);
            }
            else if(themeNumber == 2)
            {
                g.drawString("Circle Theme", 765, 100);
                if(themesUnlocked[1] == false) //circle theme locked
                {
                    g.drawImage(lock, 685, 65, 50, 50, this);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    g.drawString("Unlock for 20 coins", 770, 145);
                }
                else //circle theme unlocked
                {
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    g.drawString("Round Obstacles", 770, 145);
                }
            }
            else if(themeNumber == 3)
            {
                g.drawString("Yin Yang Theme", 765, 100);
                if(themesUnlocked[2] == false) //yin yang theme locked
                {
                    g.drawImage(lock, 685, 65, 50, 50, this);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    g.drawString("Unlock for 30 coins", 770, 145);
                }
                else //yin yang theme unlocked
                {
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    g.drawString("Black and White", 770, 145);
                }
            }
            else if(themeNumber == 4)
            {
                g.drawString("Party Theme", 765, 100);
                if(themesUnlocked[3] == false) //party theme locked
                {
                    g.drawImage(lock, 685, 65, 50, 50, this);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    g.drawString("Unlock for 40 coins", 770, 145);
                }
                else //party theme unlocked
                {
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    g.drawString("Rainbow Obstacles", 770, 145);
                }
            }
            else if(themeNumber == 5)
            {
                g.drawString("Meme Theme", 765, 100);
                if(themesUnlocked[4] == false)
                {
                    g.drawImage(lock, 685, 65, 50, 50, this);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    g.drawString("Unlock for 50 coins", 770, 145);
                }
                else
                {
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    g.drawString("MLG Dank Memes", 770, 145);
                }
            }

            //music or songs
            //lobby
            if(lobbySongsUnlocked[lobbySongNumber] == false)
            {
                g.drawRect(70, 210, 25, 160);
                g.drawImage(lock, 40 , 165, 30, 30, this);
                Graphics2D g4 = (Graphics2D) g;
                Font font3 = new Font(null, Font.PLAIN, 15);    
                AffineTransform affineTransform3 = new AffineTransform();
                affineTransform3.rotate(Math.toRadians(-90), 0, 0);
                Font rotatedFont3 = font3.deriveFont(affineTransform3);
                g4.setFont(rotatedFont3);
                g4.drawString("Unlock for 10 coins",90,360);
                //g4.dispose();
            }
            //level
            if(levelSongsUnlocked[levelSongNumber] == false)
            {
                g.drawRect(885, 210, 25, 160);
                g.drawImage(lock, 920, 165, 30, 30, this);
                Graphics2D g5 = (Graphics2D) g;
                Font font4 = new Font(null, Font.PLAIN, 15);    
                AffineTransform affineTransform4 = new AffineTransform();
                affineTransform4.rotate(Math.toRadians(90), 0, 0);
                Font rotatedFont4 = font4.deriveFont(affineTransform4);
                g5.setFont(rotatedFont4);
                g5.drawString("Unlock for 10 coins",892,225);
                //g5.dispose();
            }

            //Level 1
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.drawString("Level 1", 185, 230);
            g.drawRect(125, 175, 200, 100);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("Last Score: "+recentScore1+"", 150, 300);
            g.drawString("Best Score: "+highScore1+"", 150, 325);

            repaint();

            //Bullet Challenge
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.drawString("Bullet Challenge", 140, 435);
            g.drawRect(125, 380, 200, 100);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("Last Score: "+recentBulletScore+"", 150, 505);
            g.drawString("Best Score: "+highBulletScore+"", 150, 530);

            //Level 2
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.drawString("Level 2", 450, 230);
            g.drawRect(390, 175, 200, 100); 
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("Last Score: "+recentScore2+"", 415, 300);
            g.drawString("Best Score: "+highScore2+"", 415, 325);
            if(highScore1 < 75 || highBulletScore < 60)
            {
                g.drawImage(lock, 365, 150, 50, 50, this);
            }
            //Laser Challenge
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.drawString("Laser Challenge", 410, 435);
            g.drawRect(390, 380, 200, 100); 
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("Last Score: "+recentLaserScore+"", 415, 505);
            g.drawString("Best Score: "+highLaserScore+"", 415, 530);
            if(highScore1 < 75 || highBulletScore < 60)
            {
                g.drawImage(lock, 365, 355, 50, 50, this);
            }
            //2 Pop up
            if(level2PopUp)
            {
                if(lightMode)
                {
                    g.setColor(Color.WHITE);
                }
                else
                {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(320, 290, 340, 60);
                if(lightMode)
                {
                    g.setColor(Color.BLACK);
                }
                else
                {
                    g.setColor(Color.WHITE);
                }
                g.drawRect(320, 290, 340, 60);
                g.setFont(new Font("TimesRoman", Font.ITALIC, 15));
                g.drawString("(requires score of at least 75 on Level 1)", 365, 310);
                g.drawString("(requires score of at least 60 on Bullet Challenge)", 340, 335);
            }

            repaint();

            //Level 3
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.drawString("Level 3", 715, 230);
            g.drawRect(655, 175, 200, 100); 
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("Last Score: "+recentScore3+"", 680, 300);
            g.drawString("Best Score: "+highScore3+"", 680, 325);
            if(highScore1 < 100 || highScore2 < 40 || 
               highBulletScore < 80 || highLaserScore < 75)
            {
                //Image lock = Toolkit.getDefaultToolkit().getImage("lock.png");
                g.drawImage(lock, 630, 150, 50, 50, this);
            }
            //Bouncer Challenge
            g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
            g.drawString("Bouncer Challenge", 670, 435);
            g.drawRect(655, 380, 200, 100); 
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("Last Score: "+recentBouncerScore+"", 680, 505);
            g.drawString("Best Score: "+highBouncerScore+"", 680, 530);
            if(highScore1 < 100 || highScore2 < 40 || 
               highBulletScore < 80 || highLaserScore < 75)
            {
                //Image lock = Toolkit.getDefaultToolkit().getImage("lock.png");
                g.drawImage(lock, 630, 355, 50, 50, this);
            }
            //Level 3 Pop up
            if(level3PopUp)
            {
                if(lightMode)
                {
                    g.setColor(Color.WHITE);
                }
                else
                {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(590, 280, 330, 95);
                if(lightMode)
                {
                    g.setColor(Color.BLACK);
                }
                else
                {
                    g.setColor(Color.WHITE);
                }
                g.drawRect(590, 280, 330, 95);
                g.setFont(new Font("TimesRoman", Font.ITALIC, 15));
                g.drawString("(requires score of at least 100 on Level 1)", 625, 300);
                g.drawString("(requires score of at least 40 on Level 2)", 625, 320);
                g.drawString("(requires score of at least 80 on Bullet Challenge)", 600, 340);
                g.drawString("(requires score of at least 75 on Laser Challenge)", 600, 360);
            }

            //coins
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.drawString("Coins: "+coins, 255, 120);
            repaint();

            //power up shop: Heal
            g.drawString("Heal Power Up", 100, 580);
            Image medkit = Toolkit.getDefaultToolkit().getImage("medkit.png");
            g.drawImage(medkit, 120, 575, 100, 100, this);
            g.drawRect(80, 665, 180, 35);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            if(!powerupsUnlocked[0]) //heal power up locked
            {
                g.drawString("Unlock for 30 coins", 90, 689);
                g.drawImage(lock, 50, 545, 50, 50, this);
            }
            else //heal power up unlocked
            {
                g.drawString("Gain 1 life", 120, 689);
            }
            //power up shop: Point
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.drawString("Point Power Up", 325, 580);
            Image boost = Toolkit.getDefaultToolkit().getImage("boost.png");
            g.drawImage(boost, 365, 585, 70, 70, this);
            g.drawRect(305, 665, 180, 35);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            if(!powerupsUnlocked[1]) //point power up locked
            {
                g.drawString("Unlock for 30 coins", 315, 689);
                g.drawImage(lock, 275, 545, 50, 50, this);
            }
            else //point power up unlocked
            {
                g.drawString("Score increases 10%", 315, 689);
            }
            //power up shop: Clear
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.drawString("Clear Power Up", 550, 580);
            Image eraser = Toolkit.getDefaultToolkit().getImage("eraser.png");
            g.drawImage(eraser, 590, 585, 80, 70, this);
            g.drawRect(530, 665, 180, 35);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            if(!powerupsUnlocked[2]) //clear power up locked
            {
                g.drawString("Unlock for 30 coins", 540, 689);
                g.drawImage(lock, 500, 545, 50, 50, this);
            }
            else //clear power up unlocked
            {
                g.drawString("Removes Obstacles", 540, 689);
            }
            //power up shop: Money
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.drawString("Money Power Up", 775, 580);
            Image moneybag = Toolkit.getDefaultToolkit().getImage("moneybag.png");
            g.drawImage(moneybag, 815, 585, 80, 80, this);
            g.drawRect(755, 665, 180, 35);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            if(!powerupsUnlocked[3]) //money power up locked
            {
                g.drawString("Unlock for 30 coins", 765, 689);
                g.drawImage(lock, 725, 545, 50, 50, this);
            }
            else //money power up unlocked
            {
                g.drawString("Earns 3-5 Coins", 765, 689);
            }
            repaint();

            //rules pop up
            if(rulesPopUp)
            {
                g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                if(lightMode)
                {
                    g.setColor(Color.WHITE);
                    g.fillRect(275,525, 465, 165);
                    g.setColor(Color.BLACK);
                }
                else //dark mode
                {
                    g.setColor(Color.BLACK);
                    g.fillRect(275,525, 465, 165);
                    g.setColor(Color.WHITE);
                }
                g.drawRect(275,525, 465, 165);
                g.drawString("* Keep your pointer on the window at all times",290,550);
                g.drawString("* Do not re-adjust window size",290,575);
                g.drawString("* Do not let your pointer touch obstacles (3 lives)",290,600);
                g.drawString("* Tap to collect coins and power ups",290,625);
                g.drawString("* Clicking only works when your mouse is stationary",290,650);
                g.drawString("* Press spacebar to return to lobby screen",290,675);
            }

            //lobby music
            g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
            g.drawString("Tap to change", 15, 460);
            g.drawString("lobby music", 15, 480);
            Image music = Toolkit.getDefaultToolkit().getImage("music.png");
            g.drawImage(music, 30, 385, 50, 50, this);
            //credit to 
            //https://stackoverflow.com/questions/10083913/how-to-rotate-text-with-graphics2d-in-java
            Graphics2D g2 = (Graphics2D) g;
            Font font = new Font(null, Font.PLAIN, 20);    
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.rotate(Math.toRadians(-90), 0, 0);
            Font rotatedFont = font.deriveFont(affineTransform);
            g2.setFont(rotatedFont);
            g2.drawString(lobbySongNames[lobbySongNumber],60,370);
            //g2.dispose();
            //level music
            g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
            g.drawString("Tap to change", 890, 460);
            g.drawString("level music", 890, 480);
            g.drawImage(music, 910, 385, 50, 50, this);
            // //credit to 
            // //https://stackoverflow.com/questions/10083913/how-to-rotate-text-with-graphics2d-in-java
            Graphics2D g3 = (Graphics2D) g;
            Font font2 = new Font(null, Font.PLAIN, 20);   
            AffineTransform affineTransform2 = new AffineTransform();
            affineTransform2.rotate(Math.toRadians(90), 0, 0); //270 = 90
            Font rotatedFont2 = font.deriveFont(affineTransform2);
            g3.setFont(rotatedFont2);
            g3.drawString(levelSongNames[levelSongNumber],930,210);
            //g2.dispose();
            //g3.dispose();

            repaint();
        }
        else //gameOver == false
        {

            //Meme Theme background
            if(themeNumber == 5  && themesUnlocked[4] == true)
            {
                if(lightMode)
                {  
                    Image background = Toolkit.getDefaultToolkit().getImage("MLG meme background.jpeg");
                    g.drawImage(background, 0, 0, windowWidth, windowHeight, this);
                }
                else //dark mode
                {
                    Image background = Toolkit.getDefaultToolkit().getImage("MLG meme background inverted.jpg");
                    g.drawImage(background, 0, 0, windowWidth, windowHeight, this);
                }
            }

            //obstacle
            for(int i=0; i<obstacles.size(); i++)
            {
                SuperObstacle tempObs = obstacles.get(i);
                int tempX = tempObs.getPos().x;
                int tempY = tempObs.getPos().y;
                int tempWidth = tempObs.getWidth();
                int tempHeight = tempObs.getHeight();

                //collectibles
                if(tempObs instanceof Coin)
                {
                    // g.setColor(new Color(0xF0B00D)); //gold
                    // g.fillOval(tempX, tempY, tempWidth, tempHeight);
                    Image coin = Toolkit.getDefaultToolkit().getImage("coin.png");
                    g.drawImage(coin, tempX, tempY, tempWidth, tempHeight, this);

                }
                else if(tempObs instanceof HealPowerup)
                {
                    Image medkit = Toolkit.getDefaultToolkit().getImage("medkit.png");
                    g.drawImage(medkit, tempX, tempY, tempWidth, tempHeight, this);
                }
                else if(tempObs instanceof PointPowerup)
                {
                    Image boost = Toolkit.getDefaultToolkit().getImage("boost.png");
                    g.drawImage(boost, tempX, tempY, tempWidth, tempHeight, this);
                }
                else if(tempObs instanceof ClearPowerup)
                {
                    Image eraser = Toolkit.getDefaultToolkit().getImage("eraser.png");
                    g.drawImage(eraser, tempX, tempY, tempWidth, tempHeight, this);
                }
                else if(tempObs instanceof MoneyPowerup)
                {
                    Image moneybag = Toolkit.getDefaultToolkit().getImage("moneybag.png");
                    g.drawImage(moneybag, tempX, tempY, tempWidth, tempHeight, this);
                }
                repaint();

                //all kinds of bullet obstacles
                if(tempObs instanceof BulletHorizontal || tempObs instanceof BulletVertical ||
                tempObs instanceof BulletDiagonalNWtoSE || tempObs instanceof BulletDiagonalNEtoSW ||
                tempObs instanceof BulletHorizWave || tempObs instanceof BulletVertWave) 
                {
                    if(themeNumber == 1 || themeNumber == 2) //Default or Circle Theme
                    {
                        g.setColor(new Color(0x0091FF)); //blue
                    }
                    //other themes not unlocked
                    if((themeNumber == 3 && themesUnlocked[2] == false) ||
                    (themeNumber == 4 && themesUnlocked[3] == false) ||
                    (themeNumber == 5 && themesUnlocked[4] == false))
                    {
                        g.setColor(new Color(0x0091FF)); //blue
                    }
                    if(themeNumber == 3 && themesUnlocked[2] == true) //Yin Yang Theme
                    {
                        if(lightMode)
                        {
                            g.setColor(Color.BLACK);
                        }
                        else //dark mode
                        {
                            g.setColor(Color.WHITE);
                        }
                        repaint();
                    }
                    if(themeNumber == 4 && themesUnlocked[3] == true) //Party Theme
                    {
                        //rainbow bullets
                        int r = 0; int gr = 0; int b = 0; int a = 0;
                        r = (int)(Math.random()*256);
                        gr = (int)(Math.random()*256);
                        b = (int)(Math.random()*256);
                        a = (int)(Math.random()*256);
                        g.setColor(new Color(r,gr,b,a));
                        repaint();
                    }
                    if(themeNumber == 5  && themesUnlocked[4] == true) //Meme Theme
                    {
                        if(lightMode)
                        {
                            g.setColor(Color.WHITE);
                        }
                        else //dark mode
                        {
                            g.setColor(Color.BLACK);
                        }
                        repaint();
                    }
                    if(themeNumber == 2 && themesUnlocked[1] == true) //Circle Theme
                    {
                        g.fillOval(tempX, tempY, tempWidth, tempHeight);
                    }
                    else //All other themes have rectangular bullets
                    { 
                        g.fillRect(tempX, tempY, tempWidth, tempHeight);
                    }
                    repaint();
                }

                //laser Obstacles
                if(tempObs instanceof Laser)
                {
                    if(tempObs.getHarmful() == false)
                    {
                        if(lightMode)
                        {
                            g.setColor(new Color(0xEAD9D9)); //light pink
                        }
                        else //dark mode
                        {
                            g.setColor(new Color(0x360A0A)); //dark maroon
                        }
                        if(themeNumber == 2 && themesUnlocked[1] == true) //Circle Theme
                        {
                            g.fillOval(tempX, tempY, tempWidth, tempHeight);
                        }
                        else //All other themes have rectangular lasers
                        { 
                            g.fillRect(tempX, tempY, tempWidth, tempHeight);
                        }
                    }
                    else
                    {
                        g.setColor(Color.RED);
                        if(themeNumber == 2 && themesUnlocked[1] == true) //Circle Theme
                        {
                            g.fillOval(tempX, tempY, tempWidth, tempHeight);
                        }
                        else //All other themes have rectangular lasers
                        { 
                            g.fillRect(tempX, tempY, tempWidth, tempHeight);
                        }
                    }
                    repaint();
                }

                //DVD obstacles
                if(tempObs instanceof DVDscreensaver)
                {
                    //classic DVD color change when bouncing
                    g.setColor(tempObs.getColor());
                    if(themeNumber == 2 && themesUnlocked[1] == true) //Circle Theme
                    {
                        g.fillOval(tempX, tempY, tempWidth, tempHeight);
                    }
                    else if(themeNumber == 5  && themesUnlocked[4] == true) //Meme Theme
                    {
                        if(memeChangeTime/200.0 <= 5)
                        {
                            Image illuminati = Toolkit.getDefaultToolkit().getImage("illuminati.png");
                            g.drawImage(illuminati, tempX, tempY, tempWidth, tempHeight, this);
                        }
                        else if(memeChangeTime/200.0 <= 10)
                        {
                            Image trollface = Toolkit.getDefaultToolkit().getImage("trollface.png");
                            g.drawImage(trollface, tempX, tempY, tempWidth, tempHeight, this);
                        }
                        else if(memeChangeTime/200.0 <= 15)
                        {
                            Image pepe = Toolkit.getDefaultToolkit().getImage("pepe.png");
                            g.drawImage(pepe, tempX, tempY, tempWidth, tempHeight, this);
                        }
                        else if(memeChangeTime/200.0 <= 20)
                        {
                            Image trollface = Toolkit.getDefaultToolkit().getImage("sanic.png");
                            g.drawImage(trollface, tempX, tempY, tempWidth, tempHeight, this);
                        }
                        else
                        {
                            memeChangeTime = 0;
                        }
                    }
                    else
                    {
                        g.fillRect(tempX, tempY, tempWidth, tempHeight);
                    }
                    repaint();

                }
            }

            //lives counter
            g.setFont(new Font("TimesRoman", Font.BOLD, 50));
            if(lives > 2)
            {
                g.setColor(new Color(0x5CC159)); //green
            }
            else if(lives > 1)
            {
                g.setColor(new Color(0xff9900)); //orange
            }
            else
            {
                g.setColor(Color.RED);
            }
            Image life = Toolkit.getDefaultToolkit().getImage("life.png");
            g.drawImage(life, 50, 10, 40, 40, this);
            g.drawString(lives+"", 15, 45);
            if(lightMode)
            {
                g.setColor(Color.BLACK);
            }
            else //Dark Mode
            {
                g.setColor(Color.WHITE);
            }
            //time counter
            g.drawString(points+"", 100, 45);
            //System.out.println(points); //testing
            g.drawString("Coins: " +coins, 760, 45 );

        }
    }

    /**
     * when mouse is clicked on a component(pressed & released immediately)
     */
    public void mouseClicked(MouseEvent e)
    {
        if(themeNumber == 5  && themesUnlocked[4] == true) //Meme Theme
        {
            playMusicHitmarker();
        }
        lastClick_xCoord = e.getX();
        lastClick_yCoord = e.getY();
        System.out.println(lastClick_xCoord+" , "+lastClick_yCoord); //testing
        if(gameOver == true)
        {
            //button for game theme: light or dark
            //g.drawRect(25, 50, 170, 90); but the y value should be 30 more "why + 25?"
            if(lastClick_xCoord > 25 && lastClick_xCoord < 25+170 &&
            lastClick_yCoord > 50+30 && lastClick_yCoord < 50+90+30)
            {
                if(lightMode)
                {
                    lightMode = false;
                }
                else //dark mode
                {
                    lightMode = true;
                }
            }

            //button for changing theme
            //g.drawRect(750, 66, 200, 50);
            if(lastClick_xCoord > 750 && lastClick_xCoord < 750+200 &&
            lastClick_yCoord > 66+30 && lastClick_yCoord < 66+50+30)
            {
                themeNumber ++;
                if(themeNumber > 5)
                {
                    themeNumber -= 5;
                }
            }

            //button for changing lobby music
            //g.drawImage(music, 30, 385, 50, 50, this);
            if(lastClick_xCoord > 30 && lastClick_xCoord < 30+50 &&
            lastClick_yCoord > 385+30 && lastClick_yCoord < 385+50+30)
            {
                lobbySongNumber ++;
                if(lobbySongNumber >= 6)
                {
                    lobbySongNumber -= 6;
                }
                if(clipLobby != null)
                {
                    clipLobby.stop();
                }
                if(lobbySongsUnlocked[lobbySongNumber] == true)
                {
                    playMusicLobby(lobbySongNames[lobbySongNumber]);
                }
                //System.out.println(lobbySongNumber); //testing
            }
            //button to change level music
            //g.drawImage(music, 910, 385, 50, 50, this);
            if(lastClick_xCoord > 910 && lastClick_xCoord < 910+50 &&
            lastClick_yCoord > 385+30 && lastClick_yCoord < 385+50+30)
            {
                levelSongNumber ++;
                if(levelSongNumber >= 6)
                {
                    levelSongNumber -= 6;
                }
                if(clipLevel != null)
                {
                    clipLevel.stop();
                }
                if(!gameOver)
                {
                    if(levelSongsUnlocked[levelSongNumber])
                    {
                        playMusicLevel(levelSongNames[levelSongNumber]);
                    }
                }
                //System.out.println(lobbySongNumber); //testing
            }

            //button for level 1
            //g.drawRect(125, 175, 200, 100); but the y value should be 30 more "why + 25?"
            if(lastClick_xCoord > 125 && lastClick_xCoord < 125+200 &&
            lastClick_yCoord > 175+30 && lastClick_yCoord < 175+100+30)
            {
                if(clipLobby != null)
                {
                    clipLobby.stop();
                }
                System.out.println("---------------");
                System.out.println("Level 1");
                gameMode = "Level 1";
                gameOver = false;
                initializeGameModeSettings = false;
                if(levelSongsUnlocked[levelSongNumber])
                {
                    playMusicLevel(levelSongNames[levelSongNumber]);
                }
                repaint();
            }
            //button for bullet challenge
            //g.drawRect(125, 380, 200, 100); but the y value should be 30 more "why + 25?"
            if(lastClick_xCoord > 125 && lastClick_xCoord < 125+200 &&
            lastClick_yCoord > 380+30 && lastClick_yCoord < 380+100+30)
            {
                if(clipLobby != null)
                {
                    clipLobby.stop();
                }
                System.out.println("---------------");
                System.out.println("Bullet Challenge");
                gameMode = "Bullet Challenge";
                gameOver = false;
                initializeGameModeSettings = false;
                if(levelSongsUnlocked[levelSongNumber])
                {
                    playMusicLevel(levelSongNames[levelSongNumber]);
                }
                repaint();
            }
            //button for level 2 
            //g.drawRect(390, 175, 200, 100);  but the y value should be 30 more "why + 25?"
            if(lastClick_xCoord > 390 && lastClick_xCoord < 390+200 &&
            lastClick_yCoord > 175+30 && lastClick_yCoord < 175+100+30  &&
            highScore1 >= 75 &&  highBulletScore >= 60) //testing;
            {
                if(clipLobby != null)
                {
                    clipLobby.stop();
                }
                System.out.println("---------------");
                System.out.println("Level 2");
                gameMode = "Level 2";
                gameOver = false;
                initializeGameModeSettings = false;
                if(levelSongsUnlocked[levelSongNumber])
                {
                    playMusicLevel(levelSongNames[levelSongNumber]);
                }
                repaint();
            }
            //button for laser challenge
            //g.drawRect(390, 380, 200, 100); but the y value should be 30 more "why + 25?"
            if(lastClick_xCoord > 390 && lastClick_xCoord < 390+200 &&
               lastClick_yCoord > 380+30 && lastClick_yCoord < 380+100+30 &&
               highScore1 >= 75 && highBulletScore >= 60)
            {
                if(clipLobby != null)
                {
                    clipLobby.stop();
                }
                System.out.println("---------------");
                System.out.println("Laser Challenge");
                gameMode = "Laser Challenge";
                gameOver = false;
                initializeGameModeSettings = false;
                if(levelSongsUnlocked[levelSongNumber])
                {
                    playMusicLevel(levelSongNames[levelSongNumber]);
                }
                repaint();
            }
            //button for level 3
            //g.drawRect(655, 175, 200, 100);    but the y value should be 30 more
            if(lastClick_xCoord > 655 && lastClick_xCoord < 655+200 &&
            lastClick_yCoord > 175+30 && lastClick_yCoord < 175+100+30  &&
            highScore1 >= 100 && highScore2 >= 40 && 
            highBulletScore >= 80 && highLaserScore >= 75)
            {
                if(clipLobby != null)
                {
                    clipLobby.stop();
                }
                System.out.println("---------------");
                System.out.println("Level 3");
                gameMode = "Level 3";
                gameOver = false;
                initializeGameModeSettings = false;
                if(levelSongsUnlocked[levelSongNumber])
                {
                    playMusicLevel(levelSongNames[levelSongNumber]);
                }
                repaint();
            }
            //button for bouncer challenge 
            //g.drawRect(655, 380, 200, 100);     but the y value should be 30 more
            if(lastClick_xCoord > 655 && lastClick_xCoord < 655+200 &&
            lastClick_yCoord > 380+30 && lastClick_yCoord < 380+100+30  &&
            highScore1 >= 100 && highScore2 >= 40 && 
            highBulletScore >= 80 && highLaserScore >= 75)
            {
                if(clipLobby != null)
                {
                    clipLobby.stop();
                }
                System.out.println("---------------");
                System.out.println("Bouncer Challenge");
                gameMode = "Bouncer Challenge";
                gameOver = false;
                initializeGameModeSettings = false;
                if(levelSongsUnlocked[levelSongNumber])
                {
                    playMusicLevel(levelSongNames[levelSongNumber]);
                }
                repaint();
            }

            //button to unlock themes
            //g.drawRect(760, 125, 180, 35);
            if(lastClick_xCoord > 760 && lastClick_xCoord < 760+180 &&
            lastClick_yCoord > 125+30 && lastClick_yCoord < 125+35+30)
            {
                if(themeNumber == 2 && themesUnlocked[1] == false && coins>=20)
                {
                    coins -= 20;
                    themesUnlocked[1] = true;
                }
                else if(themeNumber == 3 && themesUnlocked[2] == false && coins>=30)
                {
                    coins-=30;
                    themesUnlocked[2] = true;
                }
                else if(themeNumber == 4 && themesUnlocked[3] == false && coins>=40)
                {
                    coins-=40;
                    themesUnlocked[3] = true;
                }
                else if(themeNumber == 5 && themesUnlocked[4] == false && coins>=50)
                {
                    coins-=50;
                    themesUnlocked[4] = true;
                }
            }
            repaint();

            //button to unlock lobby songs
            //g.drawRect(70, 210, 25, 160);
            if(lastClick_xCoord > 70 && lastClick_xCoord < 70+25 &&
            lastClick_yCoord > 210+30 && lastClick_yCoord < 210+160+30 &&
            coins >= 10)
            {
                if(lobbySongsUnlocked[lobbySongNumber] == false)
                {
                    lobbySongsUnlocked[lobbySongNumber] = true;
                    coins -= 10;
                    playMusicLobby(lobbySongNames[lobbySongNumber]);
                }
            }
            //button to unlock level songs
            //g.drawRect(885, 210, 25, 160);
            if(lastClick_xCoord > 885 && lastClick_xCoord < 885+25 &&
            lastClick_yCoord > 210+30 && lastClick_yCoord < 210+160+30 &&
            coins >= 10)
            {
                if(levelSongsUnlocked[levelSongNumber] == false)
                {
                    levelSongsUnlocked[levelSongNumber] = true;
                    coins -= 10;
                }
            }
            repaint();

            //button to unlock heal power up
            //g.drawRect(80, 665, 180, 35);
            if(powerupsUnlocked[0] == false && lastClick_xCoord > 80 && lastClick_xCoord < 80+180 &&
            lastClick_yCoord > 665+30 && lastClick_yCoord < 665+35+30 &&
            coins >= 30)
            {
                coins-=30;
                powerupsUnlocked[0] = true;
            }
            //button to unlock point power up
            //g.drawRect(305, 665, 180, 35);
            if(powerupsUnlocked[1] == false && lastClick_xCoord > 305 && lastClick_xCoord < 305+180 &&
            lastClick_yCoord > 665+30 && lastClick_yCoord < 665+35+30 &&
            coins >= 30)
            {
                coins-=30;
                powerupsUnlocked[1] = true;
            }
            //button to unlock point power up
            //g.drawRect(530, 665, 180, 35);
            if(powerupsUnlocked[2] == false && lastClick_xCoord > 530 && lastClick_xCoord < 530+180 &&
            lastClick_yCoord > 665+30 && lastClick_yCoord < 665+35+30 &&
            coins >= 30)
            {
                coins-=30;
                powerupsUnlocked[2] = true;
            }
            //button to unlock money power up
            //g.drawRect(755, 665, 180, 35);
            if(powerupsUnlocked[3] == false && lastClick_xCoord > 755 && lastClick_xCoord < 755+180 &&
            lastClick_yCoord > 665+30 && lastClick_yCoord < 665+35+30 &&
            coins >= 30)
            {
                coins-=30;
                powerupsUnlocked[3] = true;
            }
            repaint();
        }
        else //gameOver == false
        {
            for(int i=0; i<obstacles.size(); i++)
            {
                SuperObstacle tempObs = obstacles.get(i);
                int tempX = tempObs.getPos().x;
                int tempY = tempObs.getPos().y;
                int tempWidth = tempObs.getWidth();
                int tempHeight = tempObs.getHeight();
                //collectibles from clicking
                if(lastClick_xCoord > tempX && lastClick_xCoord < tempX+tempWidth &&
                lastClick_yCoord > tempY+30 && lastClick_yCoord < tempY+tempHeight+30)
                {
                    if(tempObs instanceof Coin)
                    {
                        coins++;
                        //System.out.println("removed coin"); //testing
                        obstacles.remove(i);
                    }
                    else if(tempObs instanceof HealPowerup)
                    {
                        lives++;
                        obstacles.remove(i);
                    }
                    else if(tempObs instanceof PointPowerup)
                    {
                        //System.out.println("gained "+(int)(points * 0.1)+" points");
                        points += points * 0.1;
                        //credit to
                        //https://www.baeldung.com/java-round-decimal-number
                        points = Math.round(points*Math.pow(10, 5))/Math.pow(10, 5);
                        obstacles.remove(i);
                    }
                    else if(tempObs instanceof ClearPowerup)
                    {
                        obstacles.remove(i);
                        for(int j=0; j<obstacles.size(); j++)
                        {
                            SuperObstacle tempObs2 = obstacles.get(j);
                            if(tempObs2 instanceof BulletHorizontal || tempObs2 instanceof BulletVertical ||
                            tempObs2 instanceof BulletDiagonalNWtoSE || tempObs2 instanceof BulletDiagonalNEtoSW ||
                            tempObs2 instanceof BulletHorizWave || tempObs2 instanceof BulletVertWave ||
                            tempObs2 instanceof Laser) 
                            {
                                obstacles.remove(j);
                            }
                            //obstacles.clear();
                        }
                    }
                    else if(tempObs instanceof MoneyPowerup)
                    {
                        int rand = (int)(3+Math.random()*3.0); //to do
                        coins += rand;
                        obstacles.remove(i);
                    }
                }
            }
            repaint();
        }

    }
    // });
    // }

    /**
     * when mouse is pressed on a component(holding down)
     */
    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    /**
     * when mouse is released from a component(let go)
     */
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    /**
     * when mouse enters a component
     */
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            System.out.println("space clicked");     //testing
            lives = 0;
            gameOver = true;
        }
        //developer cheats  // testing;
        if(e.getKeyCode() == KeyEvent.VK_COMMA)
        {
            coins += 10;
        }
        if(e.getKeyCode() == KeyEvent.VK_1)
        {
            highScore1 = 0;
            highBulletScore = 0;
            highScore2 = 0;
            highLaserScore = 0;
            highScore3 = 0;
            highBouncerScore = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_2)
        {
            highScore1 = 75;
            highBulletScore = 60;
            highScore2 = 0;
            highLaserScore = 0;
            highScore3 = 0;
            highBouncerScore = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_3)
        {
            highScore1 = 100;
            highBulletScore = 80;
            highScore2 = 40;
            highLaserScore = 75;
            highScore3 = 0;
            highBouncerScore = 0;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e)
    {

    }

    public void keyTyped(KeyEvent e)
    {

    }

    public void setTitle(String title)
    {

    }

    public Clip playMusicLobby(String songName)
    {
        try
        {
            File songFile = new File(songName+".wav");
            AudioInputStream music = AudioSystem.getAudioInputStream(songFile);
            clipLobby = AudioSystem.getClip();
            clipLobby.open(music);
            clipLobby.start();
            clipLobby.loop(Clip.LOOP_CONTINUOUSLY);
            return clipLobby;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public Clip playMusicLevel(String songName)
    {
        try
        {
            File songFile = new File(songName+".wav");
            AudioInputStream music = AudioSystem.getAudioInputStream(songFile);
            clipLevel = AudioSystem.getClip();
            clipLevel.open(music);
            clipLevel.start();
            clipLevel.loop(Clip.LOOP_CONTINUOUSLY);
            return clipLevel;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public Clip playMusicMLG()
    {
        try
        {
            File songFile = new File("MLG sound effects.wav");
            AudioInputStream music = AudioSystem.getAudioInputStream(songFile);
            clipMLG = AudioSystem.getClip();
            clipMLG.open(music);
            clipMLG.start();
            clipMLG.loop(Clip.LOOP_CONTINUOUSLY);
            return clipMLG;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public Clip playMusicHitmarker()
    {
        try
        {
            File songFile = new File("hitmarker.wav");
            AudioInputStream music = AudioSystem.getAudioInputStream(songFile);
            clipHitmarker = AudioSystem.getClip();
            clipHitmarker.open(music);
            clipHitmarker.start();
            return clipMLG;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public void makeObs_Coin()
    {
        int randX = (int)(Math.random()*(windowWidth+1.0-20));
        int randY = (int)(Math.random()*(windowHeight+1.0-20));
        Coin newObs = new Coin(randX, randY, 52, 45);
        obstacles.add(newObs);
        //System.out.println("new coin"); //testing
    }

    public void makeObs_HealPowerup()
    {
        int randX = (int)(Math.random()*(windowWidth+1.0-20));
        int randY = (int)(Math.random()*(windowHeight+1.0-20));
        HealPowerup newObs = new HealPowerup(randX, randY, 60, 60);
        obstacles.add(newObs);
        //System.out.println("new medkit"); //testing
    }

    public void makeObs_PointPowerup()
    {
        int randX = (int)(Math.random()*(windowWidth+1.0-20));
        int randY = (int)(Math.random()*(windowHeight+1.0-20));
        PointPowerup newObs = new PointPowerup(randX, randY, 50, 50);
        obstacles.add(newObs);
        //System.out.println("new stonks"); //testing
    }

    public void makeObs_ClearPowerup()
    {
        int randX = (int)(Math.random()*(windowWidth+1.0-20));
        int randY = (int)(Math.random()*(windowHeight+1.0-20));
        ClearPowerup newObs = new ClearPowerup(randX, randY, 50, 50);
        obstacles.add(newObs);
    }

    public void makeObs_MoneyPowerup()
    {
        int randX = (int)(Math.random()*(windowWidth+1.0-20));
        int randY = (int)(Math.random()*(windowHeight+1.0-20));
        MoneyPowerup newObs = new MoneyPowerup(randX, randY, 60, 60);
        obstacles.add(newObs);
    }

    public void makeObs_BulletMovingRight()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = -randWidth;
        int randY = (int)(Math.random()*(windowHeight+1.0-randHeight));
        BulletHorizontal newObs = new BulletHorizontal(randX, randY, randWidth, randHeight, true);
        obstacles.add(newObs);
    }

    public void makeObs_BulletMovingLeft()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = windowWidth+randWidth;
        int randY = (int)(Math.random()*(windowHeight+1.0-randHeight));
        BulletHorizontal newObs = new BulletHorizontal(randX, randY, randWidth, randHeight, false);
        obstacles.add(newObs);
    }

    public void makeObs_BulletMovingDown()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = (int)(Math.random()*(windowWidth+1.0-randWidth));
        int randY = -randHeight;
        BulletVertical newObs = new BulletVertical(randX, randY, randWidth, randHeight, true);
        obstacles.add(newObs);
    }

    public void makeObs_BulletMovingUp()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = (int)(Math.random()*(windowWidth+1.0-randWidth));
        int randY = windowHeight;
        BulletVertical newObs = new BulletVertical(randX, randY, randWidth, randHeight, false);
        obstacles.add(newObs);
    }

    public int makeObs_Laser()
    {
        int randX = (int)(Math.random()*(windowWidth+1.0));
        int randY = (int)(Math.random()*(windowHeight+1.0));
        int randHorizOrVert = (int)(Math.random()*2.0);
        if(randHorizOrVert % 2 == 0)
        {
            //horizontal laser
            Laser newObs = new Laser(0, randY, windowWidth, 20);
            obstacles.add(newObs);
        }
        else
        {
            //vertical laser
            Laser newObs = new Laser(randX, 0, 20, windowHeight);
            obstacles.add(newObs);
        }
        return obstacles.size()-1;
    }

    public void laserTransformation(int i, SuperObstacle tempObs)
    {
        if(tempObs instanceof Laser)
        {
            tempObs.runLaserTimer();
            if(tempObs.getLaserTimer() >= laserHarmRate)
            {
                if(tempObs.getHarmful() == false)
                {
                    tempObs.setHarmful();
                    //System.out.println("become harmful"); //testing
                }
                else
                {
                    if(tempObs.getLaserTimer() >= laserHarmRate+300)
                    {
                        obstacles.remove(i);
                    }
                }
            }       
        }
    }

    public void makeObs_BulletMovingSE()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = (int)(Math.random()*(windowWidth+1.0-randWidth));
        int randY = -randHeight;
        BulletDiagonalNWtoSE newObs = new BulletDiagonalNWtoSE(randX, randY, randWidth, randHeight, true);
        obstacles.add(newObs);
    }

    public void makeObs_BulletMovingNW()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = (int)(Math.random()*(windowWidth+1.0-randWidth));
        int randY = windowHeight;
        BulletDiagonalNWtoSE newObs = new BulletDiagonalNWtoSE(randX, randY, randWidth, randHeight, false);
        obstacles.add(newObs);
    }

    public void makeObs_BulletMovingSW()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = windowWidth+randWidth;
        int randY = (int)(Math.random()*(windowHeight+1.0-randHeight));
        BulletDiagonalNEtoSW newObs = new BulletDiagonalNEtoSW(randX, randY, randWidth, randHeight, true);
        obstacles.add(newObs);
    }

    public void makeObs_BulletMovingNE()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = -randWidth;
        int randY = (int)(Math.random()*(windowHeight+1.0-randHeight));
        BulletDiagonalNEtoSW newObs = new BulletDiagonalNEtoSW(randX, randY, randWidth, randHeight, false);
        obstacles.add(newObs);
    }

    public void makeObs_DVDscreensaver()
    {
        int randDimension = (int)(50+Math.random()*51.0);
        int randX = (int)(Math.random()*(windowWidth+1.0-randDimension));
        int randY = (int)(Math.random()*(windowHeight+1.0-randDimension));
        while(randX > randDimension && randX < windowWidth-randDimension &&
        randY > randDimension && randY < windowHeight-randDimension)
        {
            randX = (int)(Math.random()*(windowWidth+1.0-randDimension));
            randY = (int)(Math.random()*(windowHeight+1.0-randDimension));
        }
        DVDscreensaver newObs = new DVDscreensaver(randX, randY, randDimension, randDimension, true, true);
        //System.out.println("new DVD"); //testing
        //System.out.println(randX+" , "+randY); //testing
        obstacles.add(newObs);
    }

    public void makeObs_BulletWaveRight()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = -randWidth;
        int randY = (int)(Math.random()*(windowHeight+1.0-randHeight));
        BulletHorizWave newObs = new BulletHorizWave(randX, randY, randWidth, randHeight, true);
        obstacles.add(newObs);
    }

    public void makeObs_BulletWaveLeft()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = 1000+randWidth;
        int randY = (int)(Math.random()*(windowHeight+1.0-randHeight));
        BulletHorizWave newObs = new BulletHorizWave(randX, randY, randWidth, randHeight, false);
        obstacles.add(newObs);
    }

    public void makeObs_BulletWaveDown()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = (int)(Math.random()*(windowWidth+1.0-randWidth));
        int randY = -randHeight;
        BulletVertWave newObs = new BulletVertWave(randX, randY, randWidth, randHeight, true);
        obstacles.add(newObs);
    }

    public void makeObs_BulletWaveUp()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = (int)(Math.random()*(windowWidth+1.0-randWidth));
        int randY = windowHeight;
        BulletVertWave newObs = new BulletVertWave(randX, randY, randWidth, randHeight, false);
        obstacles.add(newObs);
    }

    public void checkCollide(SuperObstacle tempObs)
    {
        if(mouseLoc.x > tempObs.getPos().x+frame_xCoord && 
        mouseLoc.x < tempObs.getPos().x+frame_xCoord + tempObs.getWidth() &&
        mouseLoc.y > tempObs.getPos().y+frame_yCoord &&
        mouseLoc.y < tempObs.getPos().y+frame_yCoord + tempObs.getHeight())
        {
            if(tempObs instanceof Laser && tempObs.getHarmful() == false)
            {
                //nothing; harmless
            }
            else if(tempObs instanceof Coin || tempObs instanceof HealPowerup ||
            tempObs instanceof PointPowerup || tempObs instanceof ClearPowerup ||
            tempObs instanceof MoneyPowerup)
            {
                //nothing; harmless
            }
            else
            {
                System.out.println("ouch");     //testing
                lives --;
                System.out.println(mouseLoc.x +" "+ mouseLoc.y);
                //so computer doesn't spam "ouch"
                //this ends up like a small delay when 
                //player is hurt, which works well for gameplay
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    public void playLevel1()
    {
        if(initializeGameModeSettings == false) //runs one time
        {
            lives = 3;
            time = 0;
            points = 0;
            delayVariable = 4;
            bulletSpawnRate = 50;
            laserSpawnRate = 300;
            laserHarmRate = 700;
            healPowerupRate = 40+(int)(Math.random()*11.0);
            pointPowerupRate = 30+(int)(Math.random()*11.0);
            clearPowerupRate = 15+(int)(Math.random()*11.0);
            moneyPowerupRate = 20+(int)(Math.random()*11.0);
            //System.out.println(healPowerupRate); //testing
            initializeGameModeSettings = true;
        }

        if(time > 0 && time/200.0 % 5 == 0)
        {
            makeObs_Coin();
        }

        if(powerupsUnlocked[0] == true && time > 0 && time/200.0 % healPowerupRate == 0)
        {
            makeObs_HealPowerup();
        }
        if(powerupsUnlocked[1] == true && time > 0 && time/200.0 % pointPowerupRate == 0)
        {
            makeObs_PointPowerup();
        }
        if(powerupsUnlocked[2] == true && time > 0 && time/200.0 % clearPowerupRate == 0)
        {
            makeObs_ClearPowerup();
        }
        if(powerupsUnlocked[3] == true && time > 0 && time/200.0 % moneyPowerupRate == 0)
        {
            makeObs_MoneyPowerup();
        }

        if(time > 0 && time % 4000 == 0 )
        {
            delayVariable -= 0.2;
            bulletSpawnRate -= 5;
            laserHarmRate -= 50;
            //System.out.println(delayVariable); //testing
            System.out.println("harder");
        }

        if(lives<=0)
        {
            coins += (int)(points/10);
            points = Math.round(points*Math.pow(10, 5))/Math.pow(10, 5);
            //System.out.println((int)((time/200.0)/20)); //testing
            gameOver = true;
        }

        if(time % bulletSpawnRate == 0)
        {
            makeObs_BulletMovingRight();
            makeObs_BulletMovingDown();
            repaint();
        }
        if(time % laserSpawnRate == 0 && time/200.0 > 40)
        {
            makeObs_Laser();
            repaint();
        }

        for(int i=0; i<obstacles.size(); i++)
        {
            SuperObstacle tempObs = obstacles.get(i);
            laserTransformation(i, tempObs);
        }

        for(int i=0; i<obstacles.size(); i++)
        {
            SuperObstacle tempObs = obstacles.get(i);
            checkCollide(tempObs);
            tempObs.move();
            if(tempObs.getPos().x > 1100 || tempObs.getPos().y > 800 ||
            tempObs.getPos().x < -300 || tempObs.getPos().y < -300)
            {
                obstacles.remove(i);
                i--;
                //System.out.println("obstacle removed");     //testing
            }
        }
    }

    public void playBulletChallenge()
    {
        if(initializeGameModeSettings == false) //runs one time
        {
            lives = 3;
            time = 0;
            points = 0;
            delayVariable = 5;
            bulletSpawnRate = 150;
            healPowerupRate = 40+(int)(Math.random()*11.0);
            pointPowerupRate = 30+(int)(Math.random()*11.0);
            clearPowerupRate = 15+(int)(Math.random()*11.0);
            moneyPowerupRate = 20+(int)(Math.random()*11.0);
            initializeGameModeSettings = true;
        }

        if(time > 0 && time/200.0 % 5 == 0)
        {
            makeObs_Coin();
        }

        if(powerupsUnlocked[0] == true && time > 0 && time/200.0 % healPowerupRate == 0)
        {
            makeObs_HealPowerup();
        }
        if(powerupsUnlocked[1] == true && time > 0 && time/200.0 % pointPowerupRate == 0)
        {
            makeObs_PointPowerup();
        }
        if(powerupsUnlocked[2] == true && time > 0 && time/200.0 % clearPowerupRate == 0)
        {
            makeObs_ClearPowerup();
        }
        if(powerupsUnlocked[3] == true && time > 0 && time/200.0 % moneyPowerupRate == 0)
        {
            makeObs_MoneyPowerup();
        }

        if(lives<=0)
        {
            coins += (int)(points/10);
            points = Math.round(points*Math.pow(10, 5))/Math.pow(10, 5);
            //System.out.println((int)((time/200.0)/20)); //testing
            gameOver = true;
        }

        if(time % bulletSpawnRate == 0)
        {
            makeObs_BulletMovingRight();
            if(time/200.0 >= 5)
            {
                makeObs_BulletMovingDown();
            }
            if(time/200.0 >= 10)
            {
                makeObs_BulletMovingLeft();
            }
            if(time/200.0 >= 15)
            {
                makeObs_BulletMovingUp();
            }
            if(time/200.0 >= 20)
            {
                makeObs_BulletMovingSE();
            }
            if(time/200.0 >= 25)
            {
                makeObs_BulletMovingSW();
            }
            if(time/200.0 >= 30)
            {
                makeObs_BulletMovingNW();
            }
            if(time/200.0 >= 35)
            {
                makeObs_BulletMovingNE();
            }
            if(time/200.0 >= 40)
            {
                makeObs_BulletWaveRight();
            }
            if(time/200.0 >= 45) 
            {
                makeObs_BulletWaveDown();
            }
            if(time/200.0 >= 50)
            {
                makeObs_BulletWaveLeft();
            }
            if(time/200.0 >= 55)
            {
                makeObs_BulletWaveUp();
            }
            repaint();
        }
        if(time/200.0 > 60 && time/200.0 % 5 == 0 )
        {
            bulletSpawnRate -= 5;
            System.out.println("harder");
        }

        for(int i=0; i<obstacles.size(); i++)
        {
            SuperObstacle tempObs = obstacles.get(i);
            checkCollide(tempObs);
            if(tempObs instanceof BulletHorizWave || tempObs instanceof BulletVertWave)
            {
                int var = (int)(Math.toRadians(Math.sin(time/10.0))*100);
                //System.out.println(var); //testing
                tempObs.move(var);
            }
            else
            {
                tempObs.move();
            }
            if(tempObs.getPos().x > 1100 || tempObs.getPos().y > 800 ||
            tempObs.getPos().x < -300 || tempObs.getPos().y < -300)
            {
                obstacles.remove(i);
                i--;
                //System.out.println("obstacle removed");     //testing
            }
        }
    }

    public void playLevel2()
    {

        if(initializeGameModeSettings == false) //runs one time
        {
            lives = 3;
            time = 0;
            points = 0;
            delayVariable = 6;
            bulletSpawnRate = 70;
            laserSpawnRate = 300;
            laserHarmRate = 500;
            healPowerupRate = 15+(int)(Math.random()*6.0);
            pointPowerupRate = 10;
            clearPowerupRate = 5+(int)(Math.random()*6.0);
            moneyPowerupRate = 5+(int)(Math.random()*6.0);
            initializeGameModeSettings = true;
        }

        if(time > 0 && time/200.0 % 3 == 0)
        {
            makeObs_Coin();
        }

        if(powerupsUnlocked[0] == true && time > 0 && time/200.0 % healPowerupRate == 0)
        {
            makeObs_HealPowerup();
        }
        if(powerupsUnlocked[1] == true && time > 0 && time/200.0 % pointPowerupRate == 0)
        {
            makeObs_PointPowerup();
        }
        if(powerupsUnlocked[2] == true && time > 0 && time/200.0 % clearPowerupRate == 0)
        {
            makeObs_ClearPowerup();
        }
        if(powerupsUnlocked[3] == true && time > 0 && time/200.0 % moneyPowerupRate == 0)
        {
            makeObs_MoneyPowerup();
        }

        if(time > 0 && time % 2000 == 0 )
        {
            bulletSpawnRate -= 10;
            laserSpawnRate -= 30;
            laserHarmRate -= 50;
            //System.out.println(delayVariable); //testing
            System.out.println("harder");
        }

        if(lives<=0)
        {
            coins += (int)(points/5);
            points = Math.round(points*Math.pow(10, 5))/Math.pow(10, 5);
            gameOver = true;
        }

        if(time % bulletSpawnRate == 0)
        {
            makeObs_BulletMovingRight();
            makeObs_BulletMovingDown();
            makeObs_BulletMovingLeft();
            makeObs_BulletMovingUp();
            repaint();
        }
        if(time % laserSpawnRate == 0)
        {
            makeObs_Laser();
            repaint();
        }
        for(int i=0; i<obstacles.size(); i++)
        {
            SuperObstacle tempObs = obstacles.get(i);
            laserTransformation(i, tempObs);
        }
        if(time/200.0 >= 20 && time % bulletSpawnRate == 0) //testing 1 = 20
        {
            makeObs_BulletMovingSE();
            makeObs_BulletMovingNW();
            makeObs_BulletMovingSW();
            makeObs_BulletMovingNE();
        }

        for(int i=0; i<obstacles.size(); i++)
        {
            SuperObstacle tempObs = obstacles.get(i);
            checkCollide(tempObs);
            tempObs.move();
            if(tempObs.getPos().x > 1100 || tempObs.getPos().y > 800 ||
            tempObs.getPos().x < -300 || tempObs.getPos().y < -300)
            {
                obstacles.remove(i);
                i--;
                //System.out.println("obstacle removed");     //testing
            }
        }
    }

    public void playLaserChallenge()
    {
        if(initializeGameModeSettings == false) //runs one time
        {
            lives = 3;
            time = 0;
            points = 0;
            delayVariable = 4;
            laserSpawnRate = 100;
            laserHarmRate = 500;
            healPowerupRate = 40+(int)(Math.random()*11.0);
            pointPowerupRate = 30+(int)(Math.random()*11.0);
            clearPowerupRate = 15+(int)(Math.random()*11.0);
            moneyPowerupRate = 20+(int)(Math.random()*11.0);
            //System.out.println(healPowerupRate); //testing
            initializeGameModeSettings = true;
        }

        if(time > 0 && time/200.0 % 5 == 0)
        {
            makeObs_Coin();
        }

        if(powerupsUnlocked[0] == true && time > 0 && time/200.0 % healPowerupRate == 0)
        {
            makeObs_HealPowerup();
        }
        if(powerupsUnlocked[1] == true && time > 0 && time/200.0 % pointPowerupRate == 0)
        {
            makeObs_PointPowerup();
        }
        if(powerupsUnlocked[2] == true && time > 0 && time/200.0 % clearPowerupRate == 0)
        {
            makeObs_ClearPowerup();
        }
        if(powerupsUnlocked[3] == true && time > 0 && time/200.0 % moneyPowerupRate == 0)
        {
            makeObs_MoneyPowerup();
        }

        if(time/200.0 > 0 && time/200.0 % 5 == 0 )
        {
            laserSpawnRate -= 5;
            laserHarmRate -= 20;
            //System.out.println(delayVariable); //testing
            System.out.println("harder");
        }

        if(lives<=0)
        {
            coins += (int)(points/10);
            points = Math.round(points*Math.pow(10, 5))/Math.pow(10, 5);
            //System.out.println((int)((time/200.0)/20)); //testing
            gameOver = true;
        }

        if(time % laserSpawnRate == 0 && time/200.0 > 0)
        {
            makeObs_Laser();
            repaint();
        }
        if(time % laserSpawnRate == 0 && time/200.0 > 50)
        {
            makeObs_Laser();
            repaint();
        }

        for(int i=0; i<obstacles.size(); i++)
        {
            SuperObstacle tempObs = obstacles.get(i);
            laserTransformation(i, tempObs);
        }

        for(int i=0; i<obstacles.size(); i++)
        {
            SuperObstacle tempObs = obstacles.get(i);
            checkCollide(tempObs);
        }
    }
    
    public void playLevel3()
    {
        if(initializeGameModeSettings == false) //runs one time
        {
            lives = 3;
            time = 0;
            points = 0;
            delayVariable = 5;
            bulletSpawnRate = 150;
            laserSpawnRate = 300;
            laserHarmRate = 500;
            makeObs_DVDscreensaver();
            makeObs_DVDscreensaver();
            makeObs_DVDscreensaver();
            makeObs_DVDscreensaver();
            healPowerupRate = 30+(int)(Math.random()*11.0);
            pointPowerupRate = 25+(int)(Math.random()*11.0);
            clearPowerupRate = 15+(int)(Math.random()*11.0);
            moneyPowerupRate = 20+(int)(Math.random()*11.0);
            initializeGameModeSettings = true;
        }

        if(time > 0 && time/200.0 % 5 == 0)
        {
            makeObs_Coin();
        }

        if(powerupsUnlocked[0] == true && time > 0 && time/200.0 % healPowerupRate == 0)
        {
            makeObs_HealPowerup();
        }
        if(powerupsUnlocked[1] == true && time > 0 && time/200.0 % pointPowerupRate == 0)
        {
            makeObs_PointPowerup();
        }
        if(powerupsUnlocked[2] == true && time > 0 && time/200.0 % clearPowerupRate == 0)
        {
            makeObs_ClearPowerup();
        }
        if(powerupsUnlocked[3] == true && time > 0 && time/200.0 % moneyPowerupRate == 0)
        {
            makeObs_MoneyPowerup();
        }

        if(time > 0 && time % 2000 == 0 )
        {
            bulletSpawnRate -= 15;
            laserSpawnRate -= 30;
            laserHarmRate -= 20;
            //System.out.println(delayVariable); //testing
            System.out.println("harder");
        }

        if(lives<=0)
        {
            coins += (int)(points/7);
            points = Math.round(points*Math.pow(10, 5))/Math.pow(10, 5);
            gameOver = true;
        }

        if(time/200.0 < 25 && time % bulletSpawnRate == 0)
        {
            makeObs_BulletMovingRight();
            makeObs_BulletMovingDown();
            makeObs_BulletMovingLeft();
            makeObs_BulletMovingUp();
        }
        if(time % laserSpawnRate == 0)
        {
            makeObs_Laser();
            repaint();
        }
        for(int i=0; i<obstacles.size(); i++)
        {
            SuperObstacle tempObs = obstacles.get(i);
            laserTransformation(i, tempObs);
        }
        if(time/200.0 >= 25 && time % bulletSpawnRate == 0) //testing; 1 = 25
        {
            makeObs_BulletWaveRight();
            makeObs_BulletWaveLeft();
            makeObs_BulletWaveUp();
            makeObs_BulletWaveDown();
        }
        if(time/200.0 == 40) //testing; 3 = 40
        {
            makeObs_DVDscreensaver();
        }

        for(int i=0; i<obstacles.size(); i++)
        {
            SuperObstacle tempObs = obstacles.get(i);
            checkCollide(tempObs);
            if(tempObs instanceof BulletHorizWave || tempObs instanceof BulletVertWave)
            {
                int var = (int)(Math.toRadians(Math.sin(time/10.0))*100);
                //System.out.println(var); //testing
                tempObs.move(var);
            }
            else if(tempObs instanceof DVDscreensaver)
            {
                tempObs.move(windowWidth, windowHeight);
            }
            else
            {
                tempObs.move();
            }
            if(tempObs.getPos().x > 1100 || tempObs.getPos().y > 800 ||
            tempObs.getPos().x < -300 || tempObs.getPos().y < -300)
            {
                obstacles.remove(i);
                i--;
                //System.out.println("obstacle removed");     //testing
            }
        }
    }
    
    public void playBouncerChallenge()
    {
        if(initializeGameModeSettings == false) //runs one time
        {
            lives = 3;
            time = 0;
            points = 0;
            delayVariable = 5;
            makeObs_DVDscreensaver();
            healPowerupRate = 40+(int)(Math.random()*11.0);
            pointPowerupRate = 30+(int)(Math.random()*11.0);
            clearPowerupRate = 15+(int)(Math.random()*11.0);
            moneyPowerupRate = 20+(int)(Math.random()*11.0);
            initializeGameModeSettings = true;
        }

        if(time > 0 && time/200.0 % 5 == 0)
        {
            makeObs_Coin();
        }

        if(powerupsUnlocked[0] == true && time > 0 && time/200.0 % healPowerupRate == 0)
        {
            makeObs_HealPowerup();
        }
        if(powerupsUnlocked[1] == true && time > 0 && time/200.0 % pointPowerupRate == 0)
        {
            makeObs_PointPowerup();
        }
        if(powerupsUnlocked[2] == true && time > 0 && time/200.0 % clearPowerupRate == 0)
        {
            makeObs_ClearPowerup();
        }
        if(powerupsUnlocked[3] == true && time > 0 && time/200.0 % moneyPowerupRate == 0)
        {
            makeObs_MoneyPowerup();
        }

        if(time > 0 && time/200.0 % 50 == 0 )
        {
            delayVariable--;
            System.out.println("harder");
        }

        if(lives<=0)
        {
            coins += (int)(points/10);
            points = Math.round(points*Math.pow(10, 5))/Math.pow(10, 5);
            //System.out.println((int)((time/200.0)/20)); //testing
            gameOver = true;
        }

        if(time/200.0 > 0 && time/200.0 % 4 == 0)
        {
            makeObs_DVDscreensaver();
        }

        for(int i=0; i<obstacles.size(); i++)
        {
            SuperObstacle tempObs = obstacles.get(i);
            checkCollide(tempObs);
            tempObs.move(windowWidth, windowHeight);
        }
    }

    //inherit from Runnable interface
    public void run()
    {

        while(true) //runs this constantly
        {
            //get the x & y coordinates of game window on computer screen
            frame_xCoord = myFrame.getLocation().x;
            frame_yCoord = myFrame.getLocation().y+myFrame.getInsets().top;

            int mouse_xCoord = MouseInfo.getPointerInfo().getLocation().x;
            int mouse_yCoord = MouseInfo.getPointerInfo().getLocation().y;
            if(mouse_xCoord > frame_xCoord && mouse_xCoord < frame_xCoord+windowWidth &&
            mouse_yCoord > frame_yCoord && mouse_yCoord < frame_yCoord+windowHeight)
            {
                mouseLoc = MouseInfo.getPointerInfo().getLocation();
            }
            else if(!gameOver)
            {
                lives = 0;
            }

            //reset game
            if(gameOver)
            {
                //reset game
                if(playMusicGameOver)
                {
                    if(clipLevel != null)
                    {
                        clipLevel.stop();
                    }
                    if(clipMLG != null)
                    {
                        clipMLG.stop();
                    }
                    if(lobbySongsUnlocked[lobbySongNumber] == true)
                    {
                        playMusicLobby(lobbySongNames[lobbySongNumber]);
                    }
                    playMusicGameOver = false;
                }
                if(gameMode.equals("Level 1"))
                {
                    recentScore1 = points;
                    if(points > highScore1)
                    {
                        highScore1 = points; 
                    }
                }
                else if(gameMode.equals("Bullet Challenge"))
                {
                    recentBulletScore = points;
                    if(points > highBulletScore)
                    {
                        highBulletScore = points; 
                    }
                }
                else if(gameMode.equals("Level 2"))
                {
                    recentScore2 = points;
                    if(points > highScore2)
                    {
                        highScore2 = points; 
                    }
                }
                else if(gameMode.equals("Laser Challenge"))
                {
                    recentLaserScore = points;
                    if(points > highLaserScore)
                    {
                        highLaserScore = points; 
                    }
                }
                else if(gameMode.equals("Level 3"))
                {
                    recentScore3 = points;
                    if(points > highScore3)
                    {
                        highScore3 = points; 
                    }
                }
                else if(gameMode.equals("Bouncer Challenge"))
                {
                    recentBouncerScore = points;
                    if(points > highBouncerScore)
                    {
                        highBouncerScore = points; 
                    }
                }

                gameMode = "";
                obstacles.clear();
                initializeGameModeSettings = false;

                //check level2PopUp if mouse hovers over Level 2 or Laser Challenge buttons
                if((mouseLoc.x > 390+frame_xCoord && mouseLoc.x < 390+200+frame_xCoord) &&
                  ((mouseLoc.y > 175+frame_yCoord && mouseLoc.y < 175+100+frame_yCoord) ||
                  (mouseLoc.y > 380+frame_yCoord && mouseLoc.y < 380+100+frame_yCoord)))
                {
                    level2PopUp = true;
                    //System.out.println(level2PopUp); //testing
                }
                else
                {
                    level2PopUp = false;
                }
                //check level3PopUp if mouse hovers over Level 3 or Bouncer Challenge buttons
                if((mouseLoc.x > 655+frame_xCoord && mouseLoc.x < 655+200+frame_xCoord) &&
                  ((mouseLoc.y > 175+frame_yCoord && mouseLoc.y < 175+100+frame_yCoord) ||
                  (mouseLoc.y > 380+frame_yCoord && mouseLoc.y < 380+100+frame_yCoord)))
                {
                    level3PopUp = true;
                    //System.out.println(level2PopUp); //testing
                }
                else
                {
                    level3PopUp = false;
                }
                //check rulesPopUp //g.drawRect(350, 550, 330, 100); old
                if(mouseLoc.x > 425+frame_xCoord && mouseLoc.x < 585+frame_xCoord &&
                mouseLoc.y > 100+frame_yCoord && mouseLoc.y < 140+frame_yCoord)
                {
                    rulesPopUp = true;
                    //System.out.println(level2PopUp); //testing
                }
                else
                {
                    rulesPopUp = false;
                }
            }
            if(!gameOver) //game is running
            {     
                if(!playMusicGameOver)
                {
                    if(clipLobby != null)
                    {
                        clipLobby.stop();
                    }
                    if(levelSongsUnlocked[levelSongNumber])
                    {
                        playMusicLevel(levelSongNames[levelSongNumber]);
                    }
                    if(themeNumber == 5 && themesUnlocked[4] == true)
                    {
                        playMusicMLG();
                    }
                    playMusicGameOver = true;
                }
                if(gameMode.equals("Level 1"))
                {
                    playLevel1();
                }
                else if(gameMode.equals("Bullet Challenge"))
                {
                    playBulletChallenge();
                }
                else if(gameMode.equals("Level 2"))
                {
                    playLevel2();
                }
                else if(gameMode.equals("Laser Challenge"))
                {
                    playLaserChallenge();
                }
                else if(gameMode.equals("Level 3"))
                {
                    playLevel3();
                    memeChangeTime++;
                }
                else if(gameMode.equals("Bouncer Challenge"))
                {
                    playBouncerChallenge();
                    memeChangeTime++;
                }
                time++;
                points += 1.0/200.0;
                points = Math.round(points*Math.pow(10, 5))/Math.pow(10, 5);

                //System.out.println(mouseLoc);     //testing
                repaint();
                //delay to loop every 5 milliseconds; can make this a variable
                //delayVariable
                try {
                    Thread.sleep((long)(delayVariable));
                } catch (InterruptedException e) {

                }
            }
        }
    }

    public static void main(String[] args)
    {

        myFrame = new JFrame("Protect the Pointer!");
        windowHeight = 700; //customizeable window size; default 700
        windowWidth = 1000; //customizeable window size; default 1000
        PTP_Main myPanel = new PTP_Main();
        //System.out.println(gameOver);     //testing
        myPanel.setPreferredSize(new Dimension(windowWidth,windowHeight));

        myFrame.add(myPanel);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Thread(myPanel).start();
    }
}
