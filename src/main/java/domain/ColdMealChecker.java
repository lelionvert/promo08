package domain;

import java.time.DayOfWeek;
import java.util.List;

public class ColdMealChecker {


    public static int count(List<CheckIn> checkIns) {
        return Long.valueOf(checkIns.stream()
                .filter(checkIn -> checkIn.isPlanned(20, 23, DayOfWeek.THURSDAY))
                .count())
                .intValue();
    }
}
