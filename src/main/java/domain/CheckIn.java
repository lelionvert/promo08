package domain;

import java.time.DayOfWeek;
import java.util.Objects;

public class CheckIn {
    private final DayOfWeek day;
    private final String hour;
    private static final String REGEX_FOR_HOUR = "h";

    public CheckIn(DayOfWeek day, String hour) {
        this.day = day;
        this.hour = hour;
    }

    public boolean isPlanned(int earlyArriving, int lateArriving, DayOfWeek dayOfArriving) {
        String[] splittedHour = hour.split(REGEX_FOR_HOUR);
        int realHour = Integer.parseInt(splittedHour[0]);
        return day.equals(dayOfArriving) && earlyArriving <= realHour && realHour <= lateArriving;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckIn checkIn = (CheckIn) o;
        return day == checkIn.day &&
                Objects.equals(hour, checkIn.hour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, hour);
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "day='" + day + '\'' +
                ", hour='" + hour + '\'' +
                '}';
    }
}
