import { element, by, ElementFinder } from 'protractor';

export class IncaricoComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-incarico div table .btn-danger'));
    title = element.all(by.css('jhi-incarico div h2#page-heading span')).first();

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

export class IncaricoUpdatePage {
    pageTitle = element(by.id('jhi-incarico-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    riferimentoInput = element(by.id('field_riferimento'));
    tipoSelect = element(by.id('field_tipo'));
    statoSelect = element(by.id('field_stato'));
    agenteInput = element(by.id('field_agente'));
    agenteDiContattoInput = element(by.id('field_agenteDiContatto'));
    dataContattoInput = element(by.id('field_dataContatto'));
    noteTrattativaInput = element(by.id('field_noteTrattativa'));
    datiFiscaliInput = element(by.id('field_datiFiscali'));
    ruoloSelect = element(by.id('field_ruolo'));
    alertFiscaliSelect = element(by.id('field_alertFiscali'));
    alertCortesiaSelect = element(by.id('field_alertCortesia'));
    oscuraIncaricoInput = element(by.id('field_oscuraIncarico'));
    dataCreazioneInput = element(by.id('field_dataCreazione'));
    dataModificaInput = element(by.id('field_dataModifica'));
    utenteCreazioneInput = element(by.id('field_utenteCreazione'));
    utenteModificaInput = element(by.id('field_utenteModifica'));
    immobileSelect = element(by.id('field_immobile'));
    partnerSelect = element(by.id('field_partner'));
    clienteSelect = element(by.id('field_cliente'));
    acquirenteSelect = element(by.id('field_acquirente'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setRiferimentoInput(riferimento) {
        await this.riferimentoInput.sendKeys(riferimento);
    }

    async getRiferimentoInput() {
        return this.riferimentoInput.getAttribute('value');
    }

    async setTipoSelect(tipo) {
        await this.tipoSelect.sendKeys(tipo);
    }

    async getTipoSelect() {
        return this.tipoSelect.element(by.css('option:checked')).getText();
    }

    async tipoSelectLastOption() {
        await this.tipoSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setStatoSelect(stato) {
        await this.statoSelect.sendKeys(stato);
    }

    async getStatoSelect() {
        return this.statoSelect.element(by.css('option:checked')).getText();
    }

    async statoSelectLastOption() {
        await this.statoSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setAgenteInput(agente) {
        await this.agenteInput.sendKeys(agente);
    }

    async getAgenteInput() {
        return this.agenteInput.getAttribute('value');
    }

    async setAgenteDiContattoInput(agenteDiContatto) {
        await this.agenteDiContattoInput.sendKeys(agenteDiContatto);
    }

    async getAgenteDiContattoInput() {
        return this.agenteDiContattoInput.getAttribute('value');
    }

    async setDataContattoInput(dataContatto) {
        await this.dataContattoInput.sendKeys(dataContatto);
    }

    async getDataContattoInput() {
        return this.dataContattoInput.getAttribute('value');
    }

    async setNoteTrattativaInput(noteTrattativa) {
        await this.noteTrattativaInput.sendKeys(noteTrattativa);
    }

    async getNoteTrattativaInput() {
        return this.noteTrattativaInput.getAttribute('value');
    }

    async setDatiFiscaliInput(datiFiscali) {
        await this.datiFiscaliInput.sendKeys(datiFiscali);
    }

    async getDatiFiscaliInput() {
        return this.datiFiscaliInput.getAttribute('value');
    }

    async setRuoloSelect(ruolo) {
        await this.ruoloSelect.sendKeys(ruolo);
    }

    async getRuoloSelect() {
        return this.ruoloSelect.element(by.css('option:checked')).getText();
    }

    async ruoloSelectLastOption() {
        await this.ruoloSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setAlertFiscaliSelect(alertFiscali) {
        await this.alertFiscaliSelect.sendKeys(alertFiscali);
    }

    async getAlertFiscaliSelect() {
        return this.alertFiscaliSelect.element(by.css('option:checked')).getText();
    }

    async alertFiscaliSelectLastOption() {
        await this.alertFiscaliSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setAlertCortesiaSelect(alertCortesia) {
        await this.alertCortesiaSelect.sendKeys(alertCortesia);
    }

    async getAlertCortesiaSelect() {
        return this.alertCortesiaSelect.element(by.css('option:checked')).getText();
    }

    async alertCortesiaSelectLastOption() {
        await this.alertCortesiaSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    getOscuraIncaricoInput() {
        return this.oscuraIncaricoInput;
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

    async immobileSelectLastOption() {
        await this.immobileSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async immobileSelectOption(option) {
        await this.immobileSelect.sendKeys(option);
    }

    getImmobileSelect(): ElementFinder {
        return this.immobileSelect;
    }

    async getImmobileSelectedOption() {
        return this.immobileSelect.element(by.css('option:checked')).getText();
    }

    async partnerSelectLastOption() {
        await this.partnerSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async partnerSelectOption(option) {
        await this.partnerSelect.sendKeys(option);
    }

    getPartnerSelect(): ElementFinder {
        return this.partnerSelect;
    }

    async getPartnerSelectedOption() {
        return this.partnerSelect.element(by.css('option:checked')).getText();
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

    async acquirenteSelectLastOption() {
        await this.acquirenteSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async acquirenteSelectOption(option) {
        await this.acquirenteSelect.sendKeys(option);
    }

    getAcquirenteSelect(): ElementFinder {
        return this.acquirenteSelect;
    }

    async getAcquirenteSelectedOption() {
        return this.acquirenteSelect.element(by.css('option:checked')).getText();
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

export class IncaricoDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-incarico-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-incarico'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
