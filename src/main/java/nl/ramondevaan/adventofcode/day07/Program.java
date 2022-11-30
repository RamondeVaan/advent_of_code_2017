package nl.ramondevaan.adventofcode.day07;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.*;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(exclude = {"parent", "children", "siblings"})
public class Program {
  private final String name;
  private final int weight;
  @Setter(value = AccessLevel.PRIVATE)
  private int totalWeight;
  @Setter(value = AccessLevel.PRIVATE)
  private Program parent;
  private List<Program> children;
  private List<Program> siblings;

  private Program(String name, int weight) {
    this.parent = null;
    this.children = Collections.emptyList();
    this.siblings = Collections.emptyList();
    this.name = name;
    this.weight = weight;
    this.totalWeight = -1;
  }

  private void setChildren(List<Program> programs) {
    this.children = programs == null ?
        Collections.emptyList() :
        Collections.unmodifiableList(new ArrayList<>(programs));
  }

  private void setSiblings(List<Program> programs) {
    this.siblings = programs == null ?
        Collections.emptyList() :
        Collections.unmodifiableList(new ArrayList<>(programs));
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("name", getName())
        .append("weight", getWeight())
        .append("totalWeight", getTotalWeight())
        .append("children", getChildren())
        .toString();
  }

  @Data
  public static class ProgramInitializer {
    private final String name;
    private final int weight;
    private final List<String> children;
  }

  public static class Builder {
    private final List<ProgramInitializer> programs;

    public Builder() {
      programs = new ArrayList<>();
    }

    public Builder addProgram(ProgramInitializer program) {
      programs.add(program);
      return this;
    }

    public Builder addAll(Collection<ProgramInitializer> programs) {
      this.programs.addAll(programs);
      return this;
    }

    public List<ProgramInitializer> getPrograms() {
      return programs;
    }

    public Collection<Program> build() {
      Map<String, Program> programMap = new HashMap<>();

      programs.stream()
          .map(p -> new Program(p.getName(), p.getWeight()))
          .forEach(p -> programMap.put(p.getName(), p));

      programs.forEach(p -> {
        Program q = programMap.get(p.getName());

        List<Program> children = p.getChildren().stream()
            .map(programMap::get)
            .collect(Collectors.toList());

        q.setChildren(children);
        children.forEach(r -> r.setParent(q));
        children.forEach(r -> {
          List<Program> siblings = new ArrayList<>(children);
          siblings.remove(r);
          r.setSiblings(siblings);
        });
      });

      programMap.values().stream()
          .filter(p -> p.getChildren().size() == 0)
          .forEach(p -> p.setTotalWeight(p.getWeight()));

      while (programMap.values().stream().anyMatch(p -> p.getTotalWeight() < 0)) {
        programMap.values().stream()
            .filter(p -> p.getTotalWeight() < 0)
            .filter(p -> p.getChildren().stream()
                .allMatch(q -> q.getTotalWeight() >= 0))
            .forEach(p -> p.setTotalWeight(
                    p.getWeight() +
                        p.getChildren().stream()
                            .mapToInt(Program::getTotalWeight)
                            .sum()
                )
            );
      }

      return programMap.values();
    }
  }
}
