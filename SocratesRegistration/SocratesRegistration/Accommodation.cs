namespace SocratesRegistration
{
    public class Accommodation
    {
        private readonly Choice _choice;
        private readonly Stay _checkIn;

        public Accommodation(Choice choice, Stay checkIn)
        {
            this._choice = choice;
            this._checkIn = checkIn;
        }

        public Price ComputePrice()
        {
            if (_checkIn.IsAfter(21))
                return Price.ValueOf(200);
            return Price.ValueOf((int) _choice);
        }

        public enum Choice
        {
            Single = 610,
            Double = 510,
            Triple = 410,
            NoAccommodation = 240
        }
    }
}