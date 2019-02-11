using System;
using System.Text.RegularExpressions;
using static System.Int32;
using static System.Text.RegularExpressions.Regex;

namespace RPN
{
    public class Rpn
    {
        private const string Pattern = "(-?[0-9]+) (-?[0-9]+) ([+/-])";

        public static string Process(string input)
        {
            if (!IsMatch(input, Pattern))
                return input;

            Regex regex = new Regex(Pattern);
            var match = regex.Match(input);
            string result = input;
            while (match.Success)
            {
                var operation = Operation.Builder.
                    WithFirstOperand(Parse(match.Groups[1].Value)).
                    WithSecondOperand(Parse(match.Groups[2].Value)).
                    WithSymbol(match.Groups[3].Value).Build();

                if (operation is InvalidOperation)
                    return result;

                result = result.Replace(match.Groups[0].Value, operation.Calculate().ToString());
                match = regex.Match(result);
            }

            return result;
        }
    }
}