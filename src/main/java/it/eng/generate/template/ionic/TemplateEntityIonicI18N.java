package it.eng.generate.template.ionic;

import org.springframework.util.CollectionUtils;

import it.eng.generate.Column;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.ProjectRelation;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityIonicI18N extends AbstractResourceTemplate {
	private String languageCode;
	
	public TemplateEntityIonicI18N(DataBase database) {
		super(database);
	}
	
	public TemplateEntityIonicI18N(DataBase database, String languageCode) {
		super(database);
		this.languageCode = languageCode;
	}

	public String getBody() {
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"{\r\n" + 
		"  \"BACK_BUTTON_TEXT\": \"Indietro\",\r\n" + 
		"  \"CANCEL_BUTTON\": \"Annulla\",\r\n" + 
		"  \"CARDS_TITLE\": \"Social\",\r\n" + 
		"  \"DELETE_BUTTON\": \"Elimina\",\r\n" + 
		"  \"DONE_BUTTON\": \"Fatto\",\r\n" + 
		"  \"EMAIL\": \"Email\",\r\n" + 
		"  \"EMPTY_ENTITIES_MESSAGE\": \"Non hai generato nulla.\",\r\n" + 
		"  \"EXISTING_USER_ERROR\": \"Email utilizzata! Per favore scegli un altro indirizzo.\",\r\n" + 
		"  \"ID\": \"Id\",\r\n" + 
		"  \"FIRST_NAME\": \"Nome\",\r\n" + 
		"  \"INVALID_PASSWORD_ERROR\": \"Password non valida, riprova.\",\r\n" + 
		"  \"ITEM_ABOUT_PLACEHOLDER\": \"Informazioni\",\r\n" + 
		"  \"ITEM_CREATE_CHOOSE_IMAGE\": \"Aggiungi immagine\",\r\n" + 
		"  \"ITEM_CREATE_TITLE\": \"Nuovo elemento\",\r\n" + 
		"  \"ITEM_NAME_PLACEHOLDER\": \"Nome\",\r\n" + 
		"  \"LAST_NAME\": \"Cognome\",\r\n" + 
		"  \"LIST_MASTER_TITLE\": \"Elenco elementi\",\r\n" + 
		"  \"LOGIN\": \"Login\",\r\n" + 
		"  \"LOGIN_BUTTON\": \"Accedi\",\r\n" + 
		"  \"LOGIN_ERROR\": \"Non e' possibile accedere. Ricontrollare le informazioni utente digitate prima di riprovare.\",\r\n" + 
		"  \"LOGIN_TITLE\": \"Login\",\r\n" + 
		"  \"LOGOUT_TITLE\": \"Logout\",\r\n" + 
		"  \"MAP_TITLE\": \"Mappa\",\r\n" + 
		"  \"NAME\": \"Nome\",\r\n" + 
		"  \"NO_ACCOUNT_MESSAGE\": \"Non hai ancora un account?\",\r\n" + 
		"  \"PASSWORD\": \"Password\",\r\n" + 
		"  \"REMEMBER_ME\": \"Ricordami\",\r\n" + 
		"  \"SEARCH_PLACEHOLDER\": \"Trova elementi in lista, ex. \\\"Item 1\\\"\",\r\n" + 
		"  \"SEARCH_TITLE\": \"Ricerca\",\r\n" + 
		"  \"SETTINGS_OPTION1\": \"Opzione 1\",\r\n" + 
		"  \"SETTINGS_OPTION2\": \"Opzione 2\",\r\n" + 
		"  \"SETTINGS_OPTION3\": \"Opzione 3\",\r\n" + 
		"  \"SETTINGS_OPTION4\": \"Opzione 4\",\r\n" + 
		"  \"SETTINGS_PAGE_PROFILE\": \"Modifica Profilo\",\r\n" + 
		"  \"SETTINGS_PROFILE_BUTTON\": \"Modifica Profilo\",\r\n" + 
		"  \"SETTINGS_TITLE\": \"Impostazioni\",\r\n" + 
		"  \"SIGNUP\": \"Registrati\",\r\n" + 
		"  \"SIGNUP_BUTTON\": \"Registrati\",\r\n" + 
		"  \"SIGNUP_ERROR\": \"Non e' possibile creare l'utente. Assicurarsi di aver inserito le informazioni utente correttamente e poi riprovare.\",\r\n" + 
		"  \"SIGNUP_SUCCESS\": \"Registrazione salvata! Verifica sulla tua mail per conferma.\",\r\n" + 
		"  \"SIGNUP_TITLE\": \"Registrazione\",\r\n" + 
		"  \"TAB1_TITLE\": \"Elenco\",\r\n" + 
		"  \"TAB2_TITLE\": \"Ricerca\",\r\n" + 
		"  \"TAB3_TITLE\": \"Utente\",\r\n" + 
		
		buildTables(conf) +
		
		"  \"TUTORIAL_CONTINUE_BUTTON\": \"Continua\",\r\n" + 
		"  \"TUTORIAL_SKIP_BUTTON\": \"Salta\",\r\n" + 
		"  \"TUTORIAL_SLIDE1_DESCRIPTION\": \"<b>Ionic Super Starter</b> E' un applicazione di partenza per Ionic che contiene molte pagine di esempio gia'  pronte realizzate seguendo delle best practices.\",\r\n" + 
		"  \"TUTORIAL_SLIDE1_TITLE\": \"Benvenuti in Ionic Super Starter\",\r\n" + 
		"  \"TUTORIAL_SLIDE2_DESCRIPTION\": \"Mantenete le pagine che volete e rimuovete quelle che non vi servono. Troverete diverse pagine di uso comune in questa applicazione come le pagine per il login e la registrazione, layout con tabs, e questa pagina di tutorial.\",\r\n" + 
		"  \"TUTORIAL_SLIDE2_TITLE\": \"Come usare Super Starter\",\r\n" + 
		"  \"TUTORIAL_SLIDE3_DESCRIPTION\": \"Serve aiuto? Leggete il README del progetto Super Starter per avere maggiori informazioni sul tutorial completo.\",\r\n" + 
		"  \"TUTORIAL_SLIDE3_TITLE\": \"Come iniziare\",\r\n" + 
		"  \"TUTORIAL_SLIDE4_TITLE\": \"Pronti ad iniziare?\",\r\n" + 
		"  \"USERNAME\": \"Nome utente\",\r\n" + 
		"  \"WELCOME_INTRO\": \"Benvenuto nell'applicazione Ionic generata con il tool Generator Smart di Marco Martorana.\",\r\n" + 
		"  \"WELCOME_TITLE\": \"Benvenuto\"\r\n"+
		"}\r\n"; 
		return body;
	}

	
	private String buildTables(ConfigCreateProject conf) {
		String result = "";
		
		//CICLE TABLES
		for (Table tabella : Utils.getTables(database)) {
			String TABLENAME = Utils.getCostantName(tabella);
			String Tablename = Utils.getEntityName(tabella);
			String tablename = Utils.getFieldName(tabella);
			
			result += "  \""+TABLENAME+"\": {\n";
			int i = 0;
			int size = CollectionUtils.isEmpty(tabella.getColumns()) ? 0 : tabella.getColumns().size();
			
			//CICLE RELATIONS
			if(!CollectionUtils.isEmpty(conf.getProjectRelations())) {
				for(ProjectRelation rel: conf.getProjectRelations()) {
					String relationType = rel.getType();
					String nomeTabellaSx = rel.getSxTable();
					String nomeRelazioneSx = rel.getSxName();
					String nomeRelazioneDx = rel.getDxName();
					String nomeTabellaDx = rel.getDxTable();
					String nomeTabella = tabella.getNomeTabella().toLowerCase();
					
					if(nomeTabellaSx!=null && nomeTabellaDx != null) {
						if (relationType.equals(Utils.OneToOne) || relationType.equals(Utils.ManyToOne)) {
							if (nomeTabellaSx.toLowerCase().equals(nomeTabella) ) {
								Column column = new Column();
								column.setName(nomeRelazioneSx);
								result += Utils.generateJsonUppercase(column)+",\n";
							}
						
						} else if (relationType.equals(Utils.OneToMany) ) {
							if (nomeTabellaDx.toLowerCase().equals(nomeTabella.toLowerCase()) ) {
								Column column = new Column();
								column.setName(nomeRelazioneDx);
								result += Utils.generateJsonUppercase(column)+",\n";
							}
							
						} else if (relationType.equals(Utils.ManyToMany) ) {
							if (nomeTabellaSx.toLowerCase().equals(nomeTabella.toLowerCase()) ) {
								Column column = new Column();
								column.setName(nomeRelazioneSx);
								result +=  Utils.generateJsonUppercase(column)+",\n";
							}
						}
					}
				}
			}
			//CICLE RELATIONS
			
			//CICLE COLUMNS
			for (Column column : tabella.getSortedColumns()) { 
				String ColumnName = Utils.getFieldNameForMethod(column);
				result += Utils.generateJsonUppercase(column) + ( i+1<size ? ",\n" : "\n");
				i++;
			}
			//CICLE COLUMNS
			
			result += "  },\n"; 
		}
		//CICLE TABLES
		
		return result;
	}

	public String getClassName() {
		return ""+languageCode;
	}

	public String getSourceFolder() {
		return "mobile/src/assets/i18n/";
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	public String getTypeFile() {
		return "json";
	}
	
}
