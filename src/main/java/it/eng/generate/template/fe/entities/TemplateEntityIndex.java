package it.eng.generate.template.fe.entities;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.Table;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractResourceTemplate;

public class TemplateEntityIndex extends AbstractResourceTemplate {

	public TemplateEntityIndex(Table tabella) {
		super(tabella);
	}

	public String getTypeFile() {
		return "ts";
	}

	public String getBody(){
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		// https://www.buildmystring.com/
		String body = 
		"export * from './"+Utils.getClassNameLowerCase(tabella)+".service';\r\n" +
		"export * from './"+Utils.getClassNameLowerCase(tabella)+"-update.component';\r\n" +
		"export * from './"+Utils.getClassNameLowerCase(tabella)+"-delete-dialog.component';\r\n" +
		"export * from './"+Utils.getClassNameLowerCase(tabella)+"-detail.component';\r\n" +
		"export * from './"+Utils.getClassNameLowerCase(tabella)+".component';\r\n" +
		"export * from './"+Utils.getClassNameLowerCase(tabella)+".route';\r\n";
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
		return "src/main/webapp/app/entities/"+Utils.getClassNameLowerCase(tabella);
	}

}
