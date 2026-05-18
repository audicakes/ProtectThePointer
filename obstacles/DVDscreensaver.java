package obstacles;
import java.awt.*;
/**
 * Write a description of class DVDscreensaver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DVDscreensaver extends SuperObstacle
{
    // instance variables - replace the example below with your own
    private boolean right;
    private boolean down;
    private int width;
    private int height;
    private Color DVDcolor;
    private int red;
    private int green;
    private int blue;
    private int alpha;

    /**
     * Constructor for objects of class DVDscreensaver
     */
    public DVDscreensaver(int xPos, int yPos, int w, int h, boolean r, boolean d)
    {
        super(xPos, yPos, w, h);
        right = r;
        down = d;
        width = w;
        height = h;
        red = (int)(Math.random()*256);
        green = (int)(Math.random()*256);
        blue = (int)(Math.random()*256);
        alpha = (int)(Math.random()*256);
        DVDcolor = new Color(red, green, blue, alpha);
    }

    public Color getColor()
    {
        return DVDcolor;
    }
    
    public void setRandomColor()
    {
        red = (int)(Math.random()*256);
        green = (int)(Math.random()*256);
        blue = (int)(Math.random()*256);
        alpha = (int)(Math.random()*256);
        DVDcolor = new Color(red, green, blue, alpha);
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void move(int windowWidth, int windowHeight)
    {
        if(right)
        {
            super.setPos(pos.x+1, pos.y);
        }
        if(!right) 
        {
            super.setPos(pos.x-1, pos.y);
        }
        if(down)
        {
            super.setPos(pos.x, pos.y+1);
        }
        if(!down)
        {
            super.setPos(pos.x, pos.y-1);
        }

        if(this.getPos().x+width >= windowWidth)
        {
            right = false;
            setRandomColor();
            //System.out.println("boing"); //testing
        }
        if(this.getPos().x <= 0)
        {
            right = true;
            setRandomColor();
            //System.out.println("boing"); //testing
        }
        if(this.getPos().y+height >= windowHeight)
        {
            down = false;
            setRandomColor();
            //System.out.println("boing"); //testing
        }
        if(this.getPos().y <= 0)
        {
            down = true;
            setRandomColor();
            //System.out.println("boing"); //testing
        }
    }
}
