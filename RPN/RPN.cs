using System;
using System.Linq;
using static System.Int32;

namespace RPN
{
    public class Rpn
    {
        private const char Separator = ' ';

        public static string Calculate(string value)
        {
            if (string.IsNullOrEmpty(value))
                return string.Empty;

            var splitValue = value.Split(Separator);
            
            if (splitValue.Length == 3 
                && TryParse(splitValue[0], out int firstOperand) 
                && TryParse(splitValue[1], out int secondOperand))
            {
                if (splitValue[2] == "+")
                    return $"{firstOperand + secondOperand}";

                if (splitValue[2] == "-")
                    return $"{firstOperand - secondOperand}";
            }
            
            return value;
        }
    }
}