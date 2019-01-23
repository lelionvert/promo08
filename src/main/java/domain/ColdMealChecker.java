package domain;

import java.time.DayOfWeek;
import java.util.List;

public class ColdMealChecker {
    private static final int EARLY_ARRIVING_HOUR = 21;
    private static final int LATE_ARRIVING_HOUR = 23;

    public static int count(List<CheckIn> checkIns) {
        return Long.valueOf(checkIns.stream()
                .filter(checkIn -> checkIn.isPlanned(EARLY_ARRIVING_HOUR, LATE_ARRIVING_HOUR, DayOfWeek.THURSDAY))
                .count())
                .intValue();
    }
}
