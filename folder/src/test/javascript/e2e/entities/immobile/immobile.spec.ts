/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ImmobileComponentsPage, ImmobileDeleteDialog, ImmobileUpdatePage } from './immobile.page-object';

const expect = chai.expect;

describe('Immobile e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let immobileUpdatePage: ImmobileUpdatePage;
    let immobileComponentsPage: ImmobileComponentsPage;
    let immobileDeleteDialog: ImmobileDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Immobiles', async () => {
        await navBarPage.goToEntity('immobile');
        immobileComponentsPage = new ImmobileComponentsPage();
        expect(await immobileComponentsPage.getTitle()).to.eq('myappjhApp.immobile.home.title');
    });

    it('should load create Immobile page', async () => {
        await immobileComponentsPage.clickOnCreateButton();
        immobileUpdatePage = new ImmobileUpdatePage();
        expect(await immobileUpdatePage.getPageTitle()).to.eq('myappjhApp.immobile.home.createOrEditLabel');
        await immobileUpdatePage.cancel();
    });

    it('should create and save Immobiles', async () => {
        const nbButtonsBeforeCreate = await immobileComponentsPage.countDeleteButtons();

        await immobileComponentsPage.clickOnCreateButton();
        await promise.all([
            immobileUpdatePage.setCodiceInput('codice'),
            immobileUpdatePage.setDescrizioneInput('descrizione'),
            immobileUpdatePage.setIndirizzoInput('indirizzo'),
            immobileUpdatePage.setCapInput('cap'),
            immobileUpdatePage.setRegioneInput('regione'),
            immobileUpdatePage.setProvinciaInput('provincia'),
            immobileUpdatePage.setCittaInput('citta'),
            immobileUpdatePage.setNoteInput('note'),
            immobileUpdatePage.setDataCreazioneInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            immobileUpdatePage.setDataModificaInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            immobileUpdatePage.setUtenteCreazioneInput('utenteCreazione'),
            immobileUpdatePage.setUtenteModificaInput('utenteModifica'),
            immobileUpdatePage.setPathFolderInput('pathFolder'),
            immobileUpdatePage.locazioneSelectLastOption()
        ]);
        expect(await immobileUpdatePage.getCodiceInput()).to.eq('codice');
        expect(await immobileUpdatePage.getDescrizioneInput()).to.eq('descrizione');
        expect(await immobileUpdatePage.getIndirizzoInput()).to.eq('indirizzo');
        expect(await immobileUpdatePage.getCapInput()).to.eq('cap');
        expect(await immobileUpdatePage.getRegioneInput()).to.eq('regione');
        expect(await immobileUpdatePage.getProvinciaInput()).to.eq('provincia');
        expect(await immobileUpdatePage.getCittaInput()).to.eq('citta');
        expect(await immobileUpdatePage.getNoteInput()).to.eq('note');
        expect(await immobileUpdatePage.getDataCreazioneInput()).to.contain('2001-01-01T02:30');
        expect(await immobileUpdatePage.getDataModificaInput()).to.contain('2001-01-01T02:30');
        expect(await immobileUpdatePage.getUtenteCreazioneInput()).to.eq('utenteCreazione');
        expect(await immobileUpdatePage.getUtenteModificaInput()).to.eq('utenteModifica');
        expect(await immobileUpdatePage.getPathFolderInput()).to.eq('pathFolder');
        await immobileUpdatePage.save();
        expect(await immobileUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await immobileComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Immobile', async () => {
        const nbButtonsBeforeDelete = await immobileComponentsPage.countDeleteButtons();
        await immobileComponentsPage.clickOnLastDeleteButton();

        immobileDeleteDialog = new ImmobileDeleteDialog();
        expect(await immobileDeleteDialog.getDialogTitle()).to.eq('myappjhApp.immobile.delete.question');
        await immobileDeleteDialog.clickOnConfirmButton();

        expect(await immobileComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
