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
    public static int renderingField=1; //номер поля, отображаемого в тек. момент
    public static int areaEffectDiameter = 0; // радиус воздействия точки-маяка
    public static Color color;
    public static boolean ready=false;
    public static Settings settings;
    public static FieldManager fieldManager;
    public static boolean processing = false;
    public static Cell field[][];
    public static Cell bufferField[][];
    public static GraphicsOutputManager manager;
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        manager = new GraphicsOutputManager();
        InitThread thread = new InitThread();

        thread.run();

        FieldManager.initializeBlack();
        while (true){
            if (FramesManager.simulationFrame!=null){
                FramesManager.render();
                if (!processing){
                    FieldManager.update(field);
                }
                else {
                    FieldManager.test();


                }
            }else {
                Thread.sleep(10);
            }
        }

    }
}

