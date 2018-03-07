package com.codecool.tetris;

import com.codecool.termlib.*;

public class Coord {

    private int X;
    private int Y;

    Coord (int x, int y) {
        this.X = x;
        this.Y = y;
    
    }
    
    public int[] getCoord() {
        return new int[] {this.X, this.Y};
    }
}