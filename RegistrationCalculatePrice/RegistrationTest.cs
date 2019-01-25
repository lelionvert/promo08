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
            var registration = new Registration(Price.ValueOf(610));
            var registrationPrice = registration.CalculatePrice();
            var price = Price.ValueOf(610);
            Assert.AreEqual(price, registrationPrice);
        }
    }
}
