using System;
using System.Text;
using System.Collections.Generic;
using CalculateRegistration;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace CalculateRegistrationTest
{
    /// <summary>
    /// Summary description for DietReportTest
    /// </summary>
    [TestClass]
    public class DietReportTest
    {
        private readonly DateTime _coldMealLimitDate = new DateTime(2019, 10, 24, 21, 0, 0);
        private readonly DateTime _endLimitDate = new DateTime(2019, 10, 27, 11, 00, 00);

        [TestMethod]
        public void name()
        {
            //DietReport dietReportExpected;
            //Socrates socrates = new Socrates(participants, _coldMealLimitDate, _endLimitDate);
            //Assert.AreEqual(dietReportExpected, socrates.GenerateDietReport());
        }

    }
}
