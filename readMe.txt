-----------------------------------
#Sommario Attività da completare
#Sezione BE
	- Gestire le varie tipologie di relazioni (1..1,   0...N,   N...M )
	
#Sezione FE
	- Completare generazione automatica classi di TEST (LOW)
	- Gestire le varie tipologie di relazioni (1..1,   0...N,   N...M ) scelta componente grafico
	- Inserire logica di navigazione (ROUTING) di imprendocasa!! (si sfruttano interfaccie presenti)
	- Generare filtri di ricerca da file di configurazione (ricerca per testo, range date, enumeration, range valori numerici)
	
#Migliorie / Plugin
    - Creazione XML / UML per definire progetto prima della creazione con ENUMERATION, DASHBOARD, RUOLI ...!!!
	- Inserire motore di workflow (Activity BPS , Spring workflow ....)
	- Inserire vari tipi DB => Oracle!, Mysql, nosql Mongodb
	- Inserire componenti utili ==> Login Social, IFTT, Editor Mail ...
	- Inserire Google MAPS

#Punti Aperti
	- IONIC 4
	- HA singleton e scalabilità
	- JMS e code asincrone
	- BPS
	- Big Data
	- IA	
--------------------------------

## BUG Ricerca  (FILTRI RICERCA)
1) Gestire i campi BLOB  / CLOB
2) Non funzionano i filtri RANGE sulle DATE, ed i filtri numerici non vengono inviati dal FE!!
3) ==> Modello JSON => se metto un campo "firstName" camel case non funge! (Deve essere tutto minuscolo)
4) ==> Modello JSON => "id" deve essere sempre presente!
5) ==> Nascondere Id duplicato da GUI
6) ==> Aggiungere Costanti nel JSON (SIZE_MAX=189, SIZE_DEFAULT=100)
7) Aggiungere altri tipologie: ImageBlob, Blob
8) Agganciare enumeration

9) COMPLETARE MODELLO JSON 
- unique 
- minlength(5) 
- maxlength(13)  
- COSTANTI => minlength(SIZE_MAX)  


- Relations =>  OneToOne*  ManyToMany   OneToMany  ManyToOne*
- Stampa PDF/XLS inserire criteria come filtri ricerca e migliorare layout
    
    
    