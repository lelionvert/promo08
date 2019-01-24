import java.util.List;

public class MealCounter {
    public static long count(List<CheckIn> checkIns) {
        return checkIns.stream()
                .filter(CheckIn::isAfter21OnStartDay)
                .count();
    }
}
