using System;

namespace RegistrationCalculatePrice.Sources
{
    internal class CheckingDate
    {
        private readonly DayOfWeek _day;
        private readonly int _hour;

        public CheckingDate(DayOfWeek day, int hour)
        {
            _day = day;
            _hour = hour;
        }

        public bool IsAfter(DayOfWeek otherDay, int otherHour)
        {
            return _day.CompareTo(otherDay) > 0 || _hour >= otherHour;
        }

    }
}