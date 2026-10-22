package com.lld.snake_ladder.model;

public class Ladder extends BoardEntity {
    public Ladder(int start, int end) {
        super(start, end);
        if(end <= start) {
            System.out.println("Invalid ladder! End must be greater than start.");
        }
    }

    @Override
    public void display() {
        System.out.println("Ladder: " + startPosition + " -> " + endPosition);
    }

    @Override
    public String name() {
        return "LADDER";
    }
}

