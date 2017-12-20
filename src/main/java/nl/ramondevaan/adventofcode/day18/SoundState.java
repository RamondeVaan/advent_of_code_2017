package nl.ramondevaan.adventofcode.day18;

public class SoundState extends State {
    private long    lastFrequency;
    private boolean recoverCalled;

    public SoundState() {
        lastFrequency = 0;
        recoverCalled = false;
    }

    public long getLastFrequency() {
        return lastFrequency;
    }

    public void snd(long frequency) {
        lastFrequency = frequency;
    }

    public boolean rcv(Value value) {
        long xVal = value.hasIdentifier() ?
                getRegister(value.getIdentifier()).getValue() :
                value.getValueSafe();

        if(xVal != 0) {
            recoverCalled = true;
        }
        return true;
    }

    public boolean isRecoverCalled() {
        return recoverCalled;
    }
}
