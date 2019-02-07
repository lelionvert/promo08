using System;
using System.Collections.Generic;

namespace CalculateRegistration
{
    public class RoomCatalog : ICatalog
    {
        private readonly Dictionary<RoomChoice, Price> _catalog;


        public RoomCatalog(Dictionary<RoomChoice, Price> catalog)
        {
            _catalog = catalog;
        }
        public Price GetRoomPrice(RoomChoice roomChoice)
        {
            if (_catalog.ContainsKey(roomChoice))
                return _catalog[roomChoice];

            throw new ArgumentException("room choice does not exist in the catalog");
        }
    }
}
