package com.codecool.tetris;

import java.io.IOException;
import java.util.*;

public class IOHandler {

    private class Worker implements Runnable {
        private boolean isRunning;
        private Queue<Integer> buffer;
        private final Object threadLock;
        long sleepTime;


        public void sendStopSignal() {
            isRunning = false;
        }


        public void run() {
            int inputChar = 0;
            boolean inputHandled;
            isRunning = true;

            while (isRunning) {
                inputHandled = false;

                try {
                    if (System.in.available() > 0) {
                        inputChar = System.in.read();
                        inputHandled = true;
                    }
                } catch (IOException ignored) {}

                if (inputHandled) {
                    synchronized (threadLock) {
                        buffer.add(inputChar);
                    }
                }
                else {
                    try {
                        Thread.sleep(sleepTime);
                    }
                    catch (InterruptedException ignored) {}
                }
            }
        }


        public int popFromBuffer() {
            synchronized (threadLock) {
                return buffer.remove();
            }
        }


        public Worker(long sleepTime) {
            isRunning = false;
            buffer = new LinkedList<>();
            threadLock = new Object();
            this.sleepTime = sleepTime;
        }
    }

    private Worker worker;
    private Thread thread;


    public void start() {
        if (!thread.isAlive()) {
            thread.start();
        }
    }


    public void stop() {
        if (thread.isAlive()) {
            try {
                worker.sendStopSignal();
                thread.join();
            }
            catch (InterruptedException ignored) {}
        }
    }


    public int readFromBuffer() throws NoSuchElementException {
        return worker.popFromBuffer();
    }


    public void setEchoStatus(boolean status) {
        String[] cmd;

        if (status) {
            cmd = new String[] {
                "/bin/sh",
                "-c",
                "stty cooked echo"
            };
        }
        else {
            cmd = new String[] {
                "/bin/sh",
                "-c",
                "stty raw -echo"
            };
        }

        try {
            Runtime.getRuntime().exec(cmd);
        }
        catch (IOException exc) {
            throw new RuntimeException("Failed to change echo status");
        }
    }


    IOHandler(long workerSleepTime) {
        worker = new Worker(workerSleepTime);
        thread = new Thread(worker);
    }
}
