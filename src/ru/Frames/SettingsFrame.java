package ru.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import ru.Main;
import ru.Settings;


/**
 * Created by техносила on 11.09.2016.
 */
public class SettingsFrame extends JFrame {
    JLabel
            typeOfModelLabel, fieldWidthLabel, fieldHeightLabel,
            from1to2LowerTresholdLabel, from2to3LowerTresholdLabel, from3to4LowerTresholdLabel, from4to3LowerTresholdLabel,
            from1to2UpperTresholdLabel, from2to3UpperTresholdLabel, from3to4UpperTresholdLabel, from4to3UpperTresholdLabel,
            from1to2SelfChanceLabel, from2to3SelfChanceLabel, from3to4SelfChanceLabel, from4to3SelfChanceLabel,
            buffFromPositiveNeighbourLabel, buffFromNegativeNeighbourLabel, buffFromNewNeighbourLabel,
            isChangingMindLabel;

    JTextField
            from1to2LowerTreshold, from2to3LowerTreshold, from3to4LowerTreshold, from4to3LowerTreshold,
            from1to2UpperTreshold, from2to3UpperTreshold, from3to4UpperTreshold, from4to3UpperTreshold,
            from1to2SelfChance, from2to3SelfChance, from3to4SelfChance, from4to3SelfChance,
            buffFromPositiveNeighbour, buffFromNegativeNeighbour, buffFromNewNeighbour,
            fieldWidth, fieldHeight;

