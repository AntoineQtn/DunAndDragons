package game;

public class PlayerOutOfBoardException extends Exception {
    private int attemptedPosition;
    private int boardSize;
    private String playerName;

    public PlayerOutOfBoardException(String message) {
        super(message);
    }

    private static String buildMessage(String playerName, int attemptedPosition, int boardSize) {
        return String.format(
                "Player '%s' attempted to move to position %d, but board size is only %d (valid positions: 0-%d)",
                playerName, attemptedPosition, boardSize, boardSize - 1
        );
    }

    public int getAttemptedPosition() {
        return attemptedPosition;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public String getPlayerName() {
        return playerName;
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
