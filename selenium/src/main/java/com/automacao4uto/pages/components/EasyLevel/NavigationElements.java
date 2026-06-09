package com.automacao4uto.pages.components.EasyLevel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationElements {
    private WebDriver driver;

    // Locators
    private By navigationSection = By.cssSelector("[data-testid='section-navegacao-links']");
    private By currentPageLabel = By.cssSelector("[data-testid='current-page']");
    private By historyLabel = By.xpath("//*[contains(text(), 'Histórico:')]");
    private By githubLink = By.cssSelector("[data-testid='link-external']");

    // Constructor
    public NavigationElements(WebDriver driver) {
        this.driver = driver;
    }

    // Getters
    public By getNavigationSectionLocator() {
        return this.navigationSection;
    }

    public By getCurrentPageLabelLocator() {
        return this.currentPageLabel;
    }

    public By getHistoryLabelLocator() {
        return this.historyLabel;
    }

    public By getGithubLinkLocator() {
        return this.githubLink;
    }

    public WebElement getSectionNavegacao() {
        return driver.findElement(navigationSection);
    }

    public String getCurrentPageText() {
        return driver.findElement(currentPageLabel).getText();
    }

    public String getHistoryText() {
        return driver.findElement(historyLabel).getText();
    }

    public WebElement getGithubLink() {
        return driver.findElement(githubLink);
    }

    // Métodos
    public void navigateTo(String linkText) {
        driver.findElement(navigationSection)
                .findElement(By.xpath(".//*[contains(text(), '" + linkText + "')]"))
                .click();
    }
}
