package com.automacao4uto.pages.components.EasyLevel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextComponents {
    private WebDriver driver;

    // Locators
    private By textBox = By.xpath("//*[@id='section-elementos-basicos']/div[2]/div/div[2]/div[2]/div[1]/div[1]/input");
    private By textOutput = By.cssSelector("[data-testid='text-output']");

    // Constructor
    public TextComponents(WebDriver driver) {
        this.driver = driver;
    }

    // Getters
    public By getTextBoxLocator() {
        return this.textBox;
    }

    public By getTextOutputLocator() {
        return this.textOutput;
    }

    public WebElement getTextBox() {
        return driver.findElement(textBox);
    }

    public String getTextOutputText() {
        return driver.findElement(textOutput).getText();
    }

    // Métodos
    public void enterText(String text) {
        WebElement inputField = driver.findElement(textBox);
        inputField.clear();
        inputField.sendKeys(text);
    }
}
