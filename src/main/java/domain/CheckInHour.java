package domain;

public class CheckInHour {
    private static final String REGEX_FOR_HOUR = "h";

    private final int hour;

    public CheckInHour(String hour) {
        String[] splitHour = hour.split(REGEX_FOR_HOUR);
        this.hour = Integer.parseInt(splitHour[0]);
    }

    boolean isAfter(int givenHour) {
        return hour >= givenHour;
    }

}
