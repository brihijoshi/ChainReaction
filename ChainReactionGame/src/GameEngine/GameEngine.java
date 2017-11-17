package GameEngine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameEngine {

    private int _numPlayers;
    private int _gridSize;
    private int _choice;
    private GameController _gc;
    private Map player_colors;

    public GameEngine(int _numPlayers, int _gridSize) {
        this._numPlayers = _numPlayers;
        this._gridSize = _gridSize;
    }


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


    public Map getPlayer_colors() {
        return player_colors;
    }


    public void setplayer_colors(HashMap p){
        player_colors=p;
    }


    public static boolean checkResume() throws Exception {

        File file = new File("game.ser");

        boolean empty = !file.exists() || file.length() == 0;

        return !empty;

    }

    public static boolean checkUndo() throws Exception {

        File file = new File("undo.ser");

        if (file.exists()){
            return true;
        }
        return false;

    }

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
