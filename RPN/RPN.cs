using static System.Int32;
using static System.Text.RegularExpressions.Regex;

namespace RPN
{
    public class Rpn
    {
        private const char Separator = ' ';
        private const string Pattern = "-?[0-9]+ -?[0-9]+ [+-]";

        public static string Process(string input)
        {
            return IsMatch(input, Pattern) 
                ? Calculate(input) 
                : input;
        }

        private static string Calculate(string input)
        {
            var splitValue = input.Split(Separator);
            int firstOperand = Parse(splitValue[0]);
            int secondOperand = Parse(splitValue[1]);
       
            if (splitValue[2] == "-")
                return $"{firstOperand - secondOperand}";

            return $"{firstOperand + secondOperand}";
        }
    }
}