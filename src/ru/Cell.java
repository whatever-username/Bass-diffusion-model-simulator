package ru;

import ru.CellInterface;

/**
 * Created by техносила on 10.09.2016.
 */
public class Cell implements CellInterface {
    int type;
    Cell (int _type){
        //0-не пользовавшийся, 1- новый , 2 -удаливший, 3- пользующийся повторно
        type = _type;

    }
    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type=type;
    }
}
