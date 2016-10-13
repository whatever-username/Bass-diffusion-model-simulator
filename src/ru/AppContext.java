package ru;

import ru.Frames.FramesManager;

import java.awt.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Innokentiy on 10.10.2016.
 * Basic class, that holds all components together
 */
public class AppContext {
    public FieldManager fieldManager;
    public GraphicsOutputManager graphicsOutputManager;
    public FramesManager framesManager;
    public Settings settings;
    public int renderingField=1; //номер поля, отображаемого в тек. момент
    public int areaEffectDiameter = 0; // радиус воздействия точки-маяка
    public Color color;
    public boolean ready=false;
    public boolean processing = false;
    public Cell field[][];
    public Cell bufferField[][];

    public AppContext(){
        this.fieldManager = new FieldManager();
        this.graphicsOutputManager = new GraphicsOutputManager();
        this.framesManager = new FramesManager();
        this.settings = new Settings();
        fieldManager.context = this;
        graphicsOutputManager.context = this;
        framesManager.context = this;
        Mouse.context = this;
    }
}
