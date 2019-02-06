using System;
using System.Collections.Generic;
using System.Linq;

namespace CalculateRegistration
{
    internal class Socrates
    {
        private readonly List<Participant> _participants;
        private readonly DateTime _coldMealLimitDate;

        public Socrates(List<Participant> participants, DateTime coldMealLimitDate)
        {
            _participants = participants;
            _coldMealLimitDate = coldMealLimitDate;
        }

        public ColdMealReport GenerateColdMealReport()
        {
            var dayAfterColdMealLimitDay = _coldMealLimitDate.AddDays(1).Date;
            var emails = _participants.
                Where(participant => !participant.IsPresent(_coldMealLimitDate) &&
                                     participant.IsPresent(dayAfterColdMealLimitDay)).
                Select(participant => participant.Email).ToList();
            return new ColdMealReport(emails);
        }
    }
}