package com.lld.snake_ladder.model;

import com.lld.snake_ladder.strategy.setup.BoardSetupStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int size;
    private List<BoardEntity> snakesAndLadders;
    private Map<Integer, BoardEntity> boardEntities;

    public Board(int s) {
        size = s * s;  // m*m board
        snakesAndLadders = new ArrayList<>();
        boardEntities = new HashMap<>();
    }

    public boolean canAddEntity(int position) {
        return !boardEntities.containsKey(position);
    }

    public void addBoardEntity(BoardEntity boardEntity) {
        if(canAddEntity(boardEntity.getStart())) {
            snakesAndLadders.add(boardEntity);
            boardEntities.put(boardEntity.getStart(), boardEntity);
        }
    }

    public void setupBoard(BoardSetupStrategy strategy) {
        strategy.setupBoard(this);
    }

    public BoardEntity getEntity(int position) {
        return boardEntities.get(position);
    }

    public int getBoardSize() {
        return size;
    }

    public void display() {
        System.out.println("\n=== Board Configuration ===");
        System.out.println("Board Size: " + size + " cells");

        int snakeCount = 0;
        int ladderCount = 0;
        for(BoardEntity entity : snakesAndLadders) {
            if(entity.name().equals("SNAKE")) snakeCount++;
            else ladderCount++;
        }

        System.out.println("\nSnakes: " + snakeCount);
        for(BoardEntity entity : snakesAndLadders) {
            if(entity.name().equals("SNAKE")) {
                entity.display();
            }
        }

        System.out.println("\nLadders: " + ladderCount);
        for(BoardEntity entity : snakesAndLadders) {
            if(entity.name().equals("LADDER")) {
                entity.display();
            }
        }
        System.out.println("=========================");
    }
}
