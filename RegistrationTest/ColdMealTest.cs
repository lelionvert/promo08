using System;
using System.Text;
using System.Collections.Generic;
using CalculateRegistration;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CalculateRegistrationTest
{

    [TestClass]
    public class ColdMealTest
    {

        private readonly DateTime _coldMealLimitDate = new DateTime(2019, 10, 24, 21, 0, 0);
        private readonly DateTime _endDateTime = new DateTime(2019, 10, 27, 14, 0, 0);
        private Diet diet = Diet.Vegan;

        [TestMethod]
        public void Given_one_participant_with_cold_meal_should_return_report_for_one_cold_meal()
        {
            
            StayPeriod coldMealStayPeriod = StayPeriod.Builder
                .WithCheckIn(new DateTime(2019, 10, 24, 22, 00, 00))
                .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
                .Build();
            Email email = new Email("test@lacombe.fr");
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, coldMealStayPeriod, email, diet)
            };
            Socrates socrates = new Socrates(participants, _coldMealLimitDate, new DateTime(2019, 10, 27, 11, 00, 00), new CoversCalculator());
            List<Email> emails = new List<Email> {email};
            ColdMealReport coldMealReport=new ColdMealReport(emails);
            Assert.AreEqual(coldMealReport, socrates.GenerateColdMealReport());
        }

        [TestMethod]
        public void Given_participant_with_cold_meal_should_return_report_for_one_cold_meal()
        {
            
            StayPeriod coldMealStayPeriod = StayPeriod.Builder
                .WithCheckIn(new DateTime(2019, 10, 24, 22, 00, 00))
                .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
                .Build();
            StayPeriod hotMealStayPeriod = StayPeriod.Builder
                .WithCheckIn(new DateTime(2019, 10, 24, 19, 00, 00))
                .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
                .Build();
            StayPeriod noMealStayPeriod = StayPeriod.Builder
                .WithCheckIn(new DateTime(2019, 10, 25, 10, 00, 00))
                .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
                .Build();
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, coldMealStayPeriod, new Email("coldMeal@lacombe.fr"), diet),
                new Participant(RoomChoice.Single, hotMealStayPeriod, new Email("hotMeal@lacombe.fr"), diet),
                new Participant(RoomChoice.Single, coldMealStayPeriod, new Email("coldMeal2@lacombe.fr"), diet),
                new Participant(RoomChoice.Single, noMealStayPeriod, new Email("noMeal@lacombe.fr"), diet)
            };
            Socrates socrates = new Socrates(participants, _coldMealLimitDate, new DateTime(2019, 10, 27, 11, 00, 00), new CoversCalculator());
            List<Email> emails = new List<Email>
            {
                new Email("coldMeal@lacombe.fr"),
                new Email("coldMeal2@lacombe.fr")
            };
            ColdMealReport coldMealReport=new ColdMealReport(emails);
            Assert.AreEqual(coldMealReport, socrates.GenerateColdMealReport());
        }

        [TestMethod]
        public void Given_one_participant_with_cold_meal_should_return_one()
        {
            int numberOfMealsExpected = 1;
            StayPeriod coldMealStayPeriod = StayPeriod.Builder
                .WithCheckIn(new DateTime(2019, 10, 24, 22, 00, 00))
                .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
                .Build();
            Email email = new Email("test@lacombe.fr");
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, coldMealStayPeriod, email, diet)
            };
            Socrates socrates = new Socrates(participants, _coldMealLimitDate, new DateTime(2019, 10, 27, 11, 00, 00), new CoversCalculator());
            Assert.AreEqual(numberOfMealsExpected, socrates.GenerateColdMealReport().Emails.Count);
        }

        [TestMethod]
        public void Given_two_participant_with_cold_meal_should_return_two()
        {
            int numberOfMealsExpected = 2;
            StayPeriod coldMealStayPeriod = StayPeriod.Builder
                .WithCheckIn(new DateTime(2019, 10, 24, 22, 00, 00))
                .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
                .Build();
            Email email = new Email("test@lacombe.fr");
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, coldMealStayPeriod, email, diet),
                new Participant(RoomChoice.Single, coldMealStayPeriod, email, diet)
            };
            Socrates socrates = new Socrates(participants, _coldMealLimitDate, new DateTime(2019, 10, 27, 11, 00, 00), new CoversCalculator());
            Assert.AreEqual(numberOfMealsExpected, socrates.GenerateColdMealReport().Emails.Count);
        }

        [TestMethod]
        public void Given_two_participant_with_one_cold_meal_should_return_one()
        {
            int numberOfMealsExpected = 1;
            StayPeriod coldMealStayPeriod = StayPeriod.Builder
                .WithCheckIn(new DateTime(2019, 10, 24, 22, 00, 00))
                .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
                .Build();
            StayPeriod hotMealStayPeriod = StayPeriod.Builder
                .WithCheckIn(new DateTime(2019, 10, 24, 19, 00, 00))
                .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
                .Build();
            Email email = new Email("test@lacombe.fr");
            List<Participant> participants = new List<Participant>
            {
                new Participant(RoomChoice.Single, coldMealStayPeriod, email, diet),
                new Participant(RoomChoice.Single, hotMealStayPeriod, email, diet)
            };
            Socrates socrates = new Socrates(participants, _coldMealLimitDate, new DateTime(2019, 10, 27, 11, 00, 00), new CoversCalculator());
            Assert.AreEqual(numberOfMealsExpected, socrates.GenerateColdMealReport().Emails.Count);
        }
    }
}
