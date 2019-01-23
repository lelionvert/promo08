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

    @Test
    public void should_return_no_cold_meal_before_21() {
        CheckIn hotMealCheckin = new CheckIn("Thursday", "19:00");
        assertThat(ColdMealChecker.counter(hotMealCheckin)).isEqualTo(0);
    }

}
