package GUIEngine;

import GameEngine.GameController;
import GameEngine.Grid;
import GameEngine.GameEngine;
import GameEngine.Player;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

class turnGUI implements EventHandler<MouseEvent> {


    private String currentColorHEX;
    private Grid grid_put;
    private ArrayList<Player> players_put;
    boolean play_complete = false;
    ParallelTransition p = new ParallelTransition();
    long startTime;
    long endTime;
    //SequentialTransition st = new SequentialTransition();



    @Override
    public void handle(MouseEvent e) {

        if (!GUIMain.checkEndGame()) {

            ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();
            int numPlayers = GUIMain.get_gameEngine().get_numPlayers();

//        GUIMain.get_gameEngine().get_gc().set_grid(grid_put);
//        GUIMain.get_gameEngine().get_gc().set_players(players_put);


            currentColorHEX = GUIMain.getCurrentPlayer();


            //System.out.println("Mouse click detected");


            StackPane source = (StackPane) e.getSource();
            GridPane grid = (GridPane) source.getParent();
            Stage stage = (Stage) grid.getScene().getWindow();

            //for Undo state
            players_put = GUIMain.get_gameEngine().get_gc().get_players();
            //System.out.println("here" + players_put.size());
            grid_put = new Grid(GUIMain.get_gameEngine().get_gridSize());
            convertGUItoGrid(grid, grid_put);
//            GUIMain.get_gameEngine().get_gc().set_grid(grid_put);
//            GUIMain.get_gameEngine().get_gc().set_players(players_put);
            try {
                GUIMain.get_gameEngine().get_gc().saveUndoState();
            } catch (Exception e2) {
                e2.printStackTrace();
            }


            //System.out.println(grid.getChildren().indexOf(source));
            GameController.set_index(grid.getChildren().indexOf(source) - 1);
            // gives row and column
            int[] index = GameController.convert_index(GUIMain.get_numCols());
            System.out.println(Integer.toString(index[0]) + " " + Integer.toString(index[1]));
            Group cellGroup = (Group) source.getChildren().get(0);


            Sphere s = (cellGroup.getChildren().size() == 0) ? null : (Sphere) cellGroup.getChildren().get(0);
            PhongMaterial ph = (s == null) ? null : (PhongMaterial) s.getMaterial();
            Color clr = (ph == null) ? null : ph.getDiffuseColor();


            String oldColor = clr == null ? null : ColorUtil.colorToHex(clr);

            if (oldColor == null || oldColor.equals(currentColorHEX)) {

                players_put = GUIMain.get_gameEngine().get_gc().get_players();

                grid_put = new Grid(GUIMain.get_gameEngine().get_gridSize());


                System.out.println("Existing color: " + oldColor);
                System.out.println("Given color: " + currentColorHEX);
                //System.out.println("code should work");

                handleTurn(index[0], index[1], grid);

                //afterAnimation(grid, stage, players);




               //////////----------


            } else {
                System.out.println("Existing color: " + oldColor);
                System.out.println("Given color: " + currentColorHEX);
                //System.out.println("code for sound effects");

                GUIMain.playError();
            }


            try {
                GUIMain.get_gameEngine().get_gc().saveGameState();
            } catch (Exception e2) {
                e2.printStackTrace();
            }


            GUIMain.getRedoButton().setDisable(false);



        }


    }

    public void convertGUItoGrid(GridPane gp, Grid g){
        ObservableList<Node> GUIlist = gp.getChildren();
        //System.out.println("GUIlist.size() - "+ GUIlist.size());
        for (int i = 1; i < GUIlist.size(); i++) {
            StackPane cell = (StackPane) GUIlist.get(i);
            Group cell_grp = (Group) cell.getChildren().get(0);
            Sphere s = (cell_grp.getChildren().size() == 0) ? null : (Sphere) cell_grp.getChildren().get(0);
            PhongMaterial ph = (s == null) ? null : (PhongMaterial) s.getMaterial();
            Color clr = (ph == null) ? null : ph.getDiffuseColor();

            int x = (i-1)/GUIMain.get_numCols();
            int y = (i-1)%GUIMain.get_numCols();

//            System.out.println("x - " + x);
//            System.out.println("y - " + y);
//
//            System.out.println("grid row num - " + g.get_grid().size());
//            System.out.println("grid col num - " + g.get_grid().get(0).size());

            g.get_grid().get(x).get(y).set_currmass(cell_grp.getChildren().size());
            g.get_grid().get(x).get(y).set_color((clr == null) ? null : ColorUtil.colorToHex(clr));

        }
    }



