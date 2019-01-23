package domain;

public class CheckIn {
    private final String day;
    private final String hour;

    public CheckIn(String day, String hour) {
        this.day = day;
        this.hour = hour;
    }

    public boolean isColdMeal() {
        if (hour.equals("22:00"))
            return true;
        return false;
    }
}
