package com.codecool.tetris;

import com.codecool.termlib.*;
import java.util.Random;
import java.util.Arrays;

public class Main {

    private static final String hideCursor =  "\033[?25l";
    private static final String showCursor = "\033[?25h";

    public static void main(String[] args) throws InterruptedException {
        int width = 15;
        int height = 30;
        if(args.length == 2){
            try {
                width = Integer.parseInt(args[0]);
                height = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println(e);
                System.exit(1);
            }
        }

        Terminal t = new Terminal();
        
        final int startPosX = (int)(width / 2);
        final int startPosY = 0;
        Board gameBoard = new Board(height, width);
        int gameSpeed = 700;
        int i = 0;

        Shapes.Shape[] allShapes = new Shapes.Shape[] {
            new Shapes.Square(startPosX, startPosY, BGColor.RED),
            new Shapes.LShape(startPosX, startPosY, BGColor.BLUE, true),
            new Shapes.LShape(startPosX, startPosY, BGColor.BLUE, false),
            new Shapes.TShape(startPosX, startPosY, BGColor.YELLOW),
            new Shapes.ZShape(startPosX, startPosY, BGColor.GREEN, true),
            new Shapes.ZShape(startPosX, startPosY, BGColor.GREEN, false),
            new Shapes.Pillar(startPosX, startPosY, BGColor.CYAN)
        };

        while(true) {
            System.out.println(hideCursor);
            
            Random rnd = new Random();
            int idx = rnd.nextInt(allShapes.length);

            Shapes.Shape sh = allShapes[i];

            TerminalDirection dir = TerminalDirection.DOWN;
            gameBoard.addShape(sh, sh.getColor());

            while(gameBoard.isMovable(sh, TerminalDirection.DOWN)) {
                Thread.sleep(1000 - gameSpeed);
                System.out.println("");
                if(gameBoard.isMovable(sh, dir)) {
                    gameBoard.removeShape(sh, BGColor.BLACK);
                    sh.moveDown();
                    gameBoard.addShape(sh, sh.getColor());
                }
            
                gameBoard.render(t);
            }
            i++;
            if(i>=allShapes.length){break;}

        }
        System.out.println(showCursor);
    }
}
