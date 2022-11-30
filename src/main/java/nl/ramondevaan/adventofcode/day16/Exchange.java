package nl.ramondevaan.adventofcode.day16;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Exchange implements DanceMove {
  private final int position1;
  private final int position2;

  @Override
  public List<Program> execute(List<Program> programs) {
    ArrayList<Program> ret = new ArrayList<>(programs);
    Collections.swap(ret, position1, position2);

    return ret;
  }
}
