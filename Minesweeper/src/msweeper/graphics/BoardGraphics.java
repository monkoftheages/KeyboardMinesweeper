package msweeper.graphics;

import msweeper.game.Unit;
import msweeper.game.Board;
import msweeper.gui.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BoardGraphics extends JPanel {
    public static int PREF_W = 450;
    public static int PREF_H = 270;
    protected GameFrame parent;
    public static final Color RED_TRANSPARENT = new Color(1, 0, 0, .5f);
    public static final int BOTTOM_PANEL_HEIGHT = 37;
    protected BufferedImage offscreen;
    protected Graphics2D offscreenGraphics;

    public BoardGraphics(GameFrame parent) {
        this.parent = parent;
        PREF_W = Board.WIDTH * Unit.SQUARE_SIZE;
        PREF_H = Board.HEIGHT * Unit.SQUARE_SIZE + BOTTOM_PANEL_HEIGHT;
        offscreen = new BufferedImage(PREF_W, PREF_H, BufferedImage.TYPE_INT_ARGB);
        offscreenGraphics = offscreen.createGraphics();
        drawGameLive(offscreenGraphics);
    }

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2_component = (Graphics2D) g;
        offscreenGraphics.setColor(Color.BLACK);
        offscreenGraphics.fillRect(0, 0, PREF_W, PREF_H);
        if(!parent.getController().isGameOver())
            drawGameLive(offscreenGraphics);
        else
            drawGameOver(offscreenGraphics);
        offscreenGraphics.setColor(RED_TRANSPARENT);
        drawRect(offscreenGraphics, parent.getController().getLocationX() * Unit.SQUARE_SIZE, parent.getController().getLocationY() * Unit.SQUARE_SIZE, Unit.SQUARE_SIZE, Unit.SQUARE_SIZE);
        drawMinesLeft(offscreenGraphics, parent.getBoard().getNumberOfMinesRemaining());
        g2_component.drawImage(offscreen, 0, 0, getWidth(), getHeight(), null);
    }

    protected void drawGameLive(Graphics2D g2) {
        Unit[][] board = parent.getBoard().getBoardUnits();
        for (int w = 0; w < board.length; w++)
            for (int h = 0; h < board[0].length; h++)
                g2.drawImage(board[w][h].getGraphic(), board[w][h].getImageX(), board[w][h].getImageY(), null);
    }

    protected void drawGameOver(Graphics2D g2) {
        Unit[][] board = parent.getBoard().getBoardUnits();
        for (int w = 0; w < board.length; w++)
            for (int h = 0; h < board[0].length; h++)
                g2.drawImage(board[w][h].getGraphicGameOver(), board[w][h].getImageX(), board[w][h].getImageY(), null);
    }

    protected void drawMinesLeft(Graphics2D g2, int minesLeft) {
        if (minesLeft < 0)
            minesLeft = 0;
        int ones = minesLeft % 10;
        int tens = (minesLeft / 10) % 10;
        int hundreds = (minesLeft / 100) % 10;
        int startingHeight = Board.HEIGHT * Unit.SQUARE_SIZE + 4;
        int startingX = 15;
        g2.drawImage(UnitGraphics.MINE_DISPLAY_NUMBER[hundreds], startingX, startingHeight, 23, 30, null);
        g2.drawImage(UnitGraphics.MINE_DISPLAY_NUMBER[tens], startingX + 23, startingHeight, 23, 30, null);
        g2.drawImage(UnitGraphics.MINE_DISPLAY_NUMBER[ones], startingX + 46, startingHeight, 23, 30, null);
    }

    public void drawRect(Graphics2D g2, int x, int y, int width, int height) {
        if ((width < 0) || (height < 0)) {
            return;
        }

        if (height == 0 || width == 0) {
            g2.drawLine(x, y, x + width, y + height);
        } else {
            g2.drawLine(x, y, x + width - 4, y);
            g2.drawLine(x, y + 1, x + width - 4, y + 1);
            g2.drawLine(x, y + 2, x + width - 4, y + 2);

            g2.drawLine(x + width, y, x + width, y + height - 4);
            g2.drawLine(x + width - 1, y, x + width - 1, y + height - 4);
            g2.drawLine(x + width - 2, y, x + width - 2, y + height - 4);
            g2.drawLine(x + width - 3, y, x + width - 3, y + height - 4);

            g2.drawLine(x + width, y + height, x, y + height);
            g2.drawLine(x + width, y + height - 1, x, y + height - 1);
            g2.drawLine(x + width, y + height - 2, x, y + height - 2);
            g2.drawLine(x + width, y + height - 3, x, y + height - 3);

            g2.drawLine(x, y + height - 4, x, y + 3);
            g2.drawLine(x + 1, y + height - 4, x + 1, y + 3);
            g2.drawLine(x + 2, y + height - 4, x + 2, y + 3);
        }
    }
}
