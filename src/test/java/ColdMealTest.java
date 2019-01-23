import domain.CheckIn;
import domain.ColdMealChecker;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ColdMealTest {

    @Test
    public void should_return_a_cold_meal_between_21_and_midnight() {
        CheckIn coldMealCheckin = new CheckIn("Thursday", "22:00");
        assertThat(ColdMealChecker.counter(coldMealCheckin)).isEqualTo(1);
    }
}
