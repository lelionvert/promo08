using System;

namespace CalculateRegistration
{
    internal class Meal
    {
        public DateTime Time { get; }
        private MealType _mealType;

        public Meal(DateTime dateTime, MealType mealType)
        {
            Time = dateTime;
            _mealType = mealType;
        }
    }

    public enum MealType
    {
        Dinner,
        Lunch
    }
}