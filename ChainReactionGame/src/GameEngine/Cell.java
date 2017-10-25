package GameEngine;

import javafx.scene.layout.StackPane;

import java.io.Serializable;

public class Cell implements Serializable {

    //Changing _TYPE in the original UML with _color

    private String _color;

    private int _CRITMASS;

    private int _currmass;

    private final StackPane _GUI;


    public Cell(String _color, int _CRITMASS, int _currmass, StackPane _GUI) {
        this._color = _color;
        this._CRITMASS = _CRITMASS;
        this._currmass = _currmass;
        this._GUI = _GUI;
    }

    public String get_color() {
        return _color;
    }

    public void set_color(String _color) {
        this._color = _color;
    }

    public int get_CRITMASS() {
        return _CRITMASS;
    }

    public int get_currmass() {
        return _currmass;
    }

    public void set_currmass(int _currmass) {
        this._currmass = _currmass;
    }

    public StackPane get_GUI() { return _GUI; }


}
