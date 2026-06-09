package com.automacao4uto.pages.components.EasyLevel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ControllersComponents {
    private WebDriver driver;

    // Locators
    private By slider = By.cssSelector("[data-testid='range-input']");
    private By switchInput = By.cssSelector("[data-testid='toggle-switch']");

    // Constructor
    public ControllersComponents(WebDriver driver) {
        this.driver = driver;
    }

    // Getters
    public By getSliderLocator() {
        return this.slider;
    }

    public By getSwitchInputLocator() {
        return this.switchInput;
    }

    public WebElement getSlider() {
        return driver.findElement(slider);
    }

    public WebElement getSwitch() {
        return driver.findElement(switchInput);
    }

    public void adjustSlider(String targetValue) {
        WebElement sl = driver.findElement(slider);

        int currentValue = Integer.parseInt(sl.getDomAttribute("value"));
        int target = Integer.parseInt(targetValue);

        sl.click();

        int difference = target - currentValue;
        Actions move = new Actions(driver);

        if (difference > 0) {
            for (int i = 0; i < difference; i++) {
                move.sendKeys(Keys.ARROW_RIGHT).perform();
            }
        } else if (difference < 0) {
            for (int i = 0; i < Math.abs(difference); i++) {
                move.sendKeys(Keys.ARROW_LEFT).perform();
            }
        }
    }

    public void clickSwitch() {
        driver.findElement(switchInput).click();
    }
}
