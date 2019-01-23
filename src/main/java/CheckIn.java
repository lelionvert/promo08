public class CheckIn {

    String day;
    int hour;

    public CheckIn(String day, int hour) {
        this.day = day;
        this.hour = hour;
    }

    boolean isBefore(String day, int hour){
        if(this.day.equals(day) && this.hour <= hour)
            return true;
        return false;
    }


}
