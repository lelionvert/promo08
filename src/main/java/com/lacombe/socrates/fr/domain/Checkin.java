package com.lacombe.socrates.fr.domain;

import java.time.LocalDateTime;

public class Checkin {
    private final LocalDateTime arrivalDate;


    public Checkin(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
