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
            TerminalDirection.DOWNLEFT,
            TerminalDirection.RIGHT,
            TerminalDirection.UPRIGHT
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

    public static abstract class Shape implements Cloneable {

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

        public abstract Shape clone();

        Shape(int x, int y, BGColor color, TerminalDirection[] formula) {
            this.color = color;
            this.coords = new Coord(x, y);
            this.formula = formula;
        }
    }

    public static class Square extends Shape {
        public Square clone(){
            int x = this.getCoords()[0];
            int y = this.getCoords()[1];
            BGColor c = this.getColor();
            return new Square(x, y, c);
        }

        Square(int x, int y, BGColor color){
            super(x, y, color, shapeForms.get("Square"));
        }
    }

    public static class ZShape extends Shape {
        private boolean mirrored;

        public ZShape clone(){
            int x = this.getCoords()[0];
            int y = this.getCoords()[1];
            BGColor c = this.getColor();
            boolean mirrored = this.mirrored;
            return new ZShape(x, y, c, mirrored);
        }

        ZShape(int x, int y, BGColor color, boolean mirr){
            super(x, y, color, mirr? shapeForms.get("Z_mirrored") : shapeForms.get("Z"));
            this.mirrored = mirr;
        }
    }

    public static class TShape extends Shape {
        public TShape clone(){
            int x = this.getCoords()[0];
            int y = this.getCoords()[1];
            BGColor c = this.getColor();
            return new TShape(x, y, c);
        }

        TShape(int x, int y, BGColor color){
            super(x, y, color, shapeForms.get("T"));
        }
    }

    public static class LShape extends Shape {
        private boolean mirrored;

        public LShape clone(){
            int x = this.getCoords()[0];
            int y = this.getCoords()[1];
            BGColor c = this.getColor();
            boolean mirrored = this.mirrored;
            return new LShape(x, y, c, mirrored);
        }

        LShape(int x, int y, BGColor color, boolean mirr){
            super(x, y, color, mirr? shapeForms.get("L_mirrored") : shapeForms.get("L"));
            this.mirrored = mirr;
        }
    }

    public static class Pillar extends Shape {
        public Pillar clone(){
            int x = this.getCoords()[0];
            int y = this.getCoords()[1];
            BGColor c = this.getColor();
            return new Pillar(x, y, c);
        }
        Pillar(int x, int y, BGColor color){
            super(x, y, color, shapeForms.get("I"));
        }
    }
}
