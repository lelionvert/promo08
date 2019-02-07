using System;
using System.Collections.Generic;
using CalculateRegistration;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CalculateRegistrationTest
{
    [TestClass]
    public class CoversCalculatorTest
    {
        [TestMethod]
        public void TestMethod1()
        {
            Dictionary<Diet, int> covers = new Dictionary<Diet, int>
            {
                {Diet.Vegan, 1},
                {Diet.Vegetarian, 0 },
                {Diet.Pescatarian, 0 },
                {Diet.Omnivore, 0 }
            };
            Meal meal=new Meal(new DateTime(2019, 10, 25), MealType.Lunch);
            Serving servingExpected=new Serving(meal,covers);
            ICoversCalculator calculator=new CoversCalculator();
            Assert.AreEqual(servingExpected, calculator.GetCoversByDiet(meal));
        }
    }
}
