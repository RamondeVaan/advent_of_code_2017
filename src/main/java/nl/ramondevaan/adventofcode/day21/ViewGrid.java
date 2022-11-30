package nl.ramondevaan.adventofcode.day21;

public class ViewGrid implements Grid {

  private final Grid grid;
  private final int size;
  private final int rowOffset;
  private final int columnOffset;

  public ViewGrid(Grid grid, int size, int rowOffset, int columnOffset) {
    if (rowOffset + size > grid.size()) {
      throw new IllegalArgumentException();
    }
    if (columnOffset + size > grid.size()) {
      throw new IllegalArgumentException();
    }

    this.grid = grid;
    this.size = size;
    this.rowOffset = rowOffset;
    this.columnOffset = columnOffset;
  }

  @Override
  public boolean get(final int row, final int column) {
    return grid.get(row + rowOffset, column + columnOffset);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void forEach(final GridValueConsumer consumer) {
    int maxRow = rowOffset + size;
    int maxColumn = columnOffset + size;
    for (int row = rowOffset; row < maxRow; row++) {
      for (int column = columnOffset; column < maxColumn; column++) {
        consumer.accept(row, column, grid.get(row, column));
      }
    }
  }


}
