package GameEngine;
import java.io.Serializable;


/**
 *  The Cell class, a serializable object representation
 *  of each cell in the GUI grid.
 *
 *  @author adsrc
 *  @version 1.0
 *
 */
public class Cell implements Serializable {


    private String _color;

    private int _CRITMASS;

    private int _currmass;


    /**
     *
     * Parametrised constructor for a Cell object
     *
     * @param _color    A String containing hex value of the Color
     *                  the {@link javafx.scene.shape.Sphere} in the cell
     * @param _CRITMASS The critical mass of the Cell
     * @param _currmass The current number of {@link javafx.scene.shape.Sphere}
     *                  in the cell
     */
    public Cell(String _color, int _CRITMASS, int _currmass) {
        this._color = _color;
        this._CRITMASS = _CRITMASS;
        this._currmass = _currmass;

    }

    /**
     *
     * @return current hex value of the color of the occupant sphere
     */
    public String get_color() {
        return _color;
    }

    /**
     *
     * @param _color hex string of current color
     */
    public void set_color(String _color) {
        this._color = _color;
    }

    /**
     *
     * @return critical mass of the cell
     */
    public int get_CRITMASS() {
        return _CRITMASS;
    }

    /**
     *
     * @return _currmass current number of spheres in the cell
     */
    public int get_currmass() {
        return _currmass;
    }

    /**
     *
     * @param _currmass set the current number of spheres in the cell
     */
    public void set_currmass(int _currmass) {
        this._currmass = _currmass;
    }




}
