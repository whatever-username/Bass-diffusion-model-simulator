package ru;

import java.awt.*;

/**
 * Created by техносила on 10.09.2016.
 */
public class FieldManager {
    static Settings s = Main.settings;
    public static void initializeBlack() {
        Main.field = new Cell[Main.settings.fieldHeight][Main.settings.fieldWidth];
        for (int height = 0; height < Main.settings.fieldHeight; height++) {
            for (int width = 0; width < Main.settings.fieldWidth; width++) {
                Main.field[height][width] = new Cell(0);
            }
        }
    }

    public static void update(CellInterface field[][]) {

        if (Mouse.isButtonDown(1)) {

            int cellX = Mouse.getCellX();
            int cellY = Mouse.getCellY();
            //0-не пользовавшийся, 1- новый , 2 -удаливший, 3- пользующийся повторно
            if (Main.renderingField == 1) { //ставить клетки на поле обычных клеток

                try {

                    if (Main.color == Color.BLACK) {
                        field[cellY][cellX].setType(0);
                    } else if (Main.color == Color.BLUE) {
                        field[cellY][cellX].setType(1);

                    } else if (Main.color == Color.RED) {
                        field[cellY][cellX].setType(2);
                    } else if (Main.color == Color.GREEN) {
                        field[cellY][cellX].setType(3);
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
            }
//
        }

    }

    public static void calculate() {
        int positiveNeighboursAmount;
        int negativeNeighboursAmount;
        int newNeighboursAmount;
        Main.bufferField = new Cell[Main.settings.fieldHeight][Main.settings.fieldWidth];
        int usersCount = 0;

        for (int height = 0; height < Main.settings.fieldHeight; height++) {
            for (int width = 0; width < Main.settings.fieldWidth; width++) {
                //подсчет окружения
                positiveNeighboursAmount = 0;
                negativeNeighboursAmount = 0;
                newNeighboursAmount = 0;
//                    Map<Integer,Integer> newList = new HashMap<Integer, Integer>();         //TEST

                for (int i = -1; i <= 1; i++) { //идти по всему окружению
                    for (int j = -1; j <= 1; j++) {
                        if (!(i == 0 && j == 0)) {  //кроме обрабатываемой клетки
                            //0-не пользовавшийся, 1- новый , 2 -удаливший, 3- пользующийся повторно
                            try {

                                int type = Main.field[height + i][width + j].getType();
                                if (type == 3) {
                                    positiveNeighboursAmount++;
                                }
                                if (type == 2) {
                                    negativeNeighboursAmount++;
                                }
                                if (type == 1) {
                                    newNeighboursAmount++;
//                                        newList.put(height+i,width+j);      //TEST
                                }
                            } catch (ArrayIndexOutOfBoundsException ex) {

                            }catch (NullPointerException ex1){

                            }
                        }
                    }
                }

                //расчет состояния в зависимости от типа клетки
                int chance = 0;
                try {
                    switch (Main.field[height][width].getType()) {
                        case 0: //0-не пользовавшийся, 1- новый , 2 -удаливший, 3- пользующийся повторно

                            if (positiveNeighboursAmount > Main.settings.from1to2UpperTreshold) {
                                Main.bufferField[height][width] = new Cell(1);       //если количество пользователей вокруг превышает верхний порог, то становится новым пользователем
                            } else if (positiveNeighboursAmount < Main.settings.from1to2LowerTreshold) {
                                Main.bufferField[height][width] = new Cell(0);       //если количество пользователей вокруг меньше, чем нижний порог, то остается незанятой
                            } else {
                                //своя вероятность + (вер-сть от одного положительного)*(кол-во положительных)-(вер-сть от одного отриц.)*(кол-во отриц.)
                                chance = Main.settings.from1to2SelfChance + Main.settings.buffFromPositiveNeighbour * positiveNeighboursAmount - Main.settings.buffFromNegativeNeighbour * negativeNeighboursAmount + Main.settings.buffFromNewNeighbour * newNeighboursAmount;
                                if ((Math.random() * 100) <= chance) {
                                    Main.bufferField[height][width] = new Cell(1);   //иначе с определенной вероятностью станет новым пользователем
                                } else {
                                    Main.bufferField[height][width] = new Cell(0);
                                }
                            }
                            break;
                        case 1: //0-не пользовавшийся, 1- новый , 2 -удаливший, 3- пользующийся повторно
                            usersCount++;
                            if (positiveNeighboursAmount > Main.settings.from2to3UpperTreshold) {
                                Main.bufferField[height][width] = new Cell(1);       //если количество пользователей вокруг превышает верхний порог, то останется пользователем
                            } else if (positiveNeighboursAmount < Main.settings.from2to3LowerTreshold) {
                                Main.bufferField[height][width] = new Cell(2);       //если количество пользователей вокруг меньше, чем нижний порог, то удалит приложение
                            } else {
                                //своя вероятность - (вер-сть от одного положительного)*(кол-во положительных)+(вер-сть от одного отриц.)*(кол-во отриц.)
                                chance = Main.settings.from2to3SelfChance - (Main.settings.buffFromPositiveNeighbour * positiveNeighboursAmount) + Main.settings.buffFromNegativeNeighbour * negativeNeighboursAmount - Main.settings.buffFromNewNeighbour * newNeighboursAmount;

                                if ((Math.random() * 100) <= chance) {
                                    Main.bufferField[height][width] = new Cell(2);   //иначе с определенной вероятностью удалит приложение
                                } else {
                                    Main.bufferField[height][width] = new Cell(1);
                                }
                            }
                            break;
                        case 2: //0-не пользовавшийся, 1- новый , 2 -удаливший, 3- пользующийся повторно

                            if (positiveNeighboursAmount > Main.settings.from3to4UpperTreshold) {
                                Main.bufferField[height][width] = new Cell(3);       //если количество пользователей вокруг превышает верхний порог, то повторно становится пользователем
                            } else if (positiveNeighboursAmount < Main.settings.from3to4LowerTreshold) {
                                Main.bufferField[height][width] = new Cell(2);       //если количество пользователей вокруг меньше, чем нижний порог, то остается удалившим
                            } else {
                                //своя вероятность + (вер-сть от одного положительного)*(кол-во положительных)-(вер-сть от одного отриц.)*(кол-во отриц.)
                                chance = Main.settings.from3to4SelfChance + Main.settings.buffFromPositiveNeighbour * positiveNeighboursAmount - Main.settings.buffFromNegativeNeighbour * negativeNeighboursAmount + Main.settings.buffFromNewNeighbour * newNeighboursAmount;
                                if ((Math.random() * 100) <= chance) {
                                    Main.bufferField[height][width] = new Cell(3);   //иначе с определенной вероятностью станет пользователем повторно
                                } else {
                                    Main.bufferField[height][width] = new Cell(2);
                                }
                            }

                            break;
                        case 3: //0-не пользовавшийся, 1- новый , 2 -удаливший, 3- пользующийся повторно
                            usersCount++;
                            if (positiveNeighboursAmount > Main.settings.from4to3UpperTreshold) {
                                Main.bufferField[height][width] = new Cell(3);       //если количество пользователей вокруг превышает верхний порог, то остается пользователем
                            } else if (positiveNeighboursAmount < Main.settings.from4to3LowerTreshold) {
                                Main.bufferField[height][width] = new Cell(2);       //если количество пользователей вокруг меньше, чем нижний порог, то опять удаляет
                            } else {
                                //своя вероятность - (вер-сть от одного положительного)*(кол-во положительных)+(вер-сть от одного отриц.)*(кол-во отриц.)
                                chance = Main.settings.from4to3SelfChance - Main.settings.buffFromPositiveNeighbour * positiveNeighboursAmount + Main.settings.buffFromNegativeNeighbour * negativeNeighboursAmount - Main.settings.buffFromNewNeighbour * newNeighboursAmount;
                                if ((Math.random() * 100) <= chance) {
                                    Main.bufferField[height][width] = new Cell(2);   //иначе с определенной вероятностью опять удалит приложение
                                } else {
                                    Main.bufferField[height][width] = new Cell(3);
                                }
                            }


                            break;
                    }
                }catch (NullPointerException ex){
                    System.out.println("on counting "+ height+";"+width);
                }
                Main.manager.addPoint(usersCount);
//


                Main.field = Main.bufferField.clone();
            }
        }
    }
    static int turn=0;

    public static void test(){
        int usersCount = 0;
//        System.out.println(turn);
        turn++;
        int positiveNeighboursAmount;
        int negativeNeighboursAmount;
        int newNeighboursAmount;
        Main.bufferField = new Cell[Main.settings.fieldHeight][Main.settings.fieldWidth];
        /*for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Main.bufferField[i][j] = new Cell(0);
            }
        }*/
        for (int height = 0; height < Main.settings.fieldHeight; height++) {
            for (int width = 0; width < Main.settings.fieldWidth; width++) {
                positiveNeighboursAmount=0; negativeNeighboursAmount =0; newNeighboursAmount =0;
                //0-не пользовавшийся, 1- новый , 2 -удаливший, 3- пользующийся повторно
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1 ; j++) {
                        if (!(i==0 && j==0)) {
                            try {
                                switch (Main.field[height + i][width + j].getType()) {
                                    case 1:
                                        newNeighboursAmount++;
                                        break;
                                    case 2:
                                        negativeNeighboursAmount++;
                                        break;
                                    case 3:
                                        positiveNeighboursAmount++;

                                }
                            } catch (ArrayIndexOutOfBoundsException ex) {}
                        }
                    }
                }
                int chance=0;

                switch (Main.field[height][width].getType()){
                    case 0: /*пустой*/
                        if (positiveNeighboursAmount < s.from1to2LowerTreshold){/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ МЕНЬШЕ НИЖНЕЙ ГРАНИЦЫ, ТО 100% НЕ ПЕРЕЙДЕТ*/
                            Main.bufferField[height][width] = new Cell(0);
                        }else if (positiveNeighboursAmount > s.from1to2UpperTreshold){/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ БОЛЬЩЕ ВЕРХНЕЙ ГРАНИЦЫ, ТО 100% НЕ ПЕРЕЙДЕТ*/
                            Main.bufferField[height][width] = new Cell(1);
                        }else {
                            chance = s.buffFromPositiveNeighbour*positiveNeighboursAmount + s.buffFromNewNeighbour*newNeighboursAmount - s.buffFromNegativeNeighbour*negativeNeighboursAmount +s.from1to2SelfChance;
                            if ((Math.random()*100)<=chance){/*СЧИТАЕТСЯ ШАНС ПЕРЕХОДА ИЗ ПУСТОГО В ПОЛЬЗОВАТЕЛЯ ВПЕРВЫЕ*/
                                Main.bufferField[height][width] = new Cell(1);
                            }else {
                                Main.bufferField[height][width] = new Cell(0);
                            }
                        }
                        break;
                    case 1: /*новый*/
                        if (positiveNeighboursAmount < s.from2to3LowerTreshold){/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ МЕНЬШЕ НИЖНЕЙ ГРАНИЦЫ, ТО 100% ПЕРЕСТАНЕТ ПОЛЬЗОВАТЬСЯ*/
                            Main.bufferField[height][width] = new Cell(2);
                        }else if (positiveNeighboursAmount > s.from2to3UpperTreshold){/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ БОЛЬШЕ ВЕРХНЕЙ ГРАНИЦЫ, ТО 100% ОСТАНЕТСЯ ПОЛЬЗОВАТЕЛЕМ*/
                            Main.bufferField[height][width] = new Cell(1);
                        }else {
                            chance = -s.buffFromPositiveNeighbour*positiveNeighboursAmount - s.buffFromNewNeighbour*newNeighboursAmount + s.buffFromNegativeNeighbour*negativeNeighboursAmount +s.from2to3SelfChance;
                            if ((Math.random()*100)<=chance){/*ВЕРОЯТНОСТЬ ПЕРЕХОДА ИЗ НОВОГО ПОЛЬЗОВАТЕЛЯ В ОТКАЗАВШЕГОСЯ*/
                                Main.bufferField[height][width] = new Cell(2);
                            }else {
                                Main.bufferField[height][width] = new Cell(1);
                            }
                        }
                        usersCount++;
                        break;
                    case 2: /*удаливший*/
                        if (positiveNeighboursAmount < s.from3to4LowerTreshold){/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ МЕНЬШЕ НИЖНЕЙ ГРАНИЦЫ, ТО 100% НЕ НАЧНЕТ ПОЛЬЗОВАТЬСЯ СНОВА*/
                            Main.bufferField[height][width] = new Cell(2);
//                            Main.bufferField[height][width].setType(2);
                        }else if (positiveNeighboursAmount > s.from3to4UpperTreshold){/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ БОЛЬШЕ ВЕРХНЕЙ ГРАНИЦЫ, ТО 100% НАЧНЕТ ПОЛЬЗОВАТЬСЯ СНОВА*/
                            Main.bufferField[height][width] = new Cell(3);
//                            Main.bufferField[height][width].setType(3);
                        }else {
                            chance = s.buffFromPositiveNeighbour*positiveNeighboursAmount + s.buffFromNewNeighbour*newNeighboursAmount - s.buffFromNegativeNeighbour*negativeNeighboursAmount +s.from3to4SelfChance;
                            if ((Math.random()*100)<=chance){/*ВЕРОЯТНОСТЬ ПЕРЕХОДА ИЗ ОТКАЗАВШЕГОСЯ В ПОЛЬЗОВАТЕЛЯ ПОВТОРНО*/
                                Main.bufferField[height][width] = new Cell(3);
//                                Main.bufferField[height][width].setType(3);
                            }else {
                                Main.bufferField[height][width] = new Cell(2);
//                                Main.bufferField[height][width].setType(2);
                            }
                        }
                        break;
                    case 3: /*снова установивший*/
                        if (positiveNeighboursAmount < s.from4to3LowerTreshold){/*ЕСЛИ КОЛ-ВО ПОВТОРНО ПОЛЬЗУЮЩИХСЯ МЕНЬШЕ НИЖНЕЙ ГРАНИЦЫ, ТО 100% ПЕРЕСТАНЕТ ПОЛЬЗОВАТЬСЯ*/
                            Main.bufferField[height][width] = new Cell(2);
//                            Main.bufferField[height][width].setType(2);
                        }else if (positiveNeighboursAmount > s.from3to4UpperTreshold){
                            Main.bufferField[height][width] = new Cell(3);
//                            Main.bufferField[height][width].setType(3);
                        }else {
                            chance = -s.buffFromPositiveNeighbour*positiveNeighboursAmount - s.buffFromNewNeighbour*newNeighboursAmount + s.buffFromNegativeNeighbour*negativeNeighboursAmount +s.from4to3SelfChance;
                            if ((Math.random()*100)<=chance){
                                Main.bufferField[height][width] = new Cell(2);
//                                Main.bufferField[height][width].setType(2);
                            }else {
                                Main.bufferField[height][width] = new Cell(3);
//                                Main.bufferField[height][width].setType(3);
                            }
                        }
                        usersCount++;
                        break;


                }



            }
        }
        for (int height = 0; height < s.fieldHeight; height++) {
            for (int width = 0; width < s.fieldWidth; width++) {
                Main.field[height][width] = Main.bufferField[height][width];
            }
        }
        Main.manager.addPoint(usersCount);
        /*if (usersCount==(Main.settings.fieldWidth*Main.settings.fieldHeight)){
            break;
        }*/

    }


}
