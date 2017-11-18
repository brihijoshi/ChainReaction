package GameEngine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *  This is the main class for handling the Game backend.
 *
 *  This is the class that manages the backend of the game, managing
 *  serializations and de-serializations, initializing serializable
 *  instances of the players, grid and keeping track of the players'
 *  colors.
 *
 *  @author adsrc
 *  @author brihijoshi
 *  @version 1.3
 */
public class GameEngine {


    private int _numPlayers;
    private int _gridSize;
    private int _choice;
    private GameController _gc;
    private Map player_colors;

    /**
     * Parametrised constructor
     *
     * The <code>_numPlayers</code> variable speaks for itself but the
     * <code>_gridSize</code> presents a catch. If <code>_gridSize = 0</code>,
     * it implies a <code>9 x 6</code> grid, and if <code>_gridSize = 1</code>,
     * it implies a <code>15 x 10 grid</code>.
     *
     *
     * @param _numPlayers Total number of players in the game
     * @param _gridSize An integer indicating the size of the GUI grid
     */
    public GameEngine(int _numPlayers, int _gridSize) {
        this._numPlayers = _numPlayers;
        this._gridSize = _gridSize;
    }

    /**
     *
     * @return returns the current number of players
     */
    public int get_numPlayers() {
        return _numPlayers;
    }

    /**
     *
     * @param _numPlayers current number of players to be set
     */
    public void set_numPlayers(int _numPlayers) {
        this._numPlayers = _numPlayers;
    }

    /**
     *
     * @return returns the current gris size indicator
     */
    public int get_gridSize() {
        return _gridSize;
    }

    /**
     *
     * @param _gs cuurent grid size indicator to be set
     */
    public void set_gridSize(int _gs) {
        _gridSize = _gs;
    }

    /**
     *
     * @return current choice of the player (to resume or start a new game)
     */
    public int get_choice() {
        return _choice;
    }

    /**
     *
     * @param _choice current choice of player to be set
     */
    public void set_choice(int _choice) {
        this._choice = _choice;
    }

    /**
     *
     * @return {@link GameController} object currently set
     */
    public GameController get_gc() {
        return _gc;
    }

    /**
     *
     * @param _gc {@link GameController} object to be set
     */
    public void set_gc(GameController _gc) {
        this._gc = _gc;
    }


    /**
     *
     * @return {@link Map} of the {@link Player} objects and their colors
     */
    public Map getPlayer_colors() {
        return player_colors;
    }

    /**
     *
     * @param p {@link Player} to color mapping to be set
     */
    public void setplayer_colors(HashMap p){
        player_colors=p;
    }

    /**
     *  Checks if the <code>"game.ser"</code> file, which contains the
     *  {@link GameState} to be resumed, exists or not.
     *
     * @return True if the <code>"game.ser"</code> file exists, else false
     * @throws Exception In case the serialized file does not exist
     */
    public static boolean checkResume() throws Exception {

        File file = new File("game.ser");

        boolean empty = !file.exists() || file.length() == 0;

        return !empty;

    }

    /**
     *  Checks if the <code>"undo.ser"</code> file, which contains the
     *  {@link GameState} of the previous move, exists or not.
     *
     * @return True if the <code>"game.ser"</code> file exists, else false
     * @throws Exception In case the serialized file does not exist
     */
    public static boolean checkUndo() throws Exception {

        File file = new File("undo.ser");

        if (file.exists()){
            return true;
        }
        return false;

    }

    /**
     *  Initialise the essential objects in case of a new game.
     *
     *  In case the player has chosen to start a new game, i.e., the <code>_choice</code>
     *  option is set to <code>0</code>, in this case this function initializes the essential
     *  objects like all the {@link Player} objects, the initial {@link GameState}, the
     *  {@link Grid} object, and the {@link GameController}.
     *
     *  It also sets the first player as active and killable.
     *
     * @throws IOException In case their is no file to write to, or read from
     * @throws ClassNotFoundException In case there is a mis-match of classes
     */
    public void startGame() throws IOException, ClassNotFoundException {

        if(_choice == 0) {

            ArrayList<Player> players = new ArrayList<Player>();

            for (int i = 0; i < _numPlayers; i++) {
                players.add(new Player(player_colors.get(i+1).toString(), true, false, false));
            }

            players.get(0).set_isKillable(true);
            players.get(0).set_isActive(true);

            Grid grid = new Grid(_gridSize);

            GameState gs = new GameState(players, grid);

            _gc = new GameController(players, grid, gs, false);

        }



    }



}
