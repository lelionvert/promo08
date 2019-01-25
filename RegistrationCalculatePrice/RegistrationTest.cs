using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace RegistrationCalculatePrice
{
    [TestClass]
    public class RegistrationTest
    {
        [TestMethod]
        public void given_room_price_and_full_conference_should_return_room_price()
        {
            var registration = new Registration(Price.ValueOf(610), Price.ValueOf(40));
            var registrationPrice = registration.CalculatePrice();
            var price = Price.ValueOf(610);
            Assert.AreEqual(price, registrationPrice);
        }

        [TestMethod]
        public void given_room_price_and_conference_with_1_missed_meal_should_return_room_price_minus_meal_price()
        {
            var roomPrice = Price.ValueOf(610);
            var stayPeriod = new StayPeriod();
            var mealPrice = Price.ValueOf(40);
            var registration = new Registration(roomPrice, mealPrice, stayPeriod);
            var registrationPrice = registration.CalculatePrice();
            var price = roomPrice.Minus(mealPrice);
            Assert.AreEqual(price, registrationPrice);
        }

    }
}
