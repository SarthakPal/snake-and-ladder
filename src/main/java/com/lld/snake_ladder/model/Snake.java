package com.lld.snake_ladder.model;

public class Snake extends BoardEntity {
    public Snake(int start, int end) {
        super(start, end);
        if(end >= start) {
            System.out.println("Invalid snake! End must be less than start.");
        }
    }

    @Override
    public void display() {
        System.out.println("Snake: " + startPosition + " -> " + endPosition);
    }

    @Override
    public String name() {
        return "SNAKE";
    }
}

