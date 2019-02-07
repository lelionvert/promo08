using System.Collections.Generic;

namespace CalculateRegistration
{
    public interface ICoversCalculator
    {
        Serving GetCoversByDiet(Meal meal);
    }
}