package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CreateAccountPage {
    protected WebDriver driver;

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#Email")
    private WebElement emailTxt;

    @FindBy(css = "#Password")
    private WebElement passwordTxt;

    @FindBy(css = "#ConfirmPassword")
    private WebElement confirmPasswordTxt;

    @FindBy(css = "button[type=submit]")
    private WebElement registerBtn;

    @FindBy(css = "#ConfirmPassword-error")
    private WebElement confirmPasswordErrorElm;

    @FindBy(css = ".validation-summary-errors")
    private WebElement wrongPasswordErrorElm;

    @FindBy(css = ".validation-summary-errors>ul>li")
    public List<WebElement> registerErrors;

    public CreateAccountPage typeEmail(String email) {
        emailTxt.clear();
        emailTxt.sendKeys(email);
        return this;
    }

    public CreateAccountPage typePassword(String password) {
        passwordTxt.clear();
        passwordTxt.sendKeys(password);
        return this;
    }

    public CreateAccountPage typeConfirmPassword(String confirmPassword) {
        confirmPasswordTxt.clear();
        confirmPasswordTxt.sendKeys(confirmPassword);
        return this;
    }

    public CreateAccountPage submitRegisterWithFailure() {
        registerBtn.click();
        return new CreateAccountPage(driver);
    }

    public HomePage submitRegister() {
        registerBtn.click();
        return new HomePage(driver);
    }

    public CreateAccountPage assertConfirmationPasswordIsShown(String expError) {
        Assert.assertTrue(confirmPasswordErrorElm.isDisplayed());
        Assert.assertEquals(confirmPasswordErrorElm.getText(), expError);
        return this;
    }

    public CreateAccountPage assertErrorPasswordIsShown(String expError) {
        Assert.assertTrue(wrongPasswordErrorElm.isDisplayed());
        Assert.assertEquals(wrongPasswordErrorElm.getText(), expError);
        return this;
    }

    public CreateAccountPage assertRegisterErrorIsShown(String expErrors) {
        Assert.assertEquals(registerErrors.get(0).getText(), expErrors);
        return this;
    }
}
