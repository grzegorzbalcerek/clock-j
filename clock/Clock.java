package clock;

import nz.sodium.*;

public class Clock {

    private static final int SEC_MAX = 60;
    private static final int MIN_MAX = 60;
    private static final int HOUR_MAX = 24;

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

        Stream<Unit> minTimeTicks = secTimeTicks
                .gate(secCounter.map(sec -> sec == SEC_MAX - 1));
        Cell<Integer> minCounter = minTimeTicks
                .accum(0, (tick, min) -> (min + 1) % MIN_MAX);
        Cell<String> minString = minCounter.map(min -> String.format("%02d", min));
        min = minString;

        Stream<Unit> hourTimeTicks = minTimeTicks
                .gate(minCounter.map(min -> min == MIN_MAX - 1));
        Cell<Integer> hourCounter = hourTimeTicks
                .accum(0, (tick, hour) -> (hour + 1) % HOUR_MAX);
        Cell<String> hourString = hourCounter.map(hour -> String.format("%02d", hour));
        hour = hourString;

    }
}
