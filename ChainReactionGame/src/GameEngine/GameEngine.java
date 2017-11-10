package GameEngine;

import java.io.IOException;
import java.util.ArrayList;

public class GameEngine {

    private int _numPlayers;
    private int _gridSize;
    private int _choice;
    private GameController _gc;

    public int get_numPlayers() {
        return _numPlayers;
    }

    public void set_numPlayers(int _numPlayers) {
        this._numPlayers = _numPlayers;
    }

    public int get_gridSize() {
        return _gridSize;
    }

    public void set_gridSize(int _gs) {
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
    game and continue, else create a new file

     */

    public boolean checkResume() {

        return false;

    }

    public void startGame() throws IOException, ClassNotFoundException {

        if(!checkResume()) {


            ArrayList<Player> players = new ArrayList<Player>();

            for (int i = 0; i < _numPlayers; i++) {
                players.add(new Player("Red", true, false));
            }

            Grid grid = new Grid(_gridSize);

            GameState gs = new GameState(players, grid);

            _gc = new GameController(players, grid, gs, false);

        }

        else {

            // resume button handler will set some things here

            _gc = new GameController();
            _gc.loadGameState();
            this.set_numPlayers(_gc.get_players().size());


        }

    }


}
