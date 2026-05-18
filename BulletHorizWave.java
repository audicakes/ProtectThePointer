
/**
 * Write a description of class BulletHorizWave here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BulletHorizWave extends SuperObstacle
{
    // instance variables - replace the example below with your own
    private boolean right;

    /**
     * Constructor for objects of class HorizontalBullet
     */

    public BulletHorizWave(int xPos, int yPos, int w, int h, boolean b)
    {
        super(xPos, yPos, w, h);
        right = b;
    }

    public void move(int yVector)
    {
        if(right)
        {
            super.setPos(pos.x+1, pos.y-yVector);
        }
        else
        {
            super.setPos(pos.x-1, pos.y+yVector);
        }
    }
}
