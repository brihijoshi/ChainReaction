package GUIEngine;

import Exceptions.InvalidMoveException;
import GameEngine.GameController;
import GameEngine.Grid;
import GameEngine.Player;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

/**
 * This is the {@link EventHandler} class for each {@link StackPane}
 * that represents each cell of the GUI Grid.
 *
 * The <code>handle(MouseEvent e)</code> method in this class handles
 * most of the logic of each turn. Later methods help in transitions
 * and checking the end game condition.
 *
 * @author adsrc
 * @author brihijoshi
 * @version 1.3
 */
class turnGUI implements EventHandler<MouseEvent> {



    private String currentColorHEX;
    private Grid grid_put;
    private ArrayList<Player> players_put;

    /**
     *
     * @return String that gives the hex-code of current color in String format
     */
    public String getCurrentColorHEX() {
        return currentColorHEX;
    }

    /**
     *
     * @param currentColorHEX sets the hex-code to be set
     */
    public void setCurrentColorHEX(String currentColorHEX) {
        this.currentColorHEX = currentColorHEX;
    }

    /**
     *
     * @return Obtains a {@link Grid} object for serialization from the GUI grid
     */
    public Grid getGrid_put() {
        return grid_put;
    }

    /**
     *
     * @param grid_put sets the serializable grid
     */
    public void setGrid_put(Grid grid_put) {
        this.grid_put = grid_put;
    }

    /**
     *
     * @return Obtains an ArrayList of {@link Player} objects to serialize
     */
    public ArrayList<Player> getPlayers_put() {
        return players_put;
    }

    /**
     *
     * @param players_put sets the current collection of players to serialize
     */
    public void setPlayers_put(ArrayList<Player> players_put) {
        this.players_put = players_put;
    }


    /**
     * This function handles each and every turn for the game, in GUI.
     *
     * It setsthe current color that is playing, decides whether it is valid
     * move, and plays the move using the <code>handleTurn</code> method.
     *
     * It obtains the clicked, {@link StackPane} and manipulates it's child
     * sphere objects.
     *
     * @param e {@link MouseEvent} that triggers this function
     */
    @Override
    public void handle(MouseEvent e) {

        if (!GUIMain.checkEndGame()) {

            ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();
            int numPlayers = GUIMain.get_gameEngine().get_numPlayers();

            currentColorHEX = GUIMain.getCurrentPlayer();


            StackPane source = (StackPane) e.getSource();
            GridPane grid = (GridPane) source.getParent();
            Stage stage = (Stage) grid.getScene().getWindow();

            players_put = GUIMain.get_gameEngine().get_gc().get_players();
            grid_put = new Grid(GUIMain.get_gameEngine().get_gridSize());
            convertGUItoGrid(grid, grid_put);


            try {
                GUIMain.get_gameEngine().get_gc().saveUndoState();
            } catch (Exception e2) {
                e2.printStackTrace();
            }


            GameController.set_index(grid.getChildren().indexOf(source) - 1);

            int[] index = GameController.convert_index(GUIMain.get_numCols());
            Group cellGroup = (Group) source.getChildren().get(0);


            Sphere s = (cellGroup.getChildren().size() == 0) ? null : (Sphere) cellGroup.getChildren().get(0);
            PhongMaterial ph = (s == null) ? null : (PhongMaterial) s.getMaterial();
            Color clr = (ph == null) ? null : ph.getDiffuseColor();


            String oldColor = clr == null ? null : ColorUtil.colorToHex(clr);

            try {
                if (oldColor == null || oldColor.equals(currentColorHEX)) {

                    GUIMain.getRedoButton().setDisable(false);


                    players_put = GUIMain.get_gameEngine().get_gc().get_players();

                    grid_put = new Grid(GUIMain.get_gameEngine().get_gridSize());


                    GUIMain.playExplode();

                    handleTurn(index[0], index[1], grid);


                    try {
                        GUIMain.get_gameEngine().get_gc().saveGameState();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }


                }

                else{
                    throw new InvalidMoveException("Wrong move");

                }

            }
            catch(InvalidMoveException x) {
                GUIMain.getRedoButton().setDisable(true);

                GUIMain.playError();
            }


            try {

                GUIMain.get_gameEngine().get_gc().saveGameState();
            } catch (Exception e2) {

                e2.printStackTrace();
            }


        }


    }

