import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.LoginPage;

public class AddIncorrectCharacteristic extends SeleniumBaseTest {

    @Test
    public void shouldNotCreateCharacteristicWithoutProject() {
        Faker faker = new Faker();
        String characteristicName = faker.lorem().characters(0, 10);
        String lsl = "8";
        String usl = "10";

        new LoginPage(driver)
                .login(config.getApplicationUser(), config.getApplicationPassword())
                .goToCharacteristics()
                .addNewCharacteristic()
                .typeName(characteristicName)
                .typeLsl(lsl)
                .typeUsl(usl)
                .submitCreateWithFailure()
                .assertProcessError("The value 'Select process' is not valid for ProjectId.")
                .backToList()
                .assertCharacteristicIsNotShown(characteristicName)
                .deleteCharacteristic();
    }
}

