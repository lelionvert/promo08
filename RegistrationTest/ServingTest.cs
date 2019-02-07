using System;
using System.Collections.Generic;
using CalculateRegistration;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CalculateRegistrationTest
{
    [TestClass]
    public class ServingTest
    {
        private readonly StayPeriod _lateArrivalStayPeriod = StayPeriod.Builder
            .WithCheckIn(new DateTime(2019, 10, 24, 21, 00, 00))
            .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
            .Build();

        [TestMethod]
        public void Given_one_late_arrival_participant_should_return_one_cold_meal()
        {
            Meal meal=new Meal(new DateTime(2019,10,24),MealType.Dinner,dateMustBePresentBefore:new DateTime(2019,10,24,21,00,00) );
      
            Email email = new Email("test@lacombe.fr");
            ICoversCalculator coversCalculator=new CoversCalculator();
            List<Participant> participants=new List<Participant>
            {
                new Participant(RoomChoice.Single, _lateArrivalStayPeriod, email, Diet.Vegan)
            };
            Serving serving=coversCalculator.GetCoversByDiet(meal,participants);
            Assert.AreEqual(1,serving.NumberOfColdMeals);
        }

        [TestMethod]
        public void Given_two_late_arrival_participant_should_return_two_cold_meal()
        {
            Meal meal = new Meal(new DateTime(2019, 10, 24), MealType.Dinner, dateMustBePresentBefore: new DateTime(2019, 10, 24, 21, 00, 00));

            Email email = new Email("test@lacombe.fr");
            ICoversCalculator coversCalculator = new CoversCalculator();
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, _lateArrivalStayPeriod, email, Diet.Vegan),
                new Participant(RoomChoice.Single, _lateArrivalStayPeriod, email, Diet.Vegan)
            };
            Serving serving = coversCalculator.GetCoversByDiet(meal, participants);
            Assert.AreEqual(2, serving.NumberOfColdMeals);
        }
    }
}
