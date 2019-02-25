package msweeper;

import msweeper.game.Board;
import msweeper.gui.GameFrame;


public class Main {
    public static void main(String[] args) {
        if(args.length != 2)
            new GameFrame();
        else
            createNewGame(args[0], args[1]);
    }

    protected static void createNewGame(String w, String h) {
        try {
            int wi = Integer.valueOf(w);
            int he = Integer.valueOf(h);
            new GameFrame(wi, he);
        } catch(Exception e) {
            new GameFrame();
        }
    }
}
