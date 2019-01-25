using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace RegistrationCalculatePrice
{
    [TestClass]
    public class RegistrationTest
    {
        private const int RoomValue = 610;
        private const int MealValue = 40;

        [TestMethod]
        public void given_room_price_and_full_conference_should_return_room_price()
        {
            var stayPeriodStub = new StayPeriodStub(0);
            var roomValue = 610;
            var registration = new Registration(Price.ValueOf(roomValue), Price.ValueOf(40), stayPeriodStub);
            var registrationPrice = registration.CalculatePrice();
            var price = Price.ValueOf(roomValue);
            Assert.AreEqual(price, registrationPrice);
        }

        [TestMethod]
        public void given_room_price_and_conference_with_1_missed_meal_should_return_room_price_minus_meal_price()
        {
            var roomPrice = Price.ValueOf(RoomValue);
            var mealPrice = Price.ValueOf(MealValue);
            var stayPeriod = new StayPeriodStub(1);
            var registration = new Registration(roomPrice, mealPrice, stayPeriod);
            var registrationPrice = registration.CalculatePrice();
            var price = Price.ValueOf(RoomValue - MealValue);
            Assert.AreEqual(price, registrationPrice);
        }

        [TestMethod]
        public void given_room_price_and_conference_with_2_missed_meal_should_return_room_price_minus_meal_price()
        {
            var roomPrice = Price.ValueOf(RoomValue);
            var mealPrice = Price.ValueOf(MealValue);
            var countMissedMeal = 2;
            var stayPeriod = new StayPeriodStub(countMissedMeal);
            var registration = new Registration(roomPrice, mealPrice, stayPeriod);
            var registrationPrice = registration.CalculatePrice();
            var price = Price.ValueOf(RoomValue - MealValue * countMissedMeal);
            Assert.AreEqual(price, registrationPrice);
        }



    }
}
