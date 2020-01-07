## Sommario: Attività da completare

## Sezione FE
	- LOW - Completare generazione automatica classi di TEST
	- MED - Inserire logica di navigazione (ROUTING) di imprendocasa!! (si sfruttano interfaccie presenti)
	- MED - @Inserire FE componente tabella come imprendocasa...
	- LOW - Generare filtri di ricerca da file di configurazione (ricerca per testo, range date, enumeration, range valori numerici)
	- LOW - Generare profili da file di configurazione ed abilitarli alle voci menu
	
## Migliorie / Plugin
	- MED - Inserire motore di workflow (Activity BPS, Spring workflow ....)
	- MED - Inserire vari tipi DB => Oracle!, Mysql, nosql Mongodb
	- LOW - Inserire componenti utili ==> Login Social, IFTT, Editor Mail ...
	- LOW - Inserire Google MAPS

## Sezione Modello JSON
	- LOW - Modello JSON => Se metto un campo CamelCase "firstName" non funge! (Deve essere tutto minuscolo "firstname")
	- LOW - Modello JSON => Colonna "id" deve essere sempre presente! (ToDo: Add automatically)
	- LOW - COMPLETARE MODELLO JSON: Aggiungere Costanti nel JSON (SIZE_MAX=189, SIZE_DEFAULT=100) => minlength(SIZE_MAX) 

## BUG e/o Altre Migliorie
	- HIG - @Gestire i campi BLOB / CLOB - Aggiungere altri tipologie: ImageBlob, Blob, BigDecimal, Double etc
	- HIG - @Bug filtri Ricerca => Non funzionano i filtri RANGE su DATE, ed i filtri numerici non vengono inviati dal FE!!
	- HIG - @@Relations => Test/Fix relazioni su medesima tabella con naming differente! ( ? ==> OneToMany e)
	- LOW - Stampa PDF/XLS inserire criteria come filtri ricerca e migliorare layout PDF stampato
  	- HIG - ==> Creare scheletro IONIC 4
  	- LOW - Develop wizard per creare JSON progetto (vedi https://www.cc28tech.com/angular-multi-step-wizard-part-1/ )
  	- LOW - Inserire controllo Pattern MinSize e MaxSize via BE/H8
  	
## Punti Aperti
	- HA singleton e scalabilità
	- JMS e code asincrone
	- Big Data , ML / AI	
	

