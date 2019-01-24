package domain;

import java.time.DayOfWeek;

public class CheckInParser {

    private static final String REGEX = " ";

    public static CheckIn parse(String checkInStringToParse) {
        DayOfWeek checkInDay;
        CheckInHour checkInHour;

        if (!checkInStringToParse.isEmpty()) {
            String[] splitCheckInInput = checkInStringToParse.split(REGEX);
            checkInDay = DayOfWeek.valueOf(splitCheckInInput[0].toUpperCase());
            checkInHour = new CheckInHour(splitCheckInInput[1]);
        } else {
            checkInDay = null;
            checkInHour = null;
        }

        return new CheckIn(checkInDay, checkInHour);
    }
}
