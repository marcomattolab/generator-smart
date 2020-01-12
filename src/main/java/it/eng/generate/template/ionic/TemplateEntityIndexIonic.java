package it.eng.generate.template.ionic;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityIndexIonic extends AbstractResourceTemplate {

	public TemplateEntityIndexIonic(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		// https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"export * from './"+Utils.getClassNameLowerCase(tabella)+".model';\r\n" +
		"export * from './"+Utils.getClassNameLowerCase(tabella)+".service';\r\n" +
		"export * from './"+Utils.getClassNameLowerCase(tabella)+"-detail';\r\n" +
		"export * from './"+Utils.getClassNameLowerCase(tabella)+";\r\n";
		return body;
	}
	
	public String getClassName(){
		return "index";
	}

	@Override
	public String getTypeTemplate() {
		String typeTemplate = "";
		return typeTemplate;
	}

	@Override
	public String getSourceFolder() {
		return "mobile/src/app/pages/entities/"+Utils.getClassNameLowerCase(tabella);
	}

}
