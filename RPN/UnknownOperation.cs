using System;
using System.Collections.Generic;
using System.Text;

namespace RPN
{
    public class UnknownOperation : Operation
    {
        public UnknownOperation() : base(0, 0)
        {
        }

        public override int Calculate()
        {
            throw new NotImplementedException();
        }

        public override bool IsValid()
        {
            return false;
        }
    }
}
