using System;
using System.Runtime.CompilerServices;
using FsCheck;
using NUnit.Framework;
using RPN;

namespace RPNTest
{
    
    public class RpnTest
    {
        [Test]
        public void Given_a_simple_value_should_return_the_value()
        {
            string resultExpected="1";
            NUnit.Framework.Assert.AreEqual(resultExpected,Rpn.Calculate("1"));
        }

        [Test]
        public void Given_a_empty_value_should_return_the_value()
        {
            string resultExpected = string.Empty;
            Assert.AreEqual(resultExpected, Rpn.Calculate(string.Empty));
        }

        [Test]
        public void Given_an_expression_with_no_operator_should_return_the_same()
        {
            string resultExpected="1 2";
            Assert.AreEqual(resultExpected,Rpn.Calculate("1 2"));
        }

        [Test]

        public void Given_an_expression_with_plus_operator_should_return_the_sum()
        {
            string resultExpected="3";
            Assert.AreEqual(resultExpected,Rpn.Calculate("1 2 +"));
        }

        [Test]
        public void Given_an_expression_with_minus_operator_should_return_the_substraction()
        {
            string resultExpected="1";
            Assert.AreEqual(resultExpected,Rpn.Calculate("2 1 -"));
        }

        [Test]
        public void Given_an_random_expression_with_plus_operator_should_return_the_sum([Random(10)]int x, [Random(10)]int y)
        {
            
            string resultExpected = $"{x+y}";
            Assert.AreEqual(resultExpected, Rpn.Calculate($"{x} {y} +"));
        }

        [Test]
        public void Given_an_random_expression_with_minus_operator_should_return_the_substraction([Random(10)]int x, [Random(10)]int y)
        {

            string resultExpected = $"{x - y}";
            Assert.AreEqual(resultExpected, Rpn.Calculate($"{x} {y} -"));
        }

    }
}
