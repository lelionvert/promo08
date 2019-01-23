public class CSVParser {

    private String time;

    public CSVParser(String time) {
        this.time = time;
    }

    public boolean isCold(){
        return Integer.parseInt(time.substring(time.length() - 3, time.length() - 1)) >= 21;
    }

    public CheckIn parseFromCSVToCheckIn(){
        String[] dayAndHour = time.split(" ");
        String day = dayAndHour[0];
        int hour = Integer.parseInt(dayAndHour[1].substring(0,2));
        CheckIn checkIn = new CheckIn(day, hour);
        return checkIn;
    }
}
