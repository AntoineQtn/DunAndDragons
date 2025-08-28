package exception;

/**
 * Exception thrown when a player attempts to move outside the board.
 * This exception is used to handle invalid player positioning.
 * The suggested position is set to the maximum valid position.
 */
public class PlayerOutOfBoardException extends Exception {
    private int attemptedPosition;
    private int boardSize;
    private String playerName;

    /**
     * Constructor for PlayerOutOfBoardException.
     * Sets the attempted position and board size.
     * @param message
     */
    public PlayerOutOfBoardException(String message) {
        super(message);
    }


    public int getSuggestedPosition() {
        return Math.max(0, boardSize - 1);
    }

    public String getDetailedErrorMessage() {
        return String.format(
                " MOVEMENT ERROR \n" +
                        "Player: %s\n" +
                        "Attempted position: %d\n" +
                        "Board size: %d (positions 0-%d)\n" +
                        "Suggested position: %d",
                playerName, attemptedPosition, boardSize, boardSize - 1
        );
    }
}
