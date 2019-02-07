package fr.lcdlv.promo8.socrates.domain;

import com.sun.javafx.collections.UnmodifiableListSet;

import java.util.List;
import java.util.Objects;

public class ColdMealChecker {
    private final List<String> emails;
    private final int counter;

    ColdMealChecker(List<String> emails) {
        this.emails = emails;
        this.counter = this.emails.size();
    }


    int count() {
        return counter;
    }

    UnmodifiableListSet<String> getEmails() {
        return new UnmodifiableListSet<>(emails);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColdMealChecker that = (ColdMealChecker) o;
        return counter == that.counter &&
                Objects.equals(emails, that.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emails, counter);
    }
}
