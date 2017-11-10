package GameEngine;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {

    private ArrayList<Player> _players;
    private Grid _grid;

    public GameState(ArrayList<Player> _players, Grid _grid) {
        this._players = _players;
        this._grid = _grid;
    }

    public ArrayList<Player> get_players() {
        return _players;
    }

    public void set_players(ArrayList<Player> _players) {
        this._players = _players;
    }

    public Grid get_grid() {
        return _grid;
    }

    public void set_grid(Grid _grid) {
        this._grid = _grid;
    }

}
