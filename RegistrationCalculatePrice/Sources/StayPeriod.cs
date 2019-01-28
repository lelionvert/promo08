using System;
using RegistrationCalculatePrice.Tests;

namespace RegistrationCalculatePrice.Sources
{
    internal class StayPeriod : IStayPeriod
    {
        private readonly CheckingDate _checkingDate;

        public StayPeriod(CheckingDate checkingDate)
        {
            _checkingDate = checkingDate;
        }

        public int CountMissedMeals()
        {
            if (_checkingDate.IsAfter(DayOfWeek.Thursday, 21))
                return 1;
            return 0;
        }
    }
}