package com.codecool.tetris;

import com.codecool.termlib.*;

public class Coord {

    private int x;
    private int y;

    Coord (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getCoordArray() {
        return new int[] {this.x, this.y};
    }

    public void changeCoords(TerminalDirection direction) {
        switch (direction) {
            case UP:
                this.y++;
                break;
            case DOWN:
                this.y--;
                break;
            case RIGHT:
                this.x++;
                break;
            case LEFT:
                this.x--;
                break;
        }
    }
}