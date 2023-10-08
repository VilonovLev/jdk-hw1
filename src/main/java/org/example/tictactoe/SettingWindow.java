package org.example.tictactoe;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Stream;

public class SettingWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 330;
    private static final int WINDOW_WIDTH = 350;

    JButton btnStart = new JButton("Start new game");

    SettingWindow(GameWindow gameWindow) {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Start menu");

        JPanel menu = new JPanel(new GridLayout(11,1));
        menu.setBorder(new EmptyBorder(10,10,10,10));

        AbstractButton pvp = new JRadioButton("PVP");
        pvp.setSelected(true);
        pvp.setMnemonic(0);
        AbstractButton pve = new JRadioButton("PVE");
        pve.setMnemonic(1);

        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(pvp);
        radioButtonGroup.add(pve);

        JLabel headerGameMode = new JLabel("Select the game mode");
        JLabel labelSizeField = new JLabel("Selected size: 3");
        JLabel headerFieldSide = new JLabel("Select field size");
        JLabel headerWinCount = new JLabel("Select count win");
        JLabel labelWinCount = new JLabel("Count win: 3");


        Stream.of(headerFieldSide,headerGameMode,headerWinCount)
                .forEach(x -> x.setFont(new Font("Serif",Font.BOLD,18)));

        JSlider fieldSize = new JSlider(3,10,3);

        fieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelSizeField.setText(String.format("Selected size: %d",fieldSize.getValue()));
            }
        });

        JSlider countWin = new JSlider(3,10,3);
        countWin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelWinCount.setText(String.format("Count win: %d",countWin.getValue()));
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mode = radioButtonGroup.getSelection().getMnemonic();
                int size = fieldSize.getValue();
                int win = countWin.getValue();

                if (win > size) win = size;

                gameWindow.startNewGame(mode, size, size, win);
                setVisible(false);
            }
        });

        menu.add(headerGameMode);
        menu.add(pvp);
        menu.add(pve);
        menu.add(headerFieldSide);
        menu.add(labelSizeField);
        menu.add(fieldSize);
        menu.add(headerWinCount);
        menu.add(labelWinCount);
        menu.add(countWin);
        menu.add(btnStart);
        add(menu);
    }
}