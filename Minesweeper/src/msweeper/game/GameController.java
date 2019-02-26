package msweeper.game;

import msweeper.game.Board;
import msweeper.gui.GameFrame;

public class GameController {
    protected Board board;
    protected int locationX;
    protected int locationY;
    protected GameFrame parent;
    protected boolean gameOver = false;

    public GameController(GameFrame parent) {
        board = new Board();
        locationX = 0;
        locationY = 0;
        this.parent = parent;
    }

    public GameController(GameFrame parent, int width, int height) {
        board = new Board(width, height);
        locationX = 0;
        locationY = 0;
        this.parent = parent;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Board getBoard() {
        return board;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void onLeft() {
        if (locationX != 0)
            locationX -= 1;
    }

    public void onRight() {
        if (locationX < Board.WIDTH - 1)
            locationX += 1;
    }

    public void onUp() {
        if (locationY != 0)
            locationY -= 1;
    }

    public void onDown() {
        if (locationY < Board.HEIGHT - 1)
            locationY += 1;
    }

    public void clearLocation() {
        if (!board.hasGameStarted())
            board.setupMines(locationX, locationY);
        if (board.getBoardUnits()[locationX][locationY].isCleared() || board.getBoardUnits()[locationX][locationY].isFlagged())
            return;
        int isMine = board.getBoardUnits()[locationX][locationY].clearField();
        if (isMine == 0)
            clearAll(locationX, locationY);
        if (isMine == Unit.HAS_MINE)
            gameOver();
    }

    protected void clearAll(int xloc, int yloc) {
        clearLocation(xloc - 1, yloc);
        clearLocation(xloc + 1, yloc);
        clearLocation(xloc, yloc - 1);
        clearLocation(xloc, yloc + 1);
        clearLocation(xloc - 1, yloc - 1);
        clearLocation(xloc + 1, yloc - 1);
        clearLocation(xloc - 1, yloc + 1);
        clearLocation(xloc + 1, yloc + 1);
    }

    public void clearLocation(int x, int y) {
        if (x < 0 || y < 0 || x >= Board.WIDTH || y >= Board.HEIGHT)
            return;
        if (board.getBoardUnits()[x][y].isCleared())
            return;
        int isMine = board.getBoardUnits()[x][y].clearField();
        if (isMine == Unit.NO_MINE)
            clearAll(x, y);
        if (isMine == Unit.HAS_MINE)
            gameOver();

    }

    public void flagLocation() {
        if (!board.getBoardUnits()[locationX][locationY].isCleared()) {
            boolean flagged = board.getBoardUnits()[locationX][locationY].setFlagged();
            if (flagged)
                board.changeNumberOfFlags(1);
            else
                board.changeNumberOfFlags(-1);
        }
    }

    public void fastClear() {
        if (!board.getBoardUnits()[locationX][locationY].isCleared())
            return;
        int flags = getNumberSurroundingFlags();
        if (flags == board.getBoardUnits()[locationX][locationY].surroundingMines())
            clearAll(locationX, locationY);
    }

    public int getNumberSurroundingFlags(int x, int y) {
        int flags = 0;
        flags += surroundingFlag(x - 1, y);
        flags += surroundingFlag(x + 1, y);
        flags += surroundingFlag(x, y - 1);
        flags += surroundingFlag(x, y + 1);
        flags += surroundingFlag(x - 1, y - 1);
        flags += surroundingFlag(x + 1, y - 1);
        flags += surroundingFlag(x - 1, y + 1);
        flags += surroundingFlag(x + 1, y + 1);
        return flags;
    }

    public int getNumberSurroundingFlags() {
        return getNumberSurroundingFlags(locationX, locationY);
    }

    protected int surroundingFlag(int xLoc, int yLoc) {
        if (xLoc < 0 || yLoc < 0 || xLoc >= Board.WIDTH || yLoc >= Board.HEIGHT)
            return 0;
        if (board.getBoardUnits()[xLoc][yLoc].isFlagged())
            return 1;
        board.getBoardUnits()[xLoc][yLoc].setChecking(false);
        return 0;
    }

    protected void gameOver() {
        gameOver = true;
        parent.repaint();
    }

    public void restartGame() {
        gameOver = false;
        board = new Board();
        parent.repaint();
    }

    public boolean checkClear(boolean check) {
        if (check && !board.getBoardUnits()[locationX][locationY].isCleared()) {
            checkClear(false);
            return false;
        }
        setCheck(locationX - 1, locationY, check);
        setCheck(locationX + 1, locationY, check);
        setCheck(locationX, locationY - 1, check);
        setCheck(locationX, locationY + 1, check);
        setCheck(locationX - 1, locationY - 1, check);
        setCheck(locationX + 1, locationY - 1, check);
        setCheck(locationX - 1, locationY + 1, check);
        setCheck(locationX + 1, locationY + 1, check);
        return true;
    }

    protected void setCheck(int xloc, int yloc, boolean check) {
        if (xloc < 0 || yloc < 0 || xloc >= Board.WIDTH || yloc >= Board.HEIGHT)
            return;
        board.getBoardUnits()[xloc][yloc].setChecking(check);
    }
}
