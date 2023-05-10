import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.LoginPage;

public class AddCorrectProcessTest extends SeleniumBaseTest {

    @Test
    public void shouldCreateProcessWithOnlyRequiredFields() {
        Faker faker = new Faker();
        String processName = faker.lorem().characters(3, 30);

        new LoginPage(driver)
                .typeEmail(config.getApplicationUser())
                .typePassword(config.getApplicationPassword())
                .submitLogin()
                .goToProcesses()
                .clickAddNewProcess()
                .typeName(processName)
                .typeDescription(config.getApplicationProcessDescription())
                .typeNotes(config.getApplicationProcessNotes())
                .submitCreate()
                .assertProcess(processName,
                        "description123",
                        "notes123")
                .deleteProcess();
    }

    @Test
    public void shouldCreateProcessWithAllFields() {
        Faker faker = new Faker();
        String processName = faker.lorem().characters(3, 30);
        String processDescription = faker.lorem().characters(0, 256);
        String processNotes = faker.lorem().characters(0, 256);

        new LoginPage(driver)
                .typeEmail(config.getApplicationUser())
                .typePassword(config.getApplicationPassword())
                .submitLogin()
                .goToProcesses()
                .clickAddNewProcess()
                .typeName(processName)
                .typeDescription(processDescription)
                .typeNotes(processNotes)
                .submitCreate()
                .assertProcess(processName,
                        processDescription,
                        processNotes)
                .deleteProcess();
    }
}
