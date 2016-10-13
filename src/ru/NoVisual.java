package ru;

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


}
