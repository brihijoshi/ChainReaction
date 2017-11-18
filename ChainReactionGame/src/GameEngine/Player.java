package GameEngine;

import java.io.Serializable;

/**
 *  The Player class, a serializable object representation
 *  of each player in the game.
 *
 *  @author adsrc
 *  @version 1.2
 *
 */

public class Player implements Serializable {

    private String _colour;
    private boolean _isAlive;
    private boolean _isKillable;
    private boolean _isActive;

    /**
     *
     * Parametrised constructor for a Player object
     *
     * @param _colour  A String containing the Hex value of the of the Color associated with the player
     * @param _isAlive A Boolean indicating whether a player is eligible to make a move
     * @param _isKillable A Boolean indicating if a player has made their first move
     * @param _isActive A Boolean indicating if a player has the current turn
     */

    public Player(String _colour, boolean _isAlive, boolean _isKillable, boolean _isActive) {
        this._colour = _colour;
        this._isAlive = _isAlive;
        this._isKillable = _isKillable;
        this._isActive = _isActive;
    }

    /**
     *
     * @return the current color of the player
     */

    public String get_colour() {
        return _colour;
    }

    /**
     *
     * @param _colour set the color of the player
     */

    public void set_colour(String _colour) {
        this._colour = _colour;
    }

    /**
     *
     * @return the elibility of the player to make a move
     */

    public boolean get_isAlive() {
        return _isAlive;
    }

    /**
     *
     * @param _isAlive the eligibility of the player to make a move
     */

    public void set_isAlive(boolean _isAlive) {
        this._isAlive = _isAlive;
    }

    /**
     *
     * @return the eligibility of the player to be killed
     */

    public boolean get_isKillable() {
        return _isKillable;
    }

    /**
     *
     * @param _isKillable the eligibility of the player to be killed
     */

    public void set_isKillable(boolean _isKillable) {
        this._isKillable = _isKillable;
    }

    /**
     *
     * @return the eligibility of the player to play the current round
     */

    public boolean get_isActive() {
        return _isActive;
    }

    /**
     *
     * @param _isActive the eligibility of the player to play the current round
     */

    public void set_isActive(boolean _isActive) {
        this._isActive = _isActive;
    }


}
