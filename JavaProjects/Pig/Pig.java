package portfolio;

import javax.swing.JPanel;

/**
 * <p>Pig.java</p>
 * 
 * <p>Pig is a two-player dice game where players compete to be the
 * first to reach 100 points. On their turn, the player rolls the
 * dice; if neither die is 1, the player scores the total of the two
 * dice and can continue playing if they choose. If both dice are 1, 
 * the player loses all points scored in the game so far and play 
 * passes to their opponent. If only one die is 1, the player loses 
 * the points scored this round and play passes to the opponent.</p>
 * 
 * <p>This class initializes a player (the user) and an opponent 
 * (the computer) for a game of Pig.</p>
 * 
 * @author Elizabeth Lee
 * @version 2.0
 *
 */
public class Pig {

    /** <p>The amount of points needed to win.</p> */
    public static final int WIN = 100;
    
    /** <p>Player 1: the user.</p> */
    private final Player player;
    
    /** <p>Player 2: the computer.</p> */
    private final Player comp;
    
    /** <p>A dice object for playing.</p> */
    private final Dice die1;
    
    /** <p>A second dice object for playing.</p> */
    private final Dice die2;
    
    /** <p>Boolean value representing which player's turn it is.</p> */
    private boolean playerTurn;
    
    /** <p>The JPanel containing the dice.</p> */
    private final JPanel diceBlock;
    
    private final PigInstructions instructionPanel;
    
    /** <p>The JPanel containing the GUI.</p> */
    private final PigPanel pigPanel;
    
    /**
     * <p>Constructor: creates a game of Pig.</p>
     */
    public Pig() {
        player     = new Player("Player 1");
        comp       = new Player("Computer");
        die1       = new Dice();
        die2       = new Dice();
        playerTurn = true;
        diceBlock  = new JPanel();
        
        diceBlock.add(die1.getDicePanel());
        diceBlock.add(die2.getDicePanel());
        
        instructionPanel = new PigInstructions(this);
        pigPanel = new PigPanel(this);
    }
    
    /**
     * <p>Returns a string array containing the instructions for a
     * game of Pig</p>
     * 
     * @return A string array containing the instructions to play
     */
    public String[] getInstructions() {
        String[] instructions;
        
        instructions = new String[17];
        
        instructions[0]  = "Welcome to Pig!";
        instructions[1]  = " ";
        instructions[2]  = "Pig is a 2-player dice game. Play against the ";
        instructions[3]  = "computer and try not to get too greedy!";
        instructions[4]  = " ";
        instructions[5]  = "On your turn, roll the dice to score points. If ";
        instructions[6]  = "neither die has a face value of 1, you score the ";
        instructions[7]  = "sum of the two dice and the right to roll again. ";
        instructions[8]  = "If one die has a face value of 1, you lose all ";
        instructions[9]  = "points for this round and play passes to your ";
        instructions[10] = "opponent. If both dice have a face value of 1, you ";
        instructions[11] = "lose all points for this game and play passes to ";
        instructions[12] = "your opponent. At any time during your turn, you ";
        instructions[13] = "can bank your points to add your round points to ";
        instructions[14] = "your total and pass play to your opponent.";
        instructions[15] = " ";
        instructions[16] = "The first player to reach 100 points wins!";
        
        return instructions;
    }
    
    /**
     * <p>Returns the Player 1 object.</p>
     * 
     * @return The Player 1 object
     */
    public Player getP1() {
        return player;
    }
    
    /**
     * <p>Returns the computer player object</p>
     * 
     * @return The Player representing the computer
     */
    public Player getP2() {
        return comp;
    }
    
    /**
     * <p>Returns whether it is player 1's turn.</p>
     * 
     * @return True if it is player 1's turn; false if it is not
     */
    public boolean getPlayerTurn() {
        return playerTurn;
    }
    
    /**
     * <p>Returns the JPanel containing the dice</p>
     * 
     * @return The JPanel containing the dice
     */
    public JPanel getDicePanel() {
        return diceBlock;
    }
    
    /**
     * <p>Returns the JPanel GUI for this game.</p>
     * 
     * @return The PigPanel GUI object
     */
    public PigPanel getPigPanel() {
        return pigPanel;
    }
    
    public PigInstructions getInstructionPanel() {
        return instructionPanel;
    }
    
    /**
     * <p>Generates a random value for both dice.</p>
     */
    public void shuffle() {
        die1.generateValue();
        die2.generateValue();
    }
    
    /**
     * <p>Rolls both dice.</p>
     */
    public void roll() {
        die1.roll();
        die2.roll();
        System.out.println("Die 1: " + die1.getFace() + "\nDie 2: " + die2.getFace());
    }
    
    /**
     * <p>Checks whether a player has scored points on their last roll.</p>
     * 
     * @param p The Player object to check
     * @return An int: -1 if the player lost all points for the game, 0 if
     * the player lost all points for this round, and >1 if they scored
     * points on this roll
     */
    public int scorePoints(Player p) {
        final int val1 = die1.getFace();
        final int val2 = die2.getFace();
        final int result;
        
        if (val1 == 1 && val2 == 1) {
            result = -1;
        } else if (val1 == 1 || val2 == 1) {
            result = 0;
        } else {
            result = val1 + val2;
        }
        return result;
    }
    
    /**
     * <p>Updates a player object's score for their last roll.</p>
     * 
     * @param p The player object to update
     * @return Whether the player's turn continues for another roll
     */
    public boolean updatePoints(final Player p) {
        final int val1 = die1.getFace();
        final int val2 = die2.getFace();
        
        System.out.println(p + " total: " + p.getTotalPoints());
        
        if (val1 == 1 && val2 == 1) {
            p.resetPoints();
            System.out.println(p + " lost all points for this game!");
            return false;
        } else if (val1 == 1 || val2 == 1) {
            p.resetRoundPoints();
            System.out.println(p + " lost all points for this round!");
            return false;
        } else {
            p.updatePoints(val1 + val2);
            System.out.println(p + " gained " + (val1 + val2) + " points.");
            System.out.println(p + " has " + p.getRoundPoints() + " points.");
            return true;
        }
    }
    
    /**
     * <p>Simulates the computer's turn. Automatically passes play when it
     * rolls a 1, wins the game, or scores >= 20 points in a turn.</p>
     */
    public void compPlay() {
        do {
            roll();
        } while (updatePoints(comp) && checkWinner() != comp && comp.getRoundPoints() < 20 );
        
        comp.bank();
    }
    
    /**
     * <p>Simulates the player's turn. If the player has won, returns true.
     * If the player did not win, returns false.
     * 
     * @return Whether the player won after this roll
     */
    public boolean playerPlay() {
        roll();
        
        if (updatePoints(player)) {
            if (checkWinner() == player) {
                return true;
            } else {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * <p>Banks the player's points.</p>
     * 
     * @param p The player whose points to update
     */
    public void bankPoints(Player p) {
        p.bank();
    }
    
    /**
     * <p>Checks if a player has won the game.</p>
     * 
     * @return The player object that has won
     */
    public Player checkWinner() {
        if (player.getPoints() >= WIN) {
            return player;
        } else if (comp.getPoints() >= WIN) {
            return comp;
        } else {
            return null;
        }
    }
}
