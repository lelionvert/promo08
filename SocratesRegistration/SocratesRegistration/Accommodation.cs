namespace SocratesRegistration
{
    internal class Accommodation
    {
        private Choice choice;
        
        public Accommodation(Choice choice)
        {
            this.choice = choice;
        }

        public int Price => (int)choice;

        public enum Choice
        {
            Single = 610,
            Double = 510,
            Triple = 410,
            NoAccommodation = 240
        }
    }
}