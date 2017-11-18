package GameEngine;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  The serializable version of a GUI Game state.
 *
 *  This class is serializable version of a snapshot of the GUI
 *  grid at a given instance of time. It contains a serializable
 *  representation of the players via an {@link ArrayList}, and
 *  a serializable version of the GUI grid via a {@link Grid}
 *  object.
 *
 *  @author adsrc
 *  @version 1.0
 */
public class GameState implements Serializable {

    private ArrayList<Player> _players;
    private Grid _grid;

    /**
     * Parametrised constructor
     *
     * @param _players An {@link ArrayList} of {@link Player} objects
     * @param _grid An instance of the {@link Grid} class
     */
    public GameState(ArrayList<Player> _players, Grid _grid) {
        this._players = _players;
        this._grid = _grid;
    }


    /**
     *
     * @return returns the current player {@link ArrayList}
     */
    public ArrayList<Player> get_players() {
        return _players;
    }

    /**
     *
     * @param _players the ArrayList of Players to be set
     */
    public void set_players(ArrayList<Player> _players) {
        this._players = _players;
    }

    /**
     *
     * @return the current Grid object
     */
    public Grid get_grid() {
        return _grid;
    }

    /**
     *
     * @param _grid the current Grid object to be set
     */
    public void set_grid(Grid _grid) {
        this._grid = _grid;
    }




}
