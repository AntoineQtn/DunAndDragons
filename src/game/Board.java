package game;

import java.util.*;

public class Board {

    private static final int length = 64;
    private Set<Integer> chestPositions;
    private Set<Integer> enemyPositions;
    private int playerPosition;

    public Board() {
        this.chestPositions = new HashSet<>();
        this.enemyPositions = new HashSet<>();
        this.playerPosition = 0;
    }

    public int placeChestCase() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int)(Math.random() * freePositions.size()));
        chestPositions.add(position);
        return position;
    }

    public int placeEnemy(){
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int)(Math.random() * freePositions.size()));
        enemyPositions.add(position);
        return position;
    }

    private List<Integer> getFreePositions(){
        List<Integer> freePositions = new ArrayList<>();
        for (int i = 1; i < length; i++) {
            if (!chestPositions.contains(i) && !enemyPositions.contains(i) && i != playerPosition) {
                freePositions.add(i);
            }
        }
        return freePositions;
    }

    public boolean movePlayer(int newPosition){
        if (newPosition < 0 || newPosition >= length) {
            return false;
        }
        this.playerPosition = newPosition;
        return true;
    }

    public char checkPlayerPosition() {
        if (chestPositions.contains(playerPosition)) {
            return 'C';
        }
        if (enemyPositions.contains(playerPosition)) {
            return 'E';
        }
        return '.';
    }

    public int getLength(){return length;}

    public void removeChest(int position) {
        chestPositions.remove(position);
    }

    public void removeEnemy(int position) {
        enemyPositions.remove(position);
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public Set<Integer> getChestPositions() {
        return new HashSet<>(chestPositions);
    }

    public Set<Integer> getEnemyPositions() {
        return new HashSet<>(enemyPositions);
    }

    public boolean hasPlayerWon() {
        return playerPosition == length - 1;
    }

}
