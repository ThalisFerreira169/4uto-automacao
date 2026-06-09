package com.automacao4uto.pages.components.EasyLevel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormComponents {
    private WebDriver driver;

    // Locators
    private By sectionForm = By.cssSelector("[data-testid='section-formularios-simples']");
    private By inputName = By.cssSelector("[data-testid='input-name']");
    private By inputEmail = By.cssSelector("[data-testid='input-email']");
    private By inputPassword = By.cssSelector("[data-testid='input-password']");
    private By inputConfirmPassword = By.cssSelector("[data-testid='input-confirm-password']");
    private By checkboxTerms = By.cssSelector("[data-testid='checkbox-terms']");
    private By sendButton = By.xpath("//button[text()='Enviar']");
    private By clearButton = By.xpath("//button[text()='Limpar']");
    private By successMessage = By.cssSelector("[data-testid='success-message']");

    // Constructor
    public FormComponents(WebDriver driver) {
        this.driver = driver;
    }

    // Getters
    public By getSectionFormLocator() {
        return this.sectionForm;
    }

    public By getInputNameLocator() {
        return this.inputName;
    }

    public By getInputEmailLocator() {
        return this.inputEmail;
    }

    public By getInputPasswordLocator() {
        return this.inputPassword;
    }

    public By getInputConfirmPasswordLocator() {
        return this.inputConfirmPassword;
    }

    public By getCheckboxTermsLocator() {
        return this.checkboxTerms;
    }

    public By getSendButtonLocator() {
        return this.sendButton;
    }

    public By getClearButtonLocator() {
        return this.clearButton;
    }

    public By getSuccessMessageLocator() {
        return this.successMessage;
    }

    public void clickClean() {
        driver.findElement(clearButton).click();
    }

    public void clickSend() {
        driver.findElement(sendButton).click();
    }

    public WebElement getInputName() {
        return driver.findElement(inputName);
    }

    public WebElement getInputEmail() {
        return driver.findElement(inputEmail);
    }

    public WebElement getInputPassword() {
        return driver.findElement(inputPassword);
    }

    public WebElement getInputConfirmPassword() {
        return driver.findElement(inputConfirmPassword);
    }

    public WebElement getSuccessMessage() {
        return driver.findElement(successMessage);
    }

    // Métodos
    public void fillForm(String name, String email, String password, String confirmPassword) {
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputEmail).sendKeys(email);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(inputConfirmPassword).sendKeys(confirmPassword);
    }

    public void checkTerms() {
        WebElement cb = driver.findElement(checkboxTerms);
        if (!cb.isSelected())
            cb.click();
    }

    public boolean isErrorMessageVisible(String errorMessage) {
        return driver.findElement(By.xpath("//*[contains(text(), '" + errorMessage + "')]")).isDisplayed();
    }
}
