package GUIEngine;

import GameEngine.GameEngine;
import GameEngine.Grid;
import GameEngine.Player;
import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.DoubleUnaryOperator;


public class GUIMain extends Application {


    // enable choice in grids and for index conversion
    private static int _numRows;
    private static int _numCols;

    private static GameEngine _gameEngine;


    private static Button startButton;
    private static Button resumeButton;
    private static Button settingsButton;
    private static Button saveButton;



    private static Button redoButton;

    private static Button homeButton; //Made it to specifically return to the home page
    private static ComboBox numPlayersCB;
    private static ComboBox gridChoiceCB;

    private static ColorPicker player_1 = new ColorPicker(Color.RED);
    private static ColorPicker player_2 = new ColorPicker(Color.BLUE);
    private static ColorPicker player_3 = new ColorPicker(Color.GREEN);
    private static ColorPicker player_4 = new ColorPicker(Color.YELLOW);
    private static ColorPicker player_5 = new ColorPicker(Color.ORANGE);
    private static ColorPicker player_6 = new ColorPicker(Color.PINK);
    private static ColorPicker player_7 = new ColorPicker(Color.BROWN);
    private static ColorPicker player_8 = new ColorPicker(Color.PURPLE);

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        GUIMain.mediaPlayer = mediaPlayer;
    }

    private static MediaPlayer mediaPlayer;

    private static ArrayList<ColorPicker> array_CP = new ArrayList<>(8);




    private static String currentPlayer;

    private static HashMap<Integer, String> playercolor;


    public static int get_numRows() {
        return _numRows;
    }

    public static int get_numCols() {
        return _numCols;
    }

    public static Button getStartButton() {
        return startButton;
    }

    public static Button getResumeButton() {
        return resumeButton;
    }

    public static Button getSettingsButton() {
        return settingsButton;
    }

    public static Button getSaveButton() {
        return saveButton;
    }

    public static Button getHomeButton() {
        return homeButton;
    }

    public static Button getRedoButton() {
        return redoButton;
    }

    public static void setRedoButton(Button redoButton) {
        GUIMain.redoButton = redoButton;
    }

    public static void setHomeButton(Button homeButton) {
        GUIMain.homeButton = homeButton;
    }


    public static ComboBox getNumPlayersCB() {
        return numPlayersCB;
    }

    public static ComboBox getGridChoiceCB() {
        return gridChoiceCB;
    }

    public static ColorPicker getPlayer_1() {
        return player_1;
    }

    public static void setPlayer_1(ColorPicker player_1) {
        GUIMain.player_1 = player_1;
    }

    public static ColorPicker getPlayer_2() {
        return player_2;
    }

    public static void setPlayer_2(ColorPicker player_2) {
        GUIMain.player_2 = player_2;
    }

    public static ColorPicker getPlayer_3() {
        return player_3;
    }

    public static void setPlayer_3(ColorPicker player_3) {
        GUIMain.player_3 = player_3;
    }

    public static ColorPicker getPlayer_4() {
        return player_4;
    }

    public static void setPlayer_4(ColorPicker player_4) {
        GUIMain.player_4 = player_4;
    }

    public static ColorPicker getPlayer_5() {
        return player_5;
    }

    public static void setPlayer_5(ColorPicker player_5) {
        GUIMain.player_5 = player_5;
    }

    public static ColorPicker getPlayer_6() {
        return player_6;
    }

    public static void setPlayer_6(ColorPicker player_6) {
        GUIMain.player_6 = player_6;
    }

    public static ColorPicker getPlayer_7() {
        return player_7;
    }

    public static void setPlayer_7(ColorPicker player_7) {
        GUIMain.player_7 = player_7;
    }

    public static ColorPicker getPlayer_8() {
        return player_8;
    }

    public static void setPlayer_8(ColorPicker player_8) {
        GUIMain.player_8 = player_8;
    }


    public static String getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(String currentPlayer) {
        GUIMain.currentPlayer = currentPlayer;
    }

    public static HashMap<Integer, String> getPlayercolor() {
        return playercolor;
    }

    public static void setPlayercolor(HashMap<Integer, String> playercolor) {
        GUIMain.playercolor = playercolor;
    }


    public static GameEngine get_gameEngine() {
        return _gameEngine;
    }

    public static void set_gameEngine(GameEngine gameEngine) {
        _gameEngine = gameEngine;
    }

    public static ArrayList<ColorPicker> getArray_CP() {
        return array_CP;
    }

    public static void setArray_CP(ArrayList<ColorPicker> array_CP) {
        GUIMain.array_CP = array_CP;
    }




    // for help on transitions : https://gist.github.com/jewelsea/1475424

    public static BorderPane setEmptyGrid(GridPane root, int numRows, int numColumns, Color color){

        BorderPane bp = new BorderPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(5);
        root.setVgap(5);
        root.setGridLinesVisible(true);



        RowConstraints rc = new RowConstraints(80);
        rc.setMaxHeight(45);
        //rc.setPercentHeight(30);
        rc.setVgrow(Priority.ALWAYS);
        ColumnConstraints cc = new ColumnConstraints(80);
        cc.setMaxWidth(45);
        //cc.setPercentWidth(30);
        cc.setHgrow(Priority.ALWAYS);

        for(int i=0;i<numRows;i++){
            root.getRowConstraints().add(rc);

        }

        for(int j=0;j<numColumns;j++) {
            root.getColumnConstraints().add(cc);
        }
        for(int i=0;i<numRows;i++){
            for(int j=0;j<numColumns;j++){

                StackPane cellContainer = new StackPane();
                cellContainer.setBorder(makeBorder(color));
                cellContainer.setOnMouseClicked(new turnGUI());
                Group cell = new Group();
                cell.setPickOnBounds(false);
                StackPane.setMargin(cell, new Insets(2,2,2,2));
                cellContainer.getChildren().add(cell);
                root.add(cellContainer, j,i);

            }
        }

        bp.setCenter(root);

        MenuButton dd = new MenuButton("Options");
        dd.getItems().addAll(new MenuItem("Restart"), new MenuItem("Exit"));
        dd.getItems().get(0).setOnAction(new restartButtonGUI());
        dd.getItems().get(1).setOnAction(new exitButtonGUI());
        dd.setStyle("-fx-font-size: 20px; -fx-background-color: whitesmoke; -fx-border-color: white; -fx-text-fill: white;");
        //dd.se

        bp.setTop(dd);

        redoButton = new Button("Undo");
        redoButton.setStyle("-fx-background-color: fuchsia; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold;");
        redoButton.setOnAction(new undoButtonGUI());

//        try {
//            System.out.println(GameEngine.checkUndo());
//            if (!GameEngine.checkUndo()){
//
//                redoButton.setDisable(true);
//            }
//            else{
//                redoButton.setDisable(false);
//            }
//        }
//        catch (Exception v){
//            v.printStackTrace();
//        }

        redoButton.setDisable(true);

        bp.setRight(redoButton);
        bp.setStyle("-fx-background-color: black;");
        return bp;

    }

    public static Border makeBorder(Color color){
        // changes colour of the stackpane
        return new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    }

    public static void changeGridColor(GridPane grid, Color color){
        //To be done when we store the Array of the grid objects so that we can retrieve the current orbs and then change the colour

        ObservableList<Node> cells = grid.getChildren();
        for (int i = 1; i < cells.size(); i++) {
            cells.get(i).setStyle("-fx-border-color: #" + color.toString().substring(2));
        }


    }
    public static void playExplode(){
        String musicFile = "assets/explode.mp3";     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        //mediaPlayer.setVolume(200);
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void playError(){
        String musicFile = "assets/error.mp3";     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
       // mediaPlayer.setVolume(200);
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }


    public static void addOrbAndAnimate(GridPane root, int row, int column, int numSpheres, Color color){
        //To be done when we store the Array of the grid objects so that we can retrieve the current orbs and then replace it
        //To test it on the present situation only
        int size = root.getChildren().size();
        int rowsize=0;

        if ( size == 55 ){
            rowsize=6;
        }
        else{
            rowsize=10;
        }

        int removal = ( row * rowsize ) + column + 1;

        StackPane cellContainer = new StackPane();
        cellContainer.setBorder(makeBorder(color));
        cellContainer.setOnMouseClicked(new turnGUI());
        Group cell = new Group();
        cell.setPickOnBounds(false);


        //To set the Sphere color
        PhongMaterial smaterial = new PhongMaterial();
        smaterial.setDiffuseColor(color);



        switch (numSpheres){
            case 1:

                Sphere a = new Sphere(12);
                a.setMaterial(smaterial);
                cell.getChildren().addAll(a);
                break;

            case 2:

                a = new Sphere(12);
                Sphere b = new Sphere(12);

                b.setTranslateX(12);
                b.setTranslateZ(12);

                a.setMaterial(smaterial);
                b.setMaterial(smaterial);
                cell.getChildren().addAll(a, b);

                rotateOrbs(cell);

                break;

            case  3:

                a = new Sphere(12);
                b = new Sphere(12);
                Sphere c = new Sphere(12);

                b.setTranslateX(12);
                b.setTranslateZ(12);
                c.setTranslateX(6);
                c.setTranslateY(-12);

                a.setMaterial(smaterial);
                b.setMaterial(smaterial);
                c.setMaterial(smaterial);
                cell.getChildren().addAll(a, b, c);

                rotateOrbs(cell);
                break;


        }

        GridPane.setHalignment(cell, HPos.CENTER);
        GridPane.setValignment(cell, VPos.CENTER);
        StackPane.setMargin(cell, new Insets(2,2,2,2));
        cellContainer.getChildren().add(cell);
        root.add(cellContainer, column, row);
        root.getChildren().remove(cellContainer);
        root.getChildren().set(removal, cellContainer);
        //playExplode();

//        System.out.println(removal);
        //root.getChildren().add(removal,cellContainer);


    }

    public static void rotateOrbs(Group cell){

        final Rotate rotationTransform = new Rotate(0, 0, 0);
        cell.getTransforms().add(rotationTransform);

        // rotate a square using timeline attached to the rotation transform's angle property.
        final Timeline rotationAnimation = new Timeline();
        rotationAnimation.getKeyFrames()
                .add(
                        new KeyFrame(
                                Duration.seconds(5),
                                new KeyValue(
                                        rotationTransform.angleProperty(),
                                        360
                                )
                        )
                );
        rotationAnimation.setCycleCount(Animation.INDEFINITE);
        rotationAnimation.play();
    }

    public static void transitionOrbs(Node node, Double x, Double y){

        System.out.println("THIS IS INSIDE TRANSITION ORBSSSSS");

        TranslateTransition translateTransition = new TranslateTransition();

        //Setting the duration of the transition
        translateTransition.setDuration(Duration.millis(2000));

        //Setting the node for the transition
        translateTransition.setNode(node);

        //Setting the value of the transition along the x axis.
        translateTransition.setByX(x);

        translateTransition.setByY(y);

        //Setting the cycle count for the transition
        translateTransition.setCycleCount(50);

        //Setting auto reverse value to false
        translateTransition.setAutoReverse(false);

        //Playing the animation
        translateTransition.play();

    }

    public static boolean checkEndGame(){

        // TODO : remove the .ser files if game ends

        int count=0;
        for (int i = 0; i < get_gameEngine().get_gc().get_players().size(); i++) {
            if (get_gameEngine().get_gc().get_players().get(i).get_isAlive()){
                count++;
            }
        }



        return (count == 1);
    }

    public static Scene createStartPage() {

        //In case a person clicks on start, default colours should come.



        // Instantiating the buttons
        startButton = new Button("  Start  ");
        startButton.setOnAction(new startButtonGUI());
        resumeButton = new Button("Resume");
        resumeButton.setOnAction(new resumeButtonGUI());
        settingsButton = new Button("Settings");
        settingsButton.setOnAction(new settingsButtonGUI());

        try {
            System.out.println(GameEngine.checkResume());
            if (!GameEngine.checkResume()){

                resumeButton.setDisable(true);
            }
            else{
                resumeButton.setDisable(false);
            }
        }
        catch (Exception v){
            v.printStackTrace();
        }


        // Customising the buttons
        startButton.setStyle("-fx-background-color: chartreuse; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold;");
        resumeButton.setStyle("-fx-background-color: cornflowerblue; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold;");
        settingsButton.setStyle("-fx-background-color: coral; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold;");

        StackPane root = new StackPane();

        //Making the drop down menus
        ObservableList<String> grid_options = FXCollections.observableArrayList("9x6", "15x10");
        gridChoiceCB=new ComboBox(grid_options);
        String gridChoiceCBValue = GUIMain.get_gameEngine().get_gridSize() == 0 ? "9x6" : "15x10";
        gridChoiceCB.setValue(gridChoiceCBValue);


        ObservableList<String> player_options = FXCollections.observableArrayList("2", "3", "4", "5", "6", "7", "8");
        numPlayersCB = new ComboBox<>(player_options);
        numPlayersCB.setValue(GUIMain.get_gameEngine().get_numPlayers());

        //Making the Grid for the home page
        GridPane home_grid=new GridPane();

        RowConstraints rc = new RowConstraints(70);
        rc.setVgrow(Priority.ALWAYS);
        rc.setValignment(VPos.CENTER);
        ColumnConstraints cc = new ColumnConstraints(80);
        cc.setHgrow(Priority.ALWAYS);
        cc.setHalignment(HPos.CENTER);
        root.setAlignment(Pos.CENTER);
        home_grid.setAlignment(Pos.CENTER);
        home_grid.setHgap(5);
        home_grid.setVgap(5);

        for(int i=0;i<10;i++){
            home_grid.getRowConstraints().add(rc);

        }

        for(int j=0;j<12;j++) {
            home_grid.getColumnConstraints().add(cc);
        }

        StackPane startButton_sp = new StackPane(startButton);
        StackPane resumeButton_sp = new StackPane(resumeButton);
        StackPane settingsButton_sp = new StackPane(settingsButton);

        Label title = new Label("Chain Reaction");
        title.setStyle("-fx-background-color: black; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 100px; -fx-font-weight: bold; -fx-text-fill: red;");

        Label gridstyle_label = new Label("Grid Size:");
        gridstyle_label.setStyle("-fx-background-color: black; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: red;");

        Label numplayers_label = new Label("Number of Players:");
        numplayers_label.setStyle("-fx-background-color: black; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: red;");

        GridPane.setHalignment(startButton_sp, HPos.CENTER);
        GridPane.setHalignment(resumeButton_sp, HPos.CENTER);
        GridPane.setHalignment(settingsButton_sp, HPos.CENTER);
        GridPane.setHalignment(gridChoiceCB, HPos.CENTER);


        gridChoiceCB.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-border-color: white;");
        numPlayersCB.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-border-color: white;");
        home_grid.add(startButton_sp,2,5,2,1);
        home_grid.add(gridstyle_label,5,4,2,1);
        home_grid.add(gridChoiceCB,5,5,2,1);
        home_grid.add(numplayers_label,8,4,3,1);
        home_grid.add(numPlayersCB,8,5,2,1);
        home_grid.add(resumeButton_sp,4,7,2,1);
        home_grid.add(settingsButton_sp, 6, 7,2,1);
        home_grid.setStyle("-fx-background-color: black");
        home_grid.add(title, 1, 1, 10,2);

        //home_grid.setGridLinesVisible(true);
        //root.getChildren().add(home_grid);

        return new Scene(home_grid, 1200, 1000, Color.BLACK);



    }

    public static Scene createSettingsPage() {

        //Instantiating the Buttons
        //saveButton=new Button("Save");
        homeButton=new Button ("Back");
        homeButton.setOnAction(new homeButtonGUI());

        StackPane homeButton_sp = new StackPane(homeButton);


        StackPane root = new StackPane();

        //Making the Player Color pickers


        playercolor.put(1,"#" + Integer.toHexString(player_1.getValue().hashCode()));
        playercolor.put(2,"#" + Integer.toHexString(player_2.getValue().hashCode()));
        playercolor.put(3,"#" + Integer.toHexString(player_3.getValue().hashCode()));
        playercolor.put(4,"#" + Integer.toHexString(player_4.getValue().hashCode()));
        playercolor.put(5,"#" + Integer.toHexString(player_5.getValue().hashCode()));
        playercolor.put(6,"#" + Integer.toHexString(player_6.getValue().hashCode()));
        playercolor.put(7,"#" + Integer.toHexString(player_7.getValue().hashCode()));
        playercolor.put(8,"#" + Integer.toHexString(player_8.getValue().hashCode()));

        _gameEngine.setplayer_colors(playercolor); //-- Giving null pointer error cuz players havent been selected yet


        //Making the Grid for the settings page
        GridPane setting_grid=new GridPane();

        RowConstraints rc = new RowConstraints(70);
        rc.setVgrow(Priority.ALWAYS);
        rc.setValignment(VPos.CENTER);
        ColumnConstraints cc = new ColumnConstraints(80);
        cc.setHgrow(Priority.ALWAYS);
        cc.setHalignment(HPos.CENTER);
        root.setAlignment(Pos.CENTER);
        setting_grid.setAlignment(Pos.CENTER);
        setting_grid.setHgap(5);
        setting_grid.setVgap(5);

        for(int i=0;i<12;i++){
            setting_grid.getRowConstraints().add(rc);

        }

        for(int j=0;j<14;j++) {
            setting_grid.getColumnConstraints().add(cc);
        }

        //Adding the Labels and changing the style of the respective players



        Label title = new Label("Settings");
        title.setStyle("-fx-background-color: black; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 70px; -fx-font-weight: bold; -fx-text-fill: red;");


       // comboBox_1.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-border-color: white;");
        Label player1_label = new Label("Player 1:");
        player1_label.setStyle("-fx-background-color: black; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: red;");


       // comboBox_2.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-border-color: white;");

        Label player2_label = new Label("Player 2:");
        player2_label.setStyle("-fx-background-color: black; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: red;");


       // comboBox_3.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-border-color: white;");

        Label player3_label = new Label("Player 3:");
        player3_label.setStyle("-fx-background-color: black; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: red;");


       // comboBox_4.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-border-color: white;");

        Label player4_label = new Label("Player 4:");
        player4_label.setStyle("-fx-background-color: black; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: red;");


       // comboBox_5.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-border-color: white;");

        Label player5_label = new Label("Player 5:");
        player5_label.setStyle("-fx-background-color: black; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: red;");


       // comboBox_6.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-border-color: white;");

        Label player6_label = new Label("Player 6:");
        player6_label.setStyle("-fx-background-color: black; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: red;");


       // comboBox_7.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-border-color: white;");

        Label player7_label = new Label("Player 7:");
        player7_label.setStyle("-fx-background-color: black; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: red;");


       // comboBox_8.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-border-color: white;");

        Label player8_label = new Label("Player 8:");
        player8_label.setStyle("-fx-background-color: black; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: red;");


        //Adding elements to the grid
        setting_grid.setStyle("-fx-background-color: black; -fx-text-alignment: center;");
        setting_grid.add(title, 2, 0, 10,2);

        setting_grid.add(player1_label,3,2,2,1);
        setting_grid.add(player_1,8,2,2,1);

        setting_grid.add(player2_label,3,3,2,1);
        setting_grid.add(player_2,8,3,2,1);

        setting_grid.add(player3_label,3,4,2,1);
        setting_grid.add(player_3,8,4,2,1);

        setting_grid.add(player4_label,3,5,2,1);
        setting_grid.add(player_4,8,5,2,1);

        setting_grid.add(player5_label,3,6,2,1);
        setting_grid.add(player_5,8,6,2,1);

        setting_grid.add(player6_label,3,7,2,1);
        setting_grid.add(player_6,8,7,2,1);

        setting_grid.add(player7_label,3,8,2,1);
        setting_grid.add(player_7,8,8,2,1);

        setting_grid.add(player8_label,3,9,2,1);
        setting_grid.add(player_8,8,9,2,1);

        //saveButton.setStyle("-fx-background-color: lightcoral; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold;");

        //setting_grid.add(saveButton,6,10,2,2);

        homeButton.setStyle("-fx-background-color: lightcoral; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold;");

        setting_grid.add(homeButton_sp, 10, 10, 2, 2);

        ArrayList<ColorPicker> cp_array = new ArrayList<>();
        cp_array.add(player_1);
        cp_array.add(player_2);
        cp_array.add(player_3);
        cp_array.add(player_4);
        cp_array.add(player_5);
        cp_array.add(player_6);
        cp_array.add(player_7);
        cp_array.add(player_8);

        //player_1.setDisable(true);

        for (int i = 7; i > _gameEngine.get_numPlayers()-1; i--) {
            cp_array.get(i).setDisable(true);
        }

        for (int i = 0; i < _gameEngine.get_numPlayers(); i++) {
            cp_array.get(i).setDisable(false);
        }



        //Gonna send the player colors to the GameEngine to create new players


        return new Scene(setting_grid, 1200, 1000, Color.BLACK);


    }

    public static Scene createEndPage(GridPane grid,Stage stage, int winner){
        String musicFile = "assets/win.mp3";     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer1= new MediaPlayer(sound);
        mediaPlayer1.play();

        ArrayList<Player> players = get_gameEngine().get_gc().get_players();

        Button restartButton = new Button("Play Again");
        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                File file_game = new File("game.ser");
                File file_undo = new File("undo.ser");

                try {

                    if (file_game.delete() && file_undo.delete()) {


                        for (int i = 0; i < players.size(); i++) {
                            players.get(i).set_isAlive(true);
                            players.get(i).set_isKillable(false);
                            players.get(i).set_isActive(false);
                        }

                        players.get(0).set_isActive(true);
                        players.get(0).set_isKillable(true);

                        Color firstColor = Color.web(players.get(0).get_colour());

                        for (int i = 0; i < get_numRows(); i++) {
                            for (int j = 0; j < get_numCols(); j++) {

                                addOrbAndAnimate(grid, i, j, 0, firstColor);
                                get_gameEngine().get_gc().get_grid().get_grid().get(i).get(j).set_currmass(0);


                            }
                        }

                        changeGridColor(grid, firstColor);

                        System.out.println("_____ inside restart handler _______");
                        System.out.println("color of the guy that goes first" + firstColor);
                        System.out.println(" ________ end restart handler ________");

                        setCurrentPlayer(ColorUtil.colorToHex(firstColor));
                        getRedoButton().setDisable(true);
                        getRedoButton().setDisable(true);

                        stage.setScene(createGamePage());




                    }
                } catch (Exception v) {
                    v.printStackTrace();
                }
            }
        });

        Button exitButton = new Button("Back to Home Page");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    File file_game = new File("game.ser");

                    File file_undo = new File("undo.ser");

                    if (file_game.delete() && file_undo.delete()) {

                        stage.setScene(createStartPage());
                    }

                }
                catch (Exception o){
                    o.printStackTrace();
                }
            }
        });

        Label winner_label = new Label("Player "+ winner + " won the game!");



        GridPane endgame_grid=new GridPane();

        endgame_grid.add(restartButton, 3, 6, 2, 2);
        endgame_grid.add(exitButton, 9, 6, 2, 2);
        endgame_grid.add(winner_label, 6, 2, 2, 2);

        mediaPlayer.setVolume(0);


        return new Scene(endgame_grid, 1200, 1000, Color.RED);


    }


    public static Scene createGamePage() {

        if(_gameEngine.get_choice()==0) {
            GridPane root = new GridPane();

            if (_gameEngine.get_gridSize() == 0) {
                _numRows = 9;
                _numCols = 6;
            } else {
                _numRows = 15;
                _numCols = 10;
            }

            Color firstplayer = Color.web(GUIMain.get_gameEngine().get_gc().get_players().get(0).get_colour());
            //System.out.println(firstplayer);
            //System.out.println(firstplayer.toString());

            BorderPane bp = setEmptyGrid(root, _numRows, _numCols, firstplayer);

            //addOrbAndAnimate(root, 0, 0 ,1, firstplayer);
            //addOrbAndAnimate(root, 1, 1, 2, firstplayer);

            // changeGridColor(root, Color.RED);

            GUIMain.setCurrentPlayer(GUIMain.get_gameEngine().get_gc().get_players().get(0).get_colour());
            System.out.println("First Player: " + GUIMain.getCurrentPlayer());

            Scene sc = new Scene(bp, 800, 1000, Color.BLACK);

            return sc;
        }

        else {

            GridPane root = new GridPane();

            if (_gameEngine.get_gridSize() == 0) {
                _numRows = 9;
                _numCols = 6;
            } else {
                _numRows = 15;
                _numCols = 10;
            }

            Color firstplayer = null;

            int i;
            for (i = 0; i < _gameEngine.get_numPlayers(); i++) {
                if(_gameEngine.get_gc().get_players().get(i).get_isActive()) {
                    firstplayer = Color.web(_gameEngine.get_gc().get_players().get(i).get_colour());
                    break;
                }
            }


            BorderPane bp = setEmptyGrid(root, _numRows, _numCols, firstplayer);
            Grid grid = _gameEngine.get_gc().get_grid();
            convertGridToGUI(root, grid);

            changeGridColor(root,firstplayer);



            GUIMain.setCurrentPlayer(_gameEngine.get_gc().get_players().get(i).get_colour());
            System.out.println("First Player: " + GUIMain.getCurrentPlayer());

            Scene sc = new Scene(bp, 800, 1000, Color.BLACK);

            return sc;


        }


    }


    public static void convertGridToGUI(GridPane gp, Grid g) {
        for (int i = 0; i < g.get_grid().size(); i++) {
            for (int j = 0; j < g.get_grid().get(0).size(); j++) {
                if (g.get_grid().get(i).get(j).get_currmass()!=0) {
                    Color clr = Color.web(g.get_grid().get(i).get(j).get_color());
                    int currmass = g.get_grid().get(i).get(j).get_currmass();
                    addOrbAndAnimate(gp, i, j, currmass, clr);
                }

            }
        }




    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        _gameEngine = new GameEngine(2,0);
        Screen screen = Screen.getPrimary();

        Rectangle2D bounds = screen.getVisualBounds();

        playercolor= new HashMap<Integer, String>();

        array_CP.add(player_1);
        array_CP.add(player_2);
        array_CP.add(player_3);
        array_CP.add(player_4);
        array_CP.add(player_5);
        array_CP.add(player_6);
        array_CP.add(player_7);
        array_CP.add(player_8);

        primaryStage.setScene(createStartPage());
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }

}




