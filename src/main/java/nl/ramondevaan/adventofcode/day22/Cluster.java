package nl.ramondevaan.adventofcode.day22;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class Cluster {
  private final Set<Coordinate> infected;
  private final Coordinate carrierPosition;
  private final Direction carrierDirection;
}
