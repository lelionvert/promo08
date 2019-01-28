using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RegistrationCalculatePrice
{
    class StayPeriodStub : IStayPeriod
    {
        private readonly int _countMissedMeal;

        public StayPeriodStub(int countMissedMeal)
        {
            _countMissedMeal = countMissedMeal;
        }

        public int CountMissedMeals(DayOfWeek dayOfWeek, int hour)
        {
            return _countMissedMeal;
        }
    }
}
