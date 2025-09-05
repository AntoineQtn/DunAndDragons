package game;

import game.cell.Cell;
import game.cell.CellPosition;
import game.exception.PlayerOutOfBoardException;
import java.util.*;

public class Board extends Cell {
    private static final int length = 64;
    private static ArrayList<String> boardDisplay;
    private ArrayList<String> cellTypes;
    private static int playerPosition;

    public Board() {
        dragoncell = new HashSet<>();
        goblincell = new HashSet<>();
        sorcerercell = new HashSet<>();
        swordcell = new HashSet<>();
        macecell = new HashSet<>();
        minorpotioncell = new HashSet<>();
        majorpotioncell = new HashSet<>();
        fireballcell = new HashSet<>();
        lightninboltcell = new HashSet<>();
        emptycell = new HashSet<>();

        this.playerPosition = 0;
        this.boardDisplay = new ArrayList<>(Collections.nCopies(length, "."));
        this.cellTypes = new ArrayList<>(Collections.nCopies(length, "Empty"));

        for (int i = 0; i < length; i++) {
            emptycell.add(new CellPosition(i));
        }

        initializeBoard();
    }

    private void initializeBoard() {

        for (int i = 0; i < 4; i++) {
            placeDragon();
        }
        for (int i = 0; i < 10; i++) {
            placeSorcerer();
        }
        for (int i = 0; i < 10; i++) {
            placeGoblin();
        }
        for (int i = 0; i < 5; i++) {
            placeMace();
        }
        for (int i = 0; i < 4; i++) {
            placeSword();
        }
        for (int i = 0; i < 2; i++) {
            placeFireBall();
        }
        for (int i = 0; i < 5; i++) {
            placeLightninBolt();
        }
        for (int i = 0; i < 6; i++) {
            placeMinorPotion();
        }
        for (int i = 0; i < 2; i++) {
            placeMajorPotion();
        }
    }
    

