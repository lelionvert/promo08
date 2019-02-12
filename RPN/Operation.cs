namespace RPN
{
    public abstract class Operation
    {
        protected const string PlusSymbol = "+";
        protected const string MinusSymbol = "-";
        protected const string DivideSymbol = "/";
        protected readonly int _firstOperand;
        protected readonly int _secondOperand;
        public static OperationBuilder Builder=new OperationBuilder();

        protected Operation(int firstOperand, int secondOperand)
        {
            _firstOperand = firstOperand;
            _secondOperand = secondOperand;
        }

        public abstract int Calculate();

        public virtual bool IsValid()
        {
            return true;
        }

        public class OperationBuilder
        {
            private int _firstOperand;
            private int _secondOperand;
            private string _symbol;

            internal OperationBuilder() {}

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
                if (_symbol == DivideSymbol)
                    return new DivisionOperation(_firstOperand, _secondOperand);
                if (_symbol == MinusSymbol)
                    return new SusbstrationOperation(_firstOperand, _secondOperand);

                return new AdditionOperation(_firstOperand, _secondOperand);

                
            }
        }
    }
}