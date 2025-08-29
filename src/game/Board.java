package game;

import game.exception.PlayerOutOfBoardException;
import java.util.*;

public class Board extends Cell {
    private static final int length = 64;
    private ArrayList<String> boardDisplay;
    private ArrayList<String> cellTypes;
    private int playerPosition;

    public Board() {
        // Initialiser les Sets de la classe parente Cell
        surprisechestcell = new HashSet<>();
        ennemycell = new HashSet<>();
        potioncell = new HashSet<>();
        emptycell = new HashSet<>();

        this.playerPosition = 0;
        this.boardDisplay = new ArrayList<>(Collections.nCopies(length, "."));
        this.cellTypes = new ArrayList<>(Collections.nCopies(length, "Empty"));

        // Initialiser toutes les positions comme vides au début
        for (int i = 0; i < length; i++) {
            emptycell.add(new CellPosition(i));
        }

        // Placer automatiquement quelques éléments pour le test
        initializeBoard();
    }

    private void initializeBoard() {
        // Placer quelques coffres et ennemis aléatoirement
        for (int i = 0; i < 8; i++) { // 8 coffres
            placeChestCase();
        }
        for (int i = 0; i < 10; i++) { // 10 ennemis
            placeEnemy();
        }
        for (int i = 0; i < 6; i++) { // 6 potions
            placePotion();
        }
    }

    public int placeChestCase() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int) (Math.random() * freePositions.size()));

        // Retirer de emptycell et ajouter à surprisechestcell
        emptycell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        surprisechestcell.add(new CellPosition(position));

        // Mettre à jour l'affichage
        boardDisplay.set(position, "C");
        cellTypes.set(position, "Chest");

        return position;
    }

    public int placeEnemy() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int) (Math.random() * freePositions.size()));

        // Retirer de emptycell et ajouter à ennemycell
        emptycell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        ennemycell.add(new CellPosition(position));

        // Mettre à jour l'affichage
        boardDisplay.set(position, "E");
        cellTypes.set(position, "Enemy");

        return position;
    }

    public int placePotion() {
        List<Integer> freePositions = getFreePositions();
        if (freePositions.isEmpty()) {
            return -1;
        }
        int position = freePositions.get((int) (Math.random() * freePositions.size()));

        // Retirer de emptycell et ajouter à potioncell
        emptycell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        potioncell.add(new CellPosition(position));

        // Mettre à jour l'affichage
        boardDisplay.set(position, "P");
        cellTypes.set(position, "Potion");

        return position;
    }

    private List<Integer> getFreePositions() {
        List<Integer> freePositions = new ArrayList<>();
        for (int i = 1; i < length - 1; i++) { // Éviter position 0 (départ) et 63 (arrivée)
            if (isPositionFree(i) && i != playerPosition) {
                freePositions.add(i);
            }
        }
        return freePositions;
    }

    private boolean isPositionFree(int position) {
        return surprisechestcell.stream().noneMatch(cell -> ((CellPosition) cell).getPosition() == position) &&
                ennemycell.stream().noneMatch(cell -> ((CellPosition) cell).getPosition() == position) &&
                potioncell.stream().noneMatch(cell -> ((CellPosition) cell).getPosition() == position);
    }

    public void movePlayer(int newPosition) throws PlayerOutOfBoardException {
        validatePosition(newPosition);

        // Effacer l'ancienne position du joueur
        if (playerPosition >= 0 && playerPosition < length &&
                !boardDisplay.get(playerPosition).equals("C") &&
                !boardDisplay.get(playerPosition).equals("E") &&
                !boardDisplay.get(playerPosition).equals("P")) {
            boardDisplay.set(playerPosition, ".");
        }

        this.playerPosition = newPosition;

        // Marquer la nouvelle position (sauf si c'est la case d'arrivée)
        if (newPosition != length - 1) {
            char cellContent = checkPlayerPosition();
            if (cellContent == '.') {
                boardDisplay.set(newPosition, "X"); // X pour joueur
            }
        } else {
            boardDisplay.set(newPosition, "F"); // F pour finish
        }
    }

    private void validatePosition(int position) throws PlayerOutOfBoardException {
        if (position < 0 || position >= length) {
            throw new PlayerOutOfBoardException(
                    String.format("Position %d is out of bounds (board size: %d)", position, length)
            );
        }
    }

    public char checkPlayerPosition() {
        if (surprisechestcell.stream().anyMatch(cell -> ((CellPosition) cell).getPosition() == playerPosition)) {
            return 'C';
        }
        if (ennemycell.stream().anyMatch(cell -> ((CellPosition) cell).getPosition() == playerPosition)) {
            return 'E';
        }
        if (potioncell.stream().anyMatch(cell -> ((CellPosition) cell).getPosition() == playerPosition)) {
            return 'P';
        }
        return '.';
    }

    public void removeChest(int position) {
        surprisechestcell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        boardDisplay.set(position, ".");
        cellTypes.set(position, "Empty");
        emptycell.add(new CellPosition(position));
    }

    public void removeEnemy(int position) {
        ennemycell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        boardDisplay.set(position, ".");
        cellTypes.set(position, "Empty");
        emptycell.add(new CellPosition(position));
    }

    public void removePotion(int position) {
        potioncell.removeIf(cell -> ((CellPosition) cell).getPosition() == position);
        boardDisplay.set(position, ".");
        cellTypes.set(position, "Empty");
        emptycell.add(new CellPosition(position));
    }

    // Méthodes d'affichage
    public void displayBoard() {
        System.out.println("=== PLATEAU DE JEU (64 cases) ===");
        System.out.println("Légende: X=Joueur, C=Coffre, E=Ennemi, P=Potion, .=Vide, F=Arrivée");
        System.out.println();

        for (int i = 0; i < length; i++) {
            if (i % 8 == 0) {
                System.out.printf("%2d: ", i);
            }

            String display = boardDisplay.get(i);
            if (i == playerPosition && !display.equals("C") && !display.equals("E") && !display.equals("P")) {
                display = "X";
            }

            System.out.print(display + " ");

            if ((i + 1) % 8 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Position joueur: " + playerPosition);
        System.out.println("Type de case: " + cellTypes.get(playerPosition));
        System.out.println();
    }

    public void displayStats() {
        System.out.println("=== STATISTIQUES DU PLATEAU ===");
        System.out.println("Coffres: " + surprisechestcell.size());
        System.out.println("Ennemis: " + ennemycell.size());
        System.out.println("Potions: " + potioncell.size());
        System.out.println("Cases vides: " + emptycell.size());
        System.out.println("Position joueur: " + playerPosition);
        System.out.println("Victoire: " + (hasPlayerWon() ? "OUI" : "NON"));
        System.out.println();
    }

    // Getters
    public int getLength() {
        return length;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public boolean hasPlayerWon() {
        return playerPosition == length - 1;
    }

    public String getCellType(int position) {
        if (position >= 0 && position < length) {
            return cellTypes.get(position);
        }
        return "Invalid";
    }

    // Classe interne pour représenter une position de cellule
    private static class CellPosition extends Cell {
        private int position;

        public CellPosition(int position) {
            this.position = position;
            // Les Sets seront null car on ne les utilise pas dans cette sous-classe
        }

        public int getPosition() {
            return position;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            CellPosition that = (CellPosition) obj;
            return position == that.position;
        }

        @Override
        public int hashCode() {
            return Objects.hash(position);
        }
    }
}