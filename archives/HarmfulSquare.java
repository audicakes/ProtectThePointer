
/**
 * Write a description of class Obstacle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
public class HarmfulSquare extends JFrame
{
    // instance variables - replace the example below with your own
    JLabel label;

    /**
     * Constructor for objects of class Obstacle
     */
    public HarmfulSquare()
    {
        // initialise instance variables
         label = new JLabel();
    }

    public JLabel createSquareObstacle()
    {
        //JLabel label = new JLabel();
        label.setBounds(0, 0, 100, 100);
        label.setBackground(Color.red);
        label.setOpaque(true);
        return label;
    }
}
