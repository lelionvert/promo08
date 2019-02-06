using System;

namespace CalculateRegistration
{
    internal class MealCalculator : IMealCalculator
    {
        private readonly Price _mealPrice;
        private const int MaxMeals = 6;
        private readonly DateTime _firstMealDateLimit;
        private readonly DateTime _lastMealDateLimit;

        public MealCalculator(Price mealPrice, DateTime firstMealDateLimit, DateTime lastMealDateLimit)
        {
            _mealPrice = mealPrice;
            _firstMealDateLimit = firstMealDateLimit;
            _lastMealDateLimit = lastMealDateLimit;
        }

        public Price CalculateMealPrice(StayPeriod period)
        {
            var numberOfMeals = MaxMeals;
            
            if (period.CheckOut <= _lastMealDateLimit)
                numberOfMeals--;

            if (period.CheckIn >= _firstMealDateLimit)
                numberOfMeals--;

            return _mealPrice.MultiplyPrice(numberOfMeals);
        }
    }
}