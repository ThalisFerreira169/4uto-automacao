package com.automacao4uto.pages.components.EasyLevel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxsRadiosComponents {
    private WebDriver driver;

    // Locators
    private By selectAllCheckbox = By.cssSelector("[data-testid='checkbox-select-all']");
    private By option1Checkbox = By.cssSelector("[data-testid='checkbox-option1']");
    private By option2Checkbox = By.cssSelector("[data-testid='checkbox-option2']");
    private By option3Checkbox = By.cssSelector("[data-testid='checkbox-option3']");
    private By checkboxCountLabel = By.cssSelector("[data-testid='checkbox-count']");
    private By radioCountLabel = By.cssSelector("[data-testid='radio-output']");
    private By radio1 = By.cssSelector("[data-testid='radio-radio1']");
    private By radio2 = By.cssSelector("[data-testid='radio-radio2']");
    private By radio3 = By.cssSelector("[data-testid='radio-radio3']");

    // Constructor
    public CheckboxsRadiosComponents(WebDriver driver) {
        this.driver = driver;
    }

    // Getters
    public By getSelectAllCheckboxLocator() {
        return this.selectAllCheckbox;
    }

    public By getOption1CheckboxLocator() {
        return this.option1Checkbox;
    }

    public By getOption2CheckboxLocator() {
        return this.option2Checkbox;
    }

    public By getOption3CheckboxLocator() {
        return this.option3Checkbox;
    }

    public By getCheckboxCountLabelLocator() {
        return this.checkboxCountLabel;
    }

    public By getRadioCountLabelLocator() {
        return this.radioCountLabel;
    }

    public By getRadio1Locator() {
        return this.radio1;
    }

    public By getRadio2Locator() {
        return this.radio2;
    }

    public By getRadio3Locator() {
        return this.radio3;
    }

    public WebElement getOption1Checkbox() {
        return driver.findElement(option1Checkbox);
    }

    public WebElement getOption2Checkbox() {
        return driver.findElement(option2Checkbox);
    }

    public WebElement getOption3Checkbox() {
        return driver.findElement(option3Checkbox);
    }

    public String getCheckboxCountText() {
        return driver.findElement(checkboxCountLabel).getText();
    }

    public String getRadioCountText() {
        return driver.findElement(radioCountLabel).getText();
    }

    public WebElement getRadio1() {
        return driver.findElement(radio1);
    }

    public WebElement getRadio2() {
        return driver.findElement(radio2);
    }

    public WebElement getRadio3() {
        return driver.findElement(radio3);
    }

    // Métodos
    public void selectAllCheckboxes() {
        driver.findElement(selectAllCheckbox).click();
    }
}
