using System;
using System.Text;
using System.Collections.Generic;
using CalculateRegistration;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;

namespace CalculateRegistrationTest
{
    [TestClass]
    public class BillingServiceTest
    {
        private Mock<ICatalog> _roomCatalogMoq;
        private Mock<IMealCalculator> _mealMoq;
        private readonly StayPeriod fullStayPeriod = StayPeriod.Builder
            .WithCheckIn(new DateTime(2019, 10, 24, 19, 00, 00))
            .WithCheckOut(new DateTime(2019, 10, 27, 14, 00, 00))
            .Build();

        [TestInitialize]
        public void Initialize()
        {
            _roomCatalogMoq = new Mock<ICatalog>();
            _mealMoq = new Mock<IMealCalculator>();
        }

        [TestMethod]
        public void ReturnPriceOfSingleRoom()
        {
            
           _roomCatalogMoq.Setup(x => 
               x.GetRoomPrice(RoomChoice.Single)).Returns(Price.ValueOf(370));
           _mealMoq.Setup(x => x.CalculateMealPrice(fullStayPeriod)).Returns(Price.ValueOf(0));
             var billingService = new BillingService(_roomCatalogMoq.Object, _mealMoq.Object);
            var singleRoomPrice=Price.ValueOf(370);
            var participant=new Participant(RoomChoice.Single,fullStayPeriod, new Email("test@lacombe.fr"));
            Assert.AreEqual(singleRoomPrice, billingService.CalculatePrice(participant));
        }

        [TestMethod]
        public void ReturnPriceOfDoubleRoom()
        {
            _roomCatalogMoq.Setup(x =>
                x.GetRoomPrice(RoomChoice.Double)).Returns(Price.ValueOf(270));
            _mealMoq.Setup(x => x.CalculateMealPrice(fullStayPeriod)).Returns(Price.ValueOf(0));
            var billingService = new BillingService(_roomCatalogMoq.Object, _mealMoq.Object);
            var doubleRoomPrice = Price.ValueOf(270);
            var participant = new Participant(RoomChoice.Double, fullStayPeriod, new Email("test@lacombe.fr"));
            Assert.AreEqual(doubleRoomPrice, billingService.CalculatePrice(participant));
        }

        [TestMethod]
        public void ReturnPriceOfTripleRoom()
        {

            _roomCatalogMoq.Setup(x =>
                x.GetRoomPrice(RoomChoice.Triple)).Returns(Price.ValueOf(170));
            _mealMoq.Setup(x => x.CalculateMealPrice(fullStayPeriod)).Returns(Price.ValueOf(0));
            var billingService = new BillingService(_roomCatalogMoq.Object, _mealMoq.Object);
            var tripleRoomPrice = Price.ValueOf(170);
            var participant = new Participant(RoomChoice.Triple, fullStayPeriod, new Email("test@lacombe.fr"));
            Assert.AreEqual(tripleRoomPrice, billingService.CalculatePrice(participant));
        }

        [TestMethod]
        public void ReturnPriceOfSingleRoomWithMeals()
        {
            //Arrange
            
            _roomCatalogMoq.Setup(x =>
                x.GetRoomPrice(RoomChoice.Single)).Returns(Price.ValueOf(370));
            _mealMoq.Setup(x =>x.CalculateMealPrice(fullStayPeriod)
            ).Returns(Price.ValueOf(240));
            Participant participant=new Participant(RoomChoice.Single, fullStayPeriod, new Email("test@lacombe.fr"));
            BillingService billingService=new BillingService(_roomCatalogMoq.Object, _mealMoq.Object);

            //Act 
            var calculatePrice = billingService.CalculatePrice(participant);

            //Assert
            Assert.AreEqual(Price.ValueOf(610), calculatePrice);
        }
    }
}
