
/**
 * Write a description of class BulletVertWave here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BulletVertWave extends SuperObstacle
{
    // instance variables - replace the example below with your own
    private boolean down;

    /**
     * Constructor for objects of class HorizontalBullet
     */

    public BulletVertWave(int xPos, int yPos, int w, int h, boolean b)
    {
        super(xPos, yPos, w, h);
        down = b;
    }

    public void move(int xVector)
    {
        if(down)
        {
            super.setPos(pos.x+xVector, pos.y+1);
        }
        else
        {
            super.setPos(pos.x-xVector, pos.y-1);
        }
    }
}
