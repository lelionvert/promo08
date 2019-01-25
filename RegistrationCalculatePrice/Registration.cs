namespace RegistrationCalculatePrice
{
    internal class Registration
    {
        private readonly Price _roomPrice;

        public Registration(Price roomPrice)
        {
            this._roomPrice = roomPrice;
        }

        public Price CalculatePrice()
        {
            return _roomPrice;
        }
    }
}