using System;
using System.Text;
using System.Collections.Generic;
using CalculateRegistration;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;

namespace CalculateRegistrationTest
{
    
    [TestClass]
    public class GenerateDietReportTest
    {
        private readonly DateTime _coldMealLimitDate = new DateTime(2019, 10, 24, 21, 0, 0);
        private readonly DateTime _endLimitDate = new DateTime(2019, 10, 27, 11, 00, 00);

        private readonly StayPeriod _fullStayPeriod = StayPeriod.Builder
            .WithCheckIn(new DateTime(2019, 10, 24, 19, 00, 00))
            .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
            .Build();

        [TestMethod]
        public void Given_one_vegan_participant_for_full_conference_should_return_full_diet_report_with_one_vegan_on_each_meal()
        {
            Mock<ICoversCalculator> covers = new Mock<ICoversCalculator>();
            Dictionary<Diet, int> coversResult = new Dictionary<Diet, int>
            {
                {Diet.Vegan, 1},
                {Diet.Vegetarian, 0 },
                {Diet.Pescatarian, 0 },
                {Diet.Omnivore, 0 }
            };
            
            List<Serving> meals = new List<Serving>
            {
                new Serving(new Meal(new DateTime(2019, 10, 24), MealType.Dinner, mustBePresentBefore: _coldMealLimitDate), coversResult),
                new Serving(new Meal(new DateTime(2019, 10, 25), MealType.Lunch), coversResult),
                new Serving(new Meal(new DateTime(2019, 10, 25), MealType.Dinner), coversResult),
                new Serving(new Meal(new DateTime(2019, 10, 26), MealType.Lunch), coversResult),
                new Serving(new Meal(new DateTime(2019, 10, 26), MealType.Dinner), coversResult),
                new Serving(new Meal(new DateTime(2019, 10, 27), MealType.Lunch, mustBePresentAfter: _endLimitDate), coversResult)            };
            covers.Setup(x => x.GetCoversByDiet(It.IsAny<Meal>(), It.IsAny<List<Participant>>())).
                Returns<Meal, List<Participant>>((meal, list)=>new Serving(meal,coversResult));
            DietReport dietReportExpected = new DietReport(meals);
            Email email = new Email("test@lacombe.fr");
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, _fullStayPeriod, email, Diet.Vegan)
            };
            Socrates socrates = new Socrates(participants, _coldMealLimitDate, _endLimitDate, covers.Object);

            Assert.AreEqual(dietReportExpected, socrates.GenerateDietReport());
            covers.Verify(x => x.GetCoversByDiet(It.IsAny<Meal>(), It.IsAny<List<Participant>>()));
        }

    }
}
