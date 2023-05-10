package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class CharacteristicsPage extends HomePage {
    private String GENERIC_CHARACTERISTIC_REPORT_XPATH = "//td[text()='%s']/..//a[contains(@href, 'Report')]";
    private String GENERIC_CHARACTERISTIC_RESULTS_XPATH = "//td[text()='%s']/..//a[contains(@href, 'Results')]";
    private String GENERIC_CHARACTERISTIC_ROW_XPATH = "//td[text()='%s']/..";

    public CharacteristicsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".title_left h3")
    private WebElement pageHeader;

    @FindBy(css = "a[href$='/Characteristics/Create']")
    private WebElement addNewCharacteristicBtn;

    @FindBy(css = "a[href*='/Results?charId=20']")
    private WebElement resultsBtn;

    @FindBy(css = ".btn[href*=\"Characteristics/Delete\"]")
    private WebElement deleteBtn;

    @FindBy(css = "button[type=submit]")
    private WebElement confirmDeleteBtn;

    public CreateCharacteristicPage addNewCharacteristic() {
        addNewCharacteristicBtn.click();
        return new CreateCharacteristicPage(driver);
    }

    public CharacteristicsPage deleteCharacteristic() {
        deleteBtn.click();
        confirmDeleteBtn.click();
        return new CharacteristicsPage(driver);
    }

    public ResultsPage goToResults(String characteristicName) {
        String resultsBtnXpath = String.format(GENERIC_CHARACTERISTIC_RESULTS_XPATH, characteristicName);
        driver.findElement(By.xpath(resultsBtnXpath)).click();
        return new ResultsPage(driver);
    }

    public ReportPage goToReport(String characteristicName) {
        String reportBtnXpath = String.format(GENERIC_CHARACTERISTIC_REPORT_XPATH, characteristicName);
        driver.findElement(By.xpath(reportBtnXpath)).click();
        return new ReportPage(driver);
    }

    public CharacteristicsPage assertCharacteristic(String expName, String expLsl, String expUsl, String expBinCount) {
        String characteristicXpath = String.format(GENERIC_CHARACTERISTIC_ROW_XPATH, expName);
        WebElement characteristicRow = driver.findElement(By.xpath(characteristicXpath));
        String actLsl = characteristicRow.findElement(By.xpath("./td[3]")).getText();
        String actUsl = characteristicRow.findElement(By.xpath("./td[4]")).getText();
        String actBinCount = characteristicRow.findElement(By.xpath("./td[5]")).getText();
        Assert.assertEquals(actLsl, expLsl);
        Assert.assertEquals(actUsl, expUsl);
        Assert.assertEquals(actBinCount, expBinCount);
        return this;
    }

    public CharacteristicsPage assertCharacteristicsUrl(String actualUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), actualUrl);
        return this;
    }

    public CharacteristicsPage assertCharacteristicsHeader() {
        Assert.assertEquals(pageHeader.getText(), "Characteristics");
        return this;
    }

    public CharacteristicsPage assertCharacteristicIsNotShown(String characteristicName) {
        String characteristicXpath = String.format(GENERIC_CHARACTERISTIC_ROW_XPATH, characteristicName);
        List<WebElement> characteristicRow = driver.findElements(By.xpath(characteristicXpath));
        Assert.assertEquals(characteristicRow.size(), 0);
        return this;
    }
}
