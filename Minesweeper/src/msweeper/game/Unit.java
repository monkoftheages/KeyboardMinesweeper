package msweeper.game;

import msweeper.graphics.UnitGraphics;

import java.awt.*;

public class Unit {
    protected boolean hasMine = false;
    protected boolean flagged = false;
    protected boolean cleared = false;
    protected int surroundingMines = 0;
    protected int x;
    protected int y;
    public static final int SQUARE_SIZE = 15;
    protected boolean checking = false;

    public static final int HAS_MINE = -1;
    public static final int NO_MINE = 0;
    public static final int IS_FLAGGED = 1;

    public Unit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean hasMine() {
        return hasMine;
    }

    public void setMine() {
        hasMine = true;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean setFlagged() {
        this.flagged = !flagged;
        return flagged;
    }

    public int surroundingMines() {
        return surroundingMines;
    }

    public void incrementMineCount() {
        surroundingMines++;
    }

    public String printUnit() {    
        if(hasMine)
            return "*";
        if(surroundingMines == 0)
            return " ";
        return "" + surroundingMines;
    }

    public Image getGraphic() {                                                                                  
        if(cleared && hasMine)
            return UnitGraphics.MINE;
        if(cleared)
            return UnitGraphics.NUMBER[surroundingMines];   
        if(flagged)
            return UnitGraphics.FLAGGED;
        if(checking)
            return UnitGraphics.GENERIC_TEST;
        return UnitGraphics.GENERIC;
    }

    public Image getGraphicGameOver() {
        if(cleared && hasMine)
            return UnitGraphics.MINE;
        if(cleared)
            return UnitGraphics.NUMBER[surroundingMines];
        if(flagged && !hasMine)
            return UnitGraphics.FLAGGED_WRONG;
        if(flagged)
            return UnitGraphics.FLAGGED;
        if(hasMine)
            return UnitGraphics.UNCLEARED_MINE;
        return UnitGraphics.GENERIC;
    }

    public int getImageX() {
        return x * SQUARE_SIZE;
    }

    public int getImageY() {
        return y * SQUARE_SIZE;
    }

    public int clearField() {
        if(flagged)
            return IS_FLAGGED;
        cleared = true;
        if(hasMine)
            return HAS_MINE;
        return surroundingMines;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setChecking(boolean check) {
        checking = check;
        if(flagged)
            checking = false;
    }
}
