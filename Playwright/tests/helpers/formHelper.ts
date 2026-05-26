import { Locator, expect } from '@playwright/test';

export async function fillAndValidate(locator: Locator, value: string) {
    await expect(locator).toBeVisible();
    await expect(locator).toBeEnabled();

    await locator.fill(value);

    await expect(locator).toHaveValue(value);
};