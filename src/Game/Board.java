package Game;

import java.util.*;

public class Board {
    private static final int SIZE = 64; // Taille fixe du plateau
    private final String name;
    private Set<Integer> chestPositions;
    private Set<Integer> enemyPositions;
    private int playerPosition;

    public Board(String name) {
        this.name = name;
        this.chestPositions = new HashSet<>();
        this.enemyPositions = new HashSet<>();
        this.playerPosition = 0; // Le joueur commence à la position 0
    }

    /**
     * Place un coffre à une position aléatoire libre
     * @return la position du coffre placé, -1 si aucune position libre
     */
    public int placeChestCase() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int)(Math.random() * freePositions.size()));
        chestPositions.add(position);
        return position;
    }

    /**
     * Place un ennemi à une position aléatoire libre
     * @return la position de l'ennemi placé, -1 si aucune position libre
     */
    public int placeEnemy() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int)(Math.random() * freePositions.size()));
        enemyPositions.add(position);
        return position;
    }

    /**
     * Retourne la liste des positions libres sur le plateau
     */
    private List<Integer> getFreePositions() {
        List<Integer> freePositions = new ArrayList<>();
        for (int i = 1; i < SIZE; i++) {
            if (!chestPositions.contains(i) && !enemyPositions.contains(i) && i != playerPosition) {
                freePositions.add(i);
            }
        }
        return freePositions;
    }

    /**
     * Déplace le joueur à une nouvelle position
     * @param newPosition la nouvelle position du joueur
     * @return true si le mouvement est valide, false sinon
     */
    public boolean movePlayer(int newPosition) {
        if (newPosition < 0 || newPosition >= SIZE) {
            return false;
        }
        this.playerPosition = newPosition;
        return true;
    }

    /**
     * Vérifie ce qui se trouve à la position du joueur
     * @return 'C' pour coffre, 'E' pour ennemi, '.' pour vide
     */
    public char checkPlayerPosition() {
        if (chestPositions.contains(playerPosition)) {
            return 'C';
        }
        if (enemyPositions.contains(playerPosition)) {
            return 'E';
        }
        return '.';
    }

    public void removeChest(int position) {
        chestPositions.remove(position);
    }

    public void removeEnemy(int position) {
        enemyPositions.remove(position);
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getSize() {
        return SIZE;
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
        return playerPosition == SIZE - 1;
    }
}
