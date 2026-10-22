package com.lld.snake_ladder.model;

public class Dice {
    private int faces;

    public Dice(int f) {
        faces = f;
    }

    public int roll() {
        return (int)(Math.random() * faces) + 1;
    }
}
