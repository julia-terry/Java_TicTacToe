package TicTacToeGame;

/*
 * Player Class
 */

public class Player {
    private String name;
    private int roundsWon;

    Player(String name) {
        this.name = name;
        this.roundsWon = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public void setRoundsWon(int roundsWon) {
        this.roundsWon = roundsWon;
    }   
}
