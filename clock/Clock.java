package clock;

import nz.sodium.*;

public class Clock {

    private static final int SEC_MAX = 60;

    public final Cell<String> hour;
    public final Cell<String> min;
    public final Cell<String> sec;
    public final Cell<String> separator;

    public Clock(Stream<Unit> timeTicks, Stream<Unit> switchTicks, Stream<Unit> addTicks) {

        Cell<String> empty = new Cell<>("");

        Cell<Boolean> ticTac = timeTicks.accum(true, (tick, bool) -> !bool);
        Cell<String> blinkingColon = ticTac.map(bool -> bool ? "" : ":");
        separator = blinkingColon;

        Stream<Unit> secTimeTicks = timeTicks.gate(ticTac);
        Cell<Integer> secCounter = secTimeTicks
                .accum(0, (tick, sec) -> (sec + 1) % SEC_MAX);
        Cell<String> secString = secCounter.map(sec -> String.format("%02d", sec));
        sec = secString;

        min = empty;

        hour = empty;

    }
}
