package com.lld.snake_ladder.model;

public abstract class BoardEntity {
    protected int startPosition;
    protected int endPosition;

    public BoardEntity(int start, int end) {
        startPosition = start;
        endPosition = end;
    }

    public int getStart() {
        return startPosition;
    }

    public int getEnd() {
        return endPosition;
    }

    public abstract void display();
    public abstract String name();
}
