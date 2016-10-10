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

        context.framesManager.initializeFrames();


    }
}

