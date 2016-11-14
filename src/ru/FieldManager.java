package ru;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by техносила on 10.09.2016.
 */
public class FieldManager {
    /** Для учета количества пользователей на поле и записи в файл
     *  @see GraphicsOutputManager#addPoint(int)
     */
    int usersCount = 0;
    int stage=0;
    /** Для расчета состояния одной клетки по её соседям */
    int positiveNeighboursAmount;
    int negativeNeighboursAmount;
    int newNeighboursAmount;
    public AppContext context;

    public int getUsersCount() {
        return usersCount;
    }

    public int getStage() {
        return stage;
    }

    public void initializeBlack() {
        context.field = new Cell[context.settings.fieldHeight][context.settings.fieldWidth];
        for (int height = 0; height < context.settings.fieldHeight; height++) {
            for (int width = 0; width < context.settings.fieldWidth; width++) {
                context.field[height][width] = new Cell(0);
            }
        }
    }
    public class CellColor{
        String color;
        public int percentage;
        int number;
        int amount;
        public CellColor(String color, int percentage, int number){
            this.color = color;
            this.percentage = percentage;
            this.number=number;
            this.amount=0;
        }
        public String toString(){
            return (color+"; "+ percentage+"; "+number);
        }

        public int getPercentage() {
            return percentage;
        }
    }
    public void initField(){
        switch (context.settings.initFieldType){
            case "Fill in randomly":
                initializeRandom(context.settings.blackPercentage,context.settings.bluePercentage,context.settings.redPercentage,context.settings.greenPercentage);
                break;
            case "Blank field":
                initializeBlack();
                break;
        }
    }
    public void initializeRandom(int blackPercentage, int bluePercentage, int redPercentage, int greenPercentage){
        stage=0;
        if (blackPercentage+greenPercentage+bluePercentage+redPercentage > 100){
            throw new RuntimeException("sum is > 100");
        }
        CellColor black = new CellColor("black",blackPercentage,0);
        CellColor blue = new CellColor("blue",bluePercentage,1);
        CellColor red = new CellColor("red",redPercentage,2);
        CellColor green = new CellColor("green",greenPercentage,3);
        List<CellColor> colors = new ArrayList<>();
        colors.add(black);
        colors.add(blue);
        colors.add(green);
        colors.add(red);
        colors.sort(new Comparator<CellColor>() {
            @Override
            public int compare(CellColor o1, CellColor o2) {
                if (o1.percentage<o2.percentage){
                    return -1;
                }
                else if(o1.percentage>o2.percentage){
                    return 1;
                }else {
                    return 0;
                }
            }
        });
        context.field = new Cell[context.settings.fieldHeight][context.settings.fieldWidth];
        for (int height = 0; height < context.settings.fieldHeight; height++) {
            for (int width = 0; width < context.settings.fieldWidth; width++) {
                int chance = (int) (Math.random()*100);
                if ((chance < colors.get(0).percentage) && (chance >= 0)) {
                    context.field[height][width] = new Cell(colors.get(0).number);
                    colors.get(0).amount++;
                }
                else if ((chance>=colors.get(0).percentage)&&(chance<(colors.get(0).percentage+colors.get(1).percentage))){
                    context.field[height][width] = new Cell(colors.get(1).number);
                    colors.get(1).amount++;
                }
                else if ((chance>=(colors.get(1).percentage+colors.get(0).percentage))&&(chance<(colors.get(0).percentage+colors.get(1).percentage+colors.get(2).percentage))){
                    context.field[height][width] = new Cell(colors.get(2).number);
                    colors.get(2).amount++;
                }else {
                    context.field[height][width] = new Cell(colors.get(3).number);
                    colors.get(3).amount++;
                }
            }
        }
        System.out.println("Поле инициализированно случайным образом:");
        for (CellColor color: colors) {
            System.out.print("  "+color.color+": "
                             +color.amount
                             +"("
                             +((double)color.amount/(context.settings.fieldHeight*context.settings.fieldWidth)*10000)/100
                             +"); ");

        }
    }


