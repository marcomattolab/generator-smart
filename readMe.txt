## Sommario: Attività da completare

## Sezione FE
	- Completare generazione automatica classi di TEST (LOW)
	- Gestire le varie tipologie di relazioni (1..1,   0...N,   N...M ) scelta componente grafico
	- Inserire logica di navigazione (ROUTING) di imprendocasa!! (si sfruttano interfaccie presenti)
	- Generare filtri di ricerca da file di configurazione (ricerca per testo, range date, enumeration, range valori numerici)
	
## Migliorie / Plugin
    - Creazione XML / UML per definire progetto prima della creazione con ENUMERATION, DASHBOARD, RUOLI ...!!!
	- Inserire motore di workflow (Activity BPS, Spring workflow ....)
	- Inserire vari tipi DB => Oracle!, Mysql, nosql Mongodb
	- Inserire componenti utili ==> Login Social, IFTT, Editor Mail ...
	- Inserire Google MAPS

## Sezione Modello JSON
	- Modello JSON => Se metto un campo CamelCase "firstName" non funge! (Deve essere tutto minuscolo "firstname")
	- Modello JSON => Colonna "id" deve essere sempre presente! (ToDo: Add automatically)
	- COMPLETARE MODELLO JSON: Aggiungere Costanti nel JSON (SIZE_MAX=189, SIZE_DEFAULT=100)
		+ unique, minlength(5) e maxlength(13) e COSTANTI => es minlength(SIZE_MAX)  
		+ @Add Validation Pattern (Mail, URL, Phone etc) 
			++ => email String pattern(/^[^@\s]+@[^@\s]+\.[^@\s]+$/) required
			++ => urlSite String pattern(/^www.[^@\s]+\.[^@\s]+$/)

## BUG e/o Altre Migliorie
	- Gestire i campi BLOB / CLOB - Aggiungere altri tipologie: ImageBlob, Blob
	- @Bug filtri Ricerca => Non funzionano i filtri RANGE su DATE, ed i filtri numerici non vengono inviati dal FE!!
	- @Relations => OneToOne*  ManyToMany^   OneToMany***  ManyToOne**
	- @Relations => Test/Fix relazioni su medesima tabella con naming differente! ( ? ==> OneToMany e ManyToMany)
	- @Stampa PDF/XLS inserire criteria come filtri ricerca e migliorare layout PDF stampato
  	- Creare scheletro IONIC 4
  			
## Punti Aperti
	- HA singleton e scalabilità
	- JMS e code asincrone
	- BPS
	- Big Data
	- IA	
	
	