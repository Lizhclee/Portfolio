package portfolio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * <p>Extends JPanel to create a playable GUI with two dice and a roll button.</p>
 * 
 * @author Elizabeth Lee
 * @version 1.0
 *
 */
public class PigPanel extends JPanel {

    /** <p>Unique serial number for this version.</p> */
    private static final long serialVersionUID = 2238686068209322095L;
    
    /** <p>Game of Pig associated with this panel.</p> */
    private final Pig game;
    
    /** <p>The Player object associated with the user.</p> */
    private final Player player1;
    
    /** <p>The Player object associated with the computer.</p> */
    private final Player comp;
    
    /** <p>The background colour of the panel.</p> */
    private final Color bgColor;

    /** <p>The panel containing the buttons.</p> */
    private JPanel buttonPanel;
    
    /** <p>The ROLL button.</p> */
    private JButton roll;
    
    /** <p>The BANK button.</p> */
    private JButton bank;
    
    /** <p>The JLabel indicating whose turn it currently is.</p> */
    private JLabel turnMarker;
    
    /** <p>The JLabel displaying the computer's total points.</p> */
    private JLabel opponentTotal;
    
    /** <p>The JLabel displaying the computer's round points.</p> */
    private JLabel opponentRound;
    
    /** <p>The JLabel displaying the user's total points.</p> */
    private JLabel playerTotal;
    
    /** <p>The JLabel displaying the user's round points.</p> */
    private JLabel playerRound;
    
    /** <p>The timer for executing the shuffle animation.</p> */
    private Timer shuffle;
    
    /** <p>The timer for executing the computer's turn.</p> */
    private Timer compTurn;
    
    /**
     * <p>Constructor: sets up the panel.</p>
     */
    public PigPanel(final Pig game) {
        this.game  = game;
        player1    = game.getP1();
        comp       = game.getP2();
        bgColor    = Color.cyan;
        
        init();
    }
    
