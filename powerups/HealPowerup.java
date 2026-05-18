package powerups;
import obstacles.SuperObstacle;

/**
 * Write a description of class HealPowerup here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HealPowerup extends SuperObstacle
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class HealPowerup
     */
   public HealPowerup(int xPos, int yPos, int w, int h)
    {
        super(xPos, yPos, w, h);
    }
}
