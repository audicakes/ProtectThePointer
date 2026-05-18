/**
 * Write a description of class Obstacle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class JSAB_MainSolo extends JPanel implements MouseListener, KeyListener, Runnable
{
    static private JFrame myFrame;
    static private int frame_xCoord;
    static private int frame_yCoord;
    static private JPanel myPanel;

    static private int lastClick_xCoord;
    static private int lastClick_yCoord;
    static private Point mouseLoc;

    static private boolean gameOver;
    static private String gameMode;
    static private ArrayList<Obstacle> obstacles = new ArrayList<>();
    //static private ArrayList<JLabel> obsLabels = new ArrayList<>();
    static private int lives;
    static private double highScore1;

    static private double delayVariable;
    static private int time;

    public JSAB_MainSolo()
    {
        myFrame.addMouseListener(this);
        myFrame.addKeyListener(this);
        gameOver = true;
        gameMode = "";
        lives = 3;
        highScore1 = 0;
    }

    public void paintComponent(Graphics g){
        if(gameOver == true)
        {
            this.setBackground(Color.WHITE);
            g.setColor(Color.BLACK);

            // title text
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            g.drawString("Protect the Pointer!",300,75);

            //rules text
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.drawString("How To Play:",420,550);
            g.drawString("* Keep your mouse on the window at all times",250,600);
            g.drawString("* Do not let your mouse touch anything",250,625);
            g.drawString("* Press spacebar to return to home screen",250,650);

            g.drawString("Created by Kevin Zhang :)",350,150);
            //Level 1
            g.drawString("Level 1", 235, 275);
            g.drawRect(175, 225, 200, 100); 
            g.drawString("Best Score: "+highScore1+"", 175, 375);
        }
        else //gameOver == false
        {
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
            g.drawString(lives+"", 15, 45);
            g.setColor(Color.BLACK);
            //time counter
            g.drawString(time/200.0+"", 75, 45);

            //obstacle
            for(int i=0; i<obstacles.size(); i++)
            {
                Obstacle tempObs = obstacles.get(i);
                int tempX = tempObs.getPos().x;
                int tempY = tempObs.getPos().y;
                int tempWidth = tempObs.getWidth();
                int tempHeight = tempObs.getHeight();

                //horizontal and vertical Obstacles 
                if(tempObs.getType().equals("typeHoriz") || 
                //if(tempObs instanceof HorizontalBullet == true || 
                tempObs.getType().equals("typeVert"))
                {
                    g.setColor(new Color(0x0091FF)); //blue
                    g.fillRect(tempX, tempY, tempWidth, tempHeight);
                }
                //laser Obstacles
                if(tempObs.getType().equals("typePreLaser"))
                {
                    g.setColor(Color.GRAY);
                    g.fillRect(tempX, tempY, tempWidth, tempHeight);
                }
                if(tempObs.getType().equals("typeLaser"))
                {
                    g.setColor(Color.RED);
                    g.fillRect(tempX, tempY, tempWidth, tempHeight);
                    // try {
                    // Thread.sleep(3000);
                    // } catch (InterruptedException e) {

                    // }
                }
            }
        }
    }

    /**
     * when mouse is clicked on a component(pressed & released immediately)
     */
    // public void mouseInput()
    // {
    // addMouseListener(new MouseAdapter() {
    // @Override
    public void mouseClicked(MouseEvent e)
    {
        //System.out.println("click");     //testing
        lastClick_xCoord = e.getX();
        lastClick_yCoord = e.getY();
        //System.out.println(lastClick_xCoord+" , "+lastClick_yCoord);     //testing
        if(gameOver == true)
        {
            //button for level 1
            //g.drawRect(175, 225, 200, 100); 
            if(lastClick_xCoord > 175 && lastClick_xCoord < 375 &&
            lastClick_yCoord > 225 && lastClick_yCoord < 375)
            {
                System.out.println("---------------");     //testing
                System.out.println("Level 1");     //testing
                gameMode = "Level 1";
                gameOver = false;
                //System.out.println(time); //testing
                //System.out.println(delayVariable);   //testing
                repaint();
            }
        }
        repaint();
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
        if(gameMode.equals("Level 1"))
        {
            // for(int i=0; i<obsLabels.size(); i++)
            // {
            // obsLabels.get(i);
            // }
        }
    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    // public void keyboardInput()
    // {
    // setFocusable(true);
    // addKeyListener(new KeyAdapter()
    // {

    // public void keyClicked(KeyEvent e)
    // {
    // if(e.getKeyCode() == KeyEvent.VK_SPACE)
    // {
    // System.out.println("space clicked");
    // }
    // }
    // });
    // }

    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            System.out.println("space clicked");     //testing
            gameOver = true;
        }
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

    // public void makeObs_randLoc()
    // {
    // int randX = (int)(Math.random()*1001.0);
    // int randY = (int)(Math.random()*701.0);
    // Obstacle newObs = new Obstacle();
    // newObs.setPos(randX, randY);
    // newObs.setWidth(100);
    // newObs.setHeight(100);
    // obstacles.add(newObs);
    // System.out.println("new Obstacle");    //testing
    // System.out.println(mouseLoc);     //testing
    // }

    public void makeObs_typeHoriz()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = -randWidth;
        int randY = (int)(Math.random()*(701.0-randHeight));
        // HorizontalBullet newObs = new HorizontalBullet(randX, randY, randWidth, randHeight);
        // obstacles.add(newObs);
        // System.out.println(obstacles.get(obstacles.size()-1)); //testing
        // System.out.println("new Horizontal Bullet"); //testing
        Obstacle newObs = new Obstacle();
        newObs.setWidth(randWidth);
        newObs.setHeight(randHeight);
        newObs.setPos(randX, randY);
        newObs.setType("typeHoriz");
        obstacles.add(newObs);
        //System.out.println("test");
    }

    public void makeObs_typeVert()
    {
        int randWidth = (int)(10+Math.random()*11.0);
        int randHeight = (int)(10+Math.random()*11.0);
        int randX = (int)(Math.random()*(1001.0-randWidth));
        int randY =  -randHeight;
        Obstacle newObs = new Obstacle();
        newObs.setWidth(randWidth);
        newObs.setHeight(randHeight);
        newObs.setPos(randX, randY);
        newObs.setType("typeVert");
        obstacles.add(newObs);
    }

    public int makeObs_typePreLaser()
    {
        int randX = (int)(Math.random()*1001.0);
        int randY = (int)(Math.random()*701.0);
        Obstacle newObs = new Obstacle();
        int randHorizOrVert = (int)(Math.random()*2.0);
        if(randHorizOrVert % 2 == 0)
        {
            //horizontal laser
            newObs.setHeight(20);
            newObs.setWidth(1000);
            newObs.setPos(0, randY);
        }
        else
        {
            //vertical laser
            newObs.setWidth(20);
            newObs.setHeight(700);
            newObs.setPos(randX, 0);
        }
        newObs.setType("typePreLaser");
        // try {
        // Thread.sleep(3000);
        // } catch (InterruptedException e) {

        // }
        obstacles.add(newObs);
        return obstacles.size()-1;
    }

    public int makeObs_typeLaser(Obstacle preLaser)
    {
        Obstacle newObs = new Obstacle();
        newObs.setPos(preLaser.getPos().x, preLaser.getPos().y);
        newObs.setWidth(preLaser.getWidth());
        newObs.setHeight(preLaser.getHeight());
        newObs.setType("typeLaser");
        obstacles.add(newObs);
        return obstacles.size()-1;
    }

    
    //inherit from Runnable interface
    public void run()
    {
        time = 0;
        delayVariable = 4.0;
        int subTimeLaser = 0;
        int indexPreLaser = -1;
        int indexLaser = -1;
        int bulletSpawnRate = 50;
        

        while(true) //runs this constantly
        {
            //get the x & y coordinates of game window on computer screen
            frame_xCoord = myFrame.getLocation().x;
            frame_yCoord = myFrame.getLocation().y+25;

            int mouse_xCoord = MouseInfo.getPointerInfo().getLocation().x;
            int mouse_yCoord = MouseInfo.getPointerInfo().getLocation().y;
            if(mouse_xCoord > frame_xCoord && mouse_xCoord < frame_xCoord+1000 &&
            mouse_yCoord > frame_yCoord && mouse_yCoord < frame_yCoord+700)
            {
                mouseLoc = MouseInfo.getPointerInfo().getLocation();
            }

            //reset game
            if(gameOver)
            {
                gameMode = "";
                if(time/200.0 > highScore1)
                {
                    highScore1 = time/200.0; //kinda wack score system but thats ok
                }
                time = 0;
                obstacles.clear();
                lives = 3;
                delayVariable = 4.0;
                bulletSpawnRate = 50;
            }
            if(!gameOver)
            {
                if(time > 0 && time% 4000 == 0 )
                {
                    delayVariable -= 0.2;
                    bulletSpawnRate -= 5;
                    //System.out.println(delayVariable); //testing
                    System.out.println("sped");
                }
                if(gameMode.equals("Level 1"))
                {
                    //run1();
                    if(lives<=0)
                    {
                        gameOver = true;
                    }
                    if(time % bulletSpawnRate == 0)
                    {
                        //System.out.println("worked");     //testing

                        //testing random obstacles
                        //makeObs_randLoc();
                        // Obstacle newObs = new Obstacle();
                        // newObs.setPos(50,50);
                        // newObs.setWidth(100);
                        // newObs.setHeight(100);
                        // obstacles.add(newObs);
                        // System.out.println("new Obstacle");    //testing
                        // System.out.println(mouseLoc);     //testing

                        // JLabel tempLabel = newObs.createLabel(newObs.getPos(), 
                        // newObs.getWidth(), 
                        // newObs.getHeight());
                        // tempLabel.addMouseListener(this);
                        // this.add(tempLabel);
                        // this.setVisible(true);
                        // obsLabels.add(tempLabel);

                        //myFrame.addMouseListener(this);

                        makeObs_typeHoriz();
                        //System.out.println("new Horizontal Obstacle");     //testing

                        makeObs_typeVert();
                        //System.out.println("new Vertical Obstacle");     //testing
                        repaint();
                    }
                    if(time % 400 == 0 && time/200.0 > 50)
                    {
                        for(int i=0; i<obstacles.size(); i++)
                        {
                            if(obstacles.get(i).getType().equals("typePreLaser") ||
                            obstacles.get(i).getType().equals("typeLaser"))
                            {
                                obstacles.remove(i);
                                i--;
                            }
                        }
                        indexPreLaser = makeObs_typePreLaser();
                        subTimeLaser = 0;
                        repaint();
                    }
                    if(subTimeLaser == 200 && time/200.0 > 50)
                    {
                        for(int i=0; i<obstacles.size(); i++)
                        {
                            if(obstacles.get(i).getType().equals("typePreLaser"))
                            {
                                indexLaser = makeObs_typeLaser(obstacles.get(i));
                                //System.out.println("new Laser Obstacle");   //testing
                                //indexPreLaser = i;
                            }
                        }
                        // makeObs_typeLaser(obstacles.get(indexPreLaser));
                        // System.out.println("new Laser Obstacle");
                        //obstacles.remove(indexPreLaser);
                        repaint();
                    }
                    // if(subTimeLaser == 600)
                    // {
                    // obstacles.remove(indexPreLaser);
                    // obstacles.remove(indexLaser);
                    // }
                    for(int i=0; i<obstacles.size(); i++)
                    {

                        //System.out.println(obstacles.size());     //testing
                        Obstacle tempObs = obstacles.get(i);
                        if(mouseLoc.x > tempObs.getPos().x+frame_xCoord && 
                        mouseLoc.x < tempObs.getPos().x+frame_xCoord + tempObs.getWidth() &&
                        mouseLoc.y > tempObs.getPos().y+frame_yCoord &&
                        mouseLoc.y < tempObs.getPos().y+frame_yCoord + tempObs.getHeight())
                        {
                            if(tempObs.getType().equals("typePreLaser")) // To Do
                            {
                                subTimeLaser = 0;
                                //int index = i;
                                // if(subTimeLaser == 500)
                                // {
                                // makeObs_typeLaser(obstacles.get(indexPreLaser));
                                // System.out.println("new Laser Obstacle");
                                // //i = index;
                                // repaint();
                                // }
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

                         if(tempObs.getType().equals("typeHoriz")){
                        //if(tempObs instanceof HorizontalBullet == true){
                             tempObs.setPos(tempObs.getPos().x+1, tempObs.getPos().y);
                            //System.out.println("move");//testing
                            //tempObs.move(); //doesnt work bruh
                        }

                        if(tempObs.getType().equals("typeVert")){
                            tempObs.setPos(tempObs.getPos().x, tempObs.getPos().y+1);
                        }

                        if(tempObs.getPos().x > 1000 || tempObs.getPos().y > 700)
                        {
                            obstacles.remove(i);
                            i--;
                            //System.out.println("obstacle removed");     //testing
                        }
                    }
                }
                time++;
                subTimeLaser++;
                //System.out.println(subTimeLaser);     //testing
                //System.out.println(obstacles.size());     //testing
            }
            //System.out.println(mouseLoc);     //testing
            //why does this print null every other time?
            repaint();
            //delay to loop every 5 milliseconds; can make this a variable
            //delayVariable
            try {
                Thread.sleep((long)(delayVariable));
            } catch (InterruptedException e) {

            }
        }
    }

    // public void run1()
    // {
    // repaint();
    // }

    // //button: g.drawRect(175, 175, 200, 100);
    // public void play1()
    // {
    // if(gameOver == true &&
    // lastClick_xCoord > 175 && lastClick_xCoord < 375 &&
    // lastClick_yCoord > 175 && lastClick_xCoord < 275)
    // {
    // System.out.println("yeet");
    // gameMode = "Survival 1";
    // gameOver = false;
    // run1();
    // }
    // }

    public static void main(String[] args)
    {
        //trying with constructor

        // new JSAB_versionJPanel();
        // paintComponent method?
        // myFrame.setVisible(true);

        myFrame = new JFrame("Just Shapes & Beats (Walmart Edition)");

        JSAB_MainSolo myPanel = new JSAB_MainSolo();
        //System.out.println(gameOver);     //testing
        myPanel.setPreferredSize(new Dimension(1000,700));

        myFrame.add(myPanel);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);

        //myPanel.keyboardInput();
        //myPanel.mouseClicked( );
        new Thread(myPanel).start();
    }
}
