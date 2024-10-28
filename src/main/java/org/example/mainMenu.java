package org.example;

import javax.swing.*;
import java.awt.*;

public class mainMenu {

    private final String INSTALLED_FIELD_SIZE = "Установленный размер поля: ";
    private final String INSTALLED_WIN_LENGTH = "Установленный длину для победы: ";

    private int FIELD_SIZE;
    private int WIN_LENGTH;

    JFrame frame;
    JButton btnStartGame;
    JRadioButton humanVsHuman, HumanVsAI;
    ButtonGroup bg;
    JPanel finalPannel, panelMode, bgFieldSize, bgWinLen;
    JSlider fieldSize, winLen;

    mainMenu(String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(355,400);
        frame.setLocationRelativeTo(null);
        finalPannel = new JPanel(new GridLayout(3,1));
        finalPannel.add(addMode());
        finalPannel.add(setfieldSize());
        finalPannel.add(setWinLength());

        btnStartGame = new JButton("Начать новую игру");
        frame.add(finalPannel);
        frame.add(btnStartGame, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private JPanel addMode() {
        panelMode = new JPanel(new GridLayout(3,1));
        bg = new ButtonGroup();
        humanVsHuman = new JRadioButton("Человек против человека");
        HumanVsAI = new JRadioButton("Человек против компьютера");
        humanVsHuman.setSelected(true);
        bg.add(humanVsHuman);
        bg.add(HumanVsAI);
        panelMode.add(new Label("Выберите режим игры"));
        panelMode.add(humanVsHuman);
        panelMode.add(HumanVsAI);
        return panelMode;
    }

    private JPanel setfieldSize() {
        FIELD_SIZE =3;
        bgFieldSize = new JPanel(new GridLayout(2,1));
        Label installSize = new Label(INSTALLED_FIELD_SIZE + FIELD_SIZE);
        bgFieldSize.add(installSize);
        fieldSize = new JSlider(3,10,3);
        fieldSize.addChangeListener(_ -> {
            FIELD_SIZE = fieldSize.getValue();
            installSize.setText(INSTALLED_FIELD_SIZE + FIELD_SIZE);
            winLen.setMaximum(FIELD_SIZE);
        });
        bgFieldSize.add(fieldSize);
        return bgFieldSize;
    }

    private JPanel setWinLength() {
        WIN_LENGTH = 3;
        bgWinLen = new JPanel(new GridLayout(2,1));
        Label installSize = new Label(INSTALLED_WIN_LENGTH + WIN_LENGTH);
        bgWinLen.add(installSize);
        winLen = new JSlider(3,10,3);
        winLen.addChangeListener(_ -> {
            WIN_LENGTH = winLen.getValue();
            installSize.setText(INSTALLED_WIN_LENGTH + WIN_LENGTH);
        });
        bgWinLen.add(winLen);
        return bgWinLen;
    }
}
