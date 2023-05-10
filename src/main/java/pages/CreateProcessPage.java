package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CreateProcessPage extends HomePage {

    public CreateProcessPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#Name")
    private WebElement nameTxt;

    @FindBy(css = "#Description")
    private WebElement descriptionTxt;

    @FindBy(css = "#Notes")
    private WebElement notesTxt;

    @FindBy(css = "input[value=Create]")
    private WebElement createBtn;

    @FindBy(linkText = "Back to List")
    private WebElement backToListBtn;

    @FindBy(css = ".field-validation-error[data-valmsg-for=Name]")
    private WebElement nameError;

    public CreateProcessPage backToList() {
        backToListBtn.click();
        return new CreateProcessPage(driver);
    }

    public CreateProcessPage typeDescription(String description) {
        descriptionTxt.clear();
        descriptionTxt.sendKeys(description);
        return this;
    }

    public CreateProcessPage typeName(String name) {
        nameTxt.clear();
        nameTxt.sendKeys(name);
        return this;
    }

    public CreateProcessPage typeNotes(String notes) {
        notesTxt.clear();
        notesTxt.sendKeys(notes);
        return this;
    }

    public ProcessesPage submitCreate() {
        createBtn.click();
        return new ProcessesPage(driver);
    }

    public CreateProcessPage submitCreateWithFailure() {
        createBtn.click();
        return this;
    }

    public CreateProcessPage assertProcessNameError(String expError) {
        Assert.assertEquals(nameError.getText(), expError);
        return this;
    }
}
