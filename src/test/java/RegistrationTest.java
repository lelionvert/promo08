import domain.Registration;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RegistrationTest {


    @Test
    public void should_return_full_price_for_full_week_end() {
        Registration registration = new Registration("No Accommodation");
        Assertions.assertThat(registration.calculatePrice()).isEqualTo(240);
    }


}
