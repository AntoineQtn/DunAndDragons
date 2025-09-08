package game.cell.enemycell;

import game.cell.Cell;

public class SorcererCell extends Cell {
    public SorcererCell(int position) {
        super(position);
    }

    @Override
    public String getDisplaySymbol() {
        return "s";
    }

    @Override
    public String getCellType() {
        return "Sorcerer";
    }
}
