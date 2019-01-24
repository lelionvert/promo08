package domain;

import java.util.HashMap;
import java.util.Map;

public class Registration {
    private final static Map<String, Integer> ACCOMMODATION_MAP = new HashMap<>();

    private String accommodationChoice;

    static {
        ACCOMMODATION_MAP.put("Single", 610);
        ACCOMMODATION_MAP.put("Double", 510);
        ACCOMMODATION_MAP.put("Triple", 410);
        ACCOMMODATION_MAP.put("No Accommodation", 240);
    }


    public Registration(String accommodationChoice) {
        this.accommodationChoice = accommodationChoice;
    }

    public int calculatePrice() {
        return ACCOMMODATION_MAP.get(accommodationChoice);
    }

    @Override
    public String toString() {
        return "Registration{" +
                "ACCOMMODATION_MAP=" + ACCOMMODATION_MAP +
                ", accommodationChoice='" + accommodationChoice + '\'' +
                '}';
    }
}
