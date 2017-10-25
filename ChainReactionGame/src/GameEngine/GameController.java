package GameEngine;

import java.util.ArrayList;

public class GameController {

    private ArrayList<Player> _players;
    private Grid _grid;
    private GameState _gameState;
    private boolean _resume;

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

    public GameState get_gameState() {
        return _gameState;
    }

    public void set_gameState(GameState _gameState) {
        this._gameState = _gameState;
    }

    public boolean is_resume() {
        return _resume;
    }

    public void set_resume(boolean _resume) {
        this._resume = _resume;
    }

}
