using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CalculateRegistration
{
    public class Email
    {
        public string Mail { get; }

        public Email(string mail)
        {
            Mail = mail;
        }

        protected bool Equals(Email other)
        {
            return string.Equals(Mail, other.Mail);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((Email) obj);
        }

        public override int GetHashCode()
        {
            return (Mail != null ? Mail.GetHashCode() : 0);
        }

        public static bool operator ==(Email left, Email right)
        {
            return Equals(left, right);
        }

        public static bool operator !=(Email left, Email right)
        {
            return !Equals(left, right);
        }
    }
}
