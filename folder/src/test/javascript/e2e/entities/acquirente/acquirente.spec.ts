/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AcquirenteComponentsPage, AcquirenteDeleteDialog, AcquirenteUpdatePage } from './acquirente.page-object';

const expect = chai.expect;

describe('Acquirente e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let acquirenteUpdatePage: AcquirenteUpdatePage;
    let acquirenteComponentsPage: AcquirenteComponentsPage;
    let acquirenteDeleteDialog: AcquirenteDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Acquirentes', async () => {
        await navBarPage.goToEntity('acquirente');
        acquirenteComponentsPage = new AcquirenteComponentsPage();
        expect(await acquirenteComponentsPage.getTitle()).to.eq('myappjhApp.acquirente.home.title');
    });

    it('should load create Acquirente page', async () => {
        await acquirenteComponentsPage.clickOnCreateButton();
        acquirenteUpdatePage = new AcquirenteUpdatePage();
        expect(await acquirenteUpdatePage.getPageTitle()).to.eq('myappjhApp.acquirente.home.createOrEditLabel');
        await acquirenteUpdatePage.cancel();
    });

    it('should create and save Acquirentes', async () => {
        const nbButtonsBeforeCreate = await acquirenteComponentsPage.countDeleteButtons();

        await acquirenteComponentsPage.clickOnCreateButton();
        await promise.all([
            acquirenteUpdatePage.setNomeInput('nome'),
            acquirenteUpdatePage.setCognomeInput('cognome'),
            acquirenteUpdatePage.alertCompleannoSelectLastOption(),
            acquirenteUpdatePage.setDataNascitaInput('2000-12-31'),
            acquirenteUpdatePage.setMeseNascitaInput('meseNascita'),
            acquirenteUpdatePage.setTelefonoInput('telefono'),
            acquirenteUpdatePage.setEmailInput('email'),
            acquirenteUpdatePage.setIndirizzoInput('indirizzo'),
            acquirenteUpdatePage.setCapInput('cap'),
            acquirenteUpdatePage.setRegioneInput('regione'),
            acquirenteUpdatePage.setProvinciaInput('provincia'),
            acquirenteUpdatePage.setCittaInput('citta'),
            acquirenteUpdatePage.setNoteInput('note'),
            acquirenteUpdatePage.tipoClienteSelectLastOption(),
            acquirenteUpdatePage.setDataCreazioneInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            acquirenteUpdatePage.setDataModificaInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            acquirenteUpdatePage.setUtenteCreazioneInput('utenteCreazione'),
            acquirenteUpdatePage.setUtenteModificaInput('utenteModifica')
        ]);
        expect(await acquirenteUpdatePage.getNomeInput()).to.eq('nome');
        expect(await acquirenteUpdatePage.getCognomeInput()).to.eq('cognome');
        expect(await acquirenteUpdatePage.getDataNascitaInput()).to.eq('2000-12-31');
        expect(await acquirenteUpdatePage.getMeseNascitaInput()).to.eq('meseNascita');
        expect(await acquirenteUpdatePage.getTelefonoInput()).to.eq('telefono');
        expect(await acquirenteUpdatePage.getEmailInput()).to.eq('email');
        expect(await acquirenteUpdatePage.getIndirizzoInput()).to.eq('indirizzo');
        expect(await acquirenteUpdatePage.getCapInput()).to.eq('cap');
        expect(await acquirenteUpdatePage.getRegioneInput()).to.eq('regione');
        expect(await acquirenteUpdatePage.getProvinciaInput()).to.eq('provincia');
        expect(await acquirenteUpdatePage.getCittaInput()).to.eq('citta');
        expect(await acquirenteUpdatePage.getNoteInput()).to.eq('note');
        const selectedAbilitato = acquirenteUpdatePage.getAbilitatoInput();
        if (await selectedAbilitato.isSelected()) {
            await acquirenteUpdatePage.getAbilitatoInput().click();
            expect(await acquirenteUpdatePage.getAbilitatoInput().isSelected()).to.be.false;
        } else {
            await acquirenteUpdatePage.getAbilitatoInput().click();
            expect(await acquirenteUpdatePage.getAbilitatoInput().isSelected()).to.be.true;
        }
        expect(await acquirenteUpdatePage.getDataCreazioneInput()).to.contain('2001-01-01T02:30');
        expect(await acquirenteUpdatePage.getDataModificaInput()).to.contain('2001-01-01T02:30');
        expect(await acquirenteUpdatePage.getUtenteCreazioneInput()).to.eq('utenteCreazione');
        expect(await acquirenteUpdatePage.getUtenteModificaInput()).to.eq('utenteModifica');
        await acquirenteUpdatePage.save();
        expect(await acquirenteUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await acquirenteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Acquirente', async () => {
        const nbButtonsBeforeDelete = await acquirenteComponentsPage.countDeleteButtons();
        await acquirenteComponentsPage.clickOnLastDeleteButton();

        acquirenteDeleteDialog = new AcquirenteDeleteDialog();
        expect(await acquirenteDeleteDialog.getDialogTitle()).to.eq('myappjhApp.acquirente.delete.question');
        await acquirenteDeleteDialog.clickOnConfirmButton();

        expect(await acquirenteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
