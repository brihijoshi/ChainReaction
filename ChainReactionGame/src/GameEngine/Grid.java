package GameEngine;

import java.io.Serializable;
import java.util.ArrayList;

public class Grid implements Serializable {


    private ArrayList<ArrayList<Cell>> _grid;

    public Grid(int choiceOfGrid) {
        if(choiceOfGrid == 0) {

            ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>(9);

            for (int i = 0; i < 9; i++) {
                ArrayList<Cell> row = new ArrayList<Cell>();
                for (int j = 0; j < 6; j++) {
                    Cell c = new Cell(null, 0, 0);
                    row.add(j,c);
                }
                grid.add(i,row);

            }



            this._grid = grid;

        }

        else if(choiceOfGrid == 1) {

            ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>(15);

            for (int i = 0; i < 15; i++) {
                ArrayList<Cell> row = new ArrayList<Cell>();
                for (int j = 0; j < 10; j++) {
                    Cell c = new Cell(null, 0, 0);
                    row.add(j,c);
                }
                grid.add(i,row);

            }



            this._grid = grid;

        }
    }

    public ArrayList<ArrayList<Cell>> get_grid() {
        return _grid;
    }

}
