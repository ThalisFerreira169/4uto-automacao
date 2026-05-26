import { Locator, expect } from '@playwright/test';

export async function clickWithCounter(locator: Locator, times: number, label: string) {
    for (let i = 1; i <= times; i++) {
        await locator.click();
        await expect(locator).toContainText(`${label} (${i})`);
    }
};

export async function doubleClickWithCounter(locator: Locator, times: number, label: string) {
    for (let i = 1; i <= times; i++) {
        await locator.dblclick();
        await expect(locator).toContainText(`${label} (${i})`);
    }
};