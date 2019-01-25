namespace RegistrationCalculatePrice
{
    internal class Registration
    {
        private readonly Price _roomPrice;
        private readonly Price _mealPrice;
        private readonly StayPeriod _stayPeriod;

        public Registration(Price roomPrice, Price mealPrice, StayPeriod stayPeriod = null)
        {
            this._roomPrice = roomPrice;
            _mealPrice = mealPrice;
            _stayPeriod = stayPeriod;
        }

        public Price CalculatePrice()
        {
            return _stayPeriod == null 
                ? _roomPrice 
                : _roomPrice.Minus(_mealPrice);
        }
    }
}