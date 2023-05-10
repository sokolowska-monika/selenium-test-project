import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class RegisterIncorrectTest extends SeleniumBaseTest {
    Faker faker = new Faker();
    String email = faker.internet().emailAddress();
    String password = "1A!" + faker.lorem().characters(8);
    String confirmedPassword = "1A!" + faker.lorem().characters(8);

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
    public void shouldNotRegisterWithIncorrectEmail(String wrongEmail) {
        new LoginPage(driver)
                .goToRegisterPage()
                .typeEmail(wrongEmail)
                .typePassword("Test1!")
                .typeConfirmPassword("Test1!")
                .submitRegisterWithFailure()
                .assertRegisterErrorIsShown("The Email field is not a valid e-mail address.");
    }

    @DataProvider
    public static Object[][] wrongPasswords() {
        return new Object[][]{
                {"test1!", "Passwords must have at least one uppercase ('A'-'Z')."},
                {"TEST1!", "Passwords must have at least one lowercase ('a'-'z')."},
                {"Tests!", "Passwords must have at least one digit ('0'-'9')."},
                {"Test11", "Passwords must have at least one non alphanumeric character."},
                {"t", "The Password must be at least 6 and at max 100 characters long."},
                {new Faker().lorem().characters(101),
                        "The Password must be at least 6 and at max 100 characters long."},
        };
    }

    @Test(dataProvider = "wrongPasswords")
    public void shouldNotRegisterWithIncorrectPasswordTest(String password, String expErrorMessage) {
        new LoginPage(driver)
                .goToRegisterPage()
                .typeEmail(email)
                .typePassword(password)
                .typeConfirmPassword(password)
                .submitRegisterWithFailure()
                .assertErrorPasswordIsShown(expErrorMessage);
    }

    @Test
    public void shouldNotRegisterWithDifferentPasswordsInputTest() {
        new LoginPage(driver)
                .goToRegisterPage()
                .typeEmail(email)
                .typePassword(password)
                .typeConfirmPassword(confirmedPassword)
                .submitRegisterWithFailure()
                .assertConfirmationPasswordIsShown("The password and confirmation password do not match.");
    }
}
