import { Locator, expect } from '@playwright/test';

export async function navigateAndExpect(
    page: any,
    section: Locator,
    buttonName: string,
    expectedPage: string
) {
    const currentPage = section.getByTestId('current-page');
    const button = section.getByRole('button', { name: buttonName });

    await button.click();
    await expect(currentPage).toHaveText(expectedPage);
};

export async function expectHistorico(historicoLocator: Locator) {
    const texto = await historicoLocator.textContent();

    expect(texto).toMatch(/Histórico:/);
    expect(texto).toMatch(/\d+\s*página\(s\)/);
};

export async function validateExternalLink(
    page: any,
    linkLocator: Locator,
    expectedUrl: string
) {
    await expect(linkLocator).toBeVisible();
    await linkLocator.click();

    const [newPage] = await Promise.all([
        page.context().waitForEvent('page'),
        linkLocator.click()
    ]);

    await newPage.waitForLoadState();
    await expect(newPage).toHaveURL(expectedUrl);
};