    /**
     * <p>Initializes the display of the panel.</p>
     */
    private void init() {
        JPanel opponentSide;    // the opponent's side of the board
        JPanel action;          // the dice and button area
        JPanel playerSide;      // the player's side of the board
        JPanel turnLabel;       // the panel containing the turn marker
        JPanel midSection;      // keeps the dice and buttons together
        JLabel opponentHeader;  // label for the opponent's side
        JLabel playerHeader;    // label for the player's side
        
        opponentSide = new JPanel();
        action       = new JPanel();
        buttonPanel  = new JPanel();
        playerSide   = new JPanel();
        turnLabel    = new JPanel();
        midSection   = new JPanel();

        turnMarker     = new JLabel(player1 + "'s turn");
        opponentTotal  = new JLabel("Total points: 0");
        opponentRound  = new JLabel("Round points: 0");
        playerTotal    = new JLabel("Total points: 0");
        playerRound    = new JLabel("Round points: 0");

        roll = new JButton("Roll");
        bank = new JButton("Bank");
        
        roll.addActionListener(new RollListener());
        bank.addActionListener(new BankListener());
        
        shuffle = new Timer(100, new RollAnimation());
        compTurn = new Timer(2000, new OpponentListener());
        
        buttonPanel.setLayout(new GridLayout(3, 1));
        buttonPanel.setPreferredSize(new Dimension(120, 100));
        turnLabel.add(turnMarker);
        buttonPanel.add(turnLabel);
        buttonPanel.add(roll);
        buttonPanel.add(bank);
        
        opponentHeader = new JLabel(comp.toString());
        playerHeader   = new JLabel(player1.toString());

        opponentHeader.setFont(new Font("Arial", Font.BOLD, 30));
        playerHeader.setFont(new Font("Arial", Font.BOLD, 30));
        opponentTotal.setFont(new Font("Arial", Font.PLAIN, 24));
        opponentRound.setFont(new Font("Arial", Font.PLAIN, 24));
        playerTotal.setFont(new Font("Arial", Font.PLAIN, 24));
        playerRound.setFont(new Font("Arial", Font.PLAIN, 24));

        opponentSide.setLayout(new BoxLayout(opponentSide, BoxLayout.Y_AXIS));
        opponentSide.add(Box.createVerticalGlue());
        opponentSide.add(opponentHeader);
        opponentSide.add(opponentTotal);
        opponentSide.add(opponentRound);
        opponentSide.add(Box.createVerticalGlue());

        playerSide.setLayout(new BoxLayout(playerSide, BoxLayout.Y_AXIS));
        playerSide.add(Box.createVerticalGlue());
        playerSide.add(playerHeader);
        playerSide.add(playerTotal);
        playerSide.add(playerRound);
        playerSide.add(Box.createVerticalGlue());
        
        midSection.add(game.getDicePanel());
        midSection.add(buttonPanel);
        action.setLayout(new BoxLayout(action, BoxLayout.Y_AXIS));
        action.add(Box.createVerticalGlue());
        action.add(midSection);
        action.add(Box.createVerticalGlue());
        action.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.black));

        opponentSide.setBackground(bgColor);
        action.setBackground(bgColor);
        game.getDicePanel().setBackground(bgColor);
        buttonPanel.setBackground(bgColor);
        turnLabel.setBackground(bgColor);
        midSection.setBackground(bgColor);
        playerSide.setBackground(bgColor);
        
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(opponentSide);
        add(action);
        add(playerSide);
    }
    
    /**
     * <p>Updates the panel with the points scored in the last roll.</p>
     * 
     * @param total The JLabel for the current player's total points
     * @param round The JLabel for the current player's round points
     * @param p The current player
     * @param oppTotal The JLabel for the other player's total points
     * @param oppRound The JLabel for the other player's round points
     * @param opp The other player
     */
    private void update(final JLabel total, final JLabel round, final Player p,
                        final JLabel oppTotal, final JLabel oppRound, final Player opp) {
        final int totalPoints; // total points before rolling
        final int roundPoints; // round points before rolling
        final int rollResult;  // the result of rolling
        
        totalPoints = p.getTotalPoints();
        roundPoints = p.getRoundPoints();
        rollResult = game.scorePoints(p);
        
        game.updatePoints(p);
        
        oppTotal.setText("Total points: " + opp.getTotalPoints());
        oppRound.setText("Round points: " + opp.getRoundPoints());
        
        if (rollResult > 0) {
            total.setText("Total points: " + p.getTotalPoints());
            round.setText("Round points: " + p.getRoundPoints() 
                    + " +" + rollResult);
            
            // check if someone won
            if (game.checkWinner() != null) {
                gameOver();
            }
            
            // if opponent's turn, check if points >=20 and bank
            if (!game.getPlayerTurn() && p.getRoundPoints() >= 20) {
                bank(opponentTotal, opponentRound, p);
                switchPlayer();
            }
        } else if (rollResult == 0) {
            total.setText("Total points: " + p.getTotalPoints());
            round.setText("Round points: " + p.getRoundPoints() 
                    + " -" + roundPoints);
            
            switchPlayer();
        } else if (rollResult == -1) {
            total.setText("Total points: " + p.getTotalPoints() 
                    + " -" + totalPoints);
            round.setText("Round points: " + p.getRoundPoints() 
                    + " -" + roundPoints);

            switchPlayer();
        }
        
    }
    
    /**
     * <p>Banks a player's points.</p>
     * 
     * @param total The JLabel for a player's total points
     * @param round The JLabel for a player's round points
     * @param p The player to update
     */
    private void bank(final JLabel total, final JLabel round, final Player p) {
        final int roundPoints; // the points from this round
        
        roundPoints = p.getRoundPoints();
        
        game.bankPoints(p);
        
        total.setText("Total points: " + p.getTotalPoints() + " +" + roundPoints);
        round.setText("Round points: " + p.getRoundPoints());
    }
    
    /**
     * <p>Updates the display to reflect passing the turn to the 
     * other player.</p>
     */
    private void switchPlayer() {
        game.switchPlayer();
        
        toggleButtons();
        
        if (game.getPlayerTurn()) {
            compTurn.stop();
        } else {
            compTurn.start();
        }
    }
    
    /**
     * <p>Toggles the buttons between enabled and disabled.</p>
     */
    private void toggleButtons() {
        final boolean isPlayerTurn; // whether it's currently the player's turn
        final String player;        // String representing the current player
        
        isPlayerTurn = game.getPlayerTurn();
        player = (isPlayerTurn) ? player1.toString(): comp.toString();
        
        turnMarker.setText(player + "'s turn");
        roll.setEnabled(isPlayerTurn);
        bank.setEnabled(isPlayerTurn);
    }
    
    /**
     * <p>Disables the buttons.</p>
     */
    private void disableButtons() {
        roll.setEnabled(false);
        bank.setEnabled(false);
    }
    
    /**
     * <p>Updates the display to the game over condition.</p>
     */
    private void gameOver() {
        final String p; // String representing which player won
        
        compTurn.stop();
        p = game.checkWinner().toString().toUpperCase();
        turnMarker.setText(p + " WINS!!");
        disableButtons();
        
        bank(playerTotal, playerRound, player1);
        bank(opponentTotal, opponentRound, comp);
    }
    
    /**
     * <p>Listener class: implements the ActionListener interface to 
     * begin the dice-rolling animation.</p>
     * 
     * @author Elizabeth Lee
     * @version 1.0
     *
     */
    private class RollListener implements ActionListener {
        
        /**
         * <p>Implements the actionPerformed method of the ActionListener
         * interface to begin the dice-rolling animation.</p>
         */
        public void actionPerformed(ActionEvent e) {
            shuffle.start();
            game.shuffle();
        }
    }
    
    /**
     * <p>Listener class: implements the ActionListener interface to
     * create a dice-rolling animation.</p>
     * 
     * @author Elizabeth Lee
     * @version 1.0
     *
     */
    private class RollAnimation implements ActionListener {
        
        /** <p>Tracks how many times the dice values have been shuffled so far.</p> */
        private int count;
        
        /**
         * <p>Constructor: initializes count to 0.</p>
         */
        public RollAnimation() {
            count = 0;
        }
        
        /**
         * <p>Implements the actionPerformed method of the ActionListener
         * interface to play the shuffle animation and update the display
         * when the shuffle animation is finished.</p>
         */
        public void actionPerformed(ActionEvent e) {
            if (count == 5) {
                shuffle.stop();
                count = 0;
                game.roll();
                
                if (game.getPlayerTurn()) {
                    update(playerTotal, playerRound, player1, 
                            opponentTotal, opponentRound, comp);
                } else {
                    update(opponentTotal, opponentRound, comp,
                            playerTotal, playerRound, player1);
                }
                
            } else {
                game.shuffle();
                count++;
            }
        }
    }
    
    /**
     * <p>Listener class: implements the ActionListener interface
     * to bank the player's points when the BANK button is clicked.</p>
     * 
     * @author Elizabeth Lee
     * @version 1.0
     *
     */
    private class BankListener implements ActionListener {
        
        /**
         * <p>Implements the actionPerformed method of the ActionListener
         * interface to bank the player's points.</p>
         */
        public void actionPerformed(ActionEvent e) {
            bank(playerTotal, playerRound, player1);
            switchPlayer();
        }
    }
    
    /**
     * <p>Listener class: implements the ActionListener interface
     * to execute the computer's turn.</p>
     * 
     * @author Elizabeth Lee
     * @version 1.0
     *
     */
    private class OpponentListener implements ActionListener {
        
        /**
         * <p>Implements the actionPerformed method of the ActionListener 
         * interface to begin the dice-rolling animation.</p>
         */
        public void actionPerformed(ActionEvent e) {
            shuffle.start();
            game.shuffle();
        }
    }
}
