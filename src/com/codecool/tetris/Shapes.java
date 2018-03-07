package com.codecool.tetris;

import com.codecool.termlib.*;
public class Shapes {

    public abstract class Shape {

        private Coord coords;
        private BGColor color;
        private final TerminalDirection[] formula;

        public void moveLeft() {
            this.coords.changeCoords(TerminalDirection.LEFT);
        }

        public void moveRight() {
            this.coords.changeCoords(TerminalDirection.RIGHT);
        }

        public void moveDown() {
            this.coords.changeCoords(TerminalDirection.DOWN);
        }

        public BGColor getColor() {
            return this.color;
        }

        public int[] getCoords() {
            return this.coords.getCoordArray();
        }

        Shape(int x, int y, BGColor color, TerminalDirection[] formula) {
            this.color = color;
            this.coords = new Coord(x, y);
            this.formula = formula;
        }
    }

    public class Square extends Shape {
        Square(int x, int y, BGColor color){
            super(x, y, color, new TerminalDirection[] {
                TerminalDirection.UP,
                TerminalDirection.RIGHT,
                TerminalDirection.DOWN
            });
        }
    }
}
