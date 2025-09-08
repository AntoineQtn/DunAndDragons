package game.cell.spellcell;

import game.cell.Cell;

public class LightninBoltCell extends Cell {
    public LightninBoltCell(int position) {
        super(position);
    }

    @Override
    public String getDisplaySymbol() {
        return "lb";
    }

    @Override
    public String getCellType() {
        return "LightningBolt Spell";
    }
}
