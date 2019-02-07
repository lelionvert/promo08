using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CalculateRegistration
{
   public class CoversCalculator: ICoversCalculator

    {
        public Serving GetCoversByDiet(Meal meal, List<Participant> participants)
        {
            Dictionary<Diet, int> covers = new Dictionary<Diet, int>();
            foreach (Diet diet in Enum.GetValues(typeof(Diet)))
            {
                covers.Add(diet, NumberOfCoverByDietByMeal(diet, meal, participants));
            }
            return new Serving(meal, covers);
        }

        public int NumberOfCoverByDietByMeal(Diet diet, Meal meal, List<Participant> list)
        {
            return list.
                Count(participant => participant.HasDiet(diet) && meal.IsPresent(participant));
        }
    }
}
