package game.cell;

import java.util.*;

public abstract class Cell {

    private int position;

    public Cell(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    /**
     * Retourne le symbole d'affichage de la cellule
     */
    public abstract String getDisplaySymbol();

    /**
     * Retourne le type de la cellule pour identification
     */
    public abstract String getCellType();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cell cell = (Cell) obj;
        return position == cell.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, getClass());
    }

}
