using System;
using System.Collections.Generic;
using System.Text;

namespace RPN
{
    public class InvalidOperation: Operation
    {
        public InvalidOperation() : base(0,0,"/")
        {
        }
    }
}
