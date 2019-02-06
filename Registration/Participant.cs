
using System;

namespace CalculateRegistration
{
    internal class Participant
    {
        public StayPeriod Period { get; }
        public RoomChoice Choice { get; }

        public Participant(RoomChoice roomChoice, StayPeriod stayPeriod)
        {
            Period = stayPeriod;
            Choice = roomChoice;
        }

        public Participant(RoomChoice roomChoice):this(roomChoice, null)
        {
           
        }
    }
}