package game;

import game.cell.Cell;
import game.cell.EmptyCell;
import game.cell.enemycell.DragonCell;
import game.cell.enemycell.GoblinCell;
import game.cell.enemycell.SorcererCell;
import game.cell.potioncell.MajorPotionCell;
import game.cell.potioncell.MinorPotionCell;
import game.cell.spellcell.FireBallCell;
import game.cell.spellcell.LightninBoltCell;
import game.cell.weaponcell.MaceCell;
import game.cell.weaponcell.SwordCell;
import game.exception.PlayerOutOfBoardException;
import java.util.*;

public class Board {
    private static final int length = 64;
    private ArrayList<Cell> cells;
    private static int playerPosition;

    public Board() {

        this.playerPosition = 0;
        this.cells = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            cells.add(new EmptyCell(i));
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
    
private int placeCell (Cell cellToPlace) {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int) (Math.random() * freePositions.size()));
        cells.set(position, cellToPlace);
        return position;
}

    public int placeMinorPotion() {
       return placeCell(new MinorPotionCell(getFreePosition()));
    }

    public int placeMajorPotion() {

        return placeCell(new MajorPotionCell(getFreePosition()));
    }

    public int placeSword() {

        return placeCell(new SwordCell(getFreePosition()));
    }

    public int placeMace() {

        return placeCell(new MaceCell(getFreePosition()));
    }

    public int placeFireBall() {

        return placeCell(new FireBallCell(getFreePosition()));
    }

    public int placeLightninBolt() {

        return placeCell(new LightninBoltCell(getFreePosition()));
    }

    public int placeGoblin() {

        return placeCell(new GoblinCell(getFreePosition()));
    }

    public int placeDragon() {

        return placeCell(new DragonCell(getFreePosition()));
    }

    public int placeSorcerer() {
        return placeCell(new SorcererCell(getFreePosition()));
    }


    private int getFreePosition() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        return freePositions.get((int) (Math.random() * freePositions.size()));
    }

    private List<Integer> getFreePositions() {
        List<Integer> freePositions = new ArrayList<>();
        for (int i = 1; i < length - 1; i++) {
            if (i != playerPosition && cells.get(i) instanceof EmptyCell) {
                freePositions.add(i);
            }
        }
        return freePositions;
    }

    public void movePlayer(int newPosition) throws PlayerOutOfBoardException {
        validatePosition(newPosition);
        this.playerPosition = newPosition;

    }

    private static void validatePosition(int position) throws PlayerOutOfBoardException {
        if (position < 0 || position >= length) {
            throw new PlayerOutOfBoardException(
                    String.format("Position %d is out of bounds (board size: %d)", position, length)
            );
        }
    }

    public String checkPlayerPosition() {

        Cell currentCell = cells.get(playerPosition);
        return currentCell.getDisplaySymbol();
    }

    
    public void removeCell(int position){

        if (position >= 0 && position < length) {
            cells.set(position, new EmptyCell(position));
        }
    }

    public Cell getCell(int position) {
        if (position >= 0 && position < length) {
            return cells.get(position);
        }
        return null;
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