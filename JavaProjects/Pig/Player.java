package portfolio;

public class Player {
    
    private int totalPoints;
    private int roundPoints;
    private String name;
    
    public Player(final String n) {
        resetPoints();
        name = n;
    }
    
    public int getPoints() {
        return totalPoints + roundPoints;
    }
    
    public int getTotalPoints() {
        return totalPoints;
    }
    
    public int getRoundPoints() {
        return roundPoints;
    }
    
    public void updatePoints(final int update) {
        roundPoints += update;
    }
    
    public void resetRoundPoints() {
        roundPoints = 0;
    }
    
    public void bank() {
        totalPoints += roundPoints;
        roundPoints = 0;
    }
    
    public void resetPoints() {
        totalPoints = 0;
        roundPoints = 0;
    }
    
    public String toString() {
        return name;
    }

}
