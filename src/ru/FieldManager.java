package ru;

import java.awt.*;

/**
 * Created by техносила on 10.09.2016.
 */
public class FieldManager {
    /** Для учета количества пользователей на поле и записи в файл
     *  @see GraphicsOutputManager#addPoint(int)
     */
    int usersCount = 0;
    /** Для расчета состояния одной клетки по её соседям */
    int positiveNeighboursAmount;
    int negativeNeighboursAmount;
    int newNeighboursAmount;
    public AppContext context;

    public void initializeBlack() {
        context.field = new Cell[context.settings.fieldHeight][context.settings.fieldWidth];
        for (int height = 0; height < context.settings.fieldHeight; height++) {
            for (int width = 0; width < context.settings.fieldWidth; width++) {
                context.field[height][width] = new Cell(0);
            }
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
