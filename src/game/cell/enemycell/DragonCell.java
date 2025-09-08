package game.cell.enemycell;

import game.cell.Cell;

public class DragonCell extends Cell {
    public DragonCell(int position) {
        super(position);
    }

    @Override
    public String getDisplaySymbol() {
        return "d";
    }

    @Override
    public String getCellType() {
        return "Dragon";
    }
}
