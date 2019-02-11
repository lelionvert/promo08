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
        public void Given_a_empty_value_should_return_the_value()
        {
            string resultExpected = string.Empty;
            Assert.AreEqual(resultExpected, Rpn.Calculate(string.Empty));
        }

        [TestMethod]
        public void Given_an_expression_with_no_operator_should_return_the_same()
        {
            string resultExpected="1 2";
            Assert.AreEqual(resultExpected,Rpn.Calculate("1 2"));
        }

        [TestMethod]
        public void Given_an_expression_with_plus_operator_should_return_the_sum()
        {
            string resultExpected="3";
            Assert.AreEqual(resultExpected,Rpn.Calculate("1 2 +"));
        }

      
    }
}
