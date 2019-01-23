package domain;

import java.util.List;

public class ColdMealChecker {
    public static Integer counter(List<CheckIn> checkIns) {
        int counter = 0;
        for (CheckIn checkIn : checkIns) {
            if (checkIn.isColdMeal())
                counter++;
        }
        return counter;
    }
}
