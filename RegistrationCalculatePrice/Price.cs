namespace RegistrationCalculatePrice
{
    internal class Price
    {
        private readonly int _value;

        private Price(int value)
        {
            this._value = value;
        }


        public static Price ValueOf(int value)
        {
            return new Price(value);
        }

        public Price Minus(Price other)
        {
            return ValueOf(_value - other._value);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return obj is Price other && _value == other._value;
        }

      
    }
}