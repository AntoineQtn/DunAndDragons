package game.cell.enemycell;

import game.cell.Cell;

public class GoblinCell extends Cell {
    public GoblinCell(int position) {
        super(position);
    }

    @Override
    public String getDisplaySymbol() {
        return "g";
    }

    @Override
    public String getCellType() {
        return "Goblin";
    }
}
