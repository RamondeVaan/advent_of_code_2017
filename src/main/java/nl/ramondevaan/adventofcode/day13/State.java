package nl.ramondevaan.adventofcode.day13;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class State {
    private final Packet        packet;
    private final List<Layer>   layers;
    private final List<Scanner> scanners;
    private final int           iteration;

    public State next(PacketMover mover) {
        return new State(
                mover.move(this.packet, this.layers),
                this.layers,
                this.scanners,
                getIteration() + 1
        );
    }

    public State next(ScannerMover mover) {
        return new State(
                this.packet,
                this.layers,
                Collections.unmodifiableList(
                    this.scanners
                            .stream()
                            .map(s -> mover.move(this, s))
                            .collect(Collectors.toList())
                ),
                getIteration() + 1
        );
    }

    public State next(PacketMover packetMover, ScannerMover scannerMover) {
        return new State(
                packetMover.move(this.packet, this.layers),
                this.layers,
                Collections.unmodifiableList(
                        this.scanners
                                .stream()
                                .map(s -> scannerMover.move(this, s))
                                .collect(Collectors.toList())
                ),
                getIteration() + 1
        );
    }

    public static State create(Packet packet, List<Layer> layers, List<Scanner> scanners) {
        return new State(
            packet,
            layers == null ?
                    Collections.emptyList() :
                    Collections.unmodifiableList(new ArrayList<>(layers)),
            scanners == null ?
                    Collections.emptyList() :
                    Collections.unmodifiableList(new ArrayList<>(scanners)),
            0
        );

    }
}