    public Player fetchNextPlayer(String currentColor){

        ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();
        int numPlayers = GUIMain.get_gameEngine().get_numPlayers();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).get_colour().equals(currentColor)){
               int j=(i+1)%numPlayers;
               //aaj mai bathroom me phasa reh gaya
               while (i!=j){
                   if (players.get(j).get_isAlive()) {
                       return players.get(j);
                   }
                   else {
                       j = (j+1)%numPlayers;
                   }
               }
            }

        }
        return null;
    }

    public void checkAlivePlayers(Player p){
        ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();

        System.out.println("checkAlivePlayers size of payers array - " + players.size());
        Grid g = GUIMain.get_gameEngine().get_gc().get_grid();
        System.out.println("Current player color - "+ p.get_colour());
        for (int i = 0; i < players.size(); i++) {
            int count=0;
            if (!players.get(i).equals(p)) {
                System.out.println("inside the loop. Color - " + players.get(i).get_colour());
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

    public Player fetchCurrentPlayer(){

        ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();
        int numPlayers = GUIMain.get_gameEngine().get_numPlayers();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).get_colour().equals(currentColorHEX)){
                return players.get(i);
            }

        }
        return null;

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




    public void handleTurn(int row, int col, GridPane gp){


        StackPane sp = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row, col));
        Group cellGroup = (Group) sp.getChildren().get(0);

        int currMass = cellGroup.getChildren().size();

        GUIMain.addOrbAndAnimate(gp, row, col, currMass + 1, Color.web(currentColorHEX));


        //convertGUItoGrid(grid, grid_put);
        GUIMain.playExplode();
        if (currMass + 1 == get_CritMass(row, col)) {

            //startTime = System.currentTimeMillis();
            GUIMain.addOrbAndAnimate(gp, row, col, 0, Color.web(currentColorHEX));
            //convertGUItoGrid(grid, grid_put);

            handleAnimation(row, col, gp);

        }
        else {
            //GridPane grid = (GridPane) sp.getParent();
            Stage stage = (Stage) gp.getScene().getWindow();
            ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();


            //endTime = System.currentTimeMillis();
            afterAnimation(gp, stage, players);
        }


    }

    public void handleAnimation(int row, int col, GridPane gp){


        p = new ParallelTransition();

        StackPane sp = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row, col));
        Group cellGroup = (Group) sp.getChildren().get(0);


        int currMass = cellGroup.getChildren().size();

        int critMass = get_CritMass(row, col);

        PhongMaterial nph;

        //Adding spheres to stackpane and animating to valid neighbours. STEP 1 - VERBOSE


        //TOP

        if (areValidCoord(row-1,col)){
            Sphere ns = new Sphere(7.5);
            nph = new PhongMaterial(Color.web(currentColorHEX));
            ns.setMaterial(nph);
            sp.getChildren().add(ns);

            TranslateTransition nt = new TranslateTransition(Duration.millis(700),ns);
            nt.setCycleCount(1);
            nt.setAutoReverse(false);
            nt.setToY(-45);
            nt.setToX(0);
            nt.setFromY(0);
            nt.setFromX(0);
            p.getChildren().add(nt);

        }

        //LEFT

        if (areValidCoord(row,col-1)){
            Sphere ns = new Sphere(7.5);
            nph = new PhongMaterial(Color.web(currentColorHEX));
            ns.setMaterial(nph);
            sp.getChildren().add(ns);


            TranslateTransition nt = new TranslateTransition(Duration.millis(700),ns);
            nt.setCycleCount(1);
            nt.setAutoReverse(false);
            nt.setToY(0);
            nt.setToX(-45);
            nt.setFromY(0);
            nt.setFromX(0);
            p.getChildren().add(nt);

        }

        //RIGHT

        if (areValidCoord(row,col+1)){
            Sphere ns = new Sphere(7.5);
            nph = new PhongMaterial(Color.web(currentColorHEX));
            ns.setMaterial(nph);
            sp.getChildren().add(ns);

            TranslateTransition nt = new TranslateTransition(Duration.millis(700),ns);
            nt.setCycleCount(1);
            nt.setAutoReverse(false);
            nt.setToY(0);
            nt.setToX(45);
            nt.setFromY(0);
            nt.setFromX(0);
            p.getChildren().add(nt);

        }

        //BOTTOM

        if (areValidCoord(row+1,col)){
            Sphere ns = new Sphere(7.5);
            nph = new PhongMaterial(Color.web(currentColorHEX));
            ns.setMaterial(nph);
            sp.getChildren().add(ns);

            TranslateTransition nt = new TranslateTransition(Duration.millis(700),ns);
            nt.setCycleCount(1);
            nt.setAutoReverse(false);
            nt.setToY(45);
            nt.setToX(0);
            nt.setFromY(0);
            nt.setFromX(0);
            p.getChildren().add(nt);

        }

        p.setOnFinished(o->{

            //p.getChildren().removeAll();

            //Just an extra POS
            checkAlivePlayers(fetchCurrentPlayer());


            if (areValidCoord(row-1,col)){
                StackPane sp1 = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row-1, col));
                Group cellGroup1 = (Group) sp1.getChildren().get(0);

                int currMass1 = cellGroup1.getChildren().size();

                handleTurn(row-1, col, gp);

            }

            if (areValidCoord(row+1,col)){
                StackPane sp1 = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row+1, col));
                Group cellGroup1 = (Group) sp1.getChildren().get(0);

                int currMass1 = cellGroup1.getChildren().size();

                handleTurn(row+1, col, gp);

            }

            if (areValidCoord(row,col-1)){
                StackPane sp1 = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row, col-1));
                Group cellGroup1 = (Group) sp1.getChildren().get(0);

                int currMass1 = cellGroup1.getChildren().size();

                handleTurn(row, col-1, gp);

            }

            if (areValidCoord(row,col+1)){
                StackPane sp1 = (StackPane) gp.getChildren().get(getIndexOfStackPaneFromCoords(row, col+1));
                Group cellGroup1 = (Group) sp1.getChildren().get(0);

                int currMass1 = cellGroup1.getChildren().size();

                handleTurn(row, col+1, gp);

            }

            //GridPane grid = (GridPane) sp.getParent();
