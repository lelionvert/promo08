using System;

namespace RegistrationCalculatePrice
{
    internal interface IStayPeriod
    {
        int CountMissedMeals(DayOfWeek dayOfWeek, int hour);
    }
}