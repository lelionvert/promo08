using System;
using System.Collections.Generic;
using CalculateRegistration;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CalculateRegistrationTest
{
    [TestClass]
    public class CoversCalculatorTest
    {

        private readonly StayPeriod _fullStayPeriod = StayPeriod.Builder
            .WithCheckIn(new DateTime(2019, 10, 24, 19, 00, 00))
            .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
            .Build();

        private readonly Email _email = new Email("test@lacombe.net");

        [TestMethod]
        public void Given_one_meal_with_one_vegan_cover_should_return_report_with_one_meal_one_vegan_cover()
        {
            Dictionary<Diet, int> covers = new Dictionary<Diet, int>
            {
                {Diet.Vegan, 1},
                {Diet.Vegetarian, 0 },
                {Diet.Pescatarian, 0 },
                {Diet.Omnivore, 0 }
            };
            Meal meal=new Meal(new DateTime(2019, 10, 25), MealType.Lunch);
            Serving servingExpected=new Serving(meal,covers);
            ICoversCalculator calculator=new CoversCalculator();
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, _fullStayPeriod, _email, Diet.Vegan)
            };
            Assert.AreEqual(servingExpected, calculator.GetCoversByDiet(meal, participants));
        }

        [TestMethod]
        public void Given_one_meal_with_one_vegan_cover_should_return_report_with_one_meal_one_vegan_cover2()
        {
            Dictionary<Diet, int> covers = new Dictionary<Diet, int>
            {
                {Diet.Vegan, 2},
                {Diet.Vegetarian, 0 },
                {Diet.Pescatarian, 0 },
                {Diet.Omnivore, 0 }
            };
            Meal meal=new Meal(new DateTime(2019, 10, 25), MealType.Lunch);
            Serving servingExpected=new Serving(meal,covers);
            ICoversCalculator calculator=new CoversCalculator();
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, _fullStayPeriod, _email, Diet.Vegan),
                new Participant(RoomChoice.Single, _fullStayPeriod, _email, Diet.Vegan)
            };
            Assert.AreEqual(servingExpected, calculator.GetCoversByDiet(meal, participants));
        }
    }
}