    /**
     * A utility function that converts the {@link GridPane} into a
     * serializable {@link Grid} object.
     *
     * @param gp {@link GridPane} that represents the GUI grid
     * @param g The {@link Grid} object that we replicate the GUI grid to
     */
    public void convertGUItoGrid(GridPane gp, Grid g) {
        ObservableList<Node> GUIlist = gp.getChildren();

        for (int i = 1; i < GUIlist.size(); i++) {
            StackPane cell = (StackPane) GUIlist.get(i);
            Group cell_grp = (Group) cell.getChildren().get(0);
            Sphere s = (cell_grp.getChildren().size() == 0) ? null : (Sphere) cell_grp.getChildren().get(0);
            PhongMaterial ph = (s == null) ? null : (PhongMaterial) s.getMaterial();
            Color clr = (ph == null) ? null : ph.getDiffuseColor();

            int x = (i - 1) / GUIMain.get_numCols();
            int y = (i - 1) % GUIMain.get_numCols();

            g.get_grid().get(x).get(y).set_currmass(cell_grp.getChildren().size());
            g.get_grid().get(x).get(y).set_color((clr == null) ? null : ColorUtil.colorToHex(clr));

        }
    }


    /**
     * This is a utility function that fetches the next player whose turn is
     * going to come.
     *
     * This function fetches a <code>Player</code> object of the next alive
     * player from the <code>_players</code> {@link ArrayList} in the
     * <code>GameController</code> of the <code>GameEngine</code>.
     *
     * @param currentColor The hex-code of the color of the current player
     * @return {@link Player} object denoting the next player
     */
    public Player fetchNextPlayer(String currentColor) {

        ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();
        int numPlayers = GUIMain.get_gameEngine().get_numPlayers();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).get_colour().equals(currentColor)) {
                int j = (i + 1) % numPlayers;

                //aaj mai bathroom me phasa reh gaya

                while (i != j) {

                    if (players.get(j).get_isAlive()) {
                        return players.get(j);
                    } else {
                        j = (j + 1) % numPlayers;
                    }
                }
            }

        }
        return null;
    }

    /**
     *  This is a utility function that evaluates how many players
     *  are active in the <code>_players</code> ArrayList of the
     *  <code>GameController</code> of the <code>GameEngine</code>,
     *  apart from the passed player.
     *
     *  It iterates over the entire {@link GridPane}, and if no occurrences
     *  of a player's color is found, it sets the <code>_isAlive</code>
     *  property of the player to <code>false</code>.
     *
     *
     * @param p player who is not to be evaluated to be living
     */
    public void checkAlivePlayers(Player p) {
        ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();

        Grid g = GUIMain.get_gameEngine().get_gc().get_grid();
        for (int i = 0; i < players.size(); i++) {
            int count = 0;
            if (!players.get(i).equals(p)) {
                for (int j = 0; j < g.get_grid().size(); j++) {
                    for (int k = 0; k < g.get_grid().get(0).size(); k++) {
                        if (g.get_grid().get(j).get(k).get_color() != null) {
                            if (g.get_grid().get(j).get(k).get_color().equals(players.get(i).get_colour())) {
                                count++;
                            }
                        }
                    }
                }
                if (count == 0) {
                    if (players.get(i).get_isKillable()) {
                        players.get(i).set_isAlive(false);
                    }
                }
            }

        }
    }

    /**
     * Fetches the current player from the <code>_players</code> ArrayList of the
     *  <code>GameController</code> of the <code>GameEngine</code>.
     *
     *  Uses the <code>currentColorHex</code> String to find the current player.
     *
     * @return {@link Player} object of the current player
     */
    public Player fetchCurrentPlayer() {

        ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();
        int numPlayers = GUIMain.get_gameEngine().get_numPlayers();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).get_colour().equals(currentColorHEX)) {
                return players.get(i);
            }

        }
        return null;

    }

    /**
     *
     * Function to decide the validity of 2-D coordinates in the {@link GridPane}.
     *
     * Used when checking neighbour cells for potential explosions.
     *
     * @param row row number of the cell
     * @param col column number of the cell
     * @return boolean denoting the validity of the coordinates
     */
    public boolean areValidCoord(int row, int col) {
        if (row < GUIMain.get_numRows() && row >= 0 && col < GUIMain.get_numCols() && col >= 0) {
            return true;
        }
        return false;
    }

    /**
     * An adapter which, given a row and column, gives the critical mass of the cell,
     * on the basis of its coordinates.
     *
     * A corner cell has a critical mass of 2, an edge cell of 3 and an
     * internal cell has a critical mass of 4. The critical mass is the
     * number of adjacent cells that a cell will have.
     *
     * @param row row in which the cell is
     * @param col column in which the cell is
     * @return the critical mass calculated on the basis of position
     */
    public int get_CritMass(int row, int col) {

        if ((row == GUIMain.get_numRows() - 1 || row == 0) && (col == GUIMain.get_numCols() - 1 || col == 0)) {
            return 2;
        } else if ((row == 0 || row == GUIMain.get_numRows() - 1) || (col == GUIMain.get_numCols() - 1 || col == 0)) {
            return 3;
        } else {
            return 4;
        }

    }

    /**
     * An adapter, which, given 2-D coordinates of a {@link StackPane}, returns the
     * 1-D coordinate it would have in the <code>children</code> {@link ArrayList} of its
     * parent {@link GridPane}.
     *
     * Used because we did not want to maintain the index of the clicked {@link StackPane} as
     * a separate variable.
     *
     * It was easy to calculate this since we had the access to number of columns in the grid
     * from <code>GUIMain.get_numCols()</code>. An addition of <code>1</code> needs to be
     * done to the answer because indexing of <code>StackPanes</code> in the <code>children</code>
     * ArrayList of the GridPane started from 1, not 0;
     *
     * @param row row number of the coordinates to be converted
     * @param col column number of the coordinates to be converted
     * @return an integer which gives the 1-D coordinate
     */
    public int getIndexOfStackPaneFromCoords(int row, int col) {

        int ans = 0;

        if (row == 0) {
            ans = col;
        } else {
            ans = (row) * GUIMain.get_numCols() + col;
        }

        return ans + 1;

    }


    /**
     * This method handles the manipulation of spheres in the clicked {@link StackPane}.
     *
     * First, it obtains the 1-D index of the clicked <code>StackPane</code> and adds an index to it.
     * Then, it evaluates whether the <code>StackPane</code> has reached its critical mass.
     *
     * If yes, it clears the <code>StackPane</code> and calls the <code>handleAnimatio()_</code> function
     * which handles the explosion animations. If no, then it simply calls the <code>afterTurn()</code>
     * function.
     *
     * @param row row number of the clicked <code>StackPane</code>
     * @param col column number of the clicked <code>StackPane</code>
     * @param gp <code>GridPane</code> object that represents the GUI grid
     */
    public void handleTurn(int row, int col, GridPane gp) {



        StackPane sp = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row, col));
        Group cellGroup = (Group) sp.getChildren().get(0);

        int currMass = cellGroup.getChildren().size();

        GUIMain.addOrbAndAnimate(gp, row, col, currMass + 1, Color.web(currentColorHEX));



        if (currMass + 1 == get_CritMass(row, col)) {


            GUIMain.addOrbAndAnimate(gp, row, col, 0, Color.web(currentColorHEX));


            handleAnimation(row, col, gp);


        } else {


            Stage stage = (Stage) gp.getScene().getWindow();
            ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();

            afterTurn(gp, stage, players);
        }




    }

    /**
     * This method handles the explosion animation in the game.
     *
     * In case the cell has equalled it's critical mass, it must explode, with
     * appropriate transitions. This is taken care of by this function.
     *
     * It creates four new spheres and adds them to the <code>StackPane</code> that is to explode.
     * These new <code>Sphere</code> objects have {@link TranslateTransition} attached to them. The
     * {@link TranslateTransition} objects are also attached to a {@link ParallelTransition} object.
     * Once these additions are done, it plays the {@link ParallelTransition} object.
     *
     * The function also re-calls the <code>handleTurn</code> method on the valid neighbours of the
     * clicked {@link StackPane}.
     *
     * @param row row number of the exploded cell
     * @param col column number of the exploded cell
     * @param gp {@link GridPane} representing the GUI grid
     */
    public void handleAnimation(int row, int col, GridPane gp) {


        ParallelTransition p = new ParallelTransition();

        StackPane sp = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row, col));
        Group cellGroup = (Group) sp.getChildren().get(0);

        PhongMaterial nph;



        if (areValidCoord(row - 1, col)) {

            if (gp.getChildren().size() == 151) {
                Sphere ns = new Sphere(7.5);
                nph = new PhongMaterial(Color.web(currentColorHEX));
                ns.setMaterial(nph);
                sp.getChildren().add(ns);


                TranslateTransition nt = new TranslateTransition(Duration.millis(700), ns);
                nt.setOnFinished(e -> {
                    sp.getChildren().remove(ns);
                });
                nt.setCycleCount(1);
                nt.setAutoReverse(false);
                nt.setToY(-45);
                nt.setToX(0);
                nt.setFromY(0);
                nt.setFromX(0);
                p.getChildren().add(nt);

            } else {

                Sphere ns = new Sphere(12);
                nph = new PhongMaterial(Color.web(currentColorHEX));
                ns.setMaterial(nph);
                sp.getChildren().add(ns);


                TranslateTransition nt = new TranslateTransition(Duration.millis(700), ns);
                nt.setOnFinished(e -> {
                    sp.getChildren().remove(ns);
                });
                nt.setCycleCount(1);
                nt.setAutoReverse(false);
                nt.setToY(-80);
                nt.setToX(0);
                nt.setFromY(0);
                nt.setFromX(0);
                p.getChildren().add(nt);
            }

        }



        if (areValidCoord(row, col - 1)) {

            if (gp.getChildren().size() == 151) {
                Sphere ns = new Sphere(7.5);
                nph = new PhongMaterial(Color.web(currentColorHEX));
                ns.setMaterial(nph);
                sp.getChildren().add(ns);


                TranslateTransition nt = new TranslateTransition(Duration.millis(700), ns);
                nt.setOnFinished(e -> {
                    sp.getChildren().remove(ns);
                });
                nt.setCycleCount(1);
                nt.setAutoReverse(false);
                nt.setToY(0);
                nt.setToX(-45);
                nt.setFromY(0);
                nt.setFromX(0);
                p.getChildren().add(nt);

            } else {

                Sphere ns = new Sphere(12);
                nph = new PhongMaterial(Color.web(currentColorHEX));
                ns.setMaterial(nph);
                sp.getChildren().add(ns);


                TranslateTransition nt = new TranslateTransition(Duration.millis(700), ns);
                nt.setOnFinished(e -> {
                    sp.getChildren().remove(ns);
                });
                nt.setCycleCount(1);
                nt.setAutoReverse(false);
                nt.setToY(0);
                nt.setToX(-80);
                nt.setFromY(0);
                nt.setFromX(0);
                p.getChildren().add(nt);
            }

        }


        if (areValidCoord(row, col + 1)) {

            if (gp.getChildren().size() == 151) {
                Sphere ns = new Sphere(7.5);
                nph = new PhongMaterial(Color.web(currentColorHEX));
                ns.setMaterial(nph);
                sp.getChildren().add(ns);

                TranslateTransition nt = new TranslateTransition(Duration.millis(700), ns);
                nt.setOnFinished(e -> {
                    sp.getChildren().remove(ns);
                });
                nt.setCycleCount(1);
                nt.setAutoReverse(false);
                nt.setToY(0);
                nt.setToX(45);
                nt.setFromY(0);
                nt.setFromX(0);
                p.getChildren().add(nt);

            } else {

                Sphere ns = new Sphere(12);
                nph = new PhongMaterial(Color.web(currentColorHEX));
                ns.setMaterial(nph);
                sp.getChildren().add(ns);

                TranslateTransition nt = new TranslateTransition(Duration.millis(700), ns);
                nt.setOnFinished(e -> {
                    sp.getChildren().remove(ns);
                });
                nt.setCycleCount(1);
                nt.setAutoReverse(false);
                nt.setToY(0);
                nt.setToX(80);
                nt.setFromY(0);
                nt.setFromX(0);
                p.getChildren().add(nt);
            }

        }


        if (areValidCoord(row + 1, col)) {

            if (gp.getChildren().size() == 151) {
                Sphere ns = new Sphere(7.5);
                nph = new PhongMaterial(Color.web(currentColorHEX));
                ns.setMaterial(nph);
                sp.getChildren().add(ns);

                TranslateTransition nt = new TranslateTransition(Duration.millis(700), ns);
                nt.setOnFinished(e -> {
                    sp.getChildren().remove(ns);
                });
                nt.setCycleCount(1);
                nt.setAutoReverse(false);
                nt.setToY(45);
                nt.setToX(0);
                nt.setFromY(0);
                nt.setFromX(0);
                p.getChildren().add(nt);

            } else {

                Sphere ns = new Sphere(12);
                nph = new PhongMaterial(Color.web(currentColorHEX));
                ns.setMaterial(nph);
                sp.getChildren().add(ns);

                TranslateTransition nt = new TranslateTransition(Duration.millis(700), ns);
                nt.setOnFinished(e -> {
                    sp.getChildren().remove(ns);
                });
                nt.setCycleCount(1);
                nt.setAutoReverse(false);
                nt.setToY(80);
                nt.setToX(0);
                nt.setFromY(0);
                nt.setFromX(0);
                p.getChildren().add(nt);
            }

        }

        p.setOnFinished(o -> {

            checkAlivePlayers(fetchCurrentPlayer());


            if (areValidCoord(row - 1, col)) {
                StackPane sp1 = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row - 1, col));
                Group cellGroup1 = (Group) sp1.getChildren().get(0);

                handleTurn(row - 1, col, gp);

            }

            if (areValidCoord(row + 1, col)) {
                StackPane sp1 = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row + 1, col));
                Group cellGroup1 = (Group) sp1.getChildren().get(0);

                handleTurn(row + 1, col, gp);

            }

            if (areValidCoord(row, col - 1)) {
                StackPane sp1 = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row, col - 1));
                Group cellGroup1 = (Group) sp1.getChildren().get(0);

                handleTurn(row, col - 1, gp);

            }

            if (areValidCoord(row, col + 1)) {
                StackPane sp1 = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row, col + 1));
                Group cellGroup1 = (Group) sp1.getChildren().get(0);

                handleTurn(row, col + 1, gp);

            }


        });

        p.play();


    }

    /**
     * This method is called when a recursion base case is reached, i.e., a
     * cell suffers addition but does not explode.
     *
     * This function makes the changes that must happen at the end of a turn, such as serializing the
     * <code>GameState</code>, checking for end game conditions, updating the active and alive statuses
     * of the players.
     *
     * @param grid {@link GridPane} that represents the GUI grid
     * @param stage {@link Stage} of the application
     * @param players the list of current players
     */
    public void afterTurn(GridPane grid, Stage stage, ArrayList<Player> players) {


        fetchCurrentPlayer().set_isKillable(true);


        convertGUItoGrid(grid, grid_put);
        GUIMain.get_gameEngine().get_gc().set_grid(grid_put);
        GUIMain.get_gameEngine().get_gc().set_players(players_put);
        checkAlivePlayers(fetchCurrentPlayer());

        if (!GUIMain.checkEndGame()) {
            fetchCurrentPlayer().set_isActive(false);
            checkAlivePlayers(fetchCurrentPlayer());
            Player nextPlayer = fetchNextPlayer(currentColorHEX);

            GUIMain.changeGridColor(grid, Color.web(nextPlayer.get_colour()));

            nextPlayer.set_isActive(true);
            GUIMain.setCurrentPlayer(nextPlayer.get_colour());

        } else {
            int num = -1;

            for (int i = 0; i < players_put.size(); i++) {
                if (players_put.get(i).get_isAlive()) {
                    num = i + 1;
                    break;
                }
            }


            try {

                File file_game = new File("game.ser");
                File file_undo = new File("undo.ser");

                try {
                    if (file_game.delete() && file_undo.delete()) {
                        GUIMain.createEndPage(grid, stage, num);
                        GUIMain.getRedoButton().setDisable(true);
                    }
                } catch (Exception y) {
                    y.printStackTrace();
                }


            } catch (NullPointerException n) {
                n.printStackTrace();
            }


        }
    }

}
