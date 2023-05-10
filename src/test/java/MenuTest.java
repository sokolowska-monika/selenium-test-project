import org.testng.annotations.Test;
import pages.LoginPage;

public class MenuTest extends SeleniumBaseTest {

    @Test
    public void shouldMenuRedirectToProperPagesTest() {
        new LoginPage(driver)
                .typeEmail(config.getApplicationUser())
                .typePassword(config.getApplicationPassword())
                .submitLogin()
                .goToProcesses()
                .assertProcessesUrl(config.getApplicationUrl() + "Projects")
                .assertProcessesHeader()
                .goToCharacteristics()
                .assertCharacteristicsUrl(config.getApplicationUrl() + "Characteristics")
                .assertCharacteristicsHeader()
                .goToDashboards()
                .assertDashboardsUrl(config.getApplicationUrl())
                .assertDashboardHeaderIsShown();
    }
}

