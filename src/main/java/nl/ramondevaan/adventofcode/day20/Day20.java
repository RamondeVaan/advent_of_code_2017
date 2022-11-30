package nl.ramondevaan.adventofcode.day20;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static java.util.stream.Collectors.*;

public class Day20 {
  private final static int ITERATIONS = 100;
  private final Collection<Particle> particles;

  private Day20(Collection<Particle> particles) {
    this.particles = particles == null ?
        Collections.emptyList() :
        Collections.unmodifiableCollection(particles);
  }

  public int closestParticleToZeroInTerm() {
    final Vec3i ZERO = new Vec3i(0, 0, 0);

    return particles
        .stream()
        .min(Comparator
            .comparingInt((Particle p) -> Vec3i.manhattanDistance(ZERO, p.acceleration))
            .thenComparingInt(p -> Vec3i.manhattanDistance(ZERO, p.velocity))
            .thenComparingInt(p -> Vec3i.manhattanDistance(ZERO, p.position)))
        .orElseThrow(() -> new IllegalArgumentException(
            "Could not find the closest particle to position <0,0,0>"
        ))
        .getId()
        .getId();
  }

  public int numberOfParticlesNotColliding() {
    var current = Set.copyOf(particles);

    for (int count = 0; count < ITERATIONS && !current.isEmpty(); count++) {
      current = current.stream()
          .map(this::next)
          .collect(collectingAndThen(
              groupingBy(p -> p.position),
              map -> map.values().stream()
                  .filter(k -> k.size() == 1)
                  .flatMap(List::stream)
                  .collect(toSet())
          ));
    }

    return current.size();
  }

  private Particle next(Particle particle) {
    final var velocity = new Vec3i(
        particle.velocity.x + particle.acceleration.x,
        particle.velocity.y + particle.acceleration.y,
        particle.velocity.z + particle.acceleration.z
    );
    final var position = new Vec3i(
        particle.position.x + velocity.x,
        particle.position.y + velocity.y,
        particle.position.z + velocity.z
    );
    return new Particle(particle.id, position, velocity, particle.acceleration);
  }

  public static Day20 create(Path file) throws IOException {
    return new Day20(new ParticleParser().parse(file));
  }

  public static Day20 create(Collection<Particle> particles) {
    return new Day20(new HashSet<>(particles));
  }


}
