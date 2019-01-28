using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using RegistrationCalculatePrice.Sources;

namespace RegistrationCalculatePrice.Tests
{
    [TestClass]
    public class CheckingDateTest
    {
        [TestMethod]
        public void given_a_checkingDate_on_saturday_isAfter_sunday_should_be_false()
        {
            var checkingDateOnSaturday = new CheckingDate(DayOfWeek.Saturday, 11);
            Assert.IsFalse(checkingDateOnSaturday.IsAfter(DayOfWeek.Sunday, 11));
        }

        [TestMethod]
        public void given_a_checkingDate_on_friday_isAfter_sunday_should_be_false()
        {
            var checkingDateOnFriday = new CheckingDate(DayOfWeek.Friday, 15);
            Assert.IsFalse(checkingDateOnFriday.IsAfter(DayOfWeek.Sunday, 14));
        }

        [TestMethod]
        public void given_a_checkingDate_on_sunday_isAfter_saturday_should_be_true()
        {
            var checkingDateOnSunday = new CheckingDate(DayOfWeek.Sunday, 15);
            Assert.IsTrue(checkingDateOnSunday.IsAfter(DayOfWeek.Saturday, 16));
        }

        [TestMethod]
        public void toto()
        {
            var checkingDateOnSunday = new CheckingDate(DayOfWeek.Thursday, 21);
            Assert.IsFalse(checkingDateOnSunday.IsAfter(DayOfWeek.Friday, 16));
        }
    }
}
