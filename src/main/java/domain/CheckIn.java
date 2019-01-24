package domain;

import java.time.DayOfWeek;
import java.util.Objects;
import java.util.Optional;

public class CheckIn {
    private final DayOfWeek day;
    private final CheckInHour hour;

    public CheckIn(DayOfWeek day, CheckInHour hour) {
        this.day = day;
        this.hour = hour;
    }

    public boolean isPlanned(int earlyArriving, DayOfWeek dayOfArriving) {
        return Optional.ofNullable(day).filter(day -> day.equals(dayOfArriving)).isPresent() &&

                Optional.ofNullable(hour).filter(hour -> hour.isAfter(earlyArriving)).isPresent();
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