//            Stage stage = (Stage) gp.getScene().getWindow();
//            ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();
//            afterAnimation(gp, stage, players);



            sp.getChildren().remove(1, sp.getChildren().size());
        });

        p.play();
        //p.getChildren().removeAll();


    }

    public void afterAnimation(GridPane grid, Stage stage, ArrayList<Player> players ){


        fetchCurrentPlayer().set_isKillable(true);


        convertGUItoGrid(grid, grid_put);
        GUIMain.get_gameEngine().get_gc().set_grid(grid_put);
        GUIMain.get_gameEngine().get_gc().set_players(players_put);
        checkAlivePlayers(fetchCurrentPlayer());

        if (!GUIMain.checkEndGame()) {
            fetchCurrentPlayer().set_isActive(false);
            Player nextPlayer = fetchNextPlayer(currentColorHEX);

//                    while(true) {
//                        if(play_complete == true) {
            GUIMain.changeGridColor(grid, Color.web(nextPlayer.get_colour()));
//                            break;
//                        }
//                    }
//                    if (p.getChildren().size()==0) {
//                        p.setOnFinished(u -> {
//                            GUIMain.changeGridColor(grid, Color.web(nextPlayer.get_colour()));
//                        });
//
//                        GUIMain.changeGridColor(grid, Color.web(nextPlayer.get_colour()));
//                    }
//                    else{
//                        GUIMain.changeGridColor(grid, Color.web((nextPlayer.get_colour())));
//                    }

//                    p.setOnFinished(u->{
//                        GUIMain.changeGridColor(grid, Color.web(nextPlayer.get_colour()));
//                    });

//                    while (true){
//                        if (p.getChildren().size()==0){
//                            GUIMain.changeGridColor(grid, Color.web(nextPlayer.get_colour()));
//                            break;
//
//                        }
//                    }




            nextPlayer.set_isActive(true);
            GUIMain.setCurrentPlayer(nextPlayer.get_colour());

        }
        else{
            int num = -1;

            for (int i = 0; i < players_put.size(); i++) {
                if (players_put.get(i).get_isAlive()){
                    num = i+1;
                    break;
                }
            }

            System.out.println("huieuhfieufghieufgeiufgie");

            try {
                stage.setScene(GUIMain.createEndPage(grid, stage, num));
            }
            catch (NullPointerException n){
                System.out.println("Out of the Game");
            }


//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Game Over!");
//            alert.setHeaderText("Player "+num+" has won the game!");
//            alert.setContentText(null);
//            alert.initOwner(stage);
//            alert.setGraphic(null);
//
//
//            ButtonType buttonTypeOne = new ButtonType("Start Again");
//            ButtonType buttonTypeTwo = new ButtonType("Exit");
//
//
//            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
//
//
//
//
//            Platform.runLater(alert::showAndWait);
//
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.get() == buttonTypeOne) {
//
//
//                File file_game = new File("game.ser");
//                File file_undo = new File("undo.ser");
//
//                try {
//
//                    if (file_game.delete() && file_undo.delete()) {
//
//
//                        for (int i = 0; i < players.size(); i++) {
//                            players.get(i).set_isAlive(true);
//                            players.get(i).set_isKillable(false);
//                            players.get(i).set_isActive(false);
//                        }
//
//                        players.get(0).set_isActive(true);
//                        players.get(0).set_isKillable(true);
//
//                        Color firstColor = Color.web(players.get(0).get_colour());
//
//                        for (int i = 0; i < GUIMain.get_numRows(); i++) {
//                            for (int j = 0; j < GUIMain.get_numCols(); j++) {
//
//                                GUIMain.addOrbAndAnimate(grid, i, j, 0, firstColor);
//                                GUIMain.get_gameEngine().get_gc().get_grid().get_grid().get(i).get(j).set_currmass(0);
//
//
//                            }
//                        }
//
//                        GUIMain.changeGridColor(grid, firstColor);
//
//                        GUIMain.setCurrentPlayer(ColorUtil.colorToHex(firstColor));
//                        GUIMain.getRedoButton().setDisable(true);
//                        GUIMain.getRedoButton().setDisable(true);
//
//                    }
//                } catch (Exception v) {
//                    v.printStackTrace();
//                }
//            }
//            else {
//                try {
//                    File file_game = new File("game.ser");
//
//                    File file_undo = new File("undo.ser");
//
//                    if (file_game.delete() && file_undo.delete()) {
//
//                        stage.setScene(GUIMain.createStartPage());
//                    }
//
//                }
//                catch (Exception o){
//                    o.printStackTrace();
//                }
//            }
        }
    }

}
