/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ClienteComponentsPage, ClienteDeleteDialog, ClienteUpdatePage } from './cliente.page-object';

const expect = chai.expect;

describe('Cliente e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let clienteUpdatePage: ClienteUpdatePage;
    let clienteComponentsPage: ClienteComponentsPage;
    let clienteDeleteDialog: ClienteDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Clientes', async () => {
        await navBarPage.goToEntity('cliente');
        clienteComponentsPage = new ClienteComponentsPage();
        expect(await clienteComponentsPage.getTitle()).to.eq('myappjhApp.cliente.home.title');
    });

    it('should load create Cliente page', async () => {
        await clienteComponentsPage.clickOnCreateButton();
        clienteUpdatePage = new ClienteUpdatePage();
        expect(await clienteUpdatePage.getPageTitle()).to.eq('myappjhApp.cliente.home.createOrEditLabel');
        await clienteUpdatePage.cancel();
    });

    it('should create and save Clientes', async () => {
        const nbButtonsBeforeCreate = await clienteComponentsPage.countDeleteButtons();

        await clienteComponentsPage.clickOnCreateButton();
        await promise.all([
            clienteUpdatePage.setNomeInput('nome'),
            clienteUpdatePage.setCognomeInput('cognome'),
            clienteUpdatePage.alertCompleannoSelectLastOption(),
            clienteUpdatePage.setDataNascitaInput('2000-12-31'),
            clienteUpdatePage.setMeseNascitaInput('meseNascita'),
            clienteUpdatePage.setTelefonoInput('telefono'),
            clienteUpdatePage.setEmailInput('email'),
            clienteUpdatePage.setIndirizzoInput('indirizzo'),
            clienteUpdatePage.setCapInput('cap'),
            clienteUpdatePage.setRegioneInput('regione'),
            clienteUpdatePage.setProvinciaInput('provincia'),
            clienteUpdatePage.setCittaInput('citta'),
            clienteUpdatePage.setNoteInput('note'),
            clienteUpdatePage.tipoClienteSelectLastOption(),
            clienteUpdatePage.setDataCreazioneInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            clienteUpdatePage.setDataModificaInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            clienteUpdatePage.setUtenteCreazioneInput('utenteCreazione'),
            clienteUpdatePage.setUtenteModificaInput('utenteModifica')
        ]);
        expect(await clienteUpdatePage.getNomeInput()).to.eq('nome');
        expect(await clienteUpdatePage.getCognomeInput()).to.eq('cognome');
        expect(await clienteUpdatePage.getDataNascitaInput()).to.eq('2000-12-31');
        expect(await clienteUpdatePage.getMeseNascitaInput()).to.eq('meseNascita');
        expect(await clienteUpdatePage.getTelefonoInput()).to.eq('telefono');
        expect(await clienteUpdatePage.getEmailInput()).to.eq('email');
        expect(await clienteUpdatePage.getIndirizzoInput()).to.eq('indirizzo');
        expect(await clienteUpdatePage.getCapInput()).to.eq('cap');
        expect(await clienteUpdatePage.getRegioneInput()).to.eq('regione');
        expect(await clienteUpdatePage.getProvinciaInput()).to.eq('provincia');
        expect(await clienteUpdatePage.getCittaInput()).to.eq('citta');
        expect(await clienteUpdatePage.getNoteInput()).to.eq('note');
        const selectedAbilitato = clienteUpdatePage.getAbilitatoInput();
        if (await selectedAbilitato.isSelected()) {
            await clienteUpdatePage.getAbilitatoInput().click();
            expect(await clienteUpdatePage.getAbilitatoInput().isSelected()).to.be.false;
        } else {
            await clienteUpdatePage.getAbilitatoInput().click();
            expect(await clienteUpdatePage.getAbilitatoInput().isSelected()).to.be.true;
        }
        expect(await clienteUpdatePage.getDataCreazioneInput()).to.contain('2001-01-01T02:30');
        expect(await clienteUpdatePage.getDataModificaInput()).to.contain('2001-01-01T02:30');
        expect(await clienteUpdatePage.getUtenteCreazioneInput()).to.eq('utenteCreazione');
        expect(await clienteUpdatePage.getUtenteModificaInput()).to.eq('utenteModifica');
        await clienteUpdatePage.save();
        expect(await clienteUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await clienteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Cliente', async () => {
        const nbButtonsBeforeDelete = await clienteComponentsPage.countDeleteButtons();
        await clienteComponentsPage.clickOnLastDeleteButton();

        clienteDeleteDialog = new ClienteDeleteDialog();
        expect(await clienteDeleteDialog.getDialogTitle()).to.eq('myappjhApp.cliente.delete.question');
        await clienteDeleteDialog.clickOnConfirmButton();

        expect(await clienteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
