package com.lld.snake_ladder.strategy.setup;

import com.lld.snake_ladder.model.Board;
import com.lld.snake_ladder.model.Ladder;
import com.lld.snake_ladder.model.Snake;

import java.util.ArrayList;
import java.util.List;

// Custom Strategy - User provides count of snakes and ladders, and optionally their positions. If positions are not provided, they will be randomly generated.
public class CustomCountBoardSetupStrategy implements BoardSetupStrategy {
    private int numSnakes;
    private int numLadders;
    private boolean randomPositions;
    private List<Pair<Integer, Integer>> snakePositions;
    private List<Pair<Integer, Integer>> ladderPositions;

    // Simple Pair class for Java
    private static class Pair<T, U> {
        public final T first;
        public final U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }

    public CustomCountBoardSetupStrategy(int snakes, int ladders, boolean random) {
        numSnakes = snakes;
        numLadders = ladders;
        randomPositions = random;
        snakePositions = new ArrayList<>();
        ladderPositions = new ArrayList<>();
    }

    public void addSnakePosition(int start, int end) {
        snakePositions.add(new Pair<>(start, end));
    }

    public void addLadderPosition(int start, int end) {
        ladderPositions.add(new Pair<>(start, end));
    }

    @Override
    public void setupBoard(Board board) {
        if(randomPositions) {
            // Random placement with user-defined counts
            int boardSize = board.getBoardSize();

            // Add snakes
            int snakesAdded = 0;
            while(snakesAdded < numSnakes) {
                int start = (int)(Math.random() * (boardSize - 10)) + 10;
                int end = (int)(Math.random() * (start - 1)) + 1;

                if(board.canAddEntity(start)) {
                    board.addBoardEntity(new Snake(start, end));
                    snakesAdded++;
                }
            }

            // Add ladders
            int laddersAdded = 0;
            while(laddersAdded < numLadders) {
                int start = (int)(Math.random() * (boardSize - 10)) + 1;
                int end = (int)(Math.random() * (boardSize - start)) + start + 1;

                if(board.canAddEntity(start) && end < boardSize) {
                    board.addBoardEntity(new Ladder(start, end));
                    laddersAdded++;
                }
            }
        }
        else {
            // User-defined positions
            for(Pair<Integer, Integer> pos : snakePositions) {
                if(board.canAddEntity(pos.first)) {
                    board.addBoardEntity(new Snake(pos.first, pos.second));
                }
            }

            for(Pair<Integer, Integer> pos : ladderPositions) {
                if(board.canAddEntity(pos.first)) {
                    board.addBoardEntity(new Ladder(pos.first, pos.second));
                }
            }
        }
    }
}
