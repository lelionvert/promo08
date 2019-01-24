using System;

namespace SocratesRegistration
{
    public class Stay
    {
        private readonly DayOfWeek _day;
        private readonly int _hour;

        public Stay(DayOfWeek day, int hour)
        {
            this._day = day;
            this._hour = hour;
        }

        public bool IsAfter(int hour)
        {
            return _hour > hour;
        }
    }
}