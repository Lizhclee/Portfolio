package portfolio;

import java.util.Scanner;

/**
 * <p>The program plays Rock-Paper-Scissors with the user. The game continues
 * until the user decides to quit.</p>
 *
 * @author Elizabeth Lee
 * @version 1.0
 */
public class RockPaperScissors {
    /**<p>The number of wins by the user.</p>*/
    private static int wins;
    
    /**<p>The number of losses by the user.</p>*/
    private static int losses;
    
    /**<p>The number of ties between the user and the computer.</p>*/
    private static int ties;
    
    /**<p>The possible throws in a game of Rock-Paper-Scissors.</p>*/
    enum Throw {
        /**<p>The rock.</p>*/
        rock,
        /**<p>The paper.</p>*/
        paper,
        /**<p>The scissors.</p>*/
        scissors;
    }
    
    /**
     * <p>Chooses the computer's throw.</p>
     * 
     * @return The throw of rock, paper, or scissors.
     */
    private static Throw chooseThrow() {
        // A randomly generated integer representing the computer's choice
        int value;
        
        value = (int) (Math.random() * (2 + 1));
        
        // Convert computer's choice to the Throw type
        if (value == 0) {
            return Throw.rock;
        } else if (value == 1) {
            return Throw.paper;
        } else {
            return Throw.scissors;
        }
    }
    
    /**
     * <p>Converts the user's choice to a Throw type.</p>
     * 
     * @param value The string input by the user.
     * @return The throw of rock, paper, or scissors.
     */
    private static Throw chooseThrow(String value) {
        if (value.equalsIgnoreCase("R")) {
            return Throw.rock;
        } else if (value.equalsIgnoreCase("P")) {
            return Throw.paper;
        } else if (value.equalsIgnoreCase("S")) {
            return Throw.scissors;
        } else {
            throw new IllegalArgumentException("A string other than R, P, "
                    + "or S was entered");
        }
    }
    
    /**
     * <p>Checks who won the round.</p>
     * 
     * @param user The user's throw.
     * @param comp The computer's throw.
     */
    private static void whoWon(Throw user, Throw comp) {
        if (user == comp) {
            System.out.println("We tied! We both chose " + comp);
            ties += 1;
        } else if (user == Throw.rock) {
            if (comp == Throw.paper) {
                System.out.println("Paper covers rock. I win!");
                losses += 1;
            } else if (comp == Throw.scissors) {
                System.out.println("Rock crushes scissors. You win!");
                wins += 1;
            } else {
                throw new IllegalArgumentException("Invalid throw for comp");
            }
        } else if (user == Throw.paper) {
            if (comp == Throw.rock) {
                System.out.println("Paper covers rock. You win!");
                wins += 1;
            } else if (comp == Throw.scissors) {
                System.out.println("Scissors cuts paper. I win!");
                losses += 1;
            } else {
                throw new IllegalArgumentException("Invalid throw for comp");
            }
        } else if (user == Throw.scissors) {
            if (comp == Throw.rock) {
                System.out.println("Rock crushes scissors. I win!");
                losses += 1;
            } else if (comp == Throw.paper) {
                System.out.println("Scissors cuts paper. You win!");
                wins += 1;
            } else {
                throw new IllegalArgumentException("Invalid throw for comp");
            }
        } else {
            throw new IllegalArgumentException("Invalid throw for user");
        }
    }
    
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        // The computer's choice
        Throw compThrow;
        // The user's input string
        String userChoice;
        // The user's choice
        Throw userThrow;
        // A string representing whether the user wants to play another round
        String playAgain;
        
        // Create scanner object
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Let's play Rock-Paper-Scissors!\nChoose "
                + "your throw and we'll see who wins.\nAfter the round "
                + "is over, press 'Y' to continue or any other key to quit.\n");
        
        do {
            compThrow = chooseThrow();
            
            System.out.print("Please enter R, P, or S: ");
            userChoice = scan.next();
            
            // Validation of user input
            while (!userChoice.equalsIgnoreCase("R") 
                    && !userChoice.equalsIgnoreCase("P") 
                    && !userChoice.equalsIgnoreCase("S")) {
                System.out.print("Please enter a valid throw (R, P, or S): ");
                userChoice = scan.next();
            }
            
            userThrow = chooseThrow(userChoice);
            
            System.out.println("You chose " + userThrow);
            System.out.println("The computer chose " + compThrow);
            
            whoWon(userThrow, compThrow);
            
            System.out.print("Another round? (Y/?) ");
            playAgain = scan.next();
            
            // Empty line for visual appeal
            System.out.println();
        } while (playAgain.equalsIgnoreCase("Y"));
        
        System.out.println("Game over.\nYou won " + wins + " times, lost " 
                + losses + " times, and tied " + ties + " times.\n");
        
        // Close scanner object
        scan.close();
    }

};
