package nl.ramondevaan.adventofcode.day9;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Group extends Stream {
    private final Group        parent;
    private final List<Stream> children;

    public Group(Group parent, List<Stream> children) {
        this.parent = parent;
        this.children = children == null ?
                        Collections.emptyList() :
                        Collections.unmodifiableList(new ArrayList<>(children));
    }
}
