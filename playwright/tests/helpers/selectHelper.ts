import { Locator, expect } from '@playwright/test';

export async function selectOption(
    page: any,
    selectLocator: Locator,
    optionTestId: string,
    outputLocator: Locator,
    expectedLabel: string
) {
    await selectLocator.click();
    await page.getByTestId(optionTestId).click();

    await expect(outputLocator).toBeVisible();
    await expect(outputLocator).toHaveText(`Selecionado: ${expectedLabel}`);
};