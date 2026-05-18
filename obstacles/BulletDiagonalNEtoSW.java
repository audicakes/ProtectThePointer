package obstacles;

/**
 * Write a description of class BulletMovingSW here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BulletDiagonalNEtoSW extends SuperObstacle
{
    // instance variables - replace the example below with your own
    private int x;
    private boolean startNE;

    /**
     * Constructor for objects of class BulletMovingSW
     */
    public BulletDiagonalNEtoSW(int xPos, int yPos, int w, int h, boolean b)
    {
        super(xPos, yPos, w, h);
        startNE = b;
    }

    public void move()
    {
        if(startNE)
        {
            super.setPos(pos.x-1, pos.y+1);
        }
        else
        {
            super.setPos(pos.x+1, pos.y-1);
        }
    }
}
