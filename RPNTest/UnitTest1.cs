using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using RPN;

namespace RPNTest
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void Given_a_simple_value_should_return_the_value()
        {
            string resultExpected="1";
            Assert.AreEqual(resultExpected,Rpn.Calculate("1"));
        }
    }
}
