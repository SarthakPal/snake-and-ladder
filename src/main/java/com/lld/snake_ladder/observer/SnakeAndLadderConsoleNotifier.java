package com.lld.snake_ladder.observer;

public class SnakeAndLadderConsoleNotifier implements IObserver {
    public void update(String msg) {
        System.out.println("[NOTIFICATION] " + msg);
    }
}

