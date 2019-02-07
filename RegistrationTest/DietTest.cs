using System;
using System.Collections.Generic;
using CalculateRegistration;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CalculateRegistrationTest
{
    [TestClass]
    public class DietTest
    {
        private readonly DateTime _coldMealLimitDate = new DateTime(2019, 10, 24, 21, 0, 0);
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

        private readonly Email _email = new Email("test@lacombe.fr");


        [TestMethod]
        public void Given_one_vegan_participant_thursday_evening_should_return_one_cover()
        {
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, _fullStayPeriod, _email, Diet.Vegan)
            };
            Socrates socrates = new Socrates(participants, _coldMealLimitDate, new DateTime(2019, 10, 27, 11, 00, 00));
            int numberOfVegetarianCover = 1;
            Assert.AreEqual(numberOfVegetarianCover, socrates.NumberOfCoverByDietByMeal(Diet.Vegan, 
                new Meal(new DateTime(2019,10,24), MealType.Dinner)));
        }

        [TestMethod]
        public void Given_two_vegan_participants_thursday_evening_should_return_two_covers()
        {
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, _fullStayPeriod, _email, Diet.Vegan),
                new Participant(RoomChoice.Single, _fullStayPeriod, _email, Diet.Vegan)
            };
            Socrates socrates = new Socrates(participants, _coldMealLimitDate, new DateTime(2019, 10, 27, 11, 00, 00));
            int numberOfVegetarianCover = 2;
            Assert.AreEqual(numberOfVegetarianCover, socrates.NumberOfCoverByDietByMeal(Diet.Vegan, 
                new Meal(new DateTime(2019,10,24), MealType.Dinner)));
        }

        [TestMethod]
        public void Given_two_participants_with_one_vegan_thursday_evening_should_return_one_covers()
        {
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, _fullStayPeriod, _email, Diet.Vegan),
                new Participant(RoomChoice.Single, _fullStayPeriod, _email, Diet.Vegetarian)
            };
            Socrates socrates = new Socrates(participants, _coldMealLimitDate, new DateTime(2019, 10, 27, 11, 00, 00));
            int numberOfVegetarianCover = 1;
            Assert.AreEqual(numberOfVegetarianCover, socrates.NumberOfCoverByDietByMeal(Diet.Vegan, 
                new Meal(new DateTime(2019,10,24), MealType.Dinner)));
        }

        [TestMethod]
        public void Given_three_vegan_participants_with_one_late_arrival_thursday_evening_should_return_two_covers()
        {
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, _fullStayPeriod, _email, Diet.Vegan),
                new Participant(RoomChoice.Single, _fullStayPeriod, _email, Diet.Vegan),
                new Participant(RoomChoice.Single, _lateArrivalStayPeriod, _email, Diet.Vegan)
            };
            Socrates socrates = new Socrates(participants, _coldMealLimitDate, new DateTime(2019, 10, 27, 11, 00, 00));
            int numberOfVegetarianCover = 2;
            Assert.AreEqual(numberOfVegetarianCover, socrates.NumberOfCoverByDietByMeal(Diet.Vegan, 
                new Meal(new DateTime(2019,10,24), MealType.Dinner)));
        }

        [TestMethod]
        public void Given_three_vegan_participants_with_one_late_arrival_friday_morning_should_return_three_covers()
        {
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, _fullStayPeriod, _email, Diet.Vegan),
                new Participant(RoomChoice.Single, _fullStayPeriod, _email, Diet.Vegan),
                new Participant(RoomChoice.Single, _lateArrivalStayPeriod, _email, Diet.Vegan)
            };
            Socrates socrates = new Socrates(participants, _coldMealLimitDate, new DateTime(2019, 10, 27, 11, 00, 00));
            int numberOfVegetarianCover = 3;
            Assert.AreEqual(numberOfVegetarianCover, socrates.NumberOfCoverByDietByMeal(Diet.Vegan,
                new Meal(new DateTime(2019, 10, 25), MealType.Lunch)));
        }

        [TestMethod]
        public void Given_three_vegan_participants_with_one_early_departure_sunday_morning_should_return_two_covers()
        {
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, _earlyDepartureStayPeriod, _email, Diet.Vegan),
                new Participant(RoomChoice.Single, _fullStayPeriod, _email, Diet.Vegan),
                new Participant(RoomChoice.Single, _lateArrivalStayPeriod, _email, Diet.Vegan)
            };
            Socrates socrates = new Socrates(participants, _coldMealLimitDate, new DateTime(2019, 10, 27, 11, 00, 00));
            int numberOfVegetarianCover = 2;
            Assert.AreEqual(numberOfVegetarianCover, socrates.NumberOfCoverByDietByMeal(Diet.Vegan,
                new Meal(new DateTime(2019, 10, 27), MealType.Lunch)));
        }
    }
}
