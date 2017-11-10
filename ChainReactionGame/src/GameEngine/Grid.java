package GameEngine;

import java.io.Serializable;
import java.util.ArrayList;

public class Grid implements Serializable {


    private ArrayList<ArrayList<Cell>> _grid;

    public Grid(int choiceOfGrid) {
        if(choiceOfGrid == 0) {

            ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>(9);

            for (ArrayList<Cell> row:
                 grid) {
                row = new ArrayList<Cell>(6);
            }

            this._grid = grid;

        }

        else if(choiceOfGrid == 1) {

            ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>(10);

            for (ArrayList<Cell> row:
                    grid) {
                row = new ArrayList<Cell>(15);
            }

            this._grid = grid;

        }
    }

    public ArrayList<ArrayList<Cell>> get_grid() {
        return _grid;
    }

}
