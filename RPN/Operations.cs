using System;
using System.Collections.Generic;
using System.Dynamic;
using System.Text;

namespace RPN
{

    public class Operations
    {
        private const string PlusSymbol = "+";
        private const string MinusSymbol = "-";
        private const string DivideSymbol = "/";
        private readonly IReadOnlyDictionary<string, Func<int, int, Operation>> _operations;

        private static readonly object Syncroot=new object();
        private static Operations _instance;

        public static Operations Instance
        {
            get
            {
                lock (Syncroot)
                {
                    if(_instance == null)
                        _instance=new Operations();
                }
                return _instance;
            }
        }

        private Operations()
        {
            _operations = new Dictionary<string, Func<int, int, Operation>>
            {
                {PlusSymbol, (x, y) => new AdditionOperation(x, y)},
                {MinusSymbol, (x, y) => new SusbstrationOperation(x, y)},
                {DivideSymbol, (x, y) => new DivisionOperation(x, y)}
            };
        }

        public Operation GetOperation(int firstOperand, int secondOperand, string symbol)
        {
            if (!_operations.ContainsKey(symbol))
                return new UnknownOperation();
            return _operations[symbol](firstOperand, secondOperand);
        }
    }
}
