package game.cell;

public class EmptyCell extends Cell {
    public EmptyCell(int position) {
        super(position);
    }

    @Override
    public String getDisplaySymbol() {
        return ".";
    }

    @Override
    public String getCellType() {
        return "Empty";
    }
}
