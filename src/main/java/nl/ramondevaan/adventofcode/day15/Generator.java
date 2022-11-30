package nl.ramondevaan.adventofcode.day15;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.Iterator;

@Data
@AllArgsConstructor
public class Generator implements Iterator<Long> {
  private final static long DIVISOR = 2147483647;

  private final Identifier id;
  private final long factor;
  private final long criterion;

  @Setter(value = AccessLevel.NONE)
  private long value;

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public Long next() {
    return value = (value * factor) % DIVISOR;
  }

  public Long nextByCriterion() {
    long val;

    do {
      val = next();
    } while (val % criterion != 0);

    return val;
  }
}
