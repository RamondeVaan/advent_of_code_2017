package nl.ramondevaan.adventofcode.day18;

import java.util.*;

public class MessageQueue {
    private Map<StateIdentifier, Queue<Long>> messages;

    public MessageQueue(Collection<StateIdentifier> network) {
        messages = new HashMap<>();
        for(StateIdentifier s : network) {
            messages.put(s, new LinkedList<>());
        }
    }

    public void broadcast(StateIdentifier sender, long value) {
        messages.entrySet().stream()
                .filter(e -> !e.getKey().equals(sender))
                .forEach(e -> e.getValue().offer(value));
    }

    public boolean hasMessageForState(StateIdentifier s) {
        return messages.get(s).size() > 0;
    }

    public long readMessage(StateIdentifier s) {
        return messages.get(s).poll();
    }
}
