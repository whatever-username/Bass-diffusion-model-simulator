package ru;


import ru.Frames.FramesManager;
import ru.Frames.SettingsFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by техносила on 09.09.2016.
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        AppContext context = new AppContext();

        context.framesManager.initializeFrames("settings.out");
        while(true){
            context.framesManager.render();
            if (context.processing==false){
                context.fieldManager.update(context.field);
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
        while (context.fieldManager.usersCount != 10000){
            context.fieldManager.calculate();

        }
        context.graphicsOutputManager.writeToFile(context.graphicsOutputManager.outputPointsArray,"C:\\Users\\техносила\\Desktop\\Temp\\model.csv");
        List<Double> Bass = noVisual.BassModel(0.01,0.89,context.fieldManager.stage);
        context.graphicsOutputManager.writeToFile(Bass,"C:\\Users\\техносила\\Desktop\\Temp\\bass.csv");
        System.out.println(noVisual.compare(context.graphicsOutputManager.outputPointsArray,Bass));*/


//        noVisual.findpairWithMaxCorrelation();




//        NoVisual noVisual = new NoVisual(context);
//        context.settings = Settings.loadSettings("settings.out");//грузит базовые настройки
//
//        noVisual.calculate();

    }
}

