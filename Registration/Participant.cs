
using System;

namespace CalculateRegistration
{
    internal class Participant
    {
        private readonly Diet _diet;
        public Email Email { get; }
        public StayPeriod Period { get; }
        public RoomChoice Choice { get; }

        public Participant(RoomChoice roomChoice, StayPeriod stayPeriod, Email email, Diet diet)
        {
            _diet = diet;
            Email = email;
            Period = stayPeriod;
            Choice = roomChoice;
        }

        public bool IsPresent(DateTime date)
        {
            return !Period.BeginAfter(date) && !Period.EndBefore(date);
        }

        public bool HasDiet(Diet diet)
        {
            return diet == _diet;
        }
    }
}