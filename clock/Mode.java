package clock;

public enum Mode {
    SHOW_TIME,
    SET_HOUR;
    public Mode next() {
        switch (this) {
            case SHOW_TIME: return SET_HOUR;
            default: return SHOW_TIME;
        }
    }
}
