using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using static SocratesRegistration.Accommodation;

namespace SocratesRegistration
{
    [TestClass]
    public class RegistrationTest
    {
        [TestMethod]
        public void TestMethod1()
        {
            Accommodation accommodation=new Accommodation(Choice.NoAccommodation);
            Assert.AreEqual(accommodation.Price,240);
        }
    }
}
