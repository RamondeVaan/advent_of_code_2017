package nl.ramondevaan.adventofcode.day21;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ArrayGrid implements Grid {

  private final int size;
  private final boolean[][] grid;

  @Override
  public boolean get(final int row, final int column) {
    return grid[row][column];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void forEach(final GridValueConsumer consumer) {
    for (int row = 0; row < size; row++) {
      for (int column = 0; column < size; column++) {
        consumer.accept(row, column, grid[row][column]);
      }
    }
  }

  public static Builder builder(final int size) {
    return new Builder(size);
  }

  public static class Builder {
    private final int size;
    private boolean[][] grid;

    public Builder(final int size) {
      this.size = size;
      this.grid = new boolean[size][size];
    }

    public Builder set(int row, int column, boolean value) {
      grid[row][column] = value;
      return this;
    }

    public ArrayGrid build() {
      final var ret = new ArrayGrid(size, grid);
      this.grid = null;
      return ret;
    }
  }
}
