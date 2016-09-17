package ru;

import ru.Frames.FramesManager;

import java.io.IOException;

/**
 * Created by Innokentiy on 11.09.2016.
 */
public class InitThread implements Runnable {

    @Override
    public void run() {

        try {
            try {
                FramesManager.initializeFrames();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

