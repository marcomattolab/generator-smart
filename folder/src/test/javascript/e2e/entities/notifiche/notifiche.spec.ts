/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { NotificheComponentsPage, NotificheDeleteDialog, NotificheUpdatePage } from './notifiche.page-object';

const expect = chai.expect;

describe('Notifiche e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let notificheUpdatePage: NotificheUpdatePage;
    let notificheComponentsPage: NotificheComponentsPage;
    let notificheDeleteDialog: NotificheDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Notifiches', async () => {
        await navBarPage.goToEntity('notifiche');
        notificheComponentsPage = new NotificheComponentsPage();
        expect(await notificheComponentsPage.getTitle()).to.eq('myappjhApp.notifiche.home.title');
    });

    it('should load create Notifiche page', async () => {
        await notificheComponentsPage.clickOnCreateButton();
        notificheUpdatePage = new NotificheUpdatePage();
        expect(await notificheUpdatePage.getPageTitle()).to.eq('myappjhApp.notifiche.home.createOrEditLabel');
        await notificheUpdatePage.cancel();
    });

    it('should create and save Notifiches', async () => {
        const nbButtonsBeforeCreate = await notificheComponentsPage.countDeleteButtons();

        await notificheComponentsPage.clickOnCreateButton();
        await promise.all([
            notificheUpdatePage.canaleNotificaSelectLastOption(),
            notificheUpdatePage.tipoNotificaSelectLastOption(),
            notificheUpdatePage.categoriaNotificaSelectLastOption(),
            notificheUpdatePage.setOggettoNotificaInput('oggettoNotifica'),
            notificheUpdatePage.setTestoNotificaInput('testoNotifica'),
            notificheUpdatePage.setDestinatariNorificaInput('destinatariNorifica'),
            notificheUpdatePage.esitoNotificaSelectLastOption(),
            notificheUpdatePage.setDataCreazioneInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            notificheUpdatePage.setDataModificaInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            notificheUpdatePage.setUtenteCreazioneInput('utenteCreazione'),
            notificheUpdatePage.setUtenteModificaInput('utenteModifica')
        ]);
        expect(await notificheUpdatePage.getOggettoNotificaInput()).to.eq('oggettoNotifica');
        expect(await notificheUpdatePage.getTestoNotificaInput()).to.eq('testoNotifica');
        expect(await notificheUpdatePage.getDestinatariNorificaInput()).to.eq('destinatariNorifica');
        expect(await notificheUpdatePage.getDataCreazioneInput()).to.contain('2001-01-01T02:30');
        expect(await notificheUpdatePage.getDataModificaInput()).to.contain('2001-01-01T02:30');
        expect(await notificheUpdatePage.getUtenteCreazioneInput()).to.eq('utenteCreazione');
        expect(await notificheUpdatePage.getUtenteModificaInput()).to.eq('utenteModifica');
        await notificheUpdatePage.save();
        expect(await notificheUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await notificheComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Notifiche', async () => {
        const nbButtonsBeforeDelete = await notificheComponentsPage.countDeleteButtons();
        await notificheComponentsPage.clickOnLastDeleteButton();

        notificheDeleteDialog = new NotificheDeleteDialog();
        expect(await notificheDeleteDialog.getDialogTitle()).to.eq('myappjhApp.notifiche.delete.question');
        await notificheDeleteDialog.clickOnConfirmButton();

        expect(await notificheComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
