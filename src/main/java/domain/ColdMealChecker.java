package domain;

public class ColdMealChecker {
    public static Integer counter(CheckIn checkIn) {
        if (checkIn.isColdMeal())
            return 1;
        return 0;
    }
}
