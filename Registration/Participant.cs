
using System;

namespace CalculateRegistration
{
    internal class Participant
    {
        public Email Email { get; }
        public StayPeriod Period { get; }
        public RoomChoice Choice { get; }

        public Participant(RoomChoice roomChoice, StayPeriod stayPeriod, Email email)
        {
            Email = email;
            Period = stayPeriod;
            Choice = roomChoice;
        }

        public bool IsPresent(DateTime limitDate)
        {
            return !Period.BeginAfter(limitDate);
        }

    }
}