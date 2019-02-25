package msweeper.gui;

import msweeper.game.GameController;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MSKeyListener implements KeyListener {
    public static int RIGHT = 39;
    public static int LEFT = 37;
    public static int UP = 38;
    public static int SPACE = 32;
    public static int DOWN = 40;
    public static int FLAG = 70;
    public static int RIGHT_ALT = 68;
    public static int LEFT_ALT = 65;
    public static int UP_ALT = 87;
    public static int DOWN_ALT = 83;
    public static int FLAG_ALT = 47;
    public static int FAST_CLEAR = 16;
    public static int CLEAR = 10;
    public static int RESTART = 82;
    public static int SOLVE = 192;

    protected GameController controller;
    protected GameFrame parent;
    protected boolean testCheck = false;

    public MSKeyListener(GameController controller, GameFrame parent) {
        this.controller = controller;
        this.parent = parent;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
//        System.out.println("key " + e.getKeyCode());
        int keyCode = e.getKeyCode();
        if (keyCode == RIGHT || keyCode == RIGHT_ALT)
            keyPressedRight();
        else if (keyCode == LEFT || keyCode == LEFT_ALT)
            keyPressedLeft();
        else if (keyCode == UP || keyCode == UP_ALT)
            keyPressedUp();
        else if (keyCode == DOWN || keyCode == DOWN_ALT)
            keyPressedDown();
        else if (keyCode == CLEAR || keyCode == SPACE)
            keyPressedClear();
        else if (keyCode == FLAG || keyCode == FLAG_ALT)
            keyPressedFlag();
        else if (keyCode == FAST_CLEAR)
            keyPressedFastClear();
        else if (keyCode == RESTART)
            keyPressedRestartGame();
        else if (keyCode == SOLVE)
            keyPressedSolve();
    }

    protected void keyPressedRight() {
        if (testCheck)
            controller.checkClear(false);
        parent.repaint();
        controller.onRight();
        if (testCheck)
            testCheck = controller.checkClear(true);
    }

    protected void keyPressedLeft() {
        if (testCheck)
            controller.checkClear(false);
        controller.onLeft();
        if (testCheck)
            testCheck = controller.checkClear(true);                                                                                               
        parent.repaint();
    }

    protected void keyPressedUp() {
        if (testCheck)
            controller.checkClear(false);
        controller.onUp();
        if (testCheck)
            testCheck = controller.checkClear(true);
        parent.repaint();
    }

    protected void keyPressedDown() {
        if (testCheck)
            controller.checkClear(false);
        controller.onDown();
        if (testCheck)
            testCheck = controller.checkClear(true);
        parent.repaint();
    }

    protected void keyPressedClear() {
        if (testCheck)
            return;
        controller.clearLocation();
        parent.repaint();
    }

    protected void keyPressedRestartGame() {
        controller.restartGame();
    }

    protected void keyPressedFlag() {
        if (testCheck)
            return;
        controller.flagLocation();
        parent.repaint();
    }

    protected void keyPressedFastClear() {
        testCheck = controller.checkClear(true);
        parent.repaint();
    }

    protected void keyPressedSolve() {
    }

    protected void keyPressedQuery() {
        System.out.println("Number of mines left: " + controller.getBoard().getNumberOfMinesRemaining());
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == FAST_CLEAR) {
            controller.fastClear();
            parent.repaint();
            testCheck = false;
        }
    }
}
