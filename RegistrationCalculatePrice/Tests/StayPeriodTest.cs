using System;
using System.ComponentModel.Design;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using RegistrationCalculatePrice.Sources;

namespace RegistrationCalculatePrice.Tests
{
    [TestClass]
    public class StayPeriodTest
    {
        private const DayOfWeek LIMIT_DAY = DayOfWeek.Thursday;
        private const int LIMIT_HOUR = 21;

        [TestMethod]
        public void given_complete_stay_should_return_0_missed_meal()
        {
            var stayPeriod = new StayPeriod(new CheckingDate(LIMIT_DAY, 20), new CheckingDate(DayOfWeek.Sunday, 14));
            var nbMissedMeals = stayPeriod.CountMissedMeals(LIMIT_DAY, LIMIT_HOUR);
            Assert.AreEqual(0,nbMissedMeals);
        }

        [TestMethod]
        public void given_check_in_day_after_thursday_should_return_one_missed_meal()
        {
            var stayPeriod = new StayPeriod(new CheckingDate(DayOfWeek.Friday, 20), new CheckingDate(DayOfWeek.Sunday, 14));
            var nbMissedMeals = stayPeriod.CountMissedMeals(LIMIT_DAY, LIMIT_HOUR);
            Assert.AreEqual(1, nbMissedMeals);
        }

        [TestMethod]
        public void given_check_in_on_thursday_after_21_should_return_one_missed_meal()
        {
            var stayPeriod = new StayPeriod(new CheckingDate(LIMIT_DAY, LIMIT_HOUR), new CheckingDate(DayOfWeek.Sunday, 14));
            var nbMissedMeals = stayPeriod.CountMissedMeals(LIMIT_DAY, LIMIT_HOUR);
            Assert.AreEqual(1, nbMissedMeals);
        }

        [TestMethod]
        public void given_check_out_on_saturday_should_return_one_missed_meal()
        {
            var stayPeriod=new StayPeriod(new CheckingDate(LIMIT_DAY,19),
                new CheckingDate(DayOfWeek.Saturday, 18));
            var nbMissedMeals = stayPeriod.CountMissedMeals(LIMIT_DAY,LIMIT_HOUR);
            Assert.AreEqual(1,nbMissedMeals);
        }
    }
}
