package ru;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Innokentiy on 19.11.2016.
 */
public class Util {
    public static AppContext context;

    public static void setContext(AppContext context) {
        Util.context = context;
    }

    public static Dimension getAbsoluteCenterPosition(int cellX, int cellY){
        Dimension position = new Dimension();

        return position;
    }
    public static double normalDistribution(double x, double _a, double _s){
        double a = _a;  //среднее
        double s = _s;  //отклонение
        double f = (1/(s*Math.pow((2*Math.PI),0.5)))    *   Math.pow(Math.E,(-Math.pow((x-a),2)/(2*Math.pow(s,2))));
        return f;
    }
    public static ArrayList<Double> calculateData(double a, double s, int duration, int startTime, int influence){
        ArrayList<Double> data = new ArrayList<>(duration);
        double f;
        double x=0;
        for (int i = 0; i < startTime; i++) {
            data.add(0.0);
        }
        for (int i = 0; i < duration; i++) {
            f= Util.normalDistribution(x,a,s);
            data.add(f*influence);
            x+=0.1;
        }

        return data;
    }
}
