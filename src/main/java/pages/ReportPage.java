package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ReportPage {
    protected WebDriver driver;

    public ReportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//td[text()='Mean (x)']/../td[2]")
    private WebElement meanCell;

    @FindBy(css = ".menu-workspace")
    private WebElement workspaceNav;

    @FindBy(css = "a[href$=Characteristics]")
    private WebElement characteristicsMenu;

    private boolean isParentExpanded(WebElement menuLink) {
        WebElement parent = menuLink.findElement(By.xpath("./.."));
        return parent.getAttribute("class").contains("active");
    }

    public CharacteristicsPage goToCharacteristics() {
        if (!isParentExpanded(workspaceNav)) {
            workspaceNav.click();
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(characteristicsMenu));
        characteristicsMenu.click();
        return new CharacteristicsPage(driver);
    }

    public ReportPage assertMean(String expMean) {
        Assert.assertEquals(meanCell.getText(), expMean);
        return this;
    }
}
