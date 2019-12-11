import { element, by, ElementFinder } from 'protractor';

export class GeolocalizzazioneComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-geolocalizzazione div table .btn-danger'));
    title = element.all(by.css('jhi-geolocalizzazione div h2#page-heading span')).first();

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

export class GeolocalizzazioneUpdatePage {
    pageTitle = element(by.id('jhi-geolocalizzazione-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    immobileInput = element(by.id('field_immobile'));
    latitudineInput = element(by.id('field_latitudine'));
    longitudineInput = element(by.id('field_longitudine'));
    dataCreazioneInput = element(by.id('field_dataCreazione'));
    dataModificaInput = element(by.id('field_dataModifica'));
    utenteCreazioneInput = element(by.id('field_utenteCreazione'));
    utenteModificaInput = element(by.id('field_utenteModifica'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setImmobileInput(immobile) {
        await this.immobileInput.sendKeys(immobile);
    }

    async getImmobileInput() {
        return this.immobileInput.getAttribute('value');
    }

    async setLatitudineInput(latitudine) {
        await this.latitudineInput.sendKeys(latitudine);
    }

    async getLatitudineInput() {
        return this.latitudineInput.getAttribute('value');
    }

    async setLongitudineInput(longitudine) {
        await this.longitudineInput.sendKeys(longitudine);
    }

    async getLongitudineInput() {
        return this.longitudineInput.getAttribute('value');
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

export class GeolocalizzazioneDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-geolocalizzazione-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-geolocalizzazione'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
