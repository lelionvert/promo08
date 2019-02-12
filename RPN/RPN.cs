using System;
using System.Diagnostics;
using System.Text.RegularExpressions;
using static System.Int32;
using static System.Text.RegularExpressions.Regex;

namespace RPN
{
    public static class Rpn
    {
        private const int FirstOperandGroup = 1;
        private const int SecondOperandGroup = 2;
        private const int OperatorGroup = 3;
        private static readonly Regex Regex = new Regex("(-?[0-9]+) (-?[0-9]+) ([+/-])");

        public static string Process(string input)
        {
            var match = Regex.Match(input);
            string result = input;
            while (match.Success)
            {
                var operation = Operation.Builder
                    .WithFirstOperand(Parse(match.Groups[FirstOperandGroup].Value))
                    .WithSecondOperand(Parse(match.Groups[SecondOperandGroup].Value))
                    .WithSymbol(match.Groups[OperatorGroup].Value)
                    .Build();

                if (!operation.IsValid())
                    return result;

                result = result.Replace(match.Groups[0].Value, operation.Calculate().ToString());
                match = Regex.Match(result);
            }

            return result;
        }
    }
}