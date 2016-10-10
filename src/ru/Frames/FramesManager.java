package ru.Frames;

import ru.AppContext;
import ru.CellInterface;
import ru.Main;
import ru.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

/**
 * Created by Innokentiy on 11.09.2016.
 */
public class FramesManager {
    public AppContext context;
    public SettingsFrame settingsFrame;
    public SimulationFrame simulationFrame;
    public void initializeFrames(String settingsOutput) throws IOException, ClassNotFoundException, InterruptedException {
        settingsFrame = new SettingsFrame(context);
        simulationFrame = new SimulationFrame(context);
        FramesInitializer framesInitializer = new FramesInitializer("settings", settingsOutput);
        Thread thread = new Thread(framesInitializer);
        thread.start(); //поток, в котором создается окно с параметрами
        thread.join();  //выход из этого потока и продолжение main после нажатия клавиши
        framesInitializer.frame="simulation";
        thread = new Thread(framesInitializer);
        thread.start();
        thread.join();

        System.out.println("конец");



    }
    public class FramesInitializer implements Runnable {
        String frame;
        String settingsOutput;
        public FramesInitializer(String frame, String settingsOutput){
            this.frame=frame;
            this.settingsOutput = settingsOutput;
        }
        @Override
        public void run() {
            switch (frame){
                case "settings":
                    System.out.println("запущено окно настроек");
                    try {
                        settingsFrame.init(settingsOutput);
                    }catch (Exception e){

                        e.printStackTrace();
                    }


                    break;
                case "simulation":
                    try{
                        System.out.println("запущено окно симуляции");
                        simulationFrame.init();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                default:
                    throw new RuntimeException("wrong frame");
            }
        }
    }
    public void render() throws InterruptedException {

            // render
        BufferStrategy buffer = simulationFrame.c.getBufferStrategy();
        Graphics2D graph = (Graphics2D) buffer.getDrawGraphics();
        graph.setColor(Color.blue);

            if (context.renderingField == 1) {
                renderCells(context.field);
            }
            if (context.renderingField == 2) {
                //render 2 sloi
            }

        simulationFrame.c.getGraphics().dispose();
        buffer.show();
    }
    public void renderCells(CellInterface _field[][]) {            //метод, отрисовывающий все клетки на обычном поле
        BufferStrategy buffer = simulationFrame.c.getBufferStrategy();
        Graphics2D graph = (Graphics2D) buffer.getDrawGraphics();
        if (context.renderingField == 1) {  //рисовать поле клеток
            for (int height = 0; height < context.settings.fieldHeight; height++) {
                for (int width = 0; width < context.settings.fieldHeight; width++) {
                    //0-не пользовавшийся, 1- новый , 2 -удаливший, 3- пользующийся повторноновый
                    try {


                        switch (_field[height][width].getType()) {
                            case 0:
                                graph.setColor(Color.BLACK);
                                graph.fillRect(width * (600 / context.settings.fieldWidth), height * (600 / context.settings.fieldHeight), (600 / context.settings.fieldWidth), (600 / context.settings.fieldHeight));
                                break;
                            case 1:
                                graph.setColor(Color.BLUE);
                                graph.fillRect(width * (600 / context.settings.fieldWidth), height * (600 / context.settings.fieldHeight), (600 / context.settings.fieldWidth), (600 / context.settings.fieldHeight));
                                break;
                            case 2:
                                graph.setColor(Color.RED);
                                graph.fillRect(width * (600 / context.settings.fieldWidth), height * (600 / context.settings.fieldHeight), (600 / context.settings.fieldWidth), (600 / context.settings.fieldHeight));
                                break;
                            case 3:
                                graph.setColor(Color.GREEN);
                                graph.fillRect(width * (600 / context.settings.fieldWidth), height * (600 / context.settings.fieldHeight), (600 / context.settings.fieldWidth), (600 / context.settings.fieldHeight));
                                break;
                        }
                    }catch (NullPointerException ex){
                        System.out.println("on rendering "+height+";"+width);
                    }
                }
            }

        }
        simulationFrame.c.getGraphics().dispose();
        buffer.show();
    }
}
