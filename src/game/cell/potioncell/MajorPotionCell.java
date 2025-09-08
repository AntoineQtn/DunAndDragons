package game.cell.potioncell;

import game.cell.Cell;

public class MajorPotionCell extends Cell {
    public MajorPotionCell(int position) {
        super(position);
    }

    @Override
    public String getDisplaySymbol() {
        return "MP";
    }

    @Override
    public String getCellType() {
        return "Major Potion";
    }
}
