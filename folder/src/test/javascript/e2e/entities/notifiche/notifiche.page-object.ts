import { element, by, ElementFinder } from 'protractor';

export class NotificheComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-notifiche div table .btn-danger'));
    title = element.all(by.css('jhi-notifiche div h2#page-heading span')).first();

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

export class NotificheUpdatePage {
    pageTitle = element(by.id('jhi-notifiche-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    canaleNotificaSelect = element(by.id('field_canaleNotifica'));
    tipoNotificaSelect = element(by.id('field_tipoNotifica'));
    categoriaNotificaSelect = element(by.id('field_categoriaNotifica'));
    oggettoNotificaInput = element(by.id('field_oggettoNotifica'));
    testoNotificaInput = element(by.id('field_testoNotifica'));
    destinatariNorificaInput = element(by.id('field_destinatariNorifica'));
    esitoNotificaSelect = element(by.id('field_esitoNotifica'));
    dataCreazioneInput = element(by.id('field_dataCreazione'));
    dataModificaInput = element(by.id('field_dataModifica'));
    utenteCreazioneInput = element(by.id('field_utenteCreazione'));
    utenteModificaInput = element(by.id('field_utenteModifica'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCanaleNotificaSelect(canaleNotifica) {
        await this.canaleNotificaSelect.sendKeys(canaleNotifica);
    }

    async getCanaleNotificaSelect() {
        return this.canaleNotificaSelect.element(by.css('option:checked')).getText();
    }

    async canaleNotificaSelectLastOption() {
        await this.canaleNotificaSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setTipoNotificaSelect(tipoNotifica) {
        await this.tipoNotificaSelect.sendKeys(tipoNotifica);
    }

    async getTipoNotificaSelect() {
        return this.tipoNotificaSelect.element(by.css('option:checked')).getText();
    }

    async tipoNotificaSelectLastOption() {
        await this.tipoNotificaSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setCategoriaNotificaSelect(categoriaNotifica) {
        await this.categoriaNotificaSelect.sendKeys(categoriaNotifica);
    }

    async getCategoriaNotificaSelect() {
        return this.categoriaNotificaSelect.element(by.css('option:checked')).getText();
    }

    async categoriaNotificaSelectLastOption() {
        await this.categoriaNotificaSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setOggettoNotificaInput(oggettoNotifica) {
        await this.oggettoNotificaInput.sendKeys(oggettoNotifica);
    }

    async getOggettoNotificaInput() {
        return this.oggettoNotificaInput.getAttribute('value');
    }

    async setTestoNotificaInput(testoNotifica) {
        await this.testoNotificaInput.sendKeys(testoNotifica);
    }

    async getTestoNotificaInput() {
        return this.testoNotificaInput.getAttribute('value');
    }

    async setDestinatariNorificaInput(destinatariNorifica) {
        await this.destinatariNorificaInput.sendKeys(destinatariNorifica);
    }

    async getDestinatariNorificaInput() {
        return this.destinatariNorificaInput.getAttribute('value');
    }

    async setEsitoNotificaSelect(esitoNotifica) {
        await this.esitoNotificaSelect.sendKeys(esitoNotifica);
    }

    async getEsitoNotificaSelect() {
        return this.esitoNotificaSelect.element(by.css('option:checked')).getText();
    }

    async esitoNotificaSelectLastOption() {
        await this.esitoNotificaSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setDataCreazioneInput(dataCreazione) {
        await this.dataCreazioneInput.sendKeys(dataCreazione);
    }

    async getDataCreazioneInput() {
        return this.dataCreazioneInput.getAttribute('value');
    }

    async setDataModificaInput(dataModifica) {
        await this.dataModificaInput.sendKeys(dataModifica);
    }

    async getDataModificaInput() {
        return this.dataModificaInput.getAttribute('value');
    }

    async setUtenteCreazioneInput(utenteCreazione) {
        await this.utenteCreazioneInput.sendKeys(utenteCreazione);
    }

    async getUtenteCreazioneInput() {
        return this.utenteCreazioneInput.getAttribute('value');
    }

    async setUtenteModificaInput(utenteModifica) {
        await this.utenteModificaInput.sendKeys(utenteModifica);
    }

    async getUtenteModificaInput() {
        return this.utenteModificaInput.getAttribute('value');
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

export class NotificheDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-notifiche-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-notifiche'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
