package nl.ramondevaan.adventofcode.day12;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode(exclude = {"peers"})
public class Program {
    private final Identifier    id;
    private       List<Program> peers;

    public Program(Identifier id) {
        this.id = id;
        this.peers = Collections.emptyList();
    }

    public void setPeers(List<Program> peers) {
        this.peers = peers == null ?
                Collections.emptyList() :
                Collections.unmodifiableList(new ArrayList<>(peers));
    }

    @Data
    public static class ProgramInitializer {
        private final Identifier       identifier;
        private final List<Identifier> peers;
    }

    public static class Builder {
        private List<ProgramInitializer> initializers;

        public Builder() {
            this.initializers = new ArrayList<>();
        }

        public Builder addInitializer(ProgramInitializer initializer) {
            this.initializers.add(initializer);
            return this;
        }

        public Builder addAll(Collection<ProgramInitializer> initializers) {
            this.initializers.addAll(initializers);
            return this;
        }

        public Collection<Program> build() {
            Map<Identifier, Program> programs = initializers
                    .stream()
                    .flatMap(i -> {
                        List<Identifier> identifiers = new ArrayList<>();
                        identifiers.add(i.getIdentifier());
                        identifiers.addAll(i.getPeers());
                        return identifiers.stream();
                    })
                    .distinct()
                    .map(Program::new)
                    .collect(Collectors.toMap(Program::getId, p -> p));

            initializers.forEach(i -> {
                List<Program> peers = i
                        .getPeers()
                        .stream()
                        .map(programs::get)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

                Program p = programs.get(i.getIdentifier());

                if(p != null) {
                    p.setPeers(peers);
                }
            });

            return programs.values();
        }
    }
}
