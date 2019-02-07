package fr.lcdlv.promo8.socrates.domain;

import java.util.ArrayList;
import java.util.List;

public class ParticipantsRepository implements ParticipantProvider {

    private final List<Participant> participants;

    ParticipantsRepository(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Participant> getParticipants() {
        return new ArrayList<>(participants);
    }

}
