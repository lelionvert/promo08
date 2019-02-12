using System;
using System.Collections.Generic;
using System.Text;

namespace RPN
{
    public class DivisionOperation : Operation
    {
        public DivisionOperation(int firstOperand, int secondOperand) 
            : base(firstOperand, secondOperand)
        {
        }

        public override int Calculate()
        {
            return _firstOperand / _secondOperand;
        }

        public override bool IsValid()
        {
            return _secondOperand != 0;
        }
    }
}
