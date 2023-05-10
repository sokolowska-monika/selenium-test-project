import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginIncorrectTest extends SeleniumBaseTest {
    @Test
    public void shouldNotLoginWithNotRegisteredUser() {
        new LoginPage(driver)
                .typeEmail("test@test.co")
                .typePassword("Test1!")
                .submitLoginWithFailure()
                .assertLoginErrorIsShown("Invalid login attempt.");
    }

    @DataProvider
    public Object[][] wrongEmails() {
        return new Object[][]{
                {"test"},
                {"@test"},
                {"test.test.com"},
                {"test()@test.com"},
                {"test@test@"},
        };
    }

    @Test(dataProvider = "wrongEmails")
    public void shouldNotLoginWithIncorrectEmail(String wrongEmail) {
        new LoginPage(driver)
                .typeEmail(wrongEmail)
                .typePassword("Test1!")
                .submitLoginWithFailure()
                .assertLoginErrorIsShown("The Email field is not a valid e-mail address.");
    }
}

