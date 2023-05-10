import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.LoginPage;

public class ReportTest extends SeleniumBaseTest {

    @Test
    public void shouldCalculateCorrectReport() {
        String processName = "DEMO PROJECT";
        Faker faker = new Faker();
        String characteristicName = faker.lorem().characters(0, 10);
        String lsl = "12";
        String usl = "20";
        String sampleName = "Test sample";
        String results = "12.0;20.0";
        String expMean = "16.0000";

        new LoginPage(driver)
                .login(config.getApplicationUser(), config.getApplicationPassword())
                .goToCharacteristics()
                .addNewCharacteristic()
                .selectProcess(processName)
                .typeName(characteristicName)
                .typeLsl(lsl)
                .typeUsl(usl)
                .submitCreate()
                .goToResults(characteristicName)
                .clickAddResultsSample()
                .typeSampleName(sampleName)
                .typeResults(results)
                .submitCreate()
                .backToCharacteristics()
                .goToReport(characteristicName)
                .assertMean(expMean)
                .goToCharacteristics()
                .deleteCharacteristic();
    }
}
