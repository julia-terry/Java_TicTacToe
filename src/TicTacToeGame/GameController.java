package TicTacToeGame;

/*
 * Game Controller 
 */

public class GameController {
    private GameDataModel myModel;
    private StartScreen myStartScreen;
    
    public GameController(GameDataModel myModel) {
        this.myModel = myModel; 
        myStartScreen = new StartScreen(myModel, this);
        myStartScreen.setVisible(true);  
    }
    
    public void startGame() {
        myModel.startGame();   
    }   
}
