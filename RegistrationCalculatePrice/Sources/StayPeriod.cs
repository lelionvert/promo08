using System;
using RegistrationCalculatePrice.Tests;

namespace RegistrationCalculatePrice.Sources
{
    internal class StayPeriod : IStayPeriod
    {
        private readonly CheckingDate _checkingDate;
        private readonly CheckingDate _checkOut;

        public StayPeriod(CheckingDate checkIn, CheckingDate checkOut)
        {
            _checkingDate = checkIn;
            _checkOut = checkOut;
        }

        public int CountMissedMeals(DayOfWeek dayOfWeek, int hour)
        {
            if (!_checkOut.IsAfter(DayOfWeek.Sunday, 11))
                return 1;
            if (_checkingDate.IsAfter(dayOfWeek, hour))
                return 1;
            return 0;
        }
    }
}