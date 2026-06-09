package com.automacao4uto.pages.components.EasyLevel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlertsModalsComponents {
    private WebDriver driver;

    // Locators
    private By alertButton = By.cssSelector("[data-testid='button-alert']");
    private By promptButton = By.cssSelector("[data-testid='button-prompt']");
    private By confirmButton = By.cssSelector("[data-testid='button-confirm']");
    private By toastMessage = By.cssSelector("[data-testid='toast-message']");
    private By openModalButton = By.cssSelector("[data-testid='button-modal']");
    private By customModal = By.cssSelector("[data-testid='custom-modal']");
    private By closeModalButton = By.cssSelector("[data-testid='modal-close-button']");

    // Constructor
    public AlertsModalsComponents(WebDriver driver) {
        this.driver = driver;
    }

    // Getters
    public By getAlertButtonLocator() {
        return this.alertButton;
    }

    public By getPromptButtonLocator() {
        return this.promptButton;
    }

    public By getConfirmButtonLocator() {
        return this.confirmButton;
    }

    public By getToastMessageLocator() {
        return this.toastMessage;
    }

    public By getOpenModalButtonLocator() {
        return this.openModalButton;
    }

    public By getCustomModalLocator() {
        return this.customModal;
    }

    public By getCloseModalButtonLocator() {
        return this.closeModalButton;
    }

    // Métodos
    public void clickAlert() {
        driver.findElement(alertButton).click();
    }

    public void clickPrompt() {
        driver.findElement(promptButton).click();
    }

    public void clickConfirm() {
        driver.findElement(confirmButton).click();
    }

    public WebElement getToastMessage() {
        return driver.findElement(toastMessage);
    }

    public void clickOpenModal() {
        driver.findElement(openModalButton).click();
    }

    public WebElement getCustomModal() {
        return driver.findElement(customModal);
    }

    public void clickCloseModal() {
        driver.findElement(closeModalButton).click();
    }
}
