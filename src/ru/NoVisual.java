package ru;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Иннокентий on 10.10.2016.
 */
public class NoVisual {
    AppContext context;
    List<Integer> amounts = new ArrayList<>(20); //вспомогательный лист, отслеживающий "золотой век"

    public NoVisual(AppContext context){
        this.context = context;
    }
    public void calculate () throws InterruptedException {
        int stage =0;
        for (int i = 0; i < 100; i+=5) {
            for (int j = 0; j < 100; j+=5) {
                for (int k = 0; k < 100; k+=5) {
                    System.out.println(i+" "+j+" "+k);
                    stage++;
                    context.settings.buffFromNegativeNeighbour=i;
                    context.settings.buffFromPositiveNeighbour=j;
                    context.settings.buffFromNewNeighbour=k;
                    context.fieldManager.initializeRandom(90,5,3,2);
                    long begin = System.currentTimeMillis();
                    while (true){
                            context.fieldManager.calculate();
                        add(context.fieldManager.getUsersCount());
                            if (context.fieldManager.getStage()>1000) {
                                if (!check()) {
                                    context.settings.saveSettings("testing" + stage + "(onCondition).out");
                                    System.out.println("stage "+stage+"; step "+context.fieldManager.getStage());
                                    long end = System.currentTimeMillis();
                                    System.out.println(end-begin);
                                    break;
                                }else {
                                    break;
                                }
                            }
                    }


                }
            }
        }

    }
    public void add (int number){
        if (amounts.size()<20){
            amounts.add(number);
        }else {
            amounts.remove(0);
            amounts.add(number);
        }
    }
    public boolean check(){
        double sum=0;
        if (amounts.size()==20) {
            for (int i = 0; i < 20; i++) {
                sum += amounts.get(i);
            }
            sum = sum / 20;
            if ((Math.abs(sum - amounts.get(0)) < 0.1) && (Math.abs(sum - amounts.get(10)) < 0.1) && (Math.abs(sum - amounts.get(19)) < 0.1)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void findpairWithMaxCorrelation() throws IOException, ClassNotFoundException {
        context.settings=Settings.loadSettings("settings.out");
        context.settings.buffFromNewNeighbour=0;
        double min =999999999;
        double corrCoef=-1;
        for (double i = 0; i < 100; i+=0.1) {
            context.settings.buffFromNewNeighbour+=0.1;
            context.settings.buffFromNewNeighbour= Math.rint(100.0 * context.settings.buffFromNewNeighbour) / 100.0;
            context.fieldManager.stage=0;
            context.fieldManager.usersCount=0;
            context.graphicsOutputManager.outputPointsArray = new ArrayList<>();
            context.fieldManager.initializeBlack();
            context.field[50][50] = new Cell(1);
            while (context.fieldManager.usersCount != 10000){
                context.fieldManager.calculate();

            }
//            context.graphicsOutputManager.writeToFile(context.graphicsOutputManager.outputPointsArray,"C:\\Users\\техносила\\Desktop\\Temp\\model.csv");

            for (double p = 0.01; p < 1; p+=0.01) {
                for (double q = 0.01; q < 1; q+=0.01) {
                    q = Math.rint(100.0 * q) / 100.0;
                    List<Double> Bass = BassModel(p,q,context.fieldManager.stage);
                    double result = compare(context.graphicsOutputManager.outputPointsArray,Bass);
                    double resultPearson = correlation(context.graphicsOutputManager.outputPointsArray,Bass);
                    double resultChiSquared = chiSquared(context.graphicsOutputManager.outputPointsArray,Bass);
                    if (     result < min    ){
                        min= result;

//                        context.graphicsOutputManager.writeToFile(Bass,"C:\\Users\\техносила\\Desktop\\Temp\\bass.csv");
                        System.out.println("По ошибке. При коэффициенте модели "+context.settings.buffFromNewNeighbour+" и p= "+p+" и q="+q
                                +" ошибка = "+min+"; к. Пирсона равен "+corrCoef+"; хи-квадрат :"
                                +resultChiSquared);
                    }
                    if (    resultPearson>corrCoef){
                        corrCoef = resultPearson;
                        System.out.println("По Пирсону. При коэффициенте модели "+context.settings.buffFromNewNeighbour+" и p= "+p+" и q="+q
                                +" ошибка = "+min+"; к. Пирсона равен "+corrCoef+"; хи-квадрат :"
                                +resultChiSquared);
                    }
                }
            }
        }
    }
    public List BassModel(double p, double q, int length){
        List<Double> users = new ArrayList<>(length);
        for (double i = 0; i < length; i++) {
            double x = i/100.0;
            users.add(      10000* ((1-Math.pow(Math.E,-(p+q)*x))/(1+q/p*Math.pow(Math.E,-(p+q)*x)))   );
        }
        return users;
    }
    public double compare(List<? extends Number> first, List<? extends Number> second){
        double error = 0;
        if (first.size()!=second.size()){
            throw new RuntimeException("размеры сравниваемых массивов не совпадают");
        }
        for (int pos = 0; pos < first.size(); pos++) {

            error += Math.pow(first.get(pos).doubleValue() - second.get(pos).doubleValue(),2);
        }
        error=  Math.pow(error/(double)first.size(),0.5);

        return error;
    }
    public double correlation(List<? extends Number> listX, List<? extends Number> listY) {

        if (listX.size() != listY.size()) {
            throw new RuntimeException("Размер сравниваемых массивов не совпадает");
        }
        double srX = 0;
        double srY = 0;
        double top = 0;
        double diffX = 0;
        double diffY = 0;
        double diffX2 = 0;
        double diffY2 = 0;
        for (int i = 0; i < listX.size(); i++) {
            srX += listX.get(i).doubleValue();
            srY += listY.get(i).doubleValue();
        }
        srX = srX / listX.size();
        srY = srY / listY.size();
        for (int i = 0; i < listX.size(); i++) {
            diffX = listX.get(i).doubleValue() - srX;
            diffY = listY.get(i).doubleValue() - srY;
            top += diffX * diffY;
            diffX2 = diffX2 += diffX * diffX;
            diffY2 = diffY2 += diffY * diffY;
        }
        double result = top/Math.pow(diffX2*diffY2,  0.5);
        return result;
    }
    public double chiSquared(List<? extends Number> listX, List<? extends Number> listY){
        if (listX.size()!=listY.size()){
            throw new RuntimeException("размеры сравниваемых массивов не совпадают");
        }
        double result = 0.0;
        for (int elem = 1; elem < listX.size(); elem++) {
//            System.out.println("("+listX.get(elem).doubleValue()+"-"+listY.get(elem).doubleValue()+")^2"+"/"+(listY.get(elem).doubleValue()));
            result += Math.pow((listX.get(elem).doubleValue()-listY.get(elem).doubleValue()),2)/(listY.get(elem).doubleValue());

        }
        return result;
    }



}
