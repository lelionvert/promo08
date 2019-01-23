import domain.CheckIn;
import domain.ColdMealChecker;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ColdMealTest {

    @Test
    public void should_return_a_cold_meal_between_21_and_midnight() {
        List<CheckIn> coldMealCheckin = Arrays.asList(new CheckIn("Thursday", "22h00"));
        assertThat(ColdMealChecker.counter(coldMealCheckin)).isEqualTo(1);
    }

    @Test
    public void should_return_no_cold_meal_before_21() {
        List<CheckIn> hotMealCheckin = Arrays.asList(new CheckIn("Thursday", "19h00"));
        assertThat(ColdMealChecker.counter(hotMealCheckin)).isEqualTo(0);
    }

    @Test
    public void should_return_no_cold_meal_after_00() {
        List<CheckIn> hotMealCheckin = Arrays.asList(new CheckIn("Friday", "01h00"));
        assertThat(ColdMealChecker.counter(hotMealCheckin)).isEqualTo(0);
    }
}
