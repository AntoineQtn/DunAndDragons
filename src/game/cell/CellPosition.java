package game.cell;

import java.util.Objects;

public class CellPosition extends Cell {
    private int position;

    public CellPosition(int position) {
        this.position = position;
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

