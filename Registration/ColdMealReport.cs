using System.Collections.Generic;
using System.Linq;

namespace CalculateRegistration
{
    public class ColdMealReport
    {
        public IReadOnlyCollection<Email> Emails { get; }

        public ColdMealReport(IReadOnlyCollection<Email> emails)
        {
            this.Emails = emails;
        }

        protected bool Equals(ColdMealReport other)
        {
            return Emails.SequenceEqual(other.Emails);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((ColdMealReport) obj);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return Emails != null ? Emails.GetHashCode() : 0;
            }
        }

        public static bool operator ==(ColdMealReport left, ColdMealReport right)
        {
            return Equals(left, right);
        }

        public static bool operator !=(ColdMealReport left, ColdMealReport right)
        {
            return !Equals(left, right);
        }
        public override string ToString()
        {
            return $"Count: {Emails.Count}, {nameof(Emails)}: {Emails}";
        }
    }
}