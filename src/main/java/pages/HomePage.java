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

public class HomePage {
    protected WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".profile_info>h2")
    private WebElement welcomeElm;

    @FindBy(css = ".menu-workspace")
    private WebElement workspaceNav;

    @FindBy(css = "a[href$=Projects]")
    private WebElement processesMenu;

    @FindBy(css = "a[href$=Characteristics]")
    private WebElement characteristicsMenu;

    @FindBy(css = ".menu-home")
    private WebElement homeNav;

    @FindBy(xpath = "//a[contains(text(), 'Dashboard')]")
    private WebElement dashboardsMenu;

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

    public DashboardsPage goToDashboards() {
        if (!isParentExpanded(homeNav)) {
            homeNav.click();
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(dashboardsMenu));
        dashboardsMenu.click();
        return new DashboardsPage(driver);
    }

    public ProcessesPage goToProcesses() {
        if (!isParentExpanded(workspaceNav)) {
            workspaceNav.click();
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(processesMenu));
        processesMenu.click();
        return new ProcessesPage(driver);
    }

    public HomePage assertWelcomeElementIsShown() {
        Assert.assertTrue(welcomeElm.isDisplayed(), "Welcome element is not shown");
        Assert.assertTrue(welcomeElm.getText().contains("Welcome"),
                "Welcome element text: '" + welcomeElm.getText() + "' does not contain word 'Welcome'");
        return this;
    }
}

