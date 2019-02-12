using System;
using System.Collections.Generic;
using System.Text;

namespace RPN
{
    public class AdditionOperation : Operation
    {
        public AdditionOperation(int firstOperand, int secondOperand)
            : base(firstOperand, secondOperand)
        {
        }

        public override int Calculate()
        {
            return _firstOperand + _secondOperand;
        }
    }
}
