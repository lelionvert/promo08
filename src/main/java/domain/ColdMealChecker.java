package domain;

import java.time.DayOfWeek;
import java.util.List;

public class ColdMealChecker {
    private static final int EARLY_ARRIVING_HOUR = 21;

    public static int count(List<CheckIn> checkIns) {
        return Long.valueOf(checkIns.stream()
                .filter(checkIn -> checkIn.isOnRightTimeSlot(EARLY_ARRIVING_HOUR, DayOfWeek.THURSDAY))
                .count())
                .intValue();
    }
}
