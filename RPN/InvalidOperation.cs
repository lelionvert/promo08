using System;
using System.Collections.Generic;
using System.Text;

namespace RPN
{
    public class InvalidOperation: Operation
    {
        public string Message { get; }

        public InvalidOperation(string message)
            : base(0,0,"/")
        {
            Message = message;
        }
    }
}
