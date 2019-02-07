using System;

namespace CalculateRegistration
{
    public class Meal
    {
        public DateTime Time { get; }
        private MealType _mealType;

        public Meal(DateTime dateTime, MealType mealType)
        {
            Time = dateTime;
            _mealType = mealType;
        }

        protected bool Equals(Meal other)
        {
            return _mealType == other._mealType && Time.Equals(other.Time);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((Meal) obj);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return ((int) _mealType * 397) ^ Time.GetHashCode();
            }
        }

        public static bool operator ==(Meal left, Meal right)
        {
            return Equals(left, right);
        }

        public static bool operator !=(Meal left, Meal right)
        {
            return !Equals(left, right);
        }
    }

    public enum MealType
    {
        Dinner,
        Lunch
    }
}