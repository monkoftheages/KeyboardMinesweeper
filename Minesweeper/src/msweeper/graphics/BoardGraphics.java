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

    public BoardGraphics(GameFrame parent) {
        this.parent = parent;
        PREF_W = Board.WIDTH * 15;
        PREF_H = Board.HEIGHT * 15 + BOTTOM_PANEL_HEIGHT;
    }

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2_component = (Graphics2D) g;
        BufferedImage offscreen = new BufferedImage(PREF_W, PREF_H, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = offscreen.createGraphics();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, PREF_W, PREF_H);
        g2.setColor(RED_TRANSPARENT);
        if(!parent.getController().isGameOver())
            drawGameLive(g2);
        else
            drawGameOver(g2);
        drawMinesLeft(g2, parent.getBoard().getNumberOfMinesRemaining());
        g2_component.drawImage(offscreen, 0, 0, getWidth(), getHeight(), null);
        g2.dispose();
    }

    protected void drawGameLive(Graphics2D g2) {
        Unit[][] board = parent.getBoard().getBoardUnits();
        for (int w = 0; w < board.length; w++)
            for (int h = 0; h < board[0].length; h++) {
                g2.drawImage(board[w][h].getGraphic(), board[w][h].getImageX(), board[w][h].getImageY(), null);
                if (parent.getController().isSelected(w, h))
                    drawRect(g2, w * 15, h * 15, 15, 15);
            }
    }

    protected void drawGameOver(Graphics2D g2) {
        Unit[][] board = parent.getBoard().getBoardUnits();
        for (int w = 0; w < board.length; w++)
            for (int h = 0; h < board[0].length; h++) {
                g2.drawImage(board[w][h].getGraphicGameOver(), board[w][h].getImageX(), board[w][h].getImageY(), null);
                if (parent.getController().isSelected(w, h))
                    drawRect(g2, w * 15, h * 15, 15, 15);
            }

    }

    protected void drawMinesLeft(Graphics2D g2, int minesLeft) {
        if (minesLeft < 0)
            minesLeft = 0;
        int ones = minesLeft % 10;
        int tens = (minesLeft / 10) % 10;
        int hundreds = (minesLeft / 100) % 10;
        int startingHeight = Board.HEIGHT * 15 + 4;
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
