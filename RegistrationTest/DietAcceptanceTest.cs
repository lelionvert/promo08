using System;
using System.Collections.Generic;
using CalculateRegistration;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CalculateRegistrationTest
{
    [TestClass]
    public class DietAcceptanceTest
    {
        private readonly DateTime _coldMealLimitDate = new DateTime(2019, 10, 24, 21, 0, 0);
        private readonly DateTime _endLimitDate = new DateTime(2019, 10, 27, 11, 00, 00);

        private readonly StayPeriod _fullStayPeriod = StayPeriod.Builder
            .WithCheckIn(new DateTime(2019, 10, 24, 19, 00, 00))
            .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
            .Build();
        private readonly StayPeriod _lateArrivalStayPeriod = StayPeriod.Builder
            .WithCheckIn(new DateTime(2019, 10, 25, 10, 00, 00))
            .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
            .Build();
        private readonly StayPeriod _earlyDepartureStayPeriod = StayPeriod.Builder
            .WithCheckIn(new DateTime(2019, 10, 24, 19, 00, 00))
            .WithCheckOut(new DateTime(2019, 10, 27, 11, 00, 00))
            .Build();
        private readonly StayPeriod _shortStayPeriod = StayPeriod.Builder
            .WithCheckIn(new DateTime(2019, 10, 24, 22, 00, 00))
            .WithCheckOut(new DateTime(2019, 10, 26, 18, 00, 00))
            .Build();


        [TestMethod]
        public void TestMethod1()
        {
            Email email = new Email("test@lacombe.fr");
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, _fullStayPeriod, email, Diet.Vegan),
                new Participant(RoomChoice.Single, _lateArrivalStayPeriod, email, Diet.Vegan),
                new Participant(RoomChoice.Single, _earlyDepartureStayPeriod, email, Diet.Vegan),
                new Participant(RoomChoice.Single, _shortStayPeriod, email, Diet.Vegan)
            };
            Socrates socrates = new Socrates(participants, _coldMealLimitDate, _endLimitDate, new CoversCalculator());
            Dictionary<Diet, int> covers = new Dictionary<Diet, int>
            {
                {Diet.Vegan, 4},
                {Diet.Vegetarian, 0 },
                {Diet.Pescatarian, 0 },
                {Diet.Omnivore, 0 }
            };
            Dictionary<Diet, int> coversWithoutMeals = new Dictionary<Diet, int>
            {
                {Diet.Vegan, 2},
                {Diet.Vegetarian, 0 },
                {Diet.Pescatarian, 0 },
                {Diet.Omnivore, 0 }
            };
            List<Serving> servings = new List<Serving>
            {
                new Serving(new Meal(new DateTime(2019, 10, 24), MealType.Dinner), coversWithoutMeals),
                new Serving(new Meal(new DateTime(2019, 10, 25), MealType.Lunch), covers),
                new Serving(new Meal(new DateTime(2019, 10, 25), MealType.Dinner), covers),
                new Serving(new Meal(new DateTime(2019, 10, 26), MealType.Lunch), covers),
                new Serving(new Meal(new DateTime(2019, 10, 26), MealType.Dinner), covers),
                new Serving(new Meal(new DateTime(2019, 10, 27), MealType.Lunch), coversWithoutMeals)
            };
            DietReport dietReport=new DietReport(servings);
            
            Assert.AreEqual(dietReport, socrates.GenerateDietReport());
        }
    }
}
