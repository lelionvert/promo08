using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using RPN;

namespace RPNTest
{
    [TestClass]
    public class RpnTest
    {
        [TestMethod]
        public void Given_a_simple_value_should_return_the_value()
        {
            string resultExpected="1";
            Assert.AreEqual(resultExpected,Rpn.Calculate("1"));
        }

        [TestMethod]
        public void Given_an_expression_with_no_operator_should_return_the_same()
        {
            string resultExpected="1 2";
            Assert.AreEqual(resultExpected,Rpn.Calculate("1 2"));
        }
    }
}
