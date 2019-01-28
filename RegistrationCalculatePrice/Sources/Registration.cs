using System;

namespace RegistrationCalculatePrice
{
    internal class Registration
    {
        private readonly Price _roomPrice;
        private readonly Price _mealPrice;
        private readonly IStayPeriod _stayPeriod;

        public Registration(Price roomPrice, Price mealPrice, IStayPeriod stayPeriod)
        {
            _roomPrice = roomPrice;
            _mealPrice = mealPrice;
            _stayPeriod = stayPeriod;
        }

        public Price CalculatePrice()
        {
            return _roomPrice.Minus(_mealPrice.MultiplyBy(_stayPeriod.CountMissedMeals(DayOfWeek.Thursday, 21)));
        }
    }
}