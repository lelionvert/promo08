namespace CalculateRegistration
{
    public interface ICatalog
    {
        Price GetRoomPrice(RoomChoice roomChoice);
        
    }
}