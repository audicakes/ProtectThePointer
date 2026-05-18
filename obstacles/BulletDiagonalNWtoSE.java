package obstacles;

/**
 * Write a description of class BulletMovingSE here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BulletDiagonalNWtoSE extends SuperObstacle
{
    // instance variables - replace the example below with your own
    private boolean startNW;

    /**
     * Constructor for objects of class BulletMovingSE
     */
    public BulletDiagonalNWtoSE(int xPos, int yPos, int w, int h, boolean b)
    {
        super(xPos, yPos, w, h);
        startNW = b;
    }

    public void move()
    {
        if(startNW)
        {
            super.setPos(pos.x+1, pos.y+1);
        }
        else
        {
            super.setPos(pos.x-1, pos.y-1);
        }
    }
}
