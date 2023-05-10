package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class DashboardsPage extends HomePage {

    public DashboardsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.x_title")
    private WebElement pageHeader;

    @FindBy(css = "h3.text-center")
    private WebElement pageTitle;

    private boolean isElementPresent(WebElement element) {
        try {
            return pageTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public DashboardsPage assertDashboardHeaderIsShown() {
        if (isElementPresent(pageTitle)) {
            assertEmptyDashboardHeaderIsShown();
        } else {
            assertNonEmptyDashboardHeaderIsShown();
        }
        return this;
    }

    public DashboardsPage assertEmptyDashboardHeaderIsShown() {
        Assert.assertTrue(pageTitle.isDisplayed(), "There are no processes defined");
        Assert.assertTrue(pageTitle.getText().contains("There are no processes defined"));
        return this;
    }

    public DashboardsPage assertNonEmptyDashboardHeaderIsShown() {
        Assert.assertTrue(pageHeader.isDisplayed(), "There is defined process");
        return this;
    }

    public DashboardsPage assertDashboardsUrl(String actualUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), actualUrl);
        return this;
    }
}
