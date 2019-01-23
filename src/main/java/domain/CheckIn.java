package domain;

public class CheckIn {
    private final String day;
    private final String hour;

    public CheckIn(String day, String hour) {
        this.day = day;
        this.hour = hour;
    }

    public boolean isColdMeal() {
        String[] splittedHour = hour.split("h");
        int realHour = Integer.parseInt(splittedHour[0]);
        return day.equals("Thursday") && 21 <= realHour && realHour <= 23;
    }
}
