package portfolio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * <p>Extends JPanel to create a six-sided die face.</p>
 * 
 * @author Elizabeth Lee
 * @version 1.0
 *
 */
public class DicePanel extends JPanel {
        
        /** <p>Unique serial number for this version.</p> */
        private static final long serialVersionUID = 1907441208838015460L;
        
        /** <p>Size of the dots</p> */
        private static final int DOTSIZE = 25;
        
        /** <p>Top position for dots</p> */
        private static final int TOP = 9;
        
        /** <p>Middle position for dots</p> */
        private static final int MID = 37;
        
        /** <p>Bottom position for dots</p> */
        private static final int BOT = 65;
        
        /** <p>Current face value of the die.</p> */
        private int value;
        
        /**
         * <p>Constructor: sets up the GUI.</p>
         */
        public DicePanel(final int v) {
            value = v;
            setBackground(Color.white);
            setPreferredSize(new Dimension(100, 100));
            setBorder(BorderFactory.createLineBorder(Color.black, 2));
        }
        
        /**
         * <p>Updates the panel with the die's new value.</p>
         * 
         * @param v The value to set the die to
         */
        public void updatePanel(final int v) {
            value = v;
            
            repaint();
        }
        
        /**
         * <p>Overrides the paintComponent method to draw the die's
         * current face value.</p>
         */
        public void paintComponent(final Graphics page) {
            super.paintComponent(page);
            
            page.setColor(Color.black);
            
            switch(value) {
            case 6:
                page.fillOval(TOP, MID, DOTSIZE, DOTSIZE); // middle left
                page.fillOval(BOT, MID, DOTSIZE, DOTSIZE); // middle right
            case 4:
                page.fillOval(TOP, BOT, DOTSIZE, DOTSIZE); // bottom left
                page.fillOval(BOT, TOP, DOTSIZE, DOTSIZE); // top right
            case 2:
                page.fillOval(TOP, TOP, DOTSIZE, DOTSIZE); // top left
                page.fillOval(BOT, BOT, DOTSIZE, DOTSIZE); // bottom right
                break;
            case 5:
                page.fillOval(TOP, BOT, DOTSIZE, DOTSIZE); // bottom left
                page.fillOval(BOT, TOP, DOTSIZE, DOTSIZE); // top right
            case 3:
                page.fillOval(TOP, TOP, DOTSIZE, DOTSIZE); // top left
                page.fillOval(BOT, BOT, DOTSIZE, DOTSIZE); // bottom right
            case 1:
                page.fillOval(MID, MID, DOTSIZE, DOTSIZE); // middle
                break;
            default:
                throw new IllegalArgumentException(value + " is not a valid "
                        + "value for this die");
            }
        }
        
    }
