package GameEngine;

import java.io.*;
import java.util.ArrayList;


/**
 *  The GameController class is responsible for saving game states
 *  after every move.
 *
 *  It also assists in managing the resume and undo operations.
 *
 *  @author adsrc
 *  @author brihijoshi
 */
public class GameController {

    private ArrayList<Player> _players;
    private Grid _grid;
    private GameState _gameState;
    private boolean _resume;
    private static int _index;
    private boolean _endGame;


    /**
     * Default constructor
     */
    public GameController() {
    }

    /**
     *
     * Parametrised constructor
     *
     * @param _players      ArrayList of the Player objects in the game
     * @param _grid         The Grid object of the game
     * @param _gameState    The current serializable state of the game
     * @param _resume       Whether the game is a resumed instance or
     *                      a fresh game
     */
    public GameController(ArrayList<Player> _players, Grid _grid, GameState _gameState, boolean _resume) {
        this._players = _players;
        this._grid = _grid;
        this._gameState = _gameState;
        this._resume = _resume;
        this._endGame = false;
    }


    /**
     *
     * @return  an rrayList of the player objects
     */
    public ArrayList<Player> get_players() {
        return _players;
    }

    /**
     *
     * @param _players sets the players in the game
     */
    public void set_players(ArrayList<Player> _players) {
        this._players = _players;
    }

    /**
     *
     * @return a Grid object representing the current GUI grid
     */
    public Grid get_grid() {
        return _grid;
    }

    /**
     *
     * @param _grid sets the current Grid object
     */
    public void set_grid(Grid _grid) {
        this._grid = _grid;
    }

    /**
     *
     * @return returns the current GameState object
     */
    public GameState get_gameState() {
        return _gameState;
    }

    /**
     *
     * @param _gameState sets the current GameState object
     */
    public void set_gameState(GameState _gameState) {
        this._gameState = _gameState;
    }

    /**
     *
     * @return returns true is the game is a resumed instance, else false
     */
    public boolean is_resume() {
        return _resume;
    }

    /**
     *
     * @param _resume set the resume value
     */
    public void set_resume(boolean _resume) {
        this._resume = _resume;
    }

    /**
     *
     * @return get the index of the {@link javafx.scene.layout.StackPane} that has been pressed in the GUI
     */
    public static int get_index(){
        return _index;
    }

    /**
     *
     * @param _i set the index attribute
     */
    public static void set_index(int _i){
        _index=_i;
    }

    /**
     *
     * @return a boolean value to indicate whether the game is at an end condition
     */
    public boolean is_endGame() {
        return _endGame;
    }

    /**
     *
     * @param _endGame returns true if the game has reached an end condition
     */
    public void set_endGame(boolean _endGame) {
        this._endGame = _endGame;
    }


