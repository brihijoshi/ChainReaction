package GameEngine;

import java.io.Serializable;
import java.util.ArrayList;

public class Grid implements Serializable {


    private ArrayList<ArrayList<Cell>> _grid;

    public ArrayList<ArrayList<Cell>> get_grid() {
        return _grid;
    }

}
