package game.cell.spellcell;

import game.cell.Cell;

public class FireBallCell extends Cell {
    public FireBallCell(int position) {
        super(position);
    }

    @Override
    public String getDisplaySymbol() {
        return "f";
    }

    @Override
    public String getCellType() {
        return "FireBall Spell";
    }
}
