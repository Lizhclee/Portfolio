package portfolio;

/**
 * <p>Dice class.</p>
 * 
 * <p>Creates a die object and a DicePanel to show its value.</p>
 * 
 * @author Elizabeth Lee
 * @version 1.0
 *
 */
public class Dice {
    
    /** <p>The maximum value for the die; default 6.</p> */
    private final int MAX_VALUE;
    
    /** <p>The minimum value for the die; default 1.</p> */
    private final int MIN_VALUE;
    
    /** <p>The current face value of the die.</p> */
    private int value;
    
    /** <p>The DicePanel showing the die's face.</p> */
    private DicePanel panel;

    /**
     * <p>Constructor: sets up a 6-sided die and corresponding DicePanel.</p>
     */
    public Dice() {
        MAX_VALUE = 6;
        MIN_VALUE = 1;
        value     = MIN_VALUE;
        panel     = new DicePanel(value);
    }
    
    /**
     * <p>Sets the current face value of the die to a new, valid value.</p>
     */
    public void roll() {
        value = generateValue();
    }
    
    /**
     * <p>Generates a valid value for the die.</p>
     * 
     * @return A valid die value
     */
    public int generateValue() {
        final int val;
        
        val = (int) (Math.random() * (MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE);
        panel.updatePanel(val);
        
        return val;
    }
    
    /**
     * <p>Returns the current face value of the die.</p>
     * 
     * @return The current face value of the die
     */
    public int getFace() {
        return value;
    }
    
    /**
     * <p>Returns the panel displaying the dice GUI.</p>
     * 
     * @return The panel displaying the dice GUI
     */
    public DicePanel getDicePanel() {
        return panel;
    }
}
