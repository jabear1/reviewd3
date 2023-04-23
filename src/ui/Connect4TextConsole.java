package ui;

import core.Connect4ComputerPlayer;
import core.Connect4Logic;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class represents the text-based user interface for the Connect4 game.
 * It allows players to interact with the game through the command line.
 *
 * @author (Jessica Oppelt)
 * @version (1.0)
 */
public class Connect4TextConsole {

    /**
     * The main method, which executes the game loop.
     *
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        char[][] grid = new char[6][7];

        // Initialize array
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = ' ';
            }
        }

        int turn = 1;
        char player = 'R';
        boolean winner = false;

        System.out.println("Begin game, Choose game mode:");
        System.out.println("Enter ‘P’ if you want to play against another player");
        System.out.println("Enter ‘C’ to play against computer");
        char gameMode;
        do {
            System.out.print("Enter P or C: ");
            gameMode = in.next().charAt(0);
        } while (gameMode != 'P' && gameMode != 'C');

        Connect4ComputerPlayer computerPlayer = new Connect4ComputerPlayer();

        // Play a turn
        while (!winner && turn <= 42) {
            boolean validPlay;
            int play = -1;
            do {
                display(grid);

                if (player == 'R' || gameMode == 'P') {
                    System.out.print("Player " + player + ", choose a column: ");
                    try {
                        play = in.nextInt();
                        validPlay = Connect4Logic.validate(play, grid);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number between 0 and 6.");
                        validPlay = false;
                        in.nextLine(); // Discard invalid input
                    }
                } else {
                    play = computerPlayer.generateMove(grid);
                    validPlay = true;
                }
            } while (!validPlay);

            // Drop the checker
            for (int row = grid.length - 1; row >= 0; row--) {
                if (grid[row][play] == ' ') {
                    grid[row][play] = player;
                    break;
                }
            }

            // Determine if there is a winner
            winner = Connect4Logic.isWinner(player, grid);

            // Switch players
            if (player == 'R') {
                player = 'B';
            } else {
                player = 'R';
            }

            turn++;
        }
        display(grid);

        if (winner) {
            if (player == 'R') {
                System.out.println("Black won has won the game");
            } else {
                System.out.println("Red won has won the game");
            }
        } else {
            System.out.println("Tie game");
        }
    }

    /**
     * Displays the game grid on the console.
     *
     * @param grid The current game grid.
     */
    public static void display(char[][] grid) {
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println("---------------");
        for (int row = 0; row < grid.length; row++) {
            System.out.print("|");
            for (int col = 0; col < grid[0].length; col++) {
                System.out.print(grid[row][col]);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("---------------");
        }
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println();
    }
}