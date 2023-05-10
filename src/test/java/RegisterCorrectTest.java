import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.LoginPage;

public class RegisterCorrectTest extends SeleniumBaseTest {

    Faker faker = new Faker();
    String email = faker.internet().emailAddress();
    String password = "1A!" + faker.lorem().characters(8);

    @Test
    public void shouldRegister() {
        new LoginPage(driver)
                .goToRegisterPage()
                .typeEmail(email)
                .typePassword(password)
                .typeConfirmPassword(password)
                .submitRegister()
                .assertWelcomeElementIsShown();
    }
}
