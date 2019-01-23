import domain.CheckIn;
import domain.ColdMealChecker;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ColdMealTest {

    @Test
    public void should_return_cold_meals_between_21_and_midnight() {
        List<CheckIn> coldMealCheckins = Arrays.asList(
                new CheckIn("THURSDAY 22h00"),
                new CheckIn("THURSDAY 23h59"),
                new CheckIn("THURSDAY 21h00")
        );
        assertThat(ColdMealChecker.count(coldMealCheckins)).isEqualTo(3);
    }

    @Test
    public void should_return_no_cold_meal_before_21() {
        List<CheckIn> hotMealCheckins = Arrays.asList(
                new CheckIn("THURSDAY 19h00"),
                new CheckIn("THURSDAY 17h00"),
                new CheckIn("THURSDAY 20h59")
        );
        assertThat(ColdMealChecker.count(hotMealCheckins)).isEqualTo(0);
    }

    @Test
    public void should_return_no_cold_meal_after_00() {
        List<CheckIn> lateMealCheckins = Arrays.asList(
                new CheckIn("FRIDAY 01h00"),
                new CheckIn("FRIDAY 00h00"),
                new CheckIn("SATURDAY 23h00")
        );
        assertThat(ColdMealChecker.count(lateMealCheckins)).isEqualTo(0);
    }

    @Test
    public void should_return_no_cold_meal_if_empty() {
        List<CheckIn> lateMealCheckins = Collections.singletonList(
                new CheckIn("")
        );
        assertThat(ColdMealChecker.count(lateMealCheckins)).isEqualTo(0);
    }
}
