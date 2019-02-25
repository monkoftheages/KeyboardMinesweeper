package msweeper.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class UnitGraphics {
    public static Image GENERIC;
    public static Image GENERIC_TEST;
    public static Image[] NUMBER;
    public static Image MINE;
    public static Image FLAGGED;
    public static Image[] MINE_DISPLAY_NUMBER;
    public static Image FLAGGED_WRONG;
    public static Image UNCLEARED_MINE;


    public static void init() {
//        GENERIC = new ImageIcon(UnitGraphics.class.getResource("generic.jpg")).getImage();
        GENERIC = createImage("generic.jpg", 15, 15);
        GENERIC_TEST = new ImageIcon(UnitGraphics.class.getResource("generic_test.jpg")).getImage();
        MINE = new ImageIcon(UnitGraphics.class.getResource("clicked_mine.jpg")).getImage();
        FLAGGED = new ImageIcon(UnitGraphics.class.getResource("generic_flagged.jpg")).getImage();
        NUMBER = new Image[9];
        NUMBER[0] = createImage("clicked_0.jpg", 15, 15);
        NUMBER[1] = createImage("clicked_1.jpg", 15, 15);
        NUMBER[2] = new ImageIcon(UnitGraphics.class.getResource("clicked_2.jpg")).getImage();
        NUMBER[3] = new ImageIcon(UnitGraphics.class.getResource("clicked_3.jpg")).getImage();
        NUMBER[4] = new ImageIcon(UnitGraphics.class.getResource("clicked_4.jpg")).getImage();
        NUMBER[5] = new ImageIcon(UnitGraphics.class.getResource("clicked_5.jpg")).getImage();
        NUMBER[6] = new ImageIcon(UnitGraphics.class.getResource("clicked_6.jpg")).getImage();
        NUMBER[7] = new ImageIcon(UnitGraphics.class.getResource("clicked_7.jpg")).getImage();
        NUMBER[8] = new ImageIcon(UnitGraphics.class.getResource("clicked_8.jpg")).getImage();
        MINE_DISPLAY_NUMBER = new Image[10];
        MINE_DISPLAY_NUMBER[0] = new ImageIcon(UnitGraphics.class.getResource("digi_0.jpg")).getImage();
        MINE_DISPLAY_NUMBER[1] = new ImageIcon(UnitGraphics.class.getResource("digi_1.jpg")).getImage();
        MINE_DISPLAY_NUMBER[2] = new ImageIcon(UnitGraphics.class.getResource("digi_2.jpg")).getImage();
        MINE_DISPLAY_NUMBER[3] = new ImageIcon(UnitGraphics.class.getResource("digi_3.jpg")).getImage();
        MINE_DISPLAY_NUMBER[4] = new ImageIcon(UnitGraphics.class.getResource("digi_4.jpg")).getImage();
        MINE_DISPLAY_NUMBER[5] = new ImageIcon(UnitGraphics.class.getResource("digi_5.jpg")).getImage();
        MINE_DISPLAY_NUMBER[6] = new ImageIcon(UnitGraphics.class.getResource("digi_6.jpg")).getImage();
        MINE_DISPLAY_NUMBER[7] = new ImageIcon(UnitGraphics.class.getResource("digi_7.jpg")).getImage();
        MINE_DISPLAY_NUMBER[8] = new ImageIcon(UnitGraphics.class.getResource("digi_8.jpg")).getImage();
        MINE_DISPLAY_NUMBER[9] = new ImageIcon(UnitGraphics.class.getResource("digi_9.jpg")).getImage();
        FLAGGED_WRONG = new ImageIcon(UnitGraphics.class.getResource("generic_flagged_wrong.jpg")).getImage();
        UNCLEARED_MINE = new ImageIcon(UnitGraphics.class.getResource("uncleared_mine.jpg")).getImage();
    }

    public static Image createImage(String location, int width, int height) {
        Image icon = new ImageIcon(UnitGraphics.class.getResource(location)).getImage();
        BufferedImage cg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D)cg.getGraphics();
        g.drawImage(icon,  0, 0, width, height, null);
        return cg;
    }
}
