package clock;

import nz.sodium.*;

public class Clock {

    public final Cell<String> hour;
    public final Cell<String> min;
    public final Cell<String> sec;
    public final Cell<String> separator;

    public Clock(Stream<Unit> timeTicks, Stream<Unit> switchTicks, Stream<Unit> addTicks) {

        Cell<String> empty = new Cell<>("");

        separator = empty;

        sec = empty;

        min = empty;

        hour = empty;

    }
}
