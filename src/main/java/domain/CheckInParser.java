package domain;

import java.time.DayOfWeek;

public class CheckInParser {

    private static final String REGEX = " ";

    public static CheckIn parse(String checkInStringToParse) {
        DayOfWeek checkInDay;
        CheckInHour checkInHour;

        if (!checkInStringToParse.isEmpty()) {
            String[] splittedCheckInInput = checkInStringToParse.split(REGEX);
            checkInDay = DayOfWeek.valueOf(splittedCheckInInput[0].toUpperCase());
            checkInHour = new CheckInHour(splittedCheckInInput[1]);
        } else {
            checkInDay = null;
            checkInHour = null;
        }

        return new CheckIn(checkInDay, checkInHour);
    }
}
