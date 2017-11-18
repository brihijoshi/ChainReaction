package GameEngine;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *  A serializable representation of the GUI grid.
 *
 *  This class gives a serializable representation of
 *  the GUI grid. It contains a 2-D {@link ArrayList} of
 *  {@link Cell} objects that store the Grid status.
 *
 *  @author adsrc
 *  @version 1.2
 */

public class Grid implements Serializable {

    private ArrayList<ArrayList<Cell>> _grid;

    /**
     *  Parametrised constructor
     *
     *  This constructor uses the <code>_gridSize</code> attribute of the
     *  {@link GameEngine} class to return a 2-D {@link ArrayList} of the
     *  appropriate size.
     *
     *  If <code>choiceOfGrid = 0</code>, the <code>_grid</code> is set to
     *  a <code>9 x 6</code> 2-D ArrayList. Else, if <code>choiceOfGrid = 1</code>,
     *  it is set to a <code>15 x 10</code> 2-D ArrayList.
     *
     * @param choiceOfGrid indicates the player's choice of grid size
     */
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

    /**
     *
     * @return the current 2-D ArrayList which _grid is set to
     */
    public ArrayList<ArrayList<Cell>> get_grid() {
        return _grid;
    }

}
