import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class AddIncorrectProcessTest extends SeleniumBaseTest {

    @DataProvider
    public Object[][] wrongProcessesNames() {
        return new Object[][]{
                {"xx", "The field Name must be a string with a minimum length of 3 and a maximum length of 30."},
                {"", "The Name field is required."},
                {"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "The field Name must be a string with a minimum length of 3 and a maximum length of 30."}
        };
    }

    @Test(dataProvider = "wrongProcessesNames")
    public void shouldNotCreateProcessWithIncorrectName(String processName, String expError) {
        new LoginPage(driver)
                .login(config.getApplicationUser(), config.getApplicationPassword())
                .goToProcesses()
                .clickAddNewProcess()
                .typeName(processName)
                .submitCreateWithFailure()
                .assertProcessNameError(expError)
                .backToList();
                .assertProcessIsNotShown(processName);
    }
}
