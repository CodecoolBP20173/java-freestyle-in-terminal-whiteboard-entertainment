package com.codecool.tetris;

import com.codecool.termlib.*;
import com.codecool.tetris.Shapes.Shape;
import java.util.HashMap;
import java.util.Map;


public class Board {

    private final int moveDownTickCount;
    private final int row;
    private final int column;
    private BGColor[][] board;
    private int score = 0;

    public void reset() {
        for (int i=0; i<row; i++){
            for (int j=0; j<column; j++) {
                board[i][j] = BGColor.BLACK;
            }
        }
    }

    public void render(Terminal t) {

        t.clearScreen();
        printLogo(t);

        for (int i=0; i<row; i++){

            t.resetStyle();
            System.out.print("        ");

            for (int j=0; j<column; j++) {
                t.setBgColor(board[i][j]);
                System.out.print("  ");
                t.resetStyle();
            }

            if (i == 2) {
                System.out.print("           Score: " + score);
            } else if (i == 3) {
                System.out.print("           Highest score:");                
            }

            if (score < 10) {
                
                for (Integer key : Girl.scoreCase1.keySet()) {
                    String value = Girl.scoreCase1.get(key);
                    if (key == i){
                        System.out.print(value);
                    }
                }

            } else if (score >= 10) {

                for (Integer key : Girl.scoreCase2.keySet()) {
                    String value = Girl.scoreCase2.get(key);
                    if (key == i){
                        System.out.print(value);
                    }
                }

            } else if (score >= 20) {
                
                for (Integer key : Girl.scoreCase3.keySet()) {
                    String value = Girl.scoreCase3.get(key);
                    if (key == i){
                        System.out.print(value);
                    }
                }

            }
            System.out.println("");
        }
    }

    /*
    * Checks if a row is full, 
    * @param row A row full of BGColors
    **/
    public boolean isRowFull(BGColor[] row) {

        for (int i=0; i<row.length; i++) {
            if (row[i] == BGColor.BLACK) {
                return false;
            }
        }
        score += 10;
        return true;
    }

    /*
    * This gets called if a row is full
    * It rearranges the rows, every row gets lower 1 level above the row which got full of elements
    * @param row An index of the row which is full
    **/
    public void rearrangeRows(int rowIndex) {

        for (int i=rowIndex; i>0; i--) {
            board[i] = board[i - 1];
        }

        for (int j=0; j<column; j++) {
            board[0][j] = BGColor.BLACK;
        }
    }

    public void removeShape(Shape sh, BGColor color){
        addShape(sh, color);
    }

    public void addShape(Shape sh, BGColor color){
        int[] coords = sh.getCoords();
        TerminalDirection[] formula = sh.getFormula();
        
        int shapeX = coords[0];
        int shapeY = coords[1];
        Coord shapePos = new Coord(shapeX, shapeY);
        int x = shapePos.getCoordArray()[0];
        int y = shapePos.getCoordArray()[1];
        board[y][x] = color;
        for (TerminalDirection dir : formula) {
            shapePos.changeCoords(dir);
            x = shapePos.getCoordArray()[0];
            y = shapePos.getCoordArray()[1];
            board[y][x] = color;

        }

    }
                                                                        
    public void printLogo(Terminal t) {
        //System.out.print("\033[48;5;231m");
        //System.out.print("\033[38;5;202m");
    

        String logo = "                                                                           \n";
        logo += "  ooooooooooooo oooooooooooo ooooooooooooo ooooooooo.   ooooo  .oooooo..o\n";
        logo += "  8'   888   `8 `888'     `8 8'   888   `8 `888   `Y88. `888' d8P'    `Y8  \n";
        logo += "       888       888              888       888   .d88'  888  Y88bo.       \n";
        logo += "       888       888oooo8         888       888ooo88P'   888   `\"Y8888o.   \n";
        logo += "       888       888    \"         888       888`88b.     888       `\"Y88b  \n";
        logo += "       888       888       o      888       888  `88b.   888  oo     .d8P  \n";
        logo += "      o888o     o888ooooood8     o888o     o888o  o888o o888o 8\"\"88888P'";
                
        System.out.print(logo);
    }
    
    private boolean fitsInBounds(int x, int y){
        if(x >= this.column || x < 0 || y >= this.row || y < 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean hasShape(int x, int y){
        if(this.board[y][x] == BGColor.BLACK){
            return false;
        } else {
            return true;
        }
    }

    public boolean isMovable(Shape sh, TerminalDirection dir){
        this.removeShape(sh, BGColor.BLACK);
        int[] coords = sh.getCoords();
        TerminalDirection[] formula = sh.getFormula();
        
        int shapeX = coords[0];
        int shapeY = coords[1];
        Coord shapePos = new Coord(shapeX, shapeY);
        shapePos.changeCoords(dir);
        int x = shapePos.getCoordArray()[0];
        int y = shapePos.getCoordArray()[1];
        boolean fits = true;

        if(fitsInBounds(x, y) && !hasShape(x, y)) {
            for (TerminalDirection direct : formula) {
                shapePos.changeCoords(direct);
                x = shapePos.getCoordArray()[0];
                y = shapePos.getCoordArray()[1];
                if (!fitsInBounds(x, y) || hasShape(x, y)) {
                    fits = false;
                    break;
                }
            }
            this.addShape(sh, sh.getColor());
            return fits;
        } else {
            this.addShape(sh, sh.getColor());
            return false;
        }
        
    }

    int getMoveDownTickCount() {
        return this.moveDownTickCount;
    }

    Board(int row, int column) {
        this.row = row;
        this.column = column;
        this.board = new BGColor[row][column];
        this.moveDownTickCount = 14;
        reset();
    }

}
