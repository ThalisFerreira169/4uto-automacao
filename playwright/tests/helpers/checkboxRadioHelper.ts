import { Locator, expect } from '@playwright/test';

export async function expectCheckedCount(counter: Locator, expected: number | string) {
    await expect(counter).toHaveText(
        `Selecionados: ${expected}`
    );
};

export async function expectSelectedRadioCount(counter: Locator, expected: string) {
    await expect(counter).toHaveText(
        `Selecionado: ${expected}`
    );
};

export async function validateCheckbox(checkbox: Locator, shouldBeChecked: boolean) {
    if (shouldBeChecked) {
        await expect(checkbox).toBeChecked();
    } else {
        await expect(checkbox).not.toBeChecked();
    }
};

export async function validateAllCheckboxes(checkboxes: Locator[], shouldBeChecked: boolean) {
    for (const checkbox of checkboxes) {
        await validateCheckbox(checkbox, shouldBeChecked);
    }
};

export async function selectCheckbox(checkbox: Locator) {
    await checkbox.check();
    await expect(checkbox).toBeChecked();
};

export async function unselectCheckbox(checkbox: Locator) {
    await checkbox.uncheck();
    await expect(checkbox).not.toBeChecked();
};

export async function validateRadioSelection(selectedRadio: Locator, unselectedRadios: Locator[]) {
    await expect(selectedRadio).toBeChecked();

    for (const radio of unselectedRadios) {
        await expect(radio).not.toBeChecked();
    }
};