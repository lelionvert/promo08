using System;

namespace CalculateRegistration
{
    public class Meal
    {
        public DateTime Time { get; }
        private readonly MealType _mealType;
        public DateTime? DateMustBePresentBefore { get; }
        private readonly DateTime? _mustBePresentAfter;

        public Meal(DateTime dateTime, MealType mealType, DateTime? dateMustBePresentBefore = null, DateTime? mustBePresentAfter = null)
        {
            Time = dateTime;
            _mealType = mealType;
            DateMustBePresentBefore = dateMustBePresentBefore;
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
            if (obj.GetType() != GetType()) return false;
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
            var isPresentAfter = !_mustBePresentAfter.HasValue ||
                                 Time.Date != _mustBePresentAfter.Value.Date ||
                                 participant.IsPresent(_mustBePresentAfter.Value);
            var isPresentBefore = !DateMustBePresentBefore.HasValue ||
                                  Time.Date != DateMustBePresentBefore.Value.Date ||
                                  participant.IsPresent(DateMustBePresentBefore.Value);
            return isPresentAfter && isPresentBefore;
        }
    }

    public enum MealType
    {
        Dinner,
        Lunch
    }
}