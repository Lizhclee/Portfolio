package portfolio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * <p>The program sets up the GUI for a game of Pig.</p>
 * 
 * @author Elizabeth Lee
 * @version 1.0
 *
 */
public class GameOfPig {
    
    /** <p>The JPanel containing the instructions.</p> */
    private static PigInstructions instructionPanel;
    
    /** <p>The JPanel containing the game.</p> */
    private static PigPanel gamePanel;
    
    /** <p>The tabbed pane containing the instruction and game panels.</p> */
    private static JTabbedPane tabs;
    
    /**
     * <p>Main method. Sets up and displays the JFrame.</p>
     * 
     * @param args Command line arguments; unused.
     */
    public static void main(String[] args) {
        JFrame frame;
        
        frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        
        frame.getContentPane().add(tabs);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * <p>Sets up the JTabbedPane and adds a clickable button that 
     * switches tabs.</p>
     */
    private static void initComponents() {
        Pig game;

        game = new Pig();
        
        instructionPanel = game.getInstructionPanel();
        gamePanel = game.getPigPanel();
        tabs = new JTabbedPane();
        
        tabs.addTab("Instructions", instructionPanel);
        tabs.addTab("Game", gamePanel);
        
        JButton play = new JButton("PLAY >");
        play.addActionListener(new PlayListener());
        instructionPanel.add(play);
    }
    
    /**
     * <p>Listener class: implements the ActionListener interface to
     * switch tabs.</p>
     * 
     * @author Elizabeth Lee
     * @version 1.0
     *
     */
    private static class PlayListener implements ActionListener {
        
        /**
         * <p>Implements the actionPerformed method of the ActionListener
         * interface to switch the active tab when the button is clicked.</p>
         */
        public void actionPerformed(ActionEvent e) {
            tabs.setSelectedComponent(gamePanel);
        }
    }

}
