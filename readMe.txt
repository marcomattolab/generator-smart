## Attività da completare

## Sezione FE
	- LOW - Completare generazione automatica classi di TEST
	- MED - Inserire logica di navigazione (ROUTING) di imprendocasa!! (si sfruttano interfaccie presenti)
	- MED - @Inserire FE componente tabella come imprendocasa...

## Sezione Modello JSON
	- LOW - Modello JSON => Se metto un campo CamelCase "firstName" non funge! (Deve essere tutto minuscolo "firstname")
	- LOW - Modello JSON => Colonna "id" deve essere sempre presente! (ToDo: Add automatically)
	- LOW - Modello JSON => Generare profili da file di configurazione ed abilitarli alle voci menu e menu veloce(debug)
  	- LOW - Modello JSON => Develop wizard to create JSON project (see https://www.cc28tech.com/angular-multi-step-wizard-part-1/ )

## FILTRI RICERCA@
	2. @Bug filtri Ricerca => Non funzionano i filtri RANGE su DATE, ed i filtri numerici non vengonoelaborati da BE (id.greatherThan non funge)
	3. Add filtri rierca relazioni OneToMany,.. etc
	4. Stampa PDF/XLS inserire criteria come filtri ricerca 
			
## BUG e/o Altre Migliorie
	- HIG - @Gestire i campi BLOB / CLOB - Add altri tipologie: ImageBlob, Blob, BigDecimal, Double etc
  	- HIG - @Creare scheletro IONIC 4
  	- LOW - Inserire controllo Pattern | MinSize | MaxSize via BE/Hibernate

## Migliorie / Plugin / OpenPoint
	- MED - Inserire motore di workflow (Activity BPS, Spring workflow ....)
	- MED - Inserire vari tipi DB => Oracle!, Mysql, nosql Mongodb
	- LOW - Inserire componenti utili ==> Login Social, IFTT, Editor Mail ...
	- LOW - Inserire Google MAPS
	- HA singleton e scalabilità
	- JMS e code asincrone
	- Big Data , ML / AI	
	

	
	@ ONE TO ONE FILTER
	<!-- SearchFilter Add Relation: OneToOne / ManyToOne  -->                        
	<div class="col-md-4">
        <label jhiTranslate="demogeneratedApp.autore.bestseller">bestseller</label>
        <select class="form-control" id="field_proponente"
                formControlName="proponenteId" name="committente">
            <option *ngFor="let proponente of proponenti" ngDefaultControl
                    [value]="proponente.id">
                {{proponente.nome}} {{proponente.cognome}}
            </option>
        </select>
    </div>
    
    
    import {
    checkAndCompileSearchBetween,
    checkAndCompileSearchFilterContains, checkAndCompileSearchFilterEquals,
    checkAndCompileSearchFilterGt,
    checkAndCompileSearchFilterLt,
    ITEMS_PER_PAGE
} from 'app/shared'


 		// filtro agente
        searchFilter = checkAndCompileSearchFilterContains(formClienteControls, searchFilter, 'agente');
        // filtro partner
        searchFilter = checkAndCompileSearchFilterEquals(formClienteControls, searchFilter, 'partnerId');
        // filtroPrezzo
		searchFilter = checkAndCompileSearchBetween(formClienteControls, searchFilter, 'prezzoAcquistoD', 'prezzoAcquistoA', 'prezzoAcquisto');
        
        
        
        
        <jhi-selezione-cliente (clienteListChanges)="onCommittentiChanges($event)"
                                               label="{{'imprendocasaApp.incarico.committente' | translate}}"
                                               buttonLabel="{{'imprendocasaApp.incarico.committenteNuovo' | translate}}"
                                               [incaricoId]="getIncaricoId()"
                                               [tag]="tagEnum.COMMITTENTE"
                                               [completeClienteList]="committenti"
                                               [incaricoClienteList]="incarico.committentes"></jhi-selezione-cliente>
              ...............
              
              
              
              FILTER 
              <div class="col-md-4">
                            <div class="form-group">
                                <label jhiTranslate="imprendocasaApp.listaContatti.incarico">Incarico</label>
                                <input formControlName="incarico" id="typeahead-template-incarico" type="text"
                                       class="form-control" [ngbTypeahead]="searchIncarico"
                                       [inputFormatter]="incaricoFormatter" [resultFormatter]="incaricoFormatter"/>
                            </div>
                        </div>
                        
                       ......
        searchIncarico = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            map(term => term === '' ? []
                : this.incaricos.filter(v => v.riferimento.toLowerCase().indexOf(term.toLowerCase()) > -1
                    || v.tipo.toLowerCase().indexOf(term.toLowerCase()) > -1 || v.id > -1).slice(0, 10))
        );

    // clienteFormatter = (x: Cliente) => x ? (x.cognome + ' ' + x.nome + ' (' + x.codiceFiscale + ')') : '';
    clienteFormatter = (x: Cliente) => x ? (x.cognome + ' ' + x.nome) : '';
    incaricoFormatter = (x: Incarico) => x ? (x.riferimento + ' ' + x.tipo + ' (' + x.id + ') ') : '';
                       
                        
                        
                        
                                                         
                                               
        
        
                            