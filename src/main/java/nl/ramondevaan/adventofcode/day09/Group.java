package nl.ramondevaan.adventofcode.day09;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Group extends Stream {
    private List<Stream> children;

    void setChildren(List<Stream> children) {
        this.children = children == null ?
                        Collections.emptyList() :
                        Collections.unmodifiableList(new ArrayList<>(children));
    }
}
