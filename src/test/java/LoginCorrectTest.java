import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginCorrectTest extends SeleniumBaseTest {

    @Test
    public void shouldLogin() {
        new LoginPage(driver)
                .typeEmail(config.getApplicationUser())
                .typePassword(config.getApplicationPassword())
                .submitLogin()
                .assertWelcomeElementIsShown();
    }
}
