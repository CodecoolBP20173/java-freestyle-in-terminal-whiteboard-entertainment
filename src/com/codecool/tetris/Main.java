package com.codecool.tetris;

import com.codecool.termlib.*;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Arrays;

public class Main {

    private static final String hideCursor =  "\033[?25l";
    private static final String showCursor = "\033[?25h";
    private static final int tickRate = 40;

    public static void main(String[] args) throws InterruptedException {
        IOHandler ioHandler = new IOHandler(20);
        Random rnd = new Random();

        ioHandler.start();
        ioHandler.setEchoStatus(false);
        System.out.println(hideCursor);

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

        int tickCount = 0;
        char currentKey;
        int shapeIndex = rnd.nextInt(allShapes.length);
        Shapes.Shape currentShape = allShapes[shapeIndex].clone();

        while(true) {
            try {
                currentKey = Character.toLowerCase((char)ioHandler.readFromBuffer());

                if (currentKey == 'a' || currentKey == 'd') {
                    TerminalDirection direction = currentKey == 'a' ? TerminalDirection.LEFT : TerminalDirection.RIGHT;

                    if (gameBoard.isMovable(currentShape, direction)) {
                        gameBoard.removeShape(currentShape, BGColor.BLACK);
                        if (direction == TerminalDirection.LEFT) currentShape.moveLeft();
                        else currentShape.moveRight();
                        gameBoard.addShape(currentShape, currentShape.getColor());
                    }
                }
                else if (currentKey == 's') {
                    if (gameBoard.isMovable(currentShape, TerminalDirection.DOWN)) {
                        gameBoard.removeShape(currentShape, BGColor.BLACK);
                        currentShape.moveDown();
                        gameBoard.addShape(currentShape, currentShape.getColor());
                        tickCount = gameBoard.getMoveDownTickCount();
                    }
                }
                else if (currentKey == 'q') {
                    break;
                }

            }
            catch (NoSuchElementException ignore) {}


            if (tickCount == gameBoard.getMoveDownTickCount()) {
                tickCount = 0;

                if (gameBoard.isMovable(currentShape, TerminalDirection.DOWN)) {
                    gameBoard.removeShape(currentShape, BGColor.BLACK);
                    currentShape.moveDown();
                    gameBoard.addShape(currentShape, currentShape.getColor());
                }
                else {
                    shapeIndex = rnd.nextInt(allShapes.length);
                    currentShape = allShapes[shapeIndex].clone();

                    if (gameBoard.isMovable(currentShape, TerminalDirection.DOWN)) {
                        gameBoard.addShape(currentShape, currentShape.getColor());
                    }
                    else {
                        break;
                    }
                }
            }

            tickCount++;
            if (tickCount % 5 == 0) gameBoard.render(t);
            Thread.sleep(1000 / tickRate);
        }

        ioHandler.setEchoStatus(false);
        ioHandler.stop();
        System.out.println(showCursor);
    }
}
