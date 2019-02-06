package com.lacombe.socrates.fr.domain;

import java.util.Objects;

public class Mail {


    private final String mail;

    public Mail(String mail) {

        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail1 = (Mail) o;
        return Objects.equals(mail, mail1.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }
}
