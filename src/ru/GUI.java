//package ru;
//
//import javax.swing.*;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferStrategy;
//import java.io.*;
//
///**
// * Created by Иннокентий on 09.09.2016.
// */
//public class GUI{
//    public static Canvas c = new Canvas() ;
//
////    public GUI() throws IOException, ClassNotFoundException {
////        JLabel
////                typeOfModelLabel, fieldWidthLabel, fieldHeightLabel,
////                from1to2LowerTresholdLabel, from2to3LowerTresholdLabel, from3to4LowerTresholdLabel, from4to3LowerTresholdLabel,
////                from1to2UpperTresholdLabel, from2to3UpperTresholdLabel, from3to4UpperTresholdLabel, from4to3UpperTresholdLabel,
////                from1to2SelfChanceLabel, from2to3SelfChanceLabel, from3to4SelfChanceLabel, from4to3SelfChanceLabel,
////                buffFromPositiveNeighbourLabel, buffFromNegativeNeighbourLabel, buffFromNewNeighbourLabel,
////                isChangingMindLabel;
////
////        JTextField
////                from1to2LowerTreshold, from2to3LowerTreshold, from3to4LowerTreshold, from4to3LowerTreshold,
////                from1to2UpperTreshold, from2to3UpperTreshold, from3to4UpperTreshold, from4to3UpperTreshold,
////                from1to2SelfChance, from2to3SelfChance, from3to4SelfChance, from4to3SelfChance,
////                buffFromPositiveNeighbour, buffFromNegativeNeighbour, buffFromNewNeighbour,
////                fieldWidth, fieldHeight;
////
////        JCheckBox isChangingMind;
////        JButton start;
////        JComboBox wayList;
////        String[] ways = {"Fill in randomly", "Blank field", "DNR Edition"};
////        GridBagConstraints gbc = new GridBagConstraints();
////        File settingsFile = new File("temp.out");
////        if (settingsFile.exists()!=true){
////            Settings.createSettings();
////        }
////        FileInputStream fis = new FileInputStream(settingsFile);
////
////        ObjectInputStream oin = new ObjectInputStream(fis);
////        context.settings = (Settings) oin.readObject();
////
////        int row = 0;
////
////        setLayout(new GridBagLayout());
////
////        typeOfModelLabel = new JLabel("type of simulation: ");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        gbc.insets = new Insets(0, 0, 8, 0);
////        add(typeOfModelLabel, gbc);
////        wayList = new JComboBox(ways);
////        wayList.setSelectedIndex(0);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(wayList, gbc);
////        gbc.insets = new Insets(0, 0, 0, 0);
////        row++;
////
////
////        fieldWidthLabel = new JLabel("field's width: ");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(fieldWidthLabel, gbc);
////        fieldWidth = new JTextField((context.settings.fieldWidth + ""), 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(fieldWidth, gbc);
////        row++;
////
////        fieldHeightLabel = new JLabel("field's height: ");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        gbc.insets = new Insets(0, 0, 8, 0);
////        add(fieldHeightLabel, gbc);
////        fieldHeight = new JTextField(context.settings.fieldHeight + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(fieldHeight, gbc);
////        gbc.insets = new Insets(0, 0, 0, 0);
////        row++;
////        from1to2LowerTresholdLabel = new JLabel("from 1 to 2 lower treshold: ");
////        from1to2LowerTresholdLabel.setToolTipText("Eсли количество пользователей вокруг меньше, чем нижний порог, то остается незанятой");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(from1to2LowerTresholdLabel, gbc);
////        from1to2LowerTreshold = new JTextField(context.settings.from1to2LowerTreshold + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(from1to2LowerTreshold, gbc);
////        row++;
////
////        from1to2UpperTresholdLabel = new JLabel("from 1 to 2 upper treshold: ");
////        from1to2UpperTresholdLabel.setToolTipText("Если количество пользователей вокруг превышает верхний порог, то становится новым пользователем");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(from1to2UpperTresholdLabel, gbc);
////        from1to2UpperTreshold = new JTextField(context.settings.from1to2UpperTreshold + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(from1to2UpperTreshold, gbc);
////        row++;
////
////
////        row++;
////        from2to3LowerTresholdLabel = new JLabel("from 2 to 3 lower treshold: ");
////        from2to3LowerTresholdLabel.setToolTipText("если количество пользователей вокруг меньше, чем нижний порог, то удалит приложение");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(from2to3LowerTresholdLabel, gbc);
////        from2to3LowerTreshold = new JTextField(context.settings.from2to3LowerTreshold + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(from2to3LowerTreshold, gbc);
////        row++;
////        from2to3UpperTresholdLabel = new JLabel("from 2 to 3 upper treshold: ");
////        from2to3UpperTresholdLabel.setToolTipText("если количество пользователей вокруг превышает верхний порог, то останется пользователем");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(from2to3UpperTresholdLabel, gbc);
////        from2to3UpperTreshold = new JTextField(context.settings.from2to3UpperTreshold + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(from2to3UpperTreshold, gbc);
////
////        row++;
////        from3to4LowerTresholdLabel = new JLabel("from 3 to 4 lower treshold: ");
////        from3to4LowerTresholdLabel.setToolTipText("если количество пользователей вокруг меньше, чем нижний порог, то остается удалившим");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(from3to4LowerTresholdLabel, gbc);
////        from3to4LowerTreshold = new JTextField(context.settings.from3to4LowerTreshold + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(from3to4LowerTreshold, gbc);
////        row++;
////        from3to4UpperTresholdLabel = new JLabel("from 3 to 4 upper treshold: ");
////        from3to4UpperTresholdLabel.setToolTipText("если количество пользователей вокруг превышает верхний порог, то повторно становится пользователем");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(from3to4UpperTresholdLabel, gbc);
////        from3to4UpperTreshold = new JTextField(context.settings.from3to4UpperTreshold + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(from3to4UpperTreshold, gbc);
////        row++;
////
////        from4to3LowerTresholdLabel = new JLabel("from 4 to 3 lower treshold: ");
////        from4to3LowerTresholdLabel.setToolTipText("если количество пользователей вокруг меньше, чем нижний порог, то опять удаляет");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(from4to3LowerTresholdLabel, gbc);
////        from4to3LowerTreshold = new JTextField(context.settings.from4to3LowerTreshold + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(from4to3LowerTreshold, gbc);
////        row++;
////        from4to3UpperTresholdLabel = new JLabel("from 4 to 3 upper treshold: ");
////        from4to3UpperTresholdLabel.setToolTipText("если количество пользователей вокруг превышает верхний порог, то остается пользователем");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(from4to3UpperTresholdLabel, gbc);
////        from4to3UpperTreshold = new JTextField(context.settings.from4to3UpperTreshold + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(from4to3UpperTreshold, gbc);
////        row++;
////
////
////        from1to2SelfChanceLabel = new JLabel("from 1 to 2 chance: ");
////        from1to2SelfChanceLabel.setToolTipText("вероятность становления клетки пользователем в первыый раз");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(from1to2SelfChanceLabel, gbc);
////        from1to2SelfChance = new JTextField(context.settings.from1to2SelfChance + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(from1to2SelfChance, gbc);
////        row++;
////
////        from2to3SelfChanceLabel = new JLabel("from 2 to 3 chance: ");
////        from2to3SelfChanceLabel.setToolTipText("вероятность удаления после первого использования");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(from2to3SelfChanceLabel, gbc);
////        from2to3SelfChance = new JTextField(context.settings.from2to3SelfChance + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(from2to3SelfChance, gbc);
////        row++;
////
////        from3to4SelfChanceLabel = new JLabel("from 3 to 4 chance: ");
////        from3to4SelfChanceLabel.setToolTipText("вероятность повторного становления пользователем");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(from3to4SelfChanceLabel, gbc);
////        from3to4SelfChance = new JTextField(context.settings.from3to4SelfChance + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(from3to4SelfChance, gbc);
////        row++;
////
////        from4to3SelfChanceLabel = new JLabel("from 4 to 3 chance: ");
////        from4to3SelfChanceLabel.setToolTipText("вероятность повторного удаления после повторного использования");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(from4to3SelfChanceLabel, gbc);
////        from4to3SelfChance = new JTextField(context.settings.from4to3SelfChance + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(from4to3SelfChance, gbc);
////        row++;
////
////        buffFromNegativeNeighbourLabel = new JLabel("buff from negative neighbour: ");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        gbc.insets = new Insets(12, 0, 0, 0);
////        add(buffFromNegativeNeighbourLabel, gbc);
////        buffFromNegativeNeighbour = new JTextField(context.settings.buffFromNegativeNeighbour + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(buffFromNegativeNeighbour, gbc);
////        gbc.insets = new Insets(0, 0, 0, 0);
////        row++;
////
////        buffFromPositiveNeighbourLabel = new JLabel("buff from positive neighbour: ");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(buffFromPositiveNeighbourLabel, gbc);
////        buffFromPositiveNeighbour = new JTextField(context.settings.buffFromPositiveNeighbour + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(buffFromPositiveNeighbour, gbc);
////        row++;
////
////        buffFromNewNeighbourLabel = new JLabel("buff from new neighbour: ");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        add(buffFromNewNeighbourLabel, gbc);
////        buffFromNewNeighbour = new JTextField(context.settings.buffFromNewNeighbour + "", 10);
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(buffFromNewNeighbour, gbc);
////        row++;
////
////        isChangingMindLabel = new JLabel("Will the cells refuse from using?");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        gbc.insets = new Insets(20, 0, 20, 0);
////        add(isChangingMindLabel, gbc);
////        isChangingMind = new JCheckBox();
////        gbc.gridx = 1;
////        gbc.gridy = row;
////        add(isChangingMind, gbc);
////        gbc.insets = new Insets(0, 0, 0, 0);
////        row++;
////
////        start = new JButton("Go to Field Editor");
////        gbc.gridx = 0;
////        gbc.gridy = row;
////        gbc.gridwidth = 2;
////        start.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////
////
////                context.settings.isChangingMind = isChangingMind.isSelected();
////                context.settings.from1to2LowerTreshold = Integer.parseInt(from1to2LowerTreshold.getText());
////                context.settings.from2to3LowerTreshold = Integer.parseInt(from2to3LowerTreshold.getText());
////                context.settings.from3to4LowerTreshold = Integer.parseInt(from3to4LowerTreshold.getText());
////                context.settings.from1to2UpperTreshold = Integer.parseInt(from1to2UpperTreshold.getText());
////                context.settings.from2to3UpperTreshold = Integer.parseInt(from2to3UpperTreshold.getText());
////                context.settings.from3to4UpperTreshold = Integer.parseInt(from3to4UpperTreshold.getText());
////                context.settings.from4to3LowerTreshold = Integer.parseInt(from4to3LowerTreshold.getText());
////                context.settings.from4to3UpperTreshold = Integer.parseInt(from4to3UpperTreshold.getText());
////                context.settings.fieldWidth = Integer.parseInt(fieldWidth.getText());
////                context.settings.fieldHeight = Integer.parseInt(fieldHeight.getText());
////                context.settings.from1to2SelfChance = Integer.parseInt(from1to2SelfChance.getText());
////                context.settings.from2to3SelfChance = Integer.parseInt(from2to3SelfChance.getText());
////                context.settings.from3to4SelfChance = Integer.parseInt(from3to4SelfChance.getText());
////                context.settings.from4to3SelfChance = Integer.parseInt(from4to3SelfChance.getText());
////                context.settings.buffFromNegativeNeighbour = Integer.parseInt(buffFromNegativeNeighbour.getText());
////                context.settings.buffFromPositiveNeighbour = Integer.parseInt(buffFromPositiveNeighbour.getText());
////                context.settings.buffFromNewNeighbour = Integer.parseInt(buffFromNewNeighbour.getText());
////
////                Settings.saveSettings();
////                context.settingsFrame.setVisible(false);
////                System.out.println(context.settings.fieldWidth + ";" + context.settings.fieldHeight);
////                //Продолжить выполнение в программе                                                                     ПЕРЕХОД КО ВТОРОМУ ОКНУ
////                context.simulationFrame.setVisible(true);
////
////
////            }
////        });
////        add(start, gbc);
////
////        ///////////////////////////////////////
////
////
////    }
//
//    public static JFrame  initSettingsFrame () throws IOException, ClassNotFoundException, InterruptedException {
//
//        JFrame frame;
//        frame= new JFrame("Game of Life");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        JLabel
//                typeOfModelLabel, fieldWidthLabel, fieldHeightLabel,
//                from1to2LowerTresholdLabel, from2to3LowerTresholdLabel, from3to4LowerTresholdLabel, from4to3LowerTresholdLabel,
//                from1to2UpperTresholdLabel, from2to3UpperTresholdLabel, from3to4UpperTresholdLabel, from4to3UpperTresholdLabel,
//                from1to2SelfChanceLabel, from2to3SelfChanceLabel, from3to4SelfChanceLabel, from4to3SelfChanceLabel,
//                buffFromPositiveNeighbourLabel, buffFromNegativeNeighbourLabel, buffFromNewNeighbourLabel,
//                isChangingMindLabel;
//
//        JTextField
//                from1to2LowerTreshold, from2to3LowerTreshold, from3to4LowerTreshold, from4to3LowerTreshold,
//                from1to2UpperTreshold, from2to3UpperTreshold, from3to4UpperTreshold, from4to3UpperTreshold,
//                from1to2SelfChance, from2to3SelfChance, from3to4SelfChance, from4to3SelfChance,
//                buffFromPositiveNeighbour, buffFromNegativeNeighbour, buffFromNewNeighbour,
//                fieldWidth, fieldHeight;
//
//        JCheckBox isChangingMind;
//        JButton start;
//        JComboBox wayList;
//        String[] ways = {"Fill in randomly", "Blank field", "DNR Edition"};
//        GridBagConstraints gbc = new GridBagConstraints();
//        File settingsFile = new File("temp.out");
//        if (settingsFile.exists()!=true){
//            Settings.createSettings();
//        }
//        FileInputStream fis = new FileInputStream(settingsFile);
//
//        ObjectInputStream oin = new ObjectInputStream(fis);
//        context.settings = (Settings) oin.readObject();
//
//        int row = 0;
//
//        frame.setLayout(new GridBagLayout());
//
//        typeOfModelLabel = new JLabel("type of simulation: ");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        gbc.insets = new Insets(0, 0, 8, 0);
//        frame.add(typeOfModelLabel, gbc);
//        wayList = new JComboBox(ways);
//        wayList.setSelectedIndex(0);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(wayList, gbc);
//        gbc.insets = new Insets(0, 0, 0, 0);
//        row++;
//
//
//        fieldWidthLabel = new JLabel("field's width: ");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(fieldWidthLabel, gbc);
//        fieldWidth = new JTextField((context.settings.fieldWidth + ""), 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(fieldWidth, gbc);
//        row++;
//
//        fieldHeightLabel = new JLabel("field's height: ");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        gbc.insets = new Insets(0, 0, 8, 0);
//        frame.add(fieldHeightLabel, gbc);
//        fieldHeight = new JTextField(context.settings.fieldHeight + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(fieldHeight, gbc);
//        gbc.insets = new Insets(0, 0, 0, 0);
//        row++;
//        from1to2LowerTresholdLabel = new JLabel("from 1 to 2 lower treshold: ");
//        from1to2LowerTresholdLabel.setToolTipText("Eсли количество пользователей вокруг меньше, чем нижний порог, то остается незанятой");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(from1to2LowerTresholdLabel, gbc);
//        from1to2LowerTreshold = new JTextField(context.settings.from1to2LowerTreshold + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(from1to2LowerTreshold, gbc);
//        row++;
//
//        from1to2UpperTresholdLabel = new JLabel("from 1 to 2 upper treshold: ");
//        from1to2UpperTresholdLabel.setToolTipText("Если количество пользователей вокруг превышает верхний порог, то становится новым пользователем");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(from1to2UpperTresholdLabel, gbc);
//        from1to2UpperTreshold = new JTextField(context.settings.from1to2UpperTreshold + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(from1to2UpperTreshold, gbc);
//        row++;
//
//
//        row++;
//        from2to3LowerTresholdLabel = new JLabel("from 2 to 3 lower treshold: ");
//        from2to3LowerTresholdLabel.setToolTipText("если количество пользователей вокруг меньше, чем нижний порог, то удалит приложение");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(from2to3LowerTresholdLabel, gbc);
//        from2to3LowerTreshold = new JTextField(context.settings.from2to3LowerTreshold + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(from2to3LowerTreshold, gbc);
//        row++;
//        from2to3UpperTresholdLabel = new JLabel("from 2 to 3 upper treshold: ");
//        from2to3UpperTresholdLabel.setToolTipText("если количество пользователей вокруг превышает верхний порог, то останется пользователем");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(from2to3UpperTresholdLabel, gbc);
//        from2to3UpperTreshold = new JTextField(context.settings.from2to3UpperTreshold + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(from2to3UpperTreshold, gbc);
//
//        row++;
//        from3to4LowerTresholdLabel = new JLabel("from 3 to 4 lower treshold: ");
//        from3to4LowerTresholdLabel.setToolTipText("если количество пользователей вокруг меньше, чем нижний порог, то остается удалившим");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(from3to4LowerTresholdLabel, gbc);
//        from3to4LowerTreshold = new JTextField(context.settings.from3to4LowerTreshold + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(from3to4LowerTreshold, gbc);
//        row++;
//        from3to4UpperTresholdLabel = new JLabel("from 3 to 4 upper treshold: ");
//        from3to4UpperTresholdLabel.setToolTipText("если количество пользователей вокруг превышает верхний порог, то повторно становится пользователем");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(from3to4UpperTresholdLabel, gbc);
//        from3to4UpperTreshold = new JTextField(context.settings.from3to4UpperTreshold + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(from3to4UpperTreshold, gbc);
//        row++;
//
//        from4to3LowerTresholdLabel = new JLabel("from 4 to 3 lower treshold: ");
//        from4to3LowerTresholdLabel.setToolTipText("если количество пользователей вокруг меньше, чем нижний порог, то опять удаляет");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(from4to3LowerTresholdLabel, gbc);
//        from4to3LowerTreshold = new JTextField(context.settings.from4to3LowerTreshold + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(from4to3LowerTreshold, gbc);
//        row++;
//        from4to3UpperTresholdLabel = new JLabel("from 4 to 3 upper treshold: ");
//        from4to3UpperTresholdLabel.setToolTipText("если количество пользователей вокруг превышает верхний порог, то остается пользователем");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(from4to3UpperTresholdLabel, gbc);
//        from4to3UpperTreshold = new JTextField(context.settings.from4to3UpperTreshold + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(from4to3UpperTreshold, gbc);
//        row++;
//
//
//        from1to2SelfChanceLabel = new JLabel("from 1 to 2 chance: ");
//        from1to2SelfChanceLabel.setToolTipText("вероятность становления клетки пользователем в первыый раз");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(from1to2SelfChanceLabel, gbc);
//        from1to2SelfChance = new JTextField(context.settings.from1to2SelfChance + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(from1to2SelfChance, gbc);
//        row++;
//
//        from2to3SelfChanceLabel = new JLabel("from 2 to 3 chance: ");
//        from2to3SelfChanceLabel.setToolTipText("вероятность удаления после первого использования");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(from2to3SelfChanceLabel, gbc);
//        from2to3SelfChance = new JTextField(context.settings.from2to3SelfChance + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(from2to3SelfChance, gbc);
//        row++;
//
//        from3to4SelfChanceLabel = new JLabel("from 3 to 4 chance: ");
//        from3to4SelfChanceLabel.setToolTipText("вероятность повторного становления пользователем");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(from3to4SelfChanceLabel, gbc);
//        from3to4SelfChance = new JTextField(context.settings.from3to4SelfChance + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(from3to4SelfChance, gbc);
//        row++;
//
//        from4to3SelfChanceLabel = new JLabel("from 4 to 3 chance: ");
//        from4to3SelfChanceLabel.setToolTipText("вероятность повторного удаления после повторного использования");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(from4to3SelfChanceLabel, gbc);
//        from4to3SelfChance = new JTextField(context.settings.from4to3SelfChance + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(from4to3SelfChance, gbc);
//        row++;
//
//        buffFromNegativeNeighbourLabel = new JLabel("buff from negative neighbour: ");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        gbc.insets = new Insets(12, 0, 0, 0);
//        frame.add(buffFromNegativeNeighbourLabel, gbc);
//        buffFromNegativeNeighbour = new JTextField(context.settings.buffFromNegativeNeighbour + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(buffFromNegativeNeighbour, gbc);
//        gbc.insets = new Insets(0, 0, 0, 0);
//        row++;
//
//        buffFromPositiveNeighbourLabel = new JLabel("buff from positive neighbour: ");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(buffFromPositiveNeighbourLabel, gbc);
//        buffFromPositiveNeighbour = new JTextField(context.settings.buffFromPositiveNeighbour + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(buffFromPositiveNeighbour, gbc);
//        row++;
//
//        buffFromNewNeighbourLabel = new JLabel("buff from new neighbour: ");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        frame.add(buffFromNewNeighbourLabel, gbc);
//        buffFromNewNeighbour = new JTextField(context.settings.buffFromNewNeighbour + "", 10);
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(buffFromNewNeighbour, gbc);
//        row++;
//
//        isChangingMindLabel = new JLabel("Will the cells refuse from using?");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        gbc.insets = new Insets(20, 0, 20, 0);
//        frame.add(isChangingMindLabel, gbc);
//        isChangingMind = new JCheckBox();
//        gbc.gridx = 1;
//        gbc.gridy = row;
//        frame.add(isChangingMind, gbc);
//        gbc.insets = new Insets(0, 0, 0, 0);
//        row++;
//
//        start = new JButton("Go to Field Editor");
//        gbc.gridx = 0;
//        gbc.gridy = row;
//        gbc.gridwidth = 2;
//        start.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//
//                context.settings.isChangingMind = isChangingMind.isSelected();
//                context.settings.from1to2LowerTreshold = Integer.parseInt(from1to2LowerTreshold.getText());
//                context.settings.from2to3LowerTreshold = Integer.parseInt(from2to3LowerTreshold.getText());
//                context.settings.from3to4LowerTreshold = Integer.parseInt(from3to4LowerTreshold.getText());
//                context.settings.from1to2UpperTreshold = Integer.parseInt(from1to2UpperTreshold.getText());
//                context.settings.from2to3UpperTreshold = Integer.parseInt(from2to3UpperTreshold.getText());
//                context.settings.from3to4UpperTreshold = Integer.parseInt(from3to4UpperTreshold.getText());
//                context.settings.from4to3LowerTreshold = Integer.parseInt(from4to3LowerTreshold.getText());
//                context.settings.from4to3UpperTreshold = Integer.parseInt(from4to3UpperTreshold.getText());
//                context.settings.fieldWidth = Integer.parseInt(fieldWidth.getText());
//                context.settings.fieldHeight = Integer.parseInt(fieldHeight.getText());
//                context.settings.from1to2SelfChance = Integer.parseInt(from1to2SelfChance.getText());
//                context.settings.from2to3SelfChance = Integer.parseInt(from2to3SelfChance.getText());
//                context.settings.from3to4SelfChance = Integer.parseInt(from3to4SelfChance.getText());
//                context.settings.from4to3SelfChance = Integer.parseInt(from4to3SelfChance.getText());
//                context.settings.buffFromNegativeNeighbour = Integer.parseInt(buffFromNegativeNeighbour.getText());
//                context.settings.buffFromPositiveNeighbour = Integer.parseInt(buffFromPositiveNeighbour.getText());
//                context.settings.buffFromNewNeighbour = Integer.parseInt(buffFromNewNeighbour.getText());
//                Settings.saveSettings();
//                context.settingsFrame.setVisible(false);
//                //Продолжить выполнение в программе             ПЕРЕХОД КО ВТОРОМУ ОКНУ
//                Field.field = Field.initializeBlack();
//                initSimulationFrame();
//
//
//
//
//
//
//            }
//        });
//        frame.add(start, gbc);
//        frame.pack();
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        frame.setLocation(screenSize.width / 2 - frame.size().width / 2, screenSize.height / 2 - frame.size().height / 2);
//        frame.setVisible(true);
//        return frame;
//    }
//    public static void initSimulationFrame(){             //инициализирует окно редактора и симуляции
//
//        JButton black, blue, red, green, cellField, supercellField;
//        JPanel colorButtons, layerButtons, commandPanel;
//        JSlider diameter;
//        JLabel diameterText, radiusText;
//        JFrame frame = new JFrame(BorderLayout.CENTER);
//        GridBagConstraints gbc = new GridBagConstraints();
//        frame.setLayout(new GridBagLayout());
//
//        red = new JButton("red");
//        black = new JButton("black");
//        blue = new JButton("blue");
//        green = new JButton("green");
//        JLabel colorText = new JLabel("Current: black");
//        colorText.setPreferredSize(new Dimension(90,20));
//        Canvas indicator = new Canvas();
//        indicator.setSize(new Dimension(20,20));
//        indicator.setBackground(Color.BLACK);
//        cellField = new JButton("cell layer");
//        cellField.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                context.renderingField= 1;
//            }
//        });
//        supercellField = new JButton("supercell layer");
//        supercellField.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                context.renderingField = 2;
//            }
//        });
//        diameter = new JSlider(JSlider.HORIZONTAL, 1, 500, 1);
//        diameter.setPreferredSize(new Dimension(140,20));
//        diameterText = new JLabel("Area of supercells: 0");
//        radiusText = new JLabel();
//        diameter.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                context.areaEffectDiameter = diameter.getValue();
//                diameterText.setText("Area of supercells: "+context.areaEffectDiameter);
//            }
//        });
//        red.setPreferredSize(new Dimension(70, 20));
//        red.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                context.color = Color.red;
//                colorText.setText("Current: red");
//                indicator.setBackground(Color.RED);
//            }
//        });
//        black.setPreferredSize(new Dimension(70, 20));
//        black.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                context.color = Color.black;
//                colorText.setText("Current: black");
//                indicator.setBackground(Color.BLACK);
//            }
//        });
//        blue.setPreferredSize(new Dimension(70, 20));
//        blue.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                context.color = Color.blue;
//                colorText.setText("Current: blue");
//                indicator.setBackground(Color.BLUE);
//            }
//        });
//        green.setPreferredSize(new Dimension(70, 20));
//        green.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                context.color = Color.green;
//                colorText.setText("Current: green");
//                indicator.setBackground(Color.GREEN);
//            }
//        });
//        frame.setTitle("Test text");
//        frame.setResizable(false);
//        c.setSize(600, 600);
//        int line=0; int colomn=0;
//
//
//        gbc.insets = new Insets(2,2,2,2);
//        frame.add(colorText,gbc);   // 0;0
//        colomn++;
//        gbc.gridx=colomn; gbc.gridy=line;
//        frame.add(green,gbc);   //1;0
//        colomn++;
//        gbc.gridx=colomn; gbc.gridy=line;
//        frame.add(blue,gbc);    //1;1
//        colomn-=2; line++;
//        gbc.gridx=colomn; gbc.gridy=line;
//        frame.add(indicator,gbc);
//        colomn++;
//        gbc.gridx = colomn; gbc.gridy=line;
//        frame.add(red,gbc);     //2;0
//        colomn++;
//        gbc.gridx=colomn; gbc.gridy=line;
//        frame.add(black,gbc);   //2;1
//        gbc.insets = new Insets(0,20,0,0);
//
//        JLabel layerText = new JLabel("Layer:");
//        line--; colomn++;
//        gbc.gridx=colomn; gbc.gridy=line;
//        frame.add(layerText,gbc);
//        JButton layerButton = new JButton("Cells");
//        layerButton.setPreferredSize(new Dimension(100,20));
//        layerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (context.renderingField ==1){
//                    context.renderingField =2;
//                    layerButton.setText("Supercells");
//
//                }                                       // ЗАМЕНИТЬ НА TRUE FALSE
//                else if (context.renderingField==2){
//                    context.renderingField=1;
//                    layerButton.setText("Cells");
//                }
//            }
//        });
//        line++;
//        gbc.gridy=line;
//        frame.add(layerButton,gbc);
//        colomn++;
//        gbc.gridx=colomn; gbc.gridwidth=2;
//        frame.add(diameter,gbc);
//        line--;
//        gbc.gridy=line; gbc.gridwidth=1;
//        diameterText.setPreferredSize(new Dimension(135,20));
//        frame.add(diameterText,gbc);
//        gbc.insets = new Insets(0,0,0,0);
//
//        colomn=0; line=3;
//        gbc.gridx=colomn; gbc.gridy=line; gbc.gridwidth=15; gbc.gridheight=1;
//        frame.add(c,gbc);
//        JButton startButton = new JButton("Start simulation");
//        startButton.setPreferredSize(new Dimension(150,40));
//        gbc.gridy=4; gbc.insets = new Insets(5,0,5,0);
//
//        startButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                                                            //НАЧАЛО СИМУЛЯЦИИ
//            }
//        });
//        frame.add(startButton,gbc);
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//        frame.setLayout(null);
//        frame.setVisible(false);
//        c.createBufferStrategy(2);    // ex. NUM_BUFFERS
//        Mouse.create(c);
//        Keyboard.create(c);
//
//        frame.setVisible(true);
//        context.simulationFrame = frame;
//
//    }
//    public static void render() {
//
//            // render
//        BufferStrategy buffer = c.getBufferStrategy();
//        Graphics2D graph = (Graphics2D) buffer.getDrawGraphics();
//        graph.setColor(Color.blue);
//
//            if (context.renderingField == 1) {
//                renderCells(Field.field);
//            }
//            if (context.renderingField == 2) {
//                //render 2 sloi
//            }
//
//        c.getGraphics().dispose();
//        buffer.show();
//    }
//    public static void renderCells(CellInterface _field[][]) {            //метод, отрисовывающий все клетки на обычном поле
//        BufferStrategy buffer = c.getBufferStrategy();
//        Graphics2D graph = (Graphics2D) buffer.getDrawGraphics();
//        if (context.renderingField == 1) {  //рисовать поле клеток
//            for (int height = 0; height < context.settings.fieldHeight; height++) {
//                for (int width = 0; width < context.settings.fieldHeight; width++) {
//                    //0-не пользовавшийся, 1- новый , 2 -удаливший, 3- пользующийся повторноновый
//
//                    switch (_field[height][width].getType()) {
//                        case 0:
//                            graph.setColor(Color.BLACK);
//                            graph.fillRect(width * (600 / context.settings.fieldWidth), height * (600 / context.settings.fieldHeight), (600 / context.settings.fieldWidth), (600 / context.settings.fieldHeight));
//                            break;
//                        case 1:
//                            graph.setColor(Color.BLUE);
//                            graph.fillRect(width * (600 / context.settings.fieldWidth), height * (600 / context.settings.fieldHeight), (600 / context.settings.fieldWidth), (600 / context.settings.fieldHeight));
//                            break;
//                        case 2:
//                            graph.setColor(Color.RED);
//                            graph.fillRect(width * (600 / context.settings.fieldWidth), height * (600 / context.settings.fieldHeight), (600 / context.settings.fieldWidth), (600 / context.settings.fieldHeight));
//                            break;
//                        case 3:
//                            graph.setColor(Color.GREEN);
//                            graph.fillRect(width * (600 / context.settings.fieldWidth), height * (600 / context.settings.fieldHeight), (600 / context.settings.fieldWidth), (600 / context.settings.fieldHeight));
//                            break;
//                    }
//                }
//            }
//
//        }
//        c.getGraphics().dispose();
//        buffer.show();
//    }
//
//
//}
