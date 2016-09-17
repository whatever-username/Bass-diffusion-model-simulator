package ru;

import java.io.*;

/**
 * Created by Innokentiy on 21.05.2016.
 * 23:51
 * используется для запоминания параметров
 */
public class Settings implements Serializable{
    public int
            from1to2LowerTreshold, from2to3LowerTreshold,from3to4LowerTreshold,from4to3LowerTreshold,
            from1to2UpperTreshold, from2to3UpperTreshold,from3to4UpperTreshold,from4to3UpperTreshold,
            from1to2SelfChance,from2to3SelfChance,from3to4SelfChance,from4to3SelfChance,
            buffFromPositiveNeighbour,buffFromNegativeNeighbour,buffFromNewNeighbour,
            fieldWidth, fieldHeight;
    public boolean isChangingMind;
    public static void saveSettings() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("temp.out");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Settings settings = Main.settings;
        try {
            oos.writeObject(settings);
            oos.close();
            oos.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
    public static void createSettings() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("temp.out");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Settings settings = new Settings();
        settings.buffFromNegativeNeighbour = 0;
        settings.buffFromPositiveNeighbour = 0;
        settings.buffFromNewNeighbour = 1;
        settings.from1to2LowerTreshold = 0;
        settings.from2to3LowerTreshold = 0;
        settings.from3to4LowerTreshold = 0;
        settings.from4to3LowerTreshold = 0;
        settings.from1to2UpperTreshold = 8;
        settings.from2to3UpperTreshold = 8;
        settings.from3to4UpperTreshold = 8;
        settings.from4to3UpperTreshold = 8;
        ;
        settings.from1to2SelfChance = 1;
        settings.from2to3SelfChance = 0;
        settings.from3to4SelfChance = 0;
        settings.from4to3SelfChance = 0;
        settings.isChangingMind = false;
        settings.fieldHeight = 100;
        settings.fieldWidth = 100;


        try {
            oos.writeObject(settings);
            oos.close();
            oos.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}