package com.automacao4uto.pages.components.EasyLevel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ClickComponents {
    private WebDriver driver;

    // Locators
    private By simpleClickBtn = By.xpath("//*[@id='section-elementos-basicos']/div[2]/div/div[1]/div[2]/div/button[1]");
    private By doubleClickBtn = By.xpath("//*[@id='section-elementos-basicos']/div[2]/div/div[1]/div[2]/div/button[2]");

    // Constructor
    public ClickComponents(WebDriver driver) {
        this.driver = driver;
    }

    // Getters
    public By getSimpleClickBtnLocator() {
        return this.simpleClickBtn;
    }

    public By getDoubleClickBtnLocator() {
        return this.doubleClickBtn;
    }

    public WebElement getSimpleClickButton() {
        return driver.findElement(simpleClickBtn);
    }

    public WebElement getDoubleClickButton() {
        return driver.findElement(doubleClickBtn);
    }

    // Métodos
    public void simpleClick(int times) {
        for (int i = 0; i < times; i++) {
            driver.findElement(simpleClickBtn).click();
        }
    }

    public void doubleClick(int times) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++) {
            WebElement btn = driver.findElement(doubleClickBtn);
            actions.doubleClick(btn).perform();
        }
    }
}
