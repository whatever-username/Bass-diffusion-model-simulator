package ru;

import ru.Frames.FramesManager;

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

    public AppContext(){
        this.fieldManager = new FieldManager();
        this.graphicsOutputManager = new GraphicsOutputManager();
        this.framesManager = new FramesManager();
        this.settings = new Settings();
    }
}
