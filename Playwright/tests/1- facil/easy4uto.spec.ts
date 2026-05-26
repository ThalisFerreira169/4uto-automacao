import { test, expect } from '../utils/hooks';
import { simpleFormData } from '../data/simpleForm';
import { clickWithCounter, doubleClickWithCounter } from '../helpers/clickHelper';
import { fillAndValidate } from '../helpers/formHelper';
import { selectOption } from '../helpers/selectHelper';
import { navigateAndExpect } from '../helpers/navigateHelper';
import { expectHistorico } from '../helpers/navigateHelper';
import { validateExternalLink } from '../helpers/navigateHelper';
import { validateDialog } from '../helpers/alertHelper';
import { validateCustomModal } from '../helpers/alertHelper';
import {
    expectCheckedCount,
    expectSelectedRadioCount,
    validateAllCheckboxes,
    selectCheckbox,
    unselectCheckbox,
    validateRadioSelection
} from '../helpers/checkboxRadioHelper';

test('Acessando a QA Playground', async ({ page }) => {
    await expect(page).toHaveTitle(/Playground/);
});

test('Elementos básicos - Cliques', async ({ page }) => {
    const cliqueSimples = page.getByRole('button', { name: 'Clique aqui' });
    await expect(cliqueSimples).toBeVisible();
    await clickWithCounter(cliqueSimples, 10, 'Clique aqui');

    const doubleClick = page.getByRole('button', { name: 'Duplo clique' });
    await expect(doubleClick).toBeVisible();
    await doubleClickWithCounter(doubleClick, 10, 'Duplo clique');
});

test('Elementos básicos - Caixa de Texto', async ({ page }) => {
    const caixaDeTexto = page.getByRole('textbox', { name: 'Digite algo' });

    await expect(caixaDeTexto).toBeVisible();

    const texto = 'Automação 4uto por Thalis Ferreira.';
    await caixaDeTexto.click();
    await caixaDeTexto.fill(texto);

    await expect(caixaDeTexto).toHaveValue(texto);

    const textOutput = page.getByTestId('text-output');
    await expect(textOutput).toBeVisible();
    await expect(textOutput).toHaveText('Você digitou: ' + texto);
});

test('Elementos básicos - Seleção', async ({ page }) => {
    const section = page.getByTestId('section-elementos-basicos');
    const selectInput = section.getByTestId('select-input');
    await expect(selectInput).toBeVisible();

    const options = [
        { label: 'Playwright', testId: 'select-option-playwright' },
        { label: 'Cypress', testId: 'select-option-cypress' },
        { label: 'Selenium', testId: 'select-option-selenium' },
        { label: 'Robot Framework', testId: 'select-option-robot' }
    ];

    for (const option of options) {
        await selectOption(page, selectInput, option.testId, page.getByTestId('select-output'), option.label);
    }
});

test('Elementos básicos - Controles (Slider e Interruptor)', async ({ page }) => {
    const slider = page.getByTestId('range-input');
    await expect(slider).toBeVisible();

    await slider.evaluate((el: HTMLInputElement) => {
        el.value = '80';
        el.dispatchEvent(new Event('input', { bubbles: true }));
    });
    await expect(slider).toHaveValue('80');
    await expect(page.getByText('Intervalo: 80')).toBeVisible();

    const switchInput = page.getByTestId('toggle-switch');
    await expect(switchInput).toBeVisible();

    await switchInput.click();
    await expect(switchInput).toHaveAttribute(
        'aria-checked',
        'true'
    );

    await switchInput.click();
    await expect(switchInput).toHaveAttribute(
        'aria-checked',
        'false'
    );
});

test('Formulário Simples', async ({ page }) => {
    const section = page.getByTestId('section-formularios-simples');

    const fields = [
        { locator: section.getByTestId('input-name'), value: simpleFormData.nome },
        { locator: section.getByTestId('input-email'), value: simpleFormData.email },
        { locator: section.getByTestId('input-password'), value: simpleFormData.senha },
        { locator: section.getByTestId('input-confirm-password'), value: simpleFormData.confirmarSenha }
    ];

    const requiredErrors = [
        'Nome é obrigatório',
        'Email é obrigatório',
        'Senha é obrigatória',
        'Você deve aceitar os termos'
    ];

    const checkboxTerms = section.getByTestId('checkbox-terms');
    const submitButton = section.getByRole('button', { name: 'Enviar' });
    const clearButton = section.getByRole('button', { name: 'Limpar' });
    const successMessage = section.getByTestId('success-message');

    /// PREENCHIMENTO INICIAL, LIMPEZA DE CAMPOS E VALIDAÇÃO DE CAMPOS OBRIGATÓRIOS
    for (const field of fields) {
        await fillAndValidate(field.locator, field.value);
    }

    await checkboxTerms.check();

    await expect(clearButton).toBeVisible();
    await clearButton.click();

    for (const field of fields) {
        await expect(field.locator).toHaveValue('');
    }

    await submitButton.click();

    for (const error of requiredErrors) {
        await expect(
            section.getByText(error)
        ).toBeVisible();
    }

    /// PREENCHIMENTO FINAL, ENVIO DE FORMULÁRIO E VALIDAÇÃO DE CAMPOS OBRIGATÓRIOS
    for (const field of fields) {
        await fillAndValidate(field.locator, field.value);
    }

    await checkboxTerms.check();

    await expect(submitButton).toBeVisible();
    await submitButton.click();
    await expect(successMessage).toBeVisible();
});

