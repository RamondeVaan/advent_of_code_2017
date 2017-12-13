package nl.ramondevaan.adventofcode.day13;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day13 {
    private final static String  LAYER_TAG       = "layer";
    private final static String  DEPTH_TAG       = "depth";
    private final static String  SCANNER_REGEX   =
            "(?<" + LAYER_TAG + ">\\d+): (?<" + DEPTH_TAG + ">\\d+)";
    private final static Pattern SCANNER_PATTERN = Pattern.compile(SCANNER_REGEX);

    private final List<Layer> layers;

    private Day13(List<Layer> layers) {
        this.layers = layers == null ?
                Collections.emptyList() :
                Collections.unmodifiableList(layers);
    }

    public int exercise1() {
        int maxDepth = layers
                .stream()
                .mapToInt(Layer::getDepth)
                .max()
                .orElseThrow(() -> new NoSuchElementException("No layers were provided"));

        List<Layer> allLayers = IntStream
                .rangeClosed(0, maxDepth)
                .mapToObj(d -> new ImmutablePair<>(new LayerIdentifier(d), d))
                .filter(p -> layers.stream().noneMatch(l -> l.getId().equals(p.getLeft())))
                .map(p -> new Layer(
                            p.getLeft(),
                            p.getRight(),
                            0
                ))
                .collect(Collectors.toList());
        allLayers.addAll(layers);
        allLayers.sort(Comparator.comparingInt(
                l -> l.getId().getId())
        );

        List<Scanner> scanners = layers
                .stream()
                .map(l -> new Scanner(l, 0))
                .collect(Collectors.toList());

        Packet packet = new Packet(allLayers.get(0));

        PacketMover packetMover = (p, l) -> new Packet(l.get(l.indexOf(p.getLocation()) + 1));
        ScannerMover scannerMover = s -> new Scanner(
                s.getLocation(),
                (s.getPosition() + 1) % s.getLocation().getRange()
        );

        List<State> states = new ArrayList<>();
        states.add(State.create(packet, allLayers, scanners));
        states.add(states.get(states.size() - 1).next(scannerMover));

        for(int i = 0; i < maxDepth; i++) {
            states.add(states.get(states.size() - 1).next(packetMover));
            states.add(states.get(states.size() - 1).next(scannerMover));
        }

        SeverityCalculator severityCalculator = (s) -> IntStream
                .iterate(0, i -> i + 2)
                .limit(states.size() / 2)
                .mapToObj(states::get)
                .mapToInt(k -> k.getScanners()
                                .stream()
                                .filter(c -> c.getLocation()
                                              .getId()
                                              .equals(k.getPacket().getLocation().getId())
                                )
                                .filter(c -> c.getPosition() == 0)
                                .mapToInt(c -> c.getLocation().getDepth() * c.getLocation().getRange())
                                .sum()
                )
                .peek(System.out::println)
                .sum();

        return severityCalculator.compute(states);
    }

    public int exercise2() {
        return 0;
    }

    public static Day13 create(Path file) throws IOException {
        return new Day13(
                Files.lines(file)
                     .map(SCANNER_PATTERN::matcher)
                     .filter(Matcher::matches)
                     .map(m -> new ImmutablePair<>(
                             Integer.parseInt(m.group(LAYER_TAG)),
                             Integer.parseInt(m.group(DEPTH_TAG))
                     ))
                     .map(p -> new Layer(
                                  new LayerIdentifier(p.getLeft()),
                                  p.getLeft(),
                                  p.getRight()
                          )
                     )
                     .collect(Collectors.toList())
        );
    }

    public static Day13 create(List<Layer> input) {
        return new Day13(new ArrayList<>(input));
    }
}
