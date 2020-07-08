package TicTacToeGame;

/*
 * Main class where we create the game model and controller
 */

public class TicTacToe {
    public static void main(String[] args) {
        // create the game model
        GameDataModel myModel = new GameDataModel();
        // create the controller and pass it the model
        GameController myController = new GameController(myModel);
       
    }
}
