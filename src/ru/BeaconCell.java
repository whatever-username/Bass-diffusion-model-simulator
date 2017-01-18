package ru;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ru.Mouse.context;

/**
 * Created by Innokentiy on 14.11.2016.
 */
public class BeaconCell implements CellInterface {
    static int amount = 0;

    int id = 0;
    int type;
    int influence = 0;
    int areaOfEffect;
    public ArrayList<Dimension> dependent;

    public BeaconCell(int type, int areaOfEffect, int influence, int relativeX, int relativeY) {
        this.id = amount++;
        this.type = type;
        this.areaOfEffect = areaOfEffect;
        setInfluence(influence);
        countDependent(relativeX,relativeY);



    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    public void setAreaOfEffect(int areaOfEffect) {
        this.areaOfEffect = areaOfEffect;
    }

    public int getAreaOfEffect() {
        return areaOfEffect;
    }

    public void setInfluence(int influence)     {
        if (influence > 100) {
            this.influence = 100;
        }
        this.influence = influence;

    }

    public int getInfluence() {
        return influence;
    }

    public ArrayList getDependent() {
        return dependent;
    }

    @Override
    public boolean equals(Object obj) {
        BeaconCell another = (BeaconCell) obj;
        return this.id == another.id;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + type;
        result = 31 * result + areaOfEffect;
        return result;
    }

    @Override
    public String toString() {
        return ("Beacon #" + id + "; type: " + type + "; area of effect: " + areaOfEffect+ "; dependent cells: "+dependent.size()+"; influence:" + influence);
    }
    public String listDependent(){

        String s = "amount of dependent cells: "+this.dependent.size()+"\n\r";
        for (int i = 0; i < this.dependent.size(); i++) {
            s+= "#"+i+" "+this.dependent.get(i).getWidth()+";"+this.dependent.get(i).getHeight()+"\n\r";
        }
        return s;
    }
    public void countDependent(int relativeX,int relativeY) {
        ArrayList<Dimension>  list = new ArrayList<Dimension> ();

        int diameter = this.getAreaOfEffect();
        int areaInCells = (diameter / 2 / (600 / context.settings.fieldHeight));
        int absoluteX = relativeX * (600 / context.settings.fieldWidth);
        int absoluteY = relativeY * (600 / context.settings.fieldWidth);
        for (int i = -areaInCells; i <= areaInCells; i++) {
            for (int j = -areaInCells; j <= areaInCells; j++) {
                int cellCenterX =i * (600 / context.settings.fieldWidth) + (300 / context.settings.fieldWidth);
                int cellCenterY = j * (600 / context.settings.fieldWidth) + (300 / context.settings.fieldWidth);
                if (Math.pow(Math.pow(cellCenterX- (300 / context.settings.fieldWidth), 2) + Math.pow(cellCenterY- (300 / context.settings.fieldWidth), 2), 0.5) < diameter / 2) {
                    if (type != 0){


                        if (((absoluteX+cellCenterX)>0) &&  ((absoluteX+cellCenterX)<600) && ((absoluteY+cellCenterY)>0) && ((absoluteY+cellCenterY)<600)){
                            list.add(new Dimension(i,j));
                        }
                    }
                }

            }

        }
        this.dependent = list;
    }
 }