    public int placeMinorPotion() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int) (Math.random() * freePositions.size()));

        emptycell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        minorpotioncell.add(new CellPosition(position));

        boardDisplay.set(position, "mP");
        cellTypes.set(position, "Minor Potion");

        return position;
    }
    public int placeMajorPotion() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int) (Math.random() * freePositions.size()));

        emptycell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        majorpotioncell.add(new CellPosition(position));

        boardDisplay.set(position, "MP");
        cellTypes.set(position, "Major Potion");

        return position;
    }
    public int placeSword() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int) (Math.random() * freePositions.size()));

        emptycell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        swordcell.add(new CellPosition(position));

        boardDisplay.set(position, "S");
        cellTypes.set(position, "Sword");

        return position;
    }
    public int placeMace() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int) (Math.random() * freePositions.size()));

        emptycell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
       macecell.add(new CellPosition(position));

        boardDisplay.set(position, "m");
        cellTypes.set(position, "Mace");

        return position;
    }
    public int placeFireBall() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int) (Math.random() * freePositions.size()));

        emptycell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        fireballcell.add(new CellPosition(position));

        boardDisplay.set(position, "f");
        cellTypes.set(position, "FireBall Spell");

        return position;
    }
    public int placeLightninBolt() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int) (Math.random() * freePositions.size()));

        emptycell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        lightninboltcell.add(new CellPosition(position));

        boardDisplay.set(position, "lb");
        cellTypes.set(position, "LightninBolt Spell");

        return position;
    }
    public int placeGoblin() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int) (Math.random() * freePositions.size()));

        emptycell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        goblincell.add(new CellPosition(position));

        boardDisplay.set(position, "g");
        cellTypes.set(position, "Goblin");

        return position;
    }
    public int placeDragon() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int) (Math.random() * freePositions.size()));

        emptycell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        dragoncell.add(new CellPosition(position));

        boardDisplay.set(position, "d");
        cellTypes.set(position, "Dragon");

        return position;
    }
    public int placeSorcerer() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int) (Math.random() * freePositions.size()));

        emptycell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        sorcerercell.add(new CellPosition(position));

        boardDisplay.set(position, "s");
        cellTypes.set(position, "Sorcerer");

        return position;
    }
    

    private List<Integer> getFreePositions() {
        List<Integer> freePositions = new ArrayList<>();
        for (int i = 1; i < length - 1; i++) {
            if (isPositionFree(i) && i != playerPosition) {
                freePositions.add(i);
            }
        }
        return freePositions;
    }

    private boolean isPositionFree(int position) {
        return dragoncell.stream().noneMatch(cell -> ((CellPosition) cell).getPosition() == position) &&
                sorcerercell.stream().noneMatch(cell -> ((CellPosition) cell).getPosition() == position) &&
                goblincell.stream().noneMatch(cell -> ((CellPosition) cell).getPosition() == position) &&
                swordcell.stream().noneMatch(cell -> ((CellPosition) cell).getPosition() == position) &&
                macecell.stream().noneMatch(cell -> ((CellPosition) cell).getPosition() == position) &&
                fireballcell.stream().noneMatch(cell -> ((CellPosition) cell).getPosition() == position) &&
                lightninboltcell.stream().noneMatch(cell -> ((CellPosition) cell).getPosition() == position) &&
                minorpotioncell.stream().noneMatch(cell -> ((CellPosition) cell).getPosition() == position) &&
                majorpotioncell.stream().noneMatch(cell -> ((CellPosition) cell).getPosition() == position);
    }

    public void movePlayer(int newPosition) throws PlayerOutOfBoardException {
        validatePosition(newPosition);

        if (playerPosition >= 0 && playerPosition < length &&
                !boardDisplay.get(playerPosition).equals("C") &&
                !boardDisplay.get(playerPosition).equals("E") &&
                !boardDisplay.get(playerPosition).equals("P")) {
            boardDisplay.set(playerPosition, ".");
        }

        this.playerPosition = newPosition;

        if (newPosition != length - 1) {
            String cellContent = checkPlayerPosition();
            if (cellContent == ".") {
                boardDisplay.set(newPosition, "X");
            }
        } else {
            boardDisplay.set(newPosition, "F");
        }
    }

    private static void validatePosition(int position) throws PlayerOutOfBoardException {
        if (position < 0 || position >= length) {
            throw new PlayerOutOfBoardException(
                    String.format("Position %d is out of bounds (board size: %d)", position, length)
            );
        }
    }

    public String checkPlayerPosition() {

        if (dragoncell.stream().anyMatch(cell -> ((CellPosition) cell).getPosition() == playerPosition)) {
            return "d";
        }
        if (sorcerercell.stream().anyMatch(cell -> ((CellPosition) cell).getPosition() == playerPosition)) {
            return "s";
        }
        if (goblincell.stream().anyMatch(cell -> ((CellPosition) cell).getPosition() == playerPosition)) {
            return "g";
        }
        if (swordcell.stream().anyMatch(cell -> ((CellPosition) cell).getPosition() == playerPosition)) {
            return "S";
        }
        if (macecell.stream().anyMatch(cell -> ((CellPosition) cell).getPosition() == playerPosition)) {
            return "m";
        }
        if (lightninboltcell.stream().anyMatch(cell -> ((CellPosition) cell).getPosition() == playerPosition)) {
            return "lb";
        }
        if (fireballcell.stream().anyMatch(cell -> ((CellPosition) cell).getPosition() == playerPosition)) {
            return "f";
        }
        if (minorpotioncell.stream().anyMatch(cell -> ((CellPosition) cell).getPosition() == playerPosition)) {
            return "mP";
        }
        if (majorpotioncell.stream().anyMatch(cell -> ((CellPosition) cell).getPosition() == playerPosition)) {
            return "MP";
        }
        return ".";
    }

    
    public void removeCell(int position){
        dragoncell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        goblincell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        sorcerercell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        swordcell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        macecell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        fireballcell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        lightninboltcell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        minorpotioncell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        majorpotioncell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);

        boardDisplay.set(position, ".");
        cellTypes.set(position, "Empty");
        emptycell.add(new CellPosition(position));
    }



    public int getLength() {
        return length;
    }

    public static int getPlayerPosition() {
        return playerPosition;
    }

    public boolean hasPlayerWon() {
        return playerPosition == length - 1;
    }

}