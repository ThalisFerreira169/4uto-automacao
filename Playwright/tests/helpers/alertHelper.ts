import { Locator, Page, expect, Dialog } from '@playwright/test';

export async function validateDialog(
    page: Page,
    button: Locator,
    expectedType: 'alert' | 'prompt' | 'confirm',
    expectedMessage: string,
    promptText?: string
) {
    await expect(button).toBeVisible();

    page.once('dialog', async (dialog: Dialog) => {
        expect(dialog.type()).toBe(expectedType);
        expect(dialog.message()).toBe(expectedMessage);

        if (expectedType === 'prompt') {
            await dialog.accept(promptText);
        } else {
            await dialog.accept();
        }
    });

    await button.click();
};

export async function validateCustomModal(
    openButton: Locator,
    modal: Locator,
    closeButton: Locator,
    expectedTitle: string
) {
    await expect(openButton).toBeVisible();

    await openButton.click();

    await expect(modal).toBeVisible();
    await expect(modal).toContainText(expectedTitle);

    await expect(closeButton).toBeVisible();

    await closeButton.click();

    await expect(modal).not.toBeVisible();
};