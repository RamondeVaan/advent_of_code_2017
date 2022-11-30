package nl.ramondevaan.adventofcode.day24;

import lombok.Getter;

@Getter
public class Component {
  private final int id;
  private final int port1;
  private final int port2;
  private final int length;

  public Component(int id, int port1, int port2) {
    this.id = id;
    this.port1 = port1;
    this.port2 = port2;
    this.length = port1 + port2;
  }

  @Override
  public int hashCode() {
    return id;
  }
}
