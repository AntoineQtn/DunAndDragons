package game.cell.weaponcell;

import game.cell.Cell;

public class SwordCell extends Cell {
    public SwordCell(int position) {
        super(position);
    }

    @Override
    public String getDisplaySymbol() {
        return "S";
    }

    @Override
    public String getCellType() {
        return "Sword";
    }
}
