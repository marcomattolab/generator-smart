package it.eng.generate.template.resouces;

import it.eng.generate.DataBase;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateBanner extends AbstractResourceTemplate{
	
	public TemplateBanner(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = ""; 
		return typeTemplate;
	}
	
	public String getTypeFile() {
		return "txt";
	}

	public String getBody() {
		String body = "Smart Generator";
		return body;
	}

	public String getClassName() {
		return "banner";
	}
	
	public String getSourceFolder() {
		return "src/main/resources";
	}

}
