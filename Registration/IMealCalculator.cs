namespace CalculateRegistration
{
    public interface IMealCalculator
    {
        Price CalculateMealPrice(StayPeriod period);
    }
}