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

        protected bool Equals(Price other)
        {
            return _value == other._value;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((Price) obj);
        }

        public override int GetHashCode()
        {
            return _value;
        }
    }
}