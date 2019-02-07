using System.Collections.Generic;
using System.Linq;

namespace CalculateRegistration
{
    internal class DietReport
    {
        private readonly List<Serving> _servings;

        public DietReport(List<Serving> servings)
        {
            _servings = servings;
        }

        protected bool Equals(DietReport other)
        {
            return _servings.SequenceEqual(other._servings);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((DietReport) obj);
        }

        public override int GetHashCode()
        {
            return (_servings != null ? _servings.GetHashCode() : 0);
        }

        public static bool operator ==(DietReport left, DietReport right)
        {
            return Equals(left, right);
        }

        public static bool operator !=(DietReport left, DietReport right)
        {
            return !Equals(left, right);
        }
    }
}