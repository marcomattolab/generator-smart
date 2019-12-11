import { element, by, ElementFinder } from 'protractor';

export class PartnerComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-partner div table .btn-danger'));
    title = element.all(by.css('jhi-partner div h2#page-heading span')).first();

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

export class PartnerUpdatePage {
    pageTitle = element(by.id('jhi-partner-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nomeInput = element(by.id('field_nome'));
    cognomeInput = element(by.id('field_cognome'));
    professioneSelect = element(by.id('field_professione'));
    telefonoInput = element(by.id('field_telefono'));
    cellulareInput = element(by.id('field_cellulare'));
    emailInput = element(by.id('field_email'));
    tipoIndirizzoSelect = element(by.id('field_tipoIndirizzo'));
    indirizzoInput = element(by.id('field_indirizzo'));
    capInput = element(by.id('field_cap'));
    regioneInput = element(by.id('field_regione'));
    provinciaInput = element(by.id('field_provincia'));
    cittaInput = element(by.id('field_citta'));
    noteInput = element(by.id('field_note'));
    abilitatoInput = element(by.id('field_abilitato'));
    dataCreazioneInput = element(by.id('field_dataCreazione'));
    dataModificaInput = element(by.id('field_dataModifica'));
    utenteCreazioneInput = element(by.id('field_utenteCreazione'));
    utenteModificaInput = element(by.id('field_utenteModifica'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNomeInput(nome) {
        await this.nomeInput.sendKeys(nome);
    }

    async getNomeInput() {
        return this.nomeInput.getAttribute('value');
    }

    async setCognomeInput(cognome) {
        await this.cognomeInput.sendKeys(cognome);
    }

    async getCognomeInput() {
        return this.cognomeInput.getAttribute('value');
    }

    async setProfessioneSelect(professione) {
        await this.professioneSelect.sendKeys(professione);
    }

    async getProfessioneSelect() {
        return this.professioneSelect.element(by.css('option:checked')).getText();
    }

    async professioneSelectLastOption() {
        await this.professioneSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setTelefonoInput(telefono) {
        await this.telefonoInput.sendKeys(telefono);
    }

    async getTelefonoInput() {
        return this.telefonoInput.getAttribute('value');
    }

    async setCellulareInput(cellulare) {
        await this.cellulareInput.sendKeys(cellulare);
    }

    async getCellulareInput() {
        return this.cellulareInput.getAttribute('value');
    }

    async setEmailInput(email) {
        await this.emailInput.sendKeys(email);
    }

    async getEmailInput() {
        return this.emailInput.getAttribute('value');
    }

    async setTipoIndirizzoSelect(tipoIndirizzo) {
        await this.tipoIndirizzoSelect.sendKeys(tipoIndirizzo);
    }

    async getTipoIndirizzoSelect() {
        return this.tipoIndirizzoSelect.element(by.css('option:checked')).getText();
    }

    async tipoIndirizzoSelectLastOption() {
        await this.tipoIndirizzoSelect
            .all(by.tagName('option'))
            .last()
            .click();
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

    getAbilitatoInput() {
        return this.abilitatoInput;
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

export class PartnerDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-partner-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-partner'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
