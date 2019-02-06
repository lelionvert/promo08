using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CalculateRegistration
{
    public class Price
    {
        private readonly int _value;

        private Price(int value)
        {
            _value = value;
        }

        public static Price ValueOf(int value)
        {
            return new Price(value);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return obj is Price other && _value == other._value;
        }

        public override int GetHashCode()
        {
            return _value;
        }

        public Price MultiplyPrice(int value)
        {
            return Price.ValueOf(_value * value);
        }

        public static bool operator ==(Price left, Price right)
        {
            return Equals(left, right);
        }

        public static bool operator !=(Price left, Price right)
        {
            return !Equals(left, right);
        }

        public static Price operator +(Price left, Price right)
        {
            return ValueOf(left._value+right._value);
        }
    }
}
