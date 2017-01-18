package ru;


import ru.Frames.FramesManager;
import ru.Frames.SettingsFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        AppContext context = new AppContext();
        Util.setContext(context);

        context.framesManager.initializeFrames("settings.out");
        while(true){

            context.framesManager.render();
            if (context.processing==false){
                    context.fieldManager.update();

            }
            else if (context.fieldManager.usersCount != context.settings.fieldWidth*context.settings.fieldHeight){
                context.fieldManager.calculate();

            }
        }

        /*NoVisual noVisual = new NoVisual(context);
        context.settings=Settings.loadSettings("settings.out");
        context.settings.buffFromNewNeighbour=1.4;
        context.fieldManager.stage=0;
        context.fieldManager.usersCount=0;
        context.graphicsOutputManager.outputPointsArray = new ArrayList<>();
        context.fieldManager.initializeBlack();
        context.field[50][50] = new Cell(1);
        noVisual.findpairWithMaxCorrelation();*/

        /*NoVisual noVisual = new NoVisual(context);
        context.settings=Settings.loadSettings("settings.out");
        context.settings.buffFromNewNeighbour = 0.1;
        context.settings.from2to3SelfChance = 0.0;
        context.fieldManager.stage = 0;
        context.fieldManager.usersCount = 0;
        context.graphicsOutputManager.outputPointsArray = new ArrayList<>();
        context.fieldManager.initializeBlack();
        context.field[50][50] = new Cell(1);
        while (context.fieldManager.usersCount != 10000) {
            context.fieldManager.calculate();

        }
        List<Double> Bass = noVisual.BassModel(0.11, 0.63, context.fieldManager.stage);
        context.graphicsOutputManager.writeToFile(Bass, "C:\\Users\\техносила\\Desktop\\testModel\\011_063_Bass_2.csv");
        context.graphicsOutputManager.writeToFile(context.graphicsOutputManager.outputPointsArray, "C:\\Users\\техносила\\Desktop\\testModel\\01_00_Model_2.csv");*/




//        NoVisual noVisual = new NoVisual(context);
//        context.settings = Settings.loadSettings("settings.out");//грузит базовые настройки
//
//        noVisual.calculate();

    }
}

