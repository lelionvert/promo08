package com.lacombe.socrates.fr.domain;

import java.util.List;

public class ParticipantRegisterInMemory implements ParticipantRegister {

    private final List<Participant> participants;

    public ParticipantRegisterInMemory(List<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public List<Participant> getAllParticipant() {
        return participants;
    }
}
