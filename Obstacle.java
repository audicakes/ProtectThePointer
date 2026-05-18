import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Write a description of class Obstacle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Obstacle //to do: make abstract
{
    // instance variables - replace the example below with your own
    private Point pos = new Point();
    private int width;
    private int height;
    private int speed;
    private String type;
    private JLabel label;

    /**
     * Constructor for objects of class Obstacle
     */
    public Obstacle()
    {
        // initialise instance variables
        pos = new Point(0,0);
        width = 0;
        height = 0;
        speed = 0;
        JLabel label = new JLabel(); //?
    }
    
    // public Obstacle(int xPos, int yPos, int w, int h)
    // {
        // // initialise instance variables
        // pos = new Point(xPos,yPos);
        // width = w;
        // height = h;
        // JLabel label = new JLabel(); //?
    // }

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
    
    public int getSpeed()
    {
        return speed;
    }
    
    public void setSpeed(int s)
    {
        speed = s;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String s)
    {
        type = s;
    }
    // public JLabel createLabel(Point pos, int w, int h)
    // {
        // label.setBounds(pos.x, pos.y, w, h);
        // return label;
    // }
    
     public void move()
     {
         // this.setPos(this.getPos().x+1, this.getPos().y);
     }
}
