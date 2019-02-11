using System;
using System.Collections.Generic;
using System.Text;

namespace RPN
{
    public class ValidOperation: Operation
    {
        protected internal ValidOperation(int firstOperand, int secondOperand, string symbol) : base(firstOperand, secondOperand, symbol)
        {
        }
    }
}
