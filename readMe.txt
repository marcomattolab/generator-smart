## Attività da completare

## Sezione FE
	- LOW - Completare generazione automatica classi di TEST
	- MED - Inserire logica di navigazione (ROUTING) di imprendocasa!! (si sfruttano interfaccie presenti)
	- MED - @Inserire FE componente/direttiva per tabella relazione ManyToMany (come imprendocasa) ...

## Sezione Modello JSON
	- LOW - Modello JSON => Se metto un campo CamelCase "firstName" non funge! (Deve essere tutto minuscolo "firstname")
	- LOW - Modello JSON => Colonna "id" deve essere sempre presente! (ToDo: Add automatically)
  	- LOW - Modello JSON => Develop wizard to create JSON project (see https://www.cc28tech.com/angular-multi-step-wizard-part-1/ )

## FILTRI RICERCA
	- LOW - Bug filtri Ricerca => Non funzionano i filtri RANGE sui Numerici, non vengono elaborati da BE (id.greatherThan non funge!)
	- LOW - Add filtri rierca relazioni ManyToMany
	- LOW - Ottimizzazione: Implementare filtro di ricerca come imprendocasa (select dinamiche)
			
## BUG e/o Altre Migliorie
	- HIG - @Gestire i campi BLOB / CLOB - Add altri tipologie: ImageBlob, Blob, BigDecimal, Double etc
	- LOW - BUG in IONIC (autenticazione FE JWT see Bearer not found!)
  	- LOW - Inserire controllo Pattern | MinSize | MaxSize lato backend / Hibernate
  	- HIG - Non funziona il download PDF/DOC/XLS
	
	- LOW - ==> Integrare da IONIC/Mobile le librerie camera / google-map
	- LOW - ==> Creare dahboard generata dynamicamente
	- LOW - ==> Inserire pulsanti Login nella webapp 
	- LOW - ==> Classi di test sviluppo BE / FE
 
## Migliorie / Plugin / OpenPoint
	- LOW - Inserire motore di workflow (Activity BPS, Spring workflow ....)
	- MED - Inserire vari tipi DB => Oracle!, Mysql, nosql Mongodb
	- LOW - Inserire componenti utili ==> Login Social, IFTT, Editor Mail ...
	- LOW - Inserire Google MAPS
	- LOW - HA singleton e scalabilità
	- LOW - JMS e code asincrone
	- LOW - Big Data , ML / AI	
	- LOW - Creare generatore di experience utente x GUI / UX (See https://github.com/Not-A-Builder/Design-Tools )

## Documentation Ideas         
	- See https://www.jaxio.com/en/celerio.html
	- See https://www.slideshare.net/agoncal/custom-and-generated-code-side-by-side-with-jhipster
	
## MOBILE - Comando per generare con Android studio APK:
 - ionic cordova build android --prod
 - ionic cordova build android --dev

See https://ionicframework.com/docs/developing/ios WORKS!
 - cordova platform add ios --save

## BILLING / CEDOLINI
Done ==> Scraping Java (Billing Idea) : https://www.scrapingbee.com/blog/introduction-to-web-scraping-with-java/
TODO ==> Spring Scheduler (Billing Idea) : load at startup/fixrate and save into DB
TODO ==> Add pdf viewer: https://ovpv.me/open-pdf-ionic-4/   
		 https://github.com/matheusabr/ionic-pdf/blob/master/src/pages/home/home.ts
		
## IOS deploy on device
https://7labs.io/mobile/iphone/install-apps-without-app-store.html
    
    
