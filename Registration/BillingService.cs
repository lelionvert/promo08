using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CalculateRegistration
{
    internal class BillingService
    {
        private readonly ICatalog _catalog;
        private readonly IMealCalculator _mealCalculator;

        public BillingService(ICatalog catalog, IMealCalculator mealCalculator)
        {
            _catalog = catalog;
            _mealCalculator = mealCalculator;
        }

        public Price CalculatePrice(Participant participant)
        {
            var roomPrice = _catalog.GetRoomPrice(participant.Choice);
            var mealPrice = _mealCalculator.CalculateMealPrice(participant.Period);
            return roomPrice + mealPrice;
        }
    }
}
