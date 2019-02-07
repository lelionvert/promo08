using System;

namespace CalculateRegistration
{
    public class StayPeriod
    {
        public DateTime CheckIn { get; }
        public DateTime CheckOut { get; }
        private static readonly DateTime CheckInLimit = new DateTime(2019, 10, 24, 00,00,00);
        private static readonly DateTime CheckOutLimit = new DateTime(2019, 10, 28, 00,00,00);
        public static StayPeriodBuilder Builder=new StayPeriodBuilder();

        private StayPeriod(DateTime checkIn, DateTime checkOut)
        {
            CheckIn = checkIn;
            CheckOut = checkOut;
        }

        public bool BeginAfter(DateTime date)
        {
            return CheckIn >= date;
        }

        public bool EndBefore(DateTime date)
        {
            return CheckOut <= date;
        }

        public class StayPeriodBuilder
        {
            private DateTime _checkIn;
            private DateTime _checkOut;
            public StayPeriodBuilder WithCheckIn(DateTime checkIn)
            {
                if (checkIn < CheckInLimit)
                    throw new ArgumentException("Participant can't arrive before Thursday");
                _checkIn = checkIn;
                return this;
            }

            public StayPeriodBuilder WithCheckOut(DateTime checkOut)
            {
                if (checkOut > CheckOutLimit)
                    throw new ArgumentException("Participant can't leave after Sunday");
                _checkOut = checkOut;
                return this;
            }

            public StayPeriod Build()
            {
                if(_checkOut <= _checkIn)
                    throw new ArgumentException("check out must be after check in");
                return new StayPeriod(_checkIn,_checkOut);
            }
        }
    }
}