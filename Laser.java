
/**
 * Write a description of class Laser here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Laser extends SuperObstacle
{
    // instance variables - replace the example below with your own
    int laserTimer;
    Boolean harmful;
    
    /**
     * Constructor for objects of class HorizontalBullet
     */

    public Laser(int xPos, int yPos, int w, int h)
    {
        super(xPos, yPos, w, h);
        laserTimer = 0;
        harmful = false;
    }
    
    public void runLaserTimer()
    {
        laserTimer++;
    }
    
    public int getLaserTimer()
    {
        return laserTimer; 
    }
    
    public boolean getHarmful()
    {
        return harmful;
    }
    
    public void setHarmful()
    {
        harmful = true;
    }
}
