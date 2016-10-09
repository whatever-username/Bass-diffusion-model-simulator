package ru.Frames;

import ru.CellInterface;
import ru.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

/**
 * Created by Innokentiy on 11.09.2016.
 */
public class FramesManager {
    public static SettingsFrame settingsFrame;
    public static SimulationFrame simulationFrame;
    public static boolean readyForSimulation = false;
    public static void initializeFrames() throws IOException, ClassNotFoundException, InterruptedException {

        while (true){
            if (readyForSimulation == false){
                Thread.sleep(10);
            }else {
                break;
            }
        }



        simulationFrame = new SimulationFrame();
        simulationFrame.setVisible(true);


    }
    public static void render() throws InterruptedException {

            // render
        BufferStrategy buffer = simulationFrame.c.getBufferStrategy();
        Graphics2D graph = (Graphics2D) buffer.getDrawGraphics();
        graph.setColor(Color.blue);

            if (Main.renderingField == 1) {
                renderCells(Main.field);
            }
            if (Main.renderingField == 2) {
                //render 2 sloi
            }

        simulationFrame.c.getGraphics().dispose();
        buffer.show();
    }
    public static void renderCells(CellInterface _field[][]) {            //метод, отрисовывающий все клетки на обычном поле
        BufferStrategy buffer = FramesManager.simulationFrame.c.getBufferStrategy();
        Graphics2D graph = (Graphics2D) buffer.getDrawGraphics();
        if (Main.renderingField == 1) {  //рисовать поле клеток
            for (int height = 0; height < Main.settings.fieldHeight; height++) {
                for (int width = 0; width < Main.settings.fieldHeight; width++) {
                    //0-не пользовавшийся, 1- новый , 2 -удаливший, 3- пользующийся повторноновый
                    try {


                        switch (_field[height][width].getType()) {
                            case 0:
                                graph.setColor(Color.BLACK);
                                graph.fillRect(width * (600 / Main.settings.fieldWidth), height * (600 / Main.settings.fieldHeight), (600 / Main.settings.fieldWidth), (600 / Main.settings.fieldHeight));
                                break;
                            case 1:
                                graph.setColor(Color.BLUE);
                                graph.fillRect(width * (600 / Main.settings.fieldWidth), height * (600 / Main.settings.fieldHeight), (600 / Main.settings.fieldWidth), (600 / Main.settings.fieldHeight));
                                break;
                            case 2:
                                graph.setColor(Color.RED);
                                graph.fillRect(width * (600 / Main.settings.fieldWidth), height * (600 / Main.settings.fieldHeight), (600 / Main.settings.fieldWidth), (600 / Main.settings.fieldHeight));
                                break;
                            case 3:
                                graph.setColor(Color.GREEN);
                                graph.fillRect(width * (600 / Main.settings.fieldWidth), height * (600 / Main.settings.fieldHeight), (600 / Main.settings.fieldWidth), (600 / Main.settings.fieldHeight));
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
