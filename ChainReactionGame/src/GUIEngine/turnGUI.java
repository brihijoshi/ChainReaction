package GUIEngine;

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

class turnGUI implements EventHandler<MouseEvent> {



    private String currentColorHEX;
    private Grid grid_put;
    private ArrayList<Player> players_put;


    public String getCurrentColorHEX() {
        return currentColorHEX;
    }

    public void setCurrentColorHEX(String currentColorHEX) {
        this.currentColorHEX = currentColorHEX;
    }

    public Grid getGrid_put() {
        return grid_put;
    }

    public void setGrid_put(Grid grid_put) {
        this.grid_put = grid_put;
    }

    public ArrayList<Player> getPlayers_put() {
        return players_put;
    }

    public void setPlayers_put(ArrayList<Player> players_put) {
        this.players_put = players_put;
    }


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





            } else {
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

    public boolean areValidCoord(int row, int col) {
        if (row < GUIMain.get_numRows() && row >= 0 && col < GUIMain.get_numCols() && col >= 0) {
            return true;
        }
        return false;
    }

    public int get_CritMass(int row, int col) {

        if ((row == GUIMain.get_numRows() - 1 || row == 0) && (col == GUIMain.get_numCols() - 1 || col == 0)) {
            return 2;
        } else if ((row == 0 || row == GUIMain.get_numRows() - 1) || (col == GUIMain.get_numCols() - 1 || col == 0)) {
            return 3;
        } else {
            return 4;
        }

    }

    public int getIndexOfStackPaneFromCoords(int row, int col) {

        int ans = 0;

        if (row == 0) {
            ans = col;
        } else {
            ans = (row) * GUIMain.get_numCols() + col;
        }

        return ans + 1;

    }


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

            afterAnimation(gp, stage, players);
        }


    }

    public void handleAnimation(int row, int col, GridPane gp) {


        ParallelTransition p = new ParallelTransition();

        StackPane sp = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row, col));
        Group cellGroup = (Group) sp.getChildren().get(0);


        int currMass = cellGroup.getChildren().size();

        int critMass = get_CritMass(row, col);

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

    public void afterAnimation(GridPane grid, Stage stage, ArrayList<Player> players) {


        fetchCurrentPlayer().set_isKillable(true);


        convertGUItoGrid(grid, grid_put);
        GUIMain.get_gameEngine().get_gc().set_grid(grid_put);
        GUIMain.get_gameEngine().get_gc().set_players(players_put);
        checkAlivePlayers(fetchCurrentPlayer());

        if (!GUIMain.checkEndGame()) {
            fetchCurrentPlayer().set_isActive(false);
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
