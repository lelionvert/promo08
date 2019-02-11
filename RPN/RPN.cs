using System;
using System.Text.RegularExpressions;
using static System.Int32;
using static System.Text.RegularExpressions.Regex;

namespace RPN
{
    public class Rpn
    {
        private const char Separator = ' ';
        private const string Pattern = "(-?[0-9]+ -?[0-9]+ [+/-])";

        public static string Process(string input)
        {
            if (!IsMatch(input, Pattern))
                return input;

            Regex regex = new Regex(Pattern);
            var match = regex.Match(input);
            string result = input;
            while (match.Success)
            {
                var splitInput = match.Groups[0].Value.Split(Separator);

                var operation = Operation.Builder.
                    WithFirstOperand(Parse(splitInput[0])).
                    WithSecondOperand(Parse(splitInput[1])).
                    WithSymbol(splitInput[2]).Build();

                if (operation.IsDividedByZero())
                    return result;

                result = result.Replace(match.Groups[0].Value, operation.Calculate().ToString());
                match = regex.Match(result);

            }

            return result;

         
        }
    }
}