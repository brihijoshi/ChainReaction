package GameEngine;

public class Cell {

    //Changing _TYPE in the original UML with _color

    private String _color;

    private int _CRITMASS;

    private int _currmass;

    public Cell(String _color, int _CRITMASS, int _currmass) {
        this._color = _color;
        this._CRITMASS = _CRITMASS;
        this._currmass = _currmass;
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


}
