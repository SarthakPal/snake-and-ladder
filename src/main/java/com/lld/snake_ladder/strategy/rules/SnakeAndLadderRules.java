package com.lld.snake_ladder.strategy.rules;

import com.lld.snake_ladder.model.Board;

// Strategy Pattern for game rules
public interface SnakeAndLadderRules {
    boolean isValidMove(int currentPos, int diceValue, int boardSize);
    int calculateNewPosition(int currentPos, int diceValue, Board board);
    boolean checkWinCondition(int position, int boardSize);
}
