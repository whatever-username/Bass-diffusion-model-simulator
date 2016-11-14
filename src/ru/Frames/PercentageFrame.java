package ru.Frames;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Innokentiy on 21.10.2016.
 */
public class PercentageFrame extends JFrame {
    JSlider blackPercentageSlider, bluePercentageSlider , redPercentageSlider, greenPercentageSlider;

    JLabel blackPercentage,bluePercentage,redPercentage,greenPercentage;
    public PercentageFrame() {


        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        setPreferredSize(new Dimension(400, 100));

        JLabel blackPercentageLabel = new JLabel("Black percentage: ");
        JLabel bluePercentageLabel = new JLabel("Blue percentage: ");
        JLabel redPercentageLabel = new JLabel("Red percentage: ");
        JLabel greenPercentageLabel = new JLabel("Green percentage: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(blackPercentageLabel, gbc);
        gbc.gridy = 1;
        add(bluePercentageLabel, gbc);
        gbc.gridy = 2;
        add(redPercentageLabel, gbc);
        gbc.gridy = 3;
        add(greenPercentageLabel, gbc);
        blackPercentage = new JLabel("25");
        bluePercentage = new JLabel("25");
        redPercentage = new JLabel("25");
        greenPercentage = new JLabel("25");
        blackPercentageSlider = new JSlider(0, 100, 25);
        bluePercentageSlider = new JSlider(0, 100, 25);
        redPercentageSlider = new JSlider(0, 100, 25);
        greenPercentageSlider = new JSlider(0, 100, 25);
        JSlider[] array = new JSlider[4];
        array[0] = blackPercentageSlider;
        array[1] = blackPercentageSlider;
        array[2] = redPercentageSlider;
        array[3] = greenPercentageSlider;

        blackPercentageSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                blackPercentage.setText(blackPercentageSlider.getValue() + "");
            }
        });
        bluePercentageSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                bluePercentage.setText(bluePercentageSlider.getValue() + "");
            }
        });
        redPercentageSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                redPercentage.setText(redPercentageSlider.getValue() + "");
            }
        });
        greenPercentageSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                greenPercentage.setText(greenPercentageSlider.getValue() + "");
            }
        });


        gbc.gridx = 1;
        gbc.gridy = 0;
        add(blackPercentage, gbc);
        gbc.gridy = 1;
        add(bluePercentage, gbc);
        gbc.gridy = 2;
        add(redPercentage, gbc);
        gbc.gridy = 3;
        add(greenPercentage, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(blackPercentageSlider, gbc);
        gbc.gridy = 1;
        add(bluePercentageSlider, gbc);
        gbc.gridy = 2;
        add(redPercentageSlider, gbc);
        gbc.gridy = 3;
        add(greenPercentageSlider, gbc);
        pack();

    }
    int sum(){
        return blackPercentageSlider.getValue()+bluePercentageSlider.getValue()+redPercentageSlider.getValue()+greenPercentageSlider.getValue();
    }
}
