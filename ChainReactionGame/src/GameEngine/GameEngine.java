package GameEngine;

public class GameEngine {

    private int _numPlayers;
    private static int _gridSize;
    private int _choice;
    private GameController _gc;

    public int get_numPlayers() {
        return _numPlayers;
    }

    public void set_numPlayers(int _numPlayers) {
        this._numPlayers = _numPlayers;
    }

    public static int get_gridSize() {
        return _gridSize;
    }

    public static void set_gridSize(int _gs) {
        _gridSize = _gs;
    }

    public int get_choice() {
        return _choice;
    }

    public void set_choice(int _choice) {
        this._choice = _choice;
    }

    public GameController get_gc() {
        return _gc;
    }

    public void set_gc(GameController _gc) {
        this._gc = _gc;
    }

    /*

    Will check if "grid.ser" is empty or doesn't exist. If it exists, load the state of
    game and continue, else create a new file"

     */

    public boolean checkResume() {

        return true;

    }

    public void startGame() {



    }


}
