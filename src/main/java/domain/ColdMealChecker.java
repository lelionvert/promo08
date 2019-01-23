package domain;

import java.time.DayOfWeek;
import java.util.List;

public class ColdMealChecker {


    public static int count(List<CheckIn> checkIns) {
        int counter = 0;
        for (CheckIn checkIn : checkIns) {
            if (checkIn.isPlanned(20, 23, DayOfWeek.THURSDAY))
                counter++;
        }
        return counter;
    }
}
