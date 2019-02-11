namespace RPN
{
    internal class Operation
    {
        private readonly int _firstOperand;
        private readonly int _secondOperand;
        private readonly string _symbol;
        public static OperationBuilder Builder=new OperationBuilder();

        private Operation(int firstOperand, int secondOperand, string symbol)
        {
            _firstOperand = firstOperand;
            _secondOperand = secondOperand;
            _symbol = symbol;
        }

        public int Calculate()
        {
            if (_symbol == "-")
                return _firstOperand - _secondOperand;

            if (_symbol == "/")
                return _firstOperand / _secondOperand;

            return _firstOperand + _secondOperand;
        }

        public bool IsDividedByZero()
        {
            if (_secondOperand == 0 && _symbol == "/")
                return true;
            return false;
        }

        internal class OperationBuilder
        {
            private int _firstOperand;
            private int _secondOperand;
            private string _symbol;

            public OperationBuilder WithFirstOperand(int firstOperand)
            {
                _firstOperand = firstOperand;
                return this;
            }

            public OperationBuilder WithSecondOperand(int secondOperand)
            {
                _secondOperand = secondOperand;
                return this;
            }

            public OperationBuilder WithSymbol(string symbol)
            {
                _symbol = symbol;
                return this;
            }

            public Operation Build()
            {
                return new Operation(_firstOperand,_secondOperand,_symbol);
            }
        }
    }
}