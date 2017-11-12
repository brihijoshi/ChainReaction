package GameEngine;

import java.io.*;
import java.util.ArrayList;

public class GameController {


    private ArrayList<Player> _players;
    private Grid _grid;
    private GameState _gameState;
    private boolean _resume;
    private static int _index;
    private boolean _endGame;

    public GameController() {
    }

    public GameController(ArrayList<Player> _players, Grid _grid, GameState _gameState, boolean _resume) {
        this._players = _players;
        this._grid = _grid;
        this._gameState = _gameState;
        this._resume = _resume;
        this._endGame = false;
    }

    public ArrayList<Player> get_players() {
        return _players;
    }

    public void set_players(ArrayList<Player> _players) {
        this._players = _players;
    }

    public Grid get_grid() {
        return _grid;
    }

    public void set_grid(Grid _grid) {
        this._grid = _grid;
    }

    public GameState get_gameState() {
        return _gameState;
    }

    public void set_gameState(GameState _gameState) {
        this._gameState = _gameState;
    }

    public boolean is_resume() {
        return _resume;
    }

    public void set_resume(boolean _resume) {
        this._resume = _resume;
    }

    public boolean is_endGame() {
        return _endGame;
    }

    public void set_endGame(boolean _endGame) {
        this._endGame = _endGame;
    }

    public void loadGameState() throws IOException, ClassNotFoundException {

        FileInputStream fis=new FileInputStream("game.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        GameState g= (GameState) ois.readObject();
        System.out.println("Received the Game State!");
        set_gameState(g);
        set_players(g.get_players());
        set_grid(g.get_grid());
        ois.close();
        fis.close();

    }

    public GameState loadUndoState() throws IOException, ClassNotFoundException{
        FileInputStream fis=new FileInputStream("resume.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        GameState g= (GameState) ois.readObject();
        System.out.println("Received the Resume State!");
        ois.close();
        fis.close();
        return g;
    }

    public void saveGameState() throws IOException {

        GameState g = get_gameState();

        g.set_grid(_grid);
        g.set_players(_players);
        FileOutputStream fos = new FileOutputStream("game.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        System.out.println( "Game state saved!");
        oos.writeObject(g);
        oos.close();
        fos.close();

    }

    public void saveUndoState() throws IOException {

        GameState g = get_gameState();
        g.set_grid(_grid);
        g.set_players(_players);
        FileOutputStream fos = new FileOutputStream("resume.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        System.out.println( "Resume state saved!");
        oos.writeObject(g);
        oos.close();
        fos.close();

    }

    public boolean checkActivePlayer() {

        return true;

    }

    public static int get_index(){
        return _index;
    }

    public static void set_index(int _i){
        _index=_i;
    }

    public static int[] convert_index(int numCols){
        //int numRows = GameEngine.get_gridSize();

        int[] arr = {_index/numCols,_index%numCols};
        return arr;
    }


    // Obtains the index of stackPane of the clicked cell
    // and outputs the index of the cell in the 2D array

    public void takeTurn(Player p) {

        int[] pos = convert_index(_grid.get_grid().get(0).size());
        Cell clicked =_grid.get_grid().get(pos[0]).get(pos[1]);

        if (clicked.get_color().equals(p.get_colour())){

            clicked.set_currmass(clicked.get_currmass()+1);
            //Add in the GUI as well
            if ( clicked.get_currmass() > clicked.get_CRITMASS() ){
                clicked.set_currmass(0);
                explode(pos[0],pos[1]);
                //Clear the Cell

            }


        }
        else{
            //Need to make an error "Sound" or no reaction
        }
    }



    public boolean isValid(int x_index, int y_index){
        if ((x_index < _grid.get_grid().size()) && (x_index>=0) && (y_index<_grid.get_grid().get(0).size()) && (y_index>=0)){
            return true;
        }
        return false;
    }

    public void explode(int x, int y){
        //Handle Top
        if (isValid(x-1, y)){
            Cell top = _grid.get_grid().get(x-1).get(y);
            top.set_currmass(top.get_currmass()+1);
            //Add in the GUI as well
            if (top.get_currmass()>top.get_CRITMASS()){
                top.set_currmass(0);
                explode(x-1,y);
            }
        }

        //Handle Left
        if (isValid(x,y-1)){
            Cell left = _grid.get_grid().get(x).get(y-1);
            left.set_currmass(left.get_currmass()+1);
            //Add in the GUI as well
            if (left.get_currmass()>left.get_CRITMASS()){
                left.set_currmass(0);
                explode(x,y-1);
            }
        }

        //Handle Bottom
        if (isValid(x+1,y)){
            Cell bottom = _grid.get_grid().get(x+1).get(y);
            bottom.set_currmass(bottom.get_currmass()+1);
            //Add in the GUI as well
            if (bottom.get_currmass()>bottom.get_CRITMASS()){
                bottom.set_currmass(0);
                explode(x+1,y);
            }
        }

        //Handle Right
        if (isValid(x,y+1)){
            Cell right = _grid.get_grid().get(x).get(y+1);
            right.set_currmass(right.get_currmass()+1);
            //Add in the GUI as well
            if (right.get_currmass()>right.get_CRITMASS()){
                right.set_currmass(0);
                explode(x,y+1);
            }
        }
    }

    public void handleGame() {

        _endGame = false;

        if(!_resume) {

            while(!is_endGame()) {


                for (int i = 0; i < _players.size(); i++) {
                    Player p = _players.get(i);
                    if(p.get_isAlive()) {
                        takeTurn(p);
                    }


                }

            }

        }



    }


}
