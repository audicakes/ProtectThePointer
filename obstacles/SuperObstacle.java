package obstacles;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Write a description of class SuperObstacle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class SuperObstacle //to do: make abstract
{
    // instance variables - replace the example below with your own
    public Point pos;
    public int width;
    public int height;
    public JLabel label;

    /**
     * Constructor for objects of class SuperObstacle
     */
   
    public SuperObstacle(int xPos, int yPos, int w, int h)
     {
        // // initialise instance variables
        pos = new Point(xPos,yPos);
        width = w;
        height = h;
        JLabel label = new JLabel(); //?
    }

    public Point getPos()
    {
        return pos;
    }
    
    public void setPos(int x, int y)
    {
        pos.x = x;
        pos.y = y;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public void setWidth(int w)
    {
        width = w;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public void setHeight(int h)
    {
        height = h;
    }
    
     public void move() //will be overridden
     {
         // this.setPos(this.getPos().x+1, this.getPos().y);
     }
     
     public void move(int i) //will be overridden
     {
         
     }
     
     public void move(int i, int j) //will be overridden
     {
         
     }
     
     public int getLaserTimer() //will be overridden
     {
         return -420;
     }
     public void runLaserTimer() //will be overridden
     {
         
     }
     public boolean getHarmful() //will be overridden
     {
         return true;
     }
     public void setHarmful() //will be overridden
     {
         
     }
     public Color getColor() //will be overridden
     {
         return Color.MAGENTA;
     }
}
