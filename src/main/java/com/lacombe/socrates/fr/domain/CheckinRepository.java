package com.lacombe.socrates.fr.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckinRepository {

    private List<Checkin> checkins;

    public CheckinRepository(Checkin... checkins) {
        this.checkins = new ArrayList(Arrays.asList(checkins));
    }

    public int getColdMeals() {
        return checkins.size();
    }
}
