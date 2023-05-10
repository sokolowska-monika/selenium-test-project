package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateResultsPage {
    protected WebDriver driver;

    public CreateResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#Sample")
    private WebElement sampleTxt;

    @FindBy(css = "#Values")
    private WebElement resultsTxt;

    @FindBy(css = "input[type=submit]")
    private WebElement createBtn;

    public CreateResultsPage typeResults(String results) {
        resultsTxt.clear();
        resultsTxt.sendKeys(results);
        return this;
    }

    public CreateResultsPage typeSampleName(String sampleName) {
        sampleTxt.clear();
        sampleTxt.sendKeys(sampleName);
        return this;
    }

    public ResultsPage submitCreate() {
        createBtn.click();
        return new ResultsPage(driver);
    }
}
