import { element, by, ElementFinder } from 'protractor';

export class ListaContattiComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-lista-contatti div table .btn-danger'));
    title = element.all(by.css('jhi-lista-contatti div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ListaContattiUpdatePage {
    pageTitle = element(by.id('jhi-lista-contatti-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    dateTimeInput = element(by.id('field_dateTime'));
    esitoSelect = element(by.id('field_esito'));
    motivazioneInput = element(by.id('field_motivazione'));
    clienteSelect = element(by.id('field_cliente'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setDateTimeInput(dateTime) {
        await this.dateTimeInput.sendKeys(dateTime);
    }

    async getDateTimeInput() {
        return this.dateTimeInput.getAttribute('value');
    }

    async setEsitoSelect(esito) {
        await this.esitoSelect.sendKeys(esito);
    }

    async getEsitoSelect() {
        return this.esitoSelect.element(by.css('option:checked')).getText();
    }

    async esitoSelectLastOption() {
        await this.esitoSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setMotivazioneInput(motivazione) {
        await this.motivazioneInput.sendKeys(motivazione);
    }

    async getMotivazioneInput() {
        return this.motivazioneInput.getAttribute('value');
    }

    async clienteSelectLastOption() {
        await this.clienteSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async clienteSelectOption(option) {
        await this.clienteSelect.sendKeys(option);
    }

    getClienteSelect(): ElementFinder {
        return this.clienteSelect;
    }

    async getClienteSelectedOption() {
        return this.clienteSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class ListaContattiDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-listaContatti-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-listaContatti'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
