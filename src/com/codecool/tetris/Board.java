package com.codecool.tetris;

import com.codecool.termlib.*;



public class Board {

    private int row;
    private int column;

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
                System.out.print("   ");
                t.resetStyle();
            }
            System.out.println("");
        }
    }

    Board(int row, int column) {
        this.row = row;
        this.column = column;
        this.board = new BGColor[row][column];
    }

}