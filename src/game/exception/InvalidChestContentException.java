package game.exception;

public class InvalidChestContentException extends RuntimeException {

    private final int attemptedPosition;
    private final String playerName;

    public InvalidChestContentException(String message, String playerName, int attemptedPosition) {
        super(message);
        this.playerName = playerName;
        this.attemptedPosition = attemptedPosition;
    }

    /**
     * Suggest a fallback position: one case before attempted position.
     */
    public int getSuggestedPosition() {
        return Math.max(0, attemptedPosition - 1); // 🔥 recule d’une case
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " | " + playerName + " is moved back to position " + getSuggestedPosition();
    }
}
