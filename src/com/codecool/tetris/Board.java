package com.codecool.tetris;

import com.codecool.termlib.*;
import com.codecool.tetris.Shapes.Shape;


public class Board {

    private final int row;
    private final int column;
    private BGColor[][] board;

    public void reset() {
        for (int i=0; i<row; i++){
            for (int j=0; j<column; j++) {
                board[i][j] = BGColor.BLACK;
            }
        }
    }

    public void render(Terminal t) {
        for (int i=0; i<row; i++){
            for (int j=0; j<column; j++) {
                t.setBgColor(board[i][j]);
                System.out.print(" 0 ");
                t.resetStyle();
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

    public void addShape(Shape sh){
        int[] coords = sh.getCoords();
        TerminalDirection[] formula = sh.getFormula();
        BGColor color = sh.getColor();
        //board[coords[0]][coords[1]] = color;

        
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

    Board(int row, int column) {
        this.row = row;
        this.column = column;
        this.board = new BGColor[row][column];
    }

}