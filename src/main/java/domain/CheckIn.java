package domain;

import java.time.DayOfWeek;
import java.util.Objects;
import java.util.Optional;

public class CheckIn {
    private final DayOfWeek mayBeDay;
    private final CheckInHour mayBeHour;
    private static final String REGEX = " ";

    public CheckIn(String checkInInput) {
        if (!checkInInput.isEmpty()) {
            String[] splittedCheckInInput = checkInInput.split(REGEX);
            this.mayBeDay = DayOfWeek.valueOf(splittedCheckInInput[0].toUpperCase());
            this.mayBeHour = new CheckInHour(splittedCheckInInput[1]);
        } else {
            this.mayBeDay = null;
            this.mayBeHour = null;
        }
    }


    public boolean isPlanned(int earlyArriving, int lateArriving, DayOfWeek dayOfArriving) {
        return Optional.ofNullable(mayBeDay).filter(day -> day.equals(dayOfArriving)).isPresent() &&

                Optional.ofNullable(mayBeHour).filter(hour -> hour.isAfter(earlyArriving)).
                        filter(hour -> hour.isBeforeOrLastHour(lateArriving)).isPresent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckIn checkIn = (CheckIn) o;
        return mayBeDay == checkIn.mayBeDay &&
                Objects.equals(mayBeHour, checkIn.mayBeHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mayBeDay, mayBeHour);
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "mayBeDay='" + mayBeDay + '\'' +
                ", mayBeHour='" + mayBeHour + '\'' +
                '}';
    }
}