    /**
     * This method de-serializes the saved GameState object from the "game.ser"
     * file and re-attaches it to the GameController.
     *
     * It is invoked in case the user wants to resume a previously unfinished game.
     *
     * It follows the Decorator design pattern.
     *
     * @throws IOException              In case the file to load from does not exist
     * @throws ClassNotFoundException   In case there is a mismatch of objects de-serialized
     *                                  and the ones we are expecting
     */
    public void loadGameState() throws IOException, ClassNotFoundException {

        FileInputStream fis=new FileInputStream("game.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        GameState g= (GameState) ois.readObject();
        set_gameState(g);
        set_players(g.get_players());
        set_grid(g.get_grid());
        ois.close();
        fis.close();

    }

    /**
     * This method de-serializes the saved GameState object from the "undo.ser" file
     * and returns it.
     *
     * It is invoked in case the player wants to undo his played move and attempt again.
     *
     * It follows the Decorator design pattern.
     *
     * @return The de-serialized GameState of the saved instance
     * @throws IOException              In case the file to load from does not exist
     * @throws ClassNotFoundException   In case there is a mismatch of classes
     */
    public GameState loadUndoState() throws IOException, ClassNotFoundException{
        FileInputStream fis=new FileInputStream("undo.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        GameState g= (GameState) ois.readObject();
        set_gameState(g);
        set_players(g.get_players());
        set_grid(g.get_grid());
        ois.close();
        fis.close();
        return g;
    }

    /**
     * This method saves the state of the GUI grid in a serializable format
     * at the end of every turn.
     *
     * It's purpose is to create a state to be retrieved in case the player wants to resume
     * the game after an unexpected exit.
     *
     * It follows the Decorator design pattern.
     *
     * @throws IOException In case the file to write to does not exist
     */
    public void saveGameState() throws IOException {

        GameState g = get_gameState();

        g.set_grid(_grid);
        g.set_players(_players);
        FileOutputStream fos = new FileOutputStream("game.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(g);
        oos.close();
        fos.close();

    }

    /**
     * This method saves the state of the GUI grid in a serializable format
     * at the end of every turn.
     *
     * It's purpose is to create a state to be retrieved in case the player wants to undo
     * his played move and attempt again.
     *
     * It follows the Decorator design pattern.
     *
     * @throws IOException In case the file to write to does not exist
     */
    public void saveUndoState() throws IOException {

        GameState g = get_gameState();
        g.set_grid(_grid);
        g.set_players(_players);
        FileOutputStream fos = new FileOutputStream("undo.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(g);
        oos.close();
        fos.close();

    }


    /**
     * This method returns the 2-D indices of a given GUI component in the GridPane.
     *
     * The {@link javafx.scene.layout.GridPane} that has been used for a grid in the GUI of the game stores the
     * {@link javafx.scene.layout.StackPane} representing each cell in a 1-D array. From it, we need to retrieve
     * corresponding 2-D coordinates to update the Grid object. This is achieved by this function.
     *
     * It achieves this by using the <code>_index</code> attribute and the number of columns in the GUI grid which
     * is passed as parameters.
     *
     * @param numCols The number of columns in a row of the GUI grid
     * @return An integer array containing the 2-D coordinates of the corresponding Cell object
     */
    public static int[] convert_index(int numCols){

        int[] arr = {_index/numCols,_index%numCols};
        return arr;
    }


    /**
     * Indicates whether the given 2-D coordinates are valid in the <code>_grid</code> of the class.
     *
     * In order to figure out how many neighbours does the cell have, we use this function as a helper
     * and check the validity of all possible neighbour coordinates.
     *
     * @param x_index The x-coordinate of the cell
     * @param y_index The y-coordinate of the cell
     * @return  Boolean value indicating validity of the cell
     */
    public boolean isValid(int x_index, int y_index){
        if ((x_index < _grid.get_grid().size()) && (x_index>=0) && (y_index<_grid.get_grid().get(0).size()) && (y_index>=0)){
            return true;
        }
        return false;
    }

    /**
     *  A function to simulate a game turn on the <code>_grid</code> of the class.
     *
     *  Made for testing out our logic
     *
     * @param x The x-coordinate of the pressed cell
     * @param y The y-coordinate of the pressed cell
     */
    public void taketurn(int x, int y){

        //Handle Top
        if (isValid(x-1, y)){
            Cell top = _grid.get_grid().get(x-1).get(y);
            top.set_currmass(top.get_currmass()+1);

            if (top.get_currmass()>top.get_CRITMASS()){
                top.set_currmass(0);
                taketurn(x-1,y);
            }
        }


        if (isValid(x,y-1)){
            Cell left = _grid.get_grid().get(x).get(y-1);
            left.set_currmass(left.get_currmass()+1);
            //Add in the GUI as well
            if (left.get_currmass()>left.get_CRITMASS()){
                left.set_currmass(0);
                taketurn(x,y-1);
            }
        }


        if (isValid(x+1,y)){
            Cell bottom = _grid.get_grid().get(x+1).get(y);
            bottom.set_currmass(bottom.get_currmass()+1);
            //Add in the GUI as well
            if (bottom.get_currmass()>bottom.get_CRITMASS()){
                bottom.set_currmass(0);
                taketurn(x+1,y);
            }
        }


        if (isValid(x,y+1)){
            Cell right = _grid.get_grid().get(x).get(y+1);
            right.set_currmass(right.get_currmass()+1);
            //Add in the GUI as well
            if (right.get_currmass()>right.get_CRITMASS()){
                right.set_currmass(0);
                taketurn(x,y+1);
            }
        }
    }

    /**
     *  Handler function for the <code>taketurn(x, y)</code> function.
     */
    public void handleGame() {

        taketurn(0, 0);

    }


}
