namespace RPN
{
    internal class Operation
    {
        private readonly int _firstOperand;
        private readonly int _secondOperand;
        private readonly string _symbol;

        public Operation(int firstOperand, int secondOperand, string symbol)
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
    }
}