/**
 * Write a description of class Obstacle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class JSAB_crashTest extends JFrame implements MouseListener
{
    //static private BlockDisplay visualScreen;
    static private HarmfulSquare obs;
    static private JFrame frame;
    static private JLabel label;
    //JPanel panel;
    
    /**
     * Constructor for objects of class Obstacle
     */
    JSAB_crashTest()
    {
        frame = new JFrame("JSAB");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1400,800);
        this.setTitle("Just Shapes & Beats (Walmart Edition)");
           //to do: why doesn't the title show up?
        this.setLayout(null);
        
        
        
        /*
        label = new JLabel();
        label.setBounds(0, 0, 100, 100);
        label.setBackground(Color.red);
        label.setOpaque(true);
        label.addMouseListener(this);
        */
 
        obs = new HarmfulSquare();
        label = obs.createSquareObstacle();
        label.addMouseListener(this);

        this.add(label);
        this.setVisible(true);
    }
    
    /**
     * when mouse is clicked on a component(pressed & released immediately)
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("click.");
    }

    /**
     * when mouse is pressed on a component(holding down)
     */
    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    /**
     * when mouse is released from a component(let go)
     */
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    /**
     * when mouse enters a component
     */
    public void mouseEntered(MouseEvent e)
    {
        System.out.println("ouch");
    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    public void setTitle(String title)
    {
        frame.setTitle(title);
    }
    
    public static void main(String[] args)
    {
        JSAB_crashTest gameDisplay = new JSAB_crashTest();
        
        /*
        gameDisplay.setPreferredSize(new Dimension(1000, 700));
        //myPanel.setPreferredSize(new Dimension(500,500));
        //myPanel.setBackground(Color.BLUE); if this.setBackground above is not written
        //window.add(gameDisplay); to do
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //gameDisplay.keyboardInput();
        //new Thread(window).start();
        */
        
    }
}
