using System;

namespace CalculateRegistration
{
    public class Meal
    {
        public DateTime Time { get; }
        private readonly MealType _mealType;
        private readonly DateTime? _mustBePresentBefore;
        private readonly DateTime? _mustBePresentAfter;

        public Meal(DateTime dateTime, MealType mealType, DateTime? mustBePresentBefore = null, DateTime? mustBePresentAfter = null)
        {
            Time = dateTime;
            _mealType = mealType;
            _mustBePresentBefore = mustBePresentBefore;
            _mustBePresentAfter = mustBePresentAfter;
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
            return Equals((Meal)obj);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return ((int)_mealType * 397) ^ Time.GetHashCode();
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

        public bool IsPresent(Participant participant)
        {
            var isPresentAfter = _mustBePresentAfter == null ||
                                 Time.Date != _mustBePresentAfter.Value.Date ||
                                 participant.IsPresent(_mustBePresentAfter.Value);
            var isPresentBefore = _mustBePresentBefore == null ||
                                  Time.Date != _mustBePresentBefore.Value.Date ||
                                  participant.IsPresent(_mustBePresentBefore.Value);
            return isPresentAfter && isPresentBefore;
        }
    }

    public enum MealType
    {
        Dinner,
        Lunch
    }
}