using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using CalculateRegistration;
using Moq;

namespace CalculateRegistrationTest
{
    [TestClass]
    public class CalculateRegistrationTest
    {
        private Price _singleRoomAndFullStayPrice;
        private Dictionary<RoomChoice, Price> _catalog;
        private Price _mealPrice;
        private DateTime _firstMealDateLimit;
        private DateTime _lastMealDateLimit;

        [TestInitialize]
        public void Initialize()
        {
            _mealPrice = Price.ValueOf(40);
            _firstMealDateLimit = new DateTime(2019, 10, 24, 21, 00, 00);
            _lastMealDateLimit = new DateTime(2019, 10, 27, 11, 00, 00);
            _singleRoomAndFullStayPrice = Price.ValueOf(610);
            _catalog = new Dictionary<RoomChoice, Price>
            {
                {RoomChoice.NoAccommodation, Price.ValueOf(0)},
                {RoomChoice.Single, Price.ValueOf(370)},
                {RoomChoice.Double, Price.ValueOf(270)},
                {RoomChoice.Triple, Price.ValueOf(170)}
            };

        }

        [TestMethod]
        public void Given_participant_with_single_room_and_full_stay_should_return_full_price()
        {
            var checkIn = new DateTime(2019, 10, 24, 19, 00, 00);
            var checkOut = new DateTime(2019, 10, 27, 14, 00, 00);
            var stayPeriod = StayPeriod.Builder.WithCheckIn(checkIn).WithCheckOut(checkOut).Build();
            var participant=new Participant(RoomChoice.Single, stayPeriod);
            var registration = new BillingService(new RoomCatalog(_catalog), new MealCalculator(_mealPrice, _firstMealDateLimit, _lastMealDateLimit));
            Assert.AreEqual(_singleRoomAndFullStayPrice,registration.CalculatePrice(participant));
        }

    }
}
