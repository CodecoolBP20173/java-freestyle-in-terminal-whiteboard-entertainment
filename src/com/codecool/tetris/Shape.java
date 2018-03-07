package com.codecool.tetris;

import com.codecool.termlib.*;

public abstract class Shape {

    private Coord coords;
    private Color FGColor;

    public void moveLeft(){
        this.coords.changeCoords(TerminalDirection.LEFT);
    }

    public void moveRight(){
        this.coords.changeCoords(TerminalDirection.RIGHT);
    }

    public void moveDown(){
        this.coords.changeCoords(TerminalDirection.DOWN);
    }

    public int[] getShapeCoords(){
        return this.coords.getCoord();
    }

    Shape(int X, int Y, Color c){
        this.FGColor = c;
        this.coords = new Coord(X, Y);
    }
}
