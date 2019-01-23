package domain;

public class CheckInHour {
    private static final String REGEX_FOR_HOUR = "h";

    private final int hour;

    public CheckInHour(String hour) {
        String[] splittedHour = hour.split(REGEX_FOR_HOUR);
        this.hour = Integer.parseInt(splittedHour[0]);
    }

    public boolean isAfter(int givenHour) {
        return hour >= givenHour;
    }

    public boolean isBeforeOrLastHour(int givenHour) {
        return hour <= givenHour;
    }
}
