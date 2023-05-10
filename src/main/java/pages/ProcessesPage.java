package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ProcessesPage extends HomePage {

    public ProcessesPage(WebDriver driver) {
        super(driver);
    }

    private String GENERIC_PROCESS_ROW_XPATH = "//td[text()='%s']/..";

    @FindBy(css = ".title_left h3")
    private WebElement pageHeader;

    @FindBy(linkText = "Add new process")
    private WebElement addNewProcessBtn;

    @FindBy(css = ".btn[href*=\"Projects/Delete\"]")
    private WebElement deleteBtn;

    @FindBy(css = "button[type=submit]")
    private WebElement confirmDeleteBtn;

    public CreateProcessPage clickAddNewProcess() {
        addNewProcessBtn.click();
        return new CreateProcessPage(driver);
    }

    public ProcessesPage deleteProcess() {
        deleteBtn.click();
        confirmDeleteBtn.click();
        return new ProcessesPage(driver);
    }

    public ProcessesPage assertProcessesUrl(String actualUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), actualUrl);
        return this;
    }

    public ProcessesPage assertProcessesHeader() {
        Assert.assertEquals(pageHeader.getText(), "Processes");
        return this;
    }

    public ProcessesPage assertProcessIsNotShown(String processName) {
        String processXpath = String.format(GENERIC_PROCESS_ROW_XPATH, processName);
        List<WebElement> process = driver.findElements(By.xpath(processXpath));
        Assert.assertEquals(process.size(), 0);
        return this;
    }

    public ProcessesPage assertProcess(String expName, String expDescription, String expNotes) {
        String processXpath = String.format(GENERIC_PROCESS_ROW_XPATH, expName);
        WebElement processRow = driver.findElement(By.xpath(processXpath));
        String actDescription = processRow.findElement(By.xpath("./td[2]")).getText();
        String actNotes = processRow.findElement(By.xpath("./td[3]")).getText();
        Assert.assertEquals(actDescription, expDescription);
        Assert.assertEquals(actNotes, expNotes);
        return this;
    }
}
