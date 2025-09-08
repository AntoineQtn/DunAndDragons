package game.cell.weaponcell;

import game.cell.Cell;

public class MaceCell extends Cell {
    public MaceCell(int position) {
        super(position);
    }

    @Override
    public String getDisplaySymbol(){
        return "m";
    }

    @Override
    public String getCellType(){
        return "Mace";
    }
}
