using System;
using System.Diagnostics;
using System.Text.RegularExpressions;
using static System.Int32;
using static System.Text.RegularExpressions.Regex;

namespace RPN
{
    public class Rpn
    {
        private const string Pattern = "(-?[0-9]+) (-?[0-9]+) ([+/-])";
        private const int FirstOperandGroup = 1;
        private const int SecondOperandGroup = 2;
        private const int OperatorGroup = 3;

        public static string Process(string input)
        {
            if (!IsMatch(input, Pattern))
                return input;

            Regex regex = new Regex(Pattern);
            var match = regex.Match(input);
            string result = input;
            while (match.Success)
            {
                var operation = Operation.Builder
                    .WithFirstOperand(Parse(match.Groups[FirstOperandGroup].Value))
                    .WithSecondOperand(Parse(match.Groups[SecondOperandGroup].Value))
                    .WithSymbol(match.Groups[OperatorGroup].Value)
                    .Build();

                if (operation is InvalidOperation invalidOperation)
                {
                    Debug.WriteLine(invalidOperation.Message);
                    return result;
                }

                result = result.Replace(match.Groups[0].Value, operation.Calculate().ToString());
                match = regex.Match(result);
            }

            return result;
        }
    }
}