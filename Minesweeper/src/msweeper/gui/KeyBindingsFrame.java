package msweeper.gui;

import msweeper.graphics.BoardGraphics;

import javax.swing.*;
import java.awt.event.*;

public class KeyBindingsFrame extends JFrame {
    protected JLabel rightLabel;
    protected JLabel leftLabel;
    protected JLabel upLabel;
    protected JLabel downLabel;
    protected JLabel flagLabel;
    protected JLabel rightaltLabel;
    protected JLabel fastClearLabel;
    protected JLabel clearLabel;
    protected JLabel restartLabel;

    protected JTextField rightField;
    protected JTextField leftField;
    protected JTextField upField;
    protected JTextField downField;
    protected JTextField flagField;
    protected JTextField fastClearField;
    protected JTextField clearField;
    protected JTextField restartField;
    protected JTextField rightaltField;
    protected JTextField leftaltField;
    protected JTextField upaltField;
    protected JTextField downaltField;
    protected JTextField flagaltField;

    protected JButton saveButton;
    protected JButton applyButton;
    protected JButton cancelButton;

    protected int[] keyBindings;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        KeyBindingsFrame frame = new KeyBindingsFrame();
    }

    public KeyBindingsFrame() {
        super("Key Bindings");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(450, 270);
        setupComponents();
//        pack();
        setLocationRelativeTo(null);
        getKeyBindings();
        setVisible(true);
    }

    protected void getKeyBindings() {
        keyBindings = new int[13];
        keyBindings[0] = MSKeyListener.RIGHT;
        keyBindings[1] = MSKeyListener.LEFT;
        keyBindings[2] = MSKeyListener.UP;
        keyBindings[3] = MSKeyListener.DOWN;
        keyBindings[4] = MSKeyListener.FLAG;
        keyBindings[5] = MSKeyListener.FAST_CLEAR;
        keyBindings[6] = MSKeyListener.CLEAR;
        keyBindings[7] = MSKeyListener.RESTART;
        keyBindings[8] = MSKeyListener.RIGHT_ALT;
        keyBindings[9] = MSKeyListener.LEFT_ALT;
        keyBindings[10] = MSKeyListener.UP_ALT;
        keyBindings[11] = MSKeyListener.DOWN_ALT;
        keyBindings[12] = MSKeyListener.FLAG_ALT;
    }

    protected void setupComponents() {
        initializeComponents();
        setComponentSizes();
        setComponentLocations();
        setComponentOthers();
        addComponents();

    }

    protected void initializeComponents() {
        rightLabel = new JLabel("Right:");
        leftLabel = new JLabel("Left:");
        upLabel = new JLabel("Up:");
        downLabel = new JLabel("Dpwm:");
        flagLabel = new JLabel("Flag:");
        fastClearLabel = new JLabel("Fast Clear:");
        clearLabel = new JLabel("Clear:");
        restartLabel = new JLabel("Restart:");

        rightField = new JTextField(KeyEvent.getKeyText(MSKeyListener.RIGHT));
        leftField = new JTextField(KeyEvent.getKeyText(MSKeyListener.LEFT));
        upField = new JTextField(KeyEvent.getKeyText(MSKeyListener.UP));
        downField = new JTextField(KeyEvent.getKeyText(MSKeyListener.DOWN));
        flagField = new JTextField(KeyEvent.getKeyText(MSKeyListener.FLAG));
        fastClearField = new JTextField(KeyEvent.getKeyText(MSKeyListener.FAST_CLEAR));
        clearField = new JTextField(KeyEvent.getKeyText(MSKeyListener.CLEAR));
        restartField = new JTextField(KeyEvent.getKeyText(MSKeyListener.RESTART));
        rightaltField = new JTextField(KeyEvent.getKeyText(MSKeyListener.RIGHT_ALT));
        leftaltField = new JTextField(KeyEvent.getKeyText(MSKeyListener.LEFT_ALT));
        upaltField = new JTextField(KeyEvent.getKeyText(MSKeyListener.UP_ALT));
        downaltField = new JTextField(KeyEvent.getKeyText(MSKeyListener.DOWN_ALT));
        flagaltField = new JTextField(KeyEvent.getKeyText(MSKeyListener.FLAG_ALT));

        saveButton = new JButton("Save");
        applyButton = new JButton("Apply");
        cancelButton = new JButton("Cancel");
    }

    protected void setComponentSizes() {
        rightLabel.setSize(200, 15);
        leftLabel.setSize(200, 15);
        upLabel.setSize(200, 15);
        downLabel.setSize(200, 15);
        flagLabel.setSize(200, 15);
        fastClearLabel.setSize(200, 15);
        clearLabel.setSize(200, 15);
        restartLabel.setSize(200, 15);

        rightField.setSize(100, 20);
        leftField.setSize(100, 20);
        upField.setSize(100, 20);
        downField.setSize(100, 20);
        flagField.setSize(100, 20);
        fastClearField.setSize(100, 20);
        clearField.setSize(100, 20);
        restartField.setSize(100, 20);
        rightaltField.setSize(100, 20);
        leftaltField.setSize(100, 20);
        upaltField.setSize(100, 20);
        downaltField.setSize(100, 20);
        flagaltField.setSize(100, 20);

        saveButton.setSize(100, 20);
        applyButton.setSize(100, 20);
        cancelButton.setSize(100, 20);
    }

    protected void setComponentLocations() {
        rightLabel.setLocation(10, 10);
        leftLabel.setLocation(10, 30);
        upLabel.setLocation(10, 50);
        downLabel.setLocation(10, 70);
        flagLabel.setLocation(10, 90);
        fastClearLabel.setLocation(10, 110);
        clearLabel.setLocation(10, 130);
        restartLabel.setLocation(10, 150);

        rightField.setLocation(100, 10);
        rightaltField.setLocation(250, 10);
        leftField.setLocation(100, 30);
        leftaltField.setLocation(250, 30);
        upField.setLocation(100, 50);
        upaltField.setLocation(250, 50);
        downField.setLocation(100, 70);
        downaltField.setLocation(250, 70);
        flagField.setLocation(100, 90);
        flagaltField.setLocation(250, 90);
        fastClearField.setLocation(100, 110);
        clearField.setLocation(100, 130);
        restartField.setLocation(100, 150);

        saveButton.setLocation(60, 200);
        applyButton.setLocation(170, 200);
        cancelButton.setLocation(280, 200);
    }

    protected void setComponentOthers() {
        createActionListener(rightField, 0);
        createActionListener(leftField, 1);
        createActionListener(upField, 2);
        createActionListener(downField, 3);
        createActionListener(flagField, 4);
        createActionListener(fastClearField, 5);
        createActionListener(clearField, 6);
        createActionListener(restartField, 7);
        createActionListener(rightaltField, 8);
        createActionListener(leftaltField, 9);
        createActionListener(upaltField, 10);
        createActionListener(downaltField, 11);
        createActionListener(flagaltField, 12);
        createButtonActionListeners();
    }

    protected void addComponents() {
        getContentPane().add(rightLabel);
        getContentPane().add(leftLabel);
        getContentPane().add(upLabel);
        getContentPane().add(downLabel);
        getContentPane().add(flagLabel);
        getContentPane().add(fastClearLabel);
        getContentPane().add(clearLabel);
        getContentPane().add(restartLabel);

        getContentPane().add(rightField);
        getContentPane().add(leftField);
        getContentPane().add(upField);
        getContentPane().add(downField);
        getContentPane().add(flagField);
        getContentPane().add(fastClearField);
        getContentPane().add(clearField);
        getContentPane().add(restartField);
        getContentPane().add(rightaltField);
        getContentPane().add(leftaltField);
        getContentPane().add(upaltField);
        getContentPane().add(downaltField);
        getContentPane().add(flagaltField);

        getContentPane().add(saveButton);
        getContentPane().add(applyButton);
        getContentPane().add(cancelButton);
    }

    protected void createButtonActionListeners() {
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
                dispose();
            }
        });
        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    protected void createActionListener(final JTextField text, final int number) {
        text.setFocusable(true);
        text.setEditable(false);
        text.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                text.getCaret().setVisible(true);
            }

            public void focusLost(FocusEvent e) {
                text.getCaret().setVisible(false);
            }
        });
        text.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                keyBindings[number] = e.getKeyCode();
                text.setText(KeyEvent.getKeyText(e.getKeyCode()));
            }

            public void keyReleased(KeyEvent e) {
            }
        });
    }

    protected void save() {
        MSKeyListener.RIGHT = keyBindings[0];
        MSKeyListener.LEFT = keyBindings[1];
        MSKeyListener.UP = keyBindings[2];
        MSKeyListener.DOWN = keyBindings[3];
        MSKeyListener.FLAG = keyBindings[4];
        MSKeyListener.FAST_CLEAR = keyBindings[5];
        MSKeyListener.CLEAR = keyBindings[6];
        MSKeyListener.RESTART = keyBindings[7];
        MSKeyListener.RIGHT_ALT = keyBindings[8];
        MSKeyListener.LEFT_ALT = keyBindings[9];
        MSKeyListener.UP_ALT = keyBindings[10];
        MSKeyListener.DOWN_ALT = keyBindings[11];
        MSKeyListener.FLAG_ALT = keyBindings[12];
    }
}
