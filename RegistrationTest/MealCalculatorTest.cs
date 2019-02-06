using System;
using System.Text;
using System.Collections.Generic;
using CalculateRegistration;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CalculateRegistrationTest
{
 
    [TestClass]
    public class MealCalculatorTest
    {
        private Price _mealPrice;
        private DateTime _firstMealDateLimit;
        private DateTime _lastMealDateLimit;

        [TestInitialize]
        public void Initialize()
        {
            _mealPrice = Price.ValueOf(40);
            _firstMealDateLimit = new DateTime(2019, 10, 24, 21, 00, 00);
            _lastMealDateLimit = new DateTime(2019, 10, 27, 11, 00, 00);
            
        }

        [TestMethod]
        public void MealPriceForFullPeriod()
        {
            var mealCalculator=new MealCalculator(_mealPrice, _firstMealDateLimit, _lastMealDateLimit);
            DateTime checkIn=new DateTime(2019,10,24,19,00,00);
            DateTime checkOut= new DateTime(2019, 10, 27, 14, 00, 00);
            StayPeriod period=StayPeriod.Builder.WithCheckIn(checkIn).WithCheckOut(checkOut).Build();
            Assert.AreEqual(Price.ValueOf(240),mealCalculator.CalculateMealPrice(period));
        }

        [TestMethod]
        public void MealPriceForFullPeriodMinusFirstMeal()
        {
            var mealCalculator = new MealCalculator(_mealPrice, _firstMealDateLimit, _lastMealDateLimit);
            DateTime checkIn = new DateTime(2019, 10, 24, 21, 00, 00);
            DateTime checkOut = new DateTime(2019, 10, 27, 14, 00, 00);
            StayPeriod period = StayPeriod.Builder.WithCheckIn(checkIn).WithCheckOut(checkOut).Build();
            Assert.AreEqual(Price.ValueOf(200), mealCalculator.CalculateMealPrice(period));
        }

        [TestMethod]
        public void MealPriceForFullPeriodMinusLastMeal()
        {
            var mealCalculator = new MealCalculator(_mealPrice, _firstMealDateLimit, _lastMealDateLimit);
            DateTime checkIn = new DateTime(2019, 10, 24, 19, 00, 00);
            DateTime checkOut = new DateTime(2019, 10, 27, 11, 00, 00);
            StayPeriod period = StayPeriod.Builder.WithCheckIn(checkIn).WithCheckOut(checkOut).Build();
            Assert.AreEqual(Price.ValueOf(200), mealCalculator.CalculateMealPrice(period));
        }

        [TestMethod]
        public void MealPriceForFullPeriodMinusTwoMeals()
        {
            var mealCalculator = new MealCalculator(_mealPrice, _firstMealDateLimit, _lastMealDateLimit);
            DateTime checkIn = new DateTime(2019, 10, 24, 21, 00, 00);
            DateTime checkOut = new DateTime(2019, 10, 27, 11, 00, 00);
            StayPeriod period = StayPeriod.Builder.WithCheckIn(checkIn).WithCheckOut(checkOut).Build();
            Assert.AreEqual(Price.ValueOf(160), mealCalculator.CalculateMealPrice(period));
        }
    }
}
