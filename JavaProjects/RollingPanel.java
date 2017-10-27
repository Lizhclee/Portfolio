package portfolio;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * <p>Extends JPanel to create a playable GUI with two dice and a roll button.</p>
 * 
 * @author Elizabeth Lee
 * @version 1.0
 *
 */
public class RollingPanel extends JPanel {

    /** <p>Unique serial number for this version.</p> */
    private static final long serialVersionUID = 2238686068209322095L;
    
    /** <p>One of the dice objects.</p> */
    private final Dice die1;
    
    /** <p>Another of the dice objects.</p> */
    private final Dice die2;
    
    /** <p>A button to allow the user to roll the dice.</p> */
    private final JButton button;

    /** <p>A timer to update the die values multiple times, 
     * creating the shuffle animation.</p> */
    private Timer timer;
    
    /** <p>A counter of how many "shuffles" have happened so far.</p> */
    private int count;
    
    /** <p>The background color of the GUI.</p> */
    private Color color;
    
    /**
     * <p>Constructor: sets up the panel with two dice and a button.</p>
     */
    public RollingPanel() {
        final JPanel panel; // panel to keep the dice together
        final int delay;    // delay in milliseconds of the timer
        
        die1     = new Dice();
        die2     = new Dice();
        button   = new JButton("Roll!");
        count    = 0;
        panel    = new JPanel();
        delay    = 100;
        timer    = new Timer(delay, new Roller());
        color    = Color.cyan;
        
        button.addActionListener(new ButtonListener());
        
        panel.add(die1.getDicePanel());
        panel.add(die2.getDicePanel());
        panel.setBackground(color);
        
        add(panel);
        add(button);
        
        setBackground(color);
        roll();
    }
    
    /**
     * <p>Rolls a new value for each die.</p>
     */
    private void roll() {
        die1.roll();
        die2.roll();
    }
    
    /**
     * <p>Updates the dice faces in a shuffle animation.</p>
     */
    private void shuffle() {
        die1.generateValue();
        die2.generateValue();
    }
    
    /**
     * <p>Listener class: implements the ActionListener interface to roll
     * the dice.</p>
     * 
     * @author Elizabeth Lee
     * @version 1.0
     *
     */
    private class Roller implements ActionListener {
        
        /**
         * <p>Implements the actionPerformed method of the ActionListener
         * interface.</p>
         */
        public void actionPerformed(ActionEvent e) {
            if (count == 5) {
                timer.stop();
                count = 0;
                roll();
            } else {
                count++;
                shuffle();
            }
        }
    }
    
    /**
     * <p>Listener class: implements the ActionListener interface to respond
     * to the "Roll" button.</p>
     * 
     * @author Elizabeth Lee
     * @version 1.0
     *
     */
    private class ButtonListener implements ActionListener {
        
        /**
         * <p>Implements the actionPerformed method of the ActionListener
         * interface.</p>
         */
        public void actionPerformed(ActionEvent e) {
            timer.start();
            shuffle();
        }
    }
}
