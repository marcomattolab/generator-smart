package it.eng.generate.template.fe.i18n;

import java.util.List;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateGlobalI18N extends AbstractResourceTemplate{
	private String languageCode;
	
	public TemplateGlobalI18N(DataBase database) {
		super(database);
	}
	
	public TemplateGlobalI18N(DataBase database, String languageCode) {
		super(database);
		this.languageCode = languageCode;
	}

	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	public String getTypeFile() {
		return "json";
	}

	public String getBody() {
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		List<String> authorities = Utils.getGlobalAuthoritiesCredential(conf);
		String body = 
		"{\r\n" +
		"    \"global\": {\r\n" +
		"        \"title\": \""+Utils.getClassNameCamelCase(conf.getProjectName())+"\",\r\n" +
		"        \"browsehappy\":\r\n" +
		"            \"Stai utilizzando un browser <strong> non aggiornato </ strong> <a href=\\\"http://browsehappy.com/?locale=it\\\"> Aggiorna il tuo browser per </a> migliorare la tua esperienza.\",\r\n" +
		"        \"menu\": {\r\n" +
		"            \"home\": \"Home\",\r\n" +
		
		"            \"dashboard\": {\r\n" +
		"                \"main\": \"Dashboard\",\r\n" +
		"                \"barchart\": \"BarChart\",\r\n" +
		"                \"doughnutchart\": \"DoughnutChart\",\r\n" +
		"                \"linechart\": \"LineChart\",\r\n" +
		"                \"piechart\": \"PieChart\",\r\n" +
		"                \"polarareachart\": \"PolarAreaChart\",\r\n" +
		"                \"radarchart\": \"RadarChart\"\r\n" +
		"            },\r\n"+

		"            \"jhipster-needle-menu-add-element\": \"JH will add additional menu entries here (do not translate!)\",\r\n" +
		"            \"entities\": {\r\n" ;
		
		List<Table> tabelle = Utils.getTables(database);
		for (Table tabella: tabelle) {
			String NomeTabella = Utils.getEntityName(tabella); 
			String nometabella = Utils.getClassNameLowerCase(tabella); 
			body += "\t\t\t\t\""+nometabella+"\": \""+NomeTabella+"\",\r\n";
		}
		body += "\t\t\t\t\"main\": \"Entità\",\r\n";
		
		body +=
		"                \"jhipster-needle-menu-add-entry\": \"JH will add additional entities here (do not translate!)\"\r\n" +
		"            },\r\n" +
		"            \"account\": {\r\n" +
		"                \"main\": \"Utente\",\r\n" +
		"                \"settings\": \"Impostazioni\",\r\n" +
		"                \"password\": \"Password\",\r\n" +
		"                \"sessions\": \"Sessioni\",\r\n" +
		"                \"login\": \"Accesso\",\r\n" +
		"                \"logout\": \"Esci\",\r\n" +
		"                \"register\": \"Registrazione\"\r\n" +
		"            },\r\n" +
		"            \"admin\": {\r\n" +
		"                \"main\": \"Amministratore\",\r\n" +
		"                \"userManagement\": \"Gestione utenti\",\r\n" +
		"                \"tracker\": \"Tracciamento utente\",\r\n" +
		"                \"metrics\": \"Metriche\",\r\n" +
		"                \"health\": \"Integrità\",\r\n" +
		"                \"configuration\": \"Configurazione\",\r\n" +
		"                \"logs\": \"Log\",\r\n" +
		"                \"audits\": \"Cambiamenti\",\r\n" +
		"                \"apidocs\": \"API\",\r\n" +
		"                \"database\": \"Database\",\r\n" +
		"                \"entity-audit\": \"Entity Audit\",\r\n" +
		"                \"jhipster-needle-menu-add-admin-element\": \"JH will add additional menu entries here (do not translate!)\"\r\n" +
		"            },\r\n" +
		"            \"language\": \"Lingua\"\r\n" +
		"        },\r\n" +
		"        \"search\": {\r\n" +
		"            \"buttonLabel\": \"Cerca\",\r\n" +
		"            \"filtersLabel\": \"Filtri Ricerca\",\r\n" +
		"            \"clearfiltersLabel\": \"Pulisci Filtri\"\r\n" +
		"        },\r\n"+
		"        \"form\": {\r\n" +
		"            \"username\": \"Utente\",\r\n" +
		"            \"username.placeholder\": \"Il tuo nome utente\",\r\n" +
		"            \"currentpassword\": \"Current password\",\r\n" +
		"            \"currentpassword.placeholder\": \"Current password\",\r\n" +
		"            \"newpassword\": \"Nuova password\",\r\n" +
		"            \"newpassword.placeholder\": \"Nuova password\",\r\n" +
		"            \"confirmpassword\": \"Conferma nuova password\",\r\n" +
		"            \"confirmpassword.placeholder\": \"Conferma nuova password\",\r\n" +
		"            \"email\": \"Email\",\r\n" +
		"            \"email.placeholder\": \"Il tuo indirizzo email\"\r\n" +
		"        },\r\n" +
		"        \"messages\": {\r\n" +
		"            \"info\": {\r\n" +
		"                \"authenticated\": {\r\n" +
		"                    \"prefix\": \"Se vuoi \",\r\n" +
		"                    \"link\": \"accedere\",\r\n" +
		"                    \"suffix\":\r\n"+

		
		"                        \", puoi provare gli account di default:";
		for(String credential: authorities) {
			body+= "<br/>- "+Utils.getFirstUpperCase(credential)+" (login='"+credential+"' and password='"+credential+"') ";
		}
		body+= "\"\n";
		
		
		body+=
		"                },\r\n" +
		"                \"register\": {\r\n" +
		"                    \"noaccount\": \"Non hai ancora un account?\",\r\n" +
		"                    \"link\": \"Crea un account\"\r\n" +
		"                }\r\n" +
		"            },\r\n" +
		"            \"error\": {\r\n" +
		"                \"dontmatch\": \"La password e la sua conferma non corrispondono!\"\r\n" +
		"            },\r\n" +
		"            \"validate\": {\r\n" +
		"                \"newpassword\": {\r\n" +
		"                    \"required\": \"La password è obbligatoria.\",\r\n" +
		"                    \"minlength\": \"La password deve essere di almeno 4 caratteri\",\r\n" +
		"                    \"maxlength\": \"La password non può contenere più di 50 caratteri\",\r\n" +
		"                    \"strength\": \"Robustezza della password:\"\r\n" +
		"                },\r\n" +
		"                \"confirmpassword\": {\r\n" +
		"                    \"required\": \"La password di conferma è obbligatoria.\",\r\n" +
		"                    \"minlength\": \"La tua password deve essere di almeno 4 caratteri\",\r\n" +
		"                    \"maxlength\": \"La tua password non può contenere più di 50 caratteri\"\r\n" +
		"                },\r\n" +
		"                \"email\": {\r\n" +
		"                    \"required\": \"L'indirizzo email è obbligatorio.\",\r\n" +
		"                    \"invalid\": \"L'indirizzo email non è valido.\",\r\n" +
		"                    \"minlength\": \"L'indirizzo email deve essere di almeno 5 caratteri\",\r\n" +
		"                    \"maxlength\": \"L'indirizzo email non può contenere più di 50 caratteri\"\r\n" +
		"                }\r\n" +
		"            }\r\n" +
		"        },\r\n" +
		"        \"field\": {\r\n" +
		"            \"id\": \"ID\"\r\n" +
		"        },\r\n" +
		"        \"ribbon\": {\r\n" +
		"            \"dev\": \"Development\"\r\n" +
		"        },\r\n" +
		"        \"item-count\": \"Mostra {{first}} - {{second}} di {{total}} articoli.\"\r\n" +
		"    },\r\n" +
		"    \"entity\": {\r\n" +
		"        \"action\": {\r\n" +
		"            \"addblob\": \"Aggiungi blob\",\r\n" +
		"            \"addimage\": \"Aggiungi immagine\",\r\n" +
		"            \"back\": \"Indietro\",\r\n" +
		"            \"cancel\": \"Annulla\",\r\n" +
		"            \"delete\": \"Elimina\",\r\n" +
		"            \"edit\": \"Modifica\",\r\n" +
		"            \"open\": \"Open\",\r\n" +
		"            \"save\": \"Salva\",\r\n" +
		"            \"view\": \"Visualizza\"\r\n" +
		"        },\r\n" +
		"        \"detail\": {\r\n" +
		"            \"field\": \"Campo\",\r\n" +
		"            \"value\": \"Valore\"\r\n" +
		"        },\r\n" +
		"        \"delete\": {\r\n" +
		"            \"title\": \"Conferma operazione di cancellazione\"\r\n" +
		"        },\r\n" +
		"        \"validation\": {\r\n" +
		"            \"required\": \"Questo campo è obbligatorio.\",\r\n" +
		"            \"minlength\": \"Questo campo deve essere di almeno {{ min }} caratteri.\",\r\n" +
		"            \"maxlength\": \"Questo campo non può essere più di {{ max }} caratteri.\",\r\n" +
		"            \"min\": \"Questo campo dovrebbe essere più di {{ min }}.\",\r\n" +
		"            \"max\": \"Questo campo non può essere superiore a {{ max }}.\",\r\n" +
		"            \"minbytes\": \"Questo campo dovrebbe essere più di {{ min }} byte.\",\r\n" +
		"            \"maxbytes\": \"Questo campo non può essere superiore a {{ max }} byte.\",\r\n" +
		"            \"pattern\": \"Questo campo dovrebbe corrispondere al modello {{ pattern }}.\",\r\n" +
		"            \"number\": \"Questo campo dovrebbe essere un numero.\",\r\n" +
		"            \"datetimelocal\": \"Questo campo dovrebbe essere data e ora.\"\r\n" +
		"        }\r\n" +
		"    },\r\n" +
		"    \"error\": {\r\n" +
		"        \"internalServerError\": \"Internal server error\",\r\n" +
		"        \"server.not.reachable\": \"Server non raggiungibile\",\r\n" +
		"        \"url.not.found\": \"Non trovato\",\r\n" +
		"        \"NotNull\": \"Il campo {{ fieldName }} non può essere vuoto!\",\r\n" +
		"        \"Size\": \"Il campo {{ fieldName }} non è conforme ai requisiti di lunghezza min/max!\",\r\n" +
		"        \"userexists\": \"Il nome utente è già in uso!\",\r\n" +
		"        \"emailexists\": \"L'email è già in uso!\",\r\n" +
		"        \"idexists\": \"Un(a) nuovo/a {{entityName}} non può già avere un ID\",\r\n" +
		"        \"idnull\": \"Invalid ID\"\r\n" +
		"    },\r\n" +
		"    \"footer\": \"Piè di pagina\"\r\n" +
		"}\r\n";

		return body;
	}

	public String getClassName() {
		return "global";
	}

	public String getSourceFolder() {
		return "src/main/webapp/i18n/"+languageCode;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
}
