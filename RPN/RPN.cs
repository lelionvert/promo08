using static System.Int32;
using static System.Text.RegularExpressions.Regex;

namespace RPN
{
    public class Rpn
    {
        private const char Separator = ' ';
        private const string Pattern = @"-?[0-9]+ -?[0-9]+ [+/-]";

        public static string Process(string input)
        {
            if (!IsMatch(input, Pattern))
                return input;

            var splitInput = input.Split(Separator);
            int firstOperand = Parse(splitInput[0]);
            int secondOperand = Parse(splitInput[1]);
            return Calculate(firstOperand, secondOperand, splitInput[2]).ToString();

        }

        private static int Calculate(int firstOperand, int secondOperand, string operand)
        {
            if (operand == "-")
                return firstOperand - secondOperand;

            if (operand == "/")
                return firstOperand / secondOperand;

            return firstOperand + secondOperand;
        }
    }
}