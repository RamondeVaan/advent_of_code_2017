package nl.ramondevaan.adventofcode.day13;

import java.util.List;

@FunctionalInterface
public interface PacketMover {
  Packet move(Packet packet, List<Layer> layers);
}
