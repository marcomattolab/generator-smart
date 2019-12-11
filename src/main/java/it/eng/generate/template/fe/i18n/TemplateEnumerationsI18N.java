package it.eng.generate.template.fe.i18n;

import java.util.Iterator;
import java.util.List;
import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Enumeration;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEnumerationsI18N extends AbstractResourceTemplate{
	private String languageCode;
	
	public TemplateEnumerationsI18N(Enumeration enumeration, String languageCode) {
		super(enumeration);
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
		//https://www.buildmystring.com/
		String body = 
		"{\r\n" +
		"    \""+conf.getProjectName()+"App\": {\r\n" +
		"        \""+enumeration.getNomeEnumeration()+"\": {\r\n" +
		"            \"null\": \"\",\r\n";
		List<String> values = enumeration.getValoriEnumeration();
		for (Iterator it = values.iterator(); it.hasNext();) {
			String value = (String) it.next();
			body += "            \""+value+"\": \""+value+"\" " + (it.hasNext()?",\n":"\n");
		}
		body +=
		"        }\r\n" +
		"    }\r\n" +
		"}\r\n";
		return body;
	}

	public String getClassName() {
		return enumeration.getNomeEnumeration().toLowerCase();
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
