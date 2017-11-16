package GUIEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.PopOver;

public class instructionsButtonGUI implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e){
        Button source = (Button) e.getSource();
        ScrollPane scp = new ScrollPane();
        StackPane stp = new StackPane();
        scp.setPrefSize(600,300);
        Label lb = new Label("1. The gameplay takes place in an  board. The size of the board is 9x6 or 15x10.\n" +
                "2. For each cell in the board, we define a critical mass. The critical mass is equal to the number of orthogonally adjacent cells.\n" +
                "   That would be 4 for usual cells, 3 for cells in the edge and 2 for cells in the corner.\n" +
                "3. All cells are initially empty. The players take turns to place \"orbs\" of their corresponding colors.\n" +
                "   The players can only place an orb in an empty cell or a cell which already contains one or more orbs of their color.\n" +
                "   When two or more orbs are placed in the same cell, they stack up.\n" +
                "4. When a cell is loaded with a number of orbs equal to its critical mass, the stack immediately explodes.\n" +
                "   As a result of the explosion, to each of the orthogonally adjacent cells, an orb is added and the initial cell looses as many orbs as its critical mass.\n" +
                "   The explosions might result in overloading of an adjacent cell and the chain reaction of explosion continues until every cell is stable.\n" +
                "5. When a player's cell explodes and there are enemy cells around, the enemy cells are converted to the player's color and the other rules of explosions still follow.\n" +
                "   The same rule is applicable for other colors.\n" +
                "6. The winner is the one who eliminates every other player's orbs.\n");
        stp.getChildren().add(lb);
//        scp.setFitToHeight(true);
//        scp.setFitToWidth(true);
        scp.setContent(stp);
        scp.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        PopOver popOver = new PopOver(source);
        //popOver.setHeight(50);
        //popOver.setWidth(50);
        popOver.setPrefSize(2, 2);
        popOver.setDetachable(true);
        popOver.setDetached(true);
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        popOver.setContentNode(scp);
        popOver.setTitle("Instructions");
        popOver.show(source);
    }
}
