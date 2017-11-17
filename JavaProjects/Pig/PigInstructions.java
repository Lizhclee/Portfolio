package portfolio;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <p>A JPanel containing the instructions for a game of Pig.</p>
 * 
 * @author Elizabeth Lee
 * @version 1.0
 *
 */
public class PigInstructions extends JPanel {

    /** <p>Unique serial number for this version</p> */
    private static final long serialVersionUID = 2538603281035850737L;
    
    /**
     * <p>Constructor: creates the GUI containing the game's
     * instructions.</p>
     * 
     * @param game The current game of Pig
     */
    public PigInstructions(final Pig game) {
        final String[] instructions;
        
        instructions = game.getInstructions();

        JLabel label = new JLabel(instructions[0]);
        add(label);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        
        for (int i = 1; i < instructions.length; i++) {
            label = new JLabel(instructions[i]);
            add(label);
            label.setFont(new Font("Arial", Font.PLAIN, 16));
        }
        
        setLayout(new GridLayout(instructions.length + 1, 1));
        setBackground(Color.cyan);
    }

}
