import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.LoginPage;

public class AddCorrectCharacteristics extends SeleniumBaseTest {

    @Test
    public void shouldCreateCharacteristic() {
        String processName = "DEMO PROJECT";
        Faker faker = new Faker();
        String characteristicName = faker.lorem().characters(0, 10);
        String lsl = "12";
        String usl = "20";

        new LoginPage(driver)
                .login(config.getApplicationUser(), config.getApplicationPassword())
                .goToCharacteristics()
                .addNewCharacteristic()
                .selectProcess(processName)
                .typeName(characteristicName)
                .typeLsl(lsl)
                .typeUsl(usl)
                .submitCreate()
                .assertCharacteristic(characteristicName, lsl, usl, "")
                .deleteCharacteristic();
    }
}
