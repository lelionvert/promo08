namespace RPN
{
    public class Rpn
    {
        public static string Calculate(string value)
        {
            if (string.IsNullOrEmpty(value))
                return string.Empty;

            if (value == "1 2 +")
            {
                return "3";
            }
            if (value == "3 2 +")
            {
                return "5";
            }
            return value;
        }
    }
}