/**
 * Write a description of class JustShapesAndBeats here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JSAB_centeredFrameTest extends JPanel implements MouseListener
{
    // instance variables - replace the example below with your own
    private boolean gameOver;
    private int rows, cols;
    //private MyBoundedGrid<Block> conceptualGrid;
    //private BlockDisplay visualScreen;

    /**
     * Constructor for objects of class JustShapesAndBeats
     */
    public JSAB_centeredFrameTest()
    {
        // initialise instance variables

    }

    public void run(){
        /*
        while(true)
        {
        repaint();
        }
        try
        {
        Thread.sleep(10);
        }
        catch(InterruptedException e)
        {
        //nothing
        }
         */
    }

    /**
     * when mouse is clicked (pressed & released immediately)
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    /**
     * when mouse is pressed (holding down)
     */
    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    /**
     * when mouse is released (let go)
     */
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    
    
    public static void main(String[] args)
    {
        JFrame window = new JFrame("Just Shapes & Beats");
        JSAB_centeredFrameTest gameDisplay = new JSAB_centeredFrameTest();
        gameDisplay.setPreferredSize(new Dimension(1000, 700));
        //myPanel.setPreferredSize(new Dimension(500,500));
        //myPanel.setBackground(Color.BLUE); if this.setBackground above is not written
        window.add(gameDisplay);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        //gameDisplay.keyboardInput();
        //new Thread(window).start();
    }
}
