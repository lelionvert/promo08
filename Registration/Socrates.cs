using System;
using System.Collections.Generic;
using System.Linq;

namespace CalculateRegistration
{
    public class Socrates
    {
        private readonly List<Participant> _participants;
        private readonly DateTime _coldMealLimitDate;
        private readonly DateTime _endLimitDate;
        private readonly ICoversCalculator _coversCalculator;
        private readonly List<Meal> _meals = new List<Meal>();

        public Socrates(List<Participant> participants, DateTime coldMealLimitDate, DateTime endLimitDate,
            ICoversCalculator coversCalculator)
        {
            _participants = participants;
            _coldMealLimitDate = coldMealLimitDate;
            _endLimitDate = endLimitDate;
            _coversCalculator = coversCalculator;
            InitializeMeals();
        }

        private void InitializeMeals()
        {
            DateTime beginDay = _coldMealLimitDate.Date;
            DateTime endDay = _endLimitDate.Date;
            _meals.Add(new Meal(beginDay, MealType.Dinner, dateMustBePresentBefore:_coldMealLimitDate));
            for (DateTime day = beginDay.AddDays(1); day < endDay; day = day.AddDays(1))
            {
                    _meals.Add(new Meal(day, MealType.Lunch));
                    _meals.Add(new Meal(day, MealType.Dinner));
            }
            _meals.Add(new Meal(endDay, MealType.Lunch, mustBePresentAfter:_endLimitDate));
        }

        public ColdMealReport GenerateColdMealReport()
        {
            return _coversCalculator.ColdMealReporting(_coldMealLimitDate, _participants);
        }

        public DietReport GenerateDietReport()
        {
            return new DietReport(_meals.Select(meal => 
                _coversCalculator.GetCoversByDiet(meal, _participants)).ToList());
        }
    }
}