    JCheckBox isChangingMind;
    JButton start;
    JComboBox wayList;
    public SettingsFrame() throws IOException, ClassNotFoundException {


        String[] ways = {"Fill in randomly", "Blank field", "DNR Edition"};
        GridBagConstraints gbc = new GridBagConstraints();
        setName("Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        File settingsFile = new File("temp.out");
        if (settingsFile.exists()!=true){
            Settings.createSettings();
        }
        FileInputStream fis = new FileInputStream(settingsFile);

        ObjectInputStream oin = new ObjectInputStream(fis);
        Main.settings = (Settings) oin.readObject();

        int row = 0;

        setLayout(new GridBagLayout());

        typeOfModelLabel = new JLabel("type of simulation: ");
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.insets = new Insets(0, 0, 8, 0);
        add(typeOfModelLabel, gbc);
        wayList = new JComboBox(ways);
        wayList.setSelectedIndex(0);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(wayList, gbc);
        gbc.insets = new Insets(0, 0, 0, 0);
        row++;


        fieldWidthLabel = new JLabel("field's width: ");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(fieldWidthLabel, gbc);
        fieldWidth = new JTextField((Main.settings.fieldWidth + ""), 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(fieldWidth, gbc);
        row++;

        fieldHeightLabel = new JLabel("field's height: ");
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.insets = new Insets(0, 0, 8, 0);
        add(fieldHeightLabel, gbc);
        fieldHeight = new JTextField(Main.settings.fieldHeight + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(fieldHeight, gbc);
        gbc.insets = new Insets(0, 0, 0, 0);
        row++;
        from1to2LowerTresholdLabel = new JLabel("from 1 to 2 lower treshold: ");
        from1to2LowerTresholdLabel.setToolTipText("Eсли количество пользователей вокруг меньше, чем нижний порог, то остается незанятой");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(from1to2LowerTresholdLabel, gbc);
        from1to2LowerTreshold = new JTextField(Main.settings.from1to2LowerTreshold + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(from1to2LowerTreshold, gbc);
        row++;

        from1to2UpperTresholdLabel = new JLabel("from 1 to 2 upper treshold: ");
        from1to2UpperTresholdLabel.setToolTipText("Если количество пользователей вокруг превышает верхний порог, то становится новым пользователем");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(from1to2UpperTresholdLabel, gbc);
        from1to2UpperTreshold = new JTextField(Main.settings.from1to2UpperTreshold + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(from1to2UpperTreshold, gbc);
        row++;


        row++;
        from2to3LowerTresholdLabel = new JLabel("from 2 to 3 lower treshold: ");
        from2to3LowerTresholdLabel.setToolTipText("если количество пользователей вокруг меньше, чем нижний порог, то удалит приложение");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(from2to3LowerTresholdLabel, gbc);
        from2to3LowerTreshold = new JTextField(Main.settings.from2to3LowerTreshold + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(from2to3LowerTreshold, gbc);
        row++;
        from2to3UpperTresholdLabel = new JLabel("from 2 to 3 upper treshold: ");
        from2to3UpperTresholdLabel.setToolTipText("если количество пользователей вокруг превышает верхний порог, то останется пользователем");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(from2to3UpperTresholdLabel, gbc);
        from2to3UpperTreshold = new JTextField(Main.settings.from2to3UpperTreshold + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(from2to3UpperTreshold, gbc);

        row++;
        from3to4LowerTresholdLabel = new JLabel("from 3 to 4 lower treshold: ");
        from3to4LowerTresholdLabel.setToolTipText("если количество пользователей вокруг меньше, чем нижний порог, то остается удалившим");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(from3to4LowerTresholdLabel, gbc);
        from3to4LowerTreshold = new JTextField(Main.settings.from3to4LowerTreshold + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(from3to4LowerTreshold, gbc);
        row++;
        from3to4UpperTresholdLabel = new JLabel("from 3 to 4 upper treshold: ");
        from3to4UpperTresholdLabel.setToolTipText("если количество пользователей вокруг превышает верхний порог, то повторно становится пользователем");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(from3to4UpperTresholdLabel, gbc);
        from3to4UpperTreshold = new JTextField(Main.settings.from3to4UpperTreshold + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(from3to4UpperTreshold, gbc);
        row++;

        from4to3LowerTresholdLabel = new JLabel("from 4 to 3 lower treshold: ");
        from4to3LowerTresholdLabel.setToolTipText("если количество пользователей вокруг меньше, чем нижний порог, то опять удаляет");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(from4to3LowerTresholdLabel, gbc);
        from4to3LowerTreshold = new JTextField(Main.settings.from4to3LowerTreshold + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(from4to3LowerTreshold, gbc);
        row++;
        from4to3UpperTresholdLabel = new JLabel("from 4 to 3 upper treshold: ");
        from4to3UpperTresholdLabel.setToolTipText("если количество пользователей вокруг превышает верхний порог, то остается пользователем");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(from4to3UpperTresholdLabel, gbc);
        from4to3UpperTreshold = new JTextField(Main.settings.from4to3UpperTreshold + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(from4to3UpperTreshold, gbc);
        row++;


        from1to2SelfChanceLabel = new JLabel("from 1 to 2 chance: ");
        from1to2SelfChanceLabel.setToolTipText("вероятность становления клетки пользователем в первыый раз");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(from1to2SelfChanceLabel, gbc);
        from1to2SelfChance = new JTextField(Main.settings.from1to2SelfChance + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(from1to2SelfChance, gbc);
        row++;

        from2to3SelfChanceLabel = new JLabel("from 2 to 3 chance: ");
        from2to3SelfChanceLabel.setToolTipText("вероятность удаления после первого использования");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(from2to3SelfChanceLabel, gbc);
        from2to3SelfChance = new JTextField(Main.settings.from2to3SelfChance + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(from2to3SelfChance, gbc);
        row++;

        from3to4SelfChanceLabel = new JLabel("from 3 to 4 chance: ");
        from3to4SelfChanceLabel.setToolTipText("вероятность повторного становления пользователем");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(from3to4SelfChanceLabel, gbc);
        from3to4SelfChance = new JTextField(Main.settings.from3to4SelfChance + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(from3to4SelfChance, gbc);
        row++;

        from4to3SelfChanceLabel = new JLabel("from 4 to 3 chance: ");
        from4to3SelfChanceLabel.setToolTipText("вероятность повторного удаления после повторного использования");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(from4to3SelfChanceLabel, gbc);
        from4to3SelfChance = new JTextField(Main.settings.from4to3SelfChance + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(from4to3SelfChance, gbc);
        row++;

        buffFromNegativeNeighbourLabel = new JLabel("buff from negative neighbour: ");
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.insets = new Insets(12, 0, 0, 0);
        add(buffFromNegativeNeighbourLabel, gbc);
        buffFromNegativeNeighbour = new JTextField(Main.settings.buffFromNegativeNeighbour + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(buffFromNegativeNeighbour, gbc);
        gbc.insets = new Insets(0, 0, 0, 0);
        row++;

        buffFromPositiveNeighbourLabel = new JLabel("buff from positive neighbour: ");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(buffFromPositiveNeighbourLabel, gbc);
        buffFromPositiveNeighbour = new JTextField(Main.settings.buffFromPositiveNeighbour + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(buffFromPositiveNeighbour, gbc);
        row++;

        buffFromNewNeighbourLabel = new JLabel("buff from new neighbour: ");
        gbc.gridx = 0;
        gbc.gridy = row;
        add(buffFromNewNeighbourLabel, gbc);
        buffFromNewNeighbour = new JTextField(Main.settings.buffFromNewNeighbour + "", 10);
        gbc.gridx = 1;
        gbc.gridy = row;
        add(buffFromNewNeighbour, gbc);
        row++;

        isChangingMindLabel = new JLabel("Will the cells refuse from using?");
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.insets = new Insets(20, 0, 20, 0);
        add(isChangingMindLabel, gbc);
        isChangingMind = new JCheckBox();
        gbc.gridx = 1;
        gbc.gridy = row;
        add(isChangingMind, gbc);
        gbc.insets = new Insets(0, 0, 0, 0);
        row++;

        start = new JButton("Go to Field Editor");
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Main.settings.isChangingMind = isChangingMind.isSelected();
                Main.settings.from1to2LowerTreshold = Integer.parseInt(from1to2LowerTreshold.getText());
                Main.settings.from2to3LowerTreshold = Integer.parseInt(from2to3LowerTreshold.getText());
                Main.settings.from3to4LowerTreshold = Integer.parseInt(from3to4LowerTreshold.getText());
                Main.settings.from1to2UpperTreshold = Integer.parseInt(from1to2UpperTreshold.getText());
                Main.settings.from2to3UpperTreshold = Integer.parseInt(from2to3UpperTreshold.getText());
                Main.settings.from3to4UpperTreshold = Integer.parseInt(from3to4UpperTreshold.getText());
                Main.settings.from4to3LowerTreshold = Integer.parseInt(from4to3LowerTreshold.getText());
                Main.settings.from4to3UpperTreshold = Integer.parseInt(from4to3UpperTreshold.getText());
                Main.settings.fieldWidth = Integer.parseInt(fieldWidth.getText());
                Main.settings.fieldHeight = Integer.parseInt(fieldHeight.getText());
//                Main.settings.from1to2SelfChance = Integer.parseInt(from1to2SelfChance.getText());
//                Main.settings.from2to3SelfChance = Integer.parseInt(from2to3SelfChance.getText());
//                Main.settings.from3to4SelfChance = Integer.parseInt(from3to4SelfChance.getText());
//                Main.settings.from4to3SelfChance = Integer.parseInt(from4to3SelfChance.getText());
//                Main.settings.buffFromNegativeNeighbour = Integer.parseInt(buffFromNegativeNeighbour.getText());
//                Main.settings.buffFromPositiveNeighbour = Integer.parseInt(buffFromPositiveNeighbour.getText());
//                Main.settings.buffFromNewNeighbour = Integer.parseInt(buffFromNewNeighbour.getText());
                Main.settings.from1to2SelfChance = Double.parseDouble(from1to2SelfChance.getText());
                Main.settings.from2to3SelfChance = Double.parseDouble(from2to3SelfChance.getText());
                Main.settings.from3to4SelfChance = Double.parseDouble(from3to4SelfChance.getText());
                Main.settings.from4to3SelfChance = Double.parseDouble(from4to3SelfChance.getText());
                Main.settings.buffFromNegativeNeighbour = Double.parseDouble(buffFromNegativeNeighbour.getText());
                Main.settings.buffFromPositiveNeighbour = Double.parseDouble(buffFromPositiveNeighbour.getText());
                Main.settings.buffFromNewNeighbour = Double.parseDouble(buffFromNewNeighbour.getText());

                Settings.saveSettings();
                Main.fieldManager.initializeBlack();
                setVisible(false);
                //Продолжить выполнение в программе             ПЕРЕХОД КО ВТОРОМУ ОКНУ
                FramesManager.readyForSimulation = true;

            }
        });
        add(start, gbc);
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - size().width / 2, screenSize.height / 2 - size().height / 2);
        setVisible(true);
    }


}
