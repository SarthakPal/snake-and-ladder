package com.lld.snake_ladder.strategy.setup;

import com.lld.snake_ladder.model.Board;
import com.lld.snake_ladder.model.Ladder;
import com.lld.snake_ladder.model.Snake;

// Standard Board Strategy - Traditional Snake & Ladder positions
public class StandardBoardSetupStrategy implements BoardSetupStrategy {

    @Override
    public void setupBoard(Board board) {
        // Only works for 10x10 board (100 cells)
        if(board.getBoardSize() != 100) {
            System.out.println("Standard setup only works for 10x10 board!");
            return;
        }

        // Standard snake positions (based on traditional board)
        board.addBoardEntity(new Snake(99, 54));
        board.addBoardEntity(new Snake(95, 75));
        board.addBoardEntity(new Snake(92, 88));
        board.addBoardEntity(new Snake(89, 68));
        board.addBoardEntity(new Snake(74, 53));
        board.addBoardEntity(new Snake(64, 60));
        board.addBoardEntity(new Snake(62, 19));
        board.addBoardEntity(new Snake(49, 11));
        board.addBoardEntity(new Snake(46, 25));
        board.addBoardEntity(new Snake(16, 6));

        // Standard ladder positions
        board.addBoardEntity(new Ladder(2, 38));
        board.addBoardEntity(new Ladder(7, 14));
        board.addBoardEntity(new Ladder(8, 31));
        board.addBoardEntity(new Ladder(15, 26));
        board.addBoardEntity(new Ladder(21, 42));
        board.addBoardEntity(new Ladder(28, 84));
        board.addBoardEntity(new Ladder(36, 44));
        board.addBoardEntity(new Ladder(51, 67));
        board.addBoardEntity(new Ladder(71, 91));
        board.addBoardEntity(new Ladder(78, 98));
        board.addBoardEntity(new Ladder(87, 94));
    }
}
