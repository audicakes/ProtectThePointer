import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Write a description of class HorizontalBullet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BulletHorizontal extends SuperObstacle
{
    // instance variables - replace the example below with your own
    private boolean right;

    /**
     * Constructor for objects of class HorizontalBullet
     */

    public BulletHorizontal(int xPos, int yPos, int w, int h, boolean b)
    {
        super(xPos, yPos, w, h);
        right = b;
    }

    public void move()
    {
        if(right)
        {
            super.setPos(pos.x+1, pos.y);
        }
        else
        {
            super.setPos(pos.x-1, pos.y);
        }
    }
}
