package com.lld.snake_ladder;

import com.lld.snake_ladder.enums.Difficulty;
import com.lld.snake_ladder.factory.SnakeAndLadderGameFactory;
import com.lld.snake_ladder.model.Board;
import com.lld.snake_ladder.model.SnakeAndLadderPlayer;
import com.lld.snake_ladder.observer.IObserver;
import com.lld.snake_ladder.observer.SnakeAndLadderConsoleNotifier;
import com.lld.snake_ladder.strategy.setup.BoardSetupStrategy;
import com.lld.snake_ladder.strategy.setup.CustomCountBoardSetupStrategy;

import java.util.Scanner;

// Main class for Snake and Ladder
public class SnakeAndLadder {
    public static void main(String[] args) {
        System.out.println("=== SNAKE AND LADDER GAME ===");

        SnakeAndLadderGame game = null;

        System.out.println("Choose game setup:");
        System.out.println("1. Standard Game (10x10 board with traditional positions)");
        System.out.println("2. Random Game with Difficulty");
        System.out.println("3. Custom Game");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if(choice == 1) {
            // Standard game
            game = SnakeAndLadderGameFactory.createStandardGame();

        }
        else if(choice == 2) {
            // Random game with difficulty
            System.out.print("Enter board size (e.g., 10 for 10x10 board): ");
            int boardSize = scanner.nextInt();

            System.out.println("Choose difficulty:");
            System.out.println("1. Easy (more ladders)");
            System.out.println("2. Medium (balanced)");
            System.out.println("3. Hard (more snakes)");

            int diffChoice = scanner.nextInt();

            Difficulty diff;
            switch(diffChoice) {
                case 1: diff = Difficulty.EASY; break;
                case 2: diff = Difficulty.MEDIUM; break;
                case 3: diff = Difficulty.HARD; break;
                default: diff = Difficulty.MEDIUM;
            }

            game = SnakeAndLadderGameFactory.createRandomGame(boardSize, diff);

        }
        else if(choice == 3) {
            // Custom game
            System.out.print("Enter board size (e.g., 10 for 10x10 board): ");
            int boardSize = scanner.nextInt();

            System.out.println("Choose custom setup type:");
            System.out.println("1. Specify counts only (random placement)");
            System.out.println("2. Specify exact positions");

            int customChoice = scanner.nextInt();

            if(customChoice == 1) {
                System.out.print("Enter number of snakes: ");
                int numSnakes = scanner.nextInt();
                System.out.print("Enter number of ladders: ");
                int numLadders = scanner.nextInt();

                BoardSetupStrategy strategy = new CustomCountBoardSetupStrategy(numSnakes, numLadders, true);
                game = SnakeAndLadderGameFactory.createCustomGame(boardSize, strategy);

            }
            else {
                System.out.print("Enter number of snakes: ");
                int numSnakes = scanner.nextInt();
                System.out.print("Enter number of ladders: ");
                int numLadders = scanner.nextInt();

                CustomCountBoardSetupStrategy strategy = new CustomCountBoardSetupStrategy(numSnakes, numLadders, false);

                // Get snake positions
                for(int i = 0; i < numSnakes; i++) {
                    System.out.print("Enter snake " + (i+1) + " start and end positions: ");
                    int start = scanner.nextInt();
                    int end = scanner.nextInt();
                    strategy.addSnakePosition(start, end);
                }

                // Get ladder positions
                for(int i = 0; i < numLadders; i++) {
                    System.out.print("Enter ladder " + (i+1) + " start and end positions: ");
                    int start = scanner.nextInt();
                    int end = scanner.nextInt();
                    strategy.addLadderPosition(start, end);
                }

                game = SnakeAndLadderGameFactory.createCustomGame(boardSize, strategy);
            }

        }

        if(game == null) {
            System.out.println("Invalid choice!");
            scanner.close();
            return;
        }

        // Add observer
        IObserver notifier = new SnakeAndLadderConsoleNotifier();
        game.addObserver(notifier);

        // Create players
        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for(int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for player " + (i+1) + ": ");
            String name = scanner.nextLine();
            SnakeAndLadderPlayer player = new SnakeAndLadderPlayer(i+1, name);
            game.addPlayer(player);
        }

        // Play the game
        game.play();

        scanner.close();
    }
}
