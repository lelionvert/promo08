package domain;

import java.time.DayOfWeek;

public class CheckIn {
    private final DayOfWeek day;
    private final String hour;

    public CheckIn(DayOfWeek day, String hour) {
        this.day = day;
        this.hour = hour;
    }

    public boolean isPlanned(int earlyarriving, int lateArriving, DayOfWeek dayOfArriving) {
        String[] splittedHour = hour.split("h");
        int realHour = Integer.parseInt(splittedHour[0]);
        return day.equals(dayOfArriving) && earlyarriving <= realHour && realHour <= lateArriving;
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "day='" + day + '\'' +
                ", hour='" + hour + '\'' +
                '}';
    }
}
