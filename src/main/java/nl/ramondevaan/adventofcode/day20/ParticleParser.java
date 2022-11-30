package nl.ramondevaan.adventofcode.day20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParticleParser {
  private final static String POSITION_COORDINATES_TAG = "positioncoords";
  private final static String VELOCITY_COORDINATES_TAG = "velocitycoords";
  private final static String ACCELERATION_COORDINATES_TAG = "accelartioncoords";

  private final static String NUMBER_REGEX = "-?\\d+";
  private final static String COORDINATES_REGEX = NUMBER_REGEX + "(," + NUMBER_REGEX + ")*";
  private final static String POSITION_REGEX =
      "p=<(?<" + POSITION_COORDINATES_TAG + ">" + COORDINATES_REGEX + ")>";
  private final static String VELOCITY_REGEX =
      "v=<(?<" + VELOCITY_COORDINATES_TAG + ">" + COORDINATES_REGEX + ")>";
  private final static String ACCELERATION_REGEX =
      "a=<(?<" + ACCELERATION_COORDINATES_TAG + ">" + COORDINATES_REGEX + ")>";
  private final static String PARTICLE_REGEX = POSITION_REGEX + ", " + VELOCITY_REGEX + ", " + ACCELERATION_REGEX;

  private final static Pattern PARTICLE_PATTERN = Pattern.compile(PARTICLE_REGEX);

  public Collection<Particle> parse(Path file) throws IOException {
    return parse(Files.readAllLines(file).stream());
  }

  public Collection<Particle> parse(Stream<String> lines) {
    final AtomicInteger id = new AtomicInteger(0);

    return lines
        .map(PARTICLE_PATTERN::matcher)
        .filter(Matcher::matches)
        .map(m -> new Particle(
            new Identifier(id.getAndIncrement()),
            Vec3i.parse(m.group(POSITION_COORDINATES_TAG).split(",")),
            Vec3i.parse(m.group(VELOCITY_COORDINATES_TAG).split(",")),
            Vec3i.parse(m.group(ACCELERATION_COORDINATES_TAG).split(","))
        ))
        .collect(Collectors.toSet());
  }
}
