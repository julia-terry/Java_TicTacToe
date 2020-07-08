package TicTacToeGame;

import java.util.ArrayList;
import java.util.Random;

/*
 * Game Data Model that takes care of the game data/variables
 */

public class GameDataModel {
    private Player p1;
    private Player p2;
    private int numPlayers = 0;
    private boolean gameStarted = false;
    private boolean gameOver = false;
    final private int MAX_ROUNDS = 7;
    private int round = 0;
    private boolean twoPlayers;
    private boolean gameModeSelected = false;
    private boolean p1Turn;
    private Win win = Win.NONE;
    private int turnCounter = 0;
    int gameCounter = 0;
    private Token[] gameArray = new Token[9];

    public int getGameCounter() {
        return gameCounter;
    }

    public void setGameCounter(int gameCounter) {
        this.gameCounter = gameCounter;
    }

    
    public int getMAX_ROUNDS() {
        return MAX_ROUNDS;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        if (round <= MAX_ROUNDS)
            this.round = round;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }
    

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isTwoPlayers() {
        return twoPlayers;
    }

    public void setTwoPlayers(boolean twoPlayers) {
        this.twoPlayers = twoPlayers;
        this.gameModeSelected = true;
    }
    
     public boolean isP1Turn() {
        return p1Turn;
    }
     
    public boolean selectedGameMode() {
       return gameModeSelected;
    }
    
    Token getToken(int i) {
        return gameArray[i];
    }
    
    public Win getWin() {
        return win;
    }

    //makes a new player and sets the number of players in the code
    void addNewPlayer(String name) {
        if (numPlayers == 0) {
            p1 = new Player(name);
            numPlayers += 1;
        } else if (numPlayers == 1) {
            p2 = new Player(name);
            numPlayers += 1;
        } else {
            System.out.println("Shouldn't get here - trying to add more than one player");
        }
    }

    //checks to see if the players have been set up
    public boolean isGameReady() {
        boolean ready = false;
        if (twoPlayers == false) {
            if (p1 != null) {
                p2 = new Player("Computer");
                numPlayers++;
                ready = true;
            }
        } else  {
            if ((p1 != null) && (p2 != null)) {
                ready = true;
            }
        }
        return ready;
    }
    
    //sets gameStarted to true and runs the startNewRound method
    public void startGame() {
        gameStarted = true;
        startNewRound();
    }
    
    //starts a new round for the player
    public void startNewRound() {
        gameOver = false;
        p1Turn = true;
        win = Win.NONE;
        
        //randomly decides who goes first if there are two players
        if (twoPlayers) {
            double temp = Math.random();
            if (temp > 0.5) {
                p1Turn = false;
                System.out.println("P2 goes first.");
            } else {
                System.out.println("P1 goes first");
            }
        }
        
        //initialize our gameArray to be full of blanks
        for (int i = 0; i< 9; i++) {
            gameArray[i] = Token.Blank;      
        }
        
        // checks to see if player has reached max rounds of the game
        if(gameCounter == MAX_ROUNDS){
            EndJFrame endFrame = new EndJFrame(this);
            endFrame.maxRoundsMet();
            endFrame.setVisible(true);
        }
        
        // starts a new game and adds to the game counter
        else{
            GameBoard myGameBoard = new GameBoard(this);
            gameCounter++;
            myGameBoard.updateRoundLabel();
            myGameBoard.setVisible(true);
        }
        
    }
    
    //changes the token when player presses it on GameBoard
    public boolean changeToken(int index) {
        
        // make sure the space is blank, if not return
        if (gameArray[index] != Token.Blank) {
            System.out.println("Space is already used");
            return false;
        }
        
        // get token for player
        Token t;
        if (p1Turn) {
            t = Token.X;
        } else {
            t = Token.O;
        }
        
        // set token at index
        gameArray[index] = t;
        
        // add a turn to the turn counter
        turnCounter++;
        
        // see if won, if so, game is over and return true
        if (checkForWin(index)) {
            System.out.println("Wins!");
            gameOver = true;
            turnCounter = 0;
            return true;
        }
        
        // see if tied, game is over and return true
        if(turnCounter==9){
            System.out.println("You have tied");
            gameOver = true;
            return true;
        }
        
        // change turns
        p1Turn = !p1Turn;
        return true;
    }
    
    //returns if the specified index in the array is blank or if someone has already filled it
    public boolean isSpaceBlank(int index) {
        if (gameArray[index] == Token.Blank) {
            return true;
        } else {
            return false;
        }
    }
    
    //looks for what type of win it was and states that the game is over
    public boolean checkForWin(int index) {
        boolean over = false;

        if(gameArray[index].equals(gameArray[0]) && gameArray[index].equals(gameArray[1]) && gameArray[index].equals(gameArray[2])){
            win = Win.TOP_ROW;
            over = true;
        }
        
        if(gameArray[index].equals(gameArray[3]) && gameArray[index].equals(gameArray[4]) && gameArray[index].equals(gameArray[5])){
            win = Win.MID_ROW;
            over = true;
        }
        
        if(gameArray[index].equals(gameArray[6]) && gameArray[index].equals(gameArray[7]) && gameArray[index].equals(gameArray[8])){
            win = Win.BOT_ROW;
            over = true;
        }
        
        if(gameArray[index].equals(gameArray[0]) && gameArray[index].equals(gameArray[4]) && gameArray[index].equals(gameArray[8])){
            win = Win.DIAG1;
            over = true;
        }
        
        if(gameArray[index].equals(gameArray[2]) && gameArray[index].equals(gameArray[4]) && gameArray[index].equals(gameArray[6])){
            win = Win.DIAG2;
            over = true;
        }
        
        if(gameArray[index].equals(gameArray[0]) && gameArray[index].equals(gameArray[3]) && gameArray[index].equals(gameArray[6])){
            win = Win.LEFT_COL;
            over = true;
        }
        
        if(gameArray[index].equals(gameArray[1]) && gameArray[index].equals(gameArray[4]) && gameArray[index].equals(gameArray[7])){
            win = Win.MID_COL;
            over = true;
        }
        
        if(gameArray[index].equals(gameArray[2]) && gameArray[index].equals(gameArray[5]) && gameArray[index].equals(gameArray[8])){
            win = Win.RIGHT_COL;
            over = true;
        }
 
        return over;  
    }  
}

enum Token { X, O, Blank};

enum Win {NONE, TOP_ROW, MID_ROW, BOT_ROW, LEFT_COL, MID_COL, RIGHT_COL, DIAG1, DIAG2};