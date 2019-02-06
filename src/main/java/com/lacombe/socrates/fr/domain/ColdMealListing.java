package com.lacombe.socrates.fr.domain;

import java.util.List;
import java.util.Objects;

public class ColdMealListing {

    private final List<Mail> mails;
    private final int total;

    public ColdMealListing(List<Mail> mails) {

        this.mails = mails;
        this.total = mails.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColdMealListing that = (ColdMealListing) o;
        return total == that.total &&
                Objects.equals(mails, that.mails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mails, total);
    }

    @Override
    public String toString() {
        return "ColdMealListing{" +
                "mails=" + mails +
                ", total=" + total +
                '}';
    }
}
