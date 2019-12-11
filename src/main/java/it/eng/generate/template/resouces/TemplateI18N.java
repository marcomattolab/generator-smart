package it.eng.generate.template.resouces;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateI18N extends AbstractResourceTemplate{
	private String languageCode;
	
	public TemplateI18N(DataBase database) {
		super(database);
	}
	
	public TemplateI18N(DataBase database, String languageCode) {
		super(database);
		this.languageCode = languageCode;
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getResi18nFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "properties";
	}

	public String getBody() {
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = "# Error page\r\n" +
		"error.title=La tua richiesta non puo' essere processata\r\n" +
		"error.subtitle= E' stato riscontrato un errore.\r\n" +
		"error.status=Stato:\r\n" +
		"error.message=Messaggio:\r\n" +
		"# Activation email\r\n" +
		"email.activation.title=Attivazione\r\n" +
		"email.activation.greeting=Caro {0}\r\n" +
		"email.activation.text1=Il tuo account e' stato creato, clicca sul link qui sotto per attivare:\r\n" +
		"email.activation.text2=Saluti,\r\n" +
		"email.signature=Smart.\r\n" +
		"# Creation email\r\n" +
		"email.creation.text1=L'account e' stato attivato, clicca sul link qui sotto per accedere:\r\n" +
		"# Reset email\r\n" +
		"email.reset.title= Reimpostazione della password\r\n" +
		"email.reset.greeting=Caro {0}\r\n" +
		"email.reset.text1=E' stata richiesta una reimpostazione della password per il tuo account, clicca sul seguente URL per ripristinarlo:\r\n" +
		"email.reset.text2=Saluti,\r\n";
		return body;
	}

	public String getClassName() {
		return "messages_" + languageCode;
	}

	public String getSourceFolder() {
		return "src/main/resources";
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
}