test('Navegações entre páginas', async ({ page }) => {
    const section = page.getByTestId('section-navegacao-links');
    const currentPage = section.getByTestId('current-page');
    const historico = section.getByText(/Histórico:/);

    const githubLink = section.getByTestId('link-external');
    const expectedGitHubLink = 'https://github.com/qamichaelmaia';

    await expect(currentPage).toHaveText('Página Inicial');

    await navigateAndExpect(page, section, 'Ir para Sobre', 'Sobre');
    await navigateAndExpect(page, section, 'Ir para Contato', 'Contato');
    await navigateAndExpect(page, section, 'Ir para Produtos', 'Produtos');
    await navigateAndExpect(page, section, 'Voltar', 'Contato');
    await navigateAndExpect(page, section, 'Voltar', 'Sobre');
    await navigateAndExpect(page, section, 'Avançar', 'Contato');

    await expectHistorico(historico);

    await expect(githubLink).toBeVisible();
    await validateExternalLink(page, githubLink, expectedGitHubLink);
});

test('Alertas e modais', async ({ page }) => {
    const section = page.getByTestId('section-alertas-modais');

    const alertButton = section.getByTestId('button-alert');
    const promptButton = section.getByTestId('button-prompt');
    const confirmButton = section.getByTestId('button-confirm');
    const toastMessage = section.getByTestId('toast-message');

    const openModal = section.getByTestId('button-modal');
    const modal = page.getByTestId('custom-modal');
    const closeModalButton = modal.getByRole('button', { name: 'Fechar' });

    await validateDialog(page, alertButton, 'alert', 'Este é um alerta simples!');
    await validateDialog(page, promptButton, 'prompt', 'Digite seu nome:', 'Thalis Ferreira');
    await validateDialog(page, confirmButton, 'confirm', 'Você confirma esta ação?');
    await expect(toastMessage).toBeVisible();

    await validateCustomModal(openModal, modal, closeModalButton, 'Modal Customizado');
});

test('Checkboxes e radios', async ({ page }) => {
    const section = page.getByTestId('section-checkboxes-radios');

    const selectAll = section.getByTestId('checkbox-select-all');
    const option1 = section.getByTestId('checkbox-option1');
    const option2 = section.getByTestId('checkbox-option2');
    const option3 = section.getByTestId('checkbox-option3');
    const checkboxCount = section.getByTestId('checkbox-count');
    const radioCount = section.getByTestId('radio-output');
    const checkboxes = [
        option1,
        option2,
        option3
    ];

    await validateAllCheckboxes(checkboxes, false);
    await expectCheckedCount(checkboxCount, 0);

    await selectCheckbox(option1);
    await expectCheckedCount(checkboxCount, 1);

    await selectCheckbox(option2);
    await expectCheckedCount(checkboxCount, 2);

    await selectCheckbox(selectAll);
    await validateAllCheckboxes(checkboxes, true);
    await expectCheckedCount(checkboxCount, 3);

    await unselectCheckbox(selectAll);
    await validateAllCheckboxes(checkboxes, false);
    await expectCheckedCount(checkboxCount, 0);


    const radio1 = section.getByTestId('radio-radio1');
    const radio2 = section.getByTestId('radio-radio2');
    const radio3 = section.getByTestId('radio-radio3');

    await expect(radio1).not.toBeChecked();
    await expect(radio2).not.toBeChecked();
    await expect(radio3).not.toBeChecked();

    await radio1.check();
    await expectSelectedRadioCount(radioCount, 'radio1');
    await validateRadioSelection(
        radio1,
        [radio2, radio3]
    );

    await radio2.check();
    await expectSelectedRadioCount(radioCount, 'radio2');
    await validateRadioSelection(
        radio2,
        [radio1, radio3]
    );

    await radio3.check();
    await expectSelectedRadioCount(radioCount, 'radio3');
    await validateRadioSelection(
        radio3,
        [radio1, radio2]
    );
});