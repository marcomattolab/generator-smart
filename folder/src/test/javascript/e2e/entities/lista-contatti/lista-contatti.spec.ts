/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ListaContattiComponentsPage, ListaContattiDeleteDialog, ListaContattiUpdatePage } from './lista-contatti.page-object';

const expect = chai.expect;

describe('ListaContatti e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let listaContattiUpdatePage: ListaContattiUpdatePage;
    let listaContattiComponentsPage: ListaContattiComponentsPage;
    let listaContattiDeleteDialog: ListaContattiDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load ListaContattis', async () => {
        await navBarPage.goToEntity('lista-contatti');
        listaContattiComponentsPage = new ListaContattiComponentsPage();
        expect(await listaContattiComponentsPage.getTitle()).to.eq('myappjhApp.listaContatti.home.title');
    });

    it('should load create ListaContatti page', async () => {
        await listaContattiComponentsPage.clickOnCreateButton();
        listaContattiUpdatePage = new ListaContattiUpdatePage();
        expect(await listaContattiUpdatePage.getPageTitle()).to.eq('myappjhApp.listaContatti.home.createOrEditLabel');
        await listaContattiUpdatePage.cancel();
    });

    it('should create and save ListaContattis', async () => {
        const nbButtonsBeforeCreate = await listaContattiComponentsPage.countDeleteButtons();

        await listaContattiComponentsPage.clickOnCreateButton();
        await promise.all([
            listaContattiUpdatePage.setDateTimeInput('dateTime'),
            listaContattiUpdatePage.esitoSelectLastOption(),
            listaContattiUpdatePage.setMotivazioneInput('motivazione'),
            listaContattiUpdatePage.clienteSelectLastOption()
        ]);
        expect(await listaContattiUpdatePage.getDateTimeInput()).to.eq('dateTime');
        expect(await listaContattiUpdatePage.getMotivazioneInput()).to.eq('motivazione');
        await listaContattiUpdatePage.save();
        expect(await listaContattiUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await listaContattiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last ListaContatti', async () => {
        const nbButtonsBeforeDelete = await listaContattiComponentsPage.countDeleteButtons();
        await listaContattiComponentsPage.clickOnLastDeleteButton();

        listaContattiDeleteDialog = new ListaContattiDeleteDialog();
        expect(await listaContattiDeleteDialog.getDialogTitle()).to.eq('myappjhApp.listaContatti.delete.question');
        await listaContattiDeleteDialog.clickOnConfirmButton();

        expect(await listaContattiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
