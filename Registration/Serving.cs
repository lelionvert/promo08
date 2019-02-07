using System.Collections.Generic;
using System.Linq;

namespace CalculateRegistration
{
    public class Serving
    {
        private readonly Meal _meal;
        private readonly Dictionary<Diet, int> _covers;
        public int NumberOfColdMeals { get;}

        public Serving(Meal meal, Dictionary<Diet, int> covers, int numberOfColdMeals = 0)
        {
            NumberOfColdMeals = numberOfColdMeals;
            _meal = meal;
            _covers = covers;
        }


        protected bool Equals(Serving other)
        {
            return Equals(_meal, other._meal) &&
                   _covers.OrderBy(cover => cover.Key).
                       SequenceEqual(other._covers.OrderBy(cover => cover.Key));
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            return obj.GetType() == this.GetType() && Equals((Serving) obj);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return ((_meal != null ? _meal.GetHashCode() : 0) * 397) ^ (_covers != null ? _covers.GetHashCode() : 0);
            }
        }

        public static bool operator ==(Serving left, Serving right)
        {
            return Equals(left, right);
        }

        public static bool operator !=(Serving left, Serving right)
        {
            return !Equals(left, right);
        }
    }
}