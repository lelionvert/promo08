package domain;

import java.time.DayOfWeek;
import java.util.Objects;

public class CheckIn {
    private final DayOfWeek day;
    private final CheckInHour hour;
    private static final String REGEX = " ";

    public CheckIn(String checkInInput) {
        String[] splittedCheckInInput = checkInInput.split(REGEX);
        this.day = DayOfWeek.valueOf(splittedCheckInInput[0].toUpperCase());
        this.hour = new CheckInHour(splittedCheckInInput[1]);
    }


    public boolean isPlanned(int earlyArriving, int lateArriving, DayOfWeek dayOfArriving) {
        return day.equals(dayOfArriving) && hour.isAfter(earlyArriving) && hour.isBeforeOrLastHour(lateArriving);
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
