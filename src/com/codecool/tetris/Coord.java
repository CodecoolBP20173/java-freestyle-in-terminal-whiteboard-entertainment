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

    public void changeCoords(TerminalDirection direction) {
        switch (direction) {
            case Direction.DOWN: this.Y -= 1;
            break;
            case Direction.RIGHT: this.X += 1;
            break;
            case Direction.LEFT: this.X -= 1;
            break;
        }
    }


}