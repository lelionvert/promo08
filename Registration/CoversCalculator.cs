using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CalculateRegistration
{
   public class CoversCalculator: ICoversCalculator

    {
        public Serving GetCoversByDiet(Meal meal)
        {
            Dictionary<Diet, int> covers = new Dictionary<Diet, int>
            {
                {Diet.Vegan, 1},
                {Diet.Vegetarian, 0 },
                {Diet.Pescatarian, 0 },
                {Diet.Omnivore, 0 }
            };
            return new Serving(meal, covers);
        }
    }
}
