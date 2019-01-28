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
            var otherDayIsSunday = otherDay == DayOfWeek.Sunday;
            var currentDayIsSunday = _day == DayOfWeek.Sunday;
            if (currentDayIsSunday ^ otherDayIsSunday)
                return currentDayIsSunday;
            var dayComparison = _day.CompareTo(otherDay);
            if (dayComparison == 0)
                return _hour >= otherHour;
            return dayComparison > 0;
        }

    }
}