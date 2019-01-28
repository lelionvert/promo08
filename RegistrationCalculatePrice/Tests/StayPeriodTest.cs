using System;
using System.ComponentModel.Design;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using RegistrationCalculatePrice.Sources;

namespace RegistrationCalculatePrice.Tests
{
    [TestClass]
    public class StayPeriodTest
    {
        [TestMethod]
        public void given_complete_stay_should_return_0_missed_meal()
        {
            var stayPeriod = new StayPeriod(new CheckingDate(DayOfWeek.Thursday, 20));
            var nbMissedMeals = stayPeriod.CountMissedMeals();
            Assert.AreEqual(0,nbMissedMeals);
        }

        [TestMethod]
        public void given_check_in_day_after_thursday_should_return_one_missed_meal()
        {
            var stayPeriod = new StayPeriod(new CheckingDate(DayOfWeek.Friday, 20));
            var nbMissedMeals = stayPeriod.CountMissedMeals();
            Assert.AreEqual(1, nbMissedMeals);
        }

        [TestMethod]
        public void given_check_in_on_thursday_after_21_should_return_one_missed_meal()
        {
            var stayPeriod = new StayPeriod(new CheckingDate(DayOfWeek.Thursday, 21));
            var nbMissedMeals = stayPeriod.CountMissedMeals();
            Assert.AreEqual(1, nbMissedMeals);
        }
    }
}
