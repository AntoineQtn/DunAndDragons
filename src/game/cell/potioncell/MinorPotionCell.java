package game.cell.potioncell;

import game.cell.Cell;

public class MinorPotionCell extends Cell {
    public MinorPotionCell(int position) {
        super(position);
    }

    @Override
    public String getDisplaySymbol() {
        return "mP";
    }

    @Override
    public String getCellType() {
        return "Minor Potion";
    }
}
