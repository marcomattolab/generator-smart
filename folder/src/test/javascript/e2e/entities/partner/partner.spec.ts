/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PartnerComponentsPage, PartnerDeleteDialog, PartnerUpdatePage } from './partner.page-object';

const expect = chai.expect;

describe('Partner e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let partnerUpdatePage: PartnerUpdatePage;
    let partnerComponentsPage: PartnerComponentsPage;
    let partnerDeleteDialog: PartnerDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Partners', async () => {
        await navBarPage.goToEntity('partner');
        partnerComponentsPage = new PartnerComponentsPage();
        expect(await partnerComponentsPage.getTitle()).to.eq('myappjhApp.partner.home.title');
    });

    it('should load create Partner page', async () => {
        await partnerComponentsPage.clickOnCreateButton();
        partnerUpdatePage = new PartnerUpdatePage();
        expect(await partnerUpdatePage.getPageTitle()).to.eq('myappjhApp.partner.home.createOrEditLabel');
        await partnerUpdatePage.cancel();
    });

    it('should create and save Partners', async () => {
        const nbButtonsBeforeCreate = await partnerComponentsPage.countDeleteButtons();

        await partnerComponentsPage.clickOnCreateButton();
        await promise.all([
            partnerUpdatePage.setNomeInput('nome'),
            partnerUpdatePage.setCognomeInput('cognome'),
            partnerUpdatePage.professioneSelectLastOption(),
            partnerUpdatePage.setTelefonoInput('telefono'),
            partnerUpdatePage.setCellulareInput('cellulare'),
            partnerUpdatePage.setEmailInput('email'),
            partnerUpdatePage.tipoIndirizzoSelectLastOption(),
            partnerUpdatePage.setIndirizzoInput('indirizzo'),
            partnerUpdatePage.setCapInput('cap'),
            partnerUpdatePage.setRegioneInput('regione'),
            partnerUpdatePage.setProvinciaInput('provincia'),
            partnerUpdatePage.setCittaInput('citta'),
            partnerUpdatePage.setNoteInput('note'),
            partnerUpdatePage.setDataCreazioneInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            partnerUpdatePage.setDataModificaInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            partnerUpdatePage.setUtenteCreazioneInput('utenteCreazione'),
            partnerUpdatePage.setUtenteModificaInput('utenteModifica')
        ]);
        expect(await partnerUpdatePage.getNomeInput()).to.eq('nome');
        expect(await partnerUpdatePage.getCognomeInput()).to.eq('cognome');
        expect(await partnerUpdatePage.getTelefonoInput()).to.eq('telefono');
        expect(await partnerUpdatePage.getCellulareInput()).to.eq('cellulare');
        expect(await partnerUpdatePage.getEmailInput()).to.eq('email');
        expect(await partnerUpdatePage.getIndirizzoInput()).to.eq('indirizzo');
        expect(await partnerUpdatePage.getCapInput()).to.eq('cap');
        expect(await partnerUpdatePage.getRegioneInput()).to.eq('regione');
        expect(await partnerUpdatePage.getProvinciaInput()).to.eq('provincia');
        expect(await partnerUpdatePage.getCittaInput()).to.eq('citta');
        expect(await partnerUpdatePage.getNoteInput()).to.eq('note');
        const selectedAbilitato = partnerUpdatePage.getAbilitatoInput();
        if (await selectedAbilitato.isSelected()) {
            await partnerUpdatePage.getAbilitatoInput().click();
            expect(await partnerUpdatePage.getAbilitatoInput().isSelected()).to.be.false;
        } else {
            await partnerUpdatePage.getAbilitatoInput().click();
            expect(await partnerUpdatePage.getAbilitatoInput().isSelected()).to.be.true;
        }
        expect(await partnerUpdatePage.getDataCreazioneInput()).to.contain('2001-01-01T02:30');
        expect(await partnerUpdatePage.getDataModificaInput()).to.contain('2001-01-01T02:30');
        expect(await partnerUpdatePage.getUtenteCreazioneInput()).to.eq('utenteCreazione');
        expect(await partnerUpdatePage.getUtenteModificaInput()).to.eq('utenteModifica');
        await partnerUpdatePage.save();
        expect(await partnerUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await partnerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Partner', async () => {
        const nbButtonsBeforeDelete = await partnerComponentsPage.countDeleteButtons();
        await partnerComponentsPage.clickOnLastDeleteButton();

        partnerDeleteDialog = new PartnerDeleteDialog();
        expect(await partnerDeleteDialog.getDialogTitle()).to.eq('myappjhApp.partner.delete.question');
        await partnerDeleteDialog.clickOnConfirmButton();

        expect(await partnerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
