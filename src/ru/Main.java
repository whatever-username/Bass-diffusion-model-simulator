package ru;

import ru.Frames.FramesManager;
import ru.Frames.SettingsFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;

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
            else {
                context.fieldManager.calculate();

            }
        }


//
//        NoVisual noVisual = new NoVisual(context);
//        context.settings = Settings.loadSettings("settings.out");//грузит базовые настройки
//
//        noVisual.calculate();

    }
}

