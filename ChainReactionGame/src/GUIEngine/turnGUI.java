package GUIEngine;

import GameEngine.GameController;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

class turnGUI implements EventHandler<MouseEvent> {



    @Override
    public void handle(MouseEvent e) {


        /**
         *  Type reference:
         *
         *  0 - Corner
         *  1 - Edge
         *  2 - Normal
         *
         *
         * **/

        System.out.println("Mouse click detected");


        StackPane source = (StackPane) e.getSource();
        GridPane grid = (GridPane) source.getParent();
        //System.out.println(grid.getChildren().indexOf(source));
        GameController.set_index(grid.getChildren().indexOf(source)-1);
        // gives row and column
        int[] index = GameController.convert_index(GUIMain.get_numCols());
        System.out.println(Integer.toString(index[0]) + " " + Integer.toString(index[1]));
        Group cellGroup = (Group) source.getChildren().get(0);
        int currMass = cellGroup.getChildren().size();

        GUIMain.addOrbAndAnimate(grid, index[0], index[1],currMass + 1, Color.RED);
        if(currMass + 1 == get_CritMass(index[0], index[1])) {
            GUIMain.addOrbAndAnimate(grid, index[0], index[1],0, Color.RED);
            handleTurn(index[0], index[1], grid);
        }

    }

    public boolean areValidCoord(int row, int col){
        if (row < GUIMain.get_numRows() && row >= 0 && col < GUIMain.get_numCols() && col >= 0){
            return true;
        }
        return false;
    }

    public int get_CritMass(int row, int col) {

        if((row == GUIMain.get_numRows() - 1 || row == 0) && (col == GUIMain.get_numCols() - 1 || col == 0)) {
            return 2;
        }

        else if((row == 0 || row == GUIMain.get_numRows() - 1) || (col == GUIMain.get_numCols() - 1 || col == 0)) {
            return 3;
        }
        else {
            return 4;
        }

    }

    public int getIndexOfStackPaneFromCoords(int row, int col) {

        int ans = 0;

        if(row == 0) {
            ans = col;
        }

        else{
            ans = (row) * GUIMain.get_numCols() + col;
        }

        return ans + 1; // because childrenArray of gridPane starts at 1

    }

    public void handleTurn(int row, int col, GridPane gp) {

        if (areValidCoord(row - 1, col)){
            StackPane sp = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row - 1, col));
            Group grp = (Group) sp.getChildren().get(0);
            int currMass = grp.getChildren().size();
            System.out.println(currMass);
            GUIMain.addOrbAndAnimate(gp, row - 1, col, currMass + 1, Color.RED);
            int critMass = get_CritMass(row - 1, col);
            if (currMass + 1 == critMass) {
                GUIMain.addOrbAndAnimate(gp, row - 1, col, 0, Color.RED);
                handleTurn(row - 1, col, gp);
            }
        }

        //Handle Left

        if (areValidCoord(row, col - 1)){
            System.out.println(getIndexOfStackPaneFromCoords(row, col - 1));
            StackPane sp = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row, col- 1));
            Group grp = (Group) sp.getChildren().get(0);
            int currMass = grp.getChildren().size();
            System.out.println(grp.getChildren());
            System.out.println(currMass);
            GUIMain.addOrbAndAnimate(gp, row, col - 1, currMass + 1, Color.RED);
            int critMass = get_CritMass(row, col - 1);
            if (currMass + 1 == critMass) {
                GUIMain.addOrbAndAnimate(gp, row, col - 1, 0, Color.RED);
                handleTurn(row, col - 1, gp);
            }
        }

        //Handle Bottom
        if (areValidCoord(row + 1, col)){
            StackPane sp = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row + 1, col));
            Group grp = (Group) sp.getChildren().get(0);
            int currMass = grp.getChildren().size();
            System.out.println(currMass);
            GUIMain.addOrbAndAnimate(gp, row + 1, col, currMass + 1, Color.RED);
            int critMass = get_CritMass(row + 1, col);
            if (currMass + 1 == critMass) {
                GUIMain.addOrbAndAnimate(gp, row + 1, col, 0, Color.RED);
                handleTurn(row + 1, col, gp);
            }
        }
        //System.out.println("Right " + row + " "+ (col+1));
        //Handle Right
        if (areValidCoord(row, col + 1)){
            StackPane sp = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row, col + 1));
            Group grp = (Group) sp.getChildren().get(0);
            int currMass = grp.getChildren().size();
            System.out.println(currMass);
            GUIMain.addOrbAndAnimate(gp, row, col + 1, currMass + 1, Color.RED);
            int critMass = get_CritMass(row, col + 1);
            if (currMass + 1 == critMass) {
                GUIMain.addOrbAndAnimate(gp, row, col + 1, 0, Color.RED);
                handleTurn(row, col + 1, gp);
            }
        }


    }

}