    public void update(CellInterface field[][]) {

        if (Mouse.isButtonDown(1)) {

            int cellX = Mouse.getCellX();       //заменить                                          !!!
            int cellY = Mouse.getCellY();       //заменить                                          !!!
            //0-не пользовавшийся, 1- новый , 2 -удаливший, 3- пользующийся повторно
            if (context.renderingField == 1) { //ставить клетки на поле обычных клеток

                try {

                    if (context.color == Color.BLACK) {
                        field[cellY][cellX].setType(0);
                    } else if (context.color == Color.BLUE) {
                        field[cellY][cellX].setType(1);

                    } else if (context.color == Color.RED) {
                        field[cellY][cellX].setType(2);
                    } else if (context.color == Color.GREEN) {
                        field[cellY][cellX].setType(3);
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
            }else if (context.renderingField == 2){ //заполнять поле суперклеток

            }
//
        }

    }
    /**
     * <p>Расчитывает ситуацию на поле КО в следующем шаге</p>
     */
    public void calculate() {
        usersCount=0;
        stage++;
        context.bufferField = new Cell[context.settings.fieldHeight][context.settings.fieldWidth];

        for (int height = 0; height < context.settings.fieldHeight; height++) {
            for (int width = 0; width < context.settings.fieldWidth; width++) {
                positiveNeighboursAmount = 0;
                negativeNeighboursAmount = 0;
                newNeighboursAmount = 0;
                //0-не пользовавшийся, 1- новый , 2 -удаливший, 3- пользующийся повторно
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (!(i == 0 && j == 0)) {
                            try {
                                switch (context.field[height + i][width + j].getType()) {
                                    case 1:
                                        newNeighboursAmount++;
                                        break;
                                    case 2:
                                        negativeNeighboursAmount++;
                                        break;
                                    case 3:
                                        positiveNeighboursAmount++;

                                }
                            } catch (ArrayIndexOutOfBoundsException ex) {
                            }
                        }
                    }
                }
                double chance = 0;

                switch (context.field[height][width].getType()) {
                    case 0: /*пустой*/
                        if (positiveNeighboursAmount < context.settings.from1to2LowerTreshold) {/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ МЕНЬШЕ НИЖНЕЙ ГРАНИЦЫ, ТО 100% НЕ ПЕРЕЙДЕТ*/
                            context.bufferField[height][width] = new Cell(0);
                        } else if (positiveNeighboursAmount > context.settings.from1to2UpperTreshold) {/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ БОЛЬЩЕ ВЕРХНЕЙ ГРАНИЦЫ, ТО 100% НЕ ПЕРЕЙДЕТ*/
                            context.bufferField[height][width] = new Cell(1);
                        } else {
                            chance = context.settings.buffFromPositiveNeighbour * positiveNeighboursAmount
                                    + context.settings.buffFromNewNeighbour * newNeighboursAmount
                                    - context.settings.buffFromNegativeNeighbour * negativeNeighboursAmount
                                    + context.settings.from1to2SelfChance;
                            if ((Math.random() * 100) <= chance) {/*СЧИТАЕТСЯ ШАНС ПЕРЕХОДА ИЗ ПУСТОГО В ПОЛЬЗОВАТЕЛЯ ВПЕРВЫЕ*/
                                context.bufferField[height][width] = new Cell(1);
                            } else {
                                context.bufferField[height][width] = new Cell(0);
                            }
                        }
                        break;
                    case 1: /*новый*/
                        if (positiveNeighboursAmount < context.settings.from2to3LowerTreshold) {/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ МЕНЬШЕ НИЖНЕЙ ГРАНИЦЫ, ТО 100% ПЕРЕСТАНЕТ ПОЛЬЗОВАТЬСЯ*/
                            context.bufferField[height][width] = new Cell(2);
                        } else if (positiveNeighboursAmount > context.settings.from2to3UpperTreshold) {/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ БОЛЬШЕ ВЕРХНЕЙ ГРАНИЦЫ, ТО 100% ОСТАНЕТСЯ ПОЛЬЗОВАТЕЛЕМ*/
                            context.bufferField[height][width] = new Cell(1);
                        } else {
                            chance = -context.settings.buffFromPositiveNeighbour * positiveNeighboursAmount
                                    - context.settings.buffFromNewNeighbour * newNeighboursAmount
                                    + context.settings.buffFromNegativeNeighbour * negativeNeighboursAmount
                                    + context.settings.from2to3SelfChance;
                            if ((Math.random() * 100) <= chance) {/*ВЕРОЯТНОСТЬ ПЕРЕХОДА ИЗ НОВОГО ПОЛЬЗОВАТЕЛЯ В ОТКАЗАВШЕГОСЯ*/
                                context.bufferField[height][width] = new Cell(2);
                            } else {
                                context.bufferField[height][width] = new Cell(1);
                            }
                        }
                        usersCount++;
                        break;
                    case 2: /*удаливший*/
                        if (positiveNeighboursAmount < context.settings.from3to4LowerTreshold) {/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ МЕНЬШЕ НИЖНЕЙ ГРАНИЦЫ, ТО 100% НЕ НАЧНЕТ ПОЛЬЗОВАТЬСЯ СНОВА*/
                            context.bufferField[height][width] = new Cell(2);
//                            context.bufferField[height][width].setType(2);
                        } else if (positiveNeighboursAmount > context.settings.from3to4UpperTreshold) {/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ БОЛЬШЕ ВЕРХНЕЙ ГРАНИЦЫ, ТО 100% НАЧНЕТ ПОЛЬЗОВАТЬСЯ СНОВА*/
                            context.bufferField[height][width] = new Cell(3);
//                            context.bufferField[height][width].setType(3);
                        } else {
                            chance = context.settings.buffFromPositiveNeighbour * positiveNeighboursAmount
                                    + context.settings.buffFromNewNeighbour * newNeighboursAmount
                                    - context.settings.buffFromNegativeNeighbour * negativeNeighboursAmount
                                    + context.settings.from3to4SelfChance;
                            if ((Math.random() * 100) <= chance) {/*ВЕРОЯТНОСТЬ ПЕРЕХОДА ИЗ ОТКАЗАВШЕГОСЯ В ПОЛЬЗОВАТЕЛЯ ПОВТОРНО*/
                                context.bufferField[height][width] = new Cell(3);
//                                context.bufferField[height][width].setType(3);
                            } else {
                                context.bufferField[height][width] = new Cell(2);
//                                context.bufferField[height][width].setType(2);
                            }
                        }
                        break;
                    case 3: /*снова установивший*/
                        if (positiveNeighboursAmount < context.settings.from4to3LowerTreshold) {/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ МЕНЬШЕ НИЖНЕЙ ГРАНИЦЫ, ТО 100% ПЕРЕСТАНЕТ ПОЛЬЗОВАТЬСЯ*/
                            context.bufferField[height][width] = new Cell(2);
//                            context.bufferField[height][width].setType(2);
                        } else if (positiveNeighboursAmount > context.settings.from3to4UpperTreshold) {
                            context.bufferField[height][width] = new Cell(3);
//                            context.bufferField[height][width].setType(3);
                        } else {
                            chance = -context.settings.buffFromPositiveNeighbour * positiveNeighboursAmount
                                    - context.settings.buffFromNewNeighbour * newNeighboursAmount
                                    + context.settings.buffFromNegativeNeighbour * negativeNeighboursAmount
                                    + context.settings.from4to3SelfChance;
                            if ((Math.random() * 100) <= chance) {
                                context.bufferField[height][width] = new Cell(2);
//                                context.bufferField[height][width].setType(2);
                            } else {
                                context.bufferField[height][width] = new Cell(3);
//                                context.bufferField[height][width].setType(3);
                            }
                        }
                        usersCount++;
                        break;


                }


            }
        }
        for (int height = 0; height < context.settings.fieldHeight; height++) {
            for (int width = 0; width < context.settings.fieldWidth; width++) {
                context.field[height][width] = context.bufferField[height][width];
            }
        }
        context.graphicsOutputManager.addPoint(usersCount);


    }


}
