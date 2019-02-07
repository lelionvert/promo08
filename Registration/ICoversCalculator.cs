using System;
using System.Collections.Generic;

namespace CalculateRegistration
{
    public interface ICoversCalculator
    {
        Serving GetCoversByDiet(Meal meal, List<Participant> participants);
        int NumberOfCoverByDietByMeal(Diet diet, Meal meal, List<Participant> participants);
        ColdMealReport ColdMealReporting(DateTime coldMealLimitDate, List<Participant> participants);
    }
}