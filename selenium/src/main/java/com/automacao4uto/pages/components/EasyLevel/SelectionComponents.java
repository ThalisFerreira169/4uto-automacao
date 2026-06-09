package com.automacao4uto.pages.components.EasyLevel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectionComponents {
    private WebDriver driver;

    // Locators
    private By selectInput = By.xpath("//*[@id='section-elementos-basicos']/div[2]/div/div[3]/div[2]/div/div/button");
    private By selectOutput = By.cssSelector("[data-testid='select-output']");

    // Constructor
    public SelectionComponents(WebDriver driver) {
        this.driver = driver;
    }

    // Getters
    public By getSelectInputLocator() {
        return this.selectInput;
    }

    public By getSelectOutputLocator() {
        return this.selectOutput;
    }

    public WebElement getSelectInput() {
        return driver.findElement(selectInput);
    }

    public String getSelectOutputText() {
        return driver.findElement(selectOutput).getText();
    }

    // Métodos
    public void selectOptionPerVisibleText(String visibleText) {
        driver.findElement(selectInput).click();

        By optionLocator = By.xpath("//button[contains(text(), '" + visibleText + "')]");
        driver.findElement(optionLocator).click();
    }
}
