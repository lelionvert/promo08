using System.Collections.Generic;
using System.Linq;

namespace CalculateRegistration
{
    public class ColdMealReport
    {
        public IReadOnlyCollection<Email> Emails { get; }

        public ColdMealReport(IReadOnlyCollection<Email> emails)
        {
            Emails = emails;
        }

        public int Count => Emails.Count;

        protected bool Equals(ColdMealReport other)
        {
            return Emails.SequenceEqual(other.Emails);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            return obj.GetType() == GetType() && Equals((ColdMealReport) obj);
        }

        public override int GetHashCode()
        {
            return Emails != null ? Emails.GetHashCode() : 0;
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
            return $"Count: {Count}, {nameof(Emails)}: {Emails}";
        }
    }
}