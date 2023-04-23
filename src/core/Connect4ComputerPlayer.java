package core;

import java.util.Random;

/**
 * This class represents a computer player for the Connect4 game.
 * It provides a simple algorithm to generate moves for the computer player.
 *
 * @author (Jessica Oppelt)
 * @version (1.0)
 */
public class Connect4ComputerPlayer {

    private final Random random;

    /**
     * Constructs a Connect4ComputerPlayer object.
     */
    public Connect4ComputerPlayer() {
        random = new Random();
    }

    /**
     * Generates a move for the computer player by choosing a random valid column.
     *
     * @param grid The current game grid.
     * @return The column index for the computer player's move.
     */
    public int generateMove(char[][] grid) {
        int column;
        do {
            column = random.nextInt(grid[0].length);
        } while (!Connect4Logic.validate(column, grid));
        return column;
    }
}
