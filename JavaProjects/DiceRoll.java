package portfolio;

import javax.swing.JFrame;

/**
 * DiceRoll.java
 * 
 * The program displays a dice GUI.
 * 
 * @author Elizabeth Lee
 * @version 1.0
 *
 */
public class DiceRoll {
    
    /**
     * Main method. Calls the frame of the program.
     * 
     * @param args Command line arguments; unused.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.getContentPane().add(new RollingPanel());
        frame.pack();
        frame.setVisible(true);
    }

}
