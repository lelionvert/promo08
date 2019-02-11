using System;
using static System.Int32;
using static System.Text.RegularExpressions.Regex;

namespace RPN
{
    public class Rpn
    {
        private const char Separator = ' ';
        private const string Pattern = "-?[0-9]+ -?[0-9]+ [+/-]";

        public static string Process(string input)
        {
            if (!IsMatch(input, Pattern))
                return input;

            var splitInput = input.Split(Separator);

            int firstOperand = Parse(splitInput[0]);
            int secondOperand = Parse(splitInput[1]);
            var symbol = splitInput[2];
            var operation = new Operation(firstOperand, secondOperand, symbol);

            if (operation.IsDividedByZero())
                return input;

            return operation.Calculate().ToString();
        }
    }
}