package msweeper.game;

import java.util.Random;

public class Board {
    public static int WIDTH = 30;
    public static int HEIGHT = 16;
    public static int TOTAL_MINES = 99;
    protected Unit[][] board;
    protected Random random;
    protected int numberOfFlags;
    protected boolean gameStarted;

    public Board() {
        gameStarted = false;
        //16x30, 99 mines
        int ri = new Random().nextInt(10000);
        System.out.println("Seed: " + ri);
        random = new Random(ri);
//        random = new Random(9729);
        numberOfFlags = 0;
        initBoard();
    }

    public Board(int width, int height) {
        gameStarted = false;
        WIDTH = width;
        HEIGHT = height;
        TOTAL_MINES = ((width * height) / 5);
        //16x30, 99 mines
        random = new Random();
        numberOfFlags = 0;
        initBoard();
    }

    public boolean hasGameStarted() {
        return gameStarted;
    }

    public void changeNumberOfFlags(int num) {
        numberOfFlags += num;
    }

    public int getNumberOfMinesRemaining() {
        return TOTAL_MINES - numberOfFlags;
    }

    protected void initBoard() {
        board = new Unit[WIDTH][HEIGHT];
        for (int w = 0; w < WIDTH; w++)
            for (int h = 0; h < HEIGHT; h++)
                board[w][h] = new Unit(w, h);
    }

    //Setup mines by randomly placing mines on the field.
    //Do not place mines on top of other mines
    //Do not place mines next to the first cleared location
    public void setupMines(int x, int y) {
        gameStarted = true;
        int numberOfMinesSet = 0;
        while (numberOfMinesSet < TOTAL_MINES) {
            int randomWidth = random.nextInt(WIDTH);
            int randomHeight = random.nextInt(HEIGHT);
            if (!board[randomWidth][randomHeight].hasMine()) {
                if (!nearXY(randomWidth, randomHeight, x, y)) {
                    numberOfMinesSet++;
                    board[randomWidth][randomHeight].setMine();
                    incrementAllSurroundings(randomWidth, randomHeight);
                }
            }
        }
    }

    //Checks that two given points are not close to each other
    //Used to make sure mines are not placed at the first clear
    protected boolean nearXY(int x1, int y1, int x2, int y2) {
        if (Math.abs(x1 - x2) >= 2)
            return false;
        if (Math.abs(y1 - y2) >= 2)
            return false;
        return true;
    }

    //Informs the nearby locations that a mine was added
    protected void incrementAllSurroundings(int width, int height) {
        incrementMineCount(width - 1, height);
        incrementMineCount(width + 1, height);
        incrementMineCount(width, height - 1);
        incrementMineCount(width, height + 1);
        incrementMineCount(width - 1, height - 1);
        incrementMineCount(width + 1, height - 1);
        incrementMineCount(width - 1, height + 1);
        incrementMineCount(width + 1, height + 1);
    }

    protected void incrementMineCount(int width, int height) {
        if (width < 0 || height < 0 || width >= WIDTH || height >= HEIGHT)
            return;
        board[width][height].incrementMineCount();
    }

    public void printBoard() {
        for (int h = 0; h < HEIGHT; h++) {
            String line = "";
            for (int w = 0; w < WIDTH; w++) {
                line += board[w][h].printUnit();
            }
            System.out.println(line);
        }
    }

    public Unit[][] getBoardUnits() {
        return board;
    }
}
