package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CreateCharacteristicPage {
    protected WebDriver driver;

    public CreateCharacteristicPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ProjectId")
    private WebElement projectSlc;

    @FindBy(id = "Name")
    private WebElement nameTxt;

    @FindBy(id = "LowerSpecificationLimit")
    private WebElement lslTxt;

    @FindBy(id = "UpperSpecificationLimit")
    private WebElement uslTxt;

    @FindBy(linkText = "Back to List")
    private WebElement backToListBtn;

    @FindBy(css = "input[type=submit]")
    private WebElement createBtn;

    @FindBy(css = ".field-validation-error[data-valmsg-for=ProjectId]")
    private WebElement processError;

    public CharacteristicsPage backToList() {
        backToListBtn.click();
        return new CharacteristicsPage(driver);
    }

    public CreateCharacteristicPage selectProcess(String processName) {
        new Select(projectSlc).selectByVisibleText(processName);
        return this;
    }

    public CharacteristicsPage submitCreate() {
        createBtn.click();
        return new CharacteristicsPage(driver);
    }

    public CreateCharacteristicPage submitCreateWithFailure() {
        createBtn.click();
        return this;
    }

    public CreateCharacteristicPage typeName(String characteristicName) {
        nameTxt.clear();
        nameTxt.sendKeys(characteristicName);
        return this;
    }

    public CreateCharacteristicPage typeLsl(String lsl) {
        lslTxt.clear();
        lslTxt.sendKeys(lsl);
        return this;
    }

    public CreateCharacteristicPage typeUsl(String usl) {
        uslTxt.clear();
        uslTxt.sendKeys(usl);
        return this;
    }

    public CreateCharacteristicPage assertProcessError(String expError) {
        Assert.assertEquals(processError.getText(), expError);
        return this;
    }
}

