package com.lld.snake_ladder.strategy.rules;

import com.lld.snake_ladder.model.Board;
import com.lld.snake_ladder.model.BoardEntity;

// Standard rules
public class StandardSnakeAndLadderRules implements SnakeAndLadderRules {
    @Override
    public boolean isValidMove(int currentPos, int diceValue, int boardSize) {
        return (currentPos + diceValue) <= boardSize;
    }

    @Override
    public int calculateNewPosition(int currentPos, int diceValue, Board board) {
        int newPos = currentPos + diceValue;
        BoardEntity entity = board.getEntity(newPos);

        if(entity != null) {
            return entity.getEnd();
        }
        return newPos;
    }

    @Override
    public boolean checkWinCondition(int position, int boardSize) {
        return position == boardSize;
    }
}
