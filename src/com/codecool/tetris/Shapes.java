package com.codecool.tetris;

import java.util.HashMap;
import java.util.Map;
import com.codecool.termlib.*;

public class Shapes {

    private static final Map<String, TerminalDirection[]> shapeForms = fillMap();

    private static Map<String, TerminalDirection[]> fillMap(){
        Map<String, TerminalDirection[]> m = new HashMap<>();
        m.put("Z", new TerminalDirection[] {
            TerminalDirection.LEFT,
            TerminalDirection.DOWNRIGHT,
            TerminalDirection.RIGHT
        });
        m.put("Z_mirrored", new TerminalDirection[] {
            TerminalDirection.LEFT,
            TerminalDirection.UPRIGHT,
            TerminalDirection.RIGHT
        });
        m.put("T", new TerminalDirection[] {
            TerminalDirection.DOWNLEFT,
            TerminalDirection.RIGHT,
            TerminalDirection.RIGHT
        });
        m.put("L", new TerminalDirection[] {
            TerminalDirection.DOWN,
            TerminalDirection.DOWN,
            TerminalDirection.RIGHT
        });
        m.put("L_mirrored", new TerminalDirection[] {
            TerminalDirection.DOWN,
            TerminalDirection.DOWN,
            TerminalDirection.LEFT
        });
        m.put("I", new TerminalDirection[] {
            TerminalDirection.DOWN,
            TerminalDirection.DOWN,
            TerminalDirection.DOWN
        });
        m.put("Square", new TerminalDirection[] {
            TerminalDirection.RIGHT,
            TerminalDirection.DOWN,
            TerminalDirection.LEFT
        });
        return m;
    }

    public static abstract class Shape {

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

        public TerminalDirection[] getFormula() {
            return this.formula;
        }

        Shape(int x, int y, BGColor color, TerminalDirection[] formula) {
            this.color = color;
            this.coords = new Coord(x, y);
            this.formula = formula;
        }
    }

    public static class Square extends Shape {
        Square(int x, int y, BGColor color){
            super(x, y, color, shapeForms.get("Square"));
        }
    }

    public static class ZShape extends Shape {
        ZShape(int x, int y, BGColor color, boolean mirr){
            super(x, y, color, mirr? shapeForms.get("Z_mirrored") : shapeForms.get("Z"));
        }
    }

    public static class TShape extends Shape {
        TShape(int x, int y, BGColor color){
            super(x, y, color, shapeForms.get("T"));
        }
    }

    public static class LShape extends Shape {
        LShape(int x, int y, BGColor color, boolean mirr){
            super(x, y, color, mirr? shapeForms.get("L_mirrored") : shapeForms.get("L"));
        }
    }

    public static class Pillar extends Shape {
        Pillar(int x, int y, BGColor color){
            super(x, y, color, shapeForms.get("I"));
        }
    }
}
