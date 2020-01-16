## Attività da completare

## Sezione FE
	- LOW - Completare generazione automatica classi di TEST
	- MED - Inserire logica di navigazione (ROUTING) di imprendocasa!! (si sfruttano interfaccie presenti)
	- MED - @Inserire FE componente/direttiva per tabella relazione ManyToMany (come imprendocasa) ...

## Sezione Modello JSON
	- LOW - Modello JSON => Se metto un campo CamelCase "firstName" non funge! (Deve essere tutto minuscolo "firstname")
	- LOW - Modello JSON => Colonna "id" deve essere sempre presente! (ToDo: Add automatically)
	- LOW - Modello JSON => @@ Generare profili da file di configurazione ed abilitarli alle voci menu e menu veloce(debug)
  	- LOW - Modello JSON => Develop wizard to create JSON project (see https://www.cc28tech.com/angular-multi-step-wizard-part-1/ )

## FILTRI RICERCA
	- LOW - Bug filtri Ricerca => Non funzionano i filtri RANGE sui Numerici, non vengono elaborati da BE (id.greatherThan non funge!)
	- LOW - Add filtri rierca relazioni ManyToMany
	- LOW - Ottimizzazione: Implementare filtro di ricerca come imprendocasa (select dinamiche)
			
## BUG e/o Altre Migliorie
	- HIG - @Gestire i campi BLOB / CLOB - Add altri tipologie: ImageBlob, Blob, BigDecimal, Double etc
  	- HIG - @IONIC 4 - Gestire Relazioni (OneToMany etc) e gestire pulsanti (Edit Save Back non visibili)
	- LOW - BUG in IONIC (autenticazione FE JWT see Bearer not found!)
  	- LOW - Inserire controllo Pattern | MinSize | MaxSize lato backend / Hibernate

## Migliorie / Plugin / OpenPoint
	- LOW - Inserire motore di workflow (Activity BPS, Spring workflow ....)
	- MED - Inserire vari tipi DB => Oracle!, Mysql, nosql Mongodb
	- LOW - Inserire componenti utili ==> Login Social, IFTT, Editor Mail ...
	- LOW - Inserire Google MAPS
	- LOW - HA singleton e scalabilità
	- LOW - JMS e code asincrone
	- LOW - Big Data , ML / AI	

## Documentation Ideas         
	- See https://www.jaxio.com/en/celerio.html
	- See https://www.slideshare.net/agoncal/custom-and-generated-code-side-by-side-with-jhipster

	
	
## MOBILE: 
Comando per generare con Android studio APK:
ionic cordova build android --prod
ionic cordova build android --dev





BUG1:**
OneToMany => "sxTable":"Autore", "sxName":"clienti", "sxSelect":"firstname" , "dxTable":"Cliente", "dxName":"preferito2", "dxSelect":"nome"
Bug =>	In Edit Autore la select "bestseller" NON mostra il suo valore.


//TODO MOVE THIS SNIPPET 'updateForm' From ""ngOnInit" To "ionViewDidEnter" to load select 
 ionViewDidEnter(){
	this.activatedRoute.data.subscribe((response) => {
      this.updateForm(response.data);
      this.trasferta = response.data;
      this.isNew = this.trasferta.id === null || this.trasferta.id === undefined;
    });
}

     
BUG2:
OneToOne => "sxTable":"Autore", "sxName":"bestseller", "sxSelect":"isbn" , "dxTable":"Libro", "dxName":"writer", "dxSelect":"cognome"
Bug =>	In Edit Autore la select "bestseller" NON mostra il suo valore.


BUG3:
ManyToOne => "sxTable":"Libro", "sxName":"collana2", "sxSelect":"nome" , "dxName":"have", "dxTable":"Collana"
Bug =>		In edit Libro la select "collana2" NON mostra il suo valore.


BUG4:
ManyToMany => "sxTable":"Azienda", "sxName":"mysector2", "sxSelect":"code" , "dxTable":"Settore", "dxName":"myazienda2", "dxSelect":"nominativo"
Bug => In edit Azienda non salva il campo mysector2. Inoltre "mysector2" dovrebbe essere una multiselect.

	

