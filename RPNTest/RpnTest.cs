using System.Security.Policy;
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
            Assert.AreEqual(resultExpected,Rpn.Process("1"));
        }

        [Test]
        public void Given_a_empty_value_should_return_the_value()
        {
            string resultExpected = string.Empty;
            Assert.AreEqual(resultExpected, Rpn.Process(string.Empty));
        }

        [Test]
        [TestCase("Toto")]
        [TestCase("1 + 2")]
        [TestCase("toto + tata")]
        [TestCase("toto tata +")]
        public void Given_an_expression_with_no_operator_should_return_the_same(string input)
        {
            Assert.AreEqual(input, Rpn.Process(input));
        }

        [Test]

        public void Given_an_expression_with_plus_operator_should_return_the_sum()
        {
            string resultExpected="3";
            Assert.AreEqual(resultExpected,Rpn.Process("1 2 +"));
        }

        [Test]
        public void Given_an_expression_with_minus_operator_should_return_the_substraction()
        {
            string resultExpected="1";
            Assert.AreEqual(resultExpected,Rpn.Process("2 1 -"));
        }

        [Test]
        public void Given_an_random_expression_with_plus_operator_should_return_the_sum([Random(10)]int x, [Random(10)]int y)
        {
            string resultExpected = $"{x+y}";
            Assert.AreEqual(resultExpected, Rpn.Process($"{x} {y} +"));
        }

        [Test]
        public void CommutativityTestOfPlusOperator([Random(10)]int x, [Random(10)]int y)
        {
            string resultExpected = Rpn.Process($"{y} {x} +");
            Assert.AreEqual(resultExpected, Rpn.Process($"{x} {y} +"));
        }

        [Test]
        public void ElementZeroRightTestOfPlusOperator([Random(10)]int x)
        {
            string resultExpected = $"{x}";
            Assert.AreEqual(resultExpected, Rpn.Process($"{x} 0 +"));
        }

        [Test]
        public void ElementZeroLeftTestOfPlusOperator([Random(10)]int x)
        {
            string resultExpected = $"{x}";
            Assert.AreEqual(resultExpected, Rpn.Process($"0 {x} +"));
        }

        [Test]
        public void Given_an_random_expression_with_minus_operator_should_return_the_substraction([Range(-10,10)]int x, [Random(10)]int y)
        {
            string resultExpected = $"{x - y}";
            Assert.AreEqual(resultExpected, Rpn.Process($"{x} {y} -"));
        }

        [Test]
        public void ElementZeroRightTestOfMinusOperator([Range(-10, 10)]int x)
        {
            string resultExpected = $"{x}";
            Assert.AreEqual(resultExpected, Rpn.Process($"{x} 0 -"));
        }

        [Test]
        public void ElementZeroLeftTestOfMinusOperator([Range(-10, 10)]int x)
        {
            string resultExpected = $"{-x}";
            Assert.AreEqual(resultExpected, Rpn.Process($"0 {x} -"));
        }

        [Test]
        public void Given_an_expression_with_divide_operator_should_return_the_division_with_no_rest()
        {
            string resultExpected = "2";
            Assert.AreEqual(resultExpected, Rpn.Process("4 2 /"));
        }

        [Test]
        public void Given_an_expression_with_divide_operator_should_return_the_division_with_rest()
        {
            string resultExpected = "1";
            Assert.AreEqual(resultExpected, Rpn.Process("4 3 /"));
        }

        [Test]
        public void Given_an_expression_with_divide_operator_and_neutral_element_should_return_the_same([Random(10)]int x)
        {
            string resultExpected = $"{x}";
            Assert.AreEqual(resultExpected, Rpn.Process($"{x} 1 /"));
        }

        [Test]
        public void Given_an_expression_with_divide_operator_and_zero_should_return_input([Random(10)]int x)
        {
            string resultExpected = $"{x} 0 /";
            Assert.AreEqual(resultExpected, Rpn.Process($"{x} 0 /"));
        }

        [Test]
        public void Given_an_expression_with_two_operators_should_return_the_result_of_two_operations()
        {
            string resultExpected="105";
            Assert.AreEqual(resultExpected, Rpn.Process("120 10 5 + -"));
        }

        [Test]
        public void Given_an_expression_with_mixed_operators_should_return_the_result_of_many_operations()
        {
            string resultExpected="10";
            Assert.AreEqual(resultExpected, Rpn.Process("10 10 10 + + 20 -"));
        }

        [Test]
        public void Given_an_expression_with_mixed_operators_should_stop_on_invalid_operation()
        {
            string resultExpected="5 3 4";
            Assert.AreEqual(resultExpected, Rpn.Process("7 2 - 3 4"));
        }

        [Test]
        public void Given_an_expression_with_divided_operators_should_return_result_of_two_divisions()
        {
            string resultExpected="0";
            Assert.AreEqual(resultExpected, Rpn.Process("5 14 / 5 /"));
        }

        [Test]
        public void Given_an_expression_with_all_operators_should_return_result_of_all_operations()
        {
            string resultExpected = "100 0 /";
            Assert.AreEqual(resultExpected, Rpn.Process("100 40 5 35 + - /"));
        }

        [Test]
        public void Given_an_expression_with_divided_and_zero_operators_should_return_input()
        {
            string resultExpected = "50 100 0 / /";
            Assert.AreEqual(resultExpected, Rpn.Process("50 100 0 / /"));
        }

        [Test]
        public void Given_an_expression_with_minus_first_should_return_minus_first()
        {
            string resultExpected = "- 20";
            Assert.AreEqual(resultExpected, Rpn.Process("- 10 10 +"));
        }
    }
}
