import { test as base, expect } from '@playwright/test';

export const test = base;

test.beforeEach(async ({ page }) => {
    await page.goto('https://playground-for-qa.vercel.app/playground');
});

export { expect };