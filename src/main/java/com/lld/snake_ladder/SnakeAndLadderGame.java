package com.lld.snake_ladder;

import com.lld.snake_ladder.model.Board;
import com.lld.snake_ladder.model.BoardEntity;
import com.lld.snake_ladder.model.Dice;
import com.lld.snake_ladder.model.SnakeAndLadderPlayer;
import com.lld.snake_ladder.observer.IObserver;
import com.lld.snake_ladder.strategy.rules.SnakeAndLadderRules;
import com.lld.snake_ladder.strategy.rules.StandardSnakeAndLadderRules;

import java.util.*;

public // Game class
class SnakeAndLadderGame {
    private Board board;
    private Dice dice;
    private Deque<SnakeAndLadderPlayer> players;
    private SnakeAndLadderRules rules;
    private List<IObserver> observers;
    private boolean gameOver;

    public SnakeAndLadderGame(Board b, Dice d) {
        board = b;
        dice = d;
        players = new ArrayDeque<>();
        rules = new StandardSnakeAndLadderRules();
        observers = new ArrayList<>();
        gameOver = false;
    }

    public void addPlayer(SnakeAndLadderPlayer player) {
        players.addLast(player);
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void notify(String msg) {
        for(IObserver observer : observers) {
            observer.update(msg);
        }
    }

    public void displayPlayerPositions() {
        System.out.println("\n=== Current Positions ===");
        for(SnakeAndLadderPlayer player : players) {
            System.out.println(player.getName() + ": " + player.getPosition());
        }
        System.out.println("=======================");
    }

    public void play() {
        if(players.size() < 2) {
            System.out.println("Need at least 2 players!");
            return;
        }

        notify("Game started");

        board.display();

        Scanner scanner = new Scanner(System.in);

        while(!gameOver) {
            SnakeAndLadderPlayer currentPlayer = players.peekFirst();

            System.out.println("\n" + currentPlayer.getName() + "'s turn. Press Enter to roll dice...");
            scanner.nextLine();

            int diceValue = dice.roll();
            System.out.println("Rolled: " + diceValue);

            int currentPos = currentPlayer.getPosition();

            if(rules.isValidMove(currentPos, diceValue, board.getBoardSize())) {
                int intermediatePos = currentPos + diceValue;
                int newPos = rules.calculateNewPosition(currentPos, diceValue, board);

                currentPlayer.setPosition(newPos);

                // Check if player encountered snake or ladder
                BoardEntity entity = board.getEntity(intermediatePos);
                if(entity != null) {
                    boolean isSnake = entity.name().equals("SNAKE");
                    if(isSnake) {
                        System.out.println("Oh no! Snake at " + intermediatePos + "! Going down to " + newPos);
                        notify(currentPlayer.getName() + " encountered snake at " + intermediatePos + " now going down to " + newPos);
                    }
                    else {
                        System.out.println("Great! Ladder at " + intermediatePos + "! Going up to " + newPos);
                        notify(currentPlayer.getName() + " encountered ladder at " + intermediatePos + " now going up to " + newPos);
                    }
                }

                notify(currentPlayer.getName() + " played. New Position : " + newPos);
                displayPlayerPositions();

                if(rules.checkWinCondition(newPos, board.getBoardSize())) {
                    System.out.println("\n" + currentPlayer.getName() + " wins!");
                    currentPlayer.incrementScore();

                    notify("Game Ended. Winner is : " + currentPlayer.getName());
                    gameOver = true;
                }
                else {
                    // Move player to back of queue
                    players.removeFirst();
                    players.addLast(currentPlayer);
                }
            }
            else {
                System.out.println("Need exact roll to reach " + board.getBoardSize() + "!");
                // Move player to back of queue
                players.removeFirst();
                players.addLast(currentPlayer);
            }
        }

        scanner.close();
    }
}
