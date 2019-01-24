using System;
using System.Security.AccessControl;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using static SocratesRegistration.Accommodation;

namespace SocratesRegistration
{
    [TestClass]
    public class RegistrationTest
    {
        [TestMethod]
        [DataRow(Choice.NoAccommodation, 240),
         DataRow(Choice.Double, 510), 
         DataRow(Choice.Single, 610), 
         DataRow(Choice.Triple, 410)]
        public void when_given_a_accommodation_then_should_return_complete_price(Choice choice, int price)
        {
            var checkIn = new Stay(DayOfWeek.Thursday, 19);
            var accommodation=new Accommodation(choice, checkIn);
            Assert.AreEqual(accommodation.ComputePrice(), Price.ValueOf(price));
        }

        [TestMethod]
        public void when_given_a_accommodation_without_one_meal_should_return_complete_price_minus_40()
        {
            var checkIn = new Stay(DayOfWeek.Thursday, 23);
            var accommodation = new Accommodation(Choice.NoAccommodation, checkIn);
            Assert.AreEqual(accommodation.ComputePrice(), Price.ValueOf(200));
        }
    }
}
