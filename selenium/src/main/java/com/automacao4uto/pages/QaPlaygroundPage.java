package com.automacao4uto.pages;

import com.automacao4uto.pages.components.EasyLevel.ClickComponents;
import com.automacao4uto.pages.components.EasyLevel.SelectionComponents;
import com.automacao4uto.pages.components.EasyLevel.TextComponents;
import com.automacao4uto.pages.components.EasyLevel.ControllersComponents;
import com.automacao4uto.pages.components.EasyLevel.FormComponents;
import com.automacao4uto.pages.components.EasyLevel.NavigationElements;
import com.automacao4uto.pages.components.EasyLevel.AlertsModalsComponents;
import com.automacao4uto.pages.components.EasyLevel.CheckboxsRadiosComponents;

import org.openqa.selenium.WebDriver;

public class QaPlaygroundPage {
    public ClickComponents clicks;
    public TextComponents texts;
    public SelectionComponents selections;
    public ControllersComponents controllers;
    public FormComponents form;
    public NavigationElements navigation;
    public AlertsModalsComponents alertsModals;
    public CheckboxsRadiosComponents checkboxsRadios;

    // Constructor
    public QaPlaygroundPage(WebDriver driver) {
        this.clicks = new ClickComponents(driver);
        this.texts = new TextComponents(driver);
        this.selections = new SelectionComponents(driver);
        this.controllers = new ControllersComponents(driver);
        this.form = new FormComponents(driver);
        this.navigation = new NavigationElements(driver);
        this.alertsModals = new AlertsModalsComponents(driver);
        this.checkboxsRadios = new CheckboxsRadiosComponents(driver);
    }
}