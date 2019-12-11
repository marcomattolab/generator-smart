import { element, by, ElementFinder } from 'protractor';

export class ImmobileComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-immobile div table .btn-danger'));
    title = element.all(by.css('jhi-immobile div h2#page-heading span')).first();

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

export class ImmobileUpdatePage {
    pageTitle = element(by.id('jhi-immobile-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    codiceInput = element(by.id('field_codice'));
    descrizioneInput = element(by.id('field_descrizione'));
    indirizzoInput = element(by.id('field_indirizzo'));
    capInput = element(by.id('field_cap'));
    regioneInput = element(by.id('field_regione'));
    provinciaInput = element(by.id('field_provincia'));
    cittaInput = element(by.id('field_citta'));
    noteInput = element(by.id('field_note'));
    dataCreazioneInput = element(by.id('field_dataCreazione'));
    dataModificaInput = element(by.id('field_dataModifica'));
    utenteCreazioneInput = element(by.id('field_utenteCreazione'));
    utenteModificaInput = element(by.id('field_utenteModifica'));
    pathFolderInput = element(by.id('field_pathFolder'));
    locazioneSelect = element(by.id('field_locazione'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCodiceInput(codice) {
        await this.codiceInput.sendKeys(codice);
    }

    async getCodiceInput() {
        return this.codiceInput.getAttribute('value');
    }

    async setDescrizioneInput(descrizione) {
        await this.descrizioneInput.sendKeys(descrizione);
    }

    async getDescrizioneInput() {
        return this.descrizioneInput.getAttribute('value');
    }

    async setIndirizzoInput(indirizzo) {
        await this.indirizzoInput.sendKeys(indirizzo);
    }

    async getIndirizzoInput() {
        return this.indirizzoInput.getAttribute('value');
    }

    async setCapInput(cap) {
        await this.capInput.sendKeys(cap);
    }

    async getCapInput() {
        return this.capInput.getAttribute('value');
    }

    async setRegioneInput(regione) {
        await this.regioneInput.sendKeys(regione);
    }

    async getRegioneInput() {
        return this.regioneInput.getAttribute('value');
    }

    async setProvinciaInput(provincia) {
        await this.provinciaInput.sendKeys(provincia);
    }

    async getProvinciaInput() {
        return this.provinciaInput.getAttribute('value');
    }

    async setCittaInput(citta) {
        await this.cittaInput.sendKeys(citta);
    }

    async getCittaInput() {
        return this.cittaInput.getAttribute('value');
    }

    async setNoteInput(note) {
        await this.noteInput.sendKeys(note);
    }

    async getNoteInput() {
        return this.noteInput.getAttribute('value');
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

    async setPathFolderInput(pathFolder) {
        await this.pathFolderInput.sendKeys(pathFolder);
    }

    async getPathFolderInput() {
        return this.pathFolderInput.getAttribute('value');
    }

    async locazioneSelectLastOption() {
        await this.locazioneSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async locazioneSelectOption(option) {
        await this.locazioneSelect.sendKeys(option);
    }

    getLocazioneSelect(): ElementFinder {
        return this.locazioneSelect;
    }

    async getLocazioneSelectedOption() {
        return this.locazioneSelect.element(by.css('option:checked')).getText();
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

export class ImmobileDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-immobile-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-immobile'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
