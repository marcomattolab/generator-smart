/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { FilesComponentsPage, FilesDeleteDialog, FilesUpdatePage } from './files.page-object';

const expect = chai.expect;

describe('Files e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let filesUpdatePage: FilesUpdatePage;
    let filesComponentsPage: FilesComponentsPage;
    let filesDeleteDialog: FilesDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Files', async () => {
        await navBarPage.goToEntity('files');
        filesComponentsPage = new FilesComponentsPage();
        expect(await filesComponentsPage.getTitle()).to.eq('myappjhApp.files.home.title');
    });

    it('should load create Files page', async () => {
        await filesComponentsPage.clickOnCreateButton();
        filesUpdatePage = new FilesUpdatePage();
        expect(await filesUpdatePage.getPageTitle()).to.eq('myappjhApp.files.home.createOrEditLabel');
        await filesUpdatePage.cancel();
    });

    it('should create and save Files', async () => {
        const nbButtonsBeforeCreate = await filesComponentsPage.countDeleteButtons();

        await filesComponentsPage.clickOnCreateButton();
        await promise.all([
            filesUpdatePage.setNomeInput('nome'),
            filesUpdatePage.setDimensioneInput('dimensione'),
            filesUpdatePage.setEstensioneInput('estensione'),
            filesUpdatePage.setDataCreazioneInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            filesUpdatePage.setDataModificaInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            filesUpdatePage.setUtenteCreazioneInput('utenteCreazione'),
            filesUpdatePage.setUtenteModificaInput('utenteModifica'),
            filesUpdatePage.immobileSelectLastOption()
        ]);
        expect(await filesUpdatePage.getNomeInput()).to.eq('nome');
        expect(await filesUpdatePage.getDimensioneInput()).to.eq('dimensione');
        expect(await filesUpdatePage.getEstensioneInput()).to.eq('estensione');
        expect(await filesUpdatePage.getDataCreazioneInput()).to.contain('2001-01-01T02:30');
        expect(await filesUpdatePage.getDataModificaInput()).to.contain('2001-01-01T02:30');
        expect(await filesUpdatePage.getUtenteCreazioneInput()).to.eq('utenteCreazione');
        expect(await filesUpdatePage.getUtenteModificaInput()).to.eq('utenteModifica');
        await filesUpdatePage.save();
        expect(await filesUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await filesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Files', async () => {
        const nbButtonsBeforeDelete = await filesComponentsPage.countDeleteButtons();
        await filesComponentsPage.clickOnLastDeleteButton();

        filesDeleteDialog = new FilesDeleteDialog();
        expect(await filesDeleteDialog.getDialogTitle()).to.eq('myappjhApp.files.delete.question');
        await filesDeleteDialog.clickOnConfirmButton();

        expect(await filesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
