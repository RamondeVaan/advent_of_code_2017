package nl.ramondevaan.adventofcode.day18;

import java.util.Collection;
import java.util.Collections;

public class ProperState extends State {
  private final StateIdentifier id;
  private final MessageQueue messageQueue;
  private boolean waiting;
  private int messageCount;

  public ProperState(StateIdentifier id, MessageQueue messageQueue) {
    this(id, messageQueue, Collections.emptyList());
  }

  public ProperState(StateIdentifier id, MessageQueue messageQueue, Collection<Register> registers) {
    super(registers);
    this.id = id;
    this.messageQueue = messageQueue;
    this.waiting = false;
    this.messageCount = 0;
  }

  @Override
  public void snd(long val) {
    messageQueue.broadcast(this.id, val);
    messageCount++;
  }

  @Override
  public boolean rcv(Value value) {
    if (messageQueue.hasMessageForState(this.id)) {
      if (!value.hasIdentifier()) {
        throw new IllegalArgumentException("Receive action without identifier.");
      }

      Register r = new Register(value.getIdentifier(), messageQueue.readMessage(this.id));

      setRegister(r);
      this.waiting = false;
    } else {
      this.waiting = true;
    }

    return !this.waiting;
  }

  public boolean isBlocking() {
    return waiting && !messageQueue.hasMessageForState(this.id);
  }

  public int getMessageCount() {
    return messageCount;
  }
}
