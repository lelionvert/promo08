using System;
using System.Collections.Generic;
using System.Linq;

namespace CalculateRegistration
{
    internal class Socrates
    {
        private readonly List<Participant> _participants;
        private readonly DateTime _coldMealLimitDate;
        private readonly DateTime _endLimitDate;

        public Socrates(List<Participant> participants, DateTime coldMealLimitDate, DateTime endLimitDate)
        {
            _participants = participants;
            _coldMealLimitDate = coldMealLimitDate;
            _endLimitDate = endLimitDate;
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

        public int NumberOfCoverByDietByMeal(Diet diet, Meal meal)
        {
            return _participants.
                Count(participant => participant.HasDiet(diet) &&
                                     ParticipantIsPresentForFirstMeal(meal, participant) &&
                                     ParticipantIsPresentForLastMeal(meal, participant));
        }

        private bool ParticipantIsPresentForLastMeal(Meal meal, Participant participant)
        {
            return meal.Time.Date != _endLimitDate.Date || 
                   participant.IsPresent(_endLimitDate);
        }

        private bool ParticipantIsPresentForFirstMeal(Meal meal, Participant participant)
        {
            return meal.Time.Date != _coldMealLimitDate.Date ||
                   participant.IsPresent(_coldMealLimitDate);
        }

        public DietReport GenerateDietReport()
        {
            throw new NotImplementedException();
        }
    }
}