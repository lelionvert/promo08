using System;
using System.Collections.Generic;
using System.Linq;

namespace CalculateRegistration
{
   public class CoversCalculator: ICoversCalculator
    {
        public Serving GetCoversByDiet(Meal meal, List<Participant> participants)
        {
            Dictionary<Diet, int> covers = new Dictionary<Diet, int>();
            foreach (Diet diet in Enum.GetValues(typeof(Diet)))
                covers.Add(diet, NumberOfCoverByDietByMeal(diet, meal, participants));

            if (meal.DateMustBePresentBefore.HasValue)
            {
                var coldMealReport = ColdMealReporting(meal.DateMustBePresentBefore.Value, participants);
                return new Serving(meal, covers, coldMealReport.Count);
            }

            return new Serving(meal, covers);

        }

        public int NumberOfCoverByDietByMeal(Diet diet, Meal meal, List<Participant> participants)
        {
            return participants.
                Count(participant => participant.HasDiet(diet) && meal.IsPresent(participant));
        }

        public ColdMealReport ColdMealReporting(DateTime coldMealLimitDate, List<Participant> participants)
        {
            var dayAfterColdMealLimitDay = coldMealLimitDate.AddDays(1).Date;
            var emails = participants.Where(participant => !participant.IsPresent(coldMealLimitDate) &&
                                                           participant.IsPresent(dayAfterColdMealLimitDay))
                .Select(participant => participant.Email).ToList();
            return new ColdMealReport(emails);
        }
    }
}
