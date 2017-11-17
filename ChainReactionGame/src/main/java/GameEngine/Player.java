package GameEngine;

import java.io.Serializable;

public class Player implements Serializable {

    private String _colour;
    private boolean _isAlive;
    private boolean _isKillable;
    private boolean _isActive;

    public Player(String _colour, boolean _isAlive, boolean _isKillable, boolean _isActive) {
        this._colour = _colour;
        this._isAlive = _isAlive;
        this._isKillable = _isKillable;
        this._isActive = _isActive;
    }


    public String get_colour() {
        return _colour;
    }

    public void set_colour(String _colour) {
        this._colour = _colour;
    }

    public boolean get_isAlive() {
        return _isAlive;
    }

    public void set_isAlive(boolean _isAlive) {
        this._isAlive = _isAlive;
    }

    public boolean get_isKillable() {
        return _isKillable;
    }

    public void set_isKillable(boolean _isKillable) {
        this._isKillable = _isKillable;
    }

    public boolean get_isActive() {
        return _isActive;
    }

    public void set_isActive(boolean _isActive) {
        this._isActive = _isActive;
    }


}
