package com.automacao4uto;

import com.automacao4uto.pages.QaPlaygroundPage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QaPlaygroundTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private QaPlaygroundPage page;

    @Before
    public void setUp() {
        // Setup para rodar via Pipeline
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Setup para rodar localmente
        /*
         * driver = new ChromeDriver();
         * wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         * driver.manage().window().maximize();
         */

        driver.get("https://playground-for-qa.vercel.app/playground");
        page = new QaPlaygroundPage(driver);
    }

    @Test
    public void test00_AccessingQAPlayground() {
        Assert.assertTrue(driver.getTitle().contains("Playground"));
    }

    @Test
    public void test01_ClickingButtons() {
        wait.until(ExpectedConditions.elementToBeClickable(page.clicks.getSimpleClickBtnLocator()));

        Assert.assertTrue(page.clicks.getSimpleClickButton().isDisplayed());
        page.clicks.simpleClick(10);

        wait.until(ExpectedConditions.elementToBeClickable(page.clicks.getDoubleClickBtnLocator()));
        Assert.assertTrue(page.clicks.getDoubleClickButton().isDisplayed());
        page.clicks.doubleClick(10);
    }

    @Test
    public void test02_TextBox() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.texts.getTextBoxLocator()));

        String text = "Automação 4uto por Thalis Ferreira.";
        page.texts.enterText(text);

        Assert.assertEquals(text, page.texts.getTextBox().getDomAttribute("value"));
        Assert.assertEquals("Você digitou: " + text, page.texts.getTextOutputText());
    }

    @Test
    public void test03_Selections() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.selections.getSelectInputLocator()));

        String[] options = { "Playwright", "Cypress", "Selenium", "Robot Framework" };

        for (String option : options) {
            page.selections.selectOptionPerVisibleText(option);
            Assert.assertEquals("Selecionado: " + option, page.selections.getSelectOutputText());
        }
    }

    @Test
    public void test04_Controllers() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.controllers.getSliderLocator()));

        Assert.assertTrue(page.controllers.getSlider().isDisplayed());
        page.controllers.adjustSlider("80");
        Assert.assertEquals("80", page.controllers.getSlider().getDomAttribute("value"));

        Assert.assertTrue(page.controllers.getSwitch().isDisplayed());
        page.controllers.clickSwitch();
        Assert.assertEquals("true", page.controllers.getSwitch().getDomAttribute("aria-checked"));

        page.controllers.clickSwitch();
        Assert.assertEquals("false", page.controllers.getSwitch().getDomAttribute("aria-checked"));
    }

    @Test
    public void test05_fillForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.form.getSectionFormLocator()));

        String name = "Thalis Ferreira";
        String email = "thalis@teste.com";
        String password = "senhaSegura123";
        String confirmPassword = "senhaSegura123";

        page.form.fillForm(name, email, password, confirmPassword);
        page.form.checkTerms();
        page.form.clickClean();

        Assert.assertEquals("", page.form.getInputName().getDomAttribute("value"));
        Assert.assertEquals("", page.form.getInputEmail().getDomAttribute("value"));

        page.form.clickSend();
        String[] erros = { "Nome é obrigatório", "Email é obrigatório", "Senha é obrigatória",
                "Você deve aceitar os termos" };
        for (String erro : erros) {
            Assert.assertTrue(page.form.isErrorMessageVisible(erro));
        }

        page.form.fillForm(name, email, password, confirmPassword);
        page.form.checkTerms();
        page.form.clickSend();

        Assert.assertTrue(page.form.getSuccessMessage().isDisplayed());
    }

    @Test
    public void test06_Navigation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.navigation.getNavigationSectionLocator()));

        Assert.assertEquals("Página Inicial", page.navigation.getCurrentPageText());

        page.navigation.navigateTo("Sobre");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(page.navigation.getCurrentPageLabelLocator(),
                "Sobre"));
        Assert.assertEquals("Sobre", page.navigation.getCurrentPageText());

        page.navigation.navigateTo("Contato");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(page.navigation.getCurrentPageLabelLocator(),
                "Contato"));
        Assert.assertEquals("Contato", page.navigation.getCurrentPageText());

        page.navigation.navigateTo("Sobre");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(page.navigation.getCurrentPageLabelLocator(),
                "Sobre"));
        Assert.assertEquals("Sobre", page.navigation.getCurrentPageText());

        Assert.assertTrue(page.navigation.getHistoryText().contains("Histórico:"));

        Assert.assertTrue(page.navigation.getGithubLink().isDisplayed());
        String handleOriginal = driver.getWindowHandle();
        page.navigation.getGithubLink().click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(handleOriginal)) {
                driver.switchTo().window(tab);
                break;
            }
        }

        wait.until(ExpectedConditions.urlContains("github.com"));
        Assert.assertTrue(driver.getCurrentUrl().contains("github.com"));

        driver.close();
        driver.switchTo().window(handleOriginal);
    }

    @Test
    public void test07_AlertsAndModals() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.alertsModals.getAlertButtonLocator()));

        page.alertsModals.clickAlert();
        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals("Este é um alerta simples!", alerta.getText());
        alerta.accept();

        page.alertsModals.clickPrompt();
        Alert prompt = wait.until(ExpectedConditions.alertIsPresent());
        prompt.sendKeys("Thalis Ferreira");
        prompt.accept();

        page.alertsModals.clickConfirm();
        Alert confirm = wait.until(ExpectedConditions.alertIsPresent());
        confirm.accept();
        Assert.assertTrue(page.alertsModals.getToastMessage().isDisplayed());

        page.alertsModals.clickOpenModal();
        Assert.assertTrue(page.alertsModals.getCustomModal().isDisplayed());
        page.alertsModals.clickCloseModal();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(page.alertsModals.getCustomModalLocator()));
    }

    @Test
    public void test08_CheckboxsAndRadios() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.checkboxsRadios.getOption1CheckboxLocator()));

        Assert.assertFalse(page.checkboxsRadios.getOption1Checkbox().isSelected());
        Assert.assertTrue(page.checkboxsRadios.getCheckboxCountText().contains("0"));

        page.checkboxsRadios.getOption1Checkbox().click();
        Assert.assertTrue(page.checkboxsRadios.getCheckboxCountText().contains("1"));

        page.checkboxsRadios.selectAllCheckboxes();
        Assert.assertTrue(page.checkboxsRadios.getOption1Checkbox().isSelected());
        Assert.assertTrue(page.checkboxsRadios.getOption2Checkbox().isSelected());
        Assert.assertTrue(page.checkboxsRadios.getOption3Checkbox().isSelected());

        page.checkboxsRadios.selectAllCheckboxes();
        Assert.assertFalse(page.checkboxsRadios.getOption1Checkbox().isSelected());

        Assert.assertFalse(page.checkboxsRadios.getRadio1().isSelected());

        page.checkboxsRadios.getRadio1().click();
        Assert.assertTrue(page.checkboxsRadios.getRadio1().isSelected());
        Assert.assertFalse(page.checkboxsRadios.getRadio2().isSelected());
        Assert.assertTrue(page.checkboxsRadios.getRadioCountText().contains("radio1"